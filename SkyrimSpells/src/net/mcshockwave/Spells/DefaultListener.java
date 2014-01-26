package net.mcshockwave.Spells;

import java.util.HashMap;

import net.mcshockwave.Spells.Spell.SpellType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class DefaultListener implements Listener {

	public HashMap<Location, BukkitTask>	rune		= new HashMap<>();
	public HashMap<Player, BukkitTask>		cloak		= new HashMap<>();
	public HashMap<Player, BukkitTask>		cloakDis	= new HashMap<>();

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		Action a = event.getAction();
		ItemStack it = event.getItem();

		if (a.name().contains("RIGHT_CLICK")) {
			for (Spell s2 : Spell.values()) {
				if (s2.item.isSimilar(it)) {
					event.setCancelled(true);

					final Spell s = s2;
					Bukkit.getScheduler().runTaskLater(SpellsSkyrim.ins, new Runnable() {
						public void run() {
							if (s.type == SpellType.Spray) {
								final Spell s2 = s;

								for (int i = 0; i < 4; i++) {
									new BukkitRunnable() {
										public void run() {
											s2.cast(p);
										}
									}.runTaskLater(SpellsSkyrim.ins, i * 4);
								}
							} else if (s.type == SpellType.Bolt) {
								s.cast(p);
							} else if (s.type == SpellType.Rune) {
								@SuppressWarnings("deprecation")
								Block b = p.getTargetBlock(null, 8);

								final Location l = b.getLocation();
								if (!b.getType().isTransparent() && !rune.containsKey(l)) {
									rune.put(l, new BukkitRunnable() {
										public void run() {
											s.onRuneUpdate(p, l);
											boolean hit = false;
											for (Entity e : l.getWorld().getEntities()) {
												if (!(e instanceof LivingEntity))
													continue;

												LivingEntity le = (LivingEntity) e;
												if (le != p && le.getLocation().distance(l) <= 4) {
													if (!hit) {
														s.onRuneExplode(p, l);
														hit = true;
													}
													s.onRuneHitEntity(p, le);
													rune.get(l).cancel();
													rune.remove(l);
												}
											}
										}
									}.runTaskTimer(SpellsSkyrim.ins, 0, 10));
								} else
									p.sendMessage("§cCould not place rune at target");
							} else if (s.type == SpellType.Cloak) {
								if (cloak.containsKey(p)) {
									cloak.get(p).cancel();
									cloak.remove(p);
								}
								if (cloakDis.containsKey(p)) {
									cloakDis.get(p).cancel();
									cloakDis.remove(p);
								}
								cloak.put(p, new BukkitRunnable() {
									public void run() {
										s.onCloakUpdate(p);
									}
								}.runTaskTimer(SpellsSkyrim.ins, 0, 5));
								cloakDis.put(p, Bukkit.getScheduler().runTaskLater(SpellsSkyrim.ins, new Runnable() {
									public void run() {
										s.onCloakDissipate(p);
										cloak.get(p).cancel();
										cloak.remove(p);
									}
								}, s.duration));
							} else if (s.type == SpellType.Self) {
								s.castAtTarget(p, p);
							}
						}
					}, s.castTime);
				}
			}
		}
	}

}

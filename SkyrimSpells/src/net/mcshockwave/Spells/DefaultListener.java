package net.mcshockwave.Spells;

import net.mcshockwave.Spells.Spell.SpellType;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class DefaultListener implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		Action a = event.getAction();
		ItemStack it = event.getItem();

		if (a.name().contains("RIGHT_CLICK")) {
			event.setCancelled(true);

			for (Spell s : Spell.values()) {
				if (s.item.isSimilar(it)) {
					if (s.type == SpellType.Spray) {
						final Spell s2 = s;
						
						for (int i = 0; i < 4; i++) {
							new BukkitRunnable() {
								public void run() {
									s2.cast(p);
								}
							}.runTaskLater(SpellsSkyrim.ins, i * 4);
						}
					} else if (s.type == SpellType.Bolt)
						s.cast(p);
				}
			}
		}
	}

}

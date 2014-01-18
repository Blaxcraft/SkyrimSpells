package net.mcshockwave.Spells;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.mcshockwave.Spells.Utils.ItemMetaUtils;
import net.mcshockwave.Spells.Utils.LocUtils;
import net.mcshockwave.Spells.Utils.PacketUtils;
import net.mcshockwave.Spells.Utils.PacketUtils.ParticleEffect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public enum Spell {

	// Novice
	// Destruction
	Flames(
		SpellType.Spray,
		0,
		Material.INK_SACK,
		1,
		SpellDifficulty.Novice,
		SpellSchool.Destruction),
	Frostbite(
		SpellType.Spray,
		0,
		Material.INK_SACK,
		12,
		SpellDifficulty.Novice,
		SpellSchool.Destruction),
	Sparks(
		SpellType.Spray,
		0,
		Material.INK_SACK,
		6,
		SpellDifficulty.Novice,
		SpellSchool.Destruction),
	// Restoration
	Healing(
		SpellType.Spray,
		0,
		Material.INK_SACK,
		11,
		SpellDifficulty.Novice,
		SpellSchool.Restoration),
	Lesser_Ward(
		SpellType.Spray,
		0,
		Material.INK_SACK,
		13,
		SpellDifficulty.Novice,
		SpellSchool.Restoration),

	// Apprentice
	// Destruction
	Firebolt(
		SpellType.Bolt,
		20,
		Material.INK_SACK,
		1,
		SpellDifficulty.Apprentice,
		SpellSchool.Destruction),
	Ice_Spike(
		SpellType.Bolt,
		20,
		Material.INK_SACK,
		12,
		SpellDifficulty.Apprentice,
		SpellSchool.Destruction),
	Lightning_Bolt(
		SpellType.Bolt,
		20,
		Material.INK_SACK,
		6,
		SpellDifficulty.Apprentice,
		SpellSchool.Destruction),
	Fire_Rune(
		SpellType.Rune,
		20,
		Material.INK_SACK,
		1,
		SpellDifficulty.Apprentice,
		SpellSchool.Destruction),
	Frost_Rune(
		SpellType.Rune,
		20,
		Material.INK_SACK,
		12,
		SpellDifficulty.Apprentice,
		SpellSchool.Destruction),
	Lightning_Rune(
		SpellType.Rune,
		20,
		Material.INK_SACK,
		6,
		SpellDifficulty.Apprentice,
		SpellSchool.Destruction),
	// Adept
	// Destruction
	Chain_Lightning(
		SpellType.Bolt,
		30,
		Material.INK_SACK,
		6,
		SpellDifficulty.Adept,
		SpellSchool.Destruction),
	Fireball(
		SpellType.Bolt,
		30,
		Material.INK_SACK,
		1,
		SpellDifficulty.Adept,
		SpellSchool.Destruction),
	Ice_Storm(
		SpellType.Bolt,
		30,
		Material.INK_SACK,
		12,
		SpellDifficulty.Adept,
		SpellSchool.Destruction),
	Flame_Cloak(
		SpellType.Cloak,
		40,
		1200,
		Material.INK_SACK,
		1,
		SpellDifficulty.Adept,
		SpellSchool.Destruction),
	Frost_Cloak(
		SpellType.Cloak,
		40,
		1200,
		Material.INK_SACK,
		12,
		SpellDifficulty.Adept,
		SpellSchool.Destruction),
	Lightning_Cloak(
		SpellType.Cloak,
		40,
		1200,
		Material.INK_SACK,
		6,
		SpellDifficulty.Adept,
		SpellSchool.Destruction),
	// Expert
	// Destruction
	Icy_Spear(
		SpellType.Bolt,
		30,
		Material.INK_SACK,
		12,
		SpellDifficulty.Expert,
		SpellSchool.Destruction),
	Incinerate(
		SpellType.Bolt,
		30,
		Material.INK_SACK,
		1,
		SpellDifficulty.Expert,
		SpellSchool.Destruction),
	Thunderbolt(
		SpellType.Bolt,
		30,
		Material.INK_SACK,
		6,
		SpellDifficulty.Expert,
		SpellSchool.Destruction),
	Wall_of_Flames(
		SpellType.Spray,
		0,
		200,
		Material.INK_SACK,
		1,
		SpellDifficulty.Expert,
		SpellSchool.Destruction),
	Wall_of_Frost(
		SpellType.Spray,
		0,
		200,
		Material.INK_SACK,
		12,
		SpellDifficulty.Expert,
		SpellSchool.Destruction),
	Wall_of_Storms(
		SpellType.Spray,
		0,
		200,
		Material.INK_SACK,
		6,
		SpellDifficulty.Expert,
		SpellSchool.Destruction),
	// Master
	// Destruction
	Blizzard(
		SpellType.Self,
		100,
		400,
		Material.INK_SACK,
		12,
		SpellDifficulty.Master,
		SpellSchool.Destruction),
	Fire_Storm(
		SpellType.Self,
		100,
		Material.INK_SACK,
		1,
		SpellDifficulty.Master,
		SpellSchool.Destruction),
	Lightning_Storm(
		SpellType.Spray,
		0,
		Material.INK_SACK,
		6,
		SpellDifficulty.Master,
		SpellSchool.Destruction);

	public SpellType		type;
	public ItemStack		item;
	public String			name;
	public int				duration	= 0, castTime;
	public SpellDifficulty	dif;
	public SpellSchool		sch;

	Spell(SpellType type, int castTime, int duration, Material t, int data, SpellDifficulty dif, SpellSchool sch) {
		this.name = name().replace('_', ' ');
		this.type = type;
		this.duration = duration;
		this.item = ItemMetaUtils.setLore(ItemMetaUtils.setItemName(new ItemStack(t, 1, (short) data), "§r" + name),
				"", "School: " + sch.name(), "Skill Level: " + dif.name());
		this.dif = dif;
		this.sch = sch;
		this.castTime = castTime;
	}

	Spell(SpellType type, int castTime, Material t, int data, SpellDifficulty dif, SpellSchool sch) {
		this.name = name().replace('_', ' ');
		this.type = type;
		this.item = ItemMetaUtils.setLore(ItemMetaUtils.setItemName(new ItemStack(t, 1, (short) data), "§r" + name),
				"", "School: " + sch.name(), "Skill Level: " + dif.name());
		this.dif = dif;
		this.sch = sch;
		this.castTime = castTime;
	}

	public static enum SpellType {
		Bolt,
		Spray,
		Self,
		Rune,
		Target,
		Cloak;
	}

	public static enum SpellDifficulty {
		Novice,
		Apprentice,
		Adept,
		Expert,
		Master;
	}

	public static enum SpellSchool {
		Alteration,
		Conjuration,
		Destruction,
		Illusion,
		Restoration;
	}

	public void fireParProjectile(final Player la, final ParticleEffect pe, Location s, Vector v, float speed) {
		Location[] ray = rayCast(s, v, 1000);
		Location last = s;

		float delay = 0;
		for (int i = 0; i < ray.length; i++) {
			final Location m = ray[i];
			new BukkitRunnable() {
				public void run() {
					PacketUtils.playParticleEffect(pe, m, 0.3f, 0.05f, 3);
					for (Entity e : la.getNearbyEntities(100, 100, 100)) {
						if (e.getLocation().distance(m) < 1) {
							break;
						}
					}
				}
			}.runTaskLater(SpellsSkyrim.ins, (long) delay);
			delay += speed;
			last = m;
		}

		final Location last2 = last;
		new BukkitRunnable() {
			public void run() {
				onProjParHit(last2, la);
			}
		}.runTaskLater(SpellsSkyrim.ins, (long) delay);
	}

	boolean	chain	= false;

	public void onProjParHit(final Location l, final Player la) {
		if (this == Firebolt) {
			PacketUtils.playParticleEffect(ParticleEffect.EXPLODE, l, 0, 0.3f, 100);
			l.getWorld().playSound(l, Sound.EXPLODE, 2, 1);
			for (Entity e : l.getWorld().getEntities()) {
				if (!(e instanceof LivingEntity))
					continue;

				LivingEntity le = (LivingEntity) e;
				if (le != la && le.getLocation().distance(l) <= 8) {
					le.setFireTicks(200);
					le.damage(4, la);
				}
			}
		}
		if (this == Ice_Spike) {
			l.getWorld().playSound(l, Sound.GLASS, 2, 1);
			for (Entity e : l.getWorld().getEntities()) {
				if (!(e instanceof LivingEntity))
					continue;

				LivingEntity le = (LivingEntity) e;
				if (le != la && le.getLocation().distance(l) <= 8) {
					le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 2));
					le.damage(4, la);
				}
			}
		}
		if (this == Lightning_Bolt) {
			l.getWorld().strikeLightningEffect(l);
			for (Entity e : l.getWorld().getEntities()) {
				if (!(e instanceof LivingEntity))
					continue;

				LivingEntity le = (LivingEntity) e;
				if (le != la && le.getLocation().distance(l) <= 8) {
					// TODO damage magicka
					le.damage(4, la);
				}
			}
		}

		if (this == Chain_Lightning) {
			if (!chain)
				l.getWorld().strikeLightningEffect(l);
			for (Entity e : l.getWorld().getEntities()) {
				if (!(e instanceof LivingEntity))
					continue;

				LivingEntity le = (LivingEntity) e;
				if (le != la && le.getLocation().distance(l) <= 8) {
					// TODO damage magicka
					le.damage(5, la);

					if (!chain) {
						for (Entity nb : le.getNearbyEntities(5, 5, 5)) {
							if (!(nb instanceof LivingEntity))
								continue;
							fireParProjectile(la, ParticleEffect.WITCH_MAGIC, le.getEyeLocation(),
									LocUtils.getVelocity(le.getEyeLocation(), nb.getLocation()), 0.02f);
						}
						chain = true;
						Bukkit.getScheduler().runTaskLater(SpellsSkyrim.ins, new Runnable() {
							public void run() {
								chain = false;
							}
						}, 10l);
					}
				}
			}
		}
		if (this == Fireball) {
			PacketUtils.playParticleEffect(ParticleEffect.EXPLODE, l, 0, 0.3f, 100);
			l.getWorld().playSound(l, Sound.EXPLODE, 2, 1);
			for (Entity e : l.getWorld().getEntities()) {
				if (!(e instanceof LivingEntity))
					continue;

				LivingEntity le = (LivingEntity) e;
				if (le != la && le.getLocation().distance(l) <= 8) {
					le.setFireTicks(300);
					le.damage(8, la);
				}
			}
		}
		if (this == Ice_Storm) {
			l.getWorld().playSound(l, Sound.GLASS, 2, 1);
			for (Entity e : l.getWorld().getEntities()) {
				if (!(e instanceof LivingEntity))
					continue;

				LivingEntity le = (LivingEntity) e;
				if (le != la && le.getLocation().distance(l) <= 8) {
					le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 2));
					le.damage(8, la);
				}
			}
		}

		if (this == Incinerate) {
			PacketUtils.playParticleEffect(ParticleEffect.EXPLODE, l, 0, 0.3f, 100);
			l.getWorld().playSound(l, Sound.EXPLODE, 2, 1);
			for (Entity e : l.getWorld().getEntities()) {
				if (!(e instanceof LivingEntity))
					continue;

				LivingEntity le = (LivingEntity) e;
				if (le != la && le.getLocation().distance(l) <= 8) {
					le.setFireTicks(400);
					le.damage(9, la);
				}
			}
		}
		if (this == Icy_Spear) {
			l.getWorld().playSound(l, Sound.GLASS, 2, 1);
			for (Entity e : l.getWorld().getEntities()) {
				if (!(e instanceof LivingEntity))
					continue;

				LivingEntity le = (LivingEntity) e;
				if (le != la && le.getLocation().distance(l) <= 8) {
					le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 2));
					le.damage(9, la);
				}
			}
		}
		if (this == Thunderbolt) {
			l.getWorld().strikeLightningEffect(l);
			for (Entity e : l.getWorld().getEntities()) {
				if (!(e instanceof LivingEntity))
					continue;

				LivingEntity le = (LivingEntity) e;
				if (le != la && le.getLocation().distance(l) <= 8) {
					// TODO damage magicka
					le.damage(9, la);
				}
			}
		}
	}

	public Location[] rayCast(Location start, Vector vec, int distance) {
		ArrayList<Location> cast = new ArrayList<>();
		Location s = start.clone();
		Vector v = vec.clone().multiply(0.1);

		for (int i = 0; i < distance; i++) {
			s = s.add(v);
			if (!s.getBlock().getType().isTransparent()) {
				break;
			}
			cast.add(s.clone());
		}

		return cast.toArray(new Location[0]);
	}

	public void cast(final Player p) {
		Vector v = p.getLocation().getDirection();
		Location s = p.getEyeLocation().add(p.getLocation().getDirection());

		// Novice

		if (type == SpellType.Spray) {
			List<Entity> es = p.getNearbyEntities(10, 10, 10);
			Location[] cast = rayCast(s, v, 50);

			// Flames
			if (this == Flames) {
				for (Location l : cast) {
					PacketUtils.playParticleEffect(ParticleEffect.FLAME, l, 0.3f, 0.05f, 1);
					for (Entity e : es) {
						if (e instanceof LivingEntity && e.getLocation().distance(l) < 2) {
							LivingEntity le = (LivingEntity) e;
							le.setFireTicks(100);
							le.damage(1.5);
						}
					}
				}
			}

			// Frostbite
			if (this == Frostbite) {
				for (Location l : cast) {
					PacketUtils.playParticleEffect(ParticleEffect.INSTANT_SPELL, l, 0.3f, 0.05f, 1);
					for (Entity e : es) {
						if (e instanceof LivingEntity && e.getLocation().distance(l) < 2) {
							LivingEntity le = (LivingEntity) e;
							le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
							le.damage(1);
						}
					}
				}
			}

			// Sparks
			if (this == Sparks) {
				for (Location l : cast) {
					PacketUtils.playParticleEffect(ParticleEffect.WITCH_MAGIC, l, 0.3f, 0.05f, 1);
					for (Entity e : es) {
						if (e instanceof LivingEntity && e.getLocation().distance(l) < 2) {
							LivingEntity le = (LivingEntity) e;
							// TODO make this drain magicka
							le.damage(1);
						}
					}
				}
			}

			// Wall
			if (this == Wall_of_Flames) {
				createWall(cast, p, ParticleEffect.FLAME);
			}
			if (this == Wall_of_Storms) {
				createWall(cast, p, ParticleEffect.WITCH_MAGIC);
			}
			if (this == Wall_of_Frost) {
				createWall(cast, p, ParticleEffect.INSTANT_SPELL);
			}
		}

		if (this == Healing) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
			PacketUtils.playParticleEffect(ParticleEffect.HEART, p.getEyeLocation(), 1, 0, 1);
		}

		if (this == Lesser_Ward) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 1));
			PacketUtils.playParticleEffect(ParticleEffect.MAGIC_CRIT, p.getEyeLocation().add(v), 1, 0, 8);
		}

		// Apprentice

		if (this == Firebolt) {
			fireParProjectile(p, ParticleEffect.FLAME, s, v, 0.2f);
		}

		if (this == Ice_Spike) {
			fireParProjectile(p, ParticleEffect.INSTANT_SPELL, s, v, 0.15f);
		}

		if (this == Lightning_Bolt) {
			fireParProjectile(p, ParticleEffect.WITCH_MAGIC, s, v, 0.01f);
		}

		// Adept

		if (this == Chain_Lightning) {
			fireParProjectile(p, ParticleEffect.WITCH_MAGIC, s, v, 0.01f);
		}

		if (this == Fireball) {
			fireParProjectile(p, ParticleEffect.FLAME, s, v, 0.2f);
		}

		if (this == Ice_Storm) {
			Random rand = new Random();
			for (int i = 0; i < 3; i++) {
				fireParProjectile(
						p,
						ParticleEffect.INSTANT_SPELL,
						s,
						v.clone().add(
								new Vector(rand.nextDouble() / 10, rand.nextDouble() / 10, rand.nextDouble() / 10)),
						0.15f);
			}
		}

		// Expert

		if (this == Incinerate) {
			fireParProjectile(p, ParticleEffect.FLAME, s, v, 0.2f);
		}

		if (this == Icy_Spear) {
			fireParProjectile(p, ParticleEffect.INSTANT_SPELL, s, v, 0.15f);
		}

		if (this == Thunderbolt) {
			fireParProjectile(p, ParticleEffect.WITCH_MAGIC, s, v, 0.01f);
		}
		
		// Master
		
		if (this == Lightning_Storm) {
			List<Entity> es = p.getNearbyEntities(50, 50, 50);
			Location[] cast = rayCast(s, v, 200);
			
			for (Location l : cast) {
				PacketUtils.playParticleEffect(ParticleEffect.WITCH_MAGIC, l, 0.3f, 0.05f, 3);
				for (Entity e : es) {
					if (e instanceof LivingEntity && e.getLocation().distance(l) < 2) {
						LivingEntity le = (LivingEntity) e;
						// TODO make this drain magicka
						le.damage(3);
					}
				}
			}
		}

	}

	public void createWall(Location[] cast, final Player p, final ParticleEffect pe) {
		Random rand = new Random();
		for (Location l : cast) {
			if (rand.nextBoolean())
				PacketUtils.playParticleEffect(pe, l, 0.3f, 0.1f, 1);
		}
		final Location t = cast[cast.length - 1];
		if (t.getBlock().getRelative(BlockFace.DOWN).getType().isTransparent())
			return;
		final Location tu1 = t.clone().add(0, 1, 0);
		final Location tu2 = t.clone().add(0, 2, 0);

		final Spell th = this;
		for (int i = 0; i < duration / 5; i++) {
			new BukkitRunnable() {
				public void run() {
					PacketUtils.playParticleEffect(pe, t, 0.2f, 0.05f, 2);
					PacketUtils.playParticleEffect(pe, tu1, 0.2f, 0.05f, 2);
					PacketUtils.playParticleEffect(pe, tu2, 0.2f, 0.05f, 2);

					for (Entity e : t.getWorld().getEntities()) {
						if (!(e instanceof LivingEntity))
							continue;

						LivingEntity le = (LivingEntity) e;
						if (le != p && le.getLocation().distance(t) <= 1.5) {
							if (th == Wall_of_Storms) {
								le.damage(2, p);
								// TODO damage magicka
							} else if (th == Wall_of_Flames) {
								le.setFireTicks(100);
								le.damage(2, p);
							} else if (th == Wall_of_Frost) {
								le.damage(2, p);
								le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2));
							}
						}
					}
				}
			}.runTaskLater(SpellsSkyrim.ins, i * 5);
		}
	}

	public void castAtTarget(final Player p, LivingEntity targ) {
		if (this == Blizzard) {
			for (int i = 0; i < duration / 5; i++) {
				new BukkitRunnable() {
					public void run() {
						for (Entity e : p.getNearbyEntities(10, 10, 10)) {
							if (e instanceof LivingEntity) {
								LivingEntity le = (LivingEntity) e;

								le.damage(3, p);
								le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 3));
							}
						}
						PacketUtils.playParticleEffect(ParticleEffect.INSTANT_SPELL, p.getEyeLocation(), 5, 0.3f, 1000);
					}
				}.runTaskLater(SpellsSkyrim.ins, i * 5);
			}
		}
		if (this == Fire_Storm) {
			PacketUtils.playParticleEffect(ParticleEffect.FLAME, p.getLocation(), 0, 1, 500);
			PacketUtils.playParticleEffect(ParticleEffect.FLAME, p.getLocation(), 0, 0.75f, 250);
			PacketUtils.playParticleEffect(ParticleEffect.FLAME, p.getLocation(), 0, 0.5f, 100);
			p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 2, 0);
			for (Entity e : p.getNearbyEntities(10, 10, 10)) {
				if (e instanceof LivingEntity) {
					LivingEntity le = (LivingEntity) e;

					le.damage(15, p);
					le.setFireTicks(800);
				}
			}
		}
	}

	public void onRuneExplode(Player p, Location l) {
		if (this == Fire_Rune) {
			PacketUtils.playParticleEffect(ParticleEffect.EXPLODE, l, 0, 0.3f, 100);
			l.getWorld().playSound(l, Sound.EXPLODE, 2, 1);
		}
		if (this == Frost_Rune) {
			l.getWorld().playSound(l, Sound.GLASS, 2, 1);
		}
		if (this == Lightning_Rune) {
			l.getWorld().strikeLightningEffect(l);
		}
	}

	public void onRuneHitEntity(Player p, LivingEntity le) {
		if (this == Fire_Rune) {
			le.setFireTicks(250);
			le.damage(8, p);
		}
		if (this == Frost_Rune) {
			le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 2));
			le.damage(8, p);
		}
		if (this == Lightning_Rune) {
			// TODO make this drain magicka
			le.damage(8, p);
		}
	}

	public void onRuneUpdate(Player p, Location l) {
		if (this == Fire_Rune) {
			PacketUtils.playParticleEffect(ParticleEffect.FLAME, l.clone().add(0.5, 0.5, 0.5), 0.4f, 0.05f, 25);
		}
		if (this == Frost_Rune) {
			PacketUtils.playParticleEffect(ParticleEffect.INSTANT_SPELL, l.clone().add(0.5, 0.5, 0.5), 0.4f, 0.05f, 25);
		}
		if (this == Lightning_Rune) {
			PacketUtils.playParticleEffect(ParticleEffect.WITCH_MAGIC, l.clone().add(0.5, 0.5, 0.5), 0.4f, 0.05f, 25);
		}
	}

	public void onCloakUpdate(Player p) {
		if (this == Flame_Cloak) {
			PacketUtils.playParticleEffect(ParticleEffect.FLAME, p.getEyeLocation(), 0.7f, 0.05f, 25);
			for (Entity e : p.getNearbyEntities(3, 3, 3)) {
				if (e instanceof LivingEntity) {
					LivingEntity le = (LivingEntity) e;

					le.damage(2);
					le.setFireTicks(100);
				}
			}
		}
		if (this == Frost_Cloak) {
			PacketUtils.playParticleEffect(ParticleEffect.INSTANT_SPELL, p.getEyeLocation(), 0.7f, 0.05f, 25);
			for (Entity e : p.getNearbyEntities(3, 3, 3)) {
				if (e instanceof LivingEntity) {
					LivingEntity le = (LivingEntity) e;

					le.damage(2);
					le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
				}
			}
		}
		if (this == Lightning_Cloak) {
			PacketUtils.playParticleEffect(ParticleEffect.WITCH_MAGIC, p.getEyeLocation(), 0.7f, 0.05f, 25);
			for (Entity e : p.getNearbyEntities(3, 3, 3)) {
				if (e instanceof LivingEntity) {
					LivingEntity le = (LivingEntity) e;

					le.damage(2);
					// TODO damage magicka
				}
			}
		}
	}

	public void onCloakDissipate(Player p) {
		if (this == Flame_Cloak) {
			PacketUtils.playParticleEffect(ParticleEffect.EXPLODE, p.getEyeLocation(), 0, 0.3f, 100);
			p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 2, 1);
		}
		if (this == Frost_Cloak) {
			p.getWorld().playSound(p.getLocation(), Sound.GLASS, 2, 1);
		}
		if (this == Lightning_Rune) {
			p.getWorld().strikeLightningEffect(p.getLocation());
		}
	}
}

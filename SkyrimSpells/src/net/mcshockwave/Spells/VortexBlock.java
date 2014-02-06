package net.mcshockwave.Spells;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class VortexBlock {

	public Entity	entity;

	public boolean	removable			= true;

	private float	ticker_horisontal	= (float) (Math.random() * 2 * Math.PI);

	public VortexBlock(Entity e) {
		entity = e;
		removable = false;
		addMetadata();
	}

	private void addMetadata() {
		entity.setMetadata("vortex", new FixedMetadataValue(SpellsSkyrim.ins, "protected"));
	}

	public void remove() {
		if (removable) {
			entity.remove();
		}
		entity.removeMetadata("vortex", SpellsSkyrim.ins);
	}

	public void tick() {
		float horisontal = horisontalTicker();
		double rad = 1.5;

		Vector v = new Vector(rad * Math.cos(horisontal), 0.04, rad * Math.sin(horisontal));

		setVelocity(v);
	}

	private void setVelocity(Vector v) {
		entity.setVelocity(v);
	}

	private float horisontalTicker() {
		return (ticker_horisontal += 0.3f);
	}
}

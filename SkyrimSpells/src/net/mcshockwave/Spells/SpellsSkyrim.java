package net.mcshockwave.Spells;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SpellsSkyrim extends JavaPlugin {
	
	public static SpellsSkyrim ins;

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new DefaultListener(), this);
		
		ins = this;
		
		saveDefaultConfig();
	}
	
}

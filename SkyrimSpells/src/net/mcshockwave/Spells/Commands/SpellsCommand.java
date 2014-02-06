package net.mcshockwave.Spells.Commands;

import net.mcshockwave.Spells.Spell;
import net.mcshockwave.Spells.Spell.SpellSchool;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import org.apache.commons.lang.WordUtils;

public class SpellsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!sender.isOp()) {
			return false;
		}

		if (args.length > 0) {
			String cm = args[0];

			if (cm.equalsIgnoreCase("listall") && sender instanceof Player) {
				Spell[] sps = Spell.getSpells(SpellSchool.valueOf(WordUtils.capitalizeFully(args[1])));
				
				Inventory i = Bukkit.createInventory(null, (sps.length + 8) / 9 * 9, "All " + args[1] + " Spells");
				
				for (Spell s : sps) {
					i.addItem(s.item);
				}
				
				((Player) sender).openInventory(i);
			}
			if (args.length > 2) {
				String pl = args[1];
				String sp = args[2];

				if (cm.equalsIgnoreCase("give")) {
					Player p = Bukkit.getPlayer(pl);

					Spell s = null;
					for (Spell s2 : Spell.values()) {
						if (s2.name().equalsIgnoreCase(sp)) {
							s = s2;
						}
					}
					if (s == null)
						return false;

					p.getInventory().addItem(s.item);
				}
			}
		}

		return false;
	}

}

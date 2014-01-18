package net.mcshockwave.Spells.Commands;

import net.mcshockwave.Spells.Spell;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpellsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!sender.isOp()) {
			return false;
		}

		if (args.length > 2) {
			String cm = args[0];
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

		return false;
	}

}

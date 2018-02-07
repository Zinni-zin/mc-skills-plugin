package zinnia.skills.player;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import zinnia.skills.main.Skills;
import zinnia.skills.utils.Colours;

public class SkillsCommand {

	Skills plugin;
	Menu menu;

	public SkillsCommand(Skills plugin, Menu menu) {
		this.plugin = plugin;
		this.menu = menu;
	}

	public void Command(CommandSender sender, String cmd, String[] args) {
		try { // Try to do this stuff.
			if(PlayerCommands.skillsCommand(cmd)) {
				if(sender instanceof Player) { // Check if the sender is an instance of player
					PlayerCommands.doPCmd(sender, plugin, menu, cmd, args);
				} else {
					AdminCommands.doACmds(sender, plugin, cmd, args);
				}
			} // Catch an exception and return a message saying "invalid arguments!" to the sender.
		} catch(Exception e) { sender.sendMessage(Colours.RED + "Invalid arguments!"); /*e.printStackTrace();*/ }
	}
}

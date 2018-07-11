package zinnia.skills.player;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.nisovin.magicspells.MagicSpells;

import zinnia.skills.main.Skills;
import zinnia.skills.utils.Colours;
import zinnia.skills.utils.TierUtils;

public class PlayerCommands {

	public static void doPCmd(CommandSender sender, Skills plugin, Menu menu, String cmd, String[] args) {
		// Open up the menu.
		if(args.length == 0) {
			if(!plugin.playerSkills.get(((Player)sender).getUniqueId()).usingTempPoints)
				menu.OpenInventory((Player)sender);
			else sender.sendMessage(ChatColor.RED + "You're using temp point values!");
		}

		// Send the current hp of the player
		else if(PlayerCommands.sendPointCmds(args, "hp", "health")) PlayerCommands.sendHp((Player)sender);

		// Send the current damage of the player
		else if(PlayerCommands.sendPointCmds(args, "dmg", "damage")) PlayerCommands.sendDmg(plugin, (Player)sender);
		
		// Send the current defense of the player
		else if(PlayerCommands.sendPointCmds(args, "defense")) PlayerCommands.sendDefense(plugin, (Player)sender);
		
		// Send the current dodge chance of the player
		else if(PlayerCommands.sendPointCmds(args, "dodge", "dodgeChance"))  PlayerCommands.sendDodgeChance(plugin, (Player)sender);

		// Send the current crit chance of the player
		else if(PlayerCommands.sendPointCmds(args, "crit", "critical", "critChance", "criticalChance"))  PlayerCommands.sendCritChance(plugin, (Player)sender);

		// Send the current mana of the player
		else if(PlayerCommands.sendPointCmds(args, "mana")) PlayerCommands.sendMana(plugin, (Player)sender);
		
		// Send the current mana regen of the player
		else if(PlayerCommands.sendPointCmds(args, "manaregen")) PlayerCommands.sendManaRegen(plugin, (Player)sender);
		
		/*
		 * Send point data
		 */
		// Send the skill points the player has
		else if(PlayerCommands.sendPointCommand(args, "point", "points")) PlayerCommands.sendSkillPoint(plugin, (Player)sender);

		// Send the health points the player has
		else if(PlayerCommands.sendPointCommand(args, "hp", "health")) PlayerCommands.sendHealthPoint(plugin, (Player)sender);

		// Send damage points the player has
		else if(PlayerCommands.sendPointCommand(args, "damage", "dmg")) PlayerCommands.sendDmgPoint(plugin, (Player)sender);

		// Send defense points the player has
		else if(PlayerCommands.sendPointCommand(args, "defense")) PlayerCommands.sendDefensePoint(plugin, (Player)sender);

		// Send the dodge points the player has
		else if(PlayerCommands.sendPointCommand(args, "dodge", "dodgechance")) PlayerCommands.sendDodgePoint(plugin, (Player)sender);

		// Send the crit points the player has
		else if(PlayerCommands.sendPointCommand(args, "crit", "critChance", "critical", "criticalchance")) PlayerCommands.sendCritPoint(plugin, (Player)sender);

		// Send mana points the player has
		else if(PlayerCommands.sendPointCommand(args, "mana")) PlayerCommands.sendManaPoint(plugin, (Player)sender);

		// Send mana regen points the player has
		else if(PlayerCommands.sendPointCommand(args, "manaregen", "mana_regen", "regen_mana", "regenmana"))
			PlayerCommands.sendManaRegenPoint(plugin, (Player)sender);

		/*
		 * 
		 * Leader commands
		 * 
		 */

		// Check if the player is trying to increase someone's tier
		else if(PlayerCommands.increaseTierCommand(args)) {
			if(sender.hasPermission("skills.leader") || sender.hasPermission("skills.admin")) // Check if they have the leader perm or admin
				TierUtils.increaseTierOnCommand(plugin, plugin.target((Player)sender, args[1]), (Player)sender); // Increase the tier
		}

		// Check if the player is trying to decrease someone's tier
		else if(PlayerCommands.decreaseTierCommand(args)) {
			if(sender.hasPermission("skills.leader") || sender.hasPermission("skills.admin")) // Check if they have the leader perm or admin
				TierUtils.decraseTierOnCommand(plugin, plugin.target((Player)sender, args[1]), (Player)sender); // Decrease the tier
		}

		/*
		 * 
		 * Admin commands
		 * 
		 */

		/*
		 * Get section
		 */
		else if(args[0].equalsIgnoreCase("get")) {
			// Get the skill point of a user
			if(AdminCommands.getAdminCommand(args, "skill", "skillpoint", "skills", "skillsPoint", "point", "points")) {
				if(sender.hasPermission("skills.admin"))
					AdminCommands.getSkillPoint(sender, plugin, plugin.target(sender, args[1]));
			}

			// Get the health point of a user
			else if(AdminCommands.getAdminCommand(args, "health", "hp")) {
				if(sender.hasPermission("skills.admin"))
					AdminCommands.getHealthPoint(sender, plugin, plugin.target(sender, args[1]));
			}

			// Get the damage point of a user
			else if(AdminCommands.getAdminCommand(args, "damage", "dmg")) {
				if(sender.hasPermission("skills.admin"))
					AdminCommands.getDmgPoint(sender, plugin, plugin.target(sender, args[1]));
			}
		
			// Get the defense point of a user
			else if(AdminCommands.getAdminCommand(args, "defense")) {
				if(sender.hasPermission("skills.admin"))
					AdminCommands.getDefensePoint(sender, plugin, plugin.target(sender, args[1]));
			}

			// Get the dodge point of a user
			else if(AdminCommands.getAdminCommand(args, "dodge", "dodgechance")) {
				if(sender.hasPermission("skills.admin"))
					AdminCommands.getDodgePoint(sender, plugin, plugin.target(sender, args[1]));
			}

			// Get the crit point of a user
			else if(AdminCommands.getAdminCommand(args, "crit", "critchance", "critical", "criticalchance")) {
				if(sender.hasPermission("skills.admin"))
					AdminCommands.getCritPoint(sender, plugin, plugin.target(sender, args[1]));
			}

			// Get the mana point of a user
			else if(AdminCommands.getAdminCommand(args, "mana")) {
				if(sender.hasPermission("skills.admin"))
					AdminCommands.getManaPoint(sender, plugin, plugin.target(sender, args[1]));
			}

			// Get the mana regen point of a user
			else if(AdminCommands.getAdminCommand(args, "manaregen", "mana_regen", "regen_mana", "regenmana")) {
				if(sender.hasPermission("skills.admin"))
					AdminCommands.getManaRegenPoint(sender, plugin, plugin.target(sender, args[1]));
			}
		}
		/*
		 * Misc section, tbh I have no idea what I was doing when making this command part
		 * I personally hate doing commands
		 * (Send Help)
		 */

		// Reset the points and level of a player
		
		else if(args[0].equalsIgnoreCase("reset")) {
			if(args.length == 1) {
				if(sender.hasPermission("skills.player.reset"))
					AdminCommands.resetPoints(plugin, (Player)sender);
			}
			else {
				if(sender.hasPermission("skills.admin"))
					AdminCommands.resetPoints(sender, plugin, plugin.target(sender, args[1]));
			}
		}
		
		// Set a player to use temp points
		else if(args[0].equalsIgnoreCase("temp") || args[0].equalsIgnoreCase("temporary")) {
			if(args.length == 1) {
				if(sender.hasPermission("skills.player.temp"))
					PlayerCommands.tempSkillsCommand(plugin, (Player)sender);
			} else {
				if(sender.hasPermission("skills.admin"))
					AdminCommands.forceTempPoints(sender, plugin, plugin.target(sender, args[1]));
			}
		}
		
		else if(args[0].equalsIgnoreCase("load") || args[0].equalsIgnoreCase("loadfromfile")) {
			if(args.length == 1) {
				if(sender.hasPermission("skills.player.temp"))
					PlayerCommands.loadSkillsCommand(plugin, (Player)sender);
			} else {
				if(sender.hasPermission("skills.admin"))
					AdminCommands.forceLoadPoints(sender, plugin, plugin.target(sender, args[1]));
			}
		}
		
		/*
		 * Remove section
		 */
		else if(args[0].equalsIgnoreCase("remove")) {
			// Remove skill points from the player
			if(AdminCommands.getAdminCommand(args, "skill", "skillpoint", "skills", "skillsPoint", "point", "points")) {
				if(sender.hasPermission("skills.admin")) 
				AdminCommands.removeSkillPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}

			// Remove health points from the player
			else if(AdminCommands.getAdminCommand(args, "health", "hp")) {
				if(sender.hasPermission("skills.admin")) 
					AdminCommands.removeHealthPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}

			// Remove damage points from the player
			else if(AdminCommands.getAdminCommand(args, "damage", "dmg")) {
				if(sender.hasPermission("skills.admin")) 
					AdminCommands.removeDmgPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}

			// Remove defense points from the player
			else if(AdminCommands.getAdminCommand(args, "defense")) {
				if(sender.hasPermission("skills.admin"))
					AdminCommands.removeDefensePoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}
		
			// Remove dodge points from the player
			else if(AdminCommands.getAdminCommand(args, "dodge", "dodgechance")) {
				if(sender.hasPermission("skills.admin")) 
					AdminCommands.removeDodgePoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}

			// Remove crit points from the player
			else if(AdminCommands.getAdminCommand(args, "crit", "critchance", "critical", "criticalchance")) {
				if(sender.hasPermission("skills.admin")) 
					AdminCommands.removeCritPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}

			// Remove mana points from the player
			else if(AdminCommands.getAdminCommand(args, "mana")) {
				if(sender.hasPermission("skills.admin")) 
					AdminCommands.removeManaPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}

			// Remove mana regen points from the player
			else if(AdminCommands.getAdminCommand(args, "manaregen", "mana_regen", "regen_mana", "regenmana")) {
				if(sender.hasPermission("skills.admin")) 
					AdminCommands.removeMRegenPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}
		}
		/*
		 *  Give section
		 */

		// Give skill points to the player
		else if(args[0].equalsIgnoreCase("give")) {
			if(AdminCommands.getAdminCommand(args, "skill", "skillpoint", "skills", "skillsPoint", "point", "points")) {
				if(sender.hasPermission("skills.admin")) 
					AdminCommands.giveSkillPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}

			// Give health points to the player
			else if(AdminCommands.getAdminCommand(args, "health", "hp")) {
				if(sender.hasPermission("skills.admin")) 
					AdminCommands.giveHealthPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}

			// Give damage points to the player
			else if(AdminCommands.getAdminCommand(args, "damage", "dmg")) {
				if(sender.hasPermission("skills.admin")) 
					AdminCommands.giveDmgPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}
		
			// Give defense to the player
			else if (AdminCommands.getAdminCommand(args, "defense")) {
				if(sender.hasPermission("skills.admin"))
					AdminCommands.giveDefensePoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}

			// Give dodge points to the player
			else if(AdminCommands.getAdminCommand(args, "dodge", "dodgechance")) {
				if(sender.hasPermission("skills.admin")) 
					AdminCommands.giveDodgePoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}

			// Give crit points to the player
			else if(AdminCommands.getAdminCommand(args, "crit", "critchance", "critical", "criticalchance")) {
				if(sender.hasPermission("skills.admin")) 
					AdminCommands.giveCritPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}

			// Give mana points to the player
			else if(AdminCommands.getAdminCommand(args, "mana")) {
				if(sender.hasPermission("skills.admin")) 
					AdminCommands.giveManaPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}

			// Give mana regen points to the player
			else if(AdminCommands.getAdminCommand(args, "manaregen", "mana_regen", "regen_mana", "regenmana")) {
				if(sender.hasPermission("skills.admin")) 
					AdminCommands.giveManaRegenPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);
			}
		}
		
		// Set tier ranks
		else if(AdminCommands.setTierRank(args)) {
			if(sender.hasPermission("skills.admin"))
				TierUtils.setTierCommand(plugin, sender, plugin.target(sender, args[1]), args[2]);
		}

		// Reload the config command.
		else if(args[0].equalsIgnoreCase("reload")) {
			if(sender.hasPermission("skills.admin")) {
				plugin.reloadConfig();
				plugin.saveConfigData();
				plugin.loadConfigData();
				sender.sendMessage(ChatColor.GREEN + "Config reloaded");
			}
		}

		// Rank up a player and reset their skills
		else if (AdminCommands.ResetIncreaseTier(args)) {
			if(sender.hasPermission("skills.tier.resetincrease")) {
				AdminCommands.tierResetCrease(sender, plugin, plugin.target(sender, args[1]));
			}
		}
		
		else sender.sendMessage(ChatColor.RED + "Invalid arguments!");
	}

	/*
	 * 
	 * Do player command methods
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static void sendHp(Player player) {
		player.sendMessage(Colours.GREEN + "Your hp is: " + Colours.RED + player.getHealth() + 
				Colours.DARK_PURPLE + "/" + Colours.RED + player.getMaxHealth());
	}

	public static void sendDmg(Skills plugin, Player player) {
		player.sendMessage(Colours.GREEN + "Your damage is: " + Colours.YELLOW + plugin.playerSkills.get(player.getUniqueId()).getDmg());
	}
	
	public static void sendDefense(Skills plugin, Player player) {
		player.sendMessage(Colours.GREEN + "Your defense is: " + Colours.GRAY + plugin.playerSkills.get(player.getUniqueId()).getDefense());
	}
	
	public static void sendDodgeChance(Skills plugin, Player player) {
		player.sendMessage(Colours.GREEN + "Your dodge chance is: " + Colours.AQUA + plugin.playerSkills.
				get(player.getUniqueId()).getDodgeChange() + "%");
	}

	public static void sendCritChance(Skills plugin, Player player) {
		player.sendMessage(Colours.GREEN + "Your crit chance is: " + Colours.DARK_GREEN + 
				plugin.playerSkills.get(player.getUniqueId()).getCritChance() + "%");
	}

	public static void sendMana(Skills plugin, Player player) {
		player.sendMessage(Colours.GREEN + "Your Mana is: " + Colours.DARK_AQUA + MagicSpells.getManaHandler().getMaxMana(player));
	}
	
	public static void sendManaRegen(Skills plugin, Player player) {
		player.sendMessage(Colours.GREEN + "Your Mana Regen is: " + Colours.PINK + MagicSpells.getManaHandler().getRegenAmount(player));
	}
	
	/*
	 * Send point amount
	 */
	
	public static void sendSkillPoint(Skills plugin, Player player) {
		player.sendMessage(Colours.GREEN + "Your amount of skill points are: " + Colours.GOLD + 
				plugin.playerSkills.get(player.getUniqueId()).points);
	}

	// Health points
	public static void sendHealthPoint(Skills plugin, Player player) {
		player.sendMessage(Colours.GREEN + "Your amount of health points are: " + Colours.RED + 
				plugin.playerSkills.get(player.getUniqueId()).healthPoints+ "/" + Skills.maxHpPoints);
	}

	// Damage points
	public static void sendDmgPoint(Skills plugin, Player player) {
		player.sendMessage(Colours.GREEN + "Your amount of damage points are: " + Colours.YELLOW + 
				plugin.playerSkills.get(player.getUniqueId()).dmgPoints + "/" + Skills.maxDmgPoints);
	}

	// Defense points
	public static void sendDefensePoint(Skills plugin, Player player) {
		player.sendMessage(Colours.GREEN + "Your amount of defense points are: " + Colours.DARK_GRAY + 
				plugin.playerSkills.get(player.getUniqueId()).defensePoints + "/" + Skills.maxDefensePoints);
	}

	// Dodge points
	public static void sendDodgePoint(Skills plugin, Player player) {
		player.sendMessage(Colours.GREEN + "Your amount of dodge points are: " + Colours.AQUA + 
				plugin.playerSkills.get(player.getUniqueId()).dodgePoints + "/" + Skills.maxDodgePoints);
	}

	// Critical points
	public static void sendCritPoint(Skills plugin, Player player) {
		player.sendMessage(Colours.GREEN + "Your amount of crit points are: " + Colours.DARK_GREEN + 
				plugin.playerSkills.get(player.getUniqueId()).critPoints + "/" + Skills.maxCritPoints);
	}

	// Mana points
	public static void sendManaPoint(Skills plugin, Player player) {
		player.sendMessage(Colours.GREEN + "Your amount of mana points are: " + Colours.INDIGO + 
				plugin.playerSkills.get(player.getUniqueId()).manaPoints + "/" + Skills.maxManaPoints);
	}

	// Mana regen points
	public static void sendManaRegenPoint(Skills plugin, Player player) {
		player.sendMessage(Colours.GREEN + "Your amount of mana regen points are: " + 
				Colours.DARK_PURPLE +  plugin.playerSkills.get(player.getUniqueId())
				.manaRegenPoints +  "/" + Skills.maxManaRegenPoints);
	}

	public static void tempSkillsCommand(Skills plugin, Player player) {
		plugin.playerSkills.get(player.getUniqueId()).healthPoints = Skills.tempxHpPoints;
		plugin.playerSkills.get(player.getUniqueId()).dmgPoints = Skills.tempDmgPoints;
		plugin.playerSkills.get(player.getUniqueId()).defensePoints = Skills.tempDefensePoints;
		plugin.playerSkills.get(player.getUniqueId()).dodgePoints = Skills.tempDodgePoints;
		plugin.playerSkills.get(player.getUniqueId()).critPoints = Skills.tempCritPoints;
		plugin.playerSkills.get(player.getUniqueId()).manaPoints = Skills.tempManaPoints;
		plugin.playerSkills.get(player.getUniqueId()).manaRegenPoints = Skills.tempManaRegenPoints;
		
		setResources(plugin, player, true);
		
		player.sendMessage(ChatColor.GREEN + "You've given yourself temp points!");
	}
	
	
	public static void loadSkillsCommand(Skills plugin, Player player) {
		plugin.loadPointsFile(player.getUniqueId());
		
		setResources(plugin, player, false);
		
		player.sendMessage(ChatColor.GREEN + "You've set your points to noraml!");
	}
	
	public static void setResources(Skills plugin, Player player, boolean tempPoints) {
		plugin.playerSkills.get(player.getUniqueId()).increaseHealth(player);
		plugin.playerSkills.get(player.getUniqueId()).setMaxMana(player);
		plugin.playerSkills.get(player.getUniqueId()).setManaRegen(player);
		plugin.playerSkills.get(player.getUniqueId()).usingTempPoints = tempPoints;
	}
	
	/*
	 * Command check
	 * Apparently I was a big idiot when I did most of these...
	 */
	public static boolean skillsCommand(String cmd) {
		return(cmd.equalsIgnoreCase("skills") || cmd.equalsIgnoreCase("skill")
				|| cmd.equalsIgnoreCase("skillsmenu") || cmd.equalsIgnoreCase("skillmenu"))?true:false;
	}

	public static boolean sendPointCmds(String[] args, String... pointType) {
		for(String type : pointType) {
			if(args[0].equalsIgnoreCase(type)) 
				return true;
		}
		return false;
	}
	
	/*
	// sendPointCmds(args, "hp", "health");
	public static boolean sendHpCommand(String[] args) {
		return(args[0].equalsIgnoreCase("hp") || args[0].equalsIgnoreCase("health"))?true:false;
	}

	// sendPointCmds(args, "dmg", "damage");
	public static boolean sendDmgCommand(String[] args) {
		return(args[0].equalsIgnoreCase("dmg") || args[0].equalsIgnoreCase("damage"))?true:false;
	}
	
	// sendPointCmds(args, "defense");
	public static boolean sendDefenseCommand(String[] args) {
		return(args[0].equalsIgnoreCase("defense"))?true:false;
	}
	
	// sendPointCmds(args, "dodge", "dodgeChance");
	public static boolean sendDodgeChanceCommand(String[] args) {
		return(args[0].equalsIgnoreCase("dodge") || args[0].equalsIgnoreCase("dodgeChance"))?true:false;
	}

	// sendPointCmds(args, "crit", "critical", "critChance", "criticalChance");
	public static boolean sendCritChanceCommand(String[] args) {
		return(args[0].equalsIgnoreCase("crit") || args[0].equalsIgnoreCase("critChance")
				|| args[0].equalsIgnoreCase("critical") || args[0].equalsIgnoreCase(
						"criticalChance"))?true:false;
	}
	
	// sendPointCmds(args, "mana");
	public static boolean sendManaCommand(String[] args) {
		return(args[0].equalsIgnoreCase("mana"))?true:false;
	}
	
	// sendPointCmds(args, "manaregen");
	public static boolean sendManaRegenCommand(String[] args) {
		return(args[0].equalsIgnoreCase("manaregen"))?true:false;
	}
*/
	public static boolean sendPointCommand(String[] args, String... pointType) {
		for(String type : pointType) {
			return((args[0].equalsIgnoreCase("point") || args[0].equalsIgnoreCase("points")) &&
					args[1].equalsIgnoreCase(type))?true:false;
		}
		return false;
	}

	public static boolean increaseTierCommand(String[] args) {
		return((args[0].equalsIgnoreCase("increasetier") || args[0].equalsIgnoreCase("tierIncrease") ||
				args[0].equalsIgnoreCase("upgradetier") || args[0].equalsIgnoreCase("tierupgrade")))?true:false;
	}

	public static boolean decreaseTierCommand(String[] args) {
		return(args[0].equalsIgnoreCase("decreasetier") || args[0].equalsIgnoreCase("tierdecrease") ||
				args[0].equalsIgnoreCase("downgradetier") || args[0].equalsIgnoreCase("tierdowngrade"))?true:false;
	}
}

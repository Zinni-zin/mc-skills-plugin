package zinnia.skills.player;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import zinnia.skills.main.Skills;
import zinnia.skills.utils.Colours;
import zinnia.skills.utils.TierUtils;

public class AdminCommands {

	public static void doACmds(CommandSender sender, Skills plugin, String cmd, String[] args) {
		// Reload the config if the command is being used through console.
		if(args[0].equalsIgnoreCase("reload")) {
			plugin.reloadConfig();
			plugin.saveConfig();
			sender.sendMessage(ChatColor.GREEN + "Config reloaded");
		}

		/*
		 * Get skill points
		 */

		// Get the skill point of a user
		else if(AdminCommands.getPointCommand(args, "skill", "skillpoint", "skills", "skillsPoint"))
			AdminCommands.getSkillPoint(sender, plugin, plugin.target(sender, args[1]));

		// Get the health point of a user
		else if(AdminCommands.getPointCommand(args, "health", "hp"))
			AdminCommands.getHealthPoint(sender, plugin, plugin.target(sender, args[1]));

		// Get the damage point of a user
		else if(AdminCommands.getPointCommand(args, "damage", "dmg"))
			AdminCommands.getDmgPoint(sender, plugin, plugin.target(sender, args[1]));

		// Get the defense point of the user
		else if(AdminCommands.getPointCommand(args, "defense"))
			AdminCommands.getDefensePoint(sender, plugin, plugin.target(sender, args[1]));
		
		// Get the dodge point of a user
		else if(AdminCommands.getPointCommand(args, "dodge", "dodgechance"))
			AdminCommands.getDodgePoint(sender, plugin, plugin.target(sender, args[1]));

		// Get the crit point of a user
		else if(AdminCommands.getPointCommand(args, "crit", "critchance", "critical", "criticalchance"))
			AdminCommands.getCritPoint(sender, plugin, plugin.target(sender, args[1]));

		// Get the mana point of a user
		else if(AdminCommands.getPointCommand(args, "mana"))
			AdminCommands.getManaPoint(sender, plugin, plugin.target(sender, args[1]));

		// Get the crit point of a user
		else if(AdminCommands.getPointCommand(args, "manaregen", "mana-regen", "regenmana", "regen-mana"))
			AdminCommands.getManaRegenPoint(sender, plugin, plugin.target(sender, args[1]));

		/*
		 * Random Misc, I know I'm an idiot...
		 */

		// Reset the points and level of a player
		else if(args[0].equalsIgnoreCase("reset")) 
			AdminCommands.resetPoints(sender, plugin, plugin.target(sender, args[1]));

		/*
		 * Remove skill points
		 */

		// Remove skill points from the player
		else if(AdminCommands.removePointCommand(args, "skill", "skillpoint", "skills", "skillsPoint"))
			AdminCommands.removeSkillPoints(sender, plugin,  plugin.target(sender, args[1]), args[3]);

		// Remove health points from the player
		else if(AdminCommands.removePointCommand(args, "health", "hp"))
			AdminCommands.removeHealthPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);

		// Remove damage points from the player
		else if(AdminCommands.removePointCommand(args, "dmg", "damage"))
			AdminCommands.removeDmgPoints(sender, plugin, plugin.target(args[1]), args[3]);

		// Remove defense points from the player
		else if(AdminCommands.removePointCommand(args, "defense"))
			AdminCommands.removeDefensePoints(sender, plugin, plugin.target(args[1]), args[3]);
		
		// Remove dodge points from the player
		else if(AdminCommands.removePointCommand(args, "dodge", "dodgechance"))
			AdminCommands.removeDodgePoints(sender, plugin, 
					plugin.target(sender, args[1]), args[3]);

		// Remove crit points from the player
		else if(AdminCommands.removePointCommand(args, "crit", "critchance", 
				"critical", "criticalchance")) 
			AdminCommands.removeCritPoints(sender, plugin,  
					plugin.target(sender, args[1]), args[3]);

		// Remove mana points from the player
		else if(AdminCommands.removePointCommand(args, "mana"))
			AdminCommands.removeManaPoints(sender, plugin, plugin.target(args[1]), args[3]);

		// Remove mana regen points from the player
		else if(AdminCommands.removePointCommand(args, "manaregen", "mana-regen", "regenmana", "regen-mana"))
			AdminCommands.removeMRegenPoints(sender, plugin, plugin.target(args[1]), args[3]);

		/*
		 * Give skill points
		 */

		// Give skill points to the player
		else if(AdminCommands.givePointCommand(args, "skill", "skillpoint", "skills", "skillsPoint"))
			AdminCommands.giveSkillPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);

		// Give health points to the player
		else if(AdminCommands.givePointCommand(args, "health", "hp"))
			AdminCommands.giveHealthPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);

		// Give damage points to the player
		else if(AdminCommands.givePointCommand(args, "damage", "dmg"))
			AdminCommands.giveDmgPoints(sender, plugin, plugin.target(args[1]), args[3]);

		// Give defense points to the player
		else if(AdminCommands.givePointCommand(args, "defense"))
			AdminCommands.giveDefensePoints(sender, plugin, plugin.target(args[1]), args[3]);
		
		// Give dodge points to the player
		else if(AdminCommands.givePointCommand(args, "dodge", "dodgechance"))
			AdminCommands.giveDodgePoints(sender, plugin, plugin.target(sender, args[1]), args[3]);

		// Give crit points to the player
		else if(AdminCommands.givePointCommand(args, "crit", "critchance", "critical", "criticalchance"))
			AdminCommands.giveCritPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);

		// Give mana points to the player
		else if(AdminCommands.givePointCommand(args, "mana"))
			AdminCommands.giveManaPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);

		// Give mana regen points to the player
		else if(AdminCommands.givePointCommand(args, "manaregen", "mana_regen", "regen_mana", "regenmana"))
			AdminCommands.giveManaRegenPoints(sender, plugin, plugin.target(sender, args[1]), args[3]);

		// Set tier ranks
		else if(AdminCommands.setTierRank(args))
			TierUtils.setTierCommand(plugin, sender, plugin.target(sender, args[1]), args[2]);

		else sender.sendMessage(ChatColor.RED + "Invalid agruments!");
	}

	/*
	 * Get points section
	 */

	// Skill point
	public static void getSkillPoint(CommandSender sender, Skills plugin, Player target) {
		sender.sendMessage(Colours.GREEN + target.getName() +  " has " + Colours.GOLD
				+ plugin.playerSkills.get(target.getUniqueId()).points + Colours.GREEN + " skill Poings");
	}

	// Health point
	public static void getHealthPoint(CommandSender sender, Skills plugin, Player target) {
		sender.sendMessage(Colours.GREEN + target.getName() +  " has " + Colours.RED
				+ plugin.playerSkills.get(target.getUniqueId()).healthPoints +
				Colours.GREEN + " health Points");
	}

	// Damage point
	public static void getDmgPoint(CommandSender sender, Skills plugin, Player target) {
		sender.sendMessage(Colours.GREEN + target.getName() +  " has " + Colours.YELLOW
				+ plugin.playerSkills.get(target.getUniqueId()).dmgPoints +
				Colours.GREEN + " damage Points");
	}

	// Defense point
	public static void getDefensePoint(CommandSender sender, Skills plugin, Player target) {
		sender.sendMessage(Colours.GREEN + target.getName() +  " has " + Colours.DARK_GRAY
				+ plugin.playerSkills.get(target.getUniqueId()).defensePoints +
				Colours.GREEN + " defense Points");
	}

	// Dodge point
	public static void getDodgePoint(CommandSender sender, Skills plugin, Player target) {
		sender.sendMessage(Colours.GREEN + target.getName() +  " has " + Colours.AQUA
				+ plugin.playerSkills.get(target.getUniqueId()).dodgePoints + 
				Colours.GREEN +  " dodge Points");
	}

	// Critical point
	public static void getCritPoint(CommandSender sender, Skills plugin, Player target) {
		sender.sendMessage(Colours.GREEN + target.getName() +  " has " + Colours.DARK_GREEN
				+ plugin.playerSkills.get(target.getUniqueId()).critPoints +
				Colours.GREEN + " dodge Points");
	}

	// Mana point
	public static void getManaPoint(CommandSender sender, Skills plugin, Player target) {
		sender.sendMessage(Colours.GREEN + target.getName() +  " has " + Colours.INDIGO
				+ plugin.playerSkills.get(target.getUniqueId()).manaPoints +
				Colours.GREEN + " mana Points");
	}

	// Mana Regen Point
	public static void getManaRegenPoint(CommandSender sender, Skills plugin, Player target) {
		sender.sendMessage(Colours.GREEN + target.getName() +  " has " + Colours.DARK_PURPLE
				+ plugin.playerSkills.get(target.getUniqueId()).manaRegenPoints +
				Colours.GREEN + " mana regen Points");
	}

	/*
	 * Misc section, I guess yet again I know, I'm an idiot
	 */
	public static void resetPoints(CommandSender sender, Skills plugin, Player target) {
		PlayerCommands.sendSkillPoint(plugin, target); // Send their previous amount of skill points
		PlayerCommands.sendHealthPoint(plugin, target); // Send their previous amount of health points
		PlayerCommands.sendDmgPoint(plugin, target); // Send their previous amount of damage points
		PlayerCommands.sendDefensePoint(plugin, target); // Send their previous amount of defense oints
		PlayerCommands.sendDodgePoint(plugin, target); // Send their previous amount of dodge points
		PlayerCommands.sendCritPoint(plugin, target); // Send their previous amount of critical points
		PlayerCommands.sendManaPoint(plugin, target); // Send their previous amount of mana points
		PlayerCommands.sendManaRegenPoint(plugin, target); // Send their previous amount of mana regen points

		plugin.playerSkills.get(target.getUniqueId()).points = 0; // Set skill points to zero
		plugin.playerSkills.get(target.getUniqueId()).lastLevel = 0; // Set the last level to zero
		plugin.playerSkills.get(target.getUniqueId()).healthPoints = 0; // Set health points to zero
		plugin.playerSkills.get(target.getUniqueId()).dmgPoints = 0; // Set damage points to zero
		plugin.playerSkills.get(target.getUniqueId()).defensePoints = 0; // Set the defense points to zero
		plugin.playerSkills.get(target.getUniqueId()).dodgePoints = 0; // Set dodge points to zero
		plugin.playerSkills.get(target.getUniqueId()).critPoints = 0; // Set critical points to zero
		plugin.playerSkills.get(target.getUniqueId()).manaPoints = 0; // Set mana points to zero
		plugin.playerSkills.get(target.getUniqueId()).manaRegenPoints = 0; // Set mana regen points to zero
		plugin.playerSkills.get(target.getUniqueId()).increaseHealth(target); // Change the health
		plugin.playerSkills.get(target.getUniqueId()).setMaxMana(target); // Set the max mana
		plugin.playerSkills.get(target.getUniqueId()).setManaRegen(target); // Set the mana regen
		target.setLevel(0); // Set the level to zero

		plugin.savePointsFile(target.getUniqueId()); // Save the file
		target.sendMessage(Colours.RED + "An admin has reset your skill points and level!"); // Send a message
	}

	/*
	 * Remove points section
	 */

	public static void removeSkillPoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try { // Try to do stuff because parsing...
			int removeAmount = Integer.parseInt(amount); // Parse the amount string

			// Get the total amount current points - removed points
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).points - removeAmount;
			if(totalAmount >= 0) { // Make sure we don't get negative points

				// Set the target's point amount to equal the total points
				plugin.playerSkills.get(target.getUniqueId()).points = totalAmount;
				plugin.savePointsFile(target.getUniqueId()); // Save the file

				// Send the target a message saying their points have been removed
				target.sendMessage(Colours.RED + "An admin has removed: " + removeAmount + " skill points!");

				// Send whoever did this command a message saying everything went well
				sender.sendMessage(Colours.GREEN + "You have removed " + amount +
						" skill points from " + target.getName());
			} else sender.sendMessage(Colours.RED + "Skill points would be less than 0!");
		} catch(Exception e) { 
			if(target != null) // Send message if parsed string is not a number
				sender.sendMessage(Colours.RED + amount + " is not a valid number!");
		}

	}

	// Remove health points, mostly the same as the remove skill points method above
	public static void removeHealthPoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try {
			int removeAmount = Integer.parseInt(amount);
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).healthPoints - removeAmount;
			if(totalAmount >= 0) {				
				plugin.playerSkills.get(target.getUniqueId()).healthPoints = totalAmount;
				plugin.savePointsFile(target.getUniqueId());
				plugin.playerSkills.get(target.getUniqueId()).increaseHealth(target); // Set the health
				target.sendMessage(Colours.RED + "An admin has removed: " + removeAmount + " health points!");
				sender.sendMessage(Colours.GREEN + "You have removed " + amount +
						" health points from " + target.getName());
			} else sender.sendMessage(Colours.RED + "Health points would be less than 0!");
		}  catch(Exception e) { 
			if(target != null)
				sender.sendMessage(Colours.RED + amount + " is not a valid number!");
		}
	}

	// Method for removing damage points, mostly the same as the skill points method above
	public static void removeDmgPoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try {
			int removeAmount = Integer.parseInt(amount);
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).dmgPoints - removeAmount;
			if(totalAmount >= 0) {				
				plugin.playerSkills.get(target.getUniqueId()).dmgPoints = totalAmount;
				plugin.savePointsFile(target.getUniqueId());
				target.sendMessage(Colours.RED + "An admin has removed: " + removeAmount + " damage points!");
				sender.sendMessage(Colours.GREEN + "You have removed " + amount +
						" damage points from " + target.getName());
			} else sender.sendMessage(Colours.RED + "Damage points would be less than 0!");
		}  catch(Exception e) { 
			if(target != null)
				sender.sendMessage(Colours.RED + amount + " is not a valid number!");
		}
	}

	// Method for removing defense points, mostly the same as the skill points method
	public static void removeDefensePoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try {
			int removeAmount = Integer.parseInt(amount);
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).defensePoints - removeAmount;
			if(totalAmount >= 0) {				
				plugin.playerSkills.get(target.getUniqueId()).defensePoints = totalAmount;
				plugin.savePointsFile(target.getUniqueId());
				target.sendMessage(Colours.RED + "An admin has removed: " + removeAmount + " defense points!");
				sender.sendMessage(Colours.GREEN + "You have removed " + amount +
						" defense points from " + target.getName());
			} else sender.sendMessage(Colours.RED + "defense points would be less than 0!");
		}  catch(Exception e) { 
			if(target != null)
				sender.sendMessage(Colours.RED + amount + " is not a valid number!");
		}
	}

	// Remove dodge points mostly the same as the remove skill points method
	public static void removeDodgePoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try {
			int removeAmount = Integer.parseInt(amount);
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).dodgePoints - removeAmount;
			if(totalAmount >= 0) {
				plugin.playerSkills.get(target.getUniqueId()).dodgePoints = totalAmount;
				plugin.savePointsFile(target.getUniqueId());
				target.sendMessage(Colours.RED + "An admin has removed: " + removeAmount + " dodge points!");
				sender.sendMessage(Colours.GREEN + "You have removed " + amount +
						" dodge points from " + target.getName());
			} else sender.sendMessage(Colours.RED + "Dodge points would be less than 0!");
		} catch(Exception e) { 
			if(target != null)
				sender.sendMessage(Colours.RED + amount + " is not a valid number!");
		}
	}

	// Remove crit points and like always it's mostly the same as the skill point method
	public static void removeCritPoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try {
			int removeAmount = Integer.parseInt(amount);
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).critPoints - removeAmount;
			if(totalAmount >= 0) {
				plugin.playerSkills.get(target.getUniqueId()).critPoints = totalAmount;
				plugin.savePointsFile(target.getUniqueId());
				target.sendMessage(Colours.RED + "An admin has removed: " + removeAmount + " crit points!");
				sender.sendMessage(Colours.GREEN + "You have removed " + amount +
						" crit points from " + target.getName());
			} else sender.sendMessage(Colours.RED + "Crit points would be less than 0!");
		} catch(Exception e) { 
			if(target != null)
				sender.sendMessage(Colours.RED + amount + " is not a valid number!");
		}
	}

	// Remove mana points from the player, most of it is the same as the skill points method
	public static void removeManaPoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try {
			int removeAmount = Integer.parseInt(amount);
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).manaPoints - removeAmount;
			if(totalAmount >= 0) {
				plugin.playerSkills.get(target.getUniqueId()).manaPoints = totalAmount;
				plugin.playerSkills.get(target.getUniqueId()).setMaxMana(target); // Set the mana
				plugin.savePointsFile(target.getUniqueId());
				target.sendMessage(Colours.RED + "An admin has removed: " + removeAmount + " mana points!");
				sender.sendMessage(Colours.GREEN + "You have removed " + amount +
						" mana points from " + target.getName());
			} else sender.sendMessage(Colours.RED + "Mana points would be less than 0!");
		} catch(Exception e) { 
			if(target != null)
				sender.sendMessage(Colours.RED + amount + " is not a valid number!");
		}
	}

	// Method for removing MANA REGEN, mostly the same as any other remove points method
	public static void removeMRegenPoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try {
			int removeAmount = Integer.parseInt(amount);
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).manaRegenPoints - removeAmount;
			if(totalAmount >= 0) {
				plugin.playerSkills.get(target.getUniqueId()).manaRegenPoints = totalAmount;
				plugin.playerSkills.get(target.getUniqueId()).setManaRegen(target); // Set the mana
				plugin.savePointsFile(target.getUniqueId());
				target.sendMessage(Colours.RED + "An admin has removed: " + removeAmount + " mana regen "
						+ "points!");
				sender.sendMessage(Colours.GREEN + "You have removed " + amount +
						" mana regen points from " + target.getName());
			} else sender.sendMessage(Colours.RED + "Mana regen points would be less than 0!");
		} catch(Exception e) { 
			if(target != null)
				sender.sendMessage(Colours.RED + amount + " is not a valid number!");
		}
	}

	/*
	 * Give points section
	 */

	// Give skill points. Tbh I should've done the comments with this method and not the one below it... rip
	public static void giveSkillPoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try {
			int giveAmount = Integer.parseInt(amount); // Parse amount

			// Get total amount of points
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).points + giveAmount;

			// Send message
			target.sendMessage(Colours.GREEN + "An admin gave you: " + giveAmount + " skill points!");

			// Set points to total amount
			plugin.playerSkills.get(target.getUniqueId()).points = totalAmount;
			plugin.savePointsFile(target.getUniqueId()); // Save file

			// Send message
			sender.sendMessage(Colours.GREEN + "You have gave " + amount +
					" skill points to " + target.getName());
		} catch(Exception e) { // Catch parsing errors
			if(target != null) 
				sender.sendMessage(Colours.RED + amount + " is not a valid number!");
		}
	}

	// Give player health points
	public static void giveHealthPoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try { // Try because parsing can cause errors
			int giveAmount = Integer.parseInt(amount); // Parse the amount string to turn it into numbers

			// The total amount of skill points to give, is equal to current points + the give amount
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).healthPoints + giveAmount;

			// Check if the total point surpasses the the max point limit
			if(totalAmount > Skills.maxHpPoints) {
				// Send a message saying it would surpass the max point limit
				sender.sendMessage(Colours.RED + "That would surpass the max health point limit!");
			} else  { // Set the target's health points to equal the total amount
				plugin.playerSkills.get(target.getUniqueId()).healthPoints = totalAmount;
				plugin.savePointsFile(target.getUniqueId()); // Save the file
				plugin.playerSkills.get(target.getUniqueId()).increaseHealth(target); // Set the health data

				// Send a message saying an admin gave them points
				target.sendMessage(Colours.GREEN + "An admin gave you: " + giveAmount + " health points!");

				// Tell an admin everything worked fine
				sender.sendMessage(Colours.GREEN + "You have gave " + amount +
						" health points to " + target.getName());
			}
		} catch(Exception e) { 
			if(target != null) // Check if the target is null(returns error in different method)
				sender.sendMessage(Colours.RED + amount + " is not a valid number!"); // Amount is not a #
		}
	}

	// Method for giving damage points, mostly the same as the give health points method
	public static void giveDmgPoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try {
			int giveAmount = Integer.parseInt(amount);
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).dmgPoints + giveAmount;
			if(totalAmount > Skills.maxDefensePoints) {
				sender.sendMessage(Colours.RED + "That would surpass the max damage point limit!");
			} else {
				plugin.playerSkills.get(target.getUniqueId()).dmgPoints = totalAmount;
				plugin.savePointsFile(target.getUniqueId());
				target.sendMessage(Colours.GREEN + "An admin gave you: " + giveAmount + " damage points!");
				sender.sendMessage(Colours.GREEN + "You have gave " + amount +
						" damage points to " + target.getName());
			} 
		} catch(Exception e) { 
			if(target != null)
				sender.sendMessage(Colours.RED + amount + " is not a valid number!");
		}
	}

	// Method for giving defense points, mostly the same as the give health points method
	public static void giveDefensePoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try {
			int giveAmount = Integer.parseInt(amount);
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).defensePoints + giveAmount;
			if(totalAmount > Skills.maxDefensePoints) {
				sender.sendMessage(Colours.RED + "That would surpass the max defense point limit!");
			} else {
				plugin.playerSkills.get(target.getUniqueId()).defensePoints = totalAmount;
				plugin.savePointsFile(target.getUniqueId());
				target.sendMessage(Colours.GREEN + "An admin gave you: " + giveAmount + " defense points!");
				sender.sendMessage(Colours.GREEN + "You have gave " + amount +
						" defense points to " + target.getName());
			} 
		} catch(Exception e) { 
			if(target != null)
				sender.sendMessage(Colours.RED + amount + " is not a valid number!");
		}
	}

	// Method for giving dodge points yet again mostly the same as the health points method
	public static void giveDodgePoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try {
			int giveAmount = Integer.parseInt(amount);
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).dodgePoints + giveAmount;
			if(totalAmount > Skills.maxDodgePoints) {
				sender.sendMessage(Colours.RED + "That would surpass the max dodge point limit!");
			} else {
				plugin.playerSkills.get(target.getUniqueId()).dodgePoints = totalAmount;
				plugin.savePointsFile(target.getUniqueId());
				target.sendMessage(Colours.GREEN + "An admin gave you: " + giveAmount + " dodge points!");
				sender.sendMessage(Colours.GREEN + "You have gave " + amount +
						" dodge points to " + target.getName());
			} 
		} catch(Exception e) { 
			if(target != null)
				sender.sendMessage(Colours.RED + amount + " is not a valid number!");
		}
	}

	// Method for giving a target critical points, take a wild guess, mostly the same as the other methods
	public static void giveCritPoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try {
			int giveAmountAmount = Integer.parseInt(amount);
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).critPoints + giveAmountAmount;
			if(totalAmount > Skills.maxCritPoints) {
				sender.sendMessage(Colours.RED + "That would surpass the max crit point limit!");
			} else  {
				plugin.playerSkills.get(target.getUniqueId()).critPoints = totalAmount;
				plugin.savePointsFile(target.getUniqueId());
				target.sendMessage(Colours.GREEN + "An admin gave you: " + giveAmountAmount + " crit points!");
				sender.sendMessage(Colours.GREEN + "You have gave " + amount +
						" crit points to " + target.getName());
			}
		} catch(Exception e) { 
			if(target != null)
				sender.sendMessage(Colours.RED + amount + " is not a valid number!");
		}
	}

	// Method to give mana points you should know by this point
	public static void giveManaPoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try {
			int giveAmountAmount = Integer.parseInt(amount);
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).manaPoints + giveAmountAmount;
			if(totalAmount > Skills.maxManaPoints) {
				sender.sendMessage(Colours.RED + "That would surpass the max mana point limit!");
			} else  {
				plugin.playerSkills.get(target.getUniqueId()).manaPoints = totalAmount;
				plugin.playerSkills.get(target.getUniqueId()).setMaxMana(target); // Set the max mana
				plugin.savePointsFile(target.getUniqueId());
				target.sendMessage(Colours.GREEN + "An admin gave you: " + giveAmountAmount + " mana points!");
				sender.sendMessage(Colours.GREEN + "You have gave " + amount +
						" mana points to " + target.getName());
			}
		} catch(Exception e) { 
			if(target != null)
				sender.sendMessage(Colours.RED + amount + " is not a valid number!");
		}
	}

	// Method to give mana regen points to a targeted player
	public static void giveManaRegenPoints(CommandSender sender, Skills plugin, Player target, String amount) {
		try {
			int giveAmountAmount = Integer.parseInt(amount); // Attempt to parse the giving amount

			// Get the total amount the player would have
			int totalAmount = plugin.playerSkills.get(target.getUniqueId()).manaRegenPoints + giveAmountAmount;

			// Check if the total amount is greater than the max they can obtain 
			if(totalAmount > Skills.maxManaRegenPoints) { // Send a message saying it goes past the point limit
				sender.sendMessage(Colours.RED + "That would surpass the max mana regen point limit!"); 
			} else  {
				plugin.playerSkills.get(target.getUniqueId()).manaRegenPoints = totalAmount; // Set the amount of mana regen points they have
				plugin.playerSkills.get(target.getUniqueId()).setManaRegen(target); // Set mana regen
				plugin.savePointsFile(target.getUniqueId()); // Save the player file

				target.sendMessage(Colours.GREEN + "An admin gave you: " + giveAmountAmount + " mana regen"
						+ " points!"); // Send a message saying someone gave them points

				sender.sendMessage(Colours.GREEN + "You have gave " + amount +
						" mana regen points to " + target.getName()); // Send the player or console a message saying everything worked
			}
		} catch(Exception e) {
			if(target != null) // If the target isn't null(message gets sent other method if they are null)
				sender.sendMessage(Colours.RED + amount + " is not a valid number!"); // Tell them they didn't enter a valid number
		}
	}

	// Basic command typed to get an amount of points a player has
	public static boolean getPointCommand(String[] args, String... pointType) {
		for(String type : pointType) {
			return(args[0].equals("get") && args[2].equalsIgnoreCase(type))?true:false;
		}
		return false;
	}

	// Basic command typed to remove points from a player
	public static boolean removePointCommand(String[] args, String... pointType) {
		for(String type : pointType) {
			return(args[0].equals("remove") && args[2].equalsIgnoreCase(type))?true:false;
		}
		return false;
	}

	// Basic command to give points to a player
	public static boolean givePointCommand(String[] args, String... pointType) {
		for(String type : pointType) {
			return(args[0].equalsIgnoreCase("give") && args[2].equalsIgnoreCase(type))?true:false;
		}
		return false;
	}

	public static boolean setTierRank(String[] args) {
		return(args[0].equalsIgnoreCase("settier") || args[0].equalsIgnoreCase("set_tier"))?true:false;
	}
}

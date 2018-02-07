package zinnia.skills.utils;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import zinnia.skills.main.Skills;

public class TierUtils {

	/*
	 * Increase tier based on level
	 */
	public static void increaseTierOnLevel(Skills plugin, Player player) {
		UUID playerUUID = player.getUniqueId();

		// Increase to tier two
		if(player.hasPermission("skills.tier.one") && Skills.useTierTwo && player.getLevel() == Skills.tierTwoLevel ) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.two", true); // Increases to tier 2
			plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.one"); // Remove previous tier

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.GREEN + "You have increased to tier 2!");
		}

		// Increase to tier three
		else if(player.hasPermission("skills.tier.two") && Skills.useTierThree && player.getLevel() == Skills.tierThreeLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.three", true); // Increases to tier 3
			plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.two"); // Remove previous tier

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.GREEN + "You have increased to tier 3!");
		}

		// Increase to tier four
		else if(player.hasPermission("skills.tier.three") && Skills.useTierFour && player.getLevel() == Skills.tierFourLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.four", true); // Increases to tier 4
			plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.three"); // Remove previous tier

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.GREEN + "You have increased to tier 4!");
		}

		// Increase to tier five
		else if(player.hasPermission("skills.tier.four") && Skills.useTierFive && player.getLevel() == Skills.tierFiveLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.five", true); // Increases to tier 5
			plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.four"); // Remove previous tier

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.GREEN + "You have increased to tier 5!");
		}

		// Increase to tier six
		else if(player.hasPermission("skills.tier.five") && Skills.useTierSix && player.getLevel() == Skills.tierSixLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.six", true); // Increases to tier 6
			plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.five"); // Remove previous tier

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.GREEN + "You have increased to tier 6!");
		} 

		// Increase to tier seven
		else if(player.hasPermission("skills.tier.six") && Skills.useTierSeven && player.getLevel() == Skills.tierSevenLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.seven", true); // Increases to tier 7
			plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.six"); // Remove previous tier

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.GREEN + "You have increased to tier 7!");
		}

		// Increase to tier eight
		else if(player.hasPermission("skills.tier.seven") && Skills.useTierEight && player.getLevel() == Skills.tierEightLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.eight", true); // Increases to tier 8
			plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.seven"); // Remove previous tier

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.GREEN + "You have increased to tier 8!");
		}

		// Increase to tier nine
		else if(player.hasPermission("skills.tier.eight") && Skills.useTierNine && player.getLevel() == Skills.tierNineLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.nine", true); // Increases to tier 9
			plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.eight"); // Remove previous tier

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.GREEN + "You have increased to tier 9!");
		}

		// Increase to tier ten
		else if(player.hasPermission("skills.tier.nine") && Skills.useTierTen && player.getLevel() == Skills.tierTenLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.ten", true); // Increases to tier 10
			plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.nine"); // Remove previous tier

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.GREEN + "You have increased to tier 10!");
		}

		else if (Skills.useTierOne && player.getLevel() == Skills.tierOneLevel) { // Increase to tier one if they don't have any tier perm
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.one", true); 
			
			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.GREEN + "You have increased to tier 1!");
		}
	}

	/*
	 * Increase tier based on command
	 */
	public static void increaseTierOnCommand(Skills plugin, Player target, CommandSender sender) {
		UUID targetUUID = target.getUniqueId();

		// Increase to tier two
		if(target.hasPermission("skills.tier.one")) {
			if(Skills.useTierTwo) { // Check if we can increase to tier two
				plugin.tierPerms.get(targetUUID).setPermission("skills.tier.two", true); // Increases to tier 2
				plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.one"); // Remove previous tier

				if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
				if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
				if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
				
				plugin.savePointsFile(targetUUID); // Save the point files
				
				// Tell the sender they increase their target's tier
				sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 2!");

				// Check if the sender is a player or not, if they are a player use their name in the increase message
				if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.GREEN +
						" has increased you to tier 2!");

				else target.sendMessage(ChatColor.GREEN + "an admin has increased you to tier 2!");
			}
			else sender.sendMessage(ChatColor.RED + "Tier two doesn't exist!"); // Tell the sender there is no tier two!
		}

		// Increase to tier three
		else if(target.hasPermission("skills.tier.two")) {
			if(Skills.useTierThree) { // Check if we can increase to tier three
				plugin.tierPerms.get(targetUUID).setPermission("skills.tier.three", true); // Increases to tier 3
				plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.two"); // Remove previous tier

				if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
				if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
				if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
				
				plugin.savePointsFile(targetUUID); // Save the point files
				
				// Tell the sender they increase their target's tier
				sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 3!");

				// Check if the sender is a player or not, if they are a player use their name in the increase message
				if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.GREEN +
						" has increased you to tier 3!");

				else target.sendMessage(ChatColor.GREEN + "an admin has increased you to tier 3!");
			}
			else sender.sendMessage(ChatColor.RED + "Tier three doesn't exist!"); // Tell the sender there is no tier three!
		}

		// Increase to tier four
		else if(target.hasPermission("skills.tier.three")) {
			if(Skills.useTierFour) { // Check if we can increase to tier four
				plugin.tierPerms.get(targetUUID).setPermission("skills.tier.four", true); // Increases to tier 4
				plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.three"); // Remove previous tier

				if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
				if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
				if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
				
				plugin.savePointsFile(targetUUID); // Save the point files
				
				// Tell the sender they increase their target's tier
				sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 4!");

				// Check if the sender is a player or not, if they are a player use their name in the increase message
				if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.GREEN +
						" has increased you to tier 4!");

				else target.sendMessage(ChatColor.GREEN + "an admin has increased you to tier 4!");
			} 
			else sender.sendMessage(ChatColor.RED + "Tier four doesn't exist!"); // Tell the sender there is no tier four
		}

		// Increase to tier five
		else if(target.hasPermission("skills.tier.four")) {
			if(Skills.useTierFive) { // Check if we can increase to tier five
				plugin.tierPerms.get(targetUUID).setPermission("skills.tier.five", true); // Increases to tier 5
				plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.four"); // Remove previous tier

				if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
				if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
				if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
				
				plugin.savePointsFile(targetUUID); // Save the point files
				
				// Tell the sender they increase their target's tier
				sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 5!");

				// Check if the sender is a player or not, if they are a player use their name in the increase message
				if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.GREEN +
						" has increased you to tier 5!");

				else target.sendMessage(ChatColor.GREEN + "an admin has increased you to tier 5!");
			}
			else sender.sendMessage(ChatColor.RED + "Tier five doesn't exist!"); // Tell the sender there is no tier five
		}

		// Increase to tier six
		else if(target.hasPermission("skills.tier.five")) {
			if(Skills.useTierSix) { // Check if we can increase to tier six
				plugin.tierPerms.get(targetUUID).setPermission("skills.tier.six", true); // Increases to tier 6
				plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.five"); // Remove previous tier

				if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
				if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
				if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
				
				plugin.savePointsFile(targetUUID); // Save the point files
				
				// Tell the sender they increase their target's tier
				sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 6!");

				// Check if the sender is a player or not, if they are a player use their name in the increase message
				if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.GREEN +
						" has increased you to tier 6!");

				else target.sendMessage(ChatColor.GREEN + "an admin has increased you to tier 6!");
			}
			else sender.sendMessage(ChatColor.RED + "Tier six doesn't exist!"); // Tell the sender there is no tier six
		} 

		// Increase to tier seven
		else if(target.hasPermission("skills.tier.six")) {
			if(Skills.useTierSeven) { // Check if we can increase to tier seven
				plugin.tierPerms.get(targetUUID).setPermission("skills.tier.seven", true); // Increases to tier 7
				plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.six"); // Remove previous tier

				if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
				if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
				if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
				
				plugin.savePointsFile(targetUUID); // Save the point files
				
				// Tell the sender they increase their target's tier
				sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 7!");

				// Check if the sender is a player or not, if they are a player use their name in the increase message
				if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.GREEN +
						" has increased you to tier 7!");

				else target.sendMessage(ChatColor.GREEN + "an admin has increased you to tier 7!");
			}
			else sender.sendMessage(ChatColor.RED + "Tier seven doesn't exist!"); // Tell the sender there is no tier seven
		}

		// Increase to tier eight
		else if(target.hasPermission("skills.tier.seven")) {
			if(Skills.useTierEight) { // Check if we can increase to tier eight
				plugin.tierPerms.get(targetUUID).setPermission("skills.tier.eight", true); // Increases to tier 8
				plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.seven"); // Remove previous tier

				if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
				if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
				if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
				
				plugin.savePointsFile(targetUUID); // Save the point files
				
				// Tell the sender they increase their target's tier
				sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 8!");

				// Check if the sender is a player or not, if they are a player use their name in the increase message
				if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.GREEN +
						" has increased you to tier 8!");

				else target.sendMessage(ChatColor.GREEN + "an admin has increased you to tier 8!");
			}
			else sender.sendMessage(ChatColor.RED + "Tier eight doesn't exist!"); // Tell the sender there is no tier eight
		}

		// Increase to tier nine
		else if(target.hasPermission("skills.tier.eight")) {
			if(Skills.useTierNine) { // Check if we can increase to tier nine
				plugin.tierPerms.get(targetUUID).setPermission("skills.tier.nine", true); // Increases to tier 9
				plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.eight"); // Remove previous tier

				if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
				if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
				if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
				
				plugin.savePointsFile(targetUUID); // Save the point files
				
				// Tell the sender they increase their target's tier
				sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 9!");

				// Check if the sender is a player or not, if they are a player use their name in the increase message
				if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.GREEN +
						" has increased you to tier 9!");

				else target.sendMessage(ChatColor.GREEN + "an admin has increased you to tier 9!");
			}
			else sender.sendMessage(ChatColor.RED + "Tier nine doesn't exist!"); // Tell the sender there is no tier nine
		} 

		// Increase to tier ten
		else if(target.hasPermission("skills.tier.nine")) {
			if(Skills.useTierTen) { // Check if we can increase to tier ten
				plugin.tierPerms.get(targetUUID).setPermission("skills.tier.ten", true); // Increases to tier 10
				plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.nine"); // Remove previous tier

				if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
				if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
				if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
				
				plugin.savePointsFile(targetUUID); // Save the point files
				
				// Tell the sender they increase their target's tier
				sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 10!");

				// Check if the sender is a player or not, if they are a player use their name in the increase message
				if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.GREEN +
						" has increased you to tier 10!");

				else target.sendMessage(ChatColor.GREEN + "an admin has increased you to tier 10!");
			}
			else sender.sendMessage(ChatColor.RED + "Tier ten doesn't exist!"); // Tell the sender there is no tier ten
		}

		// Make sure they don't go past tier ten since there is no tier past it
		else if(target.hasPermission("skills.tier.ten")) {
			// Send a message to the sender telling them there is no tier past 10
			sender.sendMessage(ChatColor.RED + target.getName() + " is at the top tier!"); 
		}

		else { // Increase to tier one if they don't have any tier perm
			if(Skills.useTierOne) { // Check if we can increase to tier one
				if(plugin == null) System.out.println("\n\n\n\n\n\nPlugin is null!");
				plugin.tierPerms.get(targetUUID).setPermission("skills.tier.one", true);

				if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
				if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
				if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
				
				plugin.savePointsFile(targetUUID); // Save the point files
				
				// Tell the sender they increase their target's tier
				sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 1!");

				// Check if the sender is a player or not, if they are a player use their name in the increase message
				if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.GREEN +
						" has increased you to tier 1!");

				else target.sendMessage(ChatColor.GREEN + "an admin has increased you to tier 1!");
			}
			else sender.sendMessage(ChatColor.GREEN + "Tier one doesn't exist!"); // Tell the sender there is no tier one(meaning no tiers at all...)
		}
	}

	/*
	 * Decrease tier based on level
	 */
	public static void decreaseTierOnLevel(Skills plugin, Player player) {
		UUID playerUUID = player.getUniqueId();

		// Decrease to tier 9
		if(player.getLevel() < Skills.tierTenLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.nine", true);

			if(player.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.ten");
			
			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.RED + "you have decreased to tier 9!"); // Tell the player they decreased in tier!
		}

		// Decrease to tier 8
		else if(player.getLevel() < Skills.tierNineLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.eight", true);

			if(player.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.nine");
			if(player.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.ten");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.RED + "you have decreased to tier 8!"); // Tell the player they decreased in tier!
		}

		// Decrease to tier 7
		else if(player.getLevel() < Skills.tierEightLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.seven", true);

			if(player.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.eight");
			if(player.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.nine");
			if(player.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.ten");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.RED + "you have decreased to tier 7!"); // Tell the player they decreased in tier!
		}

		// Decrease to tier 6
		else if(player.getLevel() < Skills.tierSevenLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.six", true);

			if(player.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.seven");
			if(player.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.eight");
			if(player.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.nine");
			if(player.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.ten");
			
			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files

			player.sendMessage(ChatColor.RED + "you have decreased to tier 6!"); // Tell the player they decreased in tier!
		}

		// Decrease to tier 5
		else if(player.getLevel() < Skills.tierSixLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.five", true);

			if(player.hasPermission("skills.tier.six"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.six");
			if(player.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.seven");
			if(player.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.eight");
			if(player.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.nine");
			if(player.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.ten");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.RED + "you have decreased to tier 5!"); // Tell the player they decreased in tier!
		}

		// Decrease to tier 4
		else if(player.getLevel() < Skills.tierFiveLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.four", true);

			if(player.hasPermission("skills.tier.five"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.five");
			if(player.hasPermission("skills.tier.six"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.six");
			if(player.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.seven");
			if(player.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.eight");
			if(player.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.nine");
			if(player.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.ten");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.RED + "you have decreased to tier 4!"); // Tell the player they decreased in tier!
		}

		// Decrease to tier 3
		else if(player.getLevel() < Skills.tierFourLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.three", true);

			if(player.hasPermission("skills.tier.four"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.four");
			if(player.hasPermission("skills.tier.five"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.five");
			if(player.hasPermission("skills.tier.six"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.six");
			if(player.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.seven");
			if(player.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.eight");
			if(player.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.nine");
			if(player.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.ten");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.RED + "you have decreased to tier 3!"); // Tell the player they decreased in tier!
		}

		// Decrease to tier 2
		else if(player.getLevel() < Skills.tierThreeLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.one", true);

			if(player.hasPermission("skills.tier.three"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.three");
			if(player.hasPermission("skills.tier.four"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.four");
			if(player.hasPermission("skills.tier.five"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.five");
			if(player.hasPermission("skills.tier.six"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.six");
			if(player.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.seven");
			if(player.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.eight");
			if(player.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.nine");
			if(player.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.ten");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.RED + "you have decreased to tier 2!"); // Tell the player they decreased in tier!
		}

		// Decrease to tier 1
		else if(player.getLevel() < Skills.tierTwoLevel) {
			plugin.tierPerms.get(playerUUID).setPermission("skills.tier.one", true);

			if(player.hasPermission("skills.tier.two"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.two");
			if(player.hasPermission("skills.tier.three"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.three");
			if(player.hasPermission("skills.tier.four"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.four");
			if(player.hasPermission("skills.tier.five"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.five");
			if(player.hasPermission("skills.tier.six"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.six");
			if(player.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.seven");
			if(player.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.eight");
			if(player.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.nine");
			if(player.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.ten");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.RED + "you have decreased to tier 1!"); // Tell the player they decreased in tier!
		}

		// Decrease to no tier
		else if(player.getLevel() < Skills.tierOneLevel) {
			if(player.hasPermission("skills.tier.one"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.one");
			if(player.hasPermission("skills.tier.two"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.two");
			if(player.hasPermission("skills.tier.three"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.three");
			if(player.hasPermission("skills.tier.four"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.four");
			if(player.hasPermission("skills.tier.five"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.five");
			if(player.hasPermission("skills.tier.six"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.six");
			if(player.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.seven");
			if(player.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.eight");
			if(player.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.nine");
			if(player.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(playerUUID).unsetPermission("skills.tier.ten");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			plugin.savePointsFile(playerUUID); // Save the point files
			
			player.sendMessage(ChatColor.RED + "you have decreased to tier 0!"); // Tell the player they decreased in tier!
		}
	}

	/*
	 * Decrease tier based on command
	 */
	public static void decraseTierOnCommand(Skills plugin, Player target, CommandSender sender) {
		UUID targetUUID = target.getUniqueId();

		// Decrease to tier 9
		if(target.hasPermission("skills.tier.ten")) {
			plugin.tierPerms.get(targetUUID).setPermission("skills.tier.nine", true);
			plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.ten");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
			
			plugin.savePointsFile(targetUUID); // Save the point files
			
			// Tell the sender they decreased their target's tier
			sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 9!");

			// Check if the sender is a player or not, if they are a player use their name in the decrease message
			if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.RED + " has decreased you to tier 9!");
			else target.sendMessage(ChatColor.RED + "an admin has decreased you to tier 9!");
		}

		// Decrease to tier 8
		else if(target.hasPermission("skills.tier.nine")) {
			plugin.tierPerms.get(targetUUID).setPermission("skills.tier.eight", true);
			plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.nine");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
			
			plugin.savePointsFile(targetUUID); // Save the point files
			
			// Tell the sender they decreased their target's tier
			sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 8!");

			// Check if the sender is a player or not, if they are a player use their name in the decrease message
			if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.RED + " has decreased you to tier 8!");
			else target.sendMessage(ChatColor.RED + "an admin has decreased you to tier 8!");
		}

		// Decrease to tier 7
		else if(target.hasPermission("skills.tier.eight")) {
			plugin.tierPerms.get(targetUUID).setPermission("skills.tier.seven", true);
			plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.eight");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
			
			plugin.savePointsFile(targetUUID); // Save the point files
			
			// Tell the sender they decreased their target's tier
			sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 7!");

			// Check if the sender is a player or not, if they are a player use their name in the decrease message
			if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.RED + " has decreased you to tier 7!");
			else target.sendMessage(ChatColor.RED + "an admin has decreased you to tier 7!");
		}

		// Decrease to tier 6
		else if(target.hasPermission("skills.tier.seven")) {
			plugin.tierPerms.get(targetUUID).setPermission("skills.tier.six", true);
			plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.seven");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
			
			plugin.savePointsFile(targetUUID); // Save the point files
			
			// Tell the sender they decreased their target's tier
			sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 6!");

			// Check if the sender is a player or not, if they are a player use their name in the decrease message
			if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.RED + " has decreased you to tier 6!");
			else target.sendMessage(ChatColor.RED + "an admin has decreased you to tier 6!");
		}

		// Decrease to tier 5
		else if(target.hasPermission("skills.tier.six")) {
			plugin.tierPerms.get(targetUUID).setPermission("skills.tier.five", true);
			plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.six");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
			
			plugin.savePointsFile(targetUUID); // Save the point files
			
			// Tell the sender they decreased their target's tier
			sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 5!");

			// Check if the sender is a player or not, if they are a player use their name in the decrease message
			if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.RED + " has decreased you to tier 5!");
			else target.sendMessage(ChatColor.RED + "an admin has decreased you to tier 5!");
		}

		// Decrease to tier 4
		else if(target.hasPermission("skills.tier.five")) {
			plugin.tierPerms.get(targetUUID).setPermission("skills.tier.four", true);
			plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.five");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
			
			plugin.savePointsFile(targetUUID); // Save the point files
			
			// Tell the sender they decreased their target's tier
			sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 4!");

			// Check if the sender is a player or not, if they are a player use their name in the decrease message
			if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.RED + " has decreased you to tier 4!");
			else target.sendMessage(ChatColor.RED + "an admin has decreased you to tier 4!");
		}

		// Decrease to tier 3
		else if(target.hasPermission("skills.tier.four")) {
			plugin.tierPerms.get(targetUUID).setPermission("skills.tier.three", true);
			plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.four");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
			
			plugin.savePointsFile(targetUUID); // Save the point files
			
			// Tell the sender they decreased their target's tier
			sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 3!");

			// Check if the sender is a player or not, if they are a player use their name in the decrease message
			if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.RED + " has decreased you to tier 3!");
			else target.sendMessage(ChatColor.RED + "an admin has decreased you to tier 3!");
		}

		// Decrease to tier 2
		else if(target.hasPermission("skills.tier.three")) {
			plugin.tierPerms.get(targetUUID).setPermission("skills.tier.one", true);
			plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.three");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
			
			plugin.savePointsFile(targetUUID); // Save the point files
			
			// Tell the sender they decreased their target's tier
			sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 2!");

			// Check if the sender is a player or not, if they are a player use their name in the decrease message
			if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.RED + " has decreased you to tier 2!");
			else target.sendMessage(ChatColor.RED + "an admin has decreased you to tier 2!");
		}

		// Decrease to tier 1
		else if(target.hasPermission("skills.tier.two")) {
			plugin.tierPerms.get(targetUUID).setPermission("skills.tier.one", true);
			plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.two");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
			
			plugin.savePointsFile(targetUUID); // Save the point files
			
			// Tell the sender they decreased their target's tier
			sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 1!");

			// Check if the sender is a player or not, if they are a player use their name in the decrease message
			if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.RED + " has decreased you to tier 1!");
			else target.sendMessage(ChatColor.RED + "an admin has decreased you to tier 1!");
		}

		// Decrease to no tier
		else if(target.hasPermission("skills.tier.one")) {
			plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.one");

			if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
			if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
			if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
			
			plugin.savePointsFile(targetUUID); // Save the point files
			
			// Tell the sender they decreased their target's tier
			sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + target.getName() + ChatColor.GREEN + " to tier 0!");

			// Check if the sender is a player or not, if they are a player use their name in the decrease message
			if(sender instanceof Player) target.sendMessage(ChatColor.GOLD + sender.getName() + ChatColor.RED + " has decreased you to tier 0!");
			else target.sendMessage(ChatColor.RED + "an admin has decreased you to tier 0!");
		}
	}

	public static void setTierCommand(Skills plugin, CommandSender sender, Player target, String tier) {
		try {
			int tierNum = Integer.parseInt(tier);
			UUID targetUUID = target.getUniqueId();

			if(tierNum > 0 && tierNum < 11) {
				// Set them to tier 1
				if(tierNum == 1) {
					if(Skills.useTierTwo) {
						plugin.tierPerms.get(targetUUID).setPermission("skills.tier.one", true); // Set the target to tier one

						// Make sure they don't have the other tiers
						if(target.hasPermission("skills.tier.two"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.two");
						if(target.hasPermission("skills.tier.three"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.three");
						if(target.hasPermission("skills.tier.four"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.four");
						if(target.hasPermission("skills.tier.five"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.five");
						if(target.hasPermission("skills.tier.six"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.six");
						if(target.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.seven");
						if(target.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.eight");
						if(target.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.nine");
						if(target.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.ten");

						if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
						if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
						if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
						
						plugin.savePointsFile(targetUUID); // Save the point files
						
						// Send the sender a message saying the command worked!
						sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + 
								target.getName() + ChatColor.GREEN + "'s tier to 1!");

						// Send target a message saying someone switched their tier
						target.sendMessage(ChatColor.GREEN + "An admin has set you to tier 1!");
					} else sender.sendMessage(ChatColor.RED + "Tier 1 doesn't exist!"); 
				} 

				// Set them to tier two
				else if(tierNum == 2) {
					if(Skills.useTierTwo) {
						plugin.tierPerms.get(targetUUID).setPermission("skills.tier.two", true);

						if(target.hasPermission("skills.tier.one"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.one");
						if(target.hasPermission("skills.tier.three"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.three");
						if(target.hasPermission("skills.tier.four"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.four");
						if(target.hasPermission("skills.tier.five"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.five");
						if(target.hasPermission("skills.tier.six"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.six");
						if(target.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.seven");
						if(target.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.eight");
						if(target.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.nine");
						if(target.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.ten");

						if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
						if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
						if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
						
						plugin.savePointsFile(targetUUID); // Save the point files
						
						sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + 
								target.getName() + ChatColor.GREEN + "'s tier to 2!");

						target.sendMessage(ChatColor.GREEN + "An admin has set you to tier 2!");
					} else sender.sendMessage(ChatColor.RED + "Tier 2 doesn't exist!");
				}

				// Set them to tier three
				else if(tierNum == 3) {
					if(Skills.useTierTwo) {
						plugin.tierPerms.get(targetUUID).setPermission("skills.tier.three", true);

						if(target.hasPermission("skills.tier.one"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.one");
						if(target.hasPermission("skills.tier.two"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.two");
						if(target.hasPermission("skills.tier.four"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.four");
						if(target.hasPermission("skills.tier.five"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.five");
						if(target.hasPermission("skills.tier.six"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.six");
						if(target.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.seven");
						if(target.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.eight");
						if(target.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.nine");
						if(target.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.ten");

						if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
						if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
						if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
						
						plugin.savePointsFile(targetUUID); // Save the point files
						
						sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + 
								target.getName() + ChatColor.GREEN + "'s tier to 3!");

						target.sendMessage(ChatColor.GREEN + "An admin has set you to tier 3!");
					} else sender.sendMessage(ChatColor.RED + "Tier 3 doesn't exist!");
				}

				// Set them to tier four
				else if(tierNum == 4) {
					if(Skills.useTierTwo) {
						plugin.tierPerms.get(targetUUID).setPermission("skills.tier.four", true);

						if(target.hasPermission("skills.tier.one"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.one");
						if(target.hasPermission("skills.tier.two"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.two");
						if(target.hasPermission("skills.tier.three"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.three");
						if(target.hasPermission("skills.tier.five"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.five");
						if(target.hasPermission("skills.tier.six"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.six");
						if(target.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.seven");
						if(target.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.eight");
						if(target.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.nine");
						if(target.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.ten");

						if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
						if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
						if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
						
						plugin.savePointsFile(targetUUID); // Save the point files
						
						sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + 
								target.getName() + ChatColor.GREEN + "'s tier to 4!");

						target.sendMessage(ChatColor.GREEN + "An admin has set you to tier 4!");
					} else sender.sendMessage(ChatColor.RED + "Tier 4 doesn't exist!");
				}

				// Set them to tier five
				else if(tierNum == 5) {
					if(Skills.useTierTwo) {
						plugin.tierPerms.get(targetUUID).setPermission("skills.tier.five", true);

						if(target.hasPermission("skills.tier.one"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.one");
						if(target.hasPermission("skills.tier.two"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.two");
						if(target.hasPermission("skills.tier.three"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.three");
						if(target.hasPermission("skills.tier.four"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.four");
						if(target.hasPermission("skills.tier.six"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.six");
						if(target.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.seven");
						if(target.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.eight");
						if(target.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.nine");
						if(target.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.ten");

						if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
						if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
						if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
						
						plugin.savePointsFile(targetUUID); // Save the point files
						
						sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + 
								target.getName() + ChatColor.GREEN + "'s tier to 5!");

						target.sendMessage(ChatColor.GREEN + "An admin has set you to tier 5!");
					} else sender.sendMessage(ChatColor.RED + "Tier 5 doesn't exist!");
				}

				// Set them to tier six
				else if(tierNum == 6) {
					if(Skills.useTierTwo) {
						plugin.tierPerms.get(targetUUID).setPermission("skills.tier.six", true);

						if(target.hasPermission("skills.tier.one"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.one");
						if(target.hasPermission("skills.tier.two"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.two");
						if(target.hasPermission("skills.tier.three"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.three");
						if(target.hasPermission("skills.tier.four"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.four");
						if(target.hasPermission("skills.tier.five"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.five");
						if(target.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.seven");
						if(target.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.eight");
						if(target.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.nine");
						if(target.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.ten");

						if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
						if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
						if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
						
						plugin.savePointsFile(targetUUID); // Save the point files
						
						sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + 
								target.getName() + ChatColor.GREEN + "'s tier to 6!");

						target.sendMessage(ChatColor.GREEN + "An admin has set you to tier 6!");
					} else sender.sendMessage(ChatColor.RED + "Tier 6 doesn't exist!");
				}

				// Set them to tier seven
				else if(tierNum == 7) {
					if(Skills.useTierTwo) {
						plugin.tierPerms.get(targetUUID).setPermission("skills.tier.seven", true);

						if(target.hasPermission("skills.tier.one"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.one");
						if(target.hasPermission("skills.tier.two"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.two");
						if(target.hasPermission("skills.tier.three"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.three");
						if(target.hasPermission("skills.tier.four"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.four");
						if(target.hasPermission("skills.tier.five"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.five");
						if(target.hasPermission("skills.tier.six"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.six");
						if(target.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.eight");
						if(target.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.nine");
						if(target.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.ten");

						if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
						if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
						if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
						
						plugin.savePointsFile(targetUUID); // Save the point files
						
						sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + 
								target.getName() + ChatColor.GREEN + "'s tier to 7!");

						target.sendMessage(ChatColor.GREEN + "An admin has set you to tier 7!");
					} else sender.sendMessage(ChatColor.RED + "Tier 7 doesn't exist!");
				}

				// Set them to tier eight
				else if(tierNum == 8) {
					if(Skills.useTierTwo) {
						plugin.tierPerms.get(targetUUID).setPermission("skills.tier.eight", true);

						if(target.hasPermission("skills.tier.one"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.one");
						if(target.hasPermission("skills.tier.two"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.two");
						if(target.hasPermission("skills.tier.three"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.three");
						if(target.hasPermission("skills.tier.four"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.four");
						if(target.hasPermission("skills.tier.five"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.five");
						if(target.hasPermission("skills.tier.six"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.six");
						if(target.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.seven");
						if(target.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.nine");
						if(target.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.ten");

						if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
						if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
						if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
						
						plugin.savePointsFile(targetUUID); // Save the point files
						
						sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + 
								target.getName() + ChatColor.GREEN + "'s tier to 8!");

						target.sendMessage(ChatColor.GREEN + "An admin has set you to tier 8!");
					} else sender.sendMessage(ChatColor.RED + "Tier 8 doesn't exist!");
				}

				// Set them to tier nine
				else if(tierNum == 9) {
					if(Skills.useTierTwo) {
						plugin.tierPerms.get(targetUUID).setPermission("skills.tier.nine", true);

						if(target.hasPermission("skills.tier.one"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.one");
						if(target.hasPermission("skills.tier.two"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.two");
						if(target.hasPermission("skills.tier.three"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.three");
						if(target.hasPermission("skills.tier.four"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.four");
						if(target.hasPermission("skills.tier.five"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.five");
						if(target.hasPermission("skills.tier.six"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.six");
						if(target.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.seven");
						if(target.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.eight");
						if(target.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.ten");

						if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
						if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
						if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
						
						plugin.savePointsFile(targetUUID); // Save the point files
						
						sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + 
								target.getName() + ChatColor.GREEN + "'s tier to 9!");

						target.sendMessage(ChatColor.GREEN + "An admin has set you to tier 9!");
					} else sender.sendMessage(ChatColor.RED + "Tier 9 doesn't exist!");
				}

				// Set them to tier ten
				else if(tierNum == 10) {
					if(Skills.useTierTwo) {
						plugin.tierPerms.get(targetUUID).setPermission("skills.tier.ten", true);

						if(target.hasPermission("skills.tier.one"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.one");
						if(target.hasPermission("skills.tier.two"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.two");
						if(target.hasPermission("skills.tier.three"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.three");
						if(target.hasPermission("skills.tier.four"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.four");
						if(target.hasPermission("skills.tier.five"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.five");
						if(target.hasPermission("skills.tier.six"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.six");
						if(target.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.seven");
						if(target.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.eight");
						if(target.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.nine");

						if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
						if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
						if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
						
						sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + 
								target.getName() + ChatColor.GREEN + "'s tier to 10!");

						target.sendMessage(ChatColor.GREEN + "An admin has set you to tier 10!");
					} else sender.sendMessage(ChatColor.RED + "Tier 10 doesn't exist!");
				}
			}
			else if(tierNum == 0) {
				if(target.hasPermission("skills.tier.one"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.one");
				if(target.hasPermission("skills.tier.two"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.two");
				if(target.hasPermission("skills.tier.three"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.three");
				if(target.hasPermission("skills.tier.four"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.four");
				if(target.hasPermission("skills.tier.five"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.five");
				if(target.hasPermission("skills.tier.six"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.six");
				if(target.hasPermission("skills.tier.seven"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.seven");
				if(target.hasPermission("skills.tier.eight"))   plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.eight");
				if(target.hasPermission("skills.tier.nine"))    plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.nine");
				if(target.hasPermission("skills.tier.ten"))     plugin.tierPerms.get(targetUUID).unsetPermission("skills.tier.ten");
				
				if(Skills.useMaxHpSkill) plugin.playerSkills.get(targetUUID).increaseHealth(target); // Set the max health
				if(Skills.useMaxManaSkill) plugin.playerSkills.get(targetUUID).setMaxMana(target); // Set the max mana
				if(Skills.useManaRegenSkill) plugin.playerSkills.get(targetUUID).setManaRegen(target); // Set the mana regen
				
				plugin.savePointsFile(targetUUID); // Save the point files
				
				sender.sendMessage(ChatColor.GREEN + "You have set " + ChatColor.GOLD + 
						target.getName() + ChatColor.GREEN + "'s tier to 0!");

				target.sendMessage(ChatColor.GREEN + "An admin has set you to tier 0!");
			}
			else sender.sendMessage(ChatColor.RED + "Tier has to be between 0 and 10");
		} catch(Exception e) { sender.sendMessage(ChatColor.RED + tier + " is not a valid number!"); }
	}
}

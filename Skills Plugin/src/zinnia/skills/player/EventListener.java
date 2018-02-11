package zinnia.skills.player;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.permissions.PermissionAttachment;

import com.earth2me.essentials.api.Economy;

import zinnia.skills.main.Skills;
import zinnia.skills.utils.TierUtils;

public class EventListener implements Listener {

	Skills plugin; // Reference to the plugin class like always

	public EventListener(Skills plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		onJoinData(e.getPlayer().getUniqueId()); // Set the join data

		if(Skills.useMaxHpSkill) plugin.playerSkills.get(e.getPlayer().getUniqueId()).increaseHealth(e.getPlayer()); // Set the max health
		if(Skills.useMaxManaSkill) plugin.playerSkills.get(e.getPlayer().getUniqueId()).setMaxMana(e.getPlayer()); // Set the max mana
		if(Skills.useManaRegenSkill) plugin.playerSkills.get(e.getPlayer().getUniqueId()).setManaRegen(e.getPlayer()); // Set the mana regen

		// Set the player's level based off of their last level
		if(plugin.saveLevel) e.getPlayer().setLevel(plugin.playerSkills.get(e.getPlayer().getUniqueId()).lastLevel);

		try { // Try to attach permissions as a player joins
			PermissionAttachment attachment = e.getPlayer().addAttachment(plugin);
			plugin.tierPerms.put(e.getPlayer().getUniqueId(), attachment);
		} catch(Exception ex) { }
	}

	@EventHandler
	public void onLevelUp(PlayerLevelChangeEvent e) {
		if(e.getNewLevel() > plugin.playerSkills.get(e.getPlayer().getUniqueId()).lastLevel){
			// Check if the level moves up so we don't give points for deleveling
			if(e.getNewLevel() > e.getOldLevel()) {
				int pointIncrease = pointIncrease(); // Get the point increase,

				plugin.playerSkills.get(e.getPlayer().getUniqueId()).lastLevel = e.getNewLevel(); // Update their newest level

				// Increase the points
				plugin.playerSkills.get(e.getPlayer().getUniqueId()).points += pointIncrease;
				plugin.savePointsFile(e.getPlayer().getUniqueId()); // Save the file
			}
		} 

		// Update the tier
		if(e.getNewLevel() > e.getOldLevel()) TierUtils.increaseTierOnLevel(plugin, e.getPlayer());
		else if (e.getNewLevel() < e.getOldLevel()) TierUtils.decreaseTierOnLevel(plugin, e.getPlayer());

		if(plugin.saveLevel) {
			e.getPlayer().setLevel(plugin.playerSkills.get(e.getPlayer().getUniqueId()).lastLevel); // Set the player's level to their last level
		}
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) { // Check if the entity getting damaged is a player
			if(plugin.playerSkills.get(e.getEntity().getUniqueId()).Dodge()) { // Check if they dodged
				e.getEntity().sendMessage(ChatColor.GREEN + "You evaded the attack!"); // Send a message to them telling them they dodged

				if(e.getDamager() instanceof Player) // Check if the damager is a player
					e.getDamager().sendMessage( // Send them a message saying their foe evaded their attack
							ChatColor.RED + e.getEntity().getCustomName() + " evaded your attack!");

				e.setCancelled(true); // Cancel the event
			}
		}

		// Check if the damager is a player
		if(e.getDamager() instanceof Player) { 

			double damage = 2.5; // Default damage

			// Get the damage amount from the game's default damage and add the skill's damage on it and take away the defense from the enemy
			if(e.getEntity() instanceof Player) {
				damage = e.getDamage() + plugin.playerSkills.get(e.getDamager().getUniqueId()).getDmg() -
						plugin.playerSkills.get(e.getEntity().getUniqueId()).getDefense();
			}
			else {
				damage = e.getDamage() + plugin.playerSkills.get(e.getDamager().getUniqueId()).getDmg();
			}
			
			// Make sure damage doesn't go to zero or less than zero
			if(damage <= 0) damage = 2.5;

			// Check if they crit or not
			if(plugin.playerSkills.get(e.getDamager().getUniqueId()).Crit()) {
				e.getEntity().sendMessage(ChatColor.GREEN + "You got a critical hit!"); // Send a message saying they crit

				if(e.getEntity() instanceof Player) // Send the foe a message saying they got hit with a crit
					e.getEntity().sendMessage(ChatColor.RED + e.getDamager().getCustomName() + " Got a critical hit on you!");

				e.setDamage(damage * 2); // Multiply the attack damage by two
			}

			e.getDamager().sendMessage(ChatColor.RED + "Damage dealt is " + ChatColor.BLUE + damage);
			e.setDamage(damage); // Set the default damage to our damage variable
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEnchant(EnchantItemEvent e) {
		if(Skills.enchantCostMoney)
			try { Economy.subtract(e.getEnchanter().getName(), Skills.enchantCost); } catch (Exception ex) {}
	}

	// Method for getting the point increase
	private int pointIncrease() {
		double randNum = Math.random() * 100; // Multiply the generated number by 100, to get it out of decimals
		if(randNum <= 10) return 3; // 10% to give 3 skill points
		else if(randNum <= 20 && randNum > 10) return 2; // 20% to give 2 skill points
		else return 1; // 70% to give 1 skill point
	}

	// Method to set check player if player in the hash map and set data when they join
	private void onJoinData(UUID playerUUID) {
		if(!plugin.playerSkills.containsKey(playerUUID)) {  
			plugin.playerSkills.put(playerUUID, new SkillPoints());
			plugin.playerSkills.get(playerUUID).points = 0;
			plugin.playerSkills.get(playerUUID).lastLevel = 0;
			plugin.playerSkills.get(playerUUID).healthPoints = 0;
			plugin.playerSkills.get(playerUUID).dmgPoints = 0;
			plugin.playerSkills.get(playerUUID).dodgePoints = 0;
			plugin.playerSkills.get(playerUUID).critPoints = 0;
			plugin.playerSkills.get(playerUUID).manaPoints = 0;
			plugin.playerSkills.get(playerUUID).manaRegenPoints = 0;
		}
		else {
			plugin.playerSkills.get(playerUUID).points = 0;
			plugin.playerSkills.get(playerUUID).lastLevel = 0;
			plugin.playerSkills.get(playerUUID).healthPoints = 0;
			plugin.playerSkills.get(playerUUID).dmgPoints = 0;
			plugin.playerSkills.get(playerUUID).dodgePoints = 0;
			plugin.playerSkills.get(playerUUID).critPoints = 0;
			plugin.playerSkills.get(playerUUID).manaPoints = 0;
			plugin.playerSkills.get(playerUUID).manaRegenPoints = 0;
			plugin.loadPointsFile(playerUUID);
		}
	}
}

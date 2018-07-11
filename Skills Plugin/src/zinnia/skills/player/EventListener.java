package zinnia.skills.player;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
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
		try { // Try to attach permissions as a player joins
			PermissionAttachment attachment = e.getPlayer().addAttachment(plugin);
			plugin.tierPerms.put(e.getPlayer().getUniqueId(), attachment);
		} catch(Exception ex) { }
		
		onJoinData(e.getPlayer().getUniqueId()); // Set the join data

		if(Skills.useMaxHpSkill) plugin.playerSkills.get(e.getPlayer().getUniqueId()).increaseHealth(e.getPlayer()); // Set the max health
		if(Skills.useMaxManaSkill) plugin.playerSkills.get(e.getPlayer().getUniqueId()).setMaxMana(e.getPlayer()); // Set the max mana
		if(Skills.useManaRegenSkill) plugin.playerSkills.get(e.getPlayer().getUniqueId()).setManaRegen(e.getPlayer()); // Set the mana regen

		// Set the player's level based off of their last level
		if(plugin.saveLevel) e.getPlayer().setLevel(plugin.playerSkills.get(e.getPlayer().getUniqueId()).lastLevel);
	}

	@EventHandler
	public void onLevelUp(PlayerLevelChangeEvent e) {
		if(e.getNewLevel() > plugin.playerSkills.get(e.getPlayer().getUniqueId()).lastLevel){
			// Check if the level moves up so we don't give points for deleveling
			if(e.getNewLevel() > e.getOldLevel()) {
				for(int i = 0; i < e.getNewLevel() - e.getOldLevel(); i++) { // Make sure we get points for each level so they don't miss out on points
					int pointIncrease = pointIncrease(); // Get the point increase,
					plugin.playerSkills.get(e.getPlayer().getUniqueId()).lastLevel = e.getNewLevel(); // Update their newest level

					// Increase the points
					plugin.playerSkills.get(e.getPlayer().getUniqueId()).points += pointIncrease;
				}
				plugin.savePointsFile(e.getPlayer().getUniqueId()); // Save the file
			}
		} 

		// Update the tier
		if(e.getNewLevel() > e.getOldLevel() && Skills.tiersIncreaseOnLevel) TierUtils.increaseTierOnLevel(plugin, e.getPlayer());
		else if (e.getNewLevel() < e.getOldLevel() && Skills.tiersIncreaseOnLevel) TierUtils.decreaseTierOnLevel(plugin, e.getPlayer());

		if(plugin.saveLevel) {
			e.getPlayer().setLevel(plugin.playerSkills.get(e.getPlayer().getUniqueId()).lastLevel); // Set the player's level to their last level
		}
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) { // Check if the entity getting damaged is a player
			if(plugin.playerSkills.get(e.getEntity().getUniqueId()).Dodge()) { // Check if they dodged
				if(Skills._SendDodgeMessage)
					e.getEntity().sendMessage(ChatColor.GREEN + "You evaded the attack!"); // Send a message to them telling them they dodged

				if(e.getDamager() instanceof Player) // Check if the damager is a player
				{
					if(Skills._SendDodgeMessage)
						e.getDamager().sendMessage( // Send them a message saying their foe evaded their attack
								ChatColor.RED + e.getEntity().getName() + " evaded your attack!");
				}
				e.setCancelled(true); // Cancel the event
				return;
			}
		}
		
		// Check if the damager is a player
		if(e.getDamager() instanceof Player) { 
			Player damager = (Player)e.getDamager();
			double damage = 2.5; // Default damage
			
			// Get the damage amount from the game's default damage and add the skill's damage on it and take away the defense from the enemy
			if(e.getEntity() instanceof Player) {
				damage = e.getDamage() + plugin.playerSkills.get(damager.getUniqueId()).getDmg() - plugin.playerSkills.get(e.getEntity().getUniqueId()).getDefense();
			}
			else {
				damage = e.getDamage() + plugin.playerSkills.get(damager.getUniqueId()).getDmg() - mobDefense(e.getEntity().getType());
			}

			// Make sure damage doesn't go to zero or less than zero
			if(damage <= 0) damage = e.getDamage() + 2.5;

			// Check if they crit or not
			if(plugin.playerSkills.get(e.getDamager().getUniqueId()).Crit()) {
				if(Skills._SendCritMessage)
					e.getEntity().sendMessage(ChatColor.GREEN + "You got a critical hit!"); // Send a message saying they crit

				if(e.getEntity() instanceof Player) // Send the foe a message saying they got hit with a crit
					if(Skills._SendCritMessage)
						e.getEntity().sendMessage(ChatColor.RED + e.getDamager().getName() + " Got a critical hit on you!");

				e.setDamage(damage * 2); // Multiply the attack damage by two
			}

			e.setDamage(damage); // Set the default damage to our damage variable
		}
		// Mobs
		if(e.getDamager() instanceof Monster) {
			double damage = 2.5;
			
			if(e.getEntity() instanceof Player) {
				damage = e.getDamage() + mobDamage(e.getDamager().getType()) - plugin.playerSkills.get(e.getEntity().getUniqueId()).getDefense();
			}
			else {
				damage = e.getDamage() + mobDamage(e.getDamager().getType()) - mobDefense(e.getEntity().getType());
			}
			
			if(damage <= 0) damage = 2.5;
			
			e.setDamage(damage);
		}
	}

	// This event is used to make sure the mana regen and everything stays after they've died
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		plugin.playerSkills.get(e.getPlayer().getUniqueId()).setMaxMana(e.getPlayer());
		plugin.playerSkills.get(e.getPlayer().getUniqueId()).setManaRegen(e.getPlayer());
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
			plugin.playerSkills.get(playerUUID).tierLevel = 0;
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
			plugin.playerSkills.get(playerUUID).tierLevel = 0;
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

	
	// Method to get mob damage
	private double mobDamage(EntityType entity) {
		try {
			if(entity == EntityType.ZOMBIE)            return Skills.zombieDamage;
			if(entity == EntityType.ZOMBIE_VILLAGER)   return Skills.zombieVillagerDamage;
			if(entity == EntityType.GIANT)             return Skills.giantDamage;
			if(entity == EntityType.SKELETON)          return Skills.skeletonDamage;
			if(entity == EntityType.SPIDER)            return Skills.spiderDamage;
			if(entity == EntityType.CAVE_SPIDER)       return Skills.caveSpiderDamage;
			if(entity == EntityType.ENDERMAN)          return Skills.endermanDamage;
			if(entity == EntityType.WOLF)              return Skills.wolfDamage;
			if(entity == EntityType.BLAZE)             return Skills.blazeDamage;
			if(entity == EntityType.SLIME)             return Skills.slimeDamage;
			if(entity == EntityType.MAGMA_CUBE)        return Skills.magmaCubeDamage;
			if(entity == EntityType.WITHER_SKELETON)   return Skills.witherSkeletonDamage;
			if(entity == EntityType.GUARDIAN)          return Skills.guardianDamage;
			if(entity == EntityType.ELDER_GUARDIAN)    return Skills.elderGuardianDamage;
			if(entity == EntityType.POLAR_BEAR)        return Skills.polarBearDamage;
			if(entity == EntityType.VEX)               return Skills.vexDamage;
			return 0;
		} catch(Exception e) {
			return 0;
		}
	}
	
	// Method to get the defense of different mobs
	private double mobDefense(EntityType entity) {
		try {
			if(entity == EntityType.OCELOT)                                               return Skills.ocelotDefense;
			if(entity == EntityType.HORSE)        										  return Skills.horseDefense;
			if(entity == EntityType.RABBIT)                                               return Skills.rabbitDefense;
			if(entity == EntityType.SHEEP)                                                return Skills.sheepDefense;
			if(entity == EntityType.PIG)                                                  return Skills.pigDefense;
			if(entity == EntityType.CHICKEN)                                              return Skills.chickenDefense;
			if(entity == EntityType.COW)                                                  return Skills.cowDefense;
			if(entity == EntityType.MUSHROOM_COW)                                         return Skills.mooshroomDefense;
			if(entity == EntityType.SQUID)                                                return Skills.squidDefense;
			if(entity == EntityType.BAT)                                                  return Skills.batDefense;
			if(entity == EntityType.VILLAGER)                                             return Skills.villagerDefense;
			if(entity == EntityType.ZOMBIE)                                               return Skills.zombieDamage;
			if(entity == EntityType.ZOMBIE_VILLAGER)                                      return Skills.zombieVillagerDamage;
			if(entity == EntityType.GIANT)                                                return Skills.giantDamage;
			if(entity == EntityType.PIG_ZOMBIE)                                           return Skills.zombiePigmanDefense;
			if(entity == EntityType.SKELETON)                                             return Skills.skeletonDamage;
			if(entity == EntityType.SPIDER)                                               return Skills.spiderDamage;
			if(entity == EntityType.CAVE_SPIDER)                                          return Skills.caveSpiderDamage;
			if(entity == EntityType.CREEPER)                                              return Skills.creeperDefense;
			if(entity == EntityType.ENDERMAN)                                             return Skills.endermanDamage;
			if(entity == EntityType.WOLF)                                                 return Skills.wolfDamage;
			if(entity == EntityType.WITCH)                                                return Skills.witherDefense;
			if(entity == EntityType.BLAZE)                                                return Skills.blazeDamage;
			if(entity == EntityType.SLIME)                                                return Skills.slimeDamage;
			if(entity == EntityType.MAGMA_CUBE)                                           return Skills.magmaCubeDamage;
			if(entity == EntityType.SILVERFISH)                                           return Skills.silverFishDamage;
			if(entity == EntityType.WITHER_SKELETON)                                      return Skills.witherSkeletonDamage;
			if(entity == EntityType.WITHER)                                               return Skills.witherDefense;
			if(entity == EntityType.ENDER_DRAGON)                                         return Skills.enderDragonDefense;
			if(entity == EntityType.GUARDIAN)                                             return Skills.guardianDamage;
			if(entity == EntityType.ELDER_GUARDIAN)                                       return Skills.elderGuardianDamage;
			if(entity == EntityType.POLAR_BEAR)                                           return Skills.polarBearDamage;
			if(entity == EntityType.SKELETON_HORSE)                                       return Skills.horseDefense;
			if(entity == EntityType.SHULKER)                                              return Skills.shulkerDefense;
			if(entity == EntityType.LLAMA)                                                return Skills.llamaDefense;
			if(entity == EntityType.ENDERMITE)                                            return Skills.endermiteDefense;
			if(entity == EntityType.PARROT)                                               return Skills.parrotDefense;
			if(entity == EntityType.VEX)                                                  return Skills.vexDamage;
			if(entity == EntityType.STRAY)                                                return Skills.strayDefense;
			if(entity == EntityType.EVOKER)                                               return Skills.evokerDefense;
			if(entity == EntityType.VINDICATOR)                                           return Skills.vindicatorDefense;
			if(entity == EntityType.ILLUSIONER)                                           return Skills.illusionerDefense;
			return 0;
		} catch(Exception e) {
			return 0;
		}
	}
}

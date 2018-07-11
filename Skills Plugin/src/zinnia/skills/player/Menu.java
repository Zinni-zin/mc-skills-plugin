package zinnia.skills.player;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import zinnia.skills.main.Skills;
import zinnia.skills.utils.Colours;
import zinnia.skills.utils.TierUtils;

public class Menu implements Listener {

	private Inventory inv;
	private ItemStack hp, dmg, defense, dodge, crit, mana, manaRegen;

	Skills plugin;

	public Menu(Skills plugin) {
		this.plugin = plugin;

		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}

	public void OpenInventory(Player player) {
		// Set the inventory
		inv = Bukkit.getServer().createInventory(null, Skills.menuSize, 
				"Skill Points: " + plugin.playerSkills.get(player.getUniqueId()).points);

		setItemStacks(player); // Create the item and it's lore

		if(Skills.useMaxHpSkill)       inv.setItem(Skills.hpPosition,        hp); // Set the hp placement
		if(Skills.useDefenseSkill)     inv.setItem(Skills.defensePosition,   defense); // Set the defense placement
		if(Skills.useDmgSkill)         inv.setItem(Skills.dmgPosition,       dmg); // Set the damage placement

		if(Skills.useDodgeSkill)       inv.setItem(Skills.dodgePosition,     dodge); // Set the dodge placement
		if(Skills.useCritSkill)        inv.setItem(Skills.critPosition,      crit); // Set the critical placement

		if(Skills.useMaxManaSkill)     inv.setItem(Skills.manaPosition,      mana); // Set the mana placement 
		if(Skills.useManaRegenSkill)   inv.setItem(Skills.manaRegenPosition, manaRegen); // Set the mana regen placement 

		player.openInventory(inv); // Open the inventory
	}

	// Function to create the item
	private ItemStack createItem(ItemStack item, String name, String displayPoints, String pointIncrease) {
		//if(enabled) { // If the skill is enabled do the customization
		
		ItemStack i = item; // Set the item
		ItemMeta im = i.getItemMeta(); // Get the item's meta data
		im.setDisplayName(name); // Set the item's name
		im.setLore(Arrays.asList(Colours.WHITE + "Raises " + name, displayPoints, pointIncrease)); // Set the item's lore
		i.setItemMeta(im); // Set the item's meta data to the meta data created and altered
		return i; // return the item we created
	}

	private void setItemStacks(Player player) {
		// Hp item
		hp = createItem(Skills.hpItem, ChatColor.RED + "Health", Colours.RED + 
				plugin.playerSkills.get(player.getUniqueId()).healthPoints + 
				Colours.DARK_RED + "/" + Colours.RED + Skills.maxHpPoints, 
				Colours.RED + "Hp Bonus: " + plugin.playerSkills.get(player.getUniqueId()).getHealth(player));

		// Damage item
		dmg = createItem(Skills.damageItem, ChatColor.YELLOW + "Damage", Colours.YELLOW +
				plugin.playerSkills.get(player.getUniqueId()).dmgPoints + 
				Colours.GOLD + "/" + Colours.YELLOW + Skills.maxDmgPoints, 
				Colours.YELLOW + "Damage Bonus: " + plugin.playerSkills.get(player.getUniqueId()).getDmg());

		// Defense item
		defense = createItem(Skills.defenseItem, ChatColor.GRAY + "Defense", Colours.GRAY + 
				plugin.playerSkills.get(player.getUniqueId()).defensePoints + 
				Colours.DARK_GRAY + "/" + Colours.GRAY + Skills.maxDefensePoints, 
				Colours.GRAY + "Defense Bonus: " + plugin.playerSkills.get(player.getUniqueId()).getDefense());

		// Dodge item
		dodge = createItem(Skills.dodgeItem, ChatColor.AQUA + "Dodge Chance", 
				Colours.AQUA + plugin.playerSkills.get(player.getUniqueId()).dodgePoints + 
				Colours.DARK_AQUA + "/" + Colours.AQUA + Skills.maxDodgePoints, 
				Colours.AQUA + "Doge Chance: " + plugin.playerSkills.get(player.getUniqueId()).getDodgeChange() + "%");

		// Crit item
		crit = createItem(Skills.critItem, ChatColor.DARK_GREEN + "Crit Chance", 
				Colours.DARK_GREEN + plugin.playerSkills.get(player.getUniqueId()).critPoints + 
				Colours.GREEN + "/" + Colours.DARK_GREEN + Skills.maxCritPoints,
				ChatColor.DARK_GREEN + "Crit Chance: " + plugin.playerSkills.get(player.getUniqueId()).getCritChance() + "%");

		// Mana item
		mana = createItem(Skills.manaItem, Colours.INDIGO + "Mana",
				Colours.INDIGO + plugin.playerSkills.get(player.getUniqueId()).manaPoints +
				Colours.DARK_BLUE + "/" + Colours.INDIGO + Skills.maxManaPoints, Colours.INDIGO  + "Mana Bonus: " + plugin.playerSkills.get(player.getUniqueId()).getMana());

		// Mana Regen item
		manaRegen = createItem(Skills.manaRegenItem, Colours.DARK_PURPLE + "Mana Regen",
				Colours.DARK_PURPLE + plugin.playerSkills.get(player.getUniqueId()).manaRegenPoints +
				Colours.PINK + "/" + Colours.DARK_PURPLE + Skills.maxManaRegenPoints, 
				Colours.DARK_PURPLE + "Mana Regen Bonus: " + plugin.playerSkills.get(player.getUniqueId()).getManaRegen());
	}


	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		try {
			if(e.getInventory().getName().equalsIgnoreCase(inv.getName())) { // Check menu/inventory we're in
				if(e.getCurrentItem().getItemMeta() == null) return;  // If the meta data is null return nothing

				Player player = (Player)e.getWhoClicked(); // Set the player to whoever clicked
				UUID playerUUID = player.getUniqueId(); // Set the player's UUID for ease

				// Check if the item clicked is health
				if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Health")) {
					// Check if our health points are less than max health points
					if(plugin.playerSkills.get(playerUUID).healthPoints < Skills.maxHpPoints) {
						// Check if player has one or more skill points
						if(plugin.playerSkills.get(playerUUID).points > 0) { 
							plugin.playerSkills.get(playerUUID).healthPoints++; // Increase health points
							plugin.playerSkills.get(playerUUID).points--; // Take away skill points
							plugin.playerSkills.get(playerUUID).increaseHealth(player); // Set health
							
							if(Skills.skillTreeReset && plugin.playerSkills.get(playerUUID).healthPoints >= Skills.maxHpPoints) skillTreeReset(player);
							
							plugin.savePointsFile(playerUUID); // Save the file
							e.setCancelled(true); // Cancel the event
							OpenInventory(player); // Reopen it(refreshing)
						} else player.sendMessage(ChatColor.RED + "You don't have enough skill points");
					}
					e.setCancelled(true); // Cancel the event to prevent players from taking the item
				}

				// Repeat like health but with slight variation, Damage
				if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Damage")) {
					if(plugin.playerSkills.get(playerUUID).dmgPoints < Skills.maxDmgPoints) {
						if(plugin.playerSkills.get(playerUUID).points > 0) {
							plugin.playerSkills.get(playerUUID).dmgPoints++; 
							plugin.playerSkills.get(playerUUID).points--;
							
							if(Skills.skillTreeReset && (plugin.playerSkills.get(playerUUID).dmgPoints >= Skills.maxDmgPoints)) skillTreeReset(player);
							
							plugin.savePointsFile(playerUUID);
							e.setCancelled(true);
							OpenInventory(player);

						} else player.sendMessage(ChatColor.RED + "You don't have enough skill points");
					}
					e.setCancelled(true);
				}

				// Repeat like health but with slight variation, Health Regen
				if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Defense")) {
					if(plugin.playerSkills.get(playerUUID).defensePoints < Skills.maxDefensePoints) {
						if(plugin.playerSkills.get(playerUUID).points > 0) {
							plugin.playerSkills.get(playerUUID).defensePoints++;
							plugin.playerSkills.get(playerUUID).points--;
							
							if(Skills.skillTreeReset && (plugin.playerSkills.get(playerUUID).defensePoints >= Skills.maxDefensePoints)) skillTreeReset(player);
							
							plugin.savePointsFile(playerUUID);
							e.setCancelled(true);
							OpenInventory(player);

						} else player.sendMessage(ChatColor.RED + "You don't have enough skill points");
					}
					e.setCancelled(true);
				}

				// Repeat like health but with slight variation, Dodge Chance
				if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Dodge Chance")) {
					if(plugin.playerSkills.get(playerUUID).dodgePoints < Skills.maxDodgePoints) {
						if(plugin.playerSkills.get(playerUUID).points > 0) {
							plugin.playerSkills.get(playerUUID).dodgePoints++;
							plugin.playerSkills.get(playerUUID).points--;
							
							if(Skills.skillTreeReset && (plugin.playerSkills.get(playerUUID).dodgePoints >= Skills.maxDodgePoints)) skillTreeReset(player);
							
							plugin.savePointsFile(playerUUID);
							e.setCancelled(true);
							OpenInventory(player);

						} else player.sendMessage(ChatColor.RED + "You don't have enough skill points");
					}
					e.setCancelled(true);
				}

				// Repeat like health but with slight variation, Critical Chance
				if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Crit Chance")) {
					if(plugin.playerSkills.get(playerUUID).critPoints < Skills.maxCritPoints) {
						if(plugin.playerSkills.get(playerUUID).points > 0) {
							plugin.playerSkills.get(playerUUID).critPoints++;
							plugin.playerSkills.get(playerUUID).points--;
							
							if(Skills.skillTreeReset && (plugin.playerSkills.get(playerUUID).critPoints >= Skills.maxCritPoints)) skillTreeReset(player);
							
							plugin.savePointsFile(playerUUID);
							e.setCancelled(true);
							OpenInventory(player);

						} else player.sendMessage(ChatColor.RED + "You don't have enough skill points");
					}
					e.setCancelled(true);
				}

				// Repeat like health but with slight variation, Mana
				if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Mana") &&
						!e.getCurrentItem().getItemMeta().getDisplayName().contains("Regen")) {
					if(plugin.playerSkills.get(playerUUID).manaPoints < Skills.maxManaPoints) {
						if(plugin.playerSkills.get(playerUUID).points > 0) {
							plugin.playerSkills.get(playerUUID).manaPoints++;
							plugin.playerSkills.get(playerUUID).points--;

							// Set the max mana
							plugin.playerSkills.get(playerUUID).setMaxMana(player);

							if(Skills.skillTreeReset && (plugin.playerSkills.get(playerUUID).manaPoints >= Skills.maxManaPoints)) skillTreeReset(player);
							
							plugin.savePointsFile(playerUUID);

							//player.closeInventory();
							e.setCancelled(true);
							OpenInventory(player);

						} else player.sendMessage(ChatColor.RED + "You don't have enough skill points");
					}
					e.setCancelled(true);
				}

				// Repeat like health but with slight variation, Mana Regen
				if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Mana Regen")) {
					if(plugin.playerSkills.get(playerUUID).manaRegenPoints < Skills.maxManaRegenPoints) {
						if(plugin.playerSkills.get(playerUUID).points > 0) {
							plugin.playerSkills.get(playerUUID).manaRegenPoints++;
							plugin.playerSkills.get(playerUUID).points--;
							// Set the mana regen
							plugin.playerSkills.get(playerUUID).setManaRegen(player);
							
							if(Skills.skillTreeReset && (plugin.playerSkills.get(playerUUID).manaRegenPoints >= Skills.maxManaPoints)) skillTreeReset(player);
							
							plugin.savePointsFile(playerUUID);


							e.setCancelled(true);
							OpenInventory(player);

						} else player.sendMessage(ChatColor.RED + "You don't have enough skill points");
					}
					e.setCancelled(true);
				}
				
				// If allowed via config make it so tier auto levels
				if(Skills.tierResetAuto) {
					if(checkAllStatMaxOut(plugin, player.getUniqueId())) {
						AdminCommands.resetPointsForTier(plugin, player);
						TierUtils.increaseTierOnLevel(plugin, player);
					}
				}
			}
		} catch (Exception ex) { }
	}
	
	public static boolean checkAllStatMaxOut(Skills plugin, UUID playerUUID) {
		return (plugin.playerSkills.get(playerUUID).healthPoints >= Skills.maxHpPoints && 
				plugin.playerSkills.get(playerUUID).dmgPoints >= Skills.maxDmgPoints && plugin.playerSkills.get(playerUUID).defensePoints >= Skills.maxDefensePoints && 
				plugin.playerSkills.get(playerUUID).dodgePoints >= Skills.maxDodgePoints && plugin.playerSkills.get(playerUUID).manaPoints >= Skills.maxManaPoints && 
				plugin.playerSkills.get(playerUUID).manaRegenPoints >= Skills.maxManaPoints && plugin.playerSkills.get(playerUUID).critPoints >= Skills.maxCritPoints)?true:false;
	}
	
	// Method to reset skill points that aren't maxed out and reset lvl
		private void skillTreeReset(Player player) {
			UUID playerUUID = player.getUniqueId();
			plugin.playerSkills.get(playerUUID).points = 0; // Set skill points to zero
			plugin.playerSkills.get(playerUUID).lastLevel = 0; // Set the last level to zero
			if(plugin.playerSkills.get(playerUUID).healthPoints < Skills.maxHpPoints) plugin.playerSkills.get(playerUUID).healthPoints = 0; // Set health points to zero
			if(plugin.playerSkills.get(playerUUID).dmgPoints < Skills.maxDmgPoints)plugin.playerSkills.get(playerUUID).dmgPoints = 0; // Set damage points to zero
			if(plugin.playerSkills.get(playerUUID).defensePoints < Skills.maxDefensePoints)plugin.playerSkills.get(playerUUID).defensePoints = 0; // Set the defense points to zero
			if(plugin.playerSkills.get(playerUUID).dodgePoints < Skills.maxDodgePoints)plugin.playerSkills.get(playerUUID).dodgePoints = 0; // Set dodge points to zero
			if(plugin.playerSkills.get(playerUUID).critPoints < Skills.maxCritPoints)plugin.playerSkills.get(playerUUID).critPoints = 0; // Set critical points to zero
			if(plugin.playerSkills.get(playerUUID).manaPoints < Skills.maxManaPoints)plugin.playerSkills.get(playerUUID).manaPoints = 0; // Set mana points to zero
			if(plugin.playerSkills.get(playerUUID).manaRegenPoints < Skills.maxManaRegenPoints)plugin.playerSkills.get(playerUUID).manaRegenPoints = 0; // Set mana regen points to zero
			
			plugin.playerSkills.get(playerUUID).increaseHealth(player); // Change the health
			plugin.playerSkills.get(playerUUID).setMaxMana(player); // Set the max mana
			plugin.playerSkills.get(playerUUID).setManaRegen(player); // Set the mana regen
			
			player.setLevel(0); // Set the level to zero
			
			player.sendMessage(ChatColor.GREEN + "You've maxed out a skill tree!");
		}
}

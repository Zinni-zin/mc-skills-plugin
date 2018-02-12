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
	private ItemStack createItem(ItemStack item, String name, String displayPoints/*, boolean enabled*/) {
		//if(enabled) { // If the skill is enabled do the customization
		
		ItemStack i = item; // Set the item
		ItemMeta im = i.getItemMeta(); // Get the item's meta data
		im.setDisplayName(name); // Set the item's name
		im.setLore(Arrays.asList(Colours.WHITE + "Raises " + name, displayPoints)); // Set the item's lore
		i.setItemMeta(im); // Set the item's meta data to the meta data created and altered
		return i; // return the item we created

		/*} else { // Otherwise set everything to make it obvious it's disabled
			ItemStack i = new Wool(DyeColor.BLACK).toItemStack(1); // Set the item to be black wool
			ItemMeta im = i.getItemMeta(); // Get the item's meta data
			im.setDisplayName("Disabled"); // Set the display name to disable
			im.setLore(Arrays.asList(Colours.DARK_GRAY + "This Skill is disabled!")); // Set the lore to say this skill is disabled
			i.setItemMeta(im); // Set the item meta data to the data we created and altered
			return i; // Return the item stack
		}*/
	}

	private void setItemStacks(Player player) {
		// Hp item
		hp = createItem(Skills.hpItem, ChatColor.RED + "Health", Colours.RED + 
				plugin.playerSkills.get(player.getUniqueId()).healthPoints + 
				Colours.DARK_RED + "/" + Colours.RED + Skills.maxHpPoints/*, Skills.useMaxHpSkill*/);

		// Damage item
		dmg = createItem(Skills.damageItem, ChatColor.YELLOW + "Damage", Colours.YELLOW +
				plugin.playerSkills.get(player.getUniqueId()).dmgPoints + 
				Colours.GOLD + "/" + Colours.YELLOW + Skills.maxDmgPoints/*, Skills.useDmgSkill*/);

		// Defense item
		defense = createItem(Skills.defenseItem, ChatColor.GRAY + "Defense", Colours.GRAY + 
				plugin.playerSkills.get(player.getUniqueId()).defensePoints + 
				Colours.DARK_GRAY + "/" + Colours.GRAY + Skills.maxDefensePoints/*, Skills.useDefenseSkill*/);

		// Dodge item
		dodge = createItem(Skills.dodgeItem, ChatColor.AQUA + "Dodge Chance", 
				Colours.AQUA + plugin.playerSkills.get(player.getUniqueId()).dodgePoints + 
				Colours.DARK_AQUA + "/" + Colours.AQUA + Skills.maxDodgePoints/*, Skills.useDodgeSkill*/);

		// Crit item
		crit = createItem(Skills.critItem, ChatColor.DARK_GREEN + "Crit Chance", 
				Colours.DARK_GREEN + plugin.playerSkills.get(player.getUniqueId()).critPoints + 
				Colours.GREEN + "/" + Colours.DARK_GREEN + Skills.maxCritPoints/*, Skills.useCritSkill*/);

		// Mana item
		mana = createItem(Skills.manaItem, Colours.INDIGO + "Mana",
				Colours.INDIGO + plugin.playerSkills.get(player.getUniqueId()).manaPoints +
				Colours.DARK_BLUE + "/" + Colours.INDIGO + Skills.maxManaPoints/*, Skills.useMaxManaSkill*/);

		// Mana Regen item
		manaRegen = createItem(Skills.manaRegenItem, Colours.DARK_PURPLE + "Mana Regen",
				Colours.DARK_PURPLE + plugin.playerSkills.get(player.getUniqueId()).manaRegenPoints +
				Colours.PINK + "/" + Colours.DARK_PURPLE + Skills.maxManaRegenPoints/*, Skills.useManaRegenSkill*/);
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
							plugin.savePointsFile(playerUUID);

							// Set the mana regen
							plugin.playerSkills.get(playerUUID).setManaRegen(player);

							e.setCancelled(true);
							OpenInventory(player);

						} else player.sendMessage(ChatColor.RED + "You don't have enough skill points");
					}
					e.setCancelled(true);
				}

				/*
				// Check if the skill they're trying to use is disabled if so send a message informing them that it is disabled
				if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Disabled")) {
					player.sendMessage(ChatColor.RED + "That skill is disabled!");
					e.setCancelled(true);
				}
				 */
			}
		}catch (Exception ex) { }
	}
}

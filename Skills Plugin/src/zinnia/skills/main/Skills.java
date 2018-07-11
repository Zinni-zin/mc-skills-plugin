package zinnia.skills.main;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import zinnia.skills.player.EventListener;
import zinnia.skills.player.Menu;
import zinnia.skills.player.SkillPoints;
import zinnia.skills.player.SkillsCommand;
import zinnia.skills.utils.ScoreboardData;
import zinnia.skills.utils.SkillsSave;
import zinnia.skills.utils.TierUtils;

/*
 * As the packages implies this plugin is made by Zinnia James
 * This plugin uses magic spells for the mana skills and essentialX's economy for enchant cost
 */
public class Skills extends JavaPlugin {

	public HashMap<UUID, SkillPoints> playerSkills = new HashMap<UUID, SkillPoints>(); // Hashmap to handle player data with skills
	public HashMap<UUID, PermissionAttachment> tierPerms = new HashMap<UUID, PermissionAttachment>(); // Hashmap to give player tier perms

	private static Skills skills; // A method to hold the main class

	boolean isLoading;
	
	public Menu menu; // Variable for holding the menu object
	public SkillsCommand sCommand; // Variable for holding the object that does the commands

	public SkillsSave skillsFile; // File for holding player data

	public Permission tierOne = new Permission("skills.tier.one"); // Creates permission for tier 1
	public Permission tierTwo = new Permission("skills.tier.two"); // Creates permission for tier 2
	public Permission tierThree = new Permission("skills.tier.three"); // Creates permission for tier 3
	public Permission tierFour = new Permission("skills.tier.four"); // Creates permission for tier 4
	public Permission tierFive = new Permission("skills.tier.five"); // Creates permission for tier 5 
	public Permission tierSix = new Permission("skills.tier.six"); // Creates permission for tier 6 
	public Permission tierSeven = new Permission("skills.tier.seven"); // Creates permission for tier 7 
	public Permission tierEight = new Permission("skills.tier.eight"); // Creates permission for tier 8 
	public Permission tierNine = new Permission("skills.tier.nine"); // Creates permission for tier 9
	public Permission tierTen = new Permission("skills.tier.ten"); // Creates permission for tier 10

	public Permission gmPerm = new Permission("skills.leader"); // Creates a permission for leaders to increase tiers
	public Permission adminPerm = new Permission("skills.admin"); // Creates permission for admin
	public Permission tierResetIncrease = new Permission("skills.tier.resetincrease"); // Creates a perm to reset and increase tier
	public Permission playerReset = new Permission("skills.player.reset"); // Allows a player to reset their own points

	public Permission tempPerm = new Permission("skills.player.temp"); // Permission to allow a player to give themselves temp skills in a fight and load back to normal skills
	
	public FileConfiguration config; // Config file variable

	public boolean useScoreboard = false; // Variable to determine if the scoreboard is going to be used or not
	public boolean saveLevel = true; // Variable to determine if the player's level will be saved in case of deleveling 

	public static int scoreboardPacketSendPeriod = 2; // The period it takes before it sends another scoreboard packet

	public static boolean tiersIncreaseOnLevel; // Determines if tiers will increase on level
	public static boolean tierResetAuto; // Determines if the tier increases and resets automatically
	public static boolean skillTreeReset; // Determines if a player maxes out a specific skill if it'll reset their level and all other skills(maxed out skills stay)
	
	public static boolean enchantCostMoney; // Determines if enchanting will cost money
	
	public static double enchantCost = 500; // The cost to use enchantment tables
	
	/*
	 * Variables to determine what tiers will be used
	 */
	public static boolean useTierOne;
	public static boolean useTierTwo;
	public static boolean useTierThree;
	public static boolean useTierFour;
	public static boolean useTierFive;
	public static boolean useTierSix;
	public static boolean useTierSeven;
	public static boolean useTierEight;
	public static boolean useTierNine;
	public static boolean useTierTen;
	
	/*
	 * Some misc variables that'll determine if we send chat messages on crit or on dodge
	 */
	public static boolean _SendCritMessage = true;
	public static boolean _SendDodgeMessage = true;
	
	/*
	 * Variables to determine what level each tier goes up by
	 */
	public static int tierOneLevel = 5;
	public static int tierTwoLevel = 10;
	public static int tierThreeLevel = 15;
	public static int tierFourLevel = 20;
	public static int tierFiveLevel = 25;
	public static int tierSixLevel = 30;
	public static int tierSevenLevel = 35;
	public static int tierEightLevel = 40;
	public static int tierNineLevel = 45;
	public static int tierTenLevel = 50;

	/* 
	 * Variables to hold to hold tier max hp data
	 */
	public static int defaultMaxHp       = 500; // The default max hp
	public static int tierOneMaxHp       = 1000; // Tier one max hp
	public static int tierTwoMaxHp       = 1500; // Tier two max hp
	public static int tierThreeMaxHp     = 2000; // Tier three max hp
	public static int tierFourMaxHp      = 2500; // Tier four max hp
	public static int tierFiveMaxHp      = 3000; // Tier five max hp
	public static int tierSixMaxHp       = 3500; // Tier six max hp
	public static int tierSevenMaxHp     = 4000; // Tier seven max hp
	public static int tierEightMaxHp     = 4500; // Tier eight max hp
	public static int tierNineMaxHp      = 5000; // Tier nine max hp
	public static int tierTenMaxHp       = 5500; // Tier ten max hp

	/*
	 * Variables to hold tier max magic data
	 */
	public static int defaultMaxMana     = 100; // The default max mana
	public static int tierOneMaxMana     = 200; // Tier one max mana
	public static int tierTwoMaxMana     = 300; // Tier two max mana
	public static int tierThreeMaxMana   = 400; // Tier three max mana
	public static int tierFourMaxMana    = 500; // Tier four max mana
	public static int tierFiveMaxMana    = 600; // Tier five max mana
	public static int tierSixMaxMana     = 700; // Tier six max mana
	public static int tierSevenMaxMana   = 800; // Tier seven max mana
	public static int tierEightMaxMana   = 900; // Tier eight max mana
	public static int tierNineMaxMana    = 1000; // Tier nine max mana
	public static int tierTenMaxMana     = 1100; // Tier ten max mana

	/*
	 * Variables to hold tier regen magic data
	 */
	public static int defaultRegenMana    = 100; // The default mana regen
	public static int tierOneRegenMana    = 200; // Tier one mana regen
	public static int tierTwoRegenMana    = 300; // Tier two mana regen
	public static int tierThreeRegenMana  = 400; // Tier three mana regen
	public static int tierFourRegenMana   = 500; // Tier four mana regen
	public static int tierFiveRegenMana   = 600; // Tier five mana regen
	public static int tierSixRegenMana    = 700; // Tier six mana regen
	public static int tierSevenRegenMana  = 800; // Tier seven mana regen
	public static int tierEightRegenMana  = 900; // Tier eight mana regen
	public static int tierNineRegenMana   = 1000; // Tier nine mana regen
	public static int tierTenRegenMana    = 1100; // Tier ten mana regen

	/*
	 * Variables to determine what skills they want to use
	 */
	public static boolean useMaxHpSkill         = true;
	public static boolean useDmgSkill           = true;
	public static boolean useDefenseSkill       = true;
	public static boolean useDodgeSkill         = true;
	public static boolean useCritSkill          = true;
	public static boolean useMaxManaSkill       = true;
	public static boolean useManaRegenSkill     = true;

	/*
	 * Variables to hold the max amount skill points can go into a skill
	 */
	public static int maxHpPoints = 500; // Max amount of points required to get to max hp
	public static int maxDmgPoints = 500; // Max amount of points required to get max damage 
	public static int maxDefensePoints = 500; // Max amount of points required to get max defense

	public static int maxDodgePoints = 600; // Max amount of points required to get to max dodge
	public static int maxCritPoints = 600; // Max amount of points required to get to max crit


	public static int maxManaPoints = 500; // Max amount of points required to get max mana
	public static int maxManaRegenPoints = 500; // Max amount of points required to get max mana regen

	/*
	 * Variables to hold the max buff from skills
	 */
	public static int maxHp                  = 10000; // Max hp through skills
	public static int maxDmg                 = 400; // Max amount of damage through skills 
	public static int maxDefense             = 400; // Max amount of defense through skills

	public static int maxDodge               = 90; // Max dodge through skills
	public static int maxCrit                = 90; // Max crit through skills

	public static int maxMana                = 2000; // Max amount of mana through skills
	public static int maxManaRegen           = 250; // Max amount of mana regen through skills

	/*
	 * Variables for temporary skill data
	 */
	public static int tempxHpPoints          = 25;
	public static int tempDmgPoints          = 10;
	public static int tempDefensePoints      = 12;
	public static int tempDodgePoints        = 5;
	public static int tempCritPoints         = 2;
	public static int tempManaPoints         = 10;
	public static int tempManaRegenPoints    = 10;
	
	/*
	 * Variables for menu position
	 */
	public static int menuSize               = 45;
	public static int hpPosition             = 20;
	public static int defensePosition        = 22;
	public static int dmgPosition            = 24;
	public static int dodgePosition          = 12;
	public static int critPosition           = 14;
	public static int manaPosition           = 30;
	public static int manaRegenPosition      = 32;
	
	/*
	 * Variables for menu items
	 */
	public static ItemStack hpItem = new Wool(DyeColor.RED).toItemStack(1);
	public static ItemStack damageItem = new Wool(DyeColor.YELLOW).toItemStack(1);
	public static ItemStack defenseItem = new Wool(DyeColor.GRAY).toItemStack(1);
	public static ItemStack dodgeItem = new Wool(DyeColor.LIGHT_BLUE).toItemStack(1);
	public static ItemStack critItem = new Wool(DyeColor.GREEN).toItemStack(1);
	public static ItemStack manaItem = new Wool(DyeColor.BLUE).toItemStack(1);
	public static ItemStack manaRegenItem = new Wool(DyeColor.PINK).toItemStack(1);
	
	/*
	 * Variables to hold mob's damage
	 */
	public static double zombieDamage              = 6;
	public static double zombieVillagerDamage      = 6.5;
	public static double giantDamage               = 10;
	public static double skeletonDamage            = 5.5;
	public static double spiderDamage              = 5.5;
	public static double caveSpiderDamage          = 5;
	public static double endermanDamage            = 4;
	public static double wolfDamage                = 3.5;
	public static double blazeDamage               = 4.5;
	public static double slimeDamage               = 4;
	public static double magmaCubeDamage           = 4.5;
	public static double silverFishDamage          = 5;
	public static double witherSkeletonDamage      = 4.5;
	public static double guardianDamage            = 3.5;
	public static double elderGuardianDamage       = 4.5;
	public static double polarBearDamage           = 6;
	public static double vexDamage                 = 6;
	
	/*
	 * Variables to hold mob's defense
	 */
	public static double ocelotDefense             = 2.5;
	public static double horseDefense              = 7;
	public static double rabbitDefense             = 2;
	public static double sheepDefense              = 3;
	public static double pigDefense                = 3;
	public static double chickenDefense            = 1;
	public static double cowDefense                = 4;
	public static double mooshroomDefense          = 4;
	public static double squidDefense              = 3.5;
	public static double batDefense                = 2;
	public static double villagerDefense           = 5;
	public static double zombieDefense             = 6;
	public static double zombieVillagerDefense     = 6.5;
	public static double giantDefense              = 10;
	public static double zombiePigmanDefense       = 6;
	public static double skeletonDefense           = 5.5;
	public static double spiderDefense             = 5.5;
	public static double caveSpiderDefense         = 5;
	public static double creeperDefense            = 2;
	public static double endermanDefense           = 4;
	public static double wolfDefense               = 3.5;
	public static double witchDefense              = 2;
	public static double blazeDefense              = 4.5;
	public static double slimeDefense              = 4;
	public static double magmaCubeDefense          = 4.5;
	public static double silverFishDefense         = 5;
	public static double witherSkeletonDefense     = 4.5;
	public static double witherDefense             = 20;
	public static double enderDragonDefense        = 20;
	public static double guardianDefense           = 3.5;
	// What I think is above 1.8 not too sure though tbh...
	public static double elderGuardianDefense      = 4.5;
	public static double polarBearDefense          = 6;
	public static double shulkerDefense 	       = 6;
	public static double llamaDefense              = 5;
	public static double endermiteDefense          = 4;
	public static double parrotDefense             = 2;
	public static double vexDefense                = 6;
	public static double strayDefense              = 6;
	public static double evokerDefense             = 4;
	public static double vindicatorDefense         = 4;
	public static double illusionerDefense         = 4;
	
	/*
	 * Important!
	 * ON ENABLE METHOD(Initialization)!
	 */
	@Override
	public void onEnable() {
		isLoading = true;
		config = getConfig(); // Set the config variable
		skillsFile = new SkillsSave(this); // Initialize the player data file

		checkPlayerInfo(); // Call the check player info method

		// Register the event listener
		getServer().getPluginManager().registerEvents(new EventListener(this), this);
		
		menu = new Menu(this); // Initialize the menu
		sCommand = new SkillsCommand(this, menu); // Initialize the s command

		// If the config doesn't exist save it
		if (!new File("plugins" + File.separator + "Skills" + File.separator + "config.yml").exists()) saveConfigData();

		loadConfigData(); // Call the load configuration data method
		
		skills = this; // Set the skill variable to this instance
		isLoading = false;
	}

	/*
	 * IMPORTANT!
	 * ON DISABLE METHOD
	 */
	@Override
	public void onDisable() {
		saveConfigData(); // Save the config data
		skillsFile.Save(); // Save the skill file data
	}

	/*
	 * IMPORTANT!
	 * DOES COMMAND STUFF!
	 */
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){

		if(cmd.getName().equalsIgnoreCase("Skills"))  // Check the command name
			sCommand.Command(sender, Label, args); // Do the command
		
		return true;
	}

	// Load player data
	public void loadPointsFile(UUID playerUUID) {
		try {
			playerSkills.get(playerUUID).points = skillsFile.config.getInt(playerUUID + ".Skill-Points");
			playerSkills.get(playerUUID).tierLevel = skillsFile.config.getInt(playerUUID + ".tier-level");
			playerSkills.get(playerUUID).lastLevel = skillsFile.config.getInt(playerUUID + ".last-level");
			playerSkills.get(playerUUID).healthPoints = skillsFile.config.getInt(playerUUID + ".Health-Points");
			playerSkills.get(playerUUID).dmgPoints = skillsFile.config.getInt(playerUUID + ".Damage-Points");
			playerSkills.get(playerUUID).defensePoints = skillsFile.config.getInt(playerUUID + ".Defense-Points");
			playerSkills.get(playerUUID).dodgePoints = skillsFile.config.getInt(playerUUID + ".Dodge-Points");
			playerSkills.get(playerUUID).critPoints = skillsFile.config.getInt(playerUUID + ".Crit-Points");
			playerSkills.get(playerUUID).manaPoints = skillsFile.config.getInt(playerUUID + ".Mana-Points");
			playerSkills.get(playerUUID).manaRegenPoints = skillsFile.config.getInt(playerUUID + ".Mana-Regen-Points");
			if(!isLoading)
				TierUtils.loadTier(this, target(playerUUID), playerSkills.get(playerUUID).tierLevel);
		} catch(Exception e) { e.printStackTrace(); }
	}

	// Save player data
	public void savePointsFile(UUID playerUUID) {
		skillsFile.config.set(playerUUID + ".Skill-Points", playerSkills.get(playerUUID).points);
		skillsFile.config.set(playerUUID + ".tier-level", playerSkills.get(playerUUID).tierLevel);
		skillsFile.config.set(playerUUID + ".last-level", playerSkills.get(playerUUID).lastLevel);
		skillsFile.config.set(playerUUID + ".Health-Points", playerSkills.get(playerUUID).healthPoints);
		skillsFile.config.set(playerUUID + ".Damage-Points", playerSkills.get(playerUUID).dmgPoints);
		skillsFile.config.set(playerUUID + ".Defense-Points", playerSkills.get(playerUUID).defensePoints);
		skillsFile.config.set(playerUUID + ".Dodge-Points", playerSkills.get(playerUUID).dodgePoints);
		skillsFile.config.set(playerUUID + ".Crit-Points", playerSkills.get(playerUUID).critPoints);
		skillsFile.config.set(playerUUID + ".Mana-Points", playerSkills.get(playerUUID).manaPoints);
		skillsFile.config.set(playerUUID + ".Mana-Regen-Points", playerSkills.get(playerUUID).manaRegenPoints);
		skillsFile.Save();
	}

	// Load the config file
	public void loadConfigData() {
		saveLevel = config.getBoolean("Save-Highest-Level");
		
		tiersIncreaseOnLevel = config.getBoolean("Increase-Tier-On-Level-Up");

		tierResetAuto = config.getBoolean("auto-reset-tier-increase");
		skillTreeReset = config.getBoolean("point-max-lvl-reset");
		
		enchantCostMoney = config.getBoolean("Enchanting-Cost-Money");

		enchantCost = config.getDouble("Enchant-Cost");
		
		// Tiers we're using
		useTierOne = config.getBoolean("Use.Tier1");
		useTierTwo = config.getBoolean("Use.Tier2");
		useTierThree = config.getBoolean("Use.Tier3");
		useTierFour = config.getBoolean("Use.Tier4");
		useTierFive = config.getBoolean("Use.Tier5");
		useTierSix = config.getBoolean("Use.Tier6");
		useTierSeven = config.getBoolean("Use.Tier7");
		useTierEight = config.getBoolean("Use.Tier8");
		useTierNine = config.getBoolean("Use.Tier9");
		useTierTen = config.getBoolean("Use.Tier10");

		// What chat messages we're sending
		_SendCritMessage = config.getBoolean("Send-Crit-Message");
		_SendDodgeMessage = config.getBoolean("Send-Dodge-Message");
		
		// Level required to increase to x tier
		tierOneLevel = config.getInt("Tier1-Level");
		tierTwoLevel = config.getInt("Tier2-Level");
		tierThreeLevel = config.getInt("Tier3-Level");
		tierFourLevel = config.getInt("Tier4-Level");
		tierFiveLevel = config.getInt("Tier5-Level");
		tierSixLevel = config.getInt("Tier6-Level");
		tierSevenLevel = config.getInt("Tier7-Level");
		tierEightLevel = config.getInt("Tier8-Level");
		tierNineLevel = config.getInt("Tier9-Level");
		tierTenLevel = config.getInt("Tier10-Level");
		
		// Tier hp data
		defaultMaxHp = config.getInt("Tier-Hp.default");
		tierOneMaxHp = config.getInt("Tier-Hp.Tier1");
		tierTwoMaxHp = config.getInt("Tier-Hp.Tier2");
		tierThreeMaxHp = config.getInt("Tier-Hp.Tier3");
		tierFourMaxHp = config.getInt("Tier-Hp.Tier4");
		tierFiveMaxHp = config.getInt("Tier-Hp.Tier5");
		tierSixMaxHp = config.getInt("Tier-Hp.Tier6");
		tierSevenMaxHp = config.getInt("Tier-Hp.Tier7");
		tierEightMaxHp = config.getInt("Tier-Hp.Tier8");
		tierNineMaxHp = config.getInt("Tier-Hp.Tier9");
		tierTenMaxHp = config.getInt("Tier-Hp.Tier10");

		// Tier mana data
		defaultMaxMana = config.getInt("Tier-Mana.default");
		tierOneMaxMana = config.getInt("Tier-Mana.Tier1");
		tierTwoMaxMana = config.getInt("Tier-Mana.Tier2");
		tierThreeMaxMana = config.getInt("Tier-Mana.Tier3");
		tierFourMaxMana = config.getInt("Tier-Mana.Tier4");
		tierFiveMaxMana = config.getInt("Tier-Mana.Tier5");
		tierSixMaxMana = config.getInt("Tier-Mana.Tier6");
		tierSevenMaxMana = config.getInt("Tier-Mana.Tier7");
		tierEightMaxMana = config.getInt("Tier-Mana.Tier8");
		tierNineMaxMana = config.getInt("Tier-Mana.Tier9");
		tierTenMaxMana = config.getInt("Tier-Mana.Tier10");

		// Tier mana regen data
		defaultRegenMana = config.getInt("Tier-Mana-Regen.default");
		tierOneRegenMana = config.getInt("Tier-Mana-Regen.Tier1");
		tierTwoRegenMana = config.getInt("Tier-Mana-Regen.Tier2");
		tierThreeRegenMana = config.getInt("Tier-Mana-Regen.Tier3");
		tierFourRegenMana = config.getInt("Tier-Mana-Regen.Tier4");
		tierFiveRegenMana = config.getInt("Tier-Mana-Regen.Tier5");
		tierSixRegenMana = config.getInt("Tier-Mana-Regen.Tier6");
		tierSevenRegenMana = config.getInt("Tier-Mana-Regen.Tier7");
		tierEightRegenMana = config.getInt("Tier-Mana-Regen.Tier8");
		tierNineRegenMana = config.getInt("Tier-Mana-Regen.Tier9");
		tierTenRegenMana = config.getInt("Tier-Mana-Regen.Tier10");

		// Determine what skill points we're using
		useMaxHpSkill = config.getBoolean("Use-MaxHp-Skill");
		useDmgSkill = config.getBoolean("Use-Dmg-Skill");
		useDefenseSkill = config.getBoolean("Use-Defense-Skill");
		useDodgeSkill = config.getBoolean("Use-Dodge-Skill");
		useCritSkill = config.getBoolean("Use-Crit-Skill");
		useMaxManaSkill = config.getBoolean("Use-MaxMana-Skill");
		useManaRegenSkill = config.getBoolean("Use-ManaRegen-Skill");

		// Max amount of skill points
		maxHpPoints = config.getInt("Max-Skill-Ponits.Hp");
		maxDmgPoints = config.getInt("Max-Skill-Ponits.Dmg");
		maxDefensePoints = config.getInt("Max-Skill-Ponits.Defense");
		maxDodgePoints = config.getInt("Max-Skill-Ponits.Dodge");
		maxCritPoints = config.getInt("Max-Skill-Ponits.Crit");
		maxManaPoints = config.getInt("Max-Skill-Ponits.Mana");
		maxManaRegenPoints = config.getInt("Max-Skill-Ponits.Mana-Regen");

		// Max buff amount from skills
		maxHp = config.getInt("Max-Buff.Hp");
		maxDmg = config.getInt("Max-Buff.Dmg");
		maxDefense = config.getInt("Max-Buff.Defense");
		maxDodge = config.getInt("Max-Buff.Dodge");
		maxCrit = config.getInt("Max-Buff.Crit");
		maxMana = config.getInt("Max-Buff.Mana");
		maxManaRegen = config.getInt("Max-Buff.Mana-Regen");

		// Temp skill points
		tempxHpPoints = config.getInt("Temp-Points.Hp");
		tempDmgPoints = config.getInt("Temp-Points.Damage");
		tempDefensePoints= config.getInt("Temp-Points.Defense");
		tempDodgePoints = config.getInt("Temp-Points.Dodge");
		tempCritPoints = config.getInt("Temp-Points.Crit");
		tempManaPoints = config.getInt("Temp-Points.Mana");
		tempManaRegenPoints= config.getInt("Temp-Points.Mana-Regen");
		
		// Menu Positions
		menuSize = config.getInt("Menu.Size");
		hpPosition = config.getInt("Menu.Position.Hp");
		dmgPosition = config.getInt("Menu.Position.Damage");
		defensePosition = config.getInt("Menu.Position.Defense");
		dodgePosition = config.getInt("Menu.Position.Dodge");
		critPosition = config.getInt("Menu.Position.Crit");
		manaPosition = config.getInt("Menu.Position.Max-Mana");
		manaRegenPosition = config.getInt("Menu.Position.Mana-Regen");
		if(menuSize % 9 != 0) { menuSize = 45; System.out.println("Menu Size has to be a multiple of 9!"); }
		
		// Menu Items
		hpItem = config.getItemStack("Menu.Items.Hp");
		damageItem = config.getItemStack("Menu.Items.Damage");
		defenseItem = config.getItemStack("Menu.Items.Defense");
		dodgeItem = config.getItemStack("Menu.Items.Dodge");
		critItem = config.getItemStack("Menu.Items.Crit");
		manaItem = config.getItemStack("Menu.Items.Mana");
		manaRegenItem = config.getItemStack("Menu.Items.Mana-Regen");
		
		// Scoreboard Data
		scoreboardPacketSendPeriod = config.getInt("Seconds-Each-Scoreboard-Packet-Is-Sent");

		// Load all the data to tell us which scores we're using for the scoreboard
		ScoreboardData._UseScore0 = config.getBoolean("Scoreboard.Use-Slot-0");
		ScoreboardData._UseScore1 = config.getBoolean("Scoreboard.Use-Slot-1");
		ScoreboardData._UseScore2 = config.getBoolean("Scoreboard.Use-Slot-2");
		ScoreboardData._UseScore3 = config.getBoolean("Scoreboard.Use-Slot-3");
		ScoreboardData._UseScore4 = config.getBoolean("Scoreboard.Use-Slot-4");
		ScoreboardData._UseScore5 = config.getBoolean("Scoreboard.Use-Slot-5");
		ScoreboardData._UseScore6 = config.getBoolean("Scoreboard.Use-Slot-6");
		ScoreboardData._UseScore7 = config.getBoolean("Scoreboard.Use-Slot-7");
		ScoreboardData._UseScore8 = config.getBoolean("Scoreboard.Use-Slot-8");
		ScoreboardData._UseScore9 = config.getBoolean("Scoreboard.Use-Slot-9");
		ScoreboardData._UseScore10 = config.getBoolean("Scoreboard.Use-Slot-10");
		ScoreboardData._UseScore11 = config.getBoolean("Scoreboard.Use-Slot-11");
		ScoreboardData._UseScore12 = config.getBoolean("Scoreboard.Use-Slot-12");
		ScoreboardData._UseScore13 = config.getBoolean("Scoreboard.Use-Slot-13");
		ScoreboardData._UseScore14 = config.getBoolean("Scoreboard.Use-Slot-14");
		ScoreboardData._UseScore15 = config.getBoolean("Scoreboard.Use-Slot-15");
		
		/*
		 * Mob Damage
		 */
		zombieDamage = config.getDouble("Mob.Damage.zombie");
		zombieVillagerDamage = config.getDouble("Mob.Damage.zombie-villager");
		giantDamage = config.getDouble("Mob.Damage.gaint");
		skeletonDamage = config.getDouble("Mob.Damage.skeleton");
		spiderDamage = config.getDouble("Mob.Damage.spider");
		caveSpiderDamage = config.getDouble("Mob.Damage.cave-spider");
		endermanDamage = config.getDouble("Mob.Damage.enderman");
		wolfDamage = config.getDouble("Mob.Damage.wolf");
		blazeDamage = config.getDouble("Mob.Damage.blaze");
		slimeDamage = config.getDouble("Mob.Damage.slime");
		magmaCubeDamage = config.getDouble("Mob.Damage.magma-cube");
		silverFishDamage = config.getDouble("Mob.Damage.silver-fish");
		witherSkeletonDamage = config.getDouble("Mob.Damage.wither-skeleton");
		guardianDamage = config.getDouble("Mob.Damage.guardian");
		elderGuardianDamage = config.getDouble("Mob.Damage.elder-guardian");
		polarBearDamage = config.getDouble("Mob.Damage.polar-bear");
		vexDamage = config.getDouble("Mob.Damage.vex");
		
		/*
		 * Mob Defense
		 */
		ocelotDefense = config.getDouble("Mob.Defense.ocelot");
		horseDefense = config.getDouble("Mob.Defense.horse");
		rabbitDefense = config.getDouble("Mob.Defense.rabbit");
		sheepDefense = config.getDouble("Mob.Defense.sheep");
		pigDefense = config.getDouble("Mob.Defense.pig");
		chickenDefense = config.getDouble("Mob.Defense.chicken");
		cowDefense = config.getDouble("Mob.Defense.cow");
		mooshroomDefense = config.getDouble("Mob.Defense.mooshroom");
		squidDefense = config.getDouble("Mob.Defense.squid");
		batDefense = config.getDouble("Mob.Defense.bat");
		villagerDefense = config.getDouble("Mob.Defense.villager");
		zombieDefense = config.getDouble("Mob.Defense.zombie");
		zombieVillagerDefense = config.getDouble("Mob.Defense.zombie-villager");
		giantDefense = config.getDouble("Mob.Defense.giant");
		zombiePigmanDefense = config.getDouble("Mob.Defense.zombie-pigman");
		skeletonDefense = config.getDouble("Mob.Defense.skeleton");
		spiderDefense = config.getDouble("Mob.Defense.spider");
		caveSpiderDefense = config.getDouble("Mob.Defense.cave-spider");
		creeperDefense = config.getDouble("Mob.Defense.creeper");
		endermanDefense = config.getDouble("Mob.Defense.enderman");
		wolfDefense = config.getDouble("Mob.Defense.wolf");
		witchDefense = config.getDouble("Mob.Defense.witch");
		blazeDefense = config.getDouble("Mob.Defense.blaze");
		slimeDefense = config.getDouble("Mob.Defense.slime");
		magmaCubeDefense = config.getDouble("Mob.Defense.magma-cube");
		silverFishDefense = config.getDouble("Mob.Defense.silver-fish");
		witherSkeletonDefense = config.getDouble("Mob.Defense.wither-skeleton");
		witherDefense = config.getDouble("Mob.Defense.wither");
		enderDragonDefense = config.getDouble("Mob.Defense.ender-dragon");
		guardianDefense = config.getDouble("Mob.Defense.guardian");
		elderGuardianDefense = config.getDouble("Mob.Defense.elder-guardian");
		polarBearDefense = config.getDouble("Mob.Defense.polar-bear");
		shulkerDefense = config.getDouble("Mob.Defense.shulker");
		llamaDefense = config.getDouble("Mob.Defense.llama");
		endermiteDefense = config.getDouble("Mob.Defense.endermite");
		parrotDefense = config.getDouble("Mob.Defense.parrot");
		vexDefense = config.getDouble("Mob.Defense.vex");
		strayDefense = config.getDouble("Mob.Defense.stray");
		evokerDefense = config.getDouble("Mob.Defense.evoker");
		vindicatorDefense = config.getDouble("Mob.Defense.vindicator");
		illusionerDefense = config.getDouble("Mob.Defense.illusioner");
		
	}

	// Save the config file
	public void saveConfigData() {
		config.set("Save-Highest-Level", saveLevel);

		config.set("Increase-Tier-On-Level-Up", tiersIncreaseOnLevel);
		config.set("auto-reset-tier-increase", tierResetAuto);
		config.set("point-max-lvl-reset", skillTreeReset);
		
		config.set("Enchanting-Cost-Money", enchantCostMoney);

		config.set("Enchant-Cost", enchantCost);
		
		// Tiers we're using
		config.set("Use.Tier1", useTierOne);
		config.set("Use.Tier2", useTierTwo);
		config.set("Use.Tier3", useTierThree);
		config.set("Use.Tier4", useTierFour);
		config.set("Use.Tier5", useTierFive);
		config.set("Use.Tier6", useTierSix);
		config.set("Use.Tier7", useTierSeven);
		config.set("Use.Tier8", useTierEight);
		config.set("Use.Tier9", useTierNine);
		config.set("Use.Tier10", useTierTen);
		
		// What chat messages we're sending
		config.set("Send-Crit-Message", _SendCritMessage);
		config.set("Send-Dodge-Message", _SendDodgeMessage);

		// Tier1-Level
		config.set("Tier-Level.Tier1", tierOneLevel);
		config.set("Tier-Level.Tier2", tierTwoLevel);
		config.set("Tier-Level.Tier3", tierThreeLevel);
		config.set("Tier-Level.Tier4", tierFourLevel);
		config.set("Tier-Level.Tier5", tierFiveLevel);
		config.set("Tier-Level.Tier6", tierSixLevel);
		config.set("Tier-Level.Tier7", tierSevenLevel);
		config.set("Tier-Level.Tier8", tierEightLevel);
		config.set("Tier-Level.Tier9", tierNineLevel);
		config.set("Tier-Level.Tier10", tierTenLevel);

		// Tier max hp data
		config.set("Tier-Hp.default",   defaultMaxHp);
		config.set("Tier-Hp.Tier1",     tierOneMaxHp);
		config.set("Tier-Hp.Tier2",     tierTwoMaxHp);
		config.set("Tier-Hp.Tier3",     tierThreeMaxHp);
		config.set("Tier-Hp.Tier4",     tierFourMaxHp);
		config.set("Tier-Hp.Tier5",     tierFiveMaxHp);
		config.set("Tier-Hp.Tier6",     tierSixMaxHp);
		config.set("Tier-Hp.Tier7",     tierSevenMaxHp);
		config.set("Tier-Hp.Tier8",     tierEightMaxHp);
		config.set("Tier-Hp.Tier9",     tierNineMaxHp);
		config.set("Tier-Hp.Tier10",    tierTenMaxHp);

		// Tier max mana data
		config.set("Tier-Mana.default",   defaultMaxMana);
		config.set("Tier-Mana.Tier1",     tierOneMaxMana);
		config.set("Tier-Mana.Tier2",     tierTwoMaxMana);
		config.set("Tier-Mana.Tier3",     tierThreeMaxMana);
		config.set("Tier-Mana.Tier4",     tierFourMaxMana);
		config.set("Tier-Mana.Tier5",     tierFiveMaxMana);
		config.set("Tier-Mana.Tier6",     tierSixMaxMana);
		config.set("Tier-Mana.Tier7",     tierSevenMaxMana);
		config.set("Tier-Mana.Tier8",     tierEightMaxMana);
		config.set("Tier-Mana.Tier9",     tierNineMaxMana);
		config.set("Tier-Mana.Tier10",    tierTenMaxMana);

		// Tier mana regen data
		config.set("Tier-Mana-Regen.default",   defaultRegenMana);
		config.set("Tier-Mana-Regen.Tier1",     tierOneRegenMana);
		config.set("Tier-Mana-Regen.Tier2",     tierTwoRegenMana);
		config.set("Tier-Mana-Regen.Tier3",     tierThreeRegenMana);
		config.set("Tier-Mana-Regen.Tier4",     tierFourRegenMana);
		config.set("Tier-Mana-Regen.Tier5",     tierFiveRegenMana);
		config.set("Tier-Mana-Regen.Tier6",     tierSixRegenMana);
		config.set("Tier-Mana-Regen.Tier7",     tierSevenRegenMana);
		config.set("Tier-Mana-Regen.Tier8",     tierEightRegenMana);
		config.set("Tier-Mana-Regen.Tier9",     tierNineRegenMana);
		config.set("Tier-Mana-Regen.Tier10",    tierTenRegenMana);

		// Determine what skills we're using
		config.set("Use-MaxHp-Skill", useMaxHpSkill);
		config.set("Use-Dmg-Skill", useDmgSkill);
		config.set("Use-Defense-Skill", useDefenseSkill);
		config.set("Use-Dodge-Skill", useDodgeSkill);
		config.set("Use-Crit-Skill", useCritSkill);
		config.set("Use-MaxMana-Skill", useMaxManaSkill);
		config.set("Use-ManaRegen-Skill", useManaRegenSkill);

		// Max amount of skill points
		config.set("Max-Skill-Ponits.Hp", maxHpPoints);
		config.set("Max-Skill-Ponits.Dmg", maxDmgPoints);
		config.set("Max-Skill-Ponits.Defense", maxDefensePoints);
		config.set("Max-Skill-Ponits.Dodge", maxDodgePoints);
		config.set("Max-Skill-Ponits.Crit", maxCritPoints);
		config.set("Max-Skill-Ponits.Mana", maxManaPoints);
		config.set("Max-Skill-Ponits.Mana-Regen", maxManaRegenPoints);

		// Max buff amount from skills
		config.set("Max-Buff.Hp", maxHp);
		config.set("Max-Buff.Dmg", maxDmg);
		config.set("Max-Buff.Defense", maxDefense);
		config.set("Max-Buff.Dodge", maxDodge);
		config.set("Max-Buff.Crit", maxCrit);
		config.set("Max-Buff.Mana", maxMana);
		config.set("Max-Buff.Mana-Regen", maxManaRegen);

		// Temp skill points
		config.set("Temp-Points.Hp", tempxHpPoints);
		config.set("Temp-Points.Damage", tempDmgPoints);
		config.set("Temp-Points.Defense", tempDefensePoints);
		config.set("Temp-Points.Dodge", tempDodgePoints);
		config.set("Temp-Points.Crit", tempCritPoints);
		config.set("Temp-Points.Mana", tempManaPoints);
		config.set("Temp-Points.Mana-Regen", tempManaRegenPoints);
		
		// Menu positions
		config.set("Menu.Size", menuSize);
		config.set("Menu.Position.Hp", hpPosition);
		config.set("Menu.Position.Damage", dmgPosition);
		config.set("Menu.Position.Defense", defensePosition);
		config.set("Menu.Position.Dodge", dodgePosition);
		config.set("Menu.Position.Crit", critPosition);
		config.set("Menu.Position.Max-Mana", manaPosition);
		config.set("Menu.Position.Mana-Regen", manaRegenPosition);
		
		// Menu Items
		config.set("Menu.Items.Hp", hpItem);
		config.set("Menu.Items.Damage", damageItem);
		config.set("Menu.Items.Defense", defenseItem);
		config.set("Menu.Items.Dodge", dodgeItem);
		config.set("Menu.Items.Crit", critItem);
		config.set("Menu.Items.Mana", manaItem);
		config.set("Menu.Items.Mana-Regen", manaRegenItem);
		
		// Scoreboard data
		config.set("Seconds-Each-Scoreboard-Packet-Is-Sent", scoreboardPacketSendPeriod);

		// Set all the use score data
		config.set("Scoreboard.Use-Slot-0", ScoreboardData._UseScore0);
		config.set("Scoreboard.Use-Slot-1", ScoreboardData._UseScore1);
		config.set("Scoreboard.Use-Slot-2", ScoreboardData._UseScore2);
		config.set("Scoreboard.Use-Slot-3", ScoreboardData._UseScore3);
		config.set("Scoreboard.Use-Slot-4", ScoreboardData._UseScore4);
		config.set("Scoreboard.Use-Slot-5", ScoreboardData._UseScore5);
		config.set("Scoreboard.Use-Slot-6", ScoreboardData._UseScore6);
		config.set("Scoreboard.Use-Slot-7", ScoreboardData._UseScore7);
		config.set("Scoreboard.Use-Slot-8", ScoreboardData._UseScore8);
		config.set("Scoreboard.Use-Slot-9", ScoreboardData._UseScore9);
		config.set("Scoreboard.Use-Slot-10", ScoreboardData._UseScore10);
		config.set("Scoreboard.Use-Slot-11", ScoreboardData._UseScore11);
		config.set("Scoreboard.Use-Slot-12", ScoreboardData._UseScore12);
		config.set("Scoreboard.Use-Slot-13", ScoreboardData._UseScore13);
		config.set("Scoreboard.Use-Slot-14", ScoreboardData._UseScore14);
		config.set("Scoreboard.Use-Slot-15", ScoreboardData._UseScore15);

		/*
		 * Mob damage
		 */
		config.set("Mob.Damage.zombie", zombieDamage);
		config.set("Mob.Damage.zombie-villager", zombieVillagerDamage);
		config.set("Mob.Damage.gaint", giantDamage);
		config.set("Mob.Damage.skeleton", skeletonDamage);
		config.set("Mob.Damage.spider", spiderDamage);
		config.set("Mob.Damage.cave-spider", caveSpiderDamage);
		config.set("Mob.Damage.enderman", endermanDamage);
		config.set("Mob.Damage.wolf", wolfDamage);
		config.set("Mob.Damage.blaze", blazeDamage);
		config.set("Mob.Damage.slime", slimeDamage);
		config.set("Mob.Damage.magma-cube", magmaCubeDamage);
		config.set("Mob.Damage.silver-fish", silverFishDamage);
		config.set("Mob.Damage.wither-skeleton", witherSkeletonDamage);
		config.set("Mob.Damage.guardian", guardianDamage);
		config.set("Mob.Damage.elder-guardian", elderGuardianDamage);
		config.set("Mob.Damage.polar-bear", polarBearDamage);
		config.set("Mob.Damage.vex", vexDamage);
		
		/*
		 * Mob defense
		 */
		
		config.set("Mob.Defense.ocelot", ocelotDefense);
		config.set("Mob.Defense.horse", horseDefense);
		config.set("Mob.Defense.rabbit", rabbitDefense);
		config.set("Mob.Defense.sheep", sheepDefense);
		config.set("Mob.Defense.pig", pigDefense);
		config.set("Mob.Defense.chicken", chickenDefense);
		config.set("Mob.Defense.cow", cowDefense);
		config.set("Mob.Defense.mooshroom", mooshroomDefense);
		config.set("Mob.Defense.squid", squidDefense);
		config.set("Mob.Defense.bat", batDefense);
		config.set("Mob.Defense.villager", villagerDefense);
		config.set("Mob.Defense.zombie", zombieDefense);
		config.set("Mob.Defense.zombie-villager", zombieVillagerDefense);
		config.set("Mob.Defense.giant", giantDefense);
		config.set("Mob.Defense.zombie-pigman", zombiePigmanDefense);
		config.set("Mob.Defense.skeleton", skeletonDefense);
		config.set("Mob.Defense.spider", spiderDefense);
		config.set("Mob.Defense.cave-spider", caveSpiderDefense);
		config.set("Mob.Defense.creeper", creeperDefense);
		config.set("Mob.Defense.enderman", endermanDefense);
		config.set("Mob.Defense.wolf", wolfDefense);
		config.set("Mob.Defense.witch", witchDefense);
		config.set("Mob.Defense.blaze", blazeDefense);
		config.set("Mob.Defense.slime", slimeDefense);
		config.set("Mob.Defense.magma-cube", magmaCubeDefense);
		config.set("Mob.Defense.silver-fish", silverFishDefense);
		config.set("Mob.Defense.wither-skeleton", witherSkeletonDefense);
		config.set("Mob.Defense.wither", witherDefense);
		config.set("Mob.Defense.ender-dragon", enderDragonDefense);
		config.set("Mob.Defense.guardian", guardianDefense);
		config.set("Mob.Defense.elder-guardian", elderGuardianDefense);
		config.set("Mob.Defense.polar-bear", polarBearDefense);
		config.set("Mob.Defense.shulker", shulkerDefense);
		config.set("Mob.Defense.llama", llamaDefense);
		config.set("Mob.Defense.endermite", endermiteDefense);
		config.set("Mob.Defense.parrot", parrotDefense);
		config.set("Mob.Defense.vex", vexDefense);
		config.set("Mob.Defense.stray", strayDefense);
		config.set("Mob.Defense.evoker", evokerDefense);
		config.set("Mob.Defense.vindicator", vindicatorDefense);
		config.set("Mob.Defense.illusioner", illusionerDefense);
		
		saveConfig(); // Save the config
	}

	/*
	 * Make config save 15 booleans when there's no file!
	 */

	// Check's player info
	public void checkPlayerInfo()
	{
		// Loop through online players and add them to the hash map(if this somehow happens)
		for(Player p : getServer().getOnlinePlayers()) {

			// Check if the hashmap doesn't have the player's UUID
			if(!playerSkills.containsKey(p.getUniqueId())) { 
				playerSkills.put(p.getUniqueId(), new SkillPoints()); // Add them to the hashmap
				playerSkills.get(p.getUniqueId()).tierLevel = 0;
				playerSkills.get(p.getUniqueId()).points = 0; // Set the points to zero
				playerSkills.get(p.getUniqueId()).healthPoints = 0; // Set the health points to zero
				playerSkills.get(p.getUniqueId()).dodgePoints = 0; // Set the dodge points to zero
				playerSkills.get(p.getUniqueId()).critPoints = 0; // Set the crit points to zero
				loadPointsFile(p.getUniqueId()); // Save the player data file
			}
			if(playerSkills.containsKey(p.getUniqueId())) // If they are in the hashmap
				loadPointsFile(p.getUniqueId()); // Load the player data
		}

		// Repeat for offline players
		for(OfflinePlayer p : getServer().getOfflinePlayers()) {
			if(!playerSkills.containsKey(p.getUniqueId())) {
				playerSkills.put(p.getUniqueId(), new SkillPoints());
				playerSkills.get(p.getUniqueId()).tierLevel = 0;
				playerSkills.get(p.getUniqueId()).points = 0;
				playerSkills.get(p.getUniqueId()).healthPoints = 0;	
				playerSkills.get(p.getUniqueId()).dodgePoints = 0;	
				playerSkills.get(p.getUniqueId()).critPoints = 0;
				loadPointsFile(p.getUniqueId());
			}
			if(playerSkills.containsKey(p.getUniqueId()))
				loadPointsFile(p.getUniqueId());
		}
	}

	// Check if the target is online, using the player's name
	public boolean targetIsOnline(String target) {
		for(Player p : Bukkit.getOnlinePlayers()) // Loop through the online players.
			if(p.getName().equalsIgnoreCase(target)) // Get the target's name
				return true;

		return false;
	}
	
	// Check if the target is online, using the UUID
	public boolean targetIsOnline(UUID target) {
		for(Player p : Bukkit.getOnlinePlayers()) // Loop through the online players.
			if(p.getUniqueId() == target) // Get the target's name
				return true;
		return false;
	}

	public Player target(CommandSender sender, String target) {
		if(targetIsOnline(target)) // Check if the target is online
			return Bukkit.getPlayer(target); // Return target's name
		else {
			sender.sendMessage(ChatColor.RED + "That player is offline!"); // Send the player a message if this fails.
			return null; // Return nothing.
		}
	}

	public Player target(String target) {
		if(targetIsOnline(target)) // Check if the target is online
			return Bukkit.getPlayer(target); // Return target's name
		else {
			System.out.println("" + ChatColor.RED + "That player is offline!");
			return null; // Return nothing.
		}
	}
	
	public Player target(UUID target) {
		if(targetIsOnline(target)) // Check if the target is online
			return Bukkit.getPlayer(target); // Return target
		else 
			return null; // Return nothing.
	}
	
	public static Skills getInstance() {
		return skills;
	}
}

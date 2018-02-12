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

/*
 * As the packages implies this plugin is made by Zinnia James
 * This plugin uses magic spells for the mana skills and essentialX's economy for enchant cost
 * Note the scoreboard in this plugin is done via reflection
 */
public class Skills extends JavaPlugin {

	public HashMap<UUID, SkillPoints> playerSkills = new HashMap<UUID, SkillPoints>(); // Hashmap to handle player data with skills
	public HashMap<UUID, PermissionAttachment> tierPerms = new HashMap<UUID, PermissionAttachment>(); // Hashmap to give player tier perms

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

	public FileConfiguration config; // Config file variable

	public boolean useScoreboard = false; // Variable to determine if the scoreboard is going to be used or not
	public boolean saveLevel = true; // Variable to determine if the player's level will be saved in case of deleveling 

	public static int scoreboardPacketSendPeriod = 2; // The period it takes before it sends another scoreboard packet

	public static boolean tiersIncreaseOnLevel; // Determines if tiers will increase on level

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
	public static int maxHp = 10000; // Max hp through skills
	public static int maxDmg = 400; // Max amount of damage through skills 
	public static int maxDefense = 400; // Max amount of defense through skills

	public static int maxDodge = 90; // Max dodge through skills
	public static int maxCrit = 90; // Max crit through skills

	public static int maxMana = 2000; // Max amount of mana through skills
	public static int maxManaRegen = 250; // Max amount of mana regen through skills

	/*
	 * Variables for menu position
	 */
	public static int menuSize = 45;
	public static int hpPosition = 20;
	public static int defensePosition = 22;
	public static int dmgPosition = 24;
	public static int dodgePosition = 12;
	public static int critPosition = 14;
	public static int manaPosition = 30;
	public static int manaRegenPosition = 32;
	
	/*
	 * Variables for menu items
	 */
	public static ItemStack hpItem = new Wool(DyeColor.RED).toItemStack(1);
	public static ItemStack defenseItem = new Wool(DyeColor.GRAY).toItemStack(1);
	public static ItemStack damageItem = new Wool(DyeColor.YELLOW).toItemStack(1);
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
			playerSkills.get(playerUUID).lastLevel = skillsFile.config.getInt(playerUUID + ".last-level");
			playerSkills.get(playerUUID).healthPoints = skillsFile.config.getInt(playerUUID + ".Health-Points");
			playerSkills.get(playerUUID).dmgPoints = skillsFile.config.getInt(playerUUID + ".Damage-Points");
			playerSkills.get(playerUUID).defensePoints = skillsFile.config.getInt(playerUUID + ".Defense-Points");
			playerSkills.get(playerUUID).dodgePoints = skillsFile.config.getInt(playerUUID + ".Dodge-Points");
			playerSkills.get(playerUUID).critPoints = skillsFile.config.getInt(playerUUID + ".Crit-Points");
			playerSkills.get(playerUUID).manaPoints = skillsFile.config.getInt(playerUUID + ".Mana-Points");
			playerSkills.get(playerUUID).manaRegenPoints = skillsFile.config.getInt(playerUUID + ".Mana-Regen-Points");
		} catch(Exception e) {}
	}

	// Save player data
	public void savePointsFile(UUID playerUUID) {
		skillsFile.config.set(playerUUID + ".Skill-Points", playerSkills.get(playerUUID).points);
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

		enchantCostMoney = config.getBoolean("Enchanting-Cost-Money");

		enchantCost = config.getDouble("Enchant-Cost");

		// Tiers we're using
		useTierOne = config.getBoolean("Use-Tier1");
		useTierTwo = config.getBoolean("Use-Tier2");
		useTierThree = config.getBoolean("Use-Tier3");
		useTierFour = config.getBoolean("Use-Tier4");
		useTierFive = config.getBoolean("Use-Tier5");
		useTierSix = config.getBoolean("Use-Tier6");
		useTierSeven = config.getBoolean("Use-Tier7");
		useTierEight = config.getBoolean("Use-Tier8");
		useTierNine = config.getBoolean("Use-Tier9");
		useTierTen = config.getBoolean("Use-Tier10");

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
		defaultMaxHp = config.getInt("default-MaxHp");
		tierOneMaxHp = config.getInt("Tier1-MaxHp");
		tierTwoMaxHp = config.getInt("Tier2-MaxHp");
		tierThreeMaxHp = config.getInt("Tier3-MaxHp");
		tierFourMaxHp = config.getInt("Tier4-MaxHp");
		tierFiveMaxHp = config.getInt("Tier5-MaxHp");
		tierSixMaxHp = config.getInt("Tier6-MaxHp");
		tierSevenMaxHp = config.getInt("Tier7-MaxHp");
		tierEightMaxHp = config.getInt("Tier8-MaxHp");
		tierNineMaxHp = config.getInt("Tier9-MaxHp");
		tierTenMaxHp = config.getInt("Tier10-MaxHp");

		// Tier mana data
		defaultMaxMana = config.getInt("default-MaxHp");
		tierOneMaxMana = config.getInt("Tier1-MaxMana");
		tierTwoMaxMana = config.getInt("Tier2-MaxMana");
		tierThreeMaxMana = config.getInt("Tier3-MaxMana");
		tierFourMaxMana = config.getInt("Tier4-MaxMana");
		tierFiveMaxMana = config.getInt("Tier5-MaxMana");
		tierSixMaxMana = config.getInt("Tier6-MaxMana");
		tierSevenMaxMana = config.getInt("Tier7-MaxMana");
		tierEightMaxMana = config.getInt("Tier8-MaxMana");
		tierNineMaxMana = config.getInt("Tier9-MaxMana");
		tierTenMaxMana = config.getInt("Tier10-MaxMana");

		// Tier mana regen data
		defaultRegenMana = config.getInt("default-ManaRegen");
		tierOneRegenMana = config.getInt("Tier1-ManaRegen");
		tierTwoRegenMana = config.getInt("Tier2-ManaRegen");
		tierThreeRegenMana = config.getInt("Tier3-ManaRegen");
		tierFourRegenMana = config.getInt("Tier4-ManaRegen");
		tierFiveRegenMana = config.getInt("Tier5-ManaRegen");
		tierSixRegenMana = config.getInt("Tier6-ManaRegen");
		tierSevenRegenMana = config.getInt("Tier7-ManaRegen");
		tierEightRegenMana = config.getInt("Tier8-ManaRegen");
		tierNineRegenMana = config.getInt("Tier9-ManaRegen");
		tierTenRegenMana = config.getInt("Tier10-ManaRegen");

		// Determine what skill points we're using
		useMaxHpSkill = config.getBoolean("Use-MaxHp-Skill");
		useDmgSkill = config.getBoolean("Use-Dmg-Skill");
		useDefenseSkill = config.getBoolean("Use-Defense-Skill");
		useDodgeSkill = config.getBoolean("Use-Dodge-Skill");
		useCritSkill = config.getBoolean("Use-Crit-Skill");
		useMaxManaSkill = config.getBoolean("Use-MaxMana-Skill");
		useManaRegenSkill = config.getBoolean("Use-ManaRegen-Skill");

		// Max amount of skill points
		maxHpPoints = config.getInt("maxHp-Skill-Points");
		maxDmgPoints = config.getInt("maxDmg-Skill-Points");
		maxDefensePoints = config.getInt("maxDefense-Skill-Points");
		maxDodgePoints = config.getInt("maxDodge-Skill-Points");
		maxCritPoints = config.getInt("maxCrit-Skill-Points");
		maxManaPoints = config.getInt("maxMana-Skill-Points");
		maxManaRegenPoints = config.getInt("maxMana-Regen-Skill-Points");

		// Max buff amount from skills
		maxHp = config.getInt("maxHp");
		maxDmg = config.getInt("maxDmg");
		maxDefense = config.getInt("maxDefense");
		maxDodge = config.getInt("maxDodge");
		maxCrit = config.getInt("maxCrit");
		maxMana = config.getInt("maxMana");
		maxManaRegen = config.getInt("maxMana-Regen");

		// Menu Positions
		menuSize = config.getInt("menu-size");
		hpPosition = config.getInt("hp-position");
		dmgPosition = config.getInt("damage-position");
		defensePosition = config.getInt("defense-position");
		dodgePosition = config.getInt("dodge-position");
		critPosition = config.getInt("crit-position");
		manaPosition = config.getInt("max-mana-position");
		manaRegenPosition = config.getInt("mana-regen-position");
		if(menuSize % 9 != 0) { menuSize = 45; System.out.println("Menu Size has to be a multiple of 9!"); }
		
		// Menu Items
		hpItem = config.getItemStack("hp-item");
		damageItem = config.getItemStack("damage-item");
		defenseItem = config.getItemStack("defense-item");
		dodgeItem = config.getItemStack("dodge-item");
		critItem = config.getItemStack("crit-item");
		manaItem = config.getItemStack("mana-item");
		manaRegenItem = config.getItemStack("mana-regen-item");
		
		// Scoreboard Data
		scoreboardPacketSendPeriod = config.getInt("Seconds-Each-Scoreboard-Packet-Is-Sent");

		// Load all the data to tell us which scores we're using for the scoreboard
		ScoreboardData._UseScore0 = config.getBoolean("Scoreboard-Slot-0");
		ScoreboardData._UseScore1 = config.getBoolean("Scoreboard-Slot-1");
		ScoreboardData._UseScore2 = config.getBoolean("Scoreboard-Slot-2");
		ScoreboardData._UseScore3 = config.getBoolean("Scoreboard-Slot-3");
		ScoreboardData._UseScore4 = config.getBoolean("Scoreboard-Slot-4");
		ScoreboardData._UseScore5 = config.getBoolean("Scoreboard-Slot-5");
		ScoreboardData._UseScore6 = config.getBoolean("Scoreboard-Slot-6");
		ScoreboardData._UseScore7 = config.getBoolean("Scoreboard-Slot-7");
		ScoreboardData._UseScore8 = config.getBoolean("Scoreboard-Slot-8");
		ScoreboardData._UseScore9 = config.getBoolean("Scoreboard-Slot-9");
		ScoreboardData._UseScore10 = config.getBoolean("Scoreboard-Slot-10");
		ScoreboardData._UseScore11 = config.getBoolean("Scoreboard-Slot-11");
		ScoreboardData._UseScore12 = config.getBoolean("Scoreboard-Slot-12");
		ScoreboardData._UseScore13 = config.getBoolean("Scoreboard-Slot-13");
		ScoreboardData._UseScore14 = config.getBoolean("Scoreboard-Slot-14");
		ScoreboardData._UseScore15 = config.getBoolean("Scoreboard-Slot-15");
		
		/*
		 * Mob Damage
		 */
		zombieDamage = config.getDouble("zombie-damage");
		zombieVillagerDamage = config.getDouble("zombie-villager-damage");
		giantDamage = config.getDouble("gaint-damage");
		skeletonDamage = config.getDouble("skeleton-damage");
		spiderDamage = config.getDouble("spider-damage");
		caveSpiderDamage = config.getDouble("cave-spider-damage");
		endermanDamage = config.getDouble("enderman-damage");
		wolfDamage = config.getDouble("wolf-damage");
		blazeDamage = config.getDouble("blaze-damage");
		slimeDamage = config.getDouble("slime-damage");
		magmaCubeDamage = config.getDouble("magma-cube-damage");
		silverFishDamage = config.getDouble("silver-fish-damage");
		witherSkeletonDamage = config.getDouble("wither-skeleton-damage");
		guardianDamage = config.getDouble("guardian-damage");
		elderGuardianDamage = config.getDouble("elder-guardian-damage");
		polarBearDamage = config.getDouble("polar-bear-damage");
		vexDamage = config.getDouble("vex-damage");
		
		/*
		 * Mob Defense
		 */
		ocelotDefense = config.getDouble("ocelot-defense");
		horseDefense = config.getDouble("horse-defense");
		rabbitDefense = config.getDouble("rabbit-defense");
		sheepDefense = config.getDouble("sheep-defense");
		pigDefense = config.getDouble("pig-defense");
		chickenDefense = config.getDouble("chicken-defense");
		cowDefense = config.getDouble("cow-defense");
		mooshroomDefense = config.getDouble("mooshroom-defense");
		squidDefense = config.getDouble("squid-defense");
		batDefense = config.getDouble("bat-defense");
		villagerDefense = config.getDouble("villager-defense");
		zombieDefense = config.getDouble("zombie-defense");
		zombieVillagerDefense = config.getDouble("zombie-villager-defense");
		giantDefense = config.getDouble("giant-defense");
		zombiePigmanDefense = config.getDouble("zombie-pigman-defense");
		skeletonDefense = config.getDouble("skeleton-defense");
		spiderDefense = config.getDouble("spider-defense");
		caveSpiderDefense = config.getDouble("cave-spider-defense");
		creeperDefense = config.getDouble("creeper-defense");
		endermanDefense = config.getDouble("enderman-defense");
		wolfDefense = config.getDouble("wolf-defense");
		witchDefense = config.getDouble("witch-defense");
		blazeDefense = config.getDouble("blaze-defense");
		slimeDefense = config.getDouble("slime-defense");
		magmaCubeDefense = config.getDouble("magma-cube-defense");
		silverFishDefense = config.getDouble("silver-fish-defense");
		witherSkeletonDefense = config.getDouble("wither-skeleton-defense");
		witherDefense = config.getDouble("wither-defense");
		enderDragonDefense = config.getDouble("ender-dragon-defense");
		guardianDefense = config.getDouble("guardian-defense");
		elderGuardianDefense = config.getDouble("elder-guardian-defense");
		polarBearDefense = config.getDouble("polar-bear-defense");
		shulkerDefense = config.getDouble("shulker-defense");
		llamaDefense = config.getDouble("llama-defense");
		endermiteDefense = config.getDouble("endermite-defense");
		parrotDefense = config.getDouble("parrot-defense");
		vexDefense = config.getDouble("vex-defense");
		strayDefense = config.getDouble("stray-defense");
		evokerDefense = config.getDouble("evoker-defense");
		vindicatorDefense = config.getDouble("vindicator-defense");
		illusionerDefense = config.getDouble("illusioner-defense");
		
	}

	// Save the config file
	public void saveConfigData() {
		config.set("Save-Highest-Level", saveLevel);

		config.set("Increase-Tier-On-Level-Up", tiersIncreaseOnLevel);

		config.set("Enchanting-Cost-Money", enchantCostMoney);

		config.set("Enchant-Cost", enchantCost);

		// Tiers we're using
		config.set("Use-Tier1", useTierOne);
		config.set("Use-Tier2", useTierTwo);
		config.set("Use-Tier3", useTierThree);
		config.set("Use-Tier4", useTierFour);
		config.set("Use-Tier5", useTierFive);
		config.set("Use-Tier6", useTierSix);
		config.set("Use-Tier7", useTierSeven);
		config.set("Use-Tier8", useTierEight);
		config.set("Use-Tier9", useTierNine);
		config.set("Use-Tier10", useTierTen);

		// Tier1-Level
		config.set("Tier1-Level", tierOneLevel);
		config.set("Tier2-Level", tierTwoLevel);
		config.set("Tier3-Level", tierThreeLevel);
		config.set("Tier4-Level", tierFourLevel);
		config.set("Tier5-Level", tierFiveLevel);
		config.set("Tier6-Level", tierSixLevel);
		config.set("Tier7-Level", tierSevenLevel);
		config.set("Tier8-Level", tierEightLevel);
		config.set("Tier9-Level", tierNineLevel);
		config.set("Tier10-Level", tierTenLevel);

		// Tier max hp data
		config.set("default-MaxHp",   defaultMaxHp);
		config.set("Tier1-MaxHp",     tierOneMaxHp);
		config.set("Tier2-MaxHp",     tierTwoMaxHp);
		config.set("Tier3-MaxHp",     tierThreeMaxHp);
		config.set("Tier4-MaxHp",     tierFourMaxHp);
		config.set("Tier5-MaxHp",     tierFiveMaxHp);
		config.set("Tier6-MaxHp",     tierSixMaxHp);
		config.set("Tier7-MaxHp",     tierSevenMaxHp);
		config.set("Tier8-MaxHp",     tierEightMaxHp);
		config.set("Tier9-MaxHp",     tierNineMaxHp);
		config.set("Tier10-MaxHp",    tierTenMaxHp);

		// Tier max mana data
		config.set("default-MaxMana",   defaultMaxMana);
		config.set("Tier1-MaxMana",     tierOneMaxMana);
		config.set("Tier2-MaxMana",     tierTwoMaxMana);
		config.set("Tier3-MaxMana",     tierThreeMaxMana);
		config.set("Tier4-MaxMana",     tierFourMaxMana);
		config.set("Tier5-MaxMana",     tierFiveMaxMana);
		config.set("Tier6-MaxMana",     tierSixMaxMana);
		config.set("Tier7-MaxMana",     tierSevenMaxMana);
		config.set("Tier8-MaxMana",     tierEightMaxMana);
		config.set("Tier9-MaxMana",     tierNineMaxMana);
		config.set("Tier10-MaxMana",    tierTenMaxMana);

		// Tier mana regen data
		config.set("default-ManaRegen",   defaultRegenMana);
		config.set("Tier1-ManaRegen",     tierOneRegenMana);
		config.set("Tier2-ManaRegen",     tierTwoRegenMana);
		config.set("Tier3-ManaRegen",     tierThreeRegenMana);
		config.set("Tier4-ManaRegen",     tierFourRegenMana);
		config.set("Tier5-ManaRegen",     tierFiveRegenMana);
		config.set("Tier6-ManaRegen",     tierSixRegenMana);
		config.set("Tier7-ManaRegen",     tierSevenRegenMana);
		config.set("Tier8-ManaRegen",     tierEightRegenMana);
		config.set("Tier9-ManaRegen",     tierNineRegenMana);
		config.set("Tier10-ManaRegen",    tierTenRegenMana);

		// Determine what skills we're using
		config.set("Use-MaxHp-Skill", useMaxHpSkill);
		config.set("Use-Dmg-Skill", useDmgSkill);
		config.set("Use-Defense-Skill", useDefenseSkill);
		config.set("Use-Dodge-Skill", useDodgeSkill);
		config.set("Use-Crit-Skill", useCritSkill);
		config.set("Use-MaxMana-Skill", useMaxManaSkill);
		config.set("Use-ManaRegen-Skill", useManaRegenSkill);

		// Max amount of skill points
		config.set("maxHp-Skill-Points", maxHpPoints);
		config.set("maxDmg-Skill-Points", maxDmgPoints);
		config.set("maxDefense-Skill-Points", maxDefensePoints);
		config.set("maxDodge-Skill-Points", maxDodgePoints);
		config.set("maxCrit-Skill-Points", maxCritPoints);
		config.set("maxMana-Skill-Points", maxManaPoints);
		config.set("maxMana-Regen-Skill-Points", maxManaRegenPoints);

		// Max buff amount from skills
		config.set("maxHp", maxHp);
		config.set("maxDmg", maxDmg);
		config.set("maxDefense", maxDefense);
		config.set("maxDodge", maxDodge);
		config.set("maxCrit", maxCrit);
		config.set("maxMana", maxMana);
		config.set("maxMana-Regen", maxManaRegen);

		
		// Menu positions
		config.set("menu-size", menuSize);
		config.set("hp-position", hpPosition);
		config.set("damage-position", dmgPosition);
		config.set("defense-position", defensePosition);
		config.set("dodge-position", dodgePosition);
		config.set("crit-position", critPosition);
		config.set("max-mana-position", manaPosition);
		config.set("mana-regen-position", manaRegenPosition);
		
		// Menu Items
		config.set("hp-item", hpItem);
		config.set("damage-item", damageItem);
		config.set("defense-item", defenseItem);
		config.set("dodge-item", dodgeItem);
		config.set("crit-item", critItem);
		config.set("mana-item", manaItem);
		config.set("mana-regen-item", manaRegenItem);
		
		// Scoreboard data
		config.set("Seconds-Each-Scoreboard-Packet-Is-Sent", scoreboardPacketSendPeriod);

		// Set all the use score data
		config.set("Scoreboard-Slot-0", ScoreboardData._UseScore0);
		config.set("Scoreboard-Slot-1", ScoreboardData._UseScore1);
		config.set("Scoreboard-Slot-2", ScoreboardData._UseScore2);
		config.set("Scoreboard-Slot-3", ScoreboardData._UseScore3);
		config.set("Scoreboard-Slot-4", ScoreboardData._UseScore4);
		config.set("Scoreboard-Slot-5", ScoreboardData._UseScore5);
		config.set("Scoreboard-Slot-6", ScoreboardData._UseScore6);
		config.set("Scoreboard-Slot-7", ScoreboardData._UseScore7);
		config.set("Scoreboard-Slot-8", ScoreboardData._UseScore8);
		config.set("Scoreboard-Slot-9", ScoreboardData._UseScore9);
		config.set("Scoreboard-Slot-10", ScoreboardData._UseScore10);
		config.set("Scoreboard-Slot-11", ScoreboardData._UseScore11);
		config.set("Scoreboard-Slot-12", ScoreboardData._UseScore12);
		config.set("Scoreboard-Slot-13", ScoreboardData._UseScore13);
		config.set("Scoreboard-Slot-14", ScoreboardData._UseScore14);
		config.set("Scoreboard-Slot-15", ScoreboardData._UseScore15);

		/*
		 * Mob damage
		 */
		config.set("zombie-damage", zombieDamage);
		config.set("zombie-villager-damage", zombieVillagerDamage);
		config.set("gaint-damage", giantDamage);
		config.set("skeleton-damage", skeletonDamage);
		config.set("spider-damage", spiderDamage);
		config.set("cave-spider-damage", caveSpiderDamage);
		config.set("enderman-damage", endermanDamage);
		config.set("wolf-damage", wolfDamage);
		config.set("blaze-damage", blazeDamage);
		config.set("slime-damage", slimeDamage);
		config.set("magma-cube-damage", magmaCubeDamage);
		config.set("silver-fish-damage", silverFishDamage);
		config.set("wither-skeleton-damage", witherSkeletonDamage);
		config.set("guardian-damage", guardianDamage);
		config.set("elder-guardian-damage", elderGuardianDamage);
		config.set("polar-bear-damage", polarBearDamage);
		config.set("vex-damage", vexDamage);
		
		/*
		 * Mob defense
		 */
		
		config.set("ocelot-defense", ocelotDefense);
		config.set("horse-defense", horseDefense);
		config.set("rabbit-defense", rabbitDefense);
		config.set("sheep-defense", sheepDefense);
		config.set("pig-defense", pigDefense);
		config.set("chicken-defense", chickenDefense);
		config.set("cow-defense", cowDefense);
		config.set("mooshroom-defense", mooshroomDefense);
		config.set("squid-defense", squidDefense);
		config.set("bat-defense", batDefense);
		config.set("villager-defense", villagerDefense);
		config.set("zombie-defense", zombieDefense);
		config.set("zombie-villager-defense", zombieVillagerDefense);
		config.set("giant-defense", giantDefense);
		config.set("zombie-pigman-defense", zombiePigmanDefense);
		config.set("skeleton-defense", skeletonDefense);
		config.set("spider-defense", spiderDefense);
		config.set("cave-spider-defense", caveSpiderDefense);
		config.set("creeper-defense", creeperDefense);
		config.set("enderman-defense", endermanDefense);
		config.set("wolf-defense", wolfDefense);
		config.set("witch-defense", witchDefense);
		config.set("blaze-defense", blazeDefense);
		config.set("slime-defense", slimeDefense);
		config.set("magma-cube-defense", magmaCubeDefense);
		config.set("silver-fish-defense", silverFishDefense);
		config.set("wither-skeleton-defense", witherSkeletonDefense);
		config.set("wither-defense", witherDefense);
		config.set("ender-dragon-defense", enderDragonDefense);
		config.set("guardian-defense", guardianDefense);
		config.set("elder-guardian-defense", elderGuardianDefense);
		config.set("polar-bear-defense", polarBearDefense);
		config.set("shulker-defense", shulkerDefense);
		config.set("llama-defense", llamaDefense);
		config.set("endermite-defense", endermiteDefense);
		config.set("parrot-defense", parrotDefense);
		config.set("vex-defense", vexDefense);
		config.set("stray-defense", strayDefense);
		config.set("evoker-defense", evokerDefense);
		config.set("vindicator-defense", vindicatorDefense);
		config.set("illusioner-defense", illusionerDefense);
		
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

	// Check if the target is online
	public boolean targetIsOnline(String target) {
		for(Player p : Bukkit.getOnlinePlayers()) // Loop through the online players.
			if(p.getName().equalsIgnoreCase(target)) // Get the target's name
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
}

package zinnia.skills.player;

import java.text.DecimalFormat;

import org.bukkit.entity.Player;

import com.nisovin.magicspells.MagicSpells;

import zinnia.skills.main.Skills;

public class SkillPoints {

	static DecimalFormat hpFormat = new DecimalFormat("#.##");
	
	public int tierLevel;
	
	public int points; // Earned points that haven't been spent
	public int lastLevel; // Their latest level

	public int healthPoints; // Points put into health
	public int dmgPoints; // Points put into damage
	public int defensePoints; // Points put into defense

	public int dodgePoints; // Points put into dodge
	public int critPoints; // Points put into crit

	public int manaPoints; // Points put into mana
	public int manaRegenPoints; // Points put into mana regen

	public boolean usingTempPoints; // A boolean used to determine if the player is using temp points or not
	
	// Does health calculations to set the health and stuff
	@SuppressWarnings("deprecation")
	public void increaseHealth(Player player) {
		try {
			int skillMaxHp = healthPoints * Skills.maxHp / Skills.maxHpPoints;
			int totalHp = skillMaxHp + defaultMaxHp(player);

			player.setMaxHealth(totalHp); // Set the max health
			player.setHealthScaled(true); // Make it so the screen isn't filled with hearts	
		} catch(Exception e) { 
			System.out.println("\nError! Is max hp points 0? If so max hp points can't be 0!");
			player.setHealthScaled(true);
		}
	}
	
	// Sets the max mana
	public void setMaxMana(Player player) {
		MagicSpells.getManaHandler().setMaxMana(player, defaultMaxMana(player) + getMana());
	}

	// Sets the mana regen amount
	public void setManaRegen(Player player) {
		MagicSpells.getManaHandler().setRegenAmount(player, defaultManaRegen(player) + getManaRegen());
	}
	
	public double getHealth(Player player) {
		return healthPoints * Skills.maxHp / Skills.maxHpPoints;
	}

	// Get the damage to add on to the default damage of whatever damage is being dealt
	public double getDmg() {
		try {
			return dmgPoints * Skills.maxDmg / Skills.maxDmgPoints;
		} catch(Exception e) { 
			System.out.println("\nError! Is max dmg points 0? If so max dmg points can't be 0!");
			return 1;
		}
	}
	
	// Get the defense of a player
	public double getDefense() {
		try {
			return defensePoints * Skills.maxDefense / Skills.maxDefensePoints;
		} catch(Exception e) {
			System.out.println("\nError! Is max defense points 0? If so max defense points can't be 0!");
			return 1;
		}
	}

	// Gets the chance to dodge
	public boolean Dodge() {
		try {
			return chance(dodgePoints * Skills.maxDodge / Skills.maxDodgePoints);
		} catch(Exception e) { 
			System.out.println("\nError! Is max dodge points 0? If so max dodge points can't be 0!");
			return false;
		}
	}

	// Gets the chance to crit
	public boolean Crit() {
		try {
			return chance(critPoints * Skills.maxCrit / Skills.maxCritPoints);
		} catch(Exception e) { 
			System.out.println("\nError! Is max crit points 0? If so max crit points can't be 0!");
			return false;
		}
	}

	// Gets the mana increase
	public int getMana() {
		try {
			return manaPoints * Skills.maxMana / Skills.maxManaPoints;
		} catch(Exception e) { 
			System.out.println("\nError! Is max mana points 0? If so max mana points can't be 0!");
			return 1;
		}
	}

	// Gets the mana regen to give players
	public int getManaRegen() {
		try {
			return manaRegenPoints * Skills.maxManaRegen / Skills.maxManaRegenPoints;
		} catch(Exception e) { 
			System.out.println("\nError! Is max mana regen points 0? If so max mana regen points can't be 0!");
			return 1;
		}
	}

	// Gets the amount to increase dodge chance (current chance) x (max points) / [max points]
	public double getDodgeChange() {
		try {
			return dodgePoints * Skills.maxDodge / Skills.maxDodgePoints;
		} catch(Exception e) { 
			System.out.println("\nError! Is max dodge points 0? If so max dodge points can't be 0!");
			return 1;
		}
	}

	// Gets the amount to increase crit chance
	public double getCritChance() {
		try {
			return critPoints * Skills.maxCrit / Skills.maxCritPoints;
		} catch(Exception e) { 
			System.out.println("\nError! Is max crit points 0? If so max crit points can't be 0!");
			return 1;
		}
	}

	public boolean chance(double chance) { 
		double convertedChance = chance * 0.01; // Puts the chance into a decimal
		double randNum = Math.random(); // Get's a random number between 0.0 - 1.0
		if(chance <= 0) return false; // If the chance is less than zero return false(0% chance)
		else if (chance >= 100) return true; // If the chance is greater than 100 return true(100% chance)
		else return (randNum < convertedChance)?true:false; // Compare the random number and chance 
	}

	// Sets the default max hp based on tier
	private int defaultMaxHp(Player player) {
		if(player.hasPermission("skills.tier.one"))          return Skills.tierOneMaxHp;
		else if(player.hasPermission("skills.tier.two"))     return Skills.tierTwoMaxHp;
		else if(player.hasPermission("skills.tier.three"))   return Skills.tierThreeMaxHp;
		else if(player.hasPermission("skills.tier.four"))    return Skills.tierFourMaxHp;
		else if(player.hasPermission("skills.tier.five"))    return Skills.tierFiveMaxHp;
		else if(player.hasPermission("skills.tier.six"))     return Skills.tierSixMaxHp;
		else if(player.hasPermission("skills.tier.seven"))   return Skills.tierSevenMaxHp;
		else if(player.hasPermission("skills.tier.eight"))   return Skills.tierEightMaxHp;
		else if(player.hasPermission("skills.tier.nine"))    return Skills.tierNineMaxHp;
		else if(player.hasPermission("skills.tier.ten"))     return Skills.tierTenMaxHp;
		else                                                 return Skills.defaultMaxHp;
	}

	// Sets the default max hp based on tier
	private int defaultMaxMana(Player player) {
		if(player.hasPermission("skills.tier.one"))          return Skills.tierOneMaxMana;
		else if(player.hasPermission("skills.tier.two"))     return Skills.tierTwoMaxMana;
		else if(player.hasPermission("skills.tier.three"))   return Skills.tierThreeMaxMana;
		else if(player.hasPermission("skills.tier.four"))    return Skills.tierFourMaxMana;
		else if(player.hasPermission("skills.tier.five"))    return Skills.tierFiveMaxMana;
		else if(player.hasPermission("skills.tier.six"))     return Skills.tierSixMaxMana;
		else if(player.hasPermission("skills.tier.seven"))   return Skills.tierSevenMaxMana;
		else if(player.hasPermission("skills.tier.eight"))   return Skills.tierEightMaxMana;
		else if(player.hasPermission("skills.tier.nine"))    return Skills.tierNineMaxMana;
		else if(player.hasPermission("skills.tier.ten"))     return Skills.tierTenMaxMana;
		else                                                 return Skills.defaultMaxMana;
	}

	// Sets the default max hp based on tier
	private int defaultManaRegen(Player player) {
		if(player.hasPermission("skills.tier.one"))          return Skills.tierOneRegenMana;
		else if(player.hasPermission("skills.tier.two"))     return Skills.tierTwoRegenMana;
		else if(player.hasPermission("skills.tier.three"))   return Skills.tierThreeRegenMana;
		else if(player.hasPermission("skills.tier.four"))    return Skills.tierFourRegenMana;
		else if(player.hasPermission("skills.tier.five"))    return Skills.tierFiveRegenMana;
		else if(player.hasPermission("skills.tier.six"))     return Skills.tierSixRegenMana;
		else if(player.hasPermission("skills.tier.seven"))   return Skills.tierSevenRegenMana;
		else if(player.hasPermission("skills.tier.eight"))   return Skills.tierEightRegenMana;
		else if(player.hasPermission("skills.tier.nine"))    return Skills.tierNineRegenMana;
		else if(player.hasPermission("skills.tier.ten"))     return Skills.tierTenRegenMana;
		else                                                 return Skills.defaultRegenMana;
	}
}

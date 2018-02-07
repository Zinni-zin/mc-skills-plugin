package zinnia.skills.player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import zinnia.skills.utils.ScoreboardData;

/*
 * This class was made by Zinnia
 * This is done using reflection so if you don't understand don't edit!
 * The smallest mistake can throw an error so only edit if you can read the code and write your own reflection
 * The scoreboard does have a little bit of flicker but it's ight
 * If this is untouched and throws an error, it's most likely due to packet class names changing 
 * or essentials on a server reload but that can be ignored.
 * Reflection is only used to handle the package imports so it should be compatible with most version of spigot or bukkit
 * Unless of course any class names were changed as mentioned before
 */
public class SkillsScoreboard {

	//Skills plugin; // Variable to hold the plugin's main class

	Object sb; // Variable to hold the scoreboard object
	Object o; // Variable to hold the objective object
	Object bal; // Variable to hold the balance object to display the balance on the server
	
	/*
	 * Method to send the entire scoreboard
	 * Deprecation is because of economy getting the balance(moneys) via player name
	 * Deprecation means nothing so just ignore it
	 */
	public void sendScoreboardPackets(Player player) {
		try {
			/*
			 *  Can't say much packets are hidden via objectification so variable b is 
			 *  from scoreboard criteria. Get null is just saying that the variable is static
			 *  so we don't need get it from any object
			 */
			Object ScoreboardCriteria = getNMS("IScoreboardCriteria"). 
					getDeclaredField("b").get(null);

			// Set the scoreboard packet variable via a method created
			sb = packetScoreBoardConstructor().newInstance();

			// set o to an invoked register object(method created) below and pass in argument data
			o = registerObjective(sb).invoke(sb, ScoreboardData._Objective, ScoreboardCriteria);

			// set the display name of the scoreboard
			setDisplayName(o).invoke(o, ScoreboardData._DispalyName);

			// Object for removing the packet
			Object removePacket = objPlayOutScoreboardObjectiveConstructor().newInstance(o, 1);

			// Object for creating the packet
			Object createPacket = objPlayOutScoreboardObjectiveConstructor().newInstance(o, 0);

			// Object in charge of display the scoreboard
			Object display = packetPlayOutScoreboardDisplayObjective().newInstance(1, o);

			// Create and set ScoreboardScore variables for all the scores
			Object s0 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score0);
			Object s1 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score1);
			Object s2 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score2);
			Object s3 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score3);
			Object s4 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score4);
			Object s5 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score5);
			Object s6 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score6);
			Object s7 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score7);
			Object s8 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score8);
			Object s9 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score9);
			Object s10 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score10);
			Object s11 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score11);
			Object s12 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score12);
			Object s13 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score13);
			Object s14 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score14);
			Object s15 = ScoreboardScore().newInstance(sb, o, ScoreboardData._Score15);

			// use them to set the score on the scoreboard
			if(ScoreboardData._UseScore15) s15.getClass().getDeclaredMethod("setScore", int.class).invoke(s15, 15);
			if(ScoreboardData._UseScore14) s14.getClass().getDeclaredMethod("setScore", int.class).invoke(s14, 14);
			if(ScoreboardData._UseScore13) s13.getClass().getDeclaredMethod("setScore", int.class).invoke(s13, 14);
			if(ScoreboardData._UseScore12) s12.getClass().getDeclaredMethod("setScore", int.class).invoke(s12, 12);
			if(ScoreboardData._UseScore11) s11.getClass().getDeclaredMethod("setScore", int.class).invoke(s11, 11);
			if(ScoreboardData._UseScore10) s10.getClass().getDeclaredMethod("setScore", int.class).invoke(s10, 10);
			if(ScoreboardData._UseScore9) s9.getClass().getDeclaredMethod("setScore", int.class).invoke(s9, 9);
			if(ScoreboardData._UseScore8) s8.getClass().getDeclaredMethod("setScore", int.class).invoke(s8, 8);
			if(ScoreboardData._UseScore7) s7.getClass().getDeclaredMethod("setScore", int.class).invoke(s7, 7);
			if(ScoreboardData._UseScore6) s6.getClass().getDeclaredMethod("setScore", int.class).invoke(s6, 6);
			if(ScoreboardData._UseScore5) s5.getClass().getDeclaredMethod("setScore", int.class).invoke(s5, 5);
			if(ScoreboardData._UseScore4) s4.getClass().getDeclaredMethod("setScore", int.class).invoke(s4, 4);
			if(ScoreboardData._UseScore3) s3.getClass().getDeclaredMethod("setScore", int.class).invoke(s3, 3);
			if(ScoreboardData._UseScore2) s2.getClass().getDeclaredMethod("setScore", int.class).invoke(s2, 2);
			if(ScoreboardData._UseScore1) s1.getClass().getDeclaredMethod("setScore", int.class).invoke(s1, 1);
			if(ScoreboardData._UseScore0) s0.getClass().getDeclaredMethod("setScore", int.class).invoke(s0, 0);

			// Create variables to handle the packet player out stuff
			Object pScore15 = null;
			Object pScore14 = null;
			Object pScore13 = null;
			Object pScore12 = null;
			Object pScore11 = null;
			Object pScore10 = null;
			Object pScore9 = null;
			Object pScore8 = null;
			Object pScore7 = null;
			Object pScore6 = null;
			Object pScore5 = null;
			Object pScore4 = null;
			Object pScore3 = null;
			Object pScore2 = null;
			Object pScore1 = null;
			Object pScore0 = null;

			// Set the play out variables
			if(ScoreboardData._UseScore15) pScore15 = playOutConstructor(s15.getClass()).newInstance(s15);
			if(ScoreboardData._UseScore14) pScore14 = playOutConstructor(s14.getClass()).newInstance(s14);
			if(ScoreboardData._UseScore13) pScore13 = playOutConstructor(s13.getClass()).newInstance(s13);
			if(ScoreboardData._UseScore12) pScore12 = playOutConstructor(s12.getClass()).newInstance(s12);
			if(ScoreboardData._UseScore11) pScore11 = playOutConstructor(s11.getClass()).newInstance(s11);
			if(ScoreboardData._UseScore10) pScore10 = playOutConstructor(s10.getClass()).newInstance(s10);
			if(ScoreboardData._UseScore9) pScore9 = playOutConstructor(s9.getClass()).newInstance(s9);
			if(ScoreboardData._UseScore8) pScore8 = playOutConstructor(s8.getClass()).newInstance(s8);
			if(ScoreboardData._UseScore7) pScore7 = playOutConstructor(s7.getClass()).newInstance(s7);
			if(ScoreboardData._UseScore6) pScore6 = playOutConstructor(s6.getClass()).newInstance(s6);
			if(ScoreboardData._UseScore5) pScore5 = playOutConstructor(s5.getClass()).newInstance(s5);
			if(ScoreboardData._UseScore4) pScore4 = playOutConstructor(s4.getClass()).newInstance(s4);
			if(ScoreboardData._UseScore3) pScore3 = playOutConstructor(s3.getClass()).newInstance(s3);
			if(ScoreboardData._UseScore2) pScore2 = playOutConstructor(s2.getClass()).newInstance(s2);
			if(ScoreboardData._UseScore1) pScore1 = playOutConstructor(s1.getClass()).newInstance(s1);
			if(ScoreboardData._UseScore0) pScore0 = playOutConstructor(s0.getClass()).newInstance(s0);

			// Send all the packets
			sendPacket(player, removePacket);
			sendPacket(player, createPacket);
			sendPacket(player, display);

			if(ScoreboardData._UseScore15) sendPacket(player, pScore15);
			if(ScoreboardData._UseScore14) sendPacket(player, pScore14);
			if(ScoreboardData._UseScore13) sendPacket(player, pScore13);
			if(ScoreboardData._UseScore12) sendPacket(player, pScore12);
			if(ScoreboardData._UseScore11) sendPacket(player, pScore11);
			if(ScoreboardData._UseScore10) sendPacket(player, pScore10);
			if(ScoreboardData._UseScore9) sendPacket(player, pScore9);
			if(ScoreboardData._UseScore8) sendPacket(player, pScore8);
			if(ScoreboardData._UseScore7) sendPacket(player, pScore7);
			if(ScoreboardData._UseScore6) sendPacket(player, pScore6);
			if(ScoreboardData._UseScore5) sendPacket(player, pScore5);
			if(ScoreboardData._UseScore4) sendPacket(player, pScore4);
			if(ScoreboardData._UseScore3) sendPacket(player, pScore3);
			if(ScoreboardData._UseScore2) sendPacket(player, pScore2);
			if(ScoreboardData._UseScore1) sendPacket(player, pScore1);
			if(ScoreboardData._UseScore0) sendPacket(player, pScore0); 

		} catch(Exception e) { // Catch any error then print out the stack trace
			System.out.println("If this error is caused by essentials not being able "
					+ "to find a user on reload ignore it");
			e.printStackTrace(); 
		}
	}

	// Method to completely send the packet
	public void sendPacket(Player player, Object packet) {
		try {
			// Get the "getHandle" method from the craft player
			Object handler = player.getClass().getMethod("getHandle").invoke(player);

			// Use the handler to get the player connection field
			Object playerConnection = handler.getClass().getField("playerConnection").get(handler);

			// Use the player connection field to get and invoke the "sendPacket" method
			// Takes in the player connection and object
			playerConnection.getClass().getMethod("sendPacket",
					getNMS("Packet")).invoke(playerConnection, packet);
		} catch(Exception e) { e.printStackTrace(); }
	}

	// Method to get packet classes/NMS(Net Minecraft Server) classes
	public Class<?> getNMS(String name){
		// String that get's the version name
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		try {
			// Return a class that uses "net.minecract.server.[version].[name](Import & class)
			return Class.forName("net.minecraft.server." + version + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Function to return the constructor of the PacketPlayOutScoreboardScore object
	private Constructor<?> playOutConstructor(Class<?> obj) {
		try {
			return getNMS("PacketPlayOutScoreboardScore").getConstructor(obj);
		} catch(Exception e) { e.printStackTrace(); return null; }
	}

	// Packet scoreboard constructor
	private Constructor<?> packetScoreBoardConstructor() {
		try {
			return getNMS("Scoreboard").getConstructor();
		} catch(Exception e) { e.printStackTrace(); return null; }
	}

	// Packet play out scoreboard objective constructor
	private Constructor<?> objPlayOutScoreboardObjectiveConstructor() {
		try {
			return getNMS("PacketPlayOutScoreboardObjective").getConstructor(
					getNMS("ScoreboardObjective"), int.class);
		} catch(Exception e) { e.printStackTrace(); return null; }
	}

	// Scoreboard score constructor 
	private Constructor<?> ScoreboardScore(){
		try {
			return getNMS("ScoreboardScore").getConstructor(sb.getClass(), o.getClass(), String.class);
		} catch(Exception e) { e.printStackTrace(); return null; }
	}

	// The packet play out scoreboard display objective constructor
	private Constructor<?> packetPlayOutScoreboardDisplayObjective() {
		try {
			return getNMS("PacketPlayOutScoreboardDisplayObjective").getConstructor(
					int.class, getNMS("ScoreboardObjective"));
		} catch(Exception e) { e.printStackTrace(); return null; }
	}

	// Method to set the scoreboard's display name
	private Method setDisplayName(Object o) {
		try {
			return o.getClass().getDeclaredMethod("setDisplayName", String.class);
		} catch(Exception e) { e.printStackTrace(); return null; }
	}

	// Method for registering the scoreboard objective
	private Method registerObjective(Object sb) {
		try {
			return sb.getClass().getDeclaredMethod("registerObjective", String.class, 
					getNMS("IScoreboardCriteria"));
		} catch(Exception e) { e.printStackTrace(); return null; }
	}
}

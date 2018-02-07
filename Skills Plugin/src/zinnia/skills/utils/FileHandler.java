package zinnia.skills.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import zinnia.skills.main.Skills;

public class FileHandler {
	
	protected Skills plugin;
	public File file;
	public FileConfiguration config;
	
	public FileHandler(Skills plugin, String fileName) {
		this.plugin = plugin;
		this.file = new File(plugin.getDataFolder(), fileName);
		if(!file.exists())
			try{
				file.createNewFile();
			} catch(IOException e){
				e.printStackTrace();
			}
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void Save() {
		try{
			config.save(file);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}

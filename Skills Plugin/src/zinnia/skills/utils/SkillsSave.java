package zinnia.skills.utils;

import zinnia.skills.main.Skills;

public class SkillsSave extends FileHandler{
	
	public SkillsSave(Skills plugin) {
		super(plugin, "PlayerData.yml");
	}
}

package summer.skills.service;

import java.util.Map;

import summer.skills.model.SkillsModel;

public interface SkillsService {

	public Map<String,Object> getData(String creater);
	
	public Map<String,Object> save(SkillsModel skillsModel);
}

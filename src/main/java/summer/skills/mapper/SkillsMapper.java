package summer.skills.mapper;

import summer.skills.model.SkillsModel;

public interface SkillsMapper {

	public SkillsModel getData(String creater);
	
	public int selectOne(String id);
	
	public void update(SkillsModel skillsModel);
	
	public void insert(SkillsModel skillsModel);
}

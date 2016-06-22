package summer.experience.mapper;

import java.util.List;

import summer.experience.model.ProjectModel;

public interface ProjectMapper {

	public List<ProjectModel> getProjectList(String companyId);
	
	public void insert(ProjectModel projectModel);
	
	public void update(ProjectModel projectModel);
	
	public int selectOne(String id);
	
	public void delete(String id);
	
	public void deleteByCom(String companyId);
}

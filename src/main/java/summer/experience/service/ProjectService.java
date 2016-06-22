package summer.experience.service;

import java.util.List;
import java.util.Map;

import summer.experience.model.ProjectModel;

public interface ProjectService {

	public List<ProjectModel> getProjectList(String companyId);
	
	public Map<String,Object> save(ProjectModel projectModel);
	
	public Map<String,Object> delete(String id);
}

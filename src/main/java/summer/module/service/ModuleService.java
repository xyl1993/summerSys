package summer.module.service;

import java.util.Map;

import summer.module.model.ModuleModel;


public interface ModuleService {

	public Map<String,Object> getModuleList(Map<String,Object> paramsMap);
	
	public Map<String,Object> getModuleListByPid(String pid);
	
	public Map<String,Object> save(ModuleModel model);
	
	public Map<String,Object> delete(ModuleModel model);
}

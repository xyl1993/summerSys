package summer.me.service;

import java.util.Map;

import summer.me.model.MeModel;

public interface MeService {

	public Map<String,Object> getMeList(String token);
	
	public Map<String,Object> save(MeModel meModel);
	
	public Map<String,Object> delete(String id);
}

package summer.module.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import summer.module.model.ModuleModel;

public interface ModuleMapper {

	public List<ModuleModel> getModuleList(@Param("pid") String pid);

	public int selectOne(@Param("id") String id);
	
	public void insert(ModuleModel model);
	
	public void update(ModuleModel model);
	
	public void delete(String id);
}

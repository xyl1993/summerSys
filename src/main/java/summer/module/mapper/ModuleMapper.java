package summer.module.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import summer.module.model.ModuleModel;

public interface ModuleMapper {

	public List<ModuleModel> getModuleList(@Param("pid") String pid);
}

package summer.me.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import summer.me.model.MeModel;

public interface MeMapper {

	public List<MeModel> getMeList(Map<String,Object> paramsMap);
	
	public MeModel meModel(@Param("id") String id);
	
	public void insert(MeModel meModel);
	
	public void delete(@Param("id") String id);
	
	public void update(MeModel meModel);
	
	public int selectOne(@Param("id")String id);
}

package summer.experience.mapper;

import java.util.List;
import java.util.Map;

import summer.experience.model.CompanyModel;

public interface CompanyMapper {

	public List<CompanyModel> getCompanyList(Map<String,Object> paramsMap);
	
	public int getCompanyCount(String creater);
	
	public int selectOne(String id);
	
	public void insert(CompanyModel companyModel);
	
	public void update(CompanyModel companyModel);
	
	public void delete(String id);
}

package summer.experience.service;

import java.util.List;
import java.util.Map;

import summer.experience.model.CompanyModel;

public interface CompanyService {

	public Map<String,Object> getCompanyList(Map<String,Object> paramsMap);
	
	public Map<String,Object> save(CompanyModel companyModel);
	
	public Map<String,Object> delete(String id);
}

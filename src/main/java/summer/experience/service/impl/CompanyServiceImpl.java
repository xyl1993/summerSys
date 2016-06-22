package summer.experience.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import summer.base.mapper.CommonMapper;
import summer.base.util.ConstDefine;
import summer.base.util.Pagination;
import summer.core.constant.BusinessError;
import summer.experience.mapper.CompanyMapper;
import summer.experience.mapper.ProjectMapper;
import summer.experience.model.CompanyModel;
import summer.experience.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	protected static Logger logger = Logger.getLogger("CompanyService");// 异常信息
	
	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private ProjectMapper projectMapper;
	
	@Autowired
	private CommonMapper commonMapper;
	@Override
	public Map<String,Object> getCompanyList(Map<String,Object> paramsMap) {
		//当前页  
        int intPage = (int) paramsMap.get("page");
        //每页显示条数  
        int number = (int) paramsMap.get("rows"); 
        //每页的开始记录  第一页为1  第二页为number +1   
        int start = (intPage-1)*number;  
        paramsMap.put("start", start);
		Map<String, Object> respMap = new HashMap<String, Object>();
		List<CompanyModel> companyList = new ArrayList<CompanyModel>();
		try{
			companyList = companyMapper.getCompanyList(paramsMap);
			Pagination<CompanyModel> pagination = new Pagination<CompanyModel>();
			pagination.setCurrentPage(intPage);
			pagination.setList(companyList);
			pagination.setPageSize(number);
			int recordCount = companyMapper.getCompanyCount((String) paramsMap.get("creater"));
			pagination.setRecordCount(recordCount);
			pagination.setPageCount(recordCount / (int) paramsMap.get("rows")
					+ (recordCount % (int) paramsMap.get("rows") == 0 ? 0 : 1));
			respMap.put("rows", companyList);
			respMap.put("total", recordCount);
		}catch(Exception e){
			companyList = null;
			logger.error(e.getMessage());
		}
		return respMap;
	}

	@Override
	public Map<String, Object> save(CompanyModel companyModel) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		try{
			int count = companyMapper.selectOne(companyModel.getId());
			companyModel.setModifierTime(commonMapper.getDbDate());
			if(count>0){
				companyMapper.update(companyModel);
			}else{
				companyModel.setCreaterTime(commonMapper.getDbDate());
				companyMapper.insert(companyModel);
			}
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, true);
		}catch(Exception e){
			logger.error(e.getMessage());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
			respMap.put(ConstDefine.CONST_MESSAGE_KEY, BusinessError.SERVELT_ERROR);
		}
		return respMap;
	}

	@Override
	public Map<String, Object> delete(String id) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		try{
			companyMapper.delete(id);
			projectMapper.deleteByCom(id);
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, true);
		}catch(Exception e){
			logger.error(e.getMessage());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
			respMap.put(ConstDefine.CONST_MESSAGE_KEY, BusinessError.SERVELT_ERROR);
		}
		return respMap;
	}
}

package summer.experience.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import summer.base.mapper.CommonMapper;
import summer.base.util.ConstDefine;
import summer.core.constant.BusinessError;
import summer.experience.mapper.ProjectMapper;
import summer.experience.model.ProjectModel;
import summer.experience.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

protected static Logger logger = Logger.getLogger("CompanyService");// 异常信息
	
	@Autowired
	private ProjectMapper projectMapper;

	@Autowired
	private CommonMapper commonMapper;
	
	@Override
	public List<ProjectModel> getProjectList(String companyId) {
		List<ProjectModel> projectList = null;
		try{
			projectList = projectMapper.getProjectList(companyId);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return projectList;
	}

	@Override
	public Map<String, Object> save(ProjectModel projectModel) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		try{
			int count = projectMapper.selectOne(projectModel.getId());
			if(count>0){
				projectMapper.update(projectModel);
			}else{
				projectMapper.insert(projectModel);
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
			projectMapper.delete(id);
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, true);
		}catch(Exception e){
			logger.error(e.getMessage());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
			respMap.put(ConstDefine.CONST_MESSAGE_KEY, BusinessError.SERVELT_ERROR);
		}
		return respMap;
	}

}

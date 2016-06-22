package summer.skills.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import summer.base.mapper.CommonMapper;
import summer.base.util.ConstDefine;
import summer.core.constant.BusinessError;
import summer.skills.mapper.SkillsMapper;
import summer.skills.model.SkillsModel;
import summer.skills.service.SkillsService;

@Service
public class SkillsServiceImpl implements SkillsService {

	protected static Logger logger = Logger.getLogger("Skills");// 异常信息
	@Autowired
	private SkillsMapper skillsMapper;
	@Autowired
	private CommonMapper commonMapper;
	@Override
	public Map<String, Object> getData(String creater) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		try{
			SkillsModel skillsModel = skillsMapper.getData(creater);
			respMap.put(ConstDefine.CONST_DATA_KEY, skillsModel);
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, true);
		}catch(Exception e){
			logger.error(e.getMessage());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
			respMap.put(ConstDefine.CONST_MESSAGE_KEY, BusinessError.SERVELT_ERROR);
		}
		return respMap;
	}

	@Override
	public Map<String, Object> save(SkillsModel skillsModel) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		try{
			int count =skillsMapper.selectOne(skillsModel.getId()); 
			if(count>0){
				skillsMapper.update(skillsModel);
			}else{
				skillsModel.setCreaterTime(commonMapper.getDbDate());
				skillsMapper.insert(skillsModel);
			}
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, true);
		}catch(Exception e){
			logger.error(e.getMessage());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
			respMap.put(ConstDefine.CONST_MESSAGE_KEY, BusinessError.SERVELT_ERROR);
		}
		return respMap;
	}

}

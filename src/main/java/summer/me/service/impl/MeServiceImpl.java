package summer.me.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import summer.base.util.ConstDefine;
import summer.core.constant.BusinessError;
import summer.me.mapper.MeMapper;
import summer.me.model.MeModel;
import summer.me.service.MeService;

@Service
public class MeServiceImpl implements MeService {
	protected static Logger logger = Logger.getLogger("MeService");// 异常信息
	
	@Autowired
	private MeMapper meMapper;
	
	@Override
	public Map<String, Object> getMeList(String token) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("token", token);
		try{
			List<MeModel> meModelList = meMapper.getMeList(paramsMap);
			respMap.put(ConstDefine.CONST_DATA_KEY, meModelList);
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, true);
		}catch(Exception e){
			logger.error(e.getMessage());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
			respMap.put(ConstDefine.CONST_MESSAGE_KEY, BusinessError.SERVELT_ERROR);
		}
		return respMap;
	}

	@Override
	public Map<String, Object> save(MeModel meModel) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		try{
			int count = meMapper.selectOne(meModel.getId());
			if(count>0){
				meMapper.update(meModel);
			}else{
				meMapper.insert(meModel);
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
			meMapper.delete(id);
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, true);
		}catch(Exception e){
			logger.error(e.getMessage());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
			respMap.put(ConstDefine.CONST_MESSAGE_KEY, BusinessError.SERVELT_ERROR);
		}
		return respMap;
	}

}

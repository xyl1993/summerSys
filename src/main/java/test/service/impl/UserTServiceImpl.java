package test.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.mapper.UserTMapper;
import test.model.UserTModel;
import test.service.UserTService;

@Service
public class UserTServiceImpl implements UserTService {

	@Autowired
	private UserTMapper userTMapper;
	
	@Override
	public List<UserTModel> getUserTList(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		return userTMapper.getUserTList(paramsMap);
	}

}

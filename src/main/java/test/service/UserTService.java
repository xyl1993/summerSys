package test.service;

import java.util.List;
import java.util.Map;

import test.model.UserTModel;

public interface UserTService {

	public List<UserTModel> getUserTList(Map<String,Object> paramsMap);
}

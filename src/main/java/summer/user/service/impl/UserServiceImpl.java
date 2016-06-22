package summer.user.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import summer.base.mapper.CommonMapper;
import summer.base.util.BASE64Encoder;
import summer.base.util.ConstDefine;
import summer.core.constant.BusinessError;
import summer.me.model.MeModel;
import summer.user.mapper.UserMapper;
import summer.user.model.UserModel;
import summer.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	protected static Logger logger = Logger.getLogger("UserService");// 异常信息
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CommonMapper commonMapper;
	@Override
	public Map<String, Object> save(UserModel userModel) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		userModel.setCreater("admin");
		userModel.setCreaterTime(commonMapper.getDbDate());
		userModel.setModifier("admin");
		userModel.setModifierTime(commonMapper.getDbDate());
		userModel.setStatus(0);
		try{
			int count = userMapper.selectOne(userModel.getUserName());
			if(count>0){
				respMap.put(ConstDefine.CONST_MESSAGE_KEY, BusinessError.USER_HAS_REGIST);
				respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
			}else{
				userMapper.insert(userModel);
				respMap.put(ConstDefine.CONST_SUCCESS_KEY, true);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
			respMap.put(ConstDefine.CONST_MESSAGE_KEY, BusinessError.SERVELT_ERROR);
		}
		return respMap;
	}
	@Override
	public Map<String, Object> login(UserModel model) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		String userName = model.getUserName();
		String password = model.getPassword();
		try{
			UserModel userModel = userMapper.login(userName, password);
			if(userModel!=null){
				// 创建 当前时间 Calendar 对象
				Calendar calendarCurrent = Calendar.getInstance();
				// 初始化 Calendar 对象，但并不必要，除非需要重置时间
				calendarCurrent.setTime(new Date());
				String token = userModel.getToken();
				Date lastLoginTime = userModel.getLastLoginTime();
				if (token == null || ("").equals(token)) {
					token = BASE64Encoder.getToken(userModel
							.getId());
				}
				if(lastLoginTime!=null){
					// 创建 发送验证码时间 Calendar 对象
					Calendar calendarLogin = Calendar.getInstance();
					calendarLogin.setTime(lastLoginTime);
					// 判断验证使用过或者验证码时间超过（默认10天）
					calendarLogin.add(Calendar.DAY_OF_MONTH, 10);
					if (calendarCurrent.compareTo(calendarLogin) != -1) {
						token = BASE64Encoder.getToken(userModel
								.getId());
					}
				}else{
					token = BASE64Encoder.getToken(userModel
							.getId());
				}
				userModel.setToken(token);
				userModel.setLastLoginTime(lastLoginTime);
				userModel.setStatus(1);
				userMapper.update(userModel);
				
				respMap.put(ConstDefine.CONST_DATA_KEY, token);
				respMap.put(ConstDefine.CONST_SUCCESS_KEY, true);
			}else{
				respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
				respMap.put(ConstDefine.CONST_MESSAGE_KEY, BusinessError.NONEXISTENT_USER);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
			respMap.put(ConstDefine.CONST_MESSAGE_KEY, BusinessError.SERVELT_ERROR);
		}
		return respMap;
	}
	@Override
	public Map<String, Object> loginOut(String token) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		try{
			userMapper.loginOut(token, 0);
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, true);
		}catch(Exception e){
			logger.error(e.getMessage());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
			respMap.put(ConstDefine.CONST_MESSAGE_KEY, BusinessError.SERVELT_ERROR);
		}
		return respMap;
	}

}

package summer.user.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import summer.user.model.UserModel;

public interface UserService {

	public Map<String, Object> save(UserModel userModel);
	
	public Map<String,Object> login(UserModel userModel);
	
	public Map<String,Object> loginOut(String token);
}

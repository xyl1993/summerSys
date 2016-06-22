package summer.user.mapper;

import org.apache.ibatis.annotations.Param;

import summer.me.model.MeModel;
import summer.user.model.UserModel;

public interface UserMapper {

	public void insert(UserModel userModel);
	
	public int selectOne(String userName);
	
	public UserModel login(@Param("userName") String userName,@Param("password") String password);
	
	public void update(UserModel userModel);
	
	public void loginOut(@Param("token") String token,@Param("status") int status);
}

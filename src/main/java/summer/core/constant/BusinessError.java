package summer.core.constant;


/**
 * 业务及错误
 * @author xyl
 *
 */
public interface BusinessError {

	/**
	 * 用户不存在
	 */
	String NONEXISTENT_USER = "用户名不存在";
	/**
	 * 用户未完善资料
	 */
	String USERINFO_NO = "用户未完善资料";
	/**
	 * 用户未设置头像
	 */
	String USERINFO_NOPRO = "用户未设置头像";
	/**
	 * 错误的用户名或密码
	 */
	String WRONG_USR_OR_PWD = "用户名或密码错误";

	/**
	 * 用户已注册
	 */
	String USER_HAS_REGIST = "用户已注册";

	/**
	 * 用户未登陆
	 */
	String USER_NOT_LOGIN = "用户未登陆";

	/**
	 * 图片太大
	 */
	String TOO_LARGE_IMAGE = "图片太大";

	/**
	 * 图片格式不正确
	 */
	String IMAGE_FORMAT_ERROR = "图片格式不正确";
	/**
	 * 图片超出上传个数
	 */
	String IMAGE_NUMBER_ERROR = "图片超出上传个数";
	/**
	 * 电子邮箱不正确
	 */
	String EMAIL_ERROR = "电子邮箱不正确";
	
	/**
	 * 服务器状态异常
	 */
	String SERVELT_ERROR = "服务器状态异常";
}

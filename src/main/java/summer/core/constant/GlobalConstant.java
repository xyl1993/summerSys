package summer.core.constant;

import java.io.File;

/**
 * 全局常量
 * 
 * @author 丁建军
 * 
 * @date Feb 5, 2015 5:21:35 PM
 * 
 * @version 0.1
 */
public interface GlobalConstant {
	
	/**
	 * 类路径
	 */
	String CLASS_PATH = GlobalHandler.getWebRootPath() + "classes";

	/**
	 * 文件服务器
	 */
	String GEN_SERVER = GlobalHandler.getResourceValue("gen.server");

	/**
	 * 文件生成目录
	 */
	String GEN_DIR = GlobalHandler.getWebRootPath()
			+ GlobalHandler.getResourceValue("gen.dir");

	/**
	 * 文章生成目录
	 */
	String GEN_ARTICLE = GEN_DIR + File.separator
			+ GlobalHandler.getResourceValue("gen.article");

	/**
	 * 文件上传路径
	 */
	String UPLOAD_PATH = GlobalHandler.getWebRootPath()
			+ GlobalHandler.getResourceValue("upload.path");

	/**
	 * 图片路径目录
	 */
	String UPLOAD_IMAGE = UPLOAD_PATH + File.separator
			+ GlobalHandler.getResourceValue("upload.image");

	/**
	 * 服务器地址
	 */
	String UPLOAD_SERVER = GlobalHandler.getResourceValue("upload.server");
	
	/**
	 * 服务器域名
	 */
	String UPLOAD_SERVER_AREANAME =  GlobalHandler.getResourceValue("upload.server.areaName");
	/**
	 * 默认页面数据条数
	 */
	int DEFAULT_PAGE_SIZE = Integer.parseInt(GlobalHandler
			.getResourceValue("pagesize.default"));

	/**
	 * 默认摇昵称间隔时间(小时)
	 */
	int DEFAULT_SHAKE_INTERVAL = Integer.parseInt(GlobalHandler
			.getResourceValue("shakeinterval.default"));

	/**
	 * 可上出图片的后缀
	 */
	String IMAGE_EXTENSIONS = GlobalHandler
			.getResourceValue("image.extensions");
	/**
	 * JSON响应内容类型
	 */
	String JSON_CONTENT_TYPE = "application/json;charset=utf-8";
	/**
	 * 可上传图片大小
	 */
	int IMAGE_SIZE = Integer.parseInt(GlobalHandler
			.getResourceValue("image.size"));
	/**
	 * 成功
	 */
	String SUCCESS = "success";

	/**
	 * 数据
	 */
	String DATA = "data";

	/**
	 * ID
	 */
	String ID = "id";

	/**
	 * 错误码
	 */
	String ERROR_CODE = "errorCode";

	/**
	 * 错误信息
	 */
	String ERROR_MSG = "errorMessage";

	/**
	 * 验证信息
	 */
	String CHECKED_MSG = "checkedMessage";

	/**
	 * CODE
	 */
	String CODE = "code";

	/**
	 * 成功码
	 */
	int SUCCESS_CODE = 0;

	/**
	 * 字段名
	 */
	String FIELD_NAME = "fieldName";

	String headImgDefault = GlobalHandler
			.getResourceValue("ccic.hearImg.default.url");
	/**
	 * 发送短信地址
	 */
	String sendMsgUrl = GlobalHandler.getResourceValue("ccic.sendMsg.url");
	/**
	 * 发送短信注册的企业号
	 */
	String sendMsgComid = GlobalHandler.getResourceValue("ccic.sendMsg.comid");
	/**
	 * 发送短信注册的用户名
	 */
	String sendMsgUsername = GlobalHandler
			.getResourceValue("ccic.sendMsg.username");
	/**
	 * 发送短信注册的密码
	 */
	String sendMsgUserpwd = GlobalHandler
			.getResourceValue("ccic.sendMsg.userpwd");
	/**
	 * 发送短信平台号
	 */
	String sendMsgSmsnumber = GlobalHandler
			.getResourceValue("ccic.sendMsg.smsnumber");
	/**
	 * 短信发送成功的码
	 */
	String sendMsgSuccessCode = GlobalHandler
			.getResourceValue("ccic.sendMsg.successCode");
	
	int ccicXzs = Integer.parseInt(GlobalHandler.getResourceValue("ccic.xzs.id"));
	
	/**
	 * 可上出附件的后缀
	 */
	String ATTCAHMENT_EXTENSIONS = GlobalHandler.getResourceValue("attachment.extensions");
	/**
	 * 可上传附件大小
	 */
	int ATTCAHMENT_SIZE = Integer.parseInt(GlobalHandler
			.getResourceValue("attachment.size"));
	/**
	 * 图片路径目录
	 */
	String UPLOAD_ATTCAHMENT = UPLOAD_PATH + File.separator
			+ GlobalHandler.getResourceValue("upload.attachment");
	
	String SMTP_URL = GlobalHandler.getResourceValue("smtp.url");
	
	String SMTP_USER = GlobalHandler.getResourceValue("smtp.user");
	
	String SMTP_PASSWORD = GlobalHandler.getResourceValue("smtp.password");
	
	String SERVER_PORT = GlobalHandler.getResourceValue("gen.server.port");
}

package summer.core.constant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;

/**
 * 全局资源处理器
 * 
 * @author 丁建军
 * 
 * @date Feb 5, 2015 5:22:08 PM
 * 
 * @version 0.1
 */
public class GlobalHandler {
	
	/**
	 * 应用服务器上下文
	 */
	public static ServletContext servletContext;

	// 全局资源文件名
	private final static String FILE_NAME = "global.properties";

	/**
	 * 全局资源
	 */
	public final static Properties GLOBAL_RESOURCE = new Properties();

	static {
		try {
			GLOBAL_RESOURCE.load(new FileInputStream(GlobalHandler.class
					.getResource(FILE_NAME).getFile()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得资源值
	 * 
	 * @param key
	 *            资源键
	 * @return
	 */
	public static String getResourceValue(String key) {
		return GLOBAL_RESOURCE.getProperty(key);
	}

	/**
	 * 获得WEB根路径
	 * 
	 * @return
	 */
	public static String getWebRootPath() {
		if (servletContext != null) {
			return servletContext.getRealPath("/");
		}
		return System.getProperty("user.dir");
	}
}

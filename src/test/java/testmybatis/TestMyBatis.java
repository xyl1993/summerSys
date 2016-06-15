package testmybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import summer.module.service.ModuleService;
import test.model.UserTModel;
import test.service.UserTService;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})  
public class TestMyBatis {

	 private static Logger logger = Logger.getLogger(TestMyBatis.class);  
	//  private ApplicationContext ac = null;  
	    @Resource  
	    private UserTService userService = null; 
	    
	    @Resource  
	    private ModuleService moduleService = null; 
	  
	//  @Before  
	//  public void before() {  
//	      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//	      userService = (IUserService) ac.getBean("userService");  
	//  }  
	  
	    @Test  
	    public void test1() {  
	    	Map<String, Object> paramsMap = new HashMap<String, Object>();
	    	List<UserTModel> list = userService.getUserTList(paramsMap);
	        // System.out.println(user.getUserName());  
	        // logger.info("值："+user.getUserName());  
	        logger.info(JSON.toJSONString(list.get(0)));  
	    }  
	    
	    @Test  
	    public void test2() {  
	    	Map<String, Object> paramsMap = new HashMap<String, Object>();
	    	paramsMap.put("id", "0");
	    	Map<String,Object> respMap = moduleService.getModuleList(paramsMap);
	        // System.out.println(user.getUserName());  
	        // logger.info("值："+user.getUserName());  
	        logger.info(JSON.toJSONString(respMap.get("data")));  
	    }  
}

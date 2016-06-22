package testmybatis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import summer.experience.model.CompanyModel;
import summer.experience.service.CompanyService;
import summer.me.model.MeModel;
import summer.me.service.MeService;
import summer.module.service.ModuleService;
import summer.user.model.UserModel;
import summer.user.service.UserService;
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
	    private UserService userService2 = null; 
	    
	    @Resource
	    private MeService meService = null;
	    
	    @Resource  
	    private ModuleService moduleService = null; 
	    
	    @Resource
	    private CompanyService companyService = null;
	  
	//  @Before  
	//  public void before() {  
//	      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
//	      userService = (IUserService) ac.getBean("userService");  
	//  }  
	  
//	    @Test  
//	    public void test1() {  
//	    	Map<String, Object> paramsMap = new HashMap<String, Object>();
//	    	List<UserTModel> list = userService.getUserTList(paramsMap);
//	        // System.out.println(user.getUserName());  
//	        // logger.info("值："+user.getUserName());  
//	        logger.info(JSON.toJSONString(list.get(0)));  
//	    }  
	    
//	    @Test  
//	    public void test2() {  
//	    	Map<String, Object> paramsMap = new HashMap<String, Object>();
//	    	paramsMap.put("id", "0");
//	    	Map<String,Object> respMap = moduleService.getModuleList(paramsMap);
//	        logger.info(JSON.toJSONString(respMap.get("data")));  
//	    }  
	    
//	    @Test  
//	    public void test3() {  
//	    	Map<String, Object> paramsMap = new HashMap<String, Object>();
//	    	paramsMap.put("id", "0");
//	    	Map<String,Object> respMap = moduleService.getModuleListByPid("0");
//	        logger.info(JSON.toJSONString(respMap.get("data")));  
//	    }
	    
	    //me service test start
//	    @Test  
//	    public void testMe() {  
//	    	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//	    	MeModel meModel = new MeModel();
//	    	meModel.setId("1234567788");
//	    	meModel.setName("测试");
//	    	try {
//				meModel.setBirthday(format1.parse("2015-6-7"));
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	    	meModel.setCity("南通");
//	    	meModel.setTel("18306271627");
//	    	meModel.setEmail("602165057@qq.com");
//	    	meModel.setBlog("https://nodeblog-xylblog.rhcloud.com/");
//	    	meModel.setDescription("性格开朗，善于沟通;较强的逻辑思维和自学能力;对待工作认真、负责;具有强烈责任感;");
//	    	meService.save(meModel);
//	    	Map<String,Object> respMap = meService.getMeList();
//	        logger.info(JSON.toJSONString(respMap.get("data")));  
//	        meService.delete(meModel.getId());
//	    }
	  //me service test end
//	  @Test
//	  public void testLogin(){
//		  UserModel userModel = new UserModel();
//		  userModel.setUserName("111");
//		  userModel.setPassword("96e79218965eb72c92a549dda330112");
//		  Map<String,Object> respMap = userService2.login(userModel);
//		  logger.info(JSON.toJSONString(respMap.get("message")));  
//	  }
	  @Test
	  public void testCompany(){
		  CompanyModel companyModel = new CompanyModel();
		  List<CompanyModel> companyList = null;
		  companyModel.setId("asdfsdgsg122w34");
		  companyModel.setStartTime("2014-9");
		  companyModel.setEndTime("至今");
		  companyModel.setName("如云");
		  companyModel.setNature("私营.民营企业");
		  companyModel.setSize("100人");
		  companyModel.setIndustry("计算机");
		  companyModel.setAddress("南通如皋");
		  companyModel.setDuty("erp项目负责前台extjs编写，后台java编写;社交项目主要负责前台页面布局，js编码，部分java台编码工作;");
		  companyModel.setRemarks("备注");
		  Map<String,Object> respMap = companyService.save(companyModel);
		  logger.info(JSON.toJSONString(respMap.get("data")));  
		  companyList = companyService.getCompanyList("op_user395778148538308");
		  logger.info(JSON.toJSONString(companyList));  
		  respMap = companyService.delete("asdfsdgsg122w34");
		  logger.info(JSON.toJSONString(respMap.get("data")));  
	  }
}

package test.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import summer.base.util.DateEditor;
import test.model.UserTModel;
import test.service.UserTService;

@Controller
@RequestMapping("test")
public class UserTController {

	protected static Logger logger = Logger.getLogger("testController");// 异常信息
	
	@Autowired
	private UserTService userTService;
	
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String,Object>getZjhbList(HttpServletRequest request,HttpServletResponse response){
		// 存放返回数据对象
		Map<String, Object> respMap = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		try{
			List<UserTModel> list = userTService.getUserTList(paramsMap);
			respMap.put("data", list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return respMap;
	}
	
	/**
	 * initBinder
	 * 
	 * @param request
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
}

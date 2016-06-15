package summer.module.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import summer.base.util.ConstDefine;
import summer.module.model.TreeModule;
import summer.module.service.ModuleService;

/**
 * 生成菜单
 * @author xyl
 *
 */
@Controller
@RequestMapping("module")
public class ModuleController {

	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping("/getModule")
	@ResponseBody
	public Map<String,Object>getZjhbList(HttpServletRequest request,HttpServletResponse response){
		// 存放返回数据对象
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		paramsMap.put("id", id);
		return moduleService.getModuleList(paramsMap);
	}
}

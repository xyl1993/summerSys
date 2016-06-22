package summer.module.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import summer.base.util.ConstDefine;
import summer.base.util.DateEditor;
import summer.module.model.ModuleModel;
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
	public Map<String,Object>getModule(HttpServletRequest request,HttpServletResponse response){
		// 存放返回数据对象
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String needRoot = request.getParameter("needRoot");
		paramsMap.put("id", id);
		paramsMap.put("needRoot", needRoot);
		return moduleService.getModuleList(paramsMap);
	}
	
	@RequestMapping("/getModuleListByPid")
	@ResponseBody
	public Map<String,Object>getModuleListByPid(HttpServletRequest request,HttpServletResponse response){
		// 存放返回数据对象
		String pid="-1";
		String parentId = request.getParameter("pid");
		if(parentId!=null && !parentId.equals("")){
			pid=parentId;
		}
		return moduleService.getModuleListByPid(pid);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object>save(@RequestBody ModuleModel model,HttpServletRequest request,HttpServletResponse response){
		return moduleService.save(model);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object>delete(@RequestBody ModuleModel model,HttpServletRequest request,HttpServletResponse response){
		return moduleService.delete(model);
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

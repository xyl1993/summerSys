package summer.me.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import summer.base.util.DateEditor;
import summer.me.model.MeModel;
import summer.me.service.MeService;

@Controller
@RequestMapping("me")
public class MeController {

	@Autowired
	private MeService meService;
	
	@RequestMapping("/getMeList")
	@ResponseBody
	public Map<String,Object>getMeList(HttpServletRequest request,HttpServletResponse response){
		String token  = request.getHeader("token");
		return meService.getMeList(token);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object>save(@Valid @RequestBody MeModel meModel, HttpServletRequest request,HttpServletResponse response){
		return meService.save(meModel);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object>delete(HttpServletRequest request,HttpServletResponse response){
		return meService.delete(request.getParameter("id"));
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

package summer.skills.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import summer.skills.model.SkillsModel;
import summer.skills.service.SkillsService;

@Controller
@RequestMapping("skills")
public class SkillsController {

	@Autowired
	private SkillsService skillsService;
	
	@RequestMapping("/getData")
	@ResponseBody
	public Map<String,Object>getData(HttpServletRequest request,HttpServletResponse response){
		String creater = request.getParameter("creater");
		return skillsService.getData(creater);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object>save(@RequestBody SkillsModel skillsModel ,HttpServletRequest request,HttpServletResponse response){
		return skillsService.save(skillsModel);
	}
}

package summer.experience.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import summer.base.util.CommonParamsUtil;
import summer.experience.model.CompanyModel;
import summer.experience.model.ProjectModel;
import summer.experience.service.CompanyService;
import summer.experience.service.ProjectService;

@Controller
@RequestMapping("experience")
public class ExperienceController {

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/getCompanyList")
	@ResponseBody
	public Map<String,Object> getCompanyList(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> paramsMap = CommonParamsUtil.getParamsMap(request);
		return companyService.getCompanyList(paramsMap);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object>save(@RequestBody CompanyModel companyModel, HttpServletRequest request,HttpServletResponse response){
		return companyService.save(companyModel);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object>delete(HttpServletRequest request,HttpServletResponse response){
		return companyService.delete(request.getParameter("id"));
	}
	
	@RequestMapping("/getProjectList")
	@ResponseBody
	public List<ProjectModel>getProjectList(HttpServletRequest request,HttpServletResponse response){
		String companyId  = request.getParameter("companyId");
		return projectService.getProjectList(companyId);
	}
	
	@RequestMapping("/saveProject")
	@ResponseBody
	public Map<String,Object>saveProject(@RequestBody ProjectModel projectModel, HttpServletRequest request,HttpServletResponse response){
		return projectService.save(projectModel);
	}
	
	@RequestMapping("/deleteProject")
	@ResponseBody
	public Map<String,Object>deleteProject(HttpServletRequest request,HttpServletResponse response){
		return projectService.delete(request.getParameter("id"));
	}
}

package summer.module.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import summer.base.util.ConstDefine;
import summer.module.mapper.ModuleMapper;
import summer.module.model.ModuleModel;
import summer.module.model.TreeModule;
import summer.module.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

	protected static Logger logger = Logger.getLogger("moduleController");// 异常信息
	@Autowired
	private ModuleMapper moduleMapper;
	
	@Override
	public Map<String,Object> getModuleList(Map<String, Object> paramsMap) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		String pid=(String) paramsMap.get("id");
		List<TreeModule> ms=null;
		try{
			if(pid!=null && !pid.equals("")){
				ms = this.getModuleByPid(pid);
				respMap.put(ConstDefine.CONST_DATA_KEY, ms);
			}else{
				ms = this.getModuleByPid("0");
				String isNeedRoot=(String) paramsMap.get("needRoot");
				if(isNeedRoot!=null &&!isNeedRoot.equals("")){
					List<TreeModule> mss=new ArrayList<TreeModule>();
					TreeModule root= new TreeModule();
					root.setId("0");
					root.setState("open");
					root.setText("根节点");
					root.setUrl("");
					root.setChildren(ms);
					mss.add(root);
					respMap.put(ConstDefine.CONST_DATA_KEY, mss);
				}else{
					respMap.put(ConstDefine.CONST_DATA_KEY, ms);
				}
			}
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, true);
		}catch(Exception e){
			logger.error(e.getMessage());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
		}
		
		return respMap;
	}
	
	@Override
	public Map<String, Object> getModuleListByPid(String pid) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		try{
			List<ModuleModel> moduleList= moduleMapper.getModuleList(pid);
			respMap.put(ConstDefine.CONST_DATA_KEY, moduleList);
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, true);
		}catch(Exception e){
			logger.error(e.getMessage());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
		}
		return respMap;
	}
	
	public List<TreeModule> getModuleByPid(String pid){
		List<ModuleModel> modules = null;
		List<TreeModule> tms=new ArrayList<TreeModule>();
		modules = moduleMapper.getModuleList(pid);
		for(ModuleModel m:modules){
			//表示是父节点
			TreeModule tm=new TreeModule(); 
			tm.setId(m.getId());
			tm.setText(m.getText());
			tm.setState("open");
			tm.setUrl(m.getUrl());
			if(m.getIsleaf()==0){
				tm.setState("closed");
				tm.setChildren(getModuleByPid(m.getId()));
			}else{
				tm.setChildren(null);
			}
			tms.add(tm);
		}
		return tms;
	}

	@Override
	public Map<String, Object> save(ModuleModel model) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		int count = moduleMapper.selectOne(model.getId());
		try{
			if(count>0){
				moduleMapper.update(model);
			}else{
				moduleMapper.insert(model);
			}
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, true);
		}catch(Exception e){
			logger.error(e.getMessage());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
		}
		return respMap;
	}

	@Override
	public Map<String, Object> delete(ModuleModel model) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		try{
			moduleMapper.delete(model.getId());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, true);
		}catch(Exception e){
			logger.error(e.getMessage());
			respMap.put(ConstDefine.CONST_SUCCESS_KEY, false);
		}
		return respMap;
	}
}

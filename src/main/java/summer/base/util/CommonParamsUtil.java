package summer.base.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class CommonParamsUtil {

	public static Map<String,Object> getParamsMap(HttpServletRequest request){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		Enumeration enu=request.getParameterNames();  
		while(enu.hasMoreElements()){  
			String paraName=(String)enu.nextElement();  
			if(paraName.equals("page") || paraName.equals("rows")){
				paramsMap.put(paraName, Integer.valueOf(request.getParameter(paraName)));
			}else{
				paramsMap.put(paraName, request.getParameter(paraName));
			}
		}  
		return paramsMap;
	}
}

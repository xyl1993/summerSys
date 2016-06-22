package summer.base.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConstDefine {

	public static final String CONST_UPLOADFILEPATH_KEY = "\\ry_upload\\project_user\\";
	// success属性的常量定义
	public static final String CONST_SUCCESS_KEY = "success";
	// message属性的常量定义
	public static final String CONST_MESSAGE_KEY = "message";
	// total属性的常量定义
	public static final String CONST_TOTAL_KEY = "total";
	// data属性的常量定义
	public static final String CONST_DATA_KEY = "data";
	// empty属性的常量定义
	public static final String CONST_EMPTY_KEY = "empty";
	// 排他检查Key的常量定义
	public static final String CONST_BUSINESS_EXCEPTION_KEY = "businessException";
	// 日期format类型(带时分秒)
	public static final String CONST_DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";
	// 日期format类型(不带时分秒)
	public static final String CONST_DATE_FORMATE_2 = "yyyy-MM-dd";
	// 结束时间
	public static final String CONST_END_DATE = "enddate";
	
	/**
	 * 设置返回给前台DATAGRID 分页使用的Map 
	 * @param ps
	 * @return
	 */
	public static <T> Map<String,Object> getPageMap(Pagination<T> ps){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("total",ps.getCurrentPage());
		map.put("rows",ps.getRecordCount());
		return map;
	}
	
	/**
	 * 设置返回给前台DATAGRID 不分页使用的Map 
	 * @param ps
	 * @return
	 */
	public static <T> Map<String,Object> getListMap(List<T> ps){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("rows",ps);
		return map;
	}
	
}

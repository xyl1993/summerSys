package summer.base.mapper;

import java.util.Date;

/**
 * 共通方法
 * @author xyl
 *
 */
public interface CommonMapper {

	//获得数据库的系统时间
	public Date getDbDate();
}

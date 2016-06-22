package summer.base.util;

import java.util.List;

public class Pagination<T> {

	// 当前页
	private int currentPage;
	// 页大小
	private int pageSize;
	// 总页数
	private int pageCount;
	// 记录数
	private int recordCount;
	// 数据列表
	private List<T> list;

	/**
	 * 无参构造
	 */
	public Pagination() {

	}

	/**
	 * 全参构造
	 * 
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            页大小
	 * @param pageCount
	 *            总页数
	 * @param recordCount
	 *            记录数
	 * @param list
	 *            数据列表
	 */
	public Pagination(int currentPage, int pageSize, int pageCount,
			int recordCount, List<T> list) {

		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		this.recordCount = recordCount;
		this.list = list;
	}

	/**
	 * 获得当前页
	 * 
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 设置当前页
	 * 
	 * @param currentPage
	 *            当前页
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 获得页大小
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置页大小
	 * 
	 * @param pageSize
	 *            页大小
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获得总页数
	 * 
	 * @return
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * 设置总页数
	 * 
	 * @param pageCount
	 *            总页数
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * 获得记录数
	 * 
	 * @return
	 */
	public int getRecordCount() {
		return recordCount;
	}

	/**
	 * 设置记录数
	 * 
	 * @param recordCount
	 *            记录数
	 */
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	/**
	 * 获得数据列表
	 * 
	 * @return
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * 设置数据列表
	 * 
	 * @param list
	 *            数据列表
	 */
	public void setList(List<T> list) {
		this.list = list;
	}
}

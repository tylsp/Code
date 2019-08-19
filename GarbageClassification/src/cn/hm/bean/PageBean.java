package cn.hm.bean;

import java.util.List;

public class PageBean <T> {
	
	/**
	 * 总记录数 count
	 * 页面存放的数据量 pageSize
	 * 当前页码 currentPage
	 * 总页数 countPage
	 * 当前页数据 List<T>
	 */
	
	private int count;
	private int pageSize = 5;
	private int currentPage = 1;
	private int countPage;
	private List<T> list;

	public void setCount(int count) {
		if(count > 0){
			this.count = count;
			this.countPage = this.count % this.pageSize == 0 ? this.count / this.pageSize : (int)(this.count / this.pageSize) + 1;			
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if(currentPage <= 1){
			this.currentPage = 1;
			return;
		}
		if(currentPage >= this.countPage){
			this.currentPage = this.countPage;
			return;
		}
		this.currentPage = currentPage;
	}

	public int getCountPage() {
		return countPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean [count=" + count + ", pageSize=" + pageSize + ", currentPage=" + currentPage + ", countPage="
				+ countPage + ", list=" + list + "]";
	}

}

package cn.hm.bean;

import java.util.List;

/**
 * 分页的JavaBean类 每页的数据量: pageSize 总数据量: countRecord 总共页数：countPage 当前页码：
 * currentPageNo所有数据 当前页的数据：List<T> list
 */
public class Page<T> {
	private int countRecord;
	private int pageSize = 10;
	private int countPage;
	private int currentPageNo = 1;
	private List<T> list;

	public int getCountRecord() {
		return countRecord;
	}

	/**
	 * 如果每页数据量不为10，一定要在设置总记录数之前设置每页数量 否则
	 * 
	 */
	public void setCountRecord(int countRecord) {
		if (countRecord > 0) {
			this.countRecord = countRecord;
			// 总页数=总记录数/每页数据量
			this.countPage = this.countRecord % pageSize == 0 ? this.countRecord / pageSize
					: this.countRecord / pageSize + 1;
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

	public int getCountPage() {
		return countPage;
	}

//	只能读不能写
//	public void setCountPage(int countPage) {
//		this.countPage = countPage;
//	}
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		// 防止头溢出
		if (currentPageNo <= 1) {
			this.currentPageNo = 1;
			return;
		}
		// 防止尾溢出
		if (currentPageNo > this.countPage) {
			this.currentPageNo = this.countPage;
			return;
		}
		this.currentPageNo = currentPageNo;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Page [countRecord=" + countRecord + ", pageSize=" + pageSize + ", countPage=" + countPage
				+ ", currentPageNo=" + currentPageNo + ", list=" + list + "]";
	}

	
}

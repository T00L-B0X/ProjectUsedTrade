package com.itwillbs.article;

public class Criteria {

	private int page;
	private int pageSize;
	private String search;
	private String keyword;
	private String locatns;

	public Criteria() {
		this(1, 12);
	}

	public Criteria(int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;

			return;
		}

		this.page = page;
	}

	public int getStartPage() {
		return (this.page - 1) * pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize <= 0) {
			this.pageSize = 10;

			return;
		}

		this.pageSize = pageSize;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getLocatns() {
		return locatns;
	}
	
	public void setLocatns(String locatns) {
		this.locatns = locatns;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", pageSize=" + pageSize + ", search="
				+ search + ", keyword=" + keyword + ", locatns=" + locatns + "]";
	}

}
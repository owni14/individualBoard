package com.my.vo;

public class MyPagingDto {
	private int page;
	private int pageStartRow;
	private int pageEndRow;
	private int totalCount;
	private int totalPage;
	private int startPage;
	private int endPage;
	private String searchType;
	private String keyword;
	private int showRow = 10;
	
	public void setPage(int page, int totalCount) {
		this.page = page;
		this.totalCount = totalCount;
		setPageInfo();
	}

	private void setPageInfo() {
		pageEndRow = page * showRow;
		pageStartRow = pageEndRow - (showRow - 1);
		totalPage = (int) Math.ceil((double)totalCount / showRow);
		startPage = (((page - 1) / 10) * 10) + 1;
		endPage = startPage + 9;
		
		if (endPage > totalPage) {
			endPage = totalPage;
		}
	}

	public int getPageStartRow() {
		return pageStartRow;
	}

	public int getPageEndRow() {
		return pageEndRow;
	}

	public int getPage() {
		return page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public int getShowRow() {
		return showRow;
	}

	public void setShowRow(int showRow) {
		this.showRow = showRow;
	}

	@Override
	public String toString() {
		return "PagingDto [page=" + page + ", pageStartRow=" + pageStartRow + ", pageEndRow=" + pageEndRow
				+ ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", searchType=" + searchType + ", keyword=" + keyword + ", showRow=" + showRow + "]";
	}

}

package com.e_lliam.app.video.server.pojo.mobile;

import java.util.List;

public class SearchBean {
	private List<SearchResultBean> searchResult;
	private List<SearchResultBean> guessLike;
	public List<SearchResultBean> getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(List<SearchResultBean> searchResult) {
		this.searchResult = searchResult;
	}
	public List<SearchResultBean> getGuessLike() {
		return guessLike;
	}
	public void setGuessLike(List<SearchResultBean> guessLike) {
		this.guessLike = guessLike;
	}
	
}

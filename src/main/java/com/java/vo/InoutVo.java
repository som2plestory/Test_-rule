package com.java.vo;

import java.util.List;
import java.util.Map;

public class InoutVo {
	
	private Map<String, String> appHeader;
	private List<Map<String, String>> eval;
	
	public InoutVo() {}
	
	public InoutVo(Map<String, String> appHeader, List<Map<String, String>> eval) {
		super();
		this.appHeader = appHeader;
		this.eval = eval;
	}

	public Map<String, String> getAppHeader() {
		return appHeader;
	}

	public void setAppHeader(Map<String, String> appHeader) {
		this.appHeader = appHeader;
	}

	public List<Map<String, String>> getEval() {
		return eval;
	}

	public void setEval(List<Map<String, String>> eval) {
		this.eval = eval;
	}

	@Override
	public String toString() {
		return "InoutVo [appHeader=" + appHeader + ", eval=" + eval + "]";
	}
	
}

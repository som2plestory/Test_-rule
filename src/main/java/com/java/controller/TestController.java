package com.java.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.service.TestService;
import com.java.vo.InoutVo;


@Controller
public class TestController {
	
	@Autowired
	private TestService testService;
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@GetMapping("/")
	public String test() {
		System.out.println("TestController > test()"); 
		return "info/test";
	}
	
	
	/*** 테이블 정보 불러와서 저장 ***/
	@GetMapping("/test/tableInfo") public String tableInfo(HttpSession httpSession) {
		System.out.println("testController > tableInfo()");
		
		if(httpSession.getAttribute("all_tabs") == null) {
		Map<String, List<Map<String, String>>> all_tabs = testService.all_tabs();
			httpSession.setAttribute("all_tabs", all_tabs);
		}
		
		//map없앤다고 생각해보려고
		if(httpSession.getAttribute("all_tab_cols") == null) {
			List<Map<String, String>> all_tab_cols = testService.all_tab_cols();
			httpSession.setAttribute("all_tab_cols", all_tab_cols);
		}
		
		if(httpSession.getAttribute("all_cols") == null) {
			List<Map<String, Object>> all_cols = testService.all_cols();
			httpSession.setAttribute("all_cols", all_cols);
		}
		
		return "info/test";
	}
	
	
	@ResponseBody
	@PostMapping("/test/data_input") public Map<String, Object> data_input(@RequestBody InoutVo info, HttpSession session) {
		System.out.println("testController > data_input()");
		System.out.println("info: "+info); 
		
		Map<String, Object> result = new HashMap<>();
		
		Map<String, List<Map<String, String>>> all_tabs = (Map<String, List<Map<String, String>>>)session.getAttribute("all_tabs");
		List<Map<String, Object>> all_cols = (List<Map<String, Object>>)session.getAttribute("all_cols");
		//List<Map<String, String>> all_tab_cols = (List<Map<String, String>>)session.getAttribute("all_tab_cols");
		
		if(all_cols == null || all_tabs == null || all_cols.size() == 0 || all_tabs.size() == 0){
			tableInfo(session);
			all_tabs = (Map<String, List<Map<String, String>>>)session.getAttribute("all_tabs");
			all_cols = (List<Map<String, Object>>)session.getAttribute("all_cols");
			//all_tab_cols = (List<Map<String, String>>)session.getAttribute("all_tab_cols");
		};
		
		Map<String, String> appHeader = info.getAppHeader();
		System.out.println("appHeader(IN): "+appHeader);
		
		List<Map<String, String>> eval = info.getEval();
		boolean valid;
		if(eval != null && eval.size() != 0) {
			//타입이 왜 필요한지 모르겠어
			/***************************** 테이블별 insert 만들기********************************/
			List<Map<String, String>> list = new ArrayList<>(all_tabs.size());
			
			
			
			valid = testService.valid2(eval, all_cols);
						
		}else {
			logger.info("eval: No input");
			valid = false;
		}
		
		if(valid) {
			testService.create_insert(eval, all_tabs);
		}
		
		result.put("valid", valid);
		result.put("InputVo", info);
		return result;
	}

}

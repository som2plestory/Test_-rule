package com.java.controller;

import java.math.BigDecimal;
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
		//Map<String, List<Map<String, String>>> all_tabs = testService.all_tabs();
			List<Map<String, String>> all_tabs = testService.all_tabs();
			httpSession.setAttribute("all_tabs", all_tabs);
		}
		
		//map없앤다고 생각해보려고
		/*
		if(httpSession.getAttribute("all_tab_cols") == null) {
			List<Map<String, String>> all_tab_cols = testService.all_tab_cols();
			httpSession.setAttribute("all_tab_cols", all_tab_cols);
		}
		*/
		
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
		
		//Map<String, List<Map<String, String>>> all_tabs = (Map<String, List<Map<String, String>>>)session.getAttribute("all_tabs");
		List<Map<String, Object>> all_cols = (List<Map<String, Object>>)session.getAttribute("all_cols");
		List<Map<String, String>> all_tabs = (List<Map<String, String>>)session.getAttribute("all_tabs");
		
		if(all_cols == null || all_tabs == null || all_cols.size() == 0 || all_tabs.size() == 0){
			tableInfo(session);
			//all_tabs = (Map<String, List<Map<String, String>>>)session.getAttribute("all_tabs");
			all_tabs = (List<Map<String, String>>)session.getAttribute("all_tabs");
			all_cols = (List<Map<String, Object>>)session.getAttribute("all_cols");
			//all_tab_cols = (List<Map<String, String>>)session.getAttribute("all_tab_cols");
		};
		
		Map<String, String> appHeader = info.getAppHeader();
		System.out.println("appHeader(IN): "+appHeader);
		
		List<Map<String, String>> eval = info.getEval();
		boolean valid=true;
		if(eval != null && eval.size() != 0) {
			
			/*****************땡겨옴***************************************************/
			//int count_cols = all_cols.size();
			int count_valid = 3;
			System.out.println("eval: "+eval);
			
			for(int i=eval.size()-1; i>=0; i--) {
				Map<String, String> input = eval.get(i);
				System.out.println("eval("+i+"): "+input);
				//정상적으로 들어온 value count
				int count_ok=0;
				
				/////////////////////////////////////////////추가 미리 insert 정보 만들기 ///////////////////////
				//리스트 - 테이블별 입력될 로우 한개씩 > Map : 테이블 명 < 리스트 : 그 테이블에 저장될 칼럼 이름 데이터, 타입 순서대로 저장
				//List<Map<String, List<String>>> row_data = new ArrayList<>();
				//String table_name = all_tabs.get(0).get("TABLE_NAME");
				
				//data_colums 목록 별로 input 데이터 검사
				for(Map<String, Object> col : all_cols) {
					String name = col.get("COLUMN_NAME").toString();
					
					//있으면 검사, 없으면 false
					if(input.containsKey(name)) {
						String input_value = input.get(name);
						//칼럼 데이터 타입 숫자, 들어온데이터가 숫자인지 확인(//)
						if(testService.valid2(col, input_value)) {
							count_ok++;
						}
						
					//}else {
						//logger.info("eval("+i+"): "+name+" is NULL" );
						//valid = false;
					}
				}
				
				if(count_ok < count_valid) {
					logger.info("eval("+i+"): 유효한 항의 개수("+count_ok+")가 검사를 통과하기 위한 최소 개수("+count_valid+")보다 적음");
					valid = false;
				
				//통과>insert data
				}else {
					//testService.insert_data(input, all_tabs);
					//testService.test_insert(input, all_tabs);
					testService.test_insert2(input, all_tabs);
				}
			}
			
		}else {
			logger.info("eval: No input");
			valid = false;
		}
		
		/*
		if(valid) {
			testService.create_insert(eval, all_tabs);
		}
		*/
		result.put("valid", valid);
		result.put("InputVo", info);
		return result;
	}
	
}

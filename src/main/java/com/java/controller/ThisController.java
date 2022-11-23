package com.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.service.ThisService;
import com.java.vo.InoutVo;

@RequestMapping("this")
@Controller
public class ThisController {
	
	@Autowired
	private ThisService thisService;
	
	@PostMapping("/tableInfo") public String tableInfo(HttpSession httpSession) {
		System.out.println("ThisController > tableInfo()");
		
		if(httpSession.getAttribute("allTables") == null) {
		Map<String, Map<String, String>> allTables = thisService.allTables();
			httpSession.setAttribute("allTables", allTables);
		}
		
		if(httpSession.getAttribute("allColumns") == null) {
		Map<String, Map<String, String>> allColumns = thisService.allColumns();
			httpSession.setAttribute("allColumns", allColumns);
		}
		
		return "info/repeat";
	}
	
	@ResponseBody
	@PostMapping("validation") public Map<String, Object> validation(@RequestBody InoutVo info) {
		System.out.println("ThisController > validation()");
		Map<String, Object> result =  new HashMap<>();
		
		if(info == null) {
			result.put("success", "fail");
			return result;
		}
		
		Map<String, String> appHeader = info.getAppHeader();
		System.out.println("appHeader(IN): "+appHeader);
		thisService.valid_header(appHeader);
		
		List<Map<String, String>> eval = info.getEval();
		System.out.println("eval: "+eval);
		//thisService
		
		
		return result;
	}
	
	
	
	
}

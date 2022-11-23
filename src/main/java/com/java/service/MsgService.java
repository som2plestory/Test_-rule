package com.java.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.MsgDao;
import com.java.vo.InoutVo;

@Service
public class MsgService {
	
	@Autowired
	private MsgDao msgDao;
	
	public String input(String info) {
		System.out.println("MsgService > input()");
		
		JSONObject jObject = new JSONObject(info);
		System.out.println("jObject: "+jObject);
		
		JSONObject appHeader = (JSONObject)jObject.get("appHeader");
		System.out.println("appHeader(IN): "+appHeader);
		
		//헤더에 데이터 추가
		appHeader.put("custNo", "1");
		appHeader.put("cnslNo", "2");
		appHeader.put("loanNo", "3");
		System.out.println("appHeader(OUT): "+appHeader);
		
		if(jObject.keySet().contains("eval")); JSONArray eval = (JSONArray)jObject.get("eval");
		System.out.println("eval: "+eval);
		
		for(int i=0; i<eval.length(); i++) {
			JSONObject keys = (JSONObject)eval.get(i);
			Map<String, String> map = new HashMap<>();
			for(String key : keys.keySet()) {
				map.put(key, keys.getString(key));
			}
			//msgDao.input(map);
		}
		
		Map<String,String> A = new HashMap<>();
		A.put("A", "a");
		eval.put(A);
		
		System.out.println("eval:After: "+eval);
		
		jObject.put("appHeader", appHeader);
		jObject.put("eval", eval);
		
		String result = jObject.toString();
		System.out.println(result);
		
		return result;
	}
	
	
	public InoutVo input2(InoutVo info) {
		System.out.println("MsgService > input2()");
		
		Map<String, String> appHeader = info.getAppHeader();
		System.out.println("appHeader(IN): "+appHeader);
		
		//헤더에 데이터 추가
		appHeader.put("custNo", "1");
		appHeader.put("cnslNo", "2");
		appHeader.put("loanNo", "3");
		System.out.println("appHeader(OUT): "+appHeader);
		
		if(info.getEval() != null) {
			List<Map<String, String>> eval = info.getEval();

			System.out.println("eval: "+eval);
			
			for(int i=0; i<eval.size(); i++) {
				Map<String, String> map = eval.get(i);
				System.out.println("eval:Before: "+map);
				
				if(i==0) {
					msgDao.inputCust(map);
				}else {
					msgDao.inputVowel(map);
				}
			}
			
			
			Map<String,String> A = msgDao.output("a");
			eval.add(A);
			System.out.println("eval:After: "+eval);
			info.setEval((List<Map<String, String>>)eval);
		}
		
		info.setAppHeader((Map<String,String>)appHeader);
		
		System.out.println("info: "+info);
		
		return info;
	}
	
	
	//세션은?
	@SuppressWarnings("unused")
	public String validation(InoutVo info) {
		System.out.println("MsgService > validation()");
		
		Map<String, Map<String, String>> allTables = msgDao.allTables();
		System.out.println(allTables);
		Map<String, Map<String, String>> allColumns = msgDao.allColumns();
		System.out.println(allColumns);
		
		Map<String, String> appHeader = info.getAppHeader();
		System.out.println("appHeader(IN): "+appHeader);
	
		if(info.getEval() != null) {
			List<Map<String, String>> eval = info.getEval();
			System.out.println("eval: "+eval);
			
			for(int i=0; i<eval.size(); i++) {
				Map<String, String> map = eval.get(i);
				System.out.println("eval:Before: "+map);
				
				String val="success";
				for(String k: map.keySet()) {
					String v = map.get(k);
					Map<String, String> tColumns = allTables.get(k);
					
					if(v.length()>Integer.parseInt(allTables.get(k).get("DATA_LENGTH"))) {
						System.out.println(k+": 데이터 길이 초과");
						val="fail";
					}
					
					//바이트?
					switch(tColumns.get("DATA_TYPE")){
						case "NUMBER" :
							BigDecimal c = new BigDecimal(v);
							if(c.precision()>Integer.parseInt(tColumns.get("DATA_PRECISION"))) {
								System.out.println(k+"자릿수 초과");
							}
						case "VARCHAR" :
					}
				}
				
				
				if(i==0) {
					msgDao.inputCust(map);
				}else {
					msgDao.inputVowel(map);
				}
			}
		}
	
		return "ok";
	}
	
	
}
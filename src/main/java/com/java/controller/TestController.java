package com.java.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.java.vo.InputVo;

@Controller
public class TestController {
	/*
	 * @RequestMapping("/") public String test() { return "main/index"; }
	 */
	
	@ResponseBody
	@PostMapping("/test/test1")
	public List<Object> testInfo(@RequestBody Object[] info) {
		System.out.println("TestController > testInfo()");
		
		List<Object> result = new ArrayList<>();
		
		String name = (String)info[0];
		System.out.println("name: "+name);
		String nickname = (String)info[1];
		System.out.println("nickname: "+nickname);
		String phone = (String)info[2];
		System.out.println("phone: "+phone);
		
		result.add(name+"님");
		result.add(nickname+"님");
		result.add(phone+"번");
		
		return result;
	}
	
	@ResponseBody
	@PostMapping("/test/test2")
	public List<String> testInfo2(@RequestBody String[] info) {
		System.out.println("TestController > testInfo2()");
		List<String> result = new ArrayList<>();
		
		String name = (String)info[0];
		String nickname = (String)info[1];
		String phone = (String)info[2];
		
		result.add(name);
		result.add(nickname);
		result.add(phone);
		
		return result;
	}
	
	
	@ResponseBody
	@PostMapping("/test/test3")
	public Map<String,Object> testInfo3(@RequestBody Map<String,Object> info) {
		System.out.println("TestController > testInfo3()");
		Map<String,Object> result = new HashMap<>();
		
		result.put("name", info.get("name")+"님");
		result.put("nickname", info.get("nickname")+"님");
		result.put("phone", info.get("phone")+"번");
		
		System.out.println(result);
		return result;
	}
	

	@ResponseBody
	@PostMapping("/test/test4")
	public List<Map<String,String>> testInfo4(@RequestBody List<Map<String,String>> info) {
		System.out.println("TestController > testInfo4()");
		
		List<Map<String,String>> result = new ArrayList<>();

		Map<String,String> infoArr = new HashMap<>();
		infoArr.put("name", info.get(0).get("name")+"님");
		infoArr.put("nickname", info.get(0).get("nickname")+"님");
		infoArr.put("phone", info.get(0).get("phone")+"번");
		
		result.add(infoArr);
		
		System.out.println("4번 리스트<맵>"+result);
		return result;
	}
	
	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/test/test5") public InputVo testInfo5(@RequestBody InputVo
	 * info) { InputVo result = new InputVo();
	 * 
	 * String name = info.getName()+"��"; String nickname = info.getNickname()+"��";
	 * int phone = info.getPhone();
	 * 
	 * result.setName(name.substring(3)); result.setNickname(nickname.substring(5,
	 * 6)); result.setPhone(phone);
	 * 
	 * return result; }
	 */
}

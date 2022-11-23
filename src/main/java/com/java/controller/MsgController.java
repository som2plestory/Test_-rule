package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.service.MsgService;
import com.java.vo.InoutVo;

@Controller
public class MsgController {
	
	@Autowired
	private MsgService msgService;
	
	@GetMapping("/")
	public String test() {
		System.out.println("MsgController > test()"); 
		return "info/repeat";
		//return "info/headerInfo";
	}
	
	@ResponseBody
	@PostMapping("/Msg/input") public String input(@RequestBody String info) {
		System.out.println("MsgController > input()");
		System.out.println("info: "+info.toString()); 
		
		String result = msgService.input(info);
		
		return result;
	}
	
	@ResponseBody
	@PostMapping("/Msg/input2") public InoutVo input2(@RequestBody InoutVo info) {
		System.out.println("MsgController > input2()");
		System.out.println("info: "+info); 
		
		InoutVo result = msgService.input2(info);
		
		return result;
	}
	
	@ResponseBody
	@PostMapping("Msg/validation") public String validation(@RequestBody InoutVo info) {
		System.out.println("MsgController > validation()");
		
		String result = msgService.validation(info);
		
		return result;
	}

}

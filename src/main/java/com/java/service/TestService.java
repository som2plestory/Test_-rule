package com.java.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.controller.TestController;
import com.java.dao.TestDao;

@Service
public class TestService {
	
	@Autowired
	private TestDao testDao;
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	
	public static boolean isNumber(String number) {
		boolean flag = true;
		number = number.replace(".", "");
				
		if (number == null || "".equals(number)) {
			return false;
		}

		int size = number.length();
		int st_no = 0;

		// 45(-)음수여부 확인, 음수이면 시작위치를 1부터 시작
		if (number.charAt(0) == 45) {
			st_no = 1;
		}

		// 48(0)~57(9)가 아니면 false
		for (int i = st_no; i < size; ++i) {
			if (!(48 <= ((int) number.charAt(i)) && 57 >= ((int) number.charAt(i)))) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	
	public List<Map<String, String>> all_tab_cols(){
		System.out.println("TestService > all_tabs()");
		List<Map<String, String>> all_tab_cols = testDao.all_tab_cols();
		System.out.println("칼럼나열: "+all_tab_cols);
		return all_tab_cols;
	}
	
	
	public Map<String, List<Map<String, String>>> all_tabs(){
		System.out.println("TestService > all_tabs()");
		Map<String, List<Map<String, String>>> all_tabs = testDao.all_tabs();
		System.out.println("테이블정보: "+all_tabs);
		return all_tabs;
	}
	
	public List<Map<String, Object>> all_cols(){
		System.out.println("TestService > all_colss()");
		List<Map<String, Object>> all_cols = testDao.all_cols();
		System.out.println("칼럼정보: "+all_cols);
		return all_cols;
	}
	
	
	//0:차주 1~:보증인 데이터 유효성 검사 > 한번이라도 잘못됐다면 return false
	public boolean valid2(List<Map<String, String>> eval, List<Map<String, Object>> all_cols) {
		System.out.println("testService > validaion");
		boolean valid = true;
		//int count_cols = all_cols.size();
		int count_valid = 3;
		System.out.println("eval: "+eval);
		
		//보증인부터 검사
		for(int i=eval.size()-1; i>=0; i--) {
			Map<String, String> input = eval.get(i);
			System.out.println("eval("+i+"): "+input);
			//정상적으로 들어온 value count
			int count_ok=0;
			
			//data_colums 목록 별로 input 데이터 검사
			for(Map<String, Object> col : all_cols) {
				String name = col.get("COLUMN_NAME").toString();
				String type = col.get("DATA_TYPE").toString();
				int prc = ((BigDecimal)col.get("DATA_PRECISION")).intValue();
				int scale = ((BigDecimal)col.get("DATA_SCALE")).intValue();
				int length = ((BigDecimal)col.get("DATA_LENGTH")).intValue();
				
				//있으면 검사, 없으면 false
				if(input.containsKey(name)) {
					String input_value = input.get(name);

					//칼럼 데이터 타입 숫자, 들어온데이터가 숫자인지 확인(//)
					if(type.equals("NUMBER")) {

						if(isNumber(input_value)) {
							try{
								BigDecimal dec = new BigDecimal(input_value);
								
								//숫자정밀도, 소수점아래검사
								if(dec.precision()>prc || dec.scale()>scale) {
									logger.info("eval("+i+"): "+name+" precision or scale error" );
									valid = false;
								}else {
									count_ok++;
								}
								
							}catch(NumberFormatException e){
								logger.info("eval("+i+"): "+name+"(COLUMN_TYPE): NUMBER, BUT THE VALUE CAN NOT BE CASTED TO NUMBER");
								valid = false;
							}
						}else {
							logger.info("eval("+i+"): "+name+"(value: "+input_value+") is not Number" );
							valid = false;
						}
						
						
					//칼럼 데이터 타입 문자
					}else {
						//데이터 길이 확인
						/*
						if(input_value == null) {
							System.out.println("공백:");
						}else if(input_value.equals("")) {
							System.out.println("null");
						}
						*/
						
						if(input_value.length()>length) {
							logger.info("eval("+i+"): "+name+" length error" );
							valid = false;
						}else {
							count_ok++;
						}
					}
				//}else {
					//logger.info("eval("+i+"): "+name+" is NULL" );
					//valid = false;
				}
					
			}
			
			if(count_ok < count_valid) {
				logger.info("eval("+i+"): 유효한 항의 개수("+count_ok+")가 검사를 통과하기 위한 최소 개수("+count_valid+")보다 적음");
				valid = false;
			}
		}
		return valid;
	}
	
	
	
	//table별
	public void create_insert(List<Map<String, String>> eval, Map<String, List<Map<String, String>>> all_tabs) {
		System.out.println("TestService > create_insert()");		
		
		//차주 or 보증인 별로 > 나중에 합쳐서 넣을거고민 (다중테이블 다중로우)
		for(int i=eval.size()-1; i>=0; i++) {
			Map<String, String> input = eval.get(i);

			//insert할 테이블 당 행 집합
			Set<Map<String, Object>> table_rows = new HashSet<>();
			for(String table_name : input.keySet()) {
				Map<String, Object> table_row = new HashMap<>();
				List<String> columns = new ArrayList<>();
				List<String> values = new ArrayList<>();
				//테이블별 칼럼리스트 (테이블이름, 칼럼명, 데이터 타입)
				List<Map<String, String>> cols = all_tabs.get(table_name);
				//추가할
				for(int j=0; j<cols.size(); j++) {
					Map<String, String> col_info = cols.get(j);
					String col_name = col_info.get("COLUMN_NAME");
					String col_type = col_info.get("COLUMN_TYPE");
					
					if(input.containsKey(col_name)) {
						//if()
					}
					columns.add(col_name);
					
				}
				
				//if(!table_names.contains(table_name)) {
				//}
			}
			//테이블 별로 추가할 행> 반복 set.add
			//Map<String, Object> row = new HashMap<>();
			//추가할 행의 칼럼명들
			//List<Object> columns = 
			
			//테이블 이름
			//row.put("table_name", table);
		}
	}
}

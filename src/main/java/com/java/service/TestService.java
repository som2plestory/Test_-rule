package com.java.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	
	//public Map<String, List<Map<String, String>>> all_tabs(){
	public List<Map<String, String>> all_tabs(){
		System.out.println("TestService > all_tabs()");
		//Map<String, List<Map<String, String>>> all_tabs = testDao.all_tabs();
		List<Map<String, String>> all_tabs = testDao.all_tabs();
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
	public boolean valid2(Map<String, Object> col, String input_value) {
		System.out.println("testService > validaion");
		boolean valid = true;
		
		String name = col.get("COLUMN_NAME").toString();
		String type = col.get("DATA_TYPE").toString();
		int prc = ((BigDecimal)col.get("DATA_PRECISION")).intValue();
		int scale = ((BigDecimal)col.get("DATA_SCALE")).intValue();
		int length = ((BigDecimal)col.get("DATA_LENGTH")).intValue();

			//칼럼 데이터 타입 숫자, 들어온데이터가 숫자인지 확인(//)
			if(type.equals("NUMBER")) {
				if(isNumber(input_value)) {
					try{
						BigDecimal dec = new BigDecimal(input_value);
						//숫자정밀도, 소수점아래검사
						if(dec.precision()>prc || dec.scale()>scale) {
							logger.info(name+" precision or scale error" );
							valid = false;
						}
						
					}catch(NumberFormatException e){
						logger.info(name+"(COLUMN_TYPE): NUMBER, BUT THE VALUE CAN NOT BE CASTED TO NUMBER");
						valid = false;
					}
				}else {
					logger.info(name+"(value: "+input_value+") is not Number" );
					valid = false;
				}
				
			//칼럼 데이터 타입 문자
			}else 
				if(input_value.length()>length) {
				logger.info(name+" length error" );
				valid = false;
			}
			
		return valid;
	}
	
	
	
	//list > insert
	public void insert_data(Map<String, String> input, List<Map<String, String>> all_tabs) {
		System.out.println("TestService > insert_data()");
		
		List<Map<String, Object>> table_rows = new ArrayList<>();
		
		Map<String, Object> map = new HashMap<>();
		List<String> columns = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		
		//현재 체크중인 테이블 네임
		String table_name = "";
		for(int i=0; i<all_tabs.size(); i++) {
			Map<String, String> col = all_tabs.get(i);
			String col_tab = col.get("TABLE_NAME");
			String col_name = col.get("COLUMN_NAME");
			String col_type = col.get("DATA_TYPE");
			String input_data = input.get(col_name);
			
			//새로운 테이블이야
			if(!table_name.equals(col_tab)) {
				//0일때는 map:null
				if(i!=0) {
					//여태 저장한 값 추가
					map.put("table_name", table_name);
					map.put("columns", columns);
					map.put("values", values);
					table_rows.add(map);
					
					System.out.println("table("+i+"):"+map);
					System.out.println(table_rows);
					//초기화
					map = new HashMap<>();
					columns = new ArrayList<>();
					values = new ArrayList<>();
				}
				
				
			}
			//공통
			if(col_type.equals("VARCHAR")) {
				input_data = "'"+input_data+"'";
				values.add(input_data);
			}else {
				Double data = Double.parseDouble(input_data);
				values.add(data);
			}
			table_name = col_tab;
			System.out.println("table_name 세팅: "+table_name);
			columns.add(col_name);
		
		}
		System.out.println("table_rows: "+table_rows);
		//마지막 테이블 추가
		map.put("table_name", table_name);
		map.put("columns", columns);
		map.put("values", values);
		table_rows.add(map);
		
		testDao.insert_data(table_rows);
	}
	
	//map > 즉각 insert
	public void insert_data2(Map<String, String> input, List<Map<String, String>> all_tabs) {
		System.out.println("TestService > insert_data2()");
		
		Map<String, Object> map = new HashMap<>();
		List<String> columns = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		
		//현재 체크중인 테이블 네임
		String table_name = "";
		for(int i=0; i<all_tabs.size(); i++) {
			Map<String, String> col = all_tabs.get(i);
			String col_tab = col.get("TABLE_NAME");
			String col_name = col.get("COLUMN_NAME");
			String col_type = col.get("DATA_TYPE");
			String input_data = input.get(col_name);
			
			//새로운 테이블이야
			if(!table_name.equals(col_tab)) {
				//0일때는 map:null
				if(i!=0) {
					//여태 저장한 값 추가
					map.put("table_name", table_name);
					map.put("columns", columns);
					map.put("values", values);
					
					testDao.insert_data5(map);
					
					//초기화
					map = new HashMap<>();
					columns = new ArrayList<>();
					values = new ArrayList<>();
				}
				
				
			}
			//공통
			if(col_type.equals("VARCHAR")) {
				input_data = "'"+input_data+"'";
				values.add(input_data);
			}else {
				Double data = Double.parseDouble(input_data);
				values.add(data);
			}
			table_name = col_tab;
			System.out.println("table_name 세팅: "+table_name);
			columns.add(col_name);
		
		}
		//마지막 테이블 추가
		map.put("table_name", table_name);
		map.put("cols", columns);
		map.put("vals", values);
		testDao.insert_data5(map);
	}
	
	
	//STRINGBUILDER 던지기
	public void insert_data3(Map<String, String> input, List<Map<String, String>> all_tabs) {
		System.out.println("TestService > insert_data3()");
		
		Map<String, Object> map = new HashMap<>();
		StringBuilder columns = new StringBuilder();
		StringBuilder values = new StringBuilder();
		
		//현재 체크중인 테이블 네임
		String table_name = "";
		for(int i=0; i<all_tabs.size(); i++) {
			Map<String, String> col = all_tabs.get(i);
			String col_tab = col.get("TABLE_NAME");
			String col_name = col.get("COLUMN_NAME");
			String col_type = col.get("DATA_TYPE");
			String input_data = input.get(col_name);
			
			//새로운 테이블이야
			if(!table_name.equals(col_tab)) {
				//0일때는 map:null
				if(i!=0) {
					//여태 저장한 값 추가
					map.put("table_name", table_name);
					map.put("columns", columns);
					map.put("values", values);
					
					testDao.insert_data6(map);
					
					//초기화
					map = new HashMap<>();
					columns = new StringBuilder();
					values = new StringBuilder();
				}
				
			}else {
				columns.append(", ");
				values.append(", ");
			}
			
			//공통
			columns.append(col_name);
			if(col_type.equals("VARCHAR")) {
				input_data = "'"+input_data+"'";
			}
			values.append(input_data);
			
			table_name = col_tab;
		
		}
		//마지막 테이블 추가
		map.put("table_name", table_name);
		map.put("cols", columns);
		map.put("vals", values);
		testDao.insert_data6(map);
	}
	
	
	
	//테스트용
	public void test_insert(Map<String, String> input, List<Map<String, String>> all_tabs) {
		System.out.println("service>test_insert()");
		List<Map<String, Object>> table_rows = new ArrayList<>();
		
		Map<String, Object> map = new HashMap<>();
		List<String> cols = new ArrayList<>();
		List<Object> vals = new ArrayList<>();
		
		cols.add("KEY1");
		cols.add("KEY2");
		cols.add("KEY3");
		cols.add("KEY4");
		
		vals.add(22);
		vals.add("나");
		vals.add(1);
		vals.add(1);

		map.put("table_name", "TEST_A");
		map.put("cols", cols);
		map.put("vals", vals);
		
		table_rows.add(map);

		map = new HashMap<>();
		cols = new ArrayList<>();
		vals = new ArrayList<>();
		cols.add("A");
		cols.add("B");
		vals.add("에");
		vals.add("비");
		
		map.put("table_name", "TEST_V");
		map.put("cols", cols);
		map.put("vals", vals);
		
		table_rows.add(map);
		
		System.out.println("table_rows: "+table_rows.size());
		testDao.insert_data2(table_rows);
	}
	
	public void test_insert2(Map<String, String> input, List<Map<String, String>> all_tabs) {
		System.out.println("service>test_insert()");
		List<Map<String, Object>> table_rows = new ArrayList<>();
		
		Map<String, Object> map = new HashMap<>();
		
		StringBuilder cols = new StringBuilder();
		StringBuilder vals = new StringBuilder();
		cols.append("KEY1").append(", ");
		cols.append("KEY2").append(", ");
		cols.append("KEY3").append(", ");
		cols.append("KEY4");
		
		vals.append(22).append(", ");
		vals.append("'나'").append(", ");
		vals.append(1).append(", ");
		vals.append(1);

		map.put("table_name", "TEST_A");
		map.put("cols", cols);
		map.put("vals", vals);
		
		table_rows.add(map);

		map = new HashMap<>();
		cols = new StringBuilder();
		vals = new StringBuilder();
		cols.append("A").append(", ");
		cols.append("B");
		vals.append("'예'").append(", ");
		vals.append("'삐'");
		
		map.put("table_name", "TEST_V");
		map.put("cols", cols);
		map.put("vals", vals);
		
		table_rows.add(map);
		
		System.out.println("table_rows: "+table_rows.size());
		testDao.insert_data3(table_rows);
	}
}

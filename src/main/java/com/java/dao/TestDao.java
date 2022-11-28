package com.java.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {
	 
	@Autowired 
	private SqlSession sqlSession;
	
	//테이블별 맵으로 전체 테이블 정보 불러오기(테이블로 안묶이네 어떻게 다 불러와야하지?)
	//public Map<String, List<Map<String, String>>> all_tabs(){
	public List<Map<String, String>> all_tabs(){
		System.out.println("testDao > all_tabs()");
		return sqlSession.selectList("test.all_tabs");
		//return sqlSession.selectMap("test.all_tabs", "TABLE_NAME");
	}
	
	//테이블 순으로 정렬
	public List<Map<String, String>> all_tab_cols(){
		System.out.println("testDao > all_tab_cols()");
		return sqlSession.selectList("test.all_tabs");
	}
	
	//칼럼명을 key로 칼럼 정보 불러오기
	public List<Map<String, Object>> all_cols(){
		System.out.println("testDao > all_cols()");
		//return sqlSession.selectMap("test.all_cols", "COLUMN_NAME");
		return sqlSession.selectList("test.all_cols");
	}
	
	
	public void insert_data(List<Map<String, Object>> table_rows) {
		System.out.println("testDao > insert_data()");
		for(int i=0; i<table_rows.size(); i++) {
			sqlSession.insert("test.insert_data", table_rows.get(i));
		}
	}
	
	public void insert_data2(List<Map<String, Object>> table_rows) {
		System.out.println("testDao > insert_data2()");
		sqlSession.insert("test.insert_data2", table_rows);
	}
	
	//STRINGBUILDER
	public void insert_data3(List<Map<String, Object>> table_rows) {
		System.out.println("testDao > insert_data3()");
		sqlSession.insert("test.insert_data3", table_rows);
	}
	
	//STRINGBUILDER
	public void insert_data4(List<Map<String, Object>> table_rows) {
		System.out.println("testDao > insert_data4()");
		for(int i=0; i<table_rows.size(); i++) {
			sqlSession.insert("test.insert_data4", table_rows);
		}
	}
	
	//row별 (칼럼리스트, 값리스트)  :1
	public void insert_data5(Map<String, Object> table_row) {
		System.out.println("testDao > insert_data5()");
		sqlSession.insert("test.insert_data", table_row);
	}
	
	//row별 (스트링빌더)  :1
	public void insert_data6(Map<String, Object> table_row) {
		System.out.println("testDao > insert_data6()");
		sqlSession.insert("test.insert_data4", table_row);
	}
}

package com.java.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {
	 
	@Autowired 
	private SqlSession sqlSession;
	
	//테이블별 맵으로 전체 테이블 정보 불러오기(테이블로 묶은)
	public Map<String, List<Map<String, String>>> all_tabs(){
		System.out.println("testDao > all_tabs()");
		return sqlSession.selectMap("test.all_tabs", "TABLE_NAME");
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
	
	
	public void insert_data(Set<Map<String, Object>> table_rows) {
		System.out.println("testDao > insert_data()");
		sqlSession.insert("test.insert_data", table_rows);
	}
}

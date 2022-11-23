package com.java.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MsgDao {
	 
	@Autowired 
	private SqlSession sqlSession;
	 
	public void inputCust(Map<String, String> map) {
		System.out.println("MsgDao > input_cust()"); 
		sqlSession.insert("test.input_cust", map);
	}
	
	public void inputVowel(Map<String, String> map) {
		System.out.println("MsgDao > input_vowel()"); 
		sqlSession.insert("test.input_vowel", map);
	}
	
	public Map<String, String> output(String a){
		System.out.println("MsgDao > output()"); 
		return sqlSession.selectOne("test.output", a); 
	}
	
	public Map<String, Map<String, String>> allTables(){
		System.out.println("MsgDao > allTables()");
		return sqlSession.selectMap("test.all_tables", "COLUMN_NAME");
	}
	
	public Map<String, Map<String, String>> allColumns(){
		System.out.println("MsgDao > all_columns()");
		return sqlSession.selectMap("test.all_columns", "COLUMN_NAME");
	}
}

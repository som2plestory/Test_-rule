package com.java.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.MsgDao;

@Service
public class ThisService {
	
	@Autowired
	private MsgDao msgDao;
	
	public Map<String, Map<String, String>> allTables(){
		System.out.println("ThisService > allTables()");
		Map<String, Map<String, String>> allTables = msgDao.allTables();
		System.out.println("allTables: "+allTables);
		return allTables;
	}
	
	public Map<String, Map<String, String>> allColumns(){
		System.out.println("ThisService > allColumns()");
		Map<String, Map<String, String>> allColumns = msgDao.allColumns();
		System.out.println("allColumnsL "+allColumns);
		return allColumns;
	}
	
	
	public void valid_header(Map<String, String> appHeader) {
		System.out.println("ThisService > valid_header");
	}

}

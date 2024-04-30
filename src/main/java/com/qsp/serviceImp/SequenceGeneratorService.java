package com.qsp.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Long getNextSequenceValue(String sequenceName) {
		String query = "SELECT NEXTVAL('" + sequenceName + "')";
		return jdbcTemplate.queryForObject(query, Long.class);
	}
}

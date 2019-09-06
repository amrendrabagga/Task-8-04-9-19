package com.emp.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayEmp {
	public static void displayEmp(PreparedStatement ps) throws SQLException {
		ResultSet resultSet = ps.executeQuery();
		while (resultSet.next()) {
			int eno = resultSet.getInt(1);
			String ename = resultSet.getString(2);
			long salary = resultSet.getLong(3);
			String designation = resultSet.getString(4);
			String dept = resultSet.getString(5);
			Emp emp1 = new Emp(eno, ename, salary, designation, dept);
			System.out.println(emp1);
		}
	}

}

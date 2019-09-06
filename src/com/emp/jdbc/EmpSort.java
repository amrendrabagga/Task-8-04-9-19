package com.emp.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpSort {
	
	public static void empSort(Connection con,BufferedReader reader) throws IOException, SQLException{
		
		System.out.println("SORT BY");
		System.out.println("1. EMPLOYEE ID");
		System.out.println("2. EMPLOYEE NAME");
		System.out.println("3. EMPLOYEE SALARY");
		System.out.println("4. EMPLOYEE DESIGNATION");
		System.out.println("5. EMPLOYEE DEPT");
		int type = Integer.parseInt(reader.readLine());
		System.out.println("ORDER TYPE(ASC OR DESC)");
		String order = reader.readLine().trim();
		int flag = 0;// default is ascending
		if (order.equals("desc"))
			flag = 1;
		else if (order.equals("asc") || order.equals(""))
			flag = 0;

		switch (type) {

		case 1:

			if (flag == 1) {
				PreparedStatement psSortEnoDesc = con.prepareStatement("select *from emp order by eno desc");
				DisplayEmp.displayEmp(psSortEnoDesc);
			} else {
				PreparedStatement psSortEno = con.prepareStatement("select *from emp order by eno");
				DisplayEmp.displayEmp(psSortEno);
			}
			break;

		case 2:

			if (flag == 1) {
				PreparedStatement psSortEnameDesc = con.prepareStatement("select *from emp order by ename desc");
				DisplayEmp.displayEmp(psSortEnameDesc);
			} else {
				PreparedStatement psSortEname = con.prepareStatement("select *from emp order by ename");
				DisplayEmp.displayEmp(psSortEname);
			}
			break;

		case 3:
			
			if (flag == 1) {
				PreparedStatement psSortSalDesc = con.prepareStatement("select *from emp order by salary desc");
				DisplayEmp.displayEmp(psSortSalDesc);
			} else {
				PreparedStatement psSortSal = con.prepareStatement("select *from emp order by salary");
				DisplayEmp.displayEmp(psSortSal);
			}
			break;

		case 4:
			
			if (flag == 1) {
				PreparedStatement psSortDesgDesc = con.prepareStatement("select *from emp order by designation desc");
				DisplayEmp.displayEmp(psSortDesgDesc);
			} else {
				PreparedStatement psSortDesg = con.prepareStatement("select *from emp order by designation");
				DisplayEmp.displayEmp(psSortDesg);
			}
			break;

		case 5:
			
			if (flag == 1) {
				PreparedStatement psSortDeptDesc = con.prepareStatement("select *from emp order by dept desc");
				DisplayEmp.displayEmp(psSortDeptDesc);
			} else {
				PreparedStatement psSortDept = con.prepareStatement("select *from emp order by dept");
				DisplayEmp.displayEmp(psSortDept);
			}
			break;

		default:
			PreparedStatement psSortDefault = con.prepareStatement("select *from emp order by eno");
			DisplayEmp.displayEmp(psSortDefault);

		}
	}
	
}

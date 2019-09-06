package com.emp.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;


public class EmpMenuHandler {

	
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int i = 1;

		// establishing jdbc connectivity
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DatabaseConnection.getDatabaseConnection();

		while (i != 0) {

			System.out.println("OPTIONS");
			System.out.println("=============================================================");
			System.out.println("1. ADD EMPLOYEE");
			System.out.println("2. VIEW EMPLOYEES");
			System.out.println("3. REMOVE EMPLOYEE");
			System.out.println("4. CLEAR DATA");
			System.out.println("5. CHANGE SALARY");
			System.out.println("6. SEARCH EMPLOYEE");
			System.out.println("7. VIEW DEPARTMENT WISE LIST");
			System.out.println("8. COUNT EMPLOYEES WHOSE SALARY IS GREATER THAN ___");
			System.out.println("9. VIEW SORTED EMPLOYEES ");
			System.out.println("10. EXIT");

			int input = Integer.parseInt(reader.readLine());
			if (input == 10)
				System.exit(0);

			switch (input) {

			case 1:
				// using procedure addEmp to insert into emp relation
				System.out.println("ENTER Emp DETAILS - ");
				System.out.println("ENTER ENO ");
				int eno = Integer.parseInt(reader.readLine());

				System.out.println("ENTER ENAME ");
				String ename = reader.readLine();

				System.out.println("ENTER SALARY ");
				long salary = Integer.parseInt(reader.readLine());

				System.out.println("ENTER DESIGNATION ");
				String designation = reader.readLine();

				System.out.println("ENTER DEPARTMENT NAME ");
				String dept = reader.readLine();

				CallableStatement csInsert = con.prepareCall("{call addEmp(?,?,?,?,?)}");
				csInsert.setInt(1, eno);
				csInsert.setString(2, ename);
				csInsert.setLong(3, salary);
				csInsert.setString(4, designation);
				csInsert.setString(5, dept);

				int checkInsert = csInsert.executeUpdate();
				if (checkInsert != 0)
					System.out.println("Emp ADDED SUCCESSFULLY");

				break;

			case 2:
				// using PreparedStatement and result set to display emp
				PreparedStatement psShow = con.prepareStatement("select *from emp");
				DisplayEmp.displayEmp(psShow);

				break;

			case 3:
				// using PreparedStatement to delete record from emp relation
				System.out.println("ENTER ENO ");
				int eno2 = Integer.parseInt(reader.readLine());
				PreparedStatement psRemove = con.prepareStatement("delete from emp where eno=?");
				psRemove.setInt(1, eno2);
				int checkRemove = psRemove.executeUpdate();
				if (checkRemove != 0)
					System.out.println("Emp REMOVED SUCCESSFULLY");

				break;

			case 4:
				// using Statement to clear data from emp
				Statement stClean = con.createStatement();
				int checkClean = stClean.executeUpdate("delete from emp");
				if (checkClean != 0)
					System.out.println("DATA REMOVED");

				break;

			case 5:
				// using PreparedStatement to update record
				System.out.println("ENTER ENO AND NEW SALARY ");
				int eno3 = Integer.parseInt(reader.readLine());
				long salary3 = Long.parseLong(reader.readLine());
				PreparedStatement psUpdate = con.prepareStatement("update emp set salary=? where eno=?");
				psUpdate.setLong(1, salary3);
				psUpdate.setInt(2, eno3);
				int checkUpdate = psUpdate.executeUpdate();
				if (checkUpdate != 0)
					System.out.println("1 ROW UPDATED");

				break;

			case 6:
				// using procedure with out-parameters to retrieve data
				System.out.println("ENTER ENO ");
				int eno4 = Integer.parseInt(reader.readLine());
				CallableStatement csSearch = con.prepareCall("{call searchEmp(?,?,?,?,?)}");
				csSearch.setInt(1, eno4);
				csSearch.registerOutParameter(2, Types.VARCHAR);
				csSearch.registerOutParameter(3, Types.INTEGER);
				csSearch.registerOutParameter(4, Types.VARCHAR);
				csSearch.registerOutParameter(5, Types.VARCHAR);

				csSearch.execute();
				String ename4 = csSearch.getString(2);
				long salary4 = csSearch.getLong(3);
				String designation4 = csSearch.getString(4);
				String dept4 = csSearch.getString(5);
				Emp emp4 = new Emp(eno4, ename4, salary4, designation4, dept4);
				System.out.println(emp4);

				break;

			case 7:
				// using PreparedStatement to display employee with same department
				System.out.println("ENTER DEPARTMENT NAME ");
				String dept5 = reader.readLine().trim();
				PreparedStatement psDept = con.prepareStatement("select * from emp where dept=?");
				psDept.setString(1, dept5);
				DisplayEmp.displayEmp(psDept);
				break;

			case 8:
				//using function getSalCount()
				System.out.println("ENTER SALARY");
				long salary6 = Long.parseLong(reader.readLine());
				CallableStatement csSalCount = con.prepareCall("{?=call getSalCount(?)}");
				csSalCount.setLong(2, salary6);
				csSalCount.registerOutParameter(1, Types.INTEGER);
				csSalCount.execute();
				int count = csSalCount.getInt(1);
				System.out.println(count);
				break;

			case 9:
				// sorting emp using different field name
				EmpSort.empSort(con,reader);
			default:
				System.out.println("CHOOSE CORRECT OPTION");

			}

		}
		reader.close();
		con.close();
	}

	
}

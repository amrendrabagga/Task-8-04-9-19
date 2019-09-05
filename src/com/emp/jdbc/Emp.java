package com.emp.jdbc;

public class Emp {
	private int eno;
	private String ename;
	private long salary;
	private String designation;
	private String dept;

	public Emp(int eno, String ename, long salary, String designation, String dept) {
		this.eno = eno;
		this.ename = ename;
		this.salary = salary;
		this.designation = designation;
		this.dept = dept;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getEno() {
		return eno;
	}

	public void setEno(int eno) {
		this.eno = eno;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "[eno = " + eno + ", ename = " + ename + ", salary = " + salary + ", designation = " + designation
				+ ", department = " + dept + "]";
	}

}

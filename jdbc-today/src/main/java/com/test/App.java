package com.test;

import javax.swing.JOptionPane;

import com.test.entity.Student;
import com.test.service.StudentDAO;
import com.test.serviceimpl.StudentDAOImpl;

/**
 * DAO ---> DATA ACCESS OBJECT DESIGN PATTERN
 *
 */
public class App {
	public static void main(String[] args) {

		System.out.println();
		// Operations.selectLatestQuery(Integer.parseInt(JOptionPane.showInputDialog("enter
		// deptno", "enter here")));
		// Operations.selectScrollQuery();
//		System.out.println("table NOT created ---> "
//				+ Operations.createTable(JOptionPane.showInputDialog("enter query", "enter here")));
		StudentDAO sd = new StudentDAOImpl();
		Student ss = new Student();
		ss.setId(100);
		ss.setName("Chandrani");
		ss.setAddress("Bangalore");
		if (sd.addStudent(ss))
			JOptionPane.showMessageDialog(null, "Student INSERTED CHECK DB....");
		else
			JOptionPane.showMessageDialog(null, "CHORIE LAAT!!!!....");

	}
}

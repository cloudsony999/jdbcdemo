package com.test.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.test.config.Helper;

public class Operations {

	@Deprecated
	public void selectQuery(int deptno) throws SQLException {

		Connection con = Helper.makeCon();
		PreparedStatement ps = con.prepareStatement("select ename,sal from emp where deptno=?");
		ps.setInt(1, deptno);
		ResultSet rs = ps.executeQuery();
		while (rs.next())
			System.out.println("ename is " + rs.getString("ename") + " sal is " + rs.getInt("sal"));
		con.close();

	}

//try with resource with scrollable resultset which is forward scrollable
	public static void selectLatestQuery(int deptno) {

		try (Connection con = Helper.makeCon(); Connection con2 = Helper.makeCon()) {

			PreparedStatement ps = con.prepareStatement("select ename,sal from emp where deptno=?");
			ps.setInt(1, deptno);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				System.out.println("ename is " + rs.getString("ename") + " sal is " + rs.getInt("sal"));
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	// try with resource with scrollable resultset which is bothway scrollable
	public static void selectScrollQuery() {

		try (Connection con = Helper.makeCon()) {

			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			ResultSet rs = st.executeQuery("select ename, sal from emp");
			while (rs.next())
				System.out.println("ename is " + rs.getString("ename") + " sal is " + rs.getInt("sal"));
			System.out.println("-----------\nnow print backward\n---------------");
			while (rs.previous())
				System.out.println("back ename is " + rs.getString("ename") + " back  sal is " + rs.getInt("sal"));
			System.out.println("-----------\nnow 5th record\n---------------");
			rs.absolute(5);
			System.out.println("5th ename is " + rs.getString("ename") + " 5th  sal is " + rs.getInt("sal"));
			System.out.println("-----------\nnow 8th record\n---------------");
			rs.relative(3);
			System.out.println("8th ename is " + rs.getString("ename") + " 8th  sal is " + rs.getInt("sal"));
			System.out.println("-----------\n WHERE I AM \n---------------");
			System.out.println("I am in " + rs.getRow());
			System.out.println("-----------\n 4th record\n---------------");
			rs.relative(-4);
			System.out.println("4th ename is " + rs.getString("ename") + " 4th  sal is " + rs.getInt("sal"));
			System.out.println("-----------\nnow print 1st to last\n---------------");
			rs.beforeFirst();
			while (rs.next())
				System.out.println("ename is " + rs.getString("ename") + " sal is " + rs.getInt("sal"));

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	public static boolean createTable(String s) {
		boolean b = false;
		try (Connection con = Helper.makeCon()) {
			Statement st = con.createStatement();
			b = st.execute(s);// IF IT DON'T RETURN ANY RESULTSET OBJECT THEN IT WILL RETURN FALSE ELSE TRUE
		} catch (SQLException e) {

			System.out.println(e);
		}

		return b;

	}

}

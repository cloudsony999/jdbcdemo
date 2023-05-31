package com.test.serviceimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.test.config.Helper;
import com.test.entity.Student;
import com.test.service.StudentDAO;

public class StudentDAOImpl implements StudentDAO {

	@Override
	public boolean addStudent(Student st) {
		boolean status = false;
		try (Connection con = Helper.makeCon()) {

			PreparedStatement ps = con.prepareStatement("insert into student123 values(?,?,?)");
			ps.setInt(1, st.getId());
			ps.setString(2, st.getName());
			ps.setString(3, st.getAddress());
			int noOfRowsInserted = ps.executeUpdate();
			if (noOfRowsInserted > 0)
				status = true;
			else
				status = false;

		} catch (SQLException e) {
			System.out.println(e);
		}
		return status;
	}

}

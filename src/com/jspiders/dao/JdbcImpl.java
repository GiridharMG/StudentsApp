package com.jspiders.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jspiders.dto.SecurityQuestion;
import com.jspiders.dto.StudentBean;

public class JdbcImpl implements StudentDAO {
	public void resetAttempt(int regno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Driver ref = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(ref);

			String dbURL = "jdbc:mysql://localhost:3306/students_db?user=root&password=root";
			con = DriverManager.getConnection(dbURL);
			
			String sql = "update students_otherinfo set count=0 where regno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, regno);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public boolean checkAttempt(int regno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Driver ref = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(ref);

			String dbURL = "jdbc:mysql://localhost:3306/students_db?user=root&password=root";
			con = DriverManager.getConnection(dbURL);
			
			String sql = "select count from students_otherinfo where regno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, regno);
			rs = pstmt.executeQuery();
			int count = 0;
			if(rs.next()) {
				count = rs.getInt(1);
			}
			if(count<3) 
				return false;
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public StudentBean athenticate(int regno, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Driver ref = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(ref);

			String dbURL = "jdbc:mysql://localhost:3306/students_db?user=root&password=root";
			con = DriverManager.getConnection(dbURL);

			String sql = "select * from students_info si, " + " guardian_info gi, students_otherinfo so"
					+ " where si.regno=gi.regno and gi.regno=so.regno" + " and si.regno=? and so.password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, regno);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			StudentBean data = null;
			if (rs.next()) {
				data = new StudentBean();
				data.setRegno(rs.getInt("regno"));
				data.setFirstName(rs.getString("firstname"));
				data.setMiddleName(rs.getString("middlename"));
				data.setLastName(rs.getString("lastname"));
				data.setgFirstName(rs.getString("gfirstname"));
				data.setgMiddleName(rs.getString("gmiddlename"));
				data.setgLastName(rs.getString("glastname"));
				data.setIsAdmin(rs.getString("isadmin"));
				data.setPassword(rs.getString("password"));
				data.setSecurityCheck(rs.getString("security_check"));
				resetAttempt(regno);
			} else {
				attemptIncrease(regno);
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void insertSecurityQuestions(StudentBean data, SecurityQuestion[] questions) {
		String sql = "insert into students_securityinfo values(?,?,?,?)";
		String sql1 = "update students_otherinfo set security_check=? where regno=?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		try {
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_db?user=root&password=root");
			
			pstmt = con.prepareStatement(sql);
			for (SecurityQuestion securityQuestion : questions) {
				pstmt.setInt(1, data.getRegno());
				pstmt.setInt(2, securityQuestion.getQuestion_no());
				pstmt.setString(3, securityQuestion.getQuestion());
				pstmt.setString(4, securityQuestion.getAnswer());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setString(1, "Y");
			pstmt1.setInt(2, data.getRegno());
			pstmt1.executeUpdate();
			data.setSecurityCheck("Y");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt1 != null) {
				try {
					pstmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public boolean createProfile(StudentBean data) {
		String sql1 = "insert into students_info values(?,?,?,?)";
		String sql2 = "insert into guardian_info values(?,?,?,?)";
		String sql3 = "insert into students_otherinfo values(?,?,?,?,?)";

		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		try {
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_db?user=root&password=root");
			con.setAutoCommit(false);

			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, data.getRegno());
			pstmt1.setString(2, data.getFirstName());
			pstmt1.setString(3, data.getMiddleName());
			pstmt1.setString(4, data.getLastName());
			pstmt1.executeUpdate();

			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, data.getRegno());
			pstmt2.setString(2, data.getgFirstName());
			pstmt2.setString(3, data.getgMiddleName());
			pstmt2.setString(4, data.getgLastName());
			pstmt2.executeUpdate();

			pstmt3 = con.prepareStatement(sql3);
			pstmt3.setInt(1, data.getRegno());
			pstmt3.setString(3, data.getPassword());
			pstmt3.setString(2, data.getIsAdmin());
			pstmt3.setInt(4, 0);
			pstmt3.setString(5, "N");
			pstmt3.executeUpdate();
			
			con.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt1 != null) {
				try {
					pstmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public StudentBean getStudentDetails(int regno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Driver ref = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(ref);

			String dbURL = "jdbc:mysql://localhost:3306/students_db?user=root&password=root";
			con = DriverManager.getConnection(dbURL);

			String sql = "select * from students_info si, " + " guardian_info gi, students_otherinfo so"
					+ " where si.regno=gi.regno and gi.regno=so.regno" + " and si.regno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, regno);

			rs = pstmt.executeQuery();

			StudentBean data = null;
			if (rs.next()) {
				data = new StudentBean();
				data.setRegno(rs.getInt("regno"));
				data.setFirstName(rs.getString("firstname"));
				data.setMiddleName(rs.getString("middlename"));
				data.setLastName(rs.getString("lastname"));
				data.setgFirstName(rs.getString("gfirstname"));
				data.setgMiddleName(rs.getString("gmiddlename"));
				data.setgLastName(rs.getString("glastname"));
				data.setIsAdmin(rs.getString("isadmin"));
				data.setPassword(rs.getString("password"));
			}
			return data;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<StudentBean> getAllStudentDetails() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. Load the Driver
			// Driver Class : com.mysql.jdbc.Driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// 2. Get the DB Connection via Driver
			String dbUrl = "jdbc:mysql://localhost:3306/students_db?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);

			// 3. Issue SQL Queries via Connection
			String sql = "select * from students_info si, " + " guardian_info gi, students_otherinfo so"
					+ " where si.regno=gi.regno and gi.regno=so.regno";

			System.out.println("Query : " + sql);

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			ArrayList<StudentBean> list = new ArrayList<>();
			while (rs.next()) {
				StudentBean data = new StudentBean();
				data.setRegno(rs.getInt("regno"));
				data.setFirstName(rs.getString("firstname"));
				data.setMiddleName(rs.getString("middlename"));
				data.setLastName(rs.getString("lastname"));
				data.setgFirstName(rs.getString("gfirstname"));
				data.setgMiddleName(rs.getString("gmiddlename"));
				data.setgLastName(rs.getString("glastname"));
				data.setIsAdmin(rs.getString("isadmin"));
				data.setPassword(rs.getString("password"));
				data.setAttempt(rs.getInt("count"));
				list.add(data);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// 5. Close All JDBC Objects
			try {
				if (con != null) {
					con.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // End of outer try-catch block
	}

	@Override
	public void deleteStudent(int regno) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		String sql1 = "delete from students_info where regno = ?";
		String sql2 = "delete from guardian_info where regno = ?";
		String sql3 = "delete from students_otherinfo where regno = ?";
		try {
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);

			String dbUrl = "jdbc:mysql://localhost:3306/students_db?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);

			con.setAutoCommit(false);

			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, regno);
			int count1 = pstmt1.executeUpdate();

			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, regno);
			int count2 = pstmt2.executeUpdate();

			pstmt3 = con.prepareStatement(sql3);
			pstmt3.setInt(1, regno);
			int count3 = pstmt3.executeUpdate();
			
			if(count1>0 && count2>0 && count3>0) {
				con.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt1 != null) {
				try {
					pstmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt3 != null) {
				try {
					pstmt3.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean changePassword(int regno, String oldPassword, String newPassword) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Driver driver = (Driver)Class
					.forName("com.mysql.jdbc.Driver")
					.newInstance();
			DriverManager.registerDriver(driver);
			
			String dbUrl = "jdbc:mysql://localhost:3306/students_db?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);
			
			String sql = "update students_otherinfo "
					+ " set password=? where regno=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, regno);
			pstmt.setString(3, oldPassword);
			int count = pstmt.executeUpdate();
			
			if(count>0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateStudent(StudentBean data) {
		String sql1 = "update students_info set firstname=?, "
				+ " middlename=?, lastname=? where regno=?";
		String sql2 = "update guardian_info set gfirstname=?, "
				+ " gmiddlename=?, glastname=? where regno=?";
		String sql3 = "update students_otherinfo set isadmin=?,"
				+ " password=? where regno=?";
		
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		
		try {
			Driver driver = (Driver)Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			
			String dbURL = "jdbc:mysql://localhost:3306/students_db?user=root&password=root";
			con = DriverManager.getConnection(dbURL);
			
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setString(1, data.getFirstName());
			pstmt1.setString(2, data.getMiddleName());
			pstmt1.setString(3, data.getLastName());
			pstmt1.setInt(4, data.getRegno());
			
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setString(1, data.getgFirstName());
			pstmt2.setString(2, data.getgMiddleName());
			pstmt2.setString(3, data.getgLastName());
			pstmt2.setInt(4, data.getRegno());
			
			pstmt3 = con.prepareStatement(sql3);
			pstmt3.setString(1, data.getIsAdmin());
			pstmt3.setString(2, data.getPassword());
			pstmt3.setInt(3, data.getRegno());
			
			pstmt1.executeUpdate();
			pstmt2.executeUpdate();
			pstmt3.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt1!=null){
				try {
					pstmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt2!=null){
				try {
					pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt3!=null){
				try {
					pstmt3.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public List<SecurityQuestion> securityQuection(int regno) {
		List<SecurityQuestion> list = new ArrayList<SecurityQuestion>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Driver ref = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(ref);

			String dbURL = "jdbc:mysql://localhost:3306/students_db?user=root&password=root";
			con = DriverManager.getConnection(dbURL);
			
			String sql = "select * from Students_securityinfo where regno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, regno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SecurityQuestion question = new SecurityQuestion();
				question.setQuestion_no(rs.getInt("question_no"));
				question.setQuestion(rs.getString("question"));
				question.setAnswer(rs.getString("answer"));
				list.add(question);
			}
			return list;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void attemptIncrease(int regno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Driver driver = (Driver)Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			
			String dbURL = "jdbc:mysql://localhost:3306/students_db?user=root&password=root";
			con = DriverManager.getConnection(dbURL);
			
			String sql = "update students_otherinfo set count=count+1 where regno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, regno);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public boolean securityCheck(List<SecurityQuestion>list, int regno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Driver ref = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(ref);

			String dbURL = "jdbc:mysql://localhost:3306/students_db?user=root&password=root";
			con = DriverManager.getConnection(dbURL);

			String sql = "select * from students_securityinfo "
					+ " where regno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, regno);

			rs = pstmt.executeQuery();
			boolean check1 = false;
			boolean check2 = false;
			while (rs.next()) {
				if((rs.getString("question").equals(list.get(0).getQuestion())&&rs.getString("answer").equals(list.get(0).getAnswer()))) {
					check1 = true;
				}
				if((rs.getString("question").equals(list.get(1).getQuestion())&&rs.getString("answer").equals(list.get(1).getAnswer()))) {
					check2 = true;
				}
			}
			if(check1&&check2)
				return true;
			else 
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void resetPassword(int regno, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Driver driver = (Driver)Class
					.forName("com.mysql.jdbc.Driver")
					.newInstance();
			DriverManager.registerDriver(driver);
			
			String dbUrl = "jdbc:mysql://localhost:3306/students_db?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);
			
			String sql = "update students_otherinfo set password=? where regno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setInt(2, regno);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

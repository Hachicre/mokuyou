package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User extends BaseDao {

	public User() {
		super();
	}

	public ArrayList<dto.User> findAll() {

		Connection con = createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		dto.User data;
		java.util.ArrayList<dto.User> list = new ArrayList<dto.User>();

		try {

			if(con != null) {

				stmt  = con.createStatement();

				rs = stmt.executeQuery("SELECT * FROM user");

				while(rs.next()) {
					String id        = rs.getString("id");
					String name      = rs.getString("name");
					String email	= rs.getString("email");
					String password     = rs.getString("password");
					data = new dto.User(
						Integer.valueOf(id),
						name,
						email,
						password
					);
					list.add(data);
				}
			}
		} catch(SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
						rs.close();
				}
				if(stmt != null) {
						stmt.close();
				}
			} catch(SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return list;
	}


	public int registration(dto.User user) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		String sql = null;
		int ret = -1;

		try {
			if(con != null) {
				sql = "INSERT INTO user VALUES(?, ?, ?, ?)";

				pstmt  = con.prepareStatement(sql);

				pstmt.setInt(1, user.getId());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getPassword());

				ret  = pstmt.executeUpdate();
			}
		}catch(SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {

			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}

		}

		closeConnection(con);

		return ret;

	}
}


package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Plan extends BaseDao {

	public Plan() {
		super();
	}

	public ArrayList<dto.Plan> findAll() {

		Connection con = createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		dto.Plan data;
		java.util.ArrayList<dto.Plan> list = new ArrayList<dto.Plan>();

		try {

			if(con != null) {

				stmt  = con.createStatement();

				rs = stmt.executeQuery("SELECT * FROM plan");

				while(rs.next()) {
					String id        = rs.getString("id");
					String coefficient      = rs.getString("coefficient");
					String period     = rs.getString("period");
					data = new dto.Plan(
						Integer.valueOf(id),
						Integer.valueOf(coefficient),
						Integer.valueOf(period)
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


	public int registration(dto.Plan plan) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		String sql = null;
		int ret = -1;

		try {
			if(con != null) {
				sql = "INSERT INTO plan VALUES(?, ?, ?)";

				pstmt  = con.prepareStatement(sql);

				pstmt.setInt(1, plan.getId());
				pstmt.setDouble(2, plan.getCoefficient());
				pstmt.setInt(3, plan.getPeriod());

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

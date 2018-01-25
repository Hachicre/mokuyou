package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Settlement extends BaseDao {

	public Settlement() {
		super();
	}

	public ArrayList<dto.Settlement> findAll() {

		Connection con = createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		dto.Settlement data;
		java.util.ArrayList<dto.Settlement> list = new ArrayList<dto.Settlement>();

		try {

			if(con != null) {

				stmt  = con.createStatement();

				rs = stmt.executeQuery("SELECT * FROM settlement");

				while(rs.next()) {
					String id        = rs.getString("id");
					String user_id      = rs.getString("user_id");
					String rentalStartAt = rs.getString("rental_start_at");
					String price	= rs.getString("price");
					boolean beReturned	= rs.getBoolean("beReturned");
					data = new dto.Settlement(
						Integer.valueOf(id),
						Integer.valueOf(user_id),
						Integer.valueOf(rentalStartAt),
						Integer.valueOf(price),
						beReturned
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


	public int registration(dto.Settlement settlement) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		String sql = null;
		int ret = -1;

		try {
			if(con != null) {
				sql = "INSERT INTO settlement VALUES(?, ?, ?, ?, ?)";

				pstmt  = con.prepareStatement(sql);

				pstmt.setInt(1, settlement.getId());
				pstmt.setInt(2, settlement.getUserId());
				pstmt.setInt(3, settlement.getRentalStartAt());
				pstmt.setInt(4, settlement.getPrice());
				pstmt.setBoolean(5, settlement.getBeReturned());

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

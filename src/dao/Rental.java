package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Rental extends BaseDao {

	public Rental() {
		super();
	}

	public ArrayList<dto.Rental> findAll() {

		Connection con = createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		dto.Rental data;
		java.util.ArrayList<dto.Rental> list = new ArrayList<dto.Rental>();

		try {

			if(con != null) {

				stmt  = con.createStatement();

				rs = stmt.executeQuery("SELECT * FROM rental");

				while(rs.next()) {
					String id        = rs.getString("id");
					String settlementId      = rs.getString("settlement_id");
					String productId = rs.getString("product_id");
					String planId = rs.getString("plan_id");
					data = new dto.Rental(
						Integer.valueOf(id),
						Integer.valueOf(settlementId),
						Integer.valueOf(productId),
						Integer.valueOf(planId)
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


	public int registration(dto.Rental rental) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		String sql = null;
		int ret = -1;

		try {
			if(con != null) {
				sql = "INSERT INTO product VALUES(?, ?, ?, ?)";

				pstmt  = con.prepareStatement(sql);

				pstmt.setInt(1, rental.getId());
				pstmt.setInt(2, rental.getSettlementId());
				pstmt.setInt(3, rental.getProductId());
				pstmt.setInt(4, rental.getPlanId());

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

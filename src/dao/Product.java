package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Product extends BaseDao {
	
	public Product() {
		super();
	}

	public ArrayList<dto.Product> findAll() {
		
		Connection con = createConnection();
		Statement stmt = null;
		ResultSet rs = null;
		dto.Product data;
		java.util.ArrayList<dto.Product> list = new ArrayList<dto.Product>();

		try {
			
			if(con != null) {

				stmt  = con.createStatement();

				rs = stmt.executeQuery("SELECT * FROM product");

				while(rs.next()) {
					String id        = rs.getString("id");
					String name      = rs.getString("name");
					String stock     = rs.getString("stock");
					String price     = rs.getString("price");
					String createdAt = rs.getString("created_at");
					String updatedAt = rs.getString("updated_at");
					data = new dto.Product(
						Integer.valueOf(id),
						name,
						Integer.valueOf(stock),
						Integer.valueOf(price),
						createdAt,
						updatedAt
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
	

	public int registration(dto.Product product) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		String sql = null;
		int ret = -1;
		
		try {
			if(con != null) {
				sql = "INSERT INTO product VALUES(?, ?, ?, ?)";

				pstmt  = con.prepareStatement(sql);

				pstmt.setInt(1, product.getId());
				pstmt.setString(2, product.getName());
				pstmt.setInt(3, product.getStock());
				pstmt.setInt(4, product.getPrice());

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

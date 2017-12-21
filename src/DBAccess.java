import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBAccess {
	private Connection createConnection() {
	// 変数宣言
		Connection con = null;
		try {
				// DBドライバクラスのロード
				Class.forName("com.mysql.jdbc.Driver"); //MYSQl用のJDBCドライバをロード

				// コネクションオブジェクトの生成
				con = DriverManager.getConnection("jdbc:mysql://localhost:65534/TestDB", "user1", "pass1");
																														// ↑httpでのwebアクセス方法に似てる？

		}catch(ClassNotFoundException e) {
				System.out.println("JDBCドライバが見つかりません。");
				e.printStackTrace();
		}catch(SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
		}
		return con;
	}
	private void closeConnection(Connection con) {
	try {
			if(con != null) {
					con.close();
			}
	}catch(SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
	}
	}
	public ArrayList<shosekiDBSystem> findAll() {
			Connection con = createConnection();
			Statement stmt = null;
			ResultSet rs = null;
			shosekiDBSystem data;
			java.util.ArrayList<shosekiDBSystem> list = new ArrayList<shosekiDBSystem>();

			try {
					if(con != null) {

							stmt  = con.createStatement();

							rs = stmt.executeQuery("SELECT * FROM shosekiDB");

							while(rs.next()) {
									String ISBN = rs.getString("ISBN");
									String bookname = rs.getString("BOOKNAME");
									String writer = rs.getString("WRITER");
									String publisher = rs.getString("PUBLISHER");
									String year = rs.getString("YEAR");
									String month = rs.getString("MONTH");
									String day = rs.getString("DAY");
									data = new shosekiDBSystem(Integer.parseInt(ISBN),
																bookname,
																writer,
																publisher,
																Integer.parseInt(year),
																Integer.parseInt(month),
																Integer.parseInt(day));
									list.add(data);
							}
					}
			}catch(SQLException e) {
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
					}catch(SQLException e) {
							System.out.println("DBアクセス時にエラーが発生しました。");
							e.printStackTrace();
					}
			}
			closeConnection(con);
			return list;
	}

	public int registration(shosekiDBSystem shoseki) {
			Connection con = createConnection();
			PreparedStatement pstmt = null;
			String sql = null;
			int ret = -1;
			try {
					if(con != null) {
						sql = "INSERT INTO SHOSEKIDB VALUES(?, ?, ?, ?, ?, ?, ?)";

						pstmt  = con.prepareStatement(sql);

						pstmt.setInt(1,shoseki.getISBN());
						pstmt.setString(2,shoseki.getBookname());
						pstmt.setString(3,shoseki.getWriter());
						pstmt.setString(4,shoseki.getPublisher());
						pstmt.setInt(5,shoseki.getYear());
						pstmt.setInt(6,shoseki.getMonth());
						pstmt.setInt(7,shoseki.getDay());

						ret  = pstmt.executeUpdate();
					}
			}catch(SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}finally {
				try {
					if(pstmt != null) {
							pstmt.close();
					}
			}catch(SQLException e) {
					System.out.println("DBアクセス時にエラーが発生しました。");
					e.printStackTrace();
			}
			}
			closeConnection(con);
			return ret;

			}
}
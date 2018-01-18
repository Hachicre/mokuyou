package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {

	protected Connection createConnection() {
		
		Connection con = null;
		try {
			// DBドライバクラスのロード
			Class.forName("com.mysql.jdbc.Driver"); //MYSQl用のJDBCドライバをロード

			// コネクションオブジェクトの生成
			// TODO 環境に合わせて変更してください　
			con = DriverManager.getConnection("jdbc:mysql://localhost:65534/tatsuya", "user1", "pass1");
																														// ↑httpでのwebアクセス方法に似てる？

		} catch(ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch(SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
		
		return con;
	}
	
	protected void closeConnection(Connection con) {
		try {
			if(con != null) {
				con.close();
			}
		} catch(SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
	}
}

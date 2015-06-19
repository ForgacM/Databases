package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by marcelforgac on 1.4.15.
 */
public abstract class DbUtil {

	private static Connection connection = null;

	public static Connection getConnection() {

		if (connection != null)
			return connection;
		else {
			try {
				String driver = "com.mysql.jdbc.Driver";
				String url = "jdbc:mysql://mariadb55.websupport.sk:3310/ikvb6ju5";
				String user = "ikvb6ju5";
				String password = "eJG-nzG-TK4-9bX";
				/*String url = "jdbc:mysql://localhost:3306/drug_table";
				String user = "root";
				String password = "root";*/
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			return connection;

		}
	}
}

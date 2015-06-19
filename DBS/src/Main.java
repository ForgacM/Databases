import controller.MainWindowController;
import dao.UserSessionDao;
import view.MainWindow;

import java.sql.SQLException;

/**
 * Created by marcelforgac on 31.3.15.
 */
public class Main {

	public static void main(String[] args) throws SQLException {
		UserSessionDao sess = new UserSessionDao();
		sess.deleteSession();

		MainWindowController wind = new MainWindowController(new MainWindow());
	}
}

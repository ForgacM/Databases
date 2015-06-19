package controller;

import dao.UserSessionDao;
import dao.UsersDao;
import model.User;
import view.Login;
import view.MainWindow;
import view.Registration;

/**
 * Created by marcelforgac on 1.4.15.
 */
public class LoginController {

	private Login view;

	public LoginController(final Login view) {
		this.view = view;
		final UsersDao model = new UsersDao();
		final UserSessionDao userSession = new UserSessionDao();

		view.loginButton.addActionListener(e -> {
			User user = model.getUserByName(view.textField1.getText());

			if (user.getFirstName() != null) {
				userSession.loginUser(user);
				view.dispose();
				new MainWindowController(new MainWindow());
			} else view.label.setText("Neexistuje Zadaný používateľ");
		});

		view.registrationButton.addActionListener(e -> {
			new RegistrationController(new Registration(), 0);
		});
	}
}

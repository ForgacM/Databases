package controller;

import dao.UsersDao;
import model.User;
import view.Registration;

/**
 * Created by marcelforgac on 1.4.15.
 */
public class RegistrationController {

	Registration view;

	User usr = new User();

	public RegistrationController(final Registration view, int doctorId) {
		this.view = view;
		final UsersDao userDbc = new UsersDao();

		if (doctorId > 0)  {
			view.registerButton.addActionListener(e -> {
				usr.setFirstName(view.formattedTextField1.getText());
				usr.setLastName(view.formattedTextField2.getText());
				usr.setPoistovna(view.formattedTextField3.getText());
				userDbc.addUser(usr);
				view.dispose();
			});
		} else {
			view.registerButton.addActionListener(e -> {
				usr.setFirstName(view.formattedTextField1.getText());
				usr.setLastName(view.formattedTextField2.getText());
				usr.setPoistovna(view.formattedTextField3.getText());
				userDbc.addUser(usr);
				view.dispose();
			});
		}

	}
}

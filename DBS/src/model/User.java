package model;

/**
 * Created by marcelforgac on 1.4.15.
 */
public class User {

	private int userId;
	private String firstName;
	private String lastName;
	private String poistovna;
	private Boolean recept;

	public void setPoistovna(String poistovna) {
		this.poistovna = poistovna;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPoistovna() {
		return poistovna;
	}

	public int getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}

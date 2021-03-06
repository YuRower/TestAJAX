package entity;

public class User extends AbstractEntity {

	private String name;
	private String surname;
	private String email;
	private String password;
	private int userTypeId;
	private int userStatusId;

	public User() {}
	
	public User(String name, String surname, String email,  String password) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String login) {
		this.email = login;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public int getUserStatusId() {
		return userStatusId;
	}

	public void setUserStatusId(int userStatusId) {
		this.userStatusId = userStatusId;
	}

	@Override
	public String toString() {
		return "User [id=" + getId() + ", name=" + name + ", surname=" + surname + ", email=" + email+ ", userTypeId=" + userTypeId
				+ ", userStatusId=" + userStatusId + "]";
	}

}
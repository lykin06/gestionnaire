package Gestion;

import Serialization.Text;

import java.util.ArrayList;

import Personnel.Administrator;
import Personnel.Personnel;
import Personnel.Studient;
import Personnel.Teacher;

/**
 * Generate user's list
 * 
 * @author Amir BEN SLIMANE
 *
 */
public class Database {
	private ArrayList<Teacher> teachers;
	private ArrayList<Studient> studients;
	private ArrayList<Administrator> administrators;
	private Personnel currentUser;

	public Database() {
		setTeachers(new ArrayList<Teacher>());
		setStudients(new ArrayList<Studient>());
		setAdministrators(new ArrayList<Administrator>());
		load();
	}

	public void load() {
		String[] bdd = Text.load("users");

		String[] line;
		String nature, firstName, name, email, password;
		int identifiant;

		for (int i = 0; i < bdd.length; i++) {
			line = bdd[i].split(";");

			if (line.length != 6) {
				continue;
			}

			try {
				nature = line[0].trim();
				firstName = line[1].trim();
				name = line[2].trim();
				email = line[3].trim();
				password = line[4].trim();
				identifiant = Integer.parseInt(line[5].trim());

				if (nature.equals("administrateur")) {
					administrators.add(new Administrator(firstName, name,
							email, password, identifiant));
				} else if (nature.equals("etudiant")) {
					studients.add(new Studient(firstName, name, email,
							password, identifiant));
				} else if (nature.equals("enseignant")) {
					teachers.add(new Teacher(firstName, name, email, password,
							identifiant));
				}
			} catch (Exception e) {
			}
		}
	}

	public boolean isAuthorized(String email, String password) {
		for (int i = 0; i < this.getAdministrators().size(); i++) {
			this.setCurrentUser(this.getAdministrators().get(i));
			if (this.getCurrentUser().getEmail().equals(email)
					&& this.getCurrentUser().getPassword().equals(password)) {
				return true;
			}
		}

		for (int i = 0; i < this.getTeachers().size(); i++) {
			this.setCurrentUser(this.getTeachers().get(i));
			if (getCurrentUser().getEmail().equals(email)
					&& getCurrentUser().getPassword().equals(password)) {
				return true;
			}
		}

		for (int i = 0; i < this.getStudients().size(); i++) {
			this.setCurrentUser(this.getStudients().get(i));
			if (getCurrentUser().getEmail().equals(email)
					&& getCurrentUser().getPassword().equals(password)) {
				return true;
			}
		}

		return false;
	}

	public boolean addUser(Personnel user) {
		boolean b;
		String[] text = new String[6];

		text[0] = user.toString();
		text[1] = user.getFirstName();
		text[2] = user.getName();
		text[3] = user.getEmail();
		text[4] = user.getPassword();
		text[5] = Integer.toString(user.getIdentifiant());

		b = Text.store(text, "users");
		load();
		return b;
	}

	public void removeUser(Personnel user) {
		String[] bdd = Text.load("users");
		int line = -1;

		for (int i = 0; i < bdd.length; i++) {
			if (bdd[i].contains(user.getEmail())) {
				line = i;
				break;
			}
		}

		try {
			Text.deleteLine("users", line);
		} catch (Exception e) {
		}

		load();
	}

	/**
	 * @return the teachers
	 */
	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	/**
	 * @param teachers
	 *            the teachers to set
	 */
	public void setTeachers(ArrayList<Teacher> teachers) {
		this.teachers = teachers;
	}

	/**
	 * @return the studients
	 */
	public ArrayList<Studient> getStudients() {
		return studients;
	}

	/**
	 * @param studients
	 *            the studients to set
	 */
	public void setStudients(ArrayList<Studient> studients) {
		this.studients = studients;
	}

	/**
	 * @return the administrators
	 */
	public ArrayList<Administrator> getAdministrators() {
		return administrators;
	}

	/**
	 * @param administrators
	 *            the administrators to set
	 */
	public void setAdministrators(ArrayList<Administrator> administrators) {
		this.administrators = administrators;
	}

	/**
	 * @return the currentUser
	 */
	public Personnel getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser
	 *            the currentUser to set
	 */
	public void setCurrentUser(Personnel currentUser) {
		this.currentUser = currentUser;
	}
}

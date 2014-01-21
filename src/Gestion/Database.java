package Gestion;

import java.util.ArrayList;

import Personnel.Administrator;
import Personnel.Personnel;
import Personnel.Student;
import Personnel.Teacher;
import Personnel.Unknown;
import Serialization.Text;

/**
 * Generate user's list
 * 
 * @author Amir BEN SLIMANE
 * 
 */
public class Database {
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> studients;
    private ArrayList<Administrator> administrators;
    private ArrayList<Unknown> unknown;
    private Personnel currentUser;

    public Database() {
        setTeachers(new ArrayList<Teacher>());
        setStudients(new ArrayList<Student>());
        setAdministrators(new ArrayList<Administrator>());
        setUnknown(new ArrayList<Unknown>());
        load();
    }

    /**
     * Load the users from the file to their list
     */
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

                switch (nature) {
                case "administrateur":
                    administrators.add(new Administrator(firstName, name,
                            email, password, identifiant));
                    break;
                case "etudiant":
                    studients.add(new Student(firstName, name, email,
                            password, identifiant));
                    break;
                case "enseignant":
                    teachers.add(new Teacher(firstName, name, email, password,
                            identifiant));
                    break;
                case "inconnu":
                    try {
                        unknown.add(addUnknown(firstName, name, email,
                                password, identifiant));
                    } catch (Exception e) {
                        // This should not happened
                        System.out
                                .println("identifiant invalide, utilisateur supprime");
                        removeUser(new Personnel(firstName, name, email,
                                password, identifiant));
                    }
                    break;
                }

            } catch (Exception e) {
            }
        }
    }

    /**
     * <b>Add a new unknown user to the list.</b>
     * <p>
     * The user's type is determine by its identifiant :
     * <ol>
     * <li>type = administrateur</li>
     * <li>type = etudiant</li>
     * <li>type = enseignant</li>
     * <li>(or more) this should not happened</li>
     * </ol>
     * </p>
     * 
     * @param firstName
     * @param name
     * @param email
     * @param password
     * @param identifiant
     * @return an unknown user
     * @throws Exception
     *             if the identifant is not between 1 and 3
     */
    private Unknown addUnknown(String firstName, String name, String email,
            String password, int identifiant) throws Exception {
        switch (identifiant) {
        case 1:
            return new Unknown("administrateur", firstName, name, email,
                    password, identifiant);
        case 2:
            return new Unknown("etudiant", firstName, name, email, password,
                    identifiant);
        case 3:
            return new Unknown("enseignant", firstName, name, email, password,
                    identifiant);
        default:
            throw new Exception("bad identifiant");
        }
    }

    /**
     * <b>Check if the user is authorized</b>
     * <p>
     * To be authorized your inscription have to be confirmed by an
     * administrator.
     * </p>
     * 
     * @param email
     * @param password
     * @return true if the user is authorized
     */
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

        // An unknown user is not authorized
        return false;
    }

    /**
     * <b>Add a new user to the file</b>
     * 
     * @param user
     *            the user to add
     * @return the result of the store
     */
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

    /**
     * <b>Remove a user from the file</b>
     * 
     * @param user
     *            the user to remove
     */
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
    public ArrayList<Student> getStudients() {
        return studients;
    }

    /**
     * @param studients
     *            the studients to set
     */
    public void setStudients(ArrayList<Student> studients) {
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
     * @return the unknown
     */
    public ArrayList<Unknown> getUnknown() {
        return unknown;
    }

    /**
     * @param unknown
     *            the unknown to set
     */
    public void setUnknown(ArrayList<Unknown> unknown) {
        this.unknown = unknown;
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

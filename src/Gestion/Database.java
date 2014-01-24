package Gestion;

import java.util.ArrayList;

import Personnel.Administrator;
import Personnel.Borrower;
import Personnel.Personnel;
import Personnel.Student;
import Personnel.Teacher;
import Personnel.Unknown;
import Serialization.Text;

/**
 * <b>Generate list for all user types </b>
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Database {
    /**
     * List of users who are teacher
     */
    private ArrayList<Personnel> teachers;

    /**
     * List of users who are student
     */
    private ArrayList<Personnel> students;

    /**
     * List of users who are administrator
     */
    private ArrayList<Personnel> administrators;

    /**
     * List of user who are not confirmed by an administrator
     */
    private ArrayList<Unknown> unknown;

    /**
     * The current user
     */
    private Personnel currentUser;

    /**
     * <b>Constructor</b>
     * <p>
     * Generate all the list with the load method.
     * </p>
     * 
     * @see Database#load();
     */
    public Database() {
        this.administrators = new ArrayList<Personnel>();
        this.students = new ArrayList<Personnel>();
        this.teachers = new ArrayList<Personnel>();
        this.unknown = new ArrayList<Unknown>();
        this.load();
    }

    /**
     * <b>Load the users</b>
     * <p>
     * All the users are store in a text file.
     * </p>
     * 
     * @see Text#load(String)
     */
    public void load() {
        // Load the file
        String[] bdd = Text.load("users");

        String[] line;
        String nature, firstName, name, email, password;
        int identifiant, delay;

        for (int i = 0; i < bdd.length; i++) {
            line = bdd[i].split(";");

            if (line.length != 7) {
                continue;
            }

            try {
                nature = line[0].trim();
                firstName = line[1].trim();
                name = line[2].trim();
                email = line[3].trim();
                password = line[4].trim();
                identifiant = Integer.parseInt(line[5].trim());
                delay = Integer.parseInt(line[6].trim());

                switch (nature) {
                case "administrateur":
                    administrators.add(new Administrator(firstName, name,
                            email, password, identifiant));
                    break;
                case "etudiant":
                    students.add(new Student(firstName, name, email, password,
                            identifiant, delay));
                    break;
                case "enseignant":
                    teachers.add(new Teacher(firstName, name, email, password,
                            identifiant, delay));
                    break;
                case "inconnu":
                    try {
                        unknown.add(this.addUnknown(firstName, name, email,
                                password, identifiant));
                    } catch (Exception e) {
                        // This should not happened
                        System.out
                                .println("identifiant invalide, utilisateur supprime");
                        this.removeUser(new Personnel(firstName, name, email,
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

        if (this.searchList(administrators, email, password)) {
            return true;
        }

        if (this.searchList(students, email, password)) {
            return true;
        }

        if (this.searchList(teachers, email, password)) {
            return true;
        }

        // An unknown user is not authorized
        return false;
    }

    /**
     * <b>Search if the user exists in the list</b>
     * 
     * @param list
     *            the list to search
     * @param email
     *            the user's email
     * @param password
     *            the user's password
     * @return true if the user is in the list
     */
    public boolean searchList(ArrayList<Personnel> list, String email,
            String password) {
        for (int i = 0; i < list.size(); i++) {
            this.setCurrentUser(list.get(i));
            if (currentUser.getEmail().equals(email)
                    && currentUser.getPassword().equals(password)) {
                return true;
            }
        }
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
        String[] text = new String[7];

        text[0] = user.toString();
        text[1] = user.getFirstName();
        text[2] = user.getName();
        text[3] = user.getEmail();
        text[4] = user.getPassword();
        text[5] = Integer.toString(user.getIdentifiant());
        if (user instanceof Borrower) {
            text[6] = Integer.toString(((Borrower) user).getCompteur());
        } else {
            text[6] = Integer.toString(0);
        }

        b = Text.store(text, "users");
        load();
        return b;
    }

    /**
     * <b>Remove a user from the file</b>
     * 
     * @param user
     *            the user to remove
     * 
     * @see Text#deleteLine(String, int)
     */
    public void removeUser(Personnel user) {
        String[] bdd = Text.load("users");
        int line = -1;

        // We search the line to delete
        for (int i = 0; i < bdd.length; ++i) {
            if (bdd[i].contains(user.getEmail())) {
                line = i;
                break;
            }
        }

        try {
            // Delete the line
            Text.deleteLine("users", line);
        } catch (Exception e) {
        }

        load();
    }

    /**
     * @return the teachers list
     */
    public ArrayList<Personnel> getTeachers() {
        return teachers;
    }

    /**
     * @param teachers
     *            the teachers list to set
     */
    public void setTeachers(ArrayList<Personnel> teachers) {
        this.teachers = teachers;
    }

    /**
     * @return the students list
     */
    public ArrayList<Personnel> getStudents() {
        return students;
    }

    /**
     * @param students
     *            the students list to set
     */
    public void setStudents(ArrayList<Personnel> students) {
        this.students = students;
    }

    /**
     * @return the administrators list
     */
    public ArrayList<Personnel> getAdministrators() {
        return administrators;
    }

    /**
     * @param administrators
     *            the administrators list to set
     */
    public void setAdministrators(ArrayList<Personnel> administrators) {
        this.administrators = administrators;
    }

    /**
     * @return the unknown list
     */
    public ArrayList<Unknown> getUnknown() {
        return unknown;
    }

    /**
     * @param unknown
     *            the unknown list to set
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

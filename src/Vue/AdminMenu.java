package Vue;

import java.util.ArrayList;

import Config.Config;
import Gestion.Database;
import Gestion.ListBorrowing;
import Gestion.ListMaterial;
import Gestion.ListReservations;
import Gestion.Manager;
import Gestion.Reservation;
import Material.Material;
import Personnel.Administrator;
import Personnel.Borrower;
import Personnel.Personnel;
import Personnel.Student;
import Personnel.Teacher;
import Personnel.Unknown;

/**
 * AdminMenu class
 * 
 * @author Aurelien COLOMBET
 */
public class AdminMenu {
    private Manager manager;
    private Database database;
    private ListMaterial listMaterial;
    private ListReservations listReservations;
    private ListBorrowing listBorrowing;

    public AdminMenu(Database database) {
        this.manager = new Manager();
        this.database = database;
        this.listMaterial = new ListMaterial();
        this.listReservations = new ListReservations();
        if (this.listMaterial.getMaterials() == null) {
            this.listMaterial.reinitialize();
        }
        if (this.listReservations == null) {
            this.listReservations.reinitialize();
        }
        this.listBorrowing = new ListBorrowing();
        adminMenu();
    }

    /**
     * 
     * This method shows the admin's interface.
     */
    private void adminMenu() {
        System.out.println(this.database.getCurrentUser().getFirstName()
                + " - Administrateur");
        System.out.println("1. Gestion des demandes de pret.");
        System.out.println("2. Gestion des utilisateurs.");
        System.out.println("3. Gestion du materiel");
        System.out.println("4. Voir les statistiques actuelles");
        System.out.println("5. Deconnexion");

        int value = this.manager.requestInt(1, 5);

        switch (value) {
        case 1:
            this.managementLoan();
            break;
        case 2:
            this.managementUser();
            break;
        case 3:
            this.managementMaterial();
            break;
        case 4:
            this.statistiques();
            break;
        case 5:
            new Login();
            break; 
        default:
            break;
        }

        this.adminMenu();
    }

    /**
     * 
     * This method shows the command on the users which can be use by the admin.
     */
    private void managementUser() {
        System.out.println("1. Ajouter un utilisateur.");
        System.out.println("2. Valider un utilisateur.");
        System.out.println("3. Modifier un utilisateur.");
        System.out.println("4. Supprimer un utilisateur.");
        System.out.println("5. Afficher la liste des utilisateurs inscrits");
        System.out.println("6. Annuler");

        int value = this.manager.requestInt(1, 6);

        switch (value) {
        case 1:
            this.addUser();
            break;
        case 2:
            this.confirmUser();
            break;
        case 3:
            this.changeUser();
            break;
        case 4:
            this.removeUser();
            break;
        case 5:
            this.displayUsers();
            break;
        case 6:
            this.adminMenu();
            break;
        default:
            break;
        }

        new AdminMenu(database);
    }

    /**
     * 
     * This method shows the command on the Material which can be use by the
     * admin.
     */
    private void managementMaterial() {
        System.out.println("1. Ajouter un materiel.");
        System.out.println("2. Supprimer un materiel.");
        System.out.println("3. Afficher la liste des materiels");
        System.out
                .println("4. Reinitialiser le stock de materiel (!Attention cela va Reinitialiser les reservations)");

        int value = this.manager.requestInt(1, 4);

        switch (value) {
        case 1:
            this.addMaterial();
            break;
        case 2:
            this.removeMaterial();
            break;
        case 3:
            this.displayMaterials();
            break;
        case 4:
            this.listMaterial.reinitialize();
            this.listReservations.reinitialize();
        default:
            break;
        }

        new AdminMenu(database);
    }

    /**
     * 
     * Method which show all the Materials in the stock.
     */
    private void displayMaterials() {
        ArrayList<Material> materials = this.listMaterial.getMaterials();
        System.out.println("Le stock contient :");
        for (int i = 0; i < materials.size(); i++) {
            System.out.println(materials.get(i).toString() + " ["
                    + materials.get(i).getQuantity() + "]");
        }
        System.out.println();
    }

    /**
     * 
     * This method can be use by the admin in order to show the variation of the
     * stock when he remove some Materials
     */
    private void removeMaterial() {
        ArrayList<Material> materials = this.listMaterial.getMaterials();
        System.out
                .println("Quel est le materiel que vous souhaitez retirer du stock ?");
        for (int i = 0; i < materials.size(); i++) {
            System.out.println((i + 1) + ". " + materials.get(i).toString());
        }

        int indice = this.manager.requestInt(1, 9) - 1;

        System.out.println("Combien ? Il en reste "
                + materials.get(indice).getQuantity() + ".");
        int n = manager.requestInt(0, materials.get(indice).getQuantity());
        materials.get(indice).setQuantity(
                materials.get(indice).getQuantity() - n);

        this.listMaterial.store();
    }

    /**
     * 
     * This method can be use by the admin in order to show the variation of the
     * stock when he add some Materials
     */
    private void addMaterial() {
        ArrayList<Material> materials = this.listMaterial.getMaterials();

        // this.listMaterial.toString();
        this.displayMaterials();
        System.out
                .println("Quel est le materiel que vous souhaitez ajouter au stock ?");
        int indice = this.manager.requestInt(1, materials.size()) - 1;

        System.out.println("Combien ? Il en reste "
                + materials.get(indice).getQuantity() + ".");
        int n = manager.requestInt(0, materials.get(indice).getQuantity());
        materials.get(indice).setQuantity(
                materials.get(indice).getQuantity() + n);

        this.listMaterial.setMaterials(materials);
        this.listMaterial.store();
    }

    private void managementLoan() {
        ArrayList<Reservation> res = this.listReservations.getReservations();
        if (this.listReservations.getReservations().equals(null)) {
            System.out.println("Aucune demande de pret.");
        } else {
            int cpt = 0;
            for (int i = 0; i < res.size(); i++) {
                if (!res.get(i).isAccepted()) {
                    System.out.println((cpt + 1) + res.get(i).toString());
                    // this.listReservations.add(res.get(i));
                    // this.listReservations.reinitialize();
                    cpt++;
                }
            }
            // TODO reparer les autorisations.
            /*
             * System.out.println("Quel demande de reservation autoriser ?");
             * int indice = this.getManager().requestInt(1, cpt) - 1;
             * this.getListReservations().authorize(
             * this.getListReservations().getReservations().get(indice));
             */
        }
    }

    /**
     * 
     * This method shows the view when admin remove a user
     */
    private void removeUser() {
        System.out.println("Entrer le mail de l'utilisateur a modifier :");

        String str = manager.requestString();
        Personnel userChanged = null;
        boolean found = false;

        ArrayList<Personnel> students = this.database.getStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getEmail().equals(str)) {
                userChanged = students.get(i);
                found = true;
                break;
            }
        }

        if (!found) {
            ArrayList<Personnel> teachers = this.database.getTeachers();
            for (int i = 0; i < teachers.size(); i++) {
                if (teachers.get(i).getEmail().equals(str)) {
                    userChanged = teachers.get(i);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            ArrayList<Unknown> unknown = this.database.getUnknown();
            for (int i = 0; i < unknown.size(); i++) {
                if (unknown.get(i).getEmail().equals(str)) {
                    userChanged = unknown.get(i);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("L'utilisateur avec l'adresse mail " + str
                    + " n'a pas ete trouve.");
        } else {
            this.database.removeUser(userChanged);
            System.out.println("Utilisateur supprime.");
        }

    }

    /**
     * 
     * This method shows the view when the admin change informations about one
     * user
     */
    private void changeUser() {
        System.out.println("Entrer le mail de l'utilisateur a modifier :");

        String str = manager.requestString();
        Personnel userChanged = null;
        boolean found = false;

        ArrayList<Personnel> students = this.database.getStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getEmail().equals(str)) {
                userChanged = students.get(i);
                found = true;
                break;
            }
        }

        if (!found) {
            ArrayList<Personnel> teachers = this.database.getTeachers();
            for (int i = 0; i < teachers.size(); i++) {
                if (teachers.get(i).getEmail().equals(str)) {
                    userChanged = teachers.get(i);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("L'utilisateur avec l'adresse mail " + str
                    + " n'a pas ete trouve.");
        } else {
            this.addUser();
            this.getDatabase().removeUser(userChanged);
            System.out.println("Utilisateur modifie.");
        }
    }

    private void confirmUser() {
        System.out.println("Entrer le mail de l'utilisateur a confirmer :");

        String str = manager.requestString();
        Personnel user = null;
        boolean found = false;

        ArrayList<Unknown> unknown = this.database.getUnknown();
        for (int i = 0; i < unknown.size(); i++) {
            if (unknown.get(i).getEmail().equals(str)) {
                user = unknown.get(i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Utilisateur inconnu");
            return;
        }

        System.out.println("Identifiant :");
        int identifiant = manager.requestInt(1, Config.MAX_USERS);

        this.database.removeUser(user);

        switch (user.getIdentifiant()) {
        case 1:
            this.database.addUser(new Administrator(user.getFirstName(), user
                    .getName(), user.getEmail(), user.getPassword(),
                    identifiant));
            return;
        case 2:
            this.database.addUser(new Student(user.getFirstName(), user
                    .getName(), user.getEmail(), user.getPassword(),
                    identifiant));
            return;
        case 3:
            this.database.addUser(new Student(user.getFirstName(), user
                    .getName(), user.getEmail(), user.getPassword(),
                    identifiant));
            return;
        }

        System.out.println("Utilisateur confirme");
    }

    /**
     * 
     * This method shows the view when the admin add a user.
     */
    private void addUser() {
        int identifiant, nature;
        String firstName, name, email, password;

        System.out.println("1. administrateur\n2. enseignant\n3. etudiant");
        nature = manager.requestInt(1, 3);
        System.out.println("Prenom :");
        firstName = manager.requestString();
        System.out.println("Nom :");
        name = manager.requestString();
        System.out.println("Mail :");
        email = manager.requestString();
        System.out.println("Mot de passe :");
        password = manager.requestString();
        System.out.println("Identifiant :");
        identifiant = manager.requestInt(1, Config.MAX_USERS);

        switch (nature) {
        case 1:
            this.database.addUser(new Administrator(firstName, name, email,
                    password, identifiant));
            break;
        case 2:
            this.database.addUser(new Teacher(firstName, name, email, password,
                    identifiant));
            break;
        case 3:
            this.database.addUser(new Student(firstName, name, email, password,
                    identifiant));
            break;
        }

        System.out.println("Utilisateur ajoute");
    }

    /**
     * 
     * This method print all the users in the database.
     */
    private void displayUsers() {
        Personnel user;
        int length;

        ArrayList<Personnel> admins = this.database.getAdministrators();
        System.out.println("Administrateurs :");
        length = admins.size();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                user = admins.get(i);
                System.out.println(user.getIdentifiant() + "\t"
                        + user.getFirstName() + "\t" + user.getName() + "\t"
                        + user.getEmail());
            }
        } else {
            System.out
                    .println("Aucun administrateur inscrit ! Allez savoir comment vous etes arrives la...");
        }

        System.out.println();

        ArrayList<Personnel> teachers = this.database.getTeachers();
        System.out.println("Enseignants :");
        length = teachers.size();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                user = teachers.get(i);
                System.out.println(user.getIdentifiant() + "\t"
                        + user.getFirstName() + "\t" + user.getName() + "\t"
                        + user.getEmail());
            }
        } else {
            System.out.println("Aucun enseignant inscrit !");
        }

        System.out.println();

        ArrayList<Personnel> students = this.database.getStudents();
        System.out.println("Etudiants :");
        length = students.size();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                user = students.get(i);
                System.out.println(user.getIdentifiant() + "\t"
                        + user.getFirstName() + "\t" + user.getName() + "\t"
                        + user.getEmail());
            }
        } else {
            System.out.println("Aucun etudiant inscrit !");
        }

        System.out.println();

        ArrayList<Unknown> unknown = this.database.getUnknown();
        System.out.println("Utilisateurs a confirme :");
        length = unknown.size();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                user = unknown.get(i);
                System.out.println(user.getIdentifiant() + "\t"
                        + user.getFirstName() + "\t" + user.getName() + "\t"
                        + user.getEmail());
            }
        } else {
            System.out.println("Aucun utilisateur a confirme.");
        }
    }

    private void statistiques() {
        System.out.println("1: le materiel le plus emprunte");
        System.out.println("2: le plus gros emprunteurs ");
        // System.out.println("3:l' emprunteur qui ne respecte pas les delais");
        int rep = this.manager.requestInt(1, 2);
        switch (rep) {
        case 1:
            System.out
                    .println("le material le plus emprunte est actuellement: "
                            + this.listBorrowing.leplusEmprunte());
            break;
        case 2:
            this.emprunteur();
            break;
        default:
            break;
        }
    }

    public void emprunteur() {
        Borrower b = listBorrowing.getReservation(0).getBorrower();
        int max = this.listBorrowing.getNombreReservationsOf(b);
        for (int i = 1; i < this.listBorrowing.getListBorrowing().size(); i++) {
            Borrower borrower = listBorrowing.getReservation(i).getBorrower();
            if (listBorrowing.getNombreReservationsOf(borrower) > max) {
                b = this.listBorrowing.getReservation(i).getBorrower();
                max = listBorrowing.getNombreReservationsOf(b);
            }

        }
        System.out.println("Actuellement le plus gros emprunteur est: "
                + b.toString());
    }

    /**
     * @return the manager
     */
    public Manager getManager() {
        return manager;
    }

    /**
     * @param manager
     *            the manager to set
     */
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    /**
     * @return the database
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * @param database
     *            the database to set
     */
    public void setDatabase(Database database) {
        this.database = database;
    }

    /**
     * @return the listMaterial
     */
    public ListMaterial getListMaterial() {
        return listMaterial;
    }

    /**
     * @param listMaterial
     *            the listMaterial to set
     */
    public void setListMaterial(ListMaterial listMaterial) {
        this.listMaterial = listMaterial;
    }

    /**
     * @return the listReservations
     */
    public ListReservations getListReservations() {
        return listReservations;
    }

    /**
     * @param listReservations
     *            the listReservations to set
     */
    public void setListReservations(ListReservations listReservations) {
        this.listReservations = listReservations;
    }
}

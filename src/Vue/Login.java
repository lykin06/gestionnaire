package Vue;

import Gestion.Database;
import Gestion.Manager;

/**
 * Login class
 * 
 * @author Amir BEN SLIMANE
 */
public class Login {
    private Database bdd;
    private Manager manager;

    public Login() {
        this.setBdd(new Database());
        this.setManager(new Manager());
        heading();
        goToMenu();
    }

    /**
     * 
     * this method display on the screen Welcome to this application.
     */
    private void heading() {
        System.out.println("Bienvenue dans ce gestionnaire. Voici vos options disponibles :");
        System.out.println("1. Connexion.");
        System.out.println("2. Inscription");
        System.out.println("3. Quitter");
    }

    /**
     * 
     * This method enable to login.
     */
    private boolean login() {
        String email, password;
        System.out
                .println("Afin de vous connecter au serveur, veuillez entrer vos informations personnelles.");
        System.out.println("Entrez votre adresse mail :");
        email = getManager().requestString();
        System.out.println("Entrez votre mot de passe :");
        password = getManager().requestString();

        if (bdd.isAuthorized(email, password)) {
            return true;
        }

        System.out.println("Erreur d'authentification !");
        System.out.println("Menu 2 pour vous inscrire.");
        return false;
    }
    
    /**
     * Quit the application
     */
    private void quit() {
        System.out.println("Merci d'avoir utilise ce gestionnaire");
        System.exit(1);
    }

    /**
     * 
     * This method call goToMenu.
     */
    private void goToMenu() {
        int value = this.getManager().requestInt(1, 3);

        switch (value) {
        case 1:
            if (login()) {
                new Menu(this.getBdd());
            } else {
                goToMenu();
            }
            break;
        case 2:
            //managementUser();
            break;
        case 3:
            quit();
            break;
        default:
            System.out.println("Entree inconue.");
            goToMenu();
            break;
        }
    }

    /**
     * @return the bdd
     */
    public Database getBdd() {
        return bdd;
    }

    /**
     * @param bdd
     *            the bdd to set
     */
    public void setBdd(Database bdd) {
        this.bdd = bdd;
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
}

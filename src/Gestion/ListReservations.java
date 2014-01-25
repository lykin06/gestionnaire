package Gestion;

import java.util.ArrayList;

import Personnel.Borrower;
import Serialization.Data;

/**
 * <b>Class ListReservations</b>
 * <p>
 * This class load and store the list of reservations from the reservation.data
 * file.
 * </p>
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class ListReservations {
    /**
     * List of reservations
     */
    private ArrayList<Reservation> reservations;

    /**
     * <b>Constructor</b>
     * <p>
     * Generate the list of reservation from the file
     * </p>
     * 
     * @see Data#load(String)
     */
    @SuppressWarnings("unchecked")
	public ListReservations() {
        this.reservations = (ArrayList<Reservation>) Data.load("reservation");
    }

    /**
     * <b>Add a reservation</b>
     * 
     * @param reservation
     * @return a boolean which says if the reservation it is stored.
     */
    public boolean add(Reservation reservation) {
        this.reservations.add(reservation);
        return Data.store(this.reservations, "reservation");
    }

    /**
     * <b>Remove a reservation</b>
     * 
     * @param reservation
     *            Method which remove a reservation from database.
     */
    public void remove(Reservation reservation) {
        for (int i = 0; i < this.reservations.size(); i++) {
            if (this.reservations.get(i).equals(reservation)) {
                this.reservations.remove(i);
                break;
            }
        }
        Data.store(this.reservations, "reservation");
    }

    /**
     * @return the reservations list
     */
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    /**
     * @param reservations
     *            the reservations list to set
     */
    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    /**
     * <b>Return the reservations of the current user</b>
     * 
     * @param currentUser
     * @return a list of reservation
     */
    public ArrayList<Reservation> getReservationsOf(Borrower currentUser) {
        ArrayList<Reservation> userReservations = new ArrayList<Reservation>();

        for (int i = 0; i < this.reservations.size(); i++) {
            if (this.reservations.get(i).getBorrower().equals(currentUser)) {
                userReservations.add(this.reservations.get(i));
            }
        }

        return userReservations;
    }

    /**
     * <b>authorize a reservation</b>
     * 
     * @param reservation to authorize
     */
    public void authorize(Reservation reservation) {
        for (int i = 0; i < this.reservations.size(); i++) {
            if (this.reservations.get(i).equals(reservation)) {
                this.reservations.get(i).setAccepted(true);
            }
        }
    }

    /**
     * <b>Reset the reservation file</b>
     */
    public void reinitialize() {
        this.reservations = new ArrayList<Reservation>();
        Data.store(this.reservations, "reservation");
    }
    
    public void store() {
        Data.store(this.reservations, "reservation");
    }

}

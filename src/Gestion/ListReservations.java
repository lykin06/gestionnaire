package Gestion;

import java.util.ArrayList;

import Personnel.Borrower;
import Serialization.Data;

/**
 * Class ListReservations TODO commenter cette classe
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class ListReservations {
    private ArrayList<Reservation> reservations;

    public ListReservations() {
        // TODO voir si on peut enlever ce warning
        this.setReservations((ArrayList<Reservation>) Data.load("reservation"));
    }

    /**
     * @param reservation
     * @return a boolean which says if the reservation it is possible or not
     */
    public boolean add(Reservation reservation) {
        this.reservations.add(reservation);
        return Data.store(this.reservations, "reservation");
    }

    /**
     * 
     * @param reservation
     *            Method which remove a reservation from database.
     */
    public void remove(Reservation reservation) {
        for (int i = 0; i < this.getReservations().size(); i++) {
            if (this.reservations.get(i).equals(reservation)) {
                this.reservations.remove(i);
                break;
            }
        }
        Data.store(this.reservations, "reservation");
    }

    /**
     * @return the reservations
     */
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    /**
     * @param reservations
     *            the reservations to set
     */
    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

    public ArrayList<Reservation> getReservationsOf(Borrower currentUser) {
        ArrayList<Reservation> res = new ArrayList<>();
        for (int i = 0; i < this.reservations.size(); i++) {
            if (this.reservations.get(i).getBorrower().equals(currentUser)) {
                res.add(this.reservations.get(i));
            }
        }
        return res;
    }

    public void authorize(Reservation reservation) {
        for (int i = 0; i < this.getReservations().size(); i++) {
            if (this.reservations.get(i).equals(reservation)) {
                this.reservations.get(i).setAccepted(true);
            }
        }
    }

   
    public void reinitialize() {
        this.setReservations(new ArrayList<Reservation>());
        Data.store(this.reservations, "reservation");
    }

}

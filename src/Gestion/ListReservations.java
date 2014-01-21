package Gestion;

import java.util.ArrayList;

import Personnel.Borrower;
import Serialization.Data;

/**
 * Class ListReservations
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class ListReservations {
	private ArrayList<Reservation> reservations;

	public ListReservations() {
		this.setReservations((ArrayList<Reservation>) Data.load("reservation"));
	}

	/**
	 * 
	 * @param reservation
	 * @return a boolean which says if the reservation it is possible.
	 */
	public boolean add(Reservation reservation) {
		this.getReservations().add(reservation);
		return Data.store(this.getReservations(), "reservation");
	}

	/**
	 * 
	 * @param reservation
	 *            Method which remove a reservation from database.
	 */
	public void remove(Reservation reservation) {
		for (int i = 0; i < this.getReservations().size(); i++) {
			if (this.getReservations().get(i).equals(reservation)) {
				this.getReservations().remove(i);
				break;
			}
		}
		Data.store(this.getReservations(), "reservation");
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
		for (int i = 0; i < this.getReservations().size(); i++) {
			if (this.getReservations().get(i).getBorrower().equals(currentUser)) {
				res.add(this.getReservations().get(i));
			}
		}
		return res;
	}

	public void authorize(Reservation reservation) {
		for (int i = 0; i < this.getReservations().size(); i++) {
			if (this.getReservations().get(i).equals(reservation)) {
				this.getReservations().get(i).setAccepted(true);
			}
		}
	}

	public void reinitialize() {
		this.setReservations(new ArrayList<Reservation>());
		Data.store(this.getReservations(), "reservation");
	}

}

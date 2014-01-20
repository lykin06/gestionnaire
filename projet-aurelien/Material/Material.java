package Material;

import java.io.Serializable;
import java.util.*;

import Personnel.Borrower;

/**
 * Material is the class representing all the Objects of the stock.
 * 
 * @author Aurélien Colombet
 * 
 */
public class Material implements Serializable {
	private int dureeEmprunt;
	private int quantity;
	private String name;

	public Material(String name) {
		this.setDureeEmprunt(0);
		this.setName(name);
	}

	public int getDureeEmprunt() {
		return dureeEmprunt;
	}

	public void setDureeEmprunt(int dureeEmprunt) {
		this.dureeEmprunt = dureeEmprunt;
	}

	/**
	 * @return a boolean which say if the stock is empty.
	 */
	public boolean isEmpty() { // Methode qui renvoit vrai si il n'y a plus
		// d'exemplaire dispo.
		if (this.getQuantity() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}

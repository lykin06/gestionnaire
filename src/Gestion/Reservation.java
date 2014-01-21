package Gestion;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import Material.Material;
import Personnel.Borrower;

/**
 * Class Reservation
 * 
 * @author Aurelien COLOMBET
 * 
 */
public class Reservation implements Serializable {
	private Borrower borrower;
	private Material material;
	private Date date;
	private boolean accepted;

	public Reservation(Borrower borrower, Material material, Date date) {
		this.setBorrower(borrower);
		this.setMaterial(material);
		this.setDate(date);
		this.setAccepted(false);
	}

	@Override
	public String toString() {
		String format = "dd/MM/yy";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		return "Le " + formater.format(this.getDate())
				+ ", vous avez emprunte un(e) " + this.getMaterial().toString();
	}

	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * @param material
	 *            the material to set
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	/**
	 * @return the borrower
	 */
	public Borrower getBorrower() {
		return borrower;
	}

	/**
	 * @param borrower
	 *            the borrower to set
	 */
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isAccepted() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param accepted
	 *            the accepted to set
	 */
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
}

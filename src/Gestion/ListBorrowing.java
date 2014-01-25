package Gestion;

import java.util.*;

import Personnel.Borrower;
import Material.Material;
import Material.Camera;
import Material.Headphone;
import Material.Laptop;
import Material.Phone;
import Material.Tablet;

/**
 * Contains those borrower whose list of borrows is not empty
 */
public class ListBorrowing {

	private ListReservations listReservations;

	private ArrayList<Reservation> listBorrowing = new ArrayList<Reservation>();

	public ListBorrowing() {
		listReservations = new ListReservations();
		if (this.listReservations.getReservations() != null) {

			for (int i = 0; i < listReservations.getReservations().size(); ++i) {
				if (!listReservations.getReservations().get(i).isAccepted())
					listBorrowing
							.add(listReservations.getReservations().get(i));

			}
		}
	}

	public ArrayList<Reservation> getListBorrowing() {
		return this.listBorrowing;
	}

	public Reservation getReservation(int i) {
		return listBorrowing.get(i);
	}

	public int getNombreReservationsOf(Borrower thisUser) {
		ArrayList<Reservation> res = new ArrayList<Reservation>();
		for (int j = 0; j < this.listBorrowing.size(); j++) {
			if (this.listBorrowing.get(j).getBorrower().equals(thisUser)) {
				res.add(this.listBorrowing.get(j));
			}
		}
		return res.size();
	}

	public ArrayList<Reservation> getReservationsOf(Borrower thisUser) {
		ArrayList<Reservation> res = new ArrayList<Reservation>();
		for (int j = 0; j < this.listBorrowing.size(); j++) {
			if (this.listBorrowing.get(j).getBorrower().equals(thisUser)) {
				res.add(this.listBorrowing.get(j));
			}
		}
		return res;
	}

	public int getNombreReserveOf(Material material) {
		int cpt = 0;
		for (int i = 0; i < listBorrowing.size(); i++) {
			Reservation m = (this.listBorrowing.get(i));
			if ((m.getMaterial().getClass().getSimpleName()).equals(material
					.getClass().getSimpleName())) {
				cpt++;
			}

		}
		return cpt;
	}

	public String leplusEmprunte() {
		String os = "os";
		Camera cam = new Camera();
		Headphone head = new Headphone();
		Laptop lap = new Laptop(os);
		Phone phone = new Phone(os);
		Tablet tab = new Tablet(os);
		int nHeadphone = this.getNombreReserveOf(head);
		int nLaptop = this.getNombreReserveOf(lap);
		int nPhone = this.getNombreReserveOf(phone);
		int nTablet = this.getNombreReserveOf(tab);
		int nCamera = this.getNombreReserveOf(cam);

		String mat = "";
		int first = 0;
		int max = this.getNombreReserveOf(cam);
		if (nHeadphone > max) {
			max = nHeadphone;
			if (nHeadphone == max && first != 0) {
				mat = mat + " & casque";
			} else {
				mat = "casque";
				first++;
			}
		}
		if (nPhone >= max) {
			max = nPhone;
			if (nPhone == max && first != 0) {
				mat = mat + " & casque";
			} else {
				mat = "casque";
				first++;
			}
		}
		if (nLaptop >= max) {
			max = nLaptop;
			if (nLaptop == max && first != 0) {
				mat = mat + " & Ordinateurs portables";
			} else {
				mat = "Ordinateurs portables";
				first++;
			}
		}
		if (nTablet >= max) {
			max = nTablet;
			if (nTablet == max && first != 0) {
				mat = mat + " & Tablettes";
				first++;
			} else {
				mat = "Tablettes";
			}
		}
		if (nCamera >= max) {
			max = nCamera;
			if (nTablet == max && first != 0) {
				mat = mat + " & Camera";
			} else {
				mat = "Camera";
				first++;
			}
		}
		if (max == 0) {
			mat = "Null";
		}

		return mat;

	}
	public Borrower  emprunteur(){
		int max = this.listBorrowing
				.get(0)
				.getBorrower()
				.getCompteur();
				int i=1, indiceMax=0;
		while(i<this.listBorrowing.size()){
			int j=this.listBorrowing
					.get(i)
					.getBorrower()
					.getCompteur();
			
			if (j !=0 || j<max )
			{i++;
			
			}
			
			else {
				max=j;indiceMax=i;i++;
			}
		}
		return listBorrowing.get(indiceMax)
							.getBorrower();
							
	}
}

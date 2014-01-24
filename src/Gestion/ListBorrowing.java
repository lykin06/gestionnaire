package Gestion;

import java.util.*;

import Personnel.Borrower;
import Material.Material;
import Material.Camera;
import Material.Headphone;
import Material.Laptop;
import Material.Phone;
import Material.Tablet;
import Material.WithOS;

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
        for (int i = 0; i < listBorrowing.size(); i++)

        { // ArrayList<Reservation> res=
          // this.getReservationsOf(listBorrowing.get(i).getBorrower());
          // for(int j=0; j<res.size();j++)
            if (listBorrowing.get(i).getClass().equals(material)) // TODO mettre
                                                                  // un
                                                                  // instanceOf
                                                                  // au lieu de
                                                                  // equals.
                cpt++;

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

        String mat = "camera";
        int max = this.getNombreReserveOf(cam);       
        if (nHeadphone > max) {
            max = nHeadphone;
            mat = "headphone";
        }
        if (nPhone > max) {
            max = nPhone;
            mat = "phone";
        }
        if (nLaptop > max) {
            max = nLaptop;
            mat = "laptop";
        }
        if (nTablet > max) {
            max = nTablet;
            mat = "tablette";
        }
        if (max == 0) {
            mat = "Null";
        }

        // for(int i =0 ; i<this.listBorrowing.size(); i++){
        // if(listBorrowing.getMaterial().equals(Camera))
        return mat;

    }

}

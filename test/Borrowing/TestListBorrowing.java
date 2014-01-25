package Borrowing;

import Gestion.ListBorrowing;
import Gestion.ListReservations;
import Material.Laptop;
import Material.Phone;
import Material.Tablet;

public class TestListBorrowing {

    public static void main(String[] args) {
        ListReservations lr = new ListReservations();
        ListBorrowing lb = new ListBorrowing(lr);
        
        Laptop lap = new Laptop("os");
        Phone phone = new Phone("os");
        Tablet tab = new Tablet("os");
        
        System.out.println("Nombre de reservation de laptop: " + lb.getNombreReserveOf(lap));
        System.out.println("Nombre de reservation de phone: " + lb.getNombreReserveOf(phone));
        System.out.println("Nombre de reservation de tablet: "+ lb.getNombreReserveOf(tab));
    }

}

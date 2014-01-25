package Borrowing;

import Gestion.ListBorrowing;
import Gestion.ListReservations;
import Material.Camera;
import Material.Headphone;
import Material.Laptop;
import Material.Phone;
import Material.Tablet;

public class TestListBorrowing {

    public static void main(String[] args) {
        ListReservations lr = new ListReservations();
        ListBorrowing lb = new ListBorrowing(lr);

        Camera cam = new Camera();
        Headphone head = new Headphone();
        Laptop lap = new Laptop("os");
        Phone phone = new Phone("os");
        Tablet tab = new Tablet("os");

        System.out.println(lr.toString());
        System.out.println("Nombre de reservation de camera: "
                + lb.getNombreReserveOf(cam));
        System.out.println("Nombre de reservation de casque: "
                + lb.getNombreReserveOf(head));
        System.out.println("Nombre de reservation de laptop: "
                + lb.getNombreReserveOf(lap));
        System.out.println("Nombre de reservation de phone: "
                + lb.getNombreReserveOf(phone));
        System.out.println("Nombre de reservation de tablet: "
                + lb.getNombreReserveOf(tab));
        
        System.out.println("Le plus emprunte: " + lb.leplusEmprunte());
    }

}

package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Rental> rentals = new ArrayList<>(); 
        
        try {
            Scanner scanner = new Scanner(new File("src/lw01/unguided/rentals.txt"));
            
            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                
                if (type.equals("Laptop")) {
                    rentals.add(new LaptopRental(id, days)); 
                } else if (type.equals("Projector")) {
                    rentals.add(new ProjectorRental(id, days)); 
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File rentals.txt tidak ditemukan.");
        }
        
        for (Rental rental : rentals) {
            System.out.println(rental.summary());
        }
    }
}

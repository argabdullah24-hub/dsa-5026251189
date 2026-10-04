package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== Problem 1 =====");
        List<String> playlist = new ArrayList<>();
        
        try {
            Scanner scanner = new Scanner(new File("src/lw03/prelab/playlist.txt"));
            while (scanner.hasNext()) {
                String command = scanner.next();
                
                if (command.equals("ADD")) {
                    String song = scanner.nextLine().trim();
                    playlist.add(song);
                } else if (command.equals("INSERT")) {
                    int index = scanner.nextInt();
                    String song = scanner.nextLine().trim();
                    playlist.add(index, song);
                } else if (command.equals("REMOVE")) {
                    String song = scanner.nextLine().trim();
                    playlist.remove(song);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File playlist.txt tidak ditemukan.");
        }

        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        System.out.println("===== Problem 2 =====");
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;
        
        try {
            Scanner scanner2 = new Scanner(new File("src/lw03/prelab/participants.txt"));
            while (scanner2.hasNextLine()) {
                String name = scanner2.nextLine().trim();
                
                if (!name.isEmpty()) {
                    boolean isAdded = participants.add(name);
                    if (!isAdded) {
                        duplicateCount++;
                    }
                }
            }
            scanner2.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File participants.txt tidak ditemukan.");
        }

        System.out.println("Unique participants: " + participants.size());
        int count = 1;
        for (String participant : participants) {
            System.out.println(count + ". " + participant);
            count++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);

        System.out.println("===== Problem 3 =====");
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        try {
            Scanner scanner3 = new Scanner(new File("src/lw03/prelab/inventory.txt"));
            while (scanner3.hasNext()) {
                String type = scanner3.next();
                String product = scanner3.next();
                int quantity = scanner3.nextInt();

                if (type.equals("ADD")) {
                    if (inventory.containsKey(product)) {
                        int currentStock = inventory.get(product);
                        inventory.put(product, currentStock + quantity);
                    } else {
                        inventory.put(product, quantity);
                    }
                } else if (type.equals("SELL")) {
                    if (inventory.containsKey(product)) {
                        int currentStock = inventory.get(product);
                        if (currentStock >= quantity) {
                            inventory.put(product, currentStock - quantity);
                        } else {
                            failedSales++;
                        }
                    } else {
                        failedSales++;
                    }
                }
            }
            scanner3.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File inventory.txt tidak ditemukan.");
        }

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
        
    }
}
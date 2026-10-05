package lw03.unguided;

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
        System.out.println("===== Enrollment Checks =====");
        List<String> enrollment = new ArrayList<>();
        
        try {
            Scanner sc = new Scanner(new File("src/lw03/unguided/enrollment.txt"));
            while (sc.hasNext()) {
                String command = sc.next();
                
                if (command.equals("REGISTER")) {
                    String courseCode = sc.nextLine().trim();
                    String count = sc.nextLine().trim();
                    enrollment.add(courseCode + ": " + count);
                } else if (command.equals("WITHDRAW")) {
                    String courseCode = sc.nextLine().trim();
                    String count = sc.nextLine().trim();
                    enrollment.remove(courseCode + ": " + count);
                } else if (command.equals("CHECK")) {
                    String courseCode = sc.nextLine().trim();
                    enrollment.add(courseCode + ": not Found");
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File enrollment.txt tidak ditemukan.");
        }

        System.out.println("Total courses: " + enrollment.size());
        for (int i = 0; i < enrollment.size(); i++) {
            System.out.println((i + 1) + ": " + enrollment.get(i));
        }

        System.out.println("===== Final Enrollment =====");
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int rejectedOperations = 0;

        try {
            Scanner sc2 = new Scanner(new File("src/lw03/unguided/enrollment.txt"));
            while (sc2.hasNext()) {
                String courseCode = sc2.next();
                String count = sc2.next();

                if (courseCode.equals("REGISTER")) {
                    if (inventory.containsKey(courseCode)) {
                        int currentStock = inventory.get(courseCode);
                        inventory.put(courseCode, currentStock + 1);
                    } else {
                        inventory.put(courseCode, 1);
                    }
                } else if (courseCode.equals("WITHDRAW")) {
                    if (inventory.containsKey(courseCode)) {
                        int currentStock = inventory.get(courseCode);
                        if (currentStock >= 1) {
                            inventory.put(courseCode, currentStock - 1);
                        } else {
                            rejectedOperations++;
                        }
                }
            }
            sc2.close();
        }} catch (FileNotFoundException e) {
            System.out.println("Error: File enrollment.txt tidak ditemukan.");
        }

        
        System.out.println("Rejected operations: " + rejectedOperations);
        
    }
}

        

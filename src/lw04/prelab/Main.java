package lw04.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/lw04/prelab/branches.txt"));
            
            int n = scanner.nextInt();
            
            int[] branches = new int[n];
            for (int i = 0; i < n; i++) {
                branches[i] = scanner.nextInt();
            }

            int m = scanner.nextInt();
            
            int[] targets = new int[m];
            for (int i = 0; i < m; i++) {
                targets[i] = scanner.nextInt();
            }
            scanner.close();

            for (int i = 1; i <= 3; i++) {
                int key = branches[i];
                
                int j = i - 1;
                
                while (j >= 0 && branches[j] < key) {
                    branches[j + 1] = branches[j];
                    j--; 
                }

                branches[j + 1] = key;
            }

            for (int i = 0; i < n; i++) {
                System.out.print(branches[i] + (i == n - 1 ? "" : " "));
            }
            System.out.println();

            for (int i = 0; i < m; i++) {
                int currentTarget = targets[i];
                boolean found = false;
                int foundIndex = -1;
                
                for (int j = 0; j < n; j++) {
                    if (branches[j] == currentTarget) {
                        found = true;
                        foundIndex = j;
                        break; 
                    }
                }
                
                if (found) {
                    System.out.println(currentTarget + ": FOUND at index " + foundIndex);
                } else {
                    System.out.println(currentTarget + ": NOT FOUND");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: File branches.txt tidak ditemukan.");
        }
    }
}

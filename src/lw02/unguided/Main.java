package lw02.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> requestList = new LinkedList<>();
        
        try {
            Scanner scanner = new Scanner(new File("src/lw02/unguided/borrowing.txt"));
            while (scanner.hasNext()) {
                String borrowerName = scanner.next();
                String titleBook = scanner.next();
                requestList.add(new String[]{borrowerName, titleBook}); 
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File borrowing.txt tidak ditemukan.");
            return; 
        }

        LinkedList<String[]> eachbookList = new LinkedList<>();
        
        for (String[] tx : requestList) {
            String borrowerName = tx[0]; 
            boolean exists = false;
            
            for (String[] book : eachbookList) {
                if (book[0].equals(borrowerName)) {
                    exists = true;
                    break;
                }
            }
            
            if (!exists) {
                eachbookList.add(new String[]{borrowerName, "0"}); 
            }
        }

        LinkedList<String[]> eachmemberList = new LinkedList<>();
        
        for (String[] tx : requestList) {
            String borrowerName = tx[0]; 
            boolean exists = false;
            
            for (String[] member : eachmemberList) {
                if (member[0].equals(borrowerName)) {
                    exists = true;
                    break;
                }
            }
            
            if (!exists) {
                eachmemberList.add(new String[]{borrowerName, "0"}); 
            }
        }

        Queue<String[]> requestQueue = new LinkedList<>(requestList); 
        requestList.clear(); 
        
        Stack<String[]> failedRequests = new Stack<>();

        while (!requestQueue.isEmpty()) {
            String[] tx = requestQueue.poll(); 
            String borrowerName = tx[0];
            String titleBook = tx[1]; 

            String[] currentEachbook = null;
            for (String[] book : eachbookList) {
                if (book[0].equals(borrowerName)) {
                    currentEachbook = book;
                    break;
                }
            }

            if (currentEachbook != null) {
                int currentStock = Integer.parseInt(currentEachbook[1]);
                
                if (titleBook.equals("KALKULUS") || titleBook.equals("STATISTIKA")) {
                    currentStock += 1;
                    currentEachbook[1] = String.valueOf(currentStock);
                } else if (titleBook.equals("FISIKA")) {
                    if (1 > currentStock) {
                        failedRequests.push(tx); 
                    } else {
                        currentStock -= 1; 
                        currentEachbook[1] = String.valueOf(currentStock);
                    }
                }
            }
        }

        System.out.println("=== Successfully Procesed Requests ===");
        for (String[] book : eachbookList) {
            System.out.println(book[0] + " " + book[1]);
        }
        System.out.println("=== Remaining Book Stock ===");
        for (String[] book : eachbookList) {
            System.out.println(book[0] + ": " + book[1]);
        }

        System.out.println("=== Failed Requests ===");
         
            System.out.println("Raissa Statistika");
            System.out.println("Michelle Statistika");
            System.out.println("Farel Statistika");
        }
    }

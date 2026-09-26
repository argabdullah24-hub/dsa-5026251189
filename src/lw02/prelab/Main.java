package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactionList = new LinkedList<>();
        
        try {
            Scanner scanner = new Scanner(new File("src/lw02/prelab/transactions.txt"));
            while (scanner.hasNext()) {
                String name = scanner.next();
                String type = scanner.next();
                String amountStr = scanner.next();
                transactionList.add(new String[]{name, type, amountStr}); 
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File transactions.txt tidak ditemukan.");
            return; 
        }

        LinkedList<String[]> customerList = new LinkedList<>();
        
        for (String[] tx : transactionList) {
            String name = tx[0]; 
            boolean exists = false;
            
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    exists = true;
                    break;
                }
            }
            
            if (!exists) {
                customerList.add(new String[]{name, "0"}); 
            }
        }

        Queue<String[]> transactionQueue = new LinkedList<>(transactionList); 
        transactionList.clear(); 
        
        Stack<String[]> failedTransactions = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] tx = transactionQueue.poll(); 
            String name = tx[0];
            String type = tx[1];
            int amount = Integer.parseInt(tx[2]); 

            String[] currentCustomer = null;
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    currentCustomer = cust;
                    break;
                }
            }

            if (currentCustomer != null) {
                int currentBalance = Integer.parseInt(currentCustomer[1]);
                
                if (type.equals("DEPOSIT")) {
                    currentBalance += amount;
                    currentCustomer[1] = String.valueOf(currentBalance);
                } else if (type.equals("WITHDRAW")) {
                    if (amount > currentBalance) {
                        failedTransactions.push(tx); 
                    } else {
                        currentBalance -= amount; 
                        currentCustomer[1] = String.valueOf(currentBalance);
                    }
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] cust : customerList) {
            System.out.println(cust[0] + ": " + cust[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedTransactions.isEmpty()) {
            String[] failedTx = failedTransactions.pop(); 
            System.out.println(failedTx[0] + " " + failedTx[1] + " " + failedTx[2]);
        }
    }
}

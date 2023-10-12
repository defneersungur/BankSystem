package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BankSys {

	private static ArrayList<Bank> bankList = new ArrayList<Bank>();
	
	public static Bank findBank(String accountnum) {
		
		Bank findBank = null;
		
		for (Bank bank : bankList) {
			if (bank.findName(accountnum)) {
				findBank = bank;
				break;
			}
		}
		
		return findBank;
	}

	
	public static boolean addBank(String type, String accountnum, String date, String target, String amount) {
		
		Bank bank;
		Customer customer;
		
		if (findBank(accountnum) != null) {
			return false;
		}
		
		if (type.equalsIgnoreCase("d")) { 
			bank = new Deposit(accountnum, date, target, amount);
		} else { 
			bank = new Transfer(accountnum, date, target, amount);
		}
		
		bank.calculatePrice();
		
		bankList.add(bank);
		
		return true;
	}
	public static boolean DeleteBank(String accountNum) {
    	boolean removed = false;
        for (int i = 0; i < bankList.size(); i++) {
            if (bankList.get(i).getaccountNum().equalsIgnoreCase(accountNum)) {
                bankList.remove(i);
                removed = true;
                break;
            }
        }
        return removed;
    }
	
	
	public static String getBankList(String type) { 
		
		String result = "";
		Bank bank;
		
		for (int i = 0; i < bankList.size(); i++) {
			bank = bankList.get(i);
			
			if (type.equalsIgnoreCase("d") && bank instanceof Deposit) {
				result += ((Deposit)bank).toString() + "\n";
			} else if (type.equalsIgnoreCase("t") && bank instanceof Transfer) {
				result += ((Transfer)bank).toString() + "\n";
			} else if (type.equalsIgnoreCase("a")) {
				result += bank.toString() + "\n";
			}
		}
		
		return result;
	}
	
	public static String[] getaccountNums() {
		
		String[] result = new String[bankList.size()];
		
		for (int i = 0; i < result.length; i++) {
			result[i] = bankList.get(i).getaccountNum();
		}
		
		return result;
		
	}
	
	public static boolean readFromFile(String fileName) {
		
		try {
			bankList = new ArrayList<Bank>();
			
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			String[] splittedLine;
			Bank bank;
			Customer customer;
			String type, accountNum, date, target, money;
			
			while (scanner.hasNextLine()) {
				splittedLine = scanner.nextLine().split("%");
				
				type = splittedLine[0];
				accountNum = splittedLine[1];
				date = splittedLine[2];
				target = splittedLine[3];
				money = splittedLine[4];
				customer = new Customer(Integer.parseInt(splittedLine[5]), Integer.parseInt(splittedLine[6]), splittedLine[7],splittedLine[8]);
				
				if (type.equalsIgnoreCase("d")) {
					bank = new Deposit(accountNum, date, target, money);
				} else {
					bank = new Transfer(accountNum, date, target, money);
				}
				
				bank.setCustomer(customer);
				
				bank.calculatePrice();
				
				bankList.add(bank);
			}
			
			return true;
		}
		catch (Exception exc) {
			System.out.println(exc);
			return false;
		}
		
	}

	
	public static boolean writeToFile(String fileName) {
		 
		String line;
		Deposit deposit;
		Transfer transfer;
		
		File file = new File(fileName);
		
		if (!file.exists()) {
			System.out.println("File could not be found.");
			return false;
		}
		
		PrintWriter printWriter = null;
		
		try {
			printWriter = new PrintWriter(file); 
			
			for (Bank bank : bankList) {
				if (bank instanceof Deposit) {
					deposit = (Deposit)bank;
					line = "D%" + deposit.getaccountNum() + "%" + deposit.getDate() + "%" + deposit.getDirector() + "%" + deposit.getDeposit() + "%";
					line += deposit.customer.getId() + "%" + deposit.customer.getSSN() + "%" + deposit.customer.getName();
				} else {
					transfer = (Transfer)bank;
					line = "T%" + transfer.getaccountNum() + "%" + transfer.getDate() + "%" + transfer.getDestAccnum() + "%" + transfer.getMoney() + "%";
					line += transfer.customer.getId() + "%" + transfer.customer.getSSN() + "%" + transfer.customer.getName();
				}
				
				printWriter.println(line);
			}
			
			return true;
		}
		catch (Exception exc) {
			System.out.println(exc);
		}
		finally {
			if (printWriter != null) { 
				printWriter.close();
			}
		}
		
		return false;
		
	}
	
	public static void addCustomer( int id, int SSN, String name,String address) {
		
		Bank foundMedia = null;
		Customer customer;
		
		for (Bank bank : bankList) {
			if (bank.findName(name)) {
				foundMedia = bank;
				break;
			}
		}
		
		if (foundMedia != null) {
			customer = new Customer(id, SSN, name,address);
			foundMedia.setCustomer(customer);
		}
		
	}

}

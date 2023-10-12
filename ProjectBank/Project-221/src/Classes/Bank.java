package Classes;

public abstract class Bank implements Commission  {
	
	protected String accountNum;
	protected String date; 
	protected double price;
	protected Customer customer;
	protected double comission;
	
	public Bank(String accountNum, String date) {
		this.accountNum = accountNum;
		this.date = date;
		this.customer = new Customer();
	}
	
	public boolean findName(String accountNum) {
		return this.accountNum.equalsIgnoreCase(accountNum);
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public String getaccountNum() {
		return accountNum;
	}
	
	public String getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		String result = customer.toString()+ "\n\nBankAccount:\n"+"Account Number: " + accountNum + "\nDate: " + date + "\n";
		result += "Total Amount: " + price + "\n";
		return result;
	}
	
	public abstract void calculatePrice();
	
}
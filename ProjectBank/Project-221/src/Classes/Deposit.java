package Classes;

public class Deposit extends Bank {
	
	private String anotherAcc;
	private String deposit;
	
	public Deposit(String name, String date, String anotherAcc, String deposit) {
		super(name, date);
		this.anotherAcc = anotherAcc;
		this.deposit = deposit;
	}
	
	public String getDirector() {
		return anotherAcc;
	}
	
	public String getDeposit() {
		return deposit;
	}
	
	@Override
	public void calculatePrice() {
		price = addCommission(20);
	}
	
	@Override
	public String toString() {
		String result = "\nDeposit\n****************";
		result += super.toString();
		result +=  "\nTransaction\nAmount of Deposit: " + deposit + "\nComission: "+comission+"\n";
		return result;
	}

	@Override
	public double addCommission(double priceWithoutCommission) {
		return Double.parseDouble(deposit);
	}
	
}

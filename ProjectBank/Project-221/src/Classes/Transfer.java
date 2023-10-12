package Classes;

public class Transfer extends Bank {

	private String destAccnum;
	private String money;
	
	public Transfer(String name, String duration, String destAccnum, String money) {
		super(name, duration);
		this.destAccnum = destAccnum;
		this.money = money;
	}
	
	public String getDestAccnum() {
		return destAccnum;
	}
	
	public String getMoney() {
		return money;
	}
	
	@Override
	public void calculatePrice() { 
		price = addCommission(1);
	}
	
	@Override
	public String toString() {
		String result = "Transfer\n****************";
		result += super.toString();
		result += "\nTransaction:\nReciever Account Number: " + destAccnum + "\nAmount of Transfer: " + money + "\nComission: "+comission+"\n";
		return result;
	}

	@Override
	public double addCommission(double priceWithoutCommission) {
		comission=4.50;
		return -(Double.parseDouble(money)+comission);
	}
	
}
package Classes;

public class Customer {

	private int id; 
	private int ssn;
	private String name;
	private String address;

	public Customer(int id, int ssn, String name,String address) {
		this.id = id;
		this.ssn = ssn;
		this.name = name;
		this.address=address;
	}
	
	public Customer() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public int getSSN() {
		return ssn;
	}
	
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	
	@Override
	public String toString() {
		return "\nCustomer:"+"\nId: "  + id + "\nSSN: " + ssn + "\nName and Surname: " + name+"\nAddress: "+address;
	}
}
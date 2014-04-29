package deniro.user;

public class Order {

	private String userClientID;
	private String taxiType;
	private String address;
	
	public Order(String userClientID, String taxiType, String address) {
		setUserClientID(userClientID);
		setTaxiType(taxiType);
		setAddress(address);
	}
	
	public void setUserClientID(String userClientID) {
		this.userClientID = userClientID;
	}
	
	public String getUserClientID() {
		return userClientID;
	}
	
	private void setTaxiType(String taxiType) {
		this.taxiType = taxiType;
	}

	public String getTaxiType() {
		return taxiType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getOrderInfo() {
		return "This is the info of the order";
	}
}

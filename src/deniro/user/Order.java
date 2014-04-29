package deniro.user;

public class Order {

	private String userClientID;
	private String address;
	private String taxiType;
	private String time;
	
	public Order(String userClientID, String address, String taxiType, String time) {
		setUserClientID(userClientID);
		setAddress(address);
		setTaxiType(taxiType);
		setTime(time);
	}
	
	public void setUserClientID(String userClientID) {
		this.userClientID = userClientID;
	}
	
	public String getUserClientID() {
		return userClientID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	private void setTaxiType(String taxiType) {
		this.taxiType = taxiType;
	}

	public String getTaxiType() {
		return taxiType;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public String getOrderInfo() {
		return "Order info: " + getUserClientID() + ", " + getAddress() + ", " + getTaxiType() + ", " + getTime();
	}
	
}

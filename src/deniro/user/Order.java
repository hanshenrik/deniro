package deniro.user;

public class Order {

	private String userID;
	private String address;
	private String taxiType;
	private String time;
	
	public Order(String userID, String address, String taxiType, String time) {
		setUserID(userID);
		setAddress(address);
		setTaxiType(taxiType);
		setTime(time);
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getUserID() {
		return userID;
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
		return "Order info: " + getUserID() + ", " + getAddress() + ", " + getTaxiType() + ", " + getTime();
	}
	
}

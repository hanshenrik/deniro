package deniro.user;

public class Order {

	private String taxiType;
	private String userClientID;
	private int orderID;
	
	public Order(int orderID, String userClientID, String taxiType) {
		setTaxiType(taxiType);
		setUserClientID(userClientID);
		setOrderID(orderID);
	}

	private void setTaxiType(String taxiType) {
		this.taxiType = taxiType;
	}

	public String getTaxiType() {
		return taxiType;
	}
	
	public void setUserClientID(String userClientID) {
		this.userClientID = userClientID;
	}
	
	public String getUserClientID() {
		return userClientID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	public int getOrderID() {
		return orderID;
	}

}

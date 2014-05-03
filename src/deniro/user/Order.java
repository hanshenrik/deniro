package deniro.user;

import com.bitreactive.library.android.maps.model.Position;

public class Order {

	private String userID;
	private String taxiID;
	private String toAddress;
	private String fromAddress;
	private String taxiType;
	private String time;
	private String message;
	private int placeInQueue;
	private boolean isCancelled;
	private boolean isConfirmed;
	
	public Order(String userID, String fromAddress, String toAddress, String taxiType, String time) {
		setUserID(userID);
		setToAddress(toAddress);
		setFromAddress(fromAddress);
		setTaxiType(taxiType);
		setTime(time);
		setCancelled(false);
	}
	public Order(String taxiID,String fromAddress, String toAddress){
		setTaxiID(taxiID);
		setFromAddress(fromAddress);
		setToAddress(toAddress);
	}
	public Order(String taxiID, String toAddress){
		setTaxiID(taxiID);
		setToAddress(toAddress);
		fromAddress = null;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getUserID() {
		return userID;
	}

	public String getAddress() {
		return toAddress;
	}

	public void setToAddress(String address) {
		this.toAddress = address;
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
		return "Order info: " + getUserID() + ", "+","+getTaxiID() +", "+ getAddress() + ", " +
				getTaxiType() + ", " + getTime() +", "+isCancelled()+", "+isConfirmed()+
				","+getMessage()+", "+getPlaceInQueue();
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.isCancelled = cancelled;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void confirm(String taxiID) {
		this.taxiID = taxiID;
	}

	public String getTaxiID() {
		return taxiID;
	}

	public void setTaxiID(String taxiID) {
		this.taxiID = taxiID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPlaceInQueue() {
		return placeInQueue;
	}

	public void setPlaceInQueue(int placeInQueue) {
		this.placeInQueue = placeInQueue;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	
}

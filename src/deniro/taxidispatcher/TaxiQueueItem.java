package deniro.taxidispatcher;

import com.bitreactive.library.android.maps.model.Position;

public class TaxiQueueItem {

	private String taxiID;
	private Position position;
	
	public TaxiQueueItem(String taxiID, Position position) {
		setTaxiID(taxiID);
		setPosition(position);
	}

	public String getTaxiID() {
		return taxiID;
	}

	public void setTaxiID(String taxiID) {
		this.taxiID = taxiID;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}

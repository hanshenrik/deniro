package deniro.taxicentral;

import no.ntnu.item.arctis.runtime.Block;

public class TaxiCentral extends Block {

	public java.lang.String orderTopics = "userRequest,userCancel,unavailable,confirm";
	public java.lang.String stringTopics = "onduty,offduty,available";
	public java.lang.String subscribeTo = orderTopics + "," + stringTopics;
	
	public String createOrderTopics() {
		return orderTopics;
	}

	public String createStringTopics() {
		return stringTopics;
	}
	
}

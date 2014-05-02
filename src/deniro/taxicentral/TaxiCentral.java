package deniro.taxicentral;

import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;

public class TaxiCentral extends Block {

	public java.lang.String userTopics = "userRequest,userCancel";
	public java.lang.String taxiTopics = "onduty,offduty,available,unavaliable,confirm";
	public java.lang.String subscribeTo = userTopics + ", " + taxiTopics;
	

	public String createSubscribeTopics() {
		System.out.println("TaxiCentral: creating subscribeTopics...");
		System.out.println("\t "+subscribeTo);
		return subscribeTo;
	}


	public Object receivedObject(Object o) {
		Order order = (Order)o;
		System.out.println("TaxiCentral: received this order "+order.getOrderInfo());
		return o;
	}
}

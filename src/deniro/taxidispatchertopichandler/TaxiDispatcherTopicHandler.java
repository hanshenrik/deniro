package deniro.taxidispatchertopichandler;

import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;

public class TaxiDispatcherTopicHandler extends Block {

	public java.lang.String messageTopic;
	public java.lang.Object messageData;

	public String castToString(Object o) {
		return (String)o;
	}

	public Order castToOrder(Object o) {
		return (Order)o;
	}

}

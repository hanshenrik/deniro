package deniro.basicmqttserialized;

import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;

public class BasicMQTTSerialized extends Block {
	
	public Object sendingObject(Object o) {
		System.out.println("Sending: "+((Order) o).getOrderInfo());
		return o;
	}
	
	public String sendingString(String s) {
		System.out.println("Sending: "+s);
		return s;
	}

	public Object deserializedObject(Object o) {
		if (o instanceof Order) {
			System.out.println("Received this order: "+o);
		} else {
			System.out.println("Received this: "+o);
		}
		return o;
	}

	public String printSomething(String s) {
		System.out.println("### "+s);
		return s;
	}

}

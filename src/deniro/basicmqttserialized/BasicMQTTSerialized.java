package deniro.basicmqttserialized;

import no.ntnu.item.arctis.runtime.Block;

public class BasicMQTTSerialized extends Block {

	public String printError(String s) {
		System.out.println("BasicMQTTSerialized error: "+s);
		return s;
	}

}

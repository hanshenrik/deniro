package deniro.taxisystem.component;

import deniro.user.Order; // to be removed
import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public Order createMessageTest() { // to be removed
		return new Order("user0","address","type","time");
	}

}

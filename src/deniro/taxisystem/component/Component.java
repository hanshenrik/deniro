package deniro.taxisystem.component;

import deniro.user.Order; // to be removed
import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public Order createMessageTest() {
		return new Order(0,"0","maxi");
	}

}

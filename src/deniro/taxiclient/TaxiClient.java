package deniro.taxiclient;

import no.ntnu.item.arctis.runtime.Block;

public class TaxiClient extends Block {

	public String setType() {
		System.out.println("Type set to maxi/regular/cargo");
		return "regular"; //make dynamic by taking argument as input, should be set by 
	}

}

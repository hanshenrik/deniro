package deniro.taxi;

import no.ntnu.item.arctis.runtime.Block;

public class Taxi extends Block {

	public java.lang.String alias_taxiClientID;

	public void printSomething() {
		System.out.println("Taxi: "+alias_taxiClientID);
	}
	
	public static String getAlias(String alias) {
		return alias;
	}
}

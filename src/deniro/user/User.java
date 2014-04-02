package deniro.user;

import no.ntnu.item.arctis.runtime.Block;

public class User extends Block {

	public java.lang.String alias_userClientID;

	public void printSomething() {
		System.out.println("User: "+alias_userClientID);
	}
	
	public static String getAlias(String alias) {
		return alias;
	}
}

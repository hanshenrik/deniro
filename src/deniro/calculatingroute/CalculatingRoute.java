package deniro.calculatingroute;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;

import deniro.taxidispatcher.TaxiQueueItem;
import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;

public class CalculatingRoute extends Block {

	private static final String ENC = "UTF-8";

	public String createURI(LinkedList<TaxiQueueItem> taxiQueue,Order order) {
		System.out.println("CalculatingRoute...");
		String origins ="";
		String toAddress = order.getAddress();
		for(TaxiQueueItem tqi:taxiQueue){
			origins += ""+tqi.getPosition().getLatitude()/1000000+","+tqi.getPosition().getLongitude()/1000000+"|";
		}
		try {
			System.out.println("trying");
			origins += URLEncoder.encode(origins, ENC);
			toAddress = URLEncoder.encode(toAddress, ENC);
		} catch (UnsupportedEncodingException e) {
			// Deprecated code
			origins = URLEncoder.encode(origins);
			toAddress = URLEncoder.encode(toAddress);
		}
		
		return "http://maps.googleapis.com/maps/api/distancematrix/json?origin="+origins+"&destination="+toAddress+"&mode=driving&sensor=false";
	}

	public TaxiQueueItem getNearestTaxi(String request) {
		System.out.println("##############\n"+request);
		return null;
	}

}

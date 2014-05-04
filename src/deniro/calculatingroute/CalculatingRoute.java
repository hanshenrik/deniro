package deniro.calculatingroute;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;

import deniro.taxidispatcher.TaxiQueueItem;
import no.ntnu.item.arctis.runtime.Block;

public class CalculatingRoute extends Block {

	private static final String ENC = "UTF-8";

	public String createURI(LinkedList<TaxiQueueItem> TaxiQueue,String toAddress) {
		String origins ="";
		for(TaxiQueueItem tqi:TaxiQueue){
			origins += ""+tqi.getPosition().getLatitude()/1000000+","+tqi.getPosition().getLongitude()/1000000+"|";
		}
		try {
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
		System.out.println(request);
		return null;
	}

}

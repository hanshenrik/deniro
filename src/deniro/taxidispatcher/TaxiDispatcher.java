package deniro.taxidispatcher;

import java.util.LinkedList;

import com.bitreactive.library.android.maps.model.Position;

import deniro.user.Order;
import no.ntnu.item.arctis.runtime.Block;


public class TaxiDispatcher extends Block {

	public java.util.LinkedList<TaxiQueueItem> registeredTaxis;
	public java.util.LinkedList<TaxiQueueItem> availableTaxis;
	public java.util.LinkedList<Order> pendingOrders;
	public java.lang.String publishTopic;
	public deniro.user.Order tempOrder;

	public TaxiDispatcher() {
		this.registeredTaxis = new LinkedList<TaxiQueueItem>();
		this.availableTaxis = new LinkedList<TaxiQueueItem>();
		this.pendingOrders = new LinkedList<Order>();
	}
	
	public LinkedList<TaxiQueueItem> getAvailableTaxis() {
		return availableTaxis;
	}
	
	public TaxiQueueItem createTaxiQueueItem(String taxiInfo) {
		String[] info = taxiInfo.split(";");
		Position pos = new Position(Double.valueOf(info[1]), Double.valueOf(info[2]));
		return new TaxiQueueItem(info[0], pos);
	}
	
	public void taxiOnDuty(TaxiQueueItem tqi) {
		System.out.println("TaxiDispatcher: "+tqi+" now on duty");
		registeredTaxis.add(tqi);
	}

	public void taxiOffDuty(TaxiQueueItem tqi) {
		System.out.println("TaxiDispatcher: "+tqi+" now off duty (and unavailable)");
		availableTaxis.remove(tqi);
		registeredTaxis.remove(tqi);
	}
	
	public Order taxiAvailable(TaxiQueueItem tqi) {
		System.out.println("TaxiDispatcher: "+tqi+" now available");
		availableTaxis.add(tqi);
		
		if (!pendingOrders.isEmpty()) {
			return pendingOrders.pop();
		}
		return null;
	}
	
	public String taxiUnavailable(Order order) {
		System.out.println("TaxiDispatcher: "+order.getTaxiID()+" now unavailable");
		
		for (TaxiQueueItem t : availableTaxis) {
			if (order.getTaxiID().equals(t.getTaxiID())) {
				availableTaxis.remove(t);
			}
		}
		
		if (order.getUserID() == null) {
			System.out.println("TaxiDispatcher: noReject");
			return "noReject"; // no need to do anything more; is not handled as case in diagram
		}
		
		if (!availableTaxis.isEmpty()) {
			return "assignTaxi";
		} else {
			order.setMessage("No taxis available at the moment, see queue number...");
			order.setPlaceInQueue(pendingOrders.indexOf(order.getUserID()));
			return "queue";
		}
	}
	
	public Order taxiConfirmed(Order order) {
		System.out.println("TaxiDispatcher: taxi "+order.getTaxiID()+" confirming "+order.getOrderInfo());
		
		for (Order o : pendingOrders) {
			if (o.getUserID().equals(order.getUserID())) {
				pendingOrders.remove(o);
			}
		}
		
		order.setMessage("A taxi is on it's way!");
		
		return order;
	}
	
	public String requestHandler(Order order) {
		System.out.println("TaxiDispatcher: creating "+order.getOrderInfo());
		
		for (Order o : pendingOrders) {
			if (o.getUserID().equals(order.getUserID())) {
				pendingOrders.remove(o);
			}
		}
		
		pendingOrders.add(order);
		order.setPlaceInQueue(pendingOrders.indexOf(order.getUserID()));
		
		if (!availableTaxis.isEmpty()) {
			System.out.println("TaxiDispatcher: taxis are available!");
			return "assignTaxi";
		} else {
			order.setMessage("No taxis available at the moment, see queue number...");
			return "queue";
		}
	}
	
	public int numberOfAvailableTaxis() {
		System.out.println("TaxiDispatcher: number of avail taxis: "+availableTaxis.size());
		return availableTaxis.size();
	}
	
	public void printThis() {
		System.out.println("##### ");
	}
	
	public Order printOrder(Order o) {
		System.out.println("##### "+o.getOrderInfo());
		return o;
	}
	
	public LinkedList<TaxiQueueItem> printList(LinkedList<TaxiQueueItem> a) {
		System.out.println("##### "+a.size());
		return a;
	}
	
	public Order assignTaxi(Order order) {
		order.setTaxiID(availableTaxis.pop().getTaxiID());
		order.setMessage("Hang on a minute, contacting a taxi now...");
		System.out.println("TaxiDispatcher: assigned "+order.getTaxiID()+" to order "+order.getUserID());
		return order;
	}
	
	public Order assignSpecificTaxi(Order order, TaxiQueueItem tqi) {
		order.setTaxiID(tqi.getTaxiID());
		order.setMessage("Hang on a minute, contacting a taxi now...");
		
		for (TaxiQueueItem t : availableTaxis) {
			if (tqi.getTaxiID().equals(t.getTaxiID())) {
				availableTaxis.remove(t);
			}
		}
		
		System.out.println("TaxiDispatcher: assigned specific "+order.getTaxiID()+" to order "+order.getUserID());
		return order;
	}
	
	public String requestCancelled(Order order) {
		System.out.println("TaxiDispatcher: cancelling "+order.getOrderInfo());
		// not possible after taxi has confirmed!
		
		for (TaxiQueueItem tqi : availableTaxis) {
			if (tqi.getTaxiID().equals(order.getTaxiID())) {
				availableTaxis.add(tqi);
			}
		}
		
		for (Order o : pendingOrders) {
			if (o.getUserID().equals(order.getUserID())) {
				pendingOrders.remove(o);
			}
		}
		
		order.setMessage("Order cancelled!");
		
		return order.getUserID();
	}

	public String getUserID(Order order) {
		return order.getUserID();
	}
	
	public String getTaxiID(Order order) {
		return order.getTaxiID();
	}

}

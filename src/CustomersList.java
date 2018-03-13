import java.util.ArrayList;
import java.util.List;


public class CustomersList {

	private List<Customer> customers = new ArrayList<Customer>();

	public void addToList(Customer customer) {
		synchronized (this) {
			
			while (customers.size() > 100) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			customers.add(customer);
			// Notifies any thread in state holding you can continue
			// and release synchronization
			notifyAll();
		}
	}

	public Customer getFromList() {
		synchronized (this) {
			while (customers.size() == 0) {
				try {
					
					//Holding state ie release the synchronization 
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//Remove first item in list and return it >>  remove(0)
			if (customers.size() > 100) notifyAll();
			return customers.remove(0);
		}
	}
}


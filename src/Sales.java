/**
 * 
 * @author dmclark
 */


public interface Sales {

	/**
	 * 
	 * @param message messages to be processed
	 * @return returns whether the message was accepted or whether no new messages are being accepted
	 */
	public boolean new_message(String message);
	
	/**
	 * 
	 * @return returns whether it is accepting new messages
	 */
	public boolean is_receiving();
	

	
	public String log_sales_total();
	
	public String log_adjustments();
	
}

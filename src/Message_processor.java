import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * @author dmclark
 *
 */
public class Message_processor implements Sales {

	private boolean is_active;
	private int log_frequency;
	private int log_adjustments;

	private ArrayList<Message> messages;
	ArrayList<Product> sales_log;

	public Message_processor() {
		is_active = true;
		log_frequency = 10;
		log_adjustments = 50;

		messages = new ArrayList<Message>();
		sales_log = new ArrayList<Product>();

	}

	/**
	 * Stop the system from accepting any more messages
	 * 
	 * @return returns the activity status
	 */
	public boolean stop_receiving() {
		is_active = false;
		return is_active;
	}

	/**
	 * allows them system to accept more messages
	 * 
	 * @return returns the activity status
	 */
	public boolean start_receiving() {
		is_active = true;
		return is_active;
	}

	/**
	 *  returns the activity status
	 * @return  returns the activity status
	 */
	public boolean is_receiving() {

		return is_active;
	}
	
	
	/**
	 * adds new messages to the system
	 * @param message messages to be processed
	 * @return returns whether the message was accepted or whether no new messages are being accepted
	 */
	public boolean new_message(String message_s) {
		if (is_active) {

			Message message = message_type_check(message_s);
			if (message.get_type() == 3) {
				messages.add(message);
				perform_operations(message);

			} else {

				messages.add(message);

			}
			add_to_total(message);

			if ((messages.size() % log_frequency) == 0) {
				System.out.println(log_sales_total());
			}
			if ((messages.size() % log_adjustments) == 0) {

				System.out.println("system paused no new messages will be accepted");
				stop_receiving();
				System.out.println(log_adjustments());
			}

			return true;
		} else {
			return false;
		}

	}
/**
 * the message the message gets added to the total sales and value for that specific product
 * @param message accepts any type of message and applies it to the totals
 */
	public void add_to_total(Message message) {
		int index = 0;
		boolean found = false;
		while (sales_log.size() > index && !found) {

			if (sales_log.get(index).get_products_type().equals(message.get_products_type())) {

				if (message.get_type() != 3) {

					sales_log.get(index).add_sale(message.get_amount(), message.get_price());
				} else {
					if (message.get_operation() == '+') {

						sales_log.get(index).set_total_value(sales_log.get(index).get_total_value()
								+ (message.get_price() * sales_log.get(index).get_sales()));
					} else if (message.get_operation() == '-') {

						sales_log.get(index).set_total_value(sales_log.get(index).get_total_value()
								- (message.get_price() * sales_log.get(index).get_sales()));

					} else {

						if (message.get_price() > 0) {

							sales_log.get(index)
									.set_total_value(message.get_price() * sales_log.get(index).get_total_value());

						}
					}

				}

				found = true;
			}
			index++;

		}
		if (!found) {

			sales_log.add(new Product(message.get_products_type()));
			sales_log.get(sales_log.size() - 1).add_sale(message.get_amount(), message.get_price());

		}

	}
/**
 * @return returns a string containing a list of all items the number salt and a total value
 */
	public String log_sales_total() {
		String log = "";
		int length = sales_log.size();
		int index = 0;
		int total = 0;

		while (index < length) {

			log = log + "[" + sales_log.get(index).toString() + "] ";

			index++;

		}

		return log;

	}
	/**
	 * @return returns a string containing a list of all adjustments made to the sales
	 */
	public String log_adjustments() {

		String log = "";
		int length = messages.size();
		int index = 0;
		while (index < length) {

			if (messages.get(index).get_type() == 3) {

				log = log + "[" + messages.get(index).toString() + "] ";
			}
			index++;
		}

		return log;
	}
/**
 * Take send a message and if there is an operation to be carried out it will apply the operation to all other relevant sales
 * @param message message which contains an operation to be carried out
 */
	public void perform_operations(Message message) {
		int length = messages.size();
		int index = 0;
		while (index < length) {

			if (messages.get(index).get_type() != 3) {

				Character operation = messages.get(index).get_operation();
				int operation_amount = messages.get(index).get_price();

				messages.get(index).operation(operation, operation_amount);

			}
			index++;
		}
	}
/**
 * Takes a message in a string form and passes that to detect the message type  and then creates a message object which it then returns
 * @param message_s the message to be processed as a string
 * @return the message as a Message object
 */
	private Message message_type_check(String message_s) {
		Message message = null;
		StringTokenizer msg = new StringTokenizer(message_s, " ");

		if (msg.countTokens() == 7) {

			// 20_sales_of_apples_at_10p_each
			// 1__2_____3__4______5__6___7
			int amount = Integer.parseInt(msg.nextToken()); // Token_1
			String temp = msg.nextToken(); // Token_2
			temp = msg.nextToken(); // Token_3
			String products_type = msg.nextToken(); // Token_4
			temp = msg.nextToken(); // Token_5
			String price_s = msg.nextToken(); // Token_6
			int price = Integer.parseInt(price_s.replace("p", ""));

			message = new Message(2, price, amount, products_type, ' ');

		} else {

			String temp_1 = msg.nextToken(); // Token_1
			String temp_2 = msg.nextToken(); // Token_2
			String temp_3 = msg.nextToken(); // Token_3

			if ("add subtract multiply".contains(temp_1)) {
				Character operation = ' ';
				if (temp_1.contains("add")) {
					operation = '+';
				} else if (temp_1.contains("subtract")) {
					operation = '-';
				} else {
					operation = '*';
				}

				String products_type = temp_3; // Token_4

				String price_s = temp_2; // Token_6
				int price = Integer.parseInt(price_s.replace("p", ""));

				message = new Message(3, price, 0, products_type, operation);
			} else {

				String products_type = temp_1;

				String price_s = temp_3;
				int price = Integer.parseInt(price_s.replace("p", ""));

				message = new Message(1, price, 1, products_type, ' ');

			}

		}

		return message;
	}

}

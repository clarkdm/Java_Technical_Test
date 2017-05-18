/**
 * 
 * @author dmclark
 *
 */
public class Message {

	private int type = 0;
	private int price = 0;
	private String products_type = "";
	private Character operation = ' ';
	private int amount = 0;

	public Message(int type, int price, int amount, String products_type, Character operation) {
		this.type = type;
		this.price = price;
		this.products_type = products_type;
		this.amount = amount;
		this.operation = operation;
	}

	/**
	 * 
	 * @return returns a number of items sold
	 */
	public int get_amount() {

		return amount;
	}

	/**
	 * 
	 * @return returns the type of the message Type 1 "bananas at 76p" Type 2 "5
	 *         sales of oranges at 2p each" Type 3 "add 20p apples"
	 */
	public int get_type() {

		return type;
	}

	/**
	 * 
	 * @return returns the price that I can so that
	 */
	public int get_price() {

		return price;
	}

	/**
	 * 
	 * @return returns the product type
	 */
	public String get_products_type() {

		return products_type;
	}

	/**
	 * 
	 * @return returns the type of operation to be carried out or if no
	 *         operation to be carried out returns ' '
	 */
	public Character get_operation() {
		return operation;
	}

	/** 
	 * Carries out an operation on the sale in the message
	 * 
	 * @param operation
	 *            contains a type of operation add subtract or multiply
	 * @param operation_amount
	 *            contains the amount to be added subtracted or multiplied by
	 */
	public void operation(Character operation, int operation_amount) {

		if (operation == '+') {
			price = price + operation_amount;
		} else if (operation == '-') {
			price = price - operation_amount;

		} else {
			if (operation_amount > 0) {
				price = price * operation_amount;
			}
		}
	}
/**
 * Returns all the details of the sale or operation as a string
 */
	public String toString() {
		if (type == 1) {
			return (amount + " " + products_type + " " + "at" + " " + price + "p");
		} else if (type == 2) {
			return (amount + " " + products_type + " " + "at" + " " + price + "p");
		} else {
			return (products_type + " " + operation + " " + price + "p");
		}

	}

}

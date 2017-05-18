/**
 * 
 * @author dmclark
 *
 */
public class Product {
	int total_value;
	int sales;
	String products_type;
	public Product(String products_type){
		
		this.products_type = products_type;
		total_value = 0;
		sales = 0;
	}
	/**
	 * 
	 * @return returns the total number of this item sold
	 */
	public int get_sales() {

		return sales;
	}
/**
 * 
 * @return returns a total value of the fighting type sold
 */
	public int get_total_value() {

		return total_value;
	}
	/**
	 * 
	 * @param sales sets the new total number of items sold  of this type
	 * @return new total number of items sold  of this type
	 */
	public int set_sales(int sales) {
		this.sales = sales;
		return sales;
	}
/**
 * 
 * @param total_value sets the new total value of this item type sold
 * @return returns a total value salt of this item type
 */
	public int set_total_value(int total_value) {
		this.total_value = total_value;
		return total_value;
	}
	/**
	 * 
	 * @return returns the item type
	 */
	public String get_products_type() {

		return products_type;
	}

/**
 * Adds more sales of the fighting and their value
 * @param amount the amount of additional items sold
 * @param price the price which the additional items were sold at
 */
	public void add_sale(int amount ,int price) {
		sales = sales + amount;
		total_value = total_value + (amount * price);
		
		 
	}
	
	public String toString() {
		
		return (sales + " " + products_type + " " + "totalling" + " " + total_value + "p");
		
	}

	 

} 
 
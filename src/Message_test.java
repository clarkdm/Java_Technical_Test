import static org.junit.Assert.*;

import org.junit.Test;

public class Message_test {

	@Test
	public void test_Message_type_1() {
		int price = 10;
		String products_type = "apple";
		Message message = new Message(1, price, 1, products_type, ' ');

		assertEquals(price, message.get_price());
		assertEquals(products_type, message.get_products_type());
		assertEquals(1, message.get_amount());
		assertEquals(1, message.get_type());
		assertEquals("1 " + products_type + " at " + price + "p", message.toString());
		message.operation('+', 5);
		assertEquals("1 " + products_type + " at " + (price + 5) + "p", message.toString());
		message.operation('-', 5);
		assertEquals("1 " + products_type + " at " + price + "p", message.toString());
		message.operation('*', 2);
		assertEquals("1 " + products_type + " at " + (price * 2) + "p", message.toString());
		message.operation('*', -1);
		assertEquals("1 " + products_type + " at " + (20) + "p", message.toString());
	}

	@Test
	public void test_Message_type_2() {
		int price = 10;
		String products_type = "apple";
		int amount = 5;
		Message message = new Message(2, price, amount, products_type, ' ');
		assertEquals(price, message.get_price());
		assertEquals(products_type, message.get_products_type());
		assertEquals(amount, message.get_amount());
		assertEquals(2, message.get_type());
		assertEquals(amount + " " + products_type + " at " + price + "p", message.toString());
		message.operation('+', 5);
		assertEquals(amount + " " + products_type + " at " + (price + 5) + "p", message.toString());
		message.operation('-', 5);
		assertEquals(amount + " " + products_type + " at " + price + "p", message.toString());
		message.operation('*', 2);
		assertEquals(amount + " " + products_type + " at " + (price * 2) + "p", message.toString());
		message.operation('-', 30);
		assertEquals(amount + " " + products_type + " at " + ((price * 2) - 30) + "p", message.toString());
	}

	@Test
	public void test_Message_type_3() {
		int price = 10;
		String products_type = "apple";
		Character operation = '+';
		Message message = new Message(3, price, 0, products_type, operation);
		assertEquals(price, message.get_price());
		assertEquals(products_type, message.get_products_type());
		assertEquals(0, message.get_amount());
		assertEquals(3, message.get_type());
		assertEquals(operation, message.get_operation());
		assertEquals(products_type + " " + operation + " " + price + "p", message.toString());
	}

	@Test
	public void test_Sales_1() {

		Message_processor test = new Message_processor();

		assertTrue(test.start_receiving());
		assertFalse(test.stop_receiving());

		assertFalse(test.is_receiving());

		assertTrue(test.start_receiving());

		assertTrue(test.is_receiving());

		assertTrue(test.new_message("apples at 10p"));
		assertTrue(test.new_message("apples at 4p"));
		assertTrue(test.new_message("oranges at 20p"));
		assertTrue(test.new_message("oranges at 15p"));

		assertTrue(test.new_message("2 sales of apples at 5p each"));
		assertTrue(test.new_message("5 sales of oranges at 2p each"));

		assertEquals(test.log_sales_total(), "[4 apples totalling 24p] [7 oranges totalling 45p] ");

		assertTrue(test.new_message("add 20p apples"));
		assertTrue(test.new_message("subtract 1p oranges"));
		assertTrue(test.new_message("multiply  2p apples"));

		assertEquals(test.log_sales_total(), "[4 apples totalling 208p] [7 oranges totalling 38p] ");

	}

	
}

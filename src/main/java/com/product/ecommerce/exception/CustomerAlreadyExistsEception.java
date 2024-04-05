package com.product.ecommerce.exception;

public class CustomerAlreadyExistsEception extends RuntimeException {
	
	/**Creating custom exception that can be thrown when user tries to add a customer that already exists
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	 
    public CustomerAlreadyExistsEception() {}
 
    public CustomerAlreadyExistsEception(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
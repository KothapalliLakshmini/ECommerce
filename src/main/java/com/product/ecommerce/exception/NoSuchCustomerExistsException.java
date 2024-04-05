package com.product.ecommerce.exception;

public class NoSuchCustomerExistsException extends RuntimeException {
	
	/**Creating custom exception that can be thrown when user tries to update/delete a customer that doesn't exists
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	 
    public NoSuchCustomerExistsException() {}
 
    public NoSuchCustomerExistsException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}



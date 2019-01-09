package net.kzn.onlineshopping.model;

import java.io.Serializable;

import net.kzn.shoppingbackend.dto.Cart;

public class UserModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String Role) {
		role = Role;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private int userId;
	  private String fullName;
	  private String email;
	  private String role;
	  private Cart cart;
}

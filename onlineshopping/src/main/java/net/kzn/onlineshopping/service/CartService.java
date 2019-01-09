package net.kzn.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kzn.onlineshopping.model.UserModel;
import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;
import net.kzn.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {
   @Autowired 
   HttpSession session;
   @Autowired
   CartLineDAO cartLineDAO;
   @Autowired
   ProductDAO productDAO;
   
   private Cart getCart() {
	   UserModel userModel= (UserModel)session.getAttribute("userModel");
       return userModel.getCart();
   }
   public List<CartLine> getCartLines(){
	    return cartLineDAO.list(getCart().getId());
   }
public String updateCartLine(int id, int count) {
	CartLine cartLine = cartLineDAO.get(id);
	  if(cartLine==null) {
		   return "result=error";
	  }
	  else {
		    Product product= cartLine.getProduct();
		     if(product.getQuantity()<count) {
		    	 count=product.getQuantity();
		     }
		     cartLine.setProductCount(count);
		     cartLine.setBuyingPrice(product.getUnitPrice());
		     double oldTotal =cartLine.getTotal();
		     cartLine.setTotal(cartLine.getProductCount()*cartLine.getBuyingPrice());
	       	 Cart cart = this.getCart();
		       cart.setGrandTotal(cart.getGrandTotal()-oldTotal + cartLine.getTotal());
		       cartLineDAO.update(cartLine);
		       cartLineDAO.updateCart(cart);
		     return "result=updated";
	     }
	
    }
public String deleteCartLine(int id) {
	CartLine cartLine = cartLineDAO.get(id);
	  if(cartLine==null) {
		   return "result=error";
	  }
	  else {
		     double total =cartLine.getTotal();
	       	   Cart cart = this.getCart();
		       cartLineDAO.delete(cartLine);
		       cart.setGrandTotal(cart.getGrandTotal()-total);
		       cartLineDAO.updateCart(cart);
		     return "result=deleted";
	     }
  }
public String addCartLine(int productId) {
	// TODO Auto-generated method stub
	   Cart cart = this.getCart();
	  CartLine cartLine= cartLineDAO.getByCartAndProduct(cart.getId(), productId);
	   Product product = productDAO.get(productId);   
	  if(cartLine==null) {
	    	    cartLine = new CartLine();
	    	    cartLine.setAvailable(true);
	    	    
	    	    cartLine.setBuyingPrice(product.getUnitPrice());
	    	    cartLine.setCartId(cart.getId());
	    	    cartLine.setProduct(product);
	    	    cartLine.setProductCount(1);
	    	    cartLine.setTotal(product.getUnitPrice());
	    	    cartLineDAO.add(cartLine);
	    	    cart.setCartLines(cart.getCartLines()+1);
	    	    cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
	    	    cartLineDAO.updateCart(cart);
	         return "result=added";    
	  }
	  else {
		     if(cartLine.getProductCount()<3) {
		     cartLine.setProductCount(cartLine.getProductCount()+1);
		       double oldTotal =cartLine.getTotal();
		     cartLine.setTotal(product.getUnitPrice()*cartLine.getProductCount());
		     cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal()-oldTotal);
		     cartLineDAO.update(cartLine);
		     cartLineDAO.updateCart(cart);
		     
		     return "result=added"; 
		     }
		     else {
		    	 return "result=exceed";
		     }
	  }

   }
}

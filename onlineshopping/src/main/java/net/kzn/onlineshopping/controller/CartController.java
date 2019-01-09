package net.kzn.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.onlineshopping.service.CartService;
import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dto.CartLine;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;
	@Autowired
	CartLineDAO cartLineDAO;
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name="result",required=false) String result) {
		ModelAndView mv= new ModelAndView("page");    
		if(result!=null) {
            	 switch(result) {
            	 case "error": mv.addObject("message","Something went wrong..");
            	               break;
            	 case "updated": mv.addObject("message","Cart updated Successfully");
            	                    break;
            	 case "deleted": mv.addObject("message","CartLine deleted Successfully");
                                     break;
            	 case "added": mv.addObject("message","Product added  Successfully");
                 break;
            	 case "exceed": mv.addObject("message","Product Count can not be greater than 3");
                 break;
            	 }
            	
             }		
 		
		mv.addObject("userClickShowCart",true);
		mv.addObject("title","User Cart");
		mv.addObject("cartLines",cartService.getCartLines());
		return mv;
	}	
	@RequestMapping("/{cartLineId}/update")
	  public String updateCart(@PathVariable int cartLineId,@RequestParam int count) {
	   	String response =  cartService.updateCartLine(cartLineId, count);
		return "redirect:/cart/show?"+response;		
	}
	@RequestMapping("/{cartLineId}/delete")
	  public String deleteCart(@PathVariable int cartLineId) {
	   	String response =  cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?"+response;		
	}
	@RequestMapping("/add/{productId}/product")
	  public String addCart(@PathVariable int productId) {
	   	String response =  cartService.addCartLine(productId);
		return "redirect:/cart/show?"+response;		
	}
}

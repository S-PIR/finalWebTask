package by.epamlab.controller;

import by.epamlab.exception.ProductNotFoundException;
import by.epamlab.exception.QuantityOutOfRangeException;
import by.epamlab.model.beans.Product;
import by.epamlab.model.beans.cart.Cart;
import by.epamlab.model.beans.cart.Saleable;
import by.epamlab.repositories.ProductRepository;
import by.epamlab.service.CartService;
import by.epamlab.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository repository;


    @GetMapping("/cart")
    public String getCartItems(Map<String, Object> model){
        Cart cartDetail = cartService.getCart();
        Map<Saleable, Integer> products = cartDetail.getItemWithQuantity();
        if (!products.isEmpty()){
            BigDecimal totalPrice = cartDetail.getTotalPrice();
            Integer itemQuantity = cartDetail.getItemQuantity();
            model.put("products", products);
            model.put("totalPrice", totalPrice);
            model.put("itemQuantity", itemQuantity);
            return "cart";
        } else {
            return "emptyCart";
        }

    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam(value = "productId", required = true) Integer productId,
                            @RequestParam(value = "itemNumber", required = true) Integer itemNumber,
                            RedirectAttributes redirectAttributes,
                            Map<String, Object> model){
        Product product = productService.findProduct(productId);
        int availableAmount = product.getQuantity();
        Cart cartDetail = cartService.getCart();
        int itemNumberInCart;
        try {
            itemNumberInCart = cartDetail.getQuantity(product);
        } catch (ProductNotFoundException e) {
            itemNumberInCart = 0;
        }
        if (availableAmount >= itemNumber + itemNumberInCart){
            cartDetail.add(product, itemNumber);
            redirectAttributes.addFlashAttribute("product", product);
            redirectAttributes.addFlashAttribute("itemQuantity", cartService.getCart().getItemQuantity());
            return "redirect:/showProductDescription";
        } else {
            model.put("availableAmount", availableAmount);
            model.put("productId", productId);
            return "failedPurchase";
        }
    }

    @PostMapping("/buyNow")
    public String buyNow(@RequestParam(value = "productId", required = true) Integer productId,
                         @RequestParam(value = "itemNumber", required = true) Integer itemNumber,
                         Map<String, Object> model){
        Product product = productService.findProduct(productId);
        int availableAmount = product.getQuantity();
        if (availableAmount > 0 && availableAmount >= itemNumber){
            product.setQuantity(availableAmount - itemNumber);
            repository.update(product);
            return "redirect:/successPurchase";
        } else {
            model.put("availableAmount", availableAmount);
            model.put("productId", productId);
            return "failedPurchase";
        }
    }

    @PostMapping("/buyNowAllCart")
    public String buyNowAllCart(Map<String, Object> model){
        Map<Saleable, Integer> products = cartService.getCart().getItemWithQuantity();
        try {
            repository.updateAll(products);
            cartService.getCart().clear();
            return "redirect:/successPurchase";
        } catch (QuantityOutOfRangeException e){
            model.put("availableAmount", e.getValue());
            model.put("productId", e.getProductId());
            return "failedPurchase";
        }
    }

    @PostMapping("/removeItem")
    public String removeItemFromCart(@RequestParam(value = "productId", required = true) Integer productId,
                                     Map<String, Object> model){
        Product product = productService.findProduct(productId);
        try {
            cartService.getCart().remove(product);
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/cart";
    }


}



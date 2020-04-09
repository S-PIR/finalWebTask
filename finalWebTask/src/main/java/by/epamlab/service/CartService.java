package by.epamlab.service;

import by.epamlab.model.beans.cart.Cart;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {
    private Cart cart = new Cart();

    public Cart getCart(){
        return cart;
    }
}



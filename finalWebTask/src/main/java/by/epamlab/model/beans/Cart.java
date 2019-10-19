package by.epamlab.model.beans;

import by.epamlab.exception.ProductNotFoundException;
import by.epamlab.exception.QuantityOutOfRangeException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    public static final String NOT_VALID_QUANTITY = " is not valid quantity. It must be non-negative.";

    private Map<Saleable, Integer> cartItemMap = new HashMap<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private int totalQuantity = 0;

    public void add(final Saleable saleable, int quantity){
        if (cartItemMap.containsKey(saleable)){
            cartItemMap.put(saleable, cartItemMap.get(saleable) + quantity);
        } else {
            cartItemMap.put(saleable, quantity);
        }
        totalPrice = totalPrice.add(saleable.getPrice().multiply(BigDecimal.valueOf(quantity)));
        totalQuantity += quantity;
    }

    public void update(final Saleable saleable, int quantity) throws ProductNotFoundException, QuantityOutOfRangeException{
        if (!cartItemMap.containsKey(saleable)) throw new ProductNotFoundException();
        if (quantity < 0) throw new QuantityOutOfRangeException(quantity + NOT_VALID_QUANTITY);

        int productQuantity = cartItemMap.get(saleable);
        BigDecimal productPrice = saleable.getPrice().multiply(BigDecimal.valueOf(productQuantity));

        cartItemMap.put(saleable, quantity);

        totalQuantity = totalQuantity - productQuantity + quantity;
        totalPrice = totalPrice.subtract(productPrice).add(saleable.getPrice().multiply(BigDecimal.valueOf(quantity)));
        totalQuantity -= quantity;
    }

    public void remove(final Saleable saleable, int quantity) throws ProductNotFoundException, QuantityOutOfRangeException{
        if (!cartItemMap.containsKey(saleable)) throw new ProductNotFoundException();

        int productQuantity = cartItemMap.get(saleable);

        if (quantity < 0 || quantity > productQuantity) throw new QuantityOutOfRangeException();

        if (productQuantity == quantity){
            cartItemMap.remove(saleable);
        } else {
            cartItemMap.put(saleable, productQuantity - quantity);
        }

        totalPrice = totalPrice.subtract(saleable.getPrice().multiply(BigDecimal.valueOf(quantity)));
        totalQuantity -= quantity;
    }

    public void remove(final Saleable saleable) throws ProductNotFoundException {
        if (!cartItemMap.containsKey(saleable)) throw new ProductNotFoundException();
        int quantity = cartItemMap.get(saleable);
        cartItemMap.remove(saleable);
        totalPrice = totalPrice.subtract(saleable.getPrice().multiply(BigDecimal.valueOf(quantity)));
        totalQuantity -= quantity;
    }

    public void clear(){
        cartItemMap.clear();
        totalPrice = BigDecimal.ZERO;
        totalQuantity = 0;
    }

    public int getQuantity(final Saleable saleable) throws ProductNotFoundException {
        if (!cartItemMap.containsKey(saleable)) throw new ProductNotFoundException();
        return cartItemMap.get(saleable);
    }

    public BigDecimal getCost(final Saleable saleable) throws ProductNotFoundException {
        if (!cartItemMap.containsKey(saleable)) throw new ProductNotFoundException();
        return saleable.getPrice().multiply(BigDecimal.valueOf(cartItemMap.get(saleable)));
    }

    public BigDecimal getTotalPrice(){
        return totalPrice;
    }

    public Map<Saleable, Integer> getCartItemMap() {
        Map<Saleable, Integer> cartItemMap = new HashMap<>();
        cartItemMap.putAll(this.cartItemMap);
        return cartItemMap;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }
}

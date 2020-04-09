package by.epamlab.model.beans.cart;

import by.epamlab.exception.ProductNotFoundException;
import by.epamlab.exception.QuantityOutOfRangeException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Cart {
    public static final String NOT_VALID_QUANTITY = " is not valid quantity. It must be non-negative.";

    private Map<Saleable, Integer> cartItemMap = new HashMap<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private int totalQuantity = 0;

    public void add(final Saleable saleable, int quantity){
        if (quantity <= 0){
            throw new QuantityOutOfRangeException(quantity + NOT_VALID_QUANTITY);
        }
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

    public Map<Saleable, Integer> getItemWithQuantity() {
        Map<Saleable, Integer> cartItemMap = new HashMap<>();
        cartItemMap.putAll(this.cartItemMap);
        return cartItemMap;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getItemQuantity(){
        return cartItemMap.size();
    }

    public Set<Saleable> getProducts(){
        return cartItemMap.keySet();
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        for (Map.Entry<Saleable, Integer> entry : cartItemMap.entrySet()) {
            strBuilder.append(String.format("Product: %s, Unit Price: %f, Quantity: %d%n", entry.getKey().getName(), entry.getKey().getPrice(), entry.getValue()));
        }
        strBuilder.append(String.format("Total Quantity: %d, Total Price: %f", totalQuantity, totalPrice));

        return strBuilder.toString();
    }


}

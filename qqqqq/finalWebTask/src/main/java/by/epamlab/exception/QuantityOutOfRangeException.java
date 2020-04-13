package by.epamlab.exception;

public class QuantityOutOfRangeException extends IllegalArgumentException {
    public static final String DEFAULT_MESSAGE = " Quantity is out of range.";

    private int value;
    private int productId;

    public QuantityOutOfRangeException() {
        super(DEFAULT_MESSAGE);
    }

    public QuantityOutOfRangeException(String message) {
        super(message);
    }

    public QuantityOutOfRangeException(int value, int productId) {
        super(value + DEFAULT_MESSAGE);
        this.value = value;
        this.productId = productId;
    }

    public int getValue() {
        return value;
    }

    public int getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "value=" + value + DEFAULT_MESSAGE;
    }
}

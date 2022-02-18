package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {

    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product)
    {
        this.addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity)
    {
        if (quantity < 1)
        {
            throw new IllegalArgumentException("Quantity cannot be negative or 0");
        }
        this.products.put(product, quantity);
    }

    public BigDecimal getNetTotal()
    {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.products.keySet()) {
            Integer quantity = this.products.get(product);
            BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(quantity);
            BigDecimal priceOfThisItem = product.getPrice().multiply(quantityAsBigDecimal);
            sum = sum.add(priceOfThisItem);
        }
        return sum;
    }

    public BigDecimal getTax()
    {
        BigDecimal tax = BigDecimal.ZERO;
        if (products.size() == 0) {
            return BigDecimal.ZERO;
        } else {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                tax = tax.add(new BigDecimal(entry.getValue()).multiply(entry.getKey().getPrice().multiply(entry.getKey().getTaxPercent())));
            }
        }
        return tax;
    }

    public BigDecimal getTotal()
    {
        BigDecimal total = BigDecimal.ZERO;
        if (products.size() == 0) {
            return BigDecimal.ZERO;
        } else {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                total = total.add(new BigDecimal(entry.getValue()).multiply(entry.getKey().getPrice()));
            }
        }
        return total;
    }
}

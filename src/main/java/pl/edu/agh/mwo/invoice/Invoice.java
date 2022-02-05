package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
//    private Collection<Product> products = new ArrayList<>();

    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product)
    {
        this.addProduct(product, 1); //dodanie jednego produktu do mapy
    }

    public void addProduct(Product product, Integer quantity)
    {
        this.products.put(product, quantity); //dodanie danej ilosci produktow do mapy
    }

    public BigDecimal getNetTotal()
    {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.products.keySet()) {
            Integer quantity = this.products.get(product);
            BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(quantity);
            BigDecimal priceOfThisItem = product.getPrice().multiply(quantityAsBigDecimal);
            sum = sum.add(priceOfThisItem); //suma = suma + price (dla sumy typu BigDecimal)
        }
        return sum;
    }

    public BigDecimal getTax()
    {
        return BigDecimal.ZERO;
    }

    public BigDecimal getTotal()
    {
        return BigDecimal.ZERO;
    }
}

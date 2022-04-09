package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<Product, Integer>();
    private Integer invoiceNumber;

    public Invoice() {
        if (invoiceNumber == null) {
            generateInvoiceNumber();
        }
    }

    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }
    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    private void generateInvoiceNumber() {
        Random rand = new Random();
        final int max = 2000000;
        invoiceNumber = rand.nextInt(max);
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    private BigDecimal getGrossTotalForPrinting(BigDecimal priceWithTax, BigDecimal quantity) {
        return priceWithTax.multiply(quantity);
    }

    public String printing() {
        StringBuilder builder = new StringBuilder();
        String invoiceNumber = "Numer faktury: " + getInvoiceNumber() + "\n";
        builder.append(invoiceNumber);
        for (Map.Entry<Product, Integer> entry : products.entrySet())
        {
            builder.append(entry.getKey().getName())
                    .append(" Ilosc: ")
                    .append(entry.getValue())
                    .append(" Cena: ")
                    .append(getGrossTotalForPrinting(entry.getKey().getPriceWithTax(),
                            BigDecimal.valueOf(entry.getValue())))
                    .append("\n");
        }
        builder.append("lICZBA POZYCJI: ").append(products.size());
        return builder.toString();
    }
}

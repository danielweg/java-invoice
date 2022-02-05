package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {

        if (name == null)
        {
            throw new IllegalArgumentException("Product name cannot be null");
        }

        if (name.equals(""))
        {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        if (price == null)
        {
            throw new IllegalArgumentException("Price cannot be null");
        }

        if (price.signum() == -1) //signum zwraca -1 jak price jest ujemne
        {
            throw new IllegalArgumentException("Price cannot be negative");
        }


        this.name = name;
        this.price = price;
        this.taxPercent = tax;
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getTaxPercent() {
        return this.taxPercent;
    }

    public BigDecimal getPriceWithTax()
    {
        return this.price.multiply(this.taxPercent).add(this.price);

        //this.price.multiply(this.taxPercent) - mnoze price * tax percent
        //.add(this.price) - do tego co pomnozylem dodaje price
        //dlatego tak robimy jak powyzej bo operujemy na BigDecimal
    }
}

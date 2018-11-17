package model;

import com.google.common.base.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.List;
import java.util.StringJoiner;

public class Product {

    private String name;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal total;

    public Product(WebElement productRow) {
        List<WebElement> tData = productRow.findElements(By.cssSelector("td"));
        name = String.valueOf(tData.get(1).getText()).replaceAll("-", "");
        quantity = Integer.parseInt(tData.get(2).findElement(By.cssSelector("input[name='quantity'][type='text']")).getAttribute("value"));
        price = new BigDecimal(productRow.findElement(By.cssSelector(".wpsc_product_quantity + td")).getText().replaceAll("[$,]", ""));
        total = new BigDecimal(productRow.findElement(By.cssSelector(".wpsc_product_price")).getText().replaceAll("[$,]", ""));
    }

    public Product(String name, Integer quantity, BigDecimal price) {
        this.name = String.valueOf(name).replaceAll("-", "");
        this.quantity = quantity;
        this.price = price;
        this.total = new BigDecimal(this.quantity).multiply(price);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("name:'" + name + "'")
                .add("quantity:" + quantity)
                .add("price:" + price)
                .add("total:" + total)
                .toString();
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void updateQuantity(int quant) {
        this.quantity += quant;
    }

    public void addToTotalPrice(BigDecimal value) {
        this.total = total.add(value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equal(getName(), product.getName()) &&
                Objects.equal(getQuantity(), product.getQuantity()) &&
                Objects.equal(getPrice(), product.getPrice()) &&
                Objects.equal(getTotal(), product.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getQuantity(), getPrice(), getTotal());
    }
}

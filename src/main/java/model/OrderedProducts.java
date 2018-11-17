package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class OrderedProducts {

    private static List<Product> products = new ArrayList<>();

    public static List<Product> getOrderedProducts() {
        products.sort(Comparator.comparing(Product::getName));
        return products;
    }

    public static void addProduct(Product product) {
        Optional<Product> isProductExist = products.stream().filter(t -> t.getName().equals(product.getName())).findAny();
        if (isProductExist.isPresent()) {
            Product tempProduct = isProductExist.get();
            tempProduct.updateQuantity(product.getQuantity());
            tempProduct.addToTotalPrice(product.getTotal());
            return;
        }
        products.add(product);
    }
}


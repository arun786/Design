package Shipment;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;

@Getter
@AllArgsConstructor
public class Product {
    public final static Comparator<Product> BY_WEIGHT = Comparator.comparing(Product::getWeight);

    private final String name;
    private final int weight;
}

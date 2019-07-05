package Shipment;

import java.util.List;

public interface IShipment {

    void add(Product product);

    void replaceProduct(Product oldProduct, Product newProduct);

    void prepareProduct();

    List<Product> getLightWeightProducts();

    List<Product> getHeavyWeightProducts();
}

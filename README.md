# Design


## Design a Shipment which 

    1. adds an order
    2. replaces an order
    3. prepares an order based on fixed weight as Light weight and heavy weight
    
### Classes required are

    1. Product
    2. Shipment which will have the below methods
        1. addProduct
        2. replaceProduct
        3. prepareProduct
    

### Details of the class    
    

Product class
    
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


Shipment Interface

    package Shipment;
    
    import java.util.List;
    
    public interface IShipment {
    
        void add(Product product);
    
        void replaceProduct(Product oldProduct, Product newProduct);
    
        void prepareProduct();
    
        List<Product> getLightWeightProducts();
    
        List<Product> getHeavyWeightProducts();
    }


Shipment class

    package Shipment;
    
    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.List;
    
    import static Shipment.Product.BY_WEIGHT;
    
    public class Shipment implements IShipment, Iterable<Product> {
    
        private static final int PRODUCT_NOT_FOUND = -1;
        private static final int LIGHT_VAN_WEIGHT_LIMIT = 20;
    
        private List<Product> products = new ArrayList<>();
        private List<Product> heavyWeightProducts = new ArrayList<>();
        private List<Product> lightWeightProducts = new ArrayList<>();
    
        @Override
        public void add(Product product) {
            products.add(product);
        }
    
        @Override
        public void replaceProduct(Product oldProduct, Product newProduct) {
            int index = products.indexOf(oldProduct);
            if (index != PRODUCT_NOT_FOUND) {
                products.set(index, newProduct);
            }
        }
    
        @Override
        public void prepareProduct() {
    
            products.sort(BY_WEIGHT);
            int index = split();
            lightWeightProducts = products.subList(0, index);
            heavyWeightProducts = products.subList(index, products.size());
        }
    
        private int split() {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getWeight() > LIGHT_VAN_WEIGHT_LIMIT) {
                    return i;
                }
            }
            return 0;
        }
    
        @Override
        public List<Product> getLightWeightProducts() {
            return lightWeightProducts;
        }
    
        @Override
        public List<Product> getHeavyWeightProducts() {
            return heavyWeightProducts;
        }
    
        @Override
        public Iterator<Product> iterator() {
            return products.iterator();
        }
    
    
    }

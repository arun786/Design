package shipment;

import Shipment.Shipment;
import org.junit.Before;
import org.junit.Test;

import static Shipment.ProductFactory.door;
import static Shipment.ProductFactory.panel;
import static Shipment.ProductFactory.window;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;


public class ShipmentTest {

    private Shipment shipment;

    @Before
    public void setUp() {
        shipment = new Shipment();
    }

    @Test
    public void addProducts() {
        shipment.add(door);
        shipment.add(window);
        shipment.add(panel);

        assertThat(shipment, contains(door, window, panel));
    }


    @Test
    public void replaceProducts() {
        shipment.add(door);
        shipment.replaceProduct(door, window);

        assertThat(shipment, contains(window));
    }


    @Test
    public void prepareProducts() {

        shipment.add(door);
        shipment.add(window);
        shipment.add(panel);

        shipment.prepareProduct();

        assertThat(shipment.getHeavyWeightProducts(), contains(door, panel));
        assertThat(shipment.getLightWeightProducts(), contains(window));


    }
}

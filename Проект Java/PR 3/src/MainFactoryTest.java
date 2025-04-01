package com.primer.serialization;

import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

public class MainFactoryDemoTest {

    @Test
    public void testCalculation() {
        CalculationData data = new CalculationData(3.0, 4.0);
        Calculator calc = new Calculator(data);
        calc.computeSum();
        Assert.assertEquals(7.0, data.getResult(), 1e-9);

        data.setNumber(15);
        calc.computeRepresentations();
        Assert.assertEquals("1111", data.getBinaryRepresentation());
        Assert.assertEquals("17", data.getOctalRepresentation());
        Assert.assertEquals("f", data.getHexRepresentation());
    }

    @Test
    public void testManagerSerialization() {
        CalculationManager manager = new CalculationManager();

        CalculationData d1 = new CalculationData(2, 5);
        d1.setNumber(8);
        Calculator c1 = new Calculator(d1);
        c1.computeSum();
        c1.computeRepresentations();
        manager.addItem(c1.getData());

        CalculationData d2 = new CalculationData(10, 10);
        d2.setNumber(42);
        Calculator c2 = new Calculator(d2);
        c2.computeSum();
        c2.computeRepresentations();
        manager.addItem(c2.getData());

        String fileName = "test_collection.ser";
        try {
            manager.saveToFile(fileName);
        } catch (IOException e) {
            Assert.fail("Помилка збереження: " + e.getMessage());
        }

        CalculationManager restored = new CalculationManager();
        try {
            restored.loadFromFile(fileName);
        } catch (Exception e) {
            Assert.fail("Помилка відновлення: " + e.getMessage());
        }

        List<CalculationData> originalList = manager.getItems();
        List<CalculationData> restoredList = restored.getItems();

        Assert.assertEquals(originalList.size(), restoredList.size());

        for (int i = 0; i < originalList.size(); i++) {
            CalculationData orig = originalList.get(i);
            CalculationData rest = restoredList.get(i);

            Assert.assertEquals(orig.getA(), rest.getA(), 1e-9);
            Assert.assertEquals(orig.getB(), rest.getB(), 1e-9);
            Assert.assertEquals(orig.getResult(), rest.getResult(), 1e-9);
            Assert.assertEquals(orig.getBinaryRepresentation(), rest.getBinaryRepresentation());
            Assert.assertEquals(orig.getOctalRepresentation(), rest.getOctalRepresentation());
            Assert.assertEquals(orig.getHexRepresentation(), rest.getHexRepresentation());

        }
    }

    @Test
    public void testFactoryMethod() {
        ResultDisplayFactory factory = new TextResultDisplayFactory();
        ResultDisplay display = factory.createDisplay();
        Assert.assertNotNull("Фабрика має створити об'єкт ResultDisplay", display);
    }
}

package com.primer.serialization;

import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.List;

public class MainFactoryTest {

    @Test
    public void testCalculation() {
        CalculationData data = new CalculationData(3.0, 4.0);
        Calculator calc = new Calculator(data);
        calc.computeSum();
        Assert.assertEquals(7.0, data.getResult(), 1e-9);

        data.setNumber(15);
        calc.computeRepresentations();
        Assert.assertEquals("1111", data.getBinaryRepresentation());
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

        List<CalculationData> listOrig = manager.getItems();
        List<CalculationData> listRest = restored.getItems();
        Assert.assertEquals(listOrig.size(), listRest.size());
        for (int i = 0; i < listOrig.size(); i++) {
            CalculationData orig = listOrig.get(i);
            CalculationData rest = listRest.get(i);
            Assert.assertEquals(orig.getA(), rest.getA(), 1e-9);
            Assert.assertEquals(orig.getB(), rest.getB(), 1e-9);
            Assert.assertEquals(orig.getNumber(), rest.getNumber());
            Assert.assertEquals(orig.getResult(), rest.getResult(), 1e-9);
        }
    }

    @Test
    public void testFactoryMethod() {
        ResultDisplayFactory factory = new TableResultDisplayFactory();
        ResultDisplay display = factory.createDisplay();
        Assert.assertTrue("display повинен бути TableResultDisplay",
                display instanceof TableResultDisplay);
    }
}

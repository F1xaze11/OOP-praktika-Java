package com.example.serialization;
import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
public class CalculationTest {
    @Test
    public void testSum() {
        CalculationData data = new CalculationData(3.0, 4.0);
        Calculator calculator = new Calculator(data);
        calculator.computeSum();
        Assert.assertEquals(7.0, data.getResult(), 0.0001);
    }
    @Test
    public void testRepresentations() {
        CalculationData data = new CalculationData(0, 0);
        data.setNumber(15);
        Calculator calculator = new Calculator(data);
        calculator.computeRepresentations();
        Assert.assertEquals("1111", data.getBinaryRepresentation());
        Assert.assertEquals("17", data.getOctalRepresentation());
        Assert.assertEquals("f", data.getHexRepresentation());
    }
    @Test
    public void testSerialization() {
        CalculationData original = new CalculationData(2.5, 5.5);
        original.setNumber(42);
        original.setResult(8.0);
        original.setNote("Тест");
        byte[] bytes = serialize(original);
        CalculationData restored = deserialize(bytes);
        Assert.assertEquals(original.getA(), restored.getA(), 0.0001);
        Assert.assertEquals(original.getB(), restored.getB(), 0.0001);
        Assert.assertEquals(original.getNumber(), restored.getNumber());
        Assert.assertEquals(original.getResult(), restored.getResult(), 0.0001);
        Assert.assertNull(restored.getNote());
    }
    private byte[] serialize(CalculationData data) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(data);
            return baos.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }
    private CalculationData deserialize(byte[] bytes) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (CalculationData) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
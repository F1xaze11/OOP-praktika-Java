package com.example.serialization;
public class Calculator {
    private CalculationData data;
    public Calculator(CalculationData data) {
        this.data = data;
    }
    public void computeSum() {
        data.setResult(data.getA() + data.getB());
    }
    public void computeRepresentations() {
        int n = data.getNumber();
        data.setBinaryRepresentation(Integer.toBinaryString(n));
        data.setOctalRepresentation(Integer.toOctalString(n));
        data.setHexRepresentation(Integer.toHexString(n));
    }
    public CalculationData getData() {
        return data;
    }
    public void setData(CalculationData data) {
        this.data = data;
    }
}
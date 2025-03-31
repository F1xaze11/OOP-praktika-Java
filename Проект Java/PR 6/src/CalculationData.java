package com.primer.workerthread;

import java.io.Serializable;

public class CalculationData implements Serializable {
    private static final long serialVersionUID = 1L;
    private double a;
    private double b;
    private double result;
    private int number;
    private transient String note;

    public CalculationData() {
    }

    public CalculationData(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "CalculationData{" +
                "a=" + a +
                ", b=" + b +
                ", result=" + result +
                ", number=" + number +
                ", note='" + note + '\'' +
                '}';
    }
}
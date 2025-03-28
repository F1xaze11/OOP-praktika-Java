package com.primer.commandpattern;

import java.util.ArrayList;
import java.util.List;

public class ScaleCommand implements Command {
    private double scaleFactor;
    private List<Double> previousA = new ArrayList<>();
    private List<Double> previousB = new ArrayList<>();

    public ScaleCommand(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    @Override
    public void execute() {
        CalculationManager manager = CalculationManager.getInstance();
        // Зберігаємо попередні значення для undo
        for (CalculationData data : manager.getItems()) {
            previousA.add(data.getA());
            previousB.add(data.getB());
        }
        // Застосовуємо масштабування
        for (CalculationData data : manager.getItems()) {
            data.setA(data.getA() * scaleFactor);
            data.setB(data.getB() * scaleFactor);
        }
        System.out.println("Виконано масштабування з factor = " + scaleFactor);
    }

    @Override
    public void undo() {
        CalculationManager manager = CalculationManager.getInstance();
        List<CalculationData> items = manager.getItems();
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setA(previousA.get(i));
            items.get(i).setB(previousB.get(i));
        }
        System.out.println("Скасовано масштабування");
    }
}
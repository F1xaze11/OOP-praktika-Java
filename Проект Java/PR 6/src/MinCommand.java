package com.primer.workerthread;

import java.util.List;

public class MinCommand implements Command {
    private Double foundMin;  // поле для зберігання знайденого мінімуму

    @Override
    public void execute() {
        CalculationManager manager = CalculationManager.getInstance();
        List<CalculationData> list = manager.getItems();
        if (list.isEmpty()) {
            System.out.println("Колекція порожня. Мінімум не визначено.");
            foundMin = null;
            return;
        }
        double min = Double.MAX_VALUE;
        for (CalculationData d : list) {
            if (d.getA() < min) {
                min = d.getA();
            }
        }
        foundMin = min;
        System.out.println("Мінімальне значення a = " + min);
    }

    public Double getFoundMin() {
        return foundMin;
    }
}
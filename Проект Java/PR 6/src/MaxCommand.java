package com.primer.workerthread;

import java.util.List;

public class MaxCommand implements Command {
    @Override
    public void execute() {
        CalculationManager manager = CalculationManager.getInstance();
        List<CalculationData> list = manager.getItems();
        if (list.isEmpty()) {
            System.out.println("Колекція порожня. Максимум не визначено.");
            return;
        }
        double max = -Double.MAX_VALUE;
        for (CalculationData d : list) {
            if (d.getA() > max) {
                max = d.getA();
            }
        }
        System.out.println("Максимальне значення a = " + max);
    }
}
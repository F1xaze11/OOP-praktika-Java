package com.primer.workerthread;

import java.util.List;

public class AverageCommand implements Command {
    @Override
    public void execute() {
        CalculationManager manager = CalculationManager.getInstance();
        List<CalculationData> list = manager.getItems();
        if (list.isEmpty()) {
            System.out.println("Колекція порожня. Середнє не визначено.");
            return;
        }
        double sum = 0.0;
        for (CalculationData d : list) {
            sum += d.getA();
        }
        double avg = sum / list.size();
        System.out.println("Середнє значення a = " + avg);
    }
}
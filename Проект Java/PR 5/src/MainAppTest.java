package com.primer.commandpattern;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class MainAppTest {

    @Test
    public void testScaleCommand() {
        CalculationManager manager = CalculationManager.getInstance();
        manager.getItems().clear();

        CalculationData d1 = new CalculationData(1.0, 1.0);
        CalculationData d2 = new CalculationData(2.0, 2.0);
        manager.addItem(d1);
        manager.addItem(d2);

        ScaleCommand cmd = new ScaleCommand(2.0);
        cmd.execute();
        Assert.assertEquals(2.0, d1.getA(), 1e-9);
        Assert.assertEquals(2.0, d1.getB(), 1e-9);
        Assert.assertEquals(4.0, d2.getA(), 1e-9);
        Assert.assertEquals(4.0, d2.getB(), 1e-9);

        cmd.undo();
        Assert.assertEquals(1.0, d1.getA(), 1e-9);
        Assert.assertEquals(1.0, d1.getB(), 1e-9);
        Assert.assertEquals(2.0, d2.getA(), 1e-9);
        Assert.assertEquals(2.0, d2.getB(), 1e-9);
    }

    @Test
    public void testSortCommand() {
        CalculationManager manager = CalculationManager.getInstance();
        manager.getItems().clear();

        manager.addItem(new CalculationData(3.0, 1.0));
        manager.addItem(new CalculationData(1.0, 3.0));
        manager.addItem(new CalculationData(2.0, 2.0));

        SortCommand cmd = new SortCommand();
        cmd.execute();

        List<CalculationData> items = manager.getItems();
        Assert.assertEquals(1.0, items.get(0).getA(), 1e-9);
        Assert.assertEquals(2.0, items.get(1).getA(), 1e-9);
        Assert.assertEquals(3.0, items.get(2).getA(), 1e-9);

        cmd.undo();
        Assert.assertEquals(3.0, items.get(0).getA(), 1e-9);
        Assert.assertEquals(1.0, items.get(1).getA(), 1e-9);
        Assert.assertEquals(2.0, items.get(2).getA(), 1e-9);
    }

    @Test
    public void testMacroCommand() {
        CalculationManager manager = CalculationManager.getInstance();
        manager.getItems().clear();

        manager.addItem(new CalculationData(1.0, 3.0));
        manager.addItem(new CalculationData(2.0, 2.0));

        MacroCommand macro = new MacroCommand();
        macro.addCommand(new ScaleCommand(1.1));
        macro.addCommand(new SortCommand());
        macro.execute();

        List<CalculationData> items = manager.getItems();
        Assert.assertEquals(1.1, items.get(0).getA(), 1e-9);
        Assert.assertEquals(2.2, items.get(1).getA(), 1e-9);

        macro.undo();
        Assert.assertEquals(1.0, items.get(0).getA(), 1e-9);
        Assert.assertEquals(3.0, items.get(0).getB(), 1e-9);
        Assert.assertEquals(2.0, items.get(1).getA(), 1e-9);
        Assert.assertEquals(2.0, items.get(1).getB(), 1e-9);
    }
}
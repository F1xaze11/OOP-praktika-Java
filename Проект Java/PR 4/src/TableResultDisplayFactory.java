package com.primer.serialization;

public class TableResultDisplayFactory implements ResultDisplayFactory {
    @Override
    public ResultDisplay createDisplay() {
        return new TableResultDisplay();
    }
}
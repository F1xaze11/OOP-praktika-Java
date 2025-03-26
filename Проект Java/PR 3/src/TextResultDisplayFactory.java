package com.primer.serialization;

public class TextResultDisplayFactory implements ResultDisplayFactory {
    @Override
    public ResultDisplay createDisplay() {
        return new TextResultDisplay();
    }
}
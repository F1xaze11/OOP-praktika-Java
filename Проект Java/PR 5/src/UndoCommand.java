package com.primer.commandpattern;

import java.util.Stack;

public class UndoCommand implements Command {
    private static Stack<Command> history = new Stack<>();

    public static void registerExecutedCommand(Command cmd) {
        history.push(cmd);
    }

    @Override
    public void execute() {
        if (!history.isEmpty()) {
            Command last = history.pop();
            last.undo();
        } else {
            System.out.println("Нема команд для скасування");
        }
    }

    @Override
    public void undo() {
        // Для UndoCommand не потрібно спеціального undo
    }
}
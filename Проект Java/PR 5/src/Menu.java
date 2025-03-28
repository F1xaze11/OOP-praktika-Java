package com.primer.commandpattern;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, Command> commands = new HashMap<>();

    public void addCommand(String key, Command command) {
        commands.put(key, command);
    }

    public void executeCommand(String key) {
        Command cmd = commands.get(key);
        if (cmd != null) {
            cmd.execute();
            if (!(cmd instanceof UndoCommand)) {
                UndoCommand.registerExecutedCommand(cmd);
            }
        } else {
            System.out.println("Невідома команда: " + key);
        }
    }
}
package maciej.labuz;

import maciej.labuz.commands.Command;

import java.util.HashMap;
import java.util.Optional;

public class CommandCollection {
    private final HashMap<String, Command> commands;

    {
        commands = new HashMap<>();
    }

    void addCommand(Command command) {
        commands.put(command.getName(), command);
    }

    Optional<Command> getCommandByName(String name) {
        Command command = commands.get(name);
        return Optional.ofNullable(command);
    }

    boolean commandExist(String name){
        return false;
    }
}

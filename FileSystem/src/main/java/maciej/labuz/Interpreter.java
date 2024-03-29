package maciej.labuz;

import maciej.labuz.commands.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Interpreter {

    private final CommandCollection commands;

    public Interpreter(CommandCollection commands) {
        this.commands = commands;
    }

    public Command resolveCommand(String input) {
        String[] split = input.split(" ");

        String commandName;

        commandName = split[0];

        List<String> parameters = new ArrayList<>();

        if (split.length > 1){
            parameters.add(split[1]);
        }

        if (split.length > 2){
            parameters.add(split[2]);
        }

        Optional<Command> commandOptional = commands.getCommandByName(commandName);

        Command command = commandOptional.orElseThrow(() -> new RuntimeException("Unexpected command!"));
        command.setParameters(parameters);
        return command;

    }

}

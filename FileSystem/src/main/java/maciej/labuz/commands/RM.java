package maciej.labuz.commands;

import maciej.labuz.filesystem.BaseComponent;
import maciej.labuz.filesystem.Context;
import maciej.labuz.filesystem.Directory;

import java.util.List;

public class RM implements Command {
    private List<String> parameters;

    @Override
    public String getName() {
        return "rm";
    }

    @Override
    public void setParameters(List<String> list) {
        this.parameters = list;
    }

    @Override
    public void execute(Context context) {
        if (parameters.size() < 1) {
            throw new RuntimeException("Wrong number of parameters, at least 1 required");
        }

        String componentName = parameters.get(0);

        // Get the current directory from the context
        Directory currentDirectory = context.getCurrent();

        // Find the component to remove in the current directory
        BaseComponent toRemove = findComponentByName(currentDirectory, componentName);

        // Check if the component exists in the current directory
        if (toRemove == null) {
            throw new RuntimeException("Component not found in the current directory");
        }

        // Remove the component from the current directory
        currentDirectory.removeChild(toRemove);
    }

    private BaseComponent findComponentByName(Directory directory, String componentName) {
        for (BaseComponent component : directory.getChildren()) {
            if (component.getName().equals(componentName)) {
                return component;
            }
        }
        return null;
    }
}

package maciej.labuz.commands;


import maciej.labuz.filesystem.BaseComponent;
import maciej.labuz.filesystem.Context;
import maciej.labuz.filesystem.Directory;

import java.util.List;

public class TOUCH implements Command {
    private List<String> parameters;

    @Override
    public String getName() {
        return "touch";
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

        String fileName = parameters.get(0);

        // Get the current directory from the context
        Directory currentDirectory = context.getCurrent();

        // Check if a component with the same name already exists
        if (currentDirectory.hasChildWithName(fileName)) {
            throw new RuntimeException("Component with this name already exists");
        }

        // Create a new file (assuming BaseComponent has setName method)
        BaseComponent newFile = new BaseComponent();
        newFile.setName(fileName);
        newFile.setParent(currentDirectory);

        // Add the new file to the current directory
        currentDirectory.getChildren().add(newFile);
    }
}


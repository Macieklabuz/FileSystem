package maciej.labuz.commands;

import maciej.labuz.PathResolver;
import maciej.labuz.filesystem.BaseComponent;
import maciej.labuz.filesystem.Context;
import maciej.labuz.filesystem.Directory;

import java.util.List;

public class ChangeDirectory implements Command {

    private List<String> commandParameters;

@Override
    public void execute(Context executionContext) {
        String path = commandParameters.get(0);
        Directory currentDirectory = executionContext.getCurrent();

        if (path.equals("..")) {
            if (currentDirectory.getParent() == null) {
                throw new RuntimeException("Current directory is root and doesn't have a parent");
            }
            executionContext.setCurrent(currentDirectory.getParent());
        } else if (path.equals(".")) {
            executionContext.setCurrent(currentDirectory);
        } else {
            BaseComponent destinationComponent = PathResolver.resolvePath(path, executionContext);

            if (destinationComponent instanceof Directory destinationDirectory) {
                executionContext.setCurrent(destinationDirectory);
            } else {
                throw new RuntimeException("Destination is not a directory");
            }
        }
    }

    @Override
    public void setParameters(List<String> parametersList) {
        this.commandParameters = parametersList;
    }




    @Override
    public String getName() {
        return "cd";
    }

}

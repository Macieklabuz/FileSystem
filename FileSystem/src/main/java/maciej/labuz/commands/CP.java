package maciej.labuz.commands;

import maciej.labuz.PathResolver;
import maciej.labuz.filesystem.BaseComponent;
import maciej.labuz.filesystem.Context;
import maciej.labuz.filesystem.Directory;

import java.util.List;

public class CP implements Command {
    private List<String> parameters;

    @Override
    public String getName() {
        return "cp";
    }

    @Override
    public void setParameters(List<String> list) {
        this.parameters = list;
    }

    @Override
    public void execute(Context context) {

        if (parameters.size() < 2) {
            throw new RuntimeException("Wrong number of parameters required 2");
        }

        String firstParam = parameters.get(0);
        String secondParam = parameters.get(1);

        BaseComponent toCopy = PathResolver.resolvePath(firstParam, context);
        BaseComponent destination = PathResolver.resolvePath(secondParam, context);

        BaseComponent copied = toCopy.clone();


        if (destination instanceof Directory directory){
            if (directory.hasChildWithName(copied.getName())){
                throw new RuntimeException("Component with this name already exists");
            }
            copied.setParent(directory);
            directory.getChildren().add(copied);
        }
    }
}

package maciej.labuz.commands;

import maciej.labuz.PathResolver;
import maciej.labuz.filesystem.BaseComponent;
import maciej.labuz.filesystem.Context;
import maciej.labuz.filesystem.Directory;


import java.util.List;

public class MV implements Command {

    private List<String> parameters;

    @Override
    public String getName() {
        return "mv";
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

        BaseComponent toMove = PathResolver.resolvePath(firstParam, context);

        boolean changeName = false;
        BaseComponent destination;
        String newName = "";

        try {
            destination = PathResolver.resolvePath(secondParam, context);
        } catch (RuntimeException e) {
            String newPath = secondParam.substring(0, secondParam.lastIndexOf("/"));
            newName = secondParam.substring(secondParam.lastIndexOf("/") + 1);
            destination = PathResolver.resolvePath(newPath, context);
            changeName = true;
        }
        if (destination instanceof Directory directory) {
            if (changeName) {
                toMove.setName(newName);
            }
            if (directory.hasChildWithName(toMove.getName())){
                throw new RuntimeException("Component with this name already exists");
            }
            Directory oldParent = toMove.getParent();
            oldParent.removeChild(toMove);
            directory.addChild(toMove);
            toMove.setParent(directory);

        }
    }
}

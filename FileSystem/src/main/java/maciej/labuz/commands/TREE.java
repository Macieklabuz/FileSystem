package maciej.labuz.commands;

import maciej.labuz.filesystem.Context;
import maciej.labuz.filesystem.Directory;

import java.util.List;

public class TREE implements Command {

    private List<String> commandParameters;
    @Override
    public String getName() {
        return "tree";
    }

    @Override
    public void setParameters(List<String> list) {
        this.commandParameters = list;
    }

    @Override
    public void execute(Context context) {
        Directory current = context.getCurrent();
        current.print(1);
    }
}

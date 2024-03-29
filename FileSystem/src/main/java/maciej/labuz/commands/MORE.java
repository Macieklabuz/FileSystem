package maciej.labuz.commands;

import maciej.labuz.filesystem.BaseComponent;
import maciej.labuz.filesystem.Context;
import maciej.labuz.filesystem.FileSystemComponent;
import maciej.labuz.filesystem.TextFile;

import java.util.List;
import java.util.Set;

public class MORE implements Command {

    private List<String> parameters;
    @Override
    public String getName() {
        return "more";
    }

    @Override
    public void setParameters(List<String> list) {
        this.parameters = list;
    }

    @Override
    public void execute(Context context) {
        String fileName = parameters.get(0);

        Set<BaseComponent> children = context.getCurrent().getChildren();

        for (FileSystemComponent child : children) {
            if (child instanceof TextFile && ((TextFile) child).getName().equals(fileName)){
                System.out.println(((TextFile) child).getContent());
            }

        }

    }
}

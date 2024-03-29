package maciej.labuz.commands;

import maciej.labuz.filesystem.Context;

import java.util.List;

public interface Command {
    String getName();
    void setParameters(List<String> list);
    void execute(Context context);
}

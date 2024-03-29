package maciej.labuz;

import maciej.labuz.filesystem.BaseComponent;
import maciej.labuz.filesystem.Context;
import maciej.labuz.filesystem.Directory;
import maciej.labuz.filesystem.TextFile;

public abstract class PathResolver {

    public static BaseComponent resolvePath(String path, Context context) {
        Directory current;

        if (path.equals(".")) {
            return context.getCurrent();
        }

        if (path.charAt(0) == '/') {
            current = context.getCurrent();
            while (current.getParent() != null) {
                current = current.getParent();
            }
            if (path.length() == 1) {
                return current;
            }
        } else if (path.startsWith("./")) {
            current = context.getCurrent();
        } else {
            current = processRelativePath(path, context);
        }

        return current;
    }

    private static Directory processRelativePath(String path, Context context) {
        Directory current = context.getCurrent();
        String[] split = path.split("/");

        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("..")) {
                current = getParentDirectory(current);
            } else {
                current = processDirectory(current, split[i]);
            }
        }

        return current;
    }

    private static Directory getParentDirectory(Directory current) {
        if (current.getParent() == null) {
            throw new RuntimeException("Wrong path");
        }
        return current.getParent();
    }

    private static Directory processDirectory(Directory current, String name) {
        if (current.hasChildWithName(name)) {
            BaseComponent next = current.getChildByName(name);

            if (next instanceof Directory) {
                current = (Directory) next;
            } else if (next instanceof TextFile) {
                return next;
            } else {
                throw new RuntimeException("Wrong Path");
            }
        } else {
            throw new RuntimeException("Wrong path!");
        }
        return current;
    }
}

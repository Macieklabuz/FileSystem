package maciej.labuz.filesystem;

public interface FileSystemComponent extends Cloneable {
    void setName(String name);
    String getName();

    FileSystemComponent getParent();
    void setParent(Directory param);

    String getPath();

    void print(int indent);
}
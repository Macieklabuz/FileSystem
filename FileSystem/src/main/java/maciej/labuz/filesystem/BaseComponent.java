package maciej.labuz.filesystem;

import java.util.Objects;
import java.util.Set;

public class BaseComponent implements FileSystemComponent, Cloneable{
    protected String name;
    protected Directory parent;

    public String getPath() {
        if (this.parent == null) return this.name;
        else return this.parent.getPath() + "/" + this.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseComponent that = (BaseComponent) o;
        return Objects.equals(name, that.name) && Objects.equals(parent, that.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }

    @Override
    public void print(int indent) {
        if (this instanceof Directory) {
            Set<BaseComponent> children = ((Directory) this).getChildren();
            System.out.println(this.getName() + "/");
            for (FileSystemComponent child : children) {
                for (int i=0; i<indent;i++) {
                    System.out.print("**");
                }
                child.print(indent+1);
            }
        } else  {
            System.out.println(this.getName());
        }

    }

    @Override
    public BaseComponent clone() {
        try {
            BaseComponent clone = (BaseComponent) super.clone();
            clone.name = this.name;
            clone.parent = null;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

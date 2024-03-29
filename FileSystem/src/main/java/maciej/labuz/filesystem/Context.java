package maciej.labuz.filesystem;

public class Context {
    private Directory current;

    public Directory getCurrent() {
        return current;
    }

    public void setCurrent(Directory current) {
        this.current = current;
    }
}

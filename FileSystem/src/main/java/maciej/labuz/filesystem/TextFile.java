package maciej.labuz.filesystem;



public class TextFile extends BaseComponent implements Cloneable {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public TextFile clone() {
        TextFile clone = (TextFile) super.clone();
        clone.setContent(this.content);
        return clone;
    }
}

package wecan.com.todo;

/**
 * Created by owner on 5/30/2016.
 */

enum TagEnum {
    BLACK(R.color.black, R.string.colorBlack),
    BLUE(R.color.blue, R.string.colorBlue),
    GREEN(R.color.green, R.string.colorGreen),
    RED(R.color.colorAccent, R.string.colorRed),
    YELLOW(R.color.yellow, R.string.colorYellow);

    private final int tagColor;
    private final int tagName;

    TagEnum(int tagColor, int tagName){
        this.tagColor = tagColor;
        this.tagName = tagName;
    }

    public int getTagColor() {
        return tagColor;
    }

    public int getTagName() {
        return tagName;
    }
}

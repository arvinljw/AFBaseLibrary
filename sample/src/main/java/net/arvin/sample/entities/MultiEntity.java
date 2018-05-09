package net.arvin.sample.entities;

/**
 * Created by arvinljw on 17/7/30 14:48
 * Function：
 * Desc：
 */
public class MultiEntity {
    public int type;
    public String content;

    public MultiEntity(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public int getItemType() {
        return type;
    }
}

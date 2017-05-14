package net.arvin.sample.entities;


import net.arvin.afbaselibrary.uis.helpers.IBaseTabPageContact;

/**
 * Created by arvin on 17/5/14 00:25
 * Function：
 * Desc：
 */
public class TabEntity implements IBaseTabPageContact.IPageTitle {
    private String title;
    private String type;

    public TabEntity(String title) {
        this.title = title;
        this.type = title;
    }

    public TabEntity(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

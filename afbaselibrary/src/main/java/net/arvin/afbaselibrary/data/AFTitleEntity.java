package net.arvin.afbaselibrary.data;


import net.arvin.afbaselibrary.uis.adapters.PagerFragmentAdapter;

/**
 * Created by arvinljw on 17/5/12 14:42
 * Function：
 * Desc：
 */
public class AFTitleEntity implements PagerFragmentAdapter.IPageTitle {
    private String title;

    public AFTitleEntity() {
    }

    public AFTitleEntity(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return this.title;
    }
}

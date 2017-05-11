package net.arvin.afbaselibrary.uis.helpers;

import android.view.View;

/**
 * Created by arvinljw on 17/5/11 20:41
 * Function：
 * Desc：
 */
public class NeedInitViewHelper<T extends IBaseContact.IBase> implements IBaseContact.IPresenter {
    protected View mRoot;
    protected T mBaseView;


    public NeedInitViewHelper(T mBaseView) {
        this(mBaseView, null);
    }

    public NeedInitViewHelper(T mBaseView, View mRoot) {
        this.mBaseView = mBaseView;
        this.mRoot = mRoot;
    }

    @Override
    public void destroyIt() {
        mRoot = null;
        mBaseView = null;
    }
}

package net.arvin.sample.uis.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import net.arvin.afbaselibrary.uis.fragments.BaseFragment;
import net.arvin.sample.R;

import butterknife.BindView;

/**
 * Created by arvinljw on 17/5/12 15:20
 * Function：
 * Desc：
 */
public class JustTextFragment extends BaseFragment {
    @BindView(R.id.tv_content)
    TextView tvContent;

    private String content;

    public JustTextFragment() {
    }

    @SuppressLint("ValidFragment")
    public JustTextFragment(String content) {
        this.content = content;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_just_text;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        tvContent.setText(content);
    }

}

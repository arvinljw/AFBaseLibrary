package net.arvin.sample.uis.adapters;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import net.arvin.afbaselibrary.utils.AFSizeUtil;
import net.arvin.afbaselibrary.utils.AFGlideUtil;
import net.arvin.sample.R;
import net.arvin.sample.entities.CategoryEntity;

import java.util.List;

/**
 * Created by arvinljw on 17/5/15 00:10
 * Function：
 * Desc：
 */
public class CategoryListAdapter extends BaseQuickAdapter<CategoryEntity, BaseViewHolder> {

    public CategoryListAdapter(List<CategoryEntity> datas) {
        super(R.layout.item_category, datas);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryEntity item) {
        boolean isNoImg = true;
        if (item.getType().equals("福利")) {
            if (TextUtils.isEmpty(item.getUrl())) {
                isNoImg = false;
            }
        } else {
            if (item.getImages() == null || item.getImages().size() == 0) {
                isNoImg = false;
            }
        }
        if (isNoImg) {
            convertNoImage(helper, item);
            return;
        }
        convertItem(helper, item);
    }

    private void convertNoImage(BaseViewHolder helper, CategoryEntity item) {
        convertItem(helper, item);
        helper.getView(R.id.iv_item_CategoryAdapter).setVisibility(View.GONE);
    }

    private void convertItem(BaseViewHolder helper, CategoryEntity item) {
        String url = "";
        if (item.getType().equals("福利")) {
            url = item.getUrl();
        } else {
            if (!TextUtils.isEmpty(item.getShowImage())) {
                url = item.getShowImage();
            }
        }
        ImageView ivItem = helper.getView(R.id.iv_item_CategoryAdapter);
        ivItem.setVisibility(View.VISIBLE);
        AFGlideUtil.loadImage(getRealSizeUrl(url), ivItem);

        helper.setText(R.id.tv_author_CategoryAdapter, "作者：" + item.getWho());
        helper.setText(R.id.tv_desc_CategoryAdapter, item.getDesc());
        helper.setText(R.id.tv_source_CategoryAdapter, "来源：" + item.getSource());
        helper.setText(R.id.tv_publish_time_CategoryAdapter, item.getPublishedAt());
    }

    private String getRealSizeUrl(String url) {
        return url + "?imageView2/0/w/" + AFSizeUtil.dp2px(64);
    }
}

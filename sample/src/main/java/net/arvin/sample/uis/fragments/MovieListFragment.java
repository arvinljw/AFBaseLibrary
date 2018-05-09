package net.arvin.sample.uis.fragments;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.arvin.afbaselibrary.nets.ApiCallback;
import net.arvin.afbaselibrary.uis.fragments.BaseRefreshLoadFragment;
import net.arvin.sample.R;
import net.arvin.sample.entities.MovieEntity;
import net.arvin.sample.uis.activities.HtmlActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by arvinljw on 18/3/4 11:37
 * Function：
 * Desc：
 */
public class MovieListFragment extends BaseRefreshLoadFragment<MovieEntity> {

    @Override
    public int getContentView() {
        return R.layout.template_layout_refresh_load;
    }


    @Override
    public void initViews(Bundle savedInstanceState) {
        autoRefresh();
    }

    @Override
    public String getTitleText() {
        return "";
    }


    @Override
    public BaseQuickAdapter<MovieEntity, BaseViewHolder> getAdapter() {
        return new MovieListAdapter(mItems);
    }

    @Override
    public void loadData(int page) {
        Observable.just(page).map(new Function<Integer, List<MovieEntity>>() {
            @Override
            public List<MovieEntity> apply(Integer integer) throws Exception {
                String movies = getJson(getAFContext(), "douban_movie.json");
                return new Gson().fromJson(movies, new TypeToken<List<MovieEntity>>() {
                }.getType());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new ApiCallback<List<MovieEntity>>(this) {
            @Override
            public void onNext(List<MovieEntity> movieEntities) {
                mItems.clear();
                mItems.addAll(movieEntities);
                refreshLoadComplete(isSuccess(movieEntities), false);
            }

            @Override
            public void onError(Throwable e) {
                refreshLoadComplete(false, false);
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putString(HtmlActivity.URL, mItems.get(position).getDetailUrl());
        bundle.putString(HtmlActivity.TITLE, mItems.get(position).getMovieName());
        startActivity(HtmlActivity.class, bundle);
    }

    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static class MovieListAdapter extends BaseQuickAdapter<MovieEntity, BaseViewHolder> {

        MovieListAdapter(@Nullable List<MovieEntity> data) {
            super(R.layout.item_movie, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MovieEntity item) {
            helper.setText(R.id.tv_name, item.getMovieName());
            helper.setText(R.id.tv_type, "类型：" + item.getStyle());
            helper.setText(R.id.tv_score, "评分：" + item.getScoreNumber());
            helper.setText(R.id.tv_quote, item.getQuote());
            helper.setText(R.id.tv_rank, String.valueOf(helper.getLayoutPosition() + 1));

            ImageView imgArticle = helper.getView(R.id.img_article);
//            AFGlideUtil.loadImage(item.getArticleUrl(), imgArticle);
        }
    }
}

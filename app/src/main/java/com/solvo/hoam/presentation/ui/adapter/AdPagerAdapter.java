package com.solvo.hoam.presentation.ui.adapter;

import android.content.Context;

import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.solvo.hoam.R;
import com.solvo.hoam.data.network.response.Image;
import com.solvo.hoam.presentation.ui.activity.ImageActivity;
import com.solvo.hoam.presentation.ui.helper.AdHelper;

import java.util.List;

public class AdPagerAdapter extends PagerAdapter {
    private static final String TAG = AdPagerAdapter.class.getSimpleName();

    private LayoutInflater layoutInflater;
    private List<Image> imageList;
    private Context context;

    public AdPagerAdapter(Context context, List<Image> imageList) {
        this.context = context;
        this.imageList = imageList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_view);
        imageView.setOnClickListener(v -> {
            context.startActivity(ImageActivity.buildIntent(context, imageList.get(position).getBig()));
        });

        Glide.with(context)
                .load(AdHelper.getImageUrl(imageList.get(position).getBig()))
                .placeholder(AdHelper.getSupportDrawable(R.drawable.ic_placeholder, context))
                .centerCrop()
                .into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

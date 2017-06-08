package com.solvo.hoam.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.solvo.hoam.R;
import com.solvo.hoam.activity.ImageActivity;
import com.solvo.hoam.helper.AdHelper;
import com.solvo.hoam.model.data.AdLab;
import com.solvo.hoam.model.response.Image;

import java.util.List;

public class AdPagerAdapter extends PagerAdapter {
    private static final String TAG = AdPagerAdapter.class.getSimpleName();
    private List<Image> mImageList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private FragmentManager mFragmentManager;
    private Fragment mFragment;
    private String mAdId;

    public AdPagerAdapter(Context context, String adId) {
        mContext = context;
        mAdId = adId;
        mImageList = AdLab.getInstance().getAd(adId).getImages();
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mFragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_view);
        imageView.setOnClickListener(v -> {
//            mFragment = ImageActivity.buildIntent(mImageList.get(position).getBig());
//            mFragmentManager.beginTransaction()
//                    .hide(mFragmentManager.findFragmentByTag(AdFragment.TAG))
//                    .add(R.id.fragment_container, mFragment, ImageActivity.TAG)
//                    .addToBackStack(ImageActivity.TAG)
//                    .commit();
            mContext.startActivity(ImageActivity.buildIntent(mContext, mImageList.get(position).getBig()));
        });

        Glide.with(mContext)
                .load(AdHelper.getImageUrl(mImageList.get(position).getBig()))
                .centerCrop()
                .crossFade()
                .into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

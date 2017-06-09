package com.solvo.hoam.presentation.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.solvo.hoam.R;
import com.solvo.hoam.presentation.ui.activity.AdActivity;
import com.solvo.hoam.presentation.ui.helper.AdHelper;
import com.solvo.hoam.data.db.CategoryLab;
import com.solvo.hoam.data.db.LocationLab;
import com.solvo.hoam.data.network.response.Ad;

import java.util.ArrayList;
import java.util.List;

public class AdListAdapter extends RecyclerView.Adapter<AdListAdapter.AdHolder> {
    private static final String TAG = AdListAdapter.class.getSimpleName();
    private List<Ad> adList;
    private Context context;

    public AdListAdapter(Context context) {
        adList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public AdHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(AdHolder holder, int position) {
        holder.bindAd(adList.get(position));
    }

    @Override
    public int getItemCount() {
        return adList.size();
    }

    public void setAdList(List<Ad> adList) {
        this.adList.clear();
        this.adList.addAll(adList);
    }

    public void updateAdList(List<Ad> adList) {
        this.adList.addAll(adList);
    }

    class AdHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView titleTextView;
        private TextView locationTextView;
        private TextView priceTextView;
        private TextView categoryTextView;
        private TextView createdDateTextView;

        private String mAdId;

        AdHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            imageView = (ImageView) itemView.findViewById(R.id.ad_image);
            titleTextView = (TextView) itemView.findViewById(R.id.tv_title);
            locationTextView = (TextView) itemView.findViewById(R.id.tv_location);
            priceTextView = (TextView) itemView.findViewById(R.id.tv_price);
            categoryTextView = (TextView) itemView.findViewById(R.id.tv_category);
            createdDateTextView = (TextView) itemView.findViewById(R.id.tv_created_date);
        }

        void bindAd(Ad ad) {
            mAdId = ad.getId();

            titleTextView.setText(ad.getTitle());

            locationTextView.setText(LocationLab.getInstance().getLocation(ad.getCityId()));
            locationTextView.setCompoundDrawables(AdHelper.getSupportDrawable(R.drawable.ic_location, context), null, null, null);

            categoryTextView.setText(CategoryLab.getInstance().getCategory(ad.getCategoryId()));
            categoryTextView.setCompoundDrawables(AdHelper.getSupportDrawable(R.drawable.ic_category, context), null, null, null);

            priceTextView.setText(AdHelper.getPrice(ad.getPrice()));

            createdDateTextView.setText(AdHelper.getAdCreatedDate(ad.getCreatedAt(), false));

            if (ad.getImages().isEmpty()) {
                imageView.setImageDrawable(AdHelper.getSupportDrawable(R.drawable.ic_placeholder, context));
            } else {
                Glide.with(context)
                        .load(AdHelper.getImageUrl(ad.getImages().get(0).getSmall()))
                        .placeholder(AdHelper.getSupportDrawable(R.drawable.ic_placeholder, context))
                        .centerCrop()
                        .crossFade()
                        .into(imageView);
            }
        }

        @Override
        public void onClick(View v) {
            context.startActivity(AdActivity.buildIntent(context, mAdId));
        }
    }
}
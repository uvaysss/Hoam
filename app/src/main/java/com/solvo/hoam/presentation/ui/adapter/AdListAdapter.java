package com.solvo.hoam.presentation.ui.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.solvo.hoam.R;
import com.solvo.hoam.domain.model.AdEntity;
import com.solvo.hoam.presentation.ui.activity.AdActivity;
import com.solvo.hoam.presentation.ui.helper.AdHelper;

import java.util.ArrayList;
import java.util.List;

import kotlin.Function;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public class AdListAdapter extends RecyclerView.Adapter<AdListAdapter.AdHolder> {
    private static final String TAG = AdListAdapter.class.getSimpleName();
    private List<AdEntity> adList;
    private Context context;
    private Function1<String, Unit> onClick;

    public AdListAdapter(List<AdEntity> adList, Context context) {
        this.adList = adList;
        this.context = context;
    }

    public AdListAdapter(Context context, Function1<String, Unit> onClick) {
        this.context = context;
        adList = new ArrayList<>();
        this.onClick = onClick;
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


    public void setAdList(List<AdEntity> adList) {
        this.adList.clear();
        this.adList.addAll(adList);
    }

    public void updateAdList(List<AdEntity> adList) {
        this.adList.addAll(adList);
    }

    class AdHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView titleTextView;
        private TextView locationTextView;
        private TextView priceTextView;
        private TextView categoryTextView;
        private TextView createdDateTextView;

        private String adId;

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

        void bindAd(AdEntity ad) {
            adId = ad.getId();

            titleTextView.setText(ad.getTitle());

            locationTextView.setText(ad.getLocationName());
            locationTextView.setCompoundDrawables(
                    AdHelper.getSupportDrawable(R.drawable.ic_location, context),
                    null,
                    null,
                    null);

            categoryTextView.setText(ad.getCategoryName());
            categoryTextView.setCompoundDrawables(
                    AdHelper.getSupportDrawable(R.drawable.ic_category, context),
                    null,
                    null,
                    null);

            priceTextView.setText(AdHelper.getPrice(ad.getPrice()));
            createdDateTextView.setText(AdHelper.getAdCreatedDate(ad.getCreatedAt(), false));

            if (ad.getImageList().isEmpty()) {
                imageView.setImageDrawable(AdHelper.getSupportDrawable(R.drawable.ic_placeholder, context));
            } else {
                Glide.with(context)
                        .load(AdHelper.getImageUrl(ad.getImageList().get(0).getSmall()))
                        .placeholder(AdHelper.getSupportDrawable(R.drawable.ic_placeholder, context))
                        .centerCrop()
                        .into(imageView);
            }
        }

        @Override
        public void onClick(View v) {
//            context.startActivity(AdActivity.buildIntent(context, adId));

            if (onClick != null) {
                onClick.invoke(adId);
            }
        }
    }
}
package com.solvo.hoam.presentation.ui.helper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.solvo.hoam.R;
import com.solvo.hoam.data.network.RestService;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AdHelper {
    private AdHelper() {
    }

    public static String getAdCreatedDate(String createdDate, boolean lineBreak) {
        try {
            Calendar today = Calendar.getInstance();
            Calendar created = Calendar.getInstance();
            created.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createdDate));

            return created.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                    created.get(Calendar.MONTH) == today.get(Calendar.MONTH) &&
                    created.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH) ?
                    getTodayString(created, lineBreak) : getDateString(created);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return createdDate.substring(0, createdDate.length() - 9);
    }

    @NonNull
    private static String getTodayString(Calendar created, boolean lineBreak) {
        String s = lineBreak ? "Сегодня\n" : "Сегодня, ";
        return s + created.get(Calendar.HOUR_OF_DAY) + ":" + getMinutesString(created.get(Calendar.MINUTE));
    }

    @NonNull
    private static String getDateString(Calendar created) {
        return created.get(Calendar.DAY_OF_MONTH)
                + "." + getMonthString(created.get(Calendar.MONTH))
                + "." + created.get(Calendar.YEAR);
    }

    private static String getMinutesString(int minutes) {
        return minutes < 10 ? "0" + minutes : String.valueOf(minutes);
    }

    private static String getMonthString(int month) {
        return ++month < 10 ? "0" + month : String.valueOf(month);
    }

    public static String getPrice(long price) {
        if (price == 0) {
            return "Цена не указана";
        }

        DecimalFormat format = new DecimalFormat();
        format.setDecimalSeparatorAlwaysShown(false);
        return format.format(price) + " руб.";
    }

    public static String getImageUrl(String url) {
        return RestService.HOAM_URL + url;
    }

    public static String getViews(int views, Resources resources) {
        return resources.getQuantityString(R.plurals.views, views, views);
    }

    public static String getTotalAds(int ads, Resources resources) {
        return resources.getQuantityString(R.plurals.ads, ads, ads);
    }

    @Nullable
    public static Drawable getSupportDrawable(@DrawableRes int id, Context context) {
        Drawable drawable = ContextCompat.getDrawable(context, id);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        return drawable;
    }
}

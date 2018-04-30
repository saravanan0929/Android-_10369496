package info.dublinbikes.bikes.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import info.dublinbikes.bikes.R;

/*************************************************************
**
**************************************************************
**Student ID: 10381791
**Assignment : Android
**Student Name : Ananth Bharadwaj(Msc.Information systems with computing
**
*************************************************************/

public class ImageUtil {

    private final static String TAG = ImageUtil.class.getSimpleName();

    public static int DEFAULT_IMAGE = R.drawable.dublinbikes;

    public static void loadImageFromUrl(final Context context, final String url, final ImageView ivAvatar) {

        Glide.with(context).load(url).asBitmap().listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                Log.e(TAG, "Load image from url ---> " + e.getMessage());
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                // do show
                return false;
            }
        }).fitCenter().placeholder(DEFAULT_IMAGE).into(new BitmapImageViewTarget(ivAvatar) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                ivAvatar.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

}

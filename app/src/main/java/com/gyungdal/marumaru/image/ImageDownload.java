package com.gyungdal.marumaru.image;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.gyungdal.marumaru.MainActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by GyungDal on 2016-05-06.
 */
public class ImageDownload extends AsyncTask<ArrayList<String>, Objects, Boolean> {
    private Context context;

    public ImageDownload(Context context){
        this.context = context;
    }

    /**
     * @params.[0] URL
     * @params.[1] 화
     * @return
     */
    @Override
    protected Boolean doInBackground(ArrayList<String>... params) {
        try {
            int i = 0;
            ArrayList<String> imageUrl = params[0];
            String savePath = Environment.getExternalStorageDirectory() + File.separator +
                    Environment.DIRECTORY_DOWNLOADS + File.separator +
                    MainActivity.comicTitle + File.separator +
                    params[1].get(0) + File.separator;
            File file = new File(savePath);
            if (!file.exists())
                file.mkdirs();
            for(String url : imageUrl){
                file = new File(savePath + String.format("%03d",++i) +
                        url.substring(url.lastIndexOf("."), url.length()));
                Picasso.with(context)
                        .load(url)
                        .into((Target) file);
            }
        }catch(Exception e) {
            Log.e(ImageDownload.class.getName(), e.getMessage());
            return false;
        }
        return true;
    }
}

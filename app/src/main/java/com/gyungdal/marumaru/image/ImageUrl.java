package com.gyungdal.marumaru.image;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;


/**
 * Created by GyungDal on 2016-05-06.
 * 각 화별 이미지 주소 추출
 */
public class ImageUrl extends AsyncTask<String, Void, ArrayList<String>>{
    private static final String IMAGE_TAG = "data-lazy-src";


    @Override
    protected ArrayList<String> doInBackground(String... params) {
        ArrayList<String> result = new ArrayList<>();
        try {
            Source source = new Source(new URL(params[0]));
            source.fullSequentialParse();
            List<Element> list = source.getAllElements(HTMLElementName.IMG);
            for(Element element : list){
                if(element.getAttributeValue("class").contains("alignnone"))
                    result.add(element.getAttributeValue(IMAGE_TAG));
            }
        }catch (MalformedURLException eurl){
            Log.e("Get image url", eurl.getMessage());
        }catch (IOException eio){
            Log.e("Get image url", eio.getMessage());
        }
        return result;
    }
}

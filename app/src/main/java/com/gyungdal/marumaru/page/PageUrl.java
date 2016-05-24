package com.gyungdal.marumaru.page;

import android.os.AsyncTask;
import android.util.Log;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GyungDal on 2016-05-24.
 */
public class PageUrl extends AsyncTask<String, Void, ArrayList<String>>{
    @Override
    protected ArrayList<String> doInBackground(String... params) {
        ArrayList<String> result = new ArrayList<>();
        try {
            Source source = new Source(new URL(params[0]));
            source.fullSequentialParse();
            List<Element> list = source.getAllElements(HTMLElementName.A);
            for(Element element : list){
                if(element.getAttributeValue("target").equals("_blank")
                    && element.getAttributeValue("href").contains("http://www.shencomics.com/archives"))
                        result.add(element.getAttributeValue("href").replaceAll("shencomics","yuncomics"));
            }
        }catch (MalformedURLException eurl){
            Log.e("Get page url", eurl.getMessage());
        }catch (IOException eio){
            Log.e("Get page url", eio.getMessage());
        }
        return result;
    }
}

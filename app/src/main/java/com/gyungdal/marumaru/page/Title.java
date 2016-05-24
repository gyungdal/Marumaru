package com.gyungdal.marumaru.page;

import android.os.AsyncTask;
import android.util.Log;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by GyungDal on 2016-05-24.
 */
public class Title extends AsyncTask<String, Void, String>{
    @Override
    protected String doInBackground(String... params) {
        String result = "";
        try {
            Source source = new Source(new URL(params[0]));
            source.fullSequentialParse();
            List<Element> list = source.getAllElements(HTMLElementName.H1);
            for(Element element : list){
                String temp = element.getTextExtractor().toString();
                result = temp.substring(0, temp.lastIndexOf(" "));
            }
        }catch (MalformedURLException eurl){
            Log.e("Get page url", eurl.getMessage());
        }catch (IOException eio){
            Log.e("Get page url", eio.getMessage());
        }
        return result;
    }
}

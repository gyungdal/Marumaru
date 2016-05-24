package com.gyungdal.marumaru;

import android.content.ClipboardManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.gyungdal.marumaru.image.ImageDownload;
import com.gyungdal.marumaru.page.PageTitle;

public class MainActivity extends AppCompatActivity {
    public static String comicTitle;
    private ClipboardManager clipboardManager;
    private PageTitle title;
    private ImageDownload download;
    private EditText eurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        title = new PageTitle();
        download = new ImageDownload(getApplicationContext());
        eurl = (EditText)findViewById(R.id.url);
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }
    private boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void Click(View v){
        switch(v.getId()){
            case R.id.fab :
                if(isOnline()) {
                    if(eurl.getText().toString().contains("marumaru.in/b/manga")){

                    }
                }else{
                    Snackbar.make(getCurrentFocus(), "온라인 상태가 아닙니다.",
                            Snackbar.LENGTH_SHORT).show();
                }
                break;
            case R.id.copy :
                eurl.setText(clipboardManager.getText().toString());
                break;
            default : break;
        }
    }
}

package com.dotplays.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {


    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);
    }

    public void openGoogle(View view) {
        Uri uri = Uri.parse("https://google.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    public void loadGoogleSite(View view) {


        webView.setWebChromeClient(new WebChromeClient() {

        });

        webView.setWebViewClient(new WebViewClient() {

        });
        webView.loadUrl("https://vnexpress.net");
    }

    public void httpRequest(View view) {


        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        try {
            URL url = new URL("http://docbao.vn");

            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();
            String data = "";

            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNext()) {
                data = data + scanner.nextLine();
            }

            Log.e("DATA", data);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

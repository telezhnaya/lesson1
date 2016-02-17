package com.csc.lesson1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "https://pp.vk.me/c11308/u97494504/118338570/z_e32887d7.jpg";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        new DownloadPhotoTask().execute(URL);
    }

    class DownloadPhotoTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            InputStream input = null;
            try {
                String url = params[0];
                URL urlConn = new URL(url);
                input = urlConn.openStream();
            }
            catch (Exception e) {
                Log.d("main", "Something went wrong.");
                e.printStackTrace();
            }

            return BitmapFactory.decodeStream(input);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }
    }
}
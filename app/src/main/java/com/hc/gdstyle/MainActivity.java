package com.hc.gdstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.maps.MapView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.map_view);

        mapView.onCreate(savedInstanceState);

        mapView.getMap().setMapCustomEnable(true);

    }

    @Override
    protected void onResume() {
        super.onResume();

        mapView.onResume();


       // String ps = MapUtils.setMapCustomFile(MainActivity.this,"style.data");
     //   Log.i("whc", "onResume: " + ps);



        mapView.getMap().setCustomMapStyle(
                new com.amap.api.maps.model.CustomMapStyleOptions()
                        .setEnable(true)
                        .setStyleDataPath(setMapCustomFile(MainActivity.this,"style.data"))
                        .setStyleExtraPath(setMapCustomFile(MainActivity.this,"style_extra.data"))
        );








    }



    // 设置个性化地图config文件路径
    public static String setMapCustomFile(Context context, String PATH) {
        FileOutputStream out = null;
        InputStream inputStream = null;
        String moduleName = null;
        try {
            moduleName = context.getFilesDir().getAbsolutePath();
            File f = new File(moduleName + "/" + PATH);
            if (f.exists()) {
                return moduleName + "/" + PATH;
            }
            inputStream = context.getAssets()
                    .open("" + PATH);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            f.createNewFile();
            out = new FileOutputStream(f);
            out.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return moduleName + "/" + PATH;


    }


}

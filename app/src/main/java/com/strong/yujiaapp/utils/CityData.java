package com.strong.yujiaapp.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017-08-30.
 */

public class CityData {

    /**
     * 从asset目录下读取城市json文件转化为String类型
     *
     *
     */
    public String getJson(Context context) {
        StringBuffer sb = new StringBuffer();
        AssetManager mAssetManager = context.getAssets();
        try {
            InputStream is = mAssetManager.open("citylist.json");
            byte[] data = new byte[is.available()];
            int len = -1;
            while ((len = is.read(data)) != -1) {
                sb.append(new String(data, 0, len, "utf-8"));
            }
            is.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }


}
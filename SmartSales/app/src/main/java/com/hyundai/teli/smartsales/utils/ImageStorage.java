package com.hyundai.teli.smartsales.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by nith on 2/25/15.
 */

public class ImageStorage {


    public static boolean saveToSdCard(String path, String filename, Bitmap bitmap) {

        File sdcard = Environment.getExternalStorageDirectory();

        File folder = new File(sdcard.getAbsoluteFile(), path);//the dot makes this directory hidden to the user
        folder.mkdir();
        File file = new File(folder.getAbsoluteFile(), filename + ".jpg");
        if (file.exists())
            return true;

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static File getImage(String path, String imageName) {

        File mediaImage = null;
        try {
            String root = Environment.getExternalStorageDirectory().getAbsolutePath();
            File myDir = new File(root);
            if (!myDir.exists())
                return null;

            mediaImage = new File(myDir.getPath() + path + "/" + imageName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mediaImage;
    }

    public static boolean checkifImageExists(String imageName, String path) {
        Bitmap bmp = null;
        File file = ImageStorage.getImage(path, "/" + imageName + ".jpg");

        if (file.exists())
            bmp = BitmapFactory.decodeFile(file.getPath());

        if (bmp == null || bmp.equals("")) {
            return false;
        }
        return true;
    }
}

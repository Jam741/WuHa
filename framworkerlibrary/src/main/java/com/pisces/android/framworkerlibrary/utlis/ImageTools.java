package com.pisces.android.framworkerlibrary.utlis;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by Jam on 2017/4/20 上午10:22.
 * Describe:
 */

public class ImageTools {

//    /**
//     * 获取图片宽度
//     *
//     * @param file 图片文件
//     * @return 宽度
//     */
//    public static int getImgWidth(File file) {
//        InputStream is = null;
//        BufferedImage src = null;
//        int ret = -1;
//        try {
//            is = new FileInputStream(file);
//            src = javax.imageio.ImageIO.read(is);
//            ret = src.getWidth(null); // 得到源图宽
//            is.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ret;
//    }
//
//
//    /**
//     * 获取图片高度
//     *
//     * @param file 图片文件
//     * @return 高度
//     */
//    public static int getImgHeight(File file) {
//        InputStream is = null;
//        BufferedImage src = null;
//        int ret = -1;
//        try {
//            is = new FileInputStream(file);
//            src = javax.imageio.ImageIO.read(is);
//            ret = src.getHeight(null); // 得到源图高
//            is.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ret;
//    }


    public static int[] getImgWidthHeight(String filePath) {
        int[] size = new int[2];

        BitmapFactory.Options options = new BitmapFactory.Options();

        /**
         * 最关键在此，把options.inJustDecodeBounds = true;
         * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
         */
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options); // 此时返回的bitmap为null
        /**
         *options.outHeight为原始图片的高
         */
        Log.d("Test", "Bitmap Height == " + options.outHeight);
        Log.d("Test", "Bitmap Width == " + options.outWidth);

        size[0] = options.outWidth;
        size[1] = options.outHeight;
        return size;
    }

    public static String getImageWidthHeightStringParam(String filePath) {
        int[] size = getImgWidthHeight(filePath);
        String param = "?width=" + size[0] + "&height=" + size[1];
        return param;
    }
}

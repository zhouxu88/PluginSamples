package com.zx.pluginsamples;

import android.util.Log;

import java.lang.reflect.Method;

/**
 * @author zhouxu
 * @descripe
 * @e-mail 374952705@qq.com
 * @time 2020/4/21
 */


public class Manage {
    /**
     * describe {@link #bb(String)} }
     */
    public void aa() {

    }

    private void bb(String path) {

    }

    public static String getText(Class clazz) {
        String result = "";
        Method method = null;
        try {
            Object object = clazz.newInstance();
            method = clazz.getMethod("getTextFromPlugin");
            method.setAccessible(true);
            result = (String) method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("tag", "getText-----: "+e.toString());
        }
        return result;
    }
}

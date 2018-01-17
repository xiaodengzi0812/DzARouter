package com.business.base.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CacheManager {

    private final String TAG = "CacheManager";

    private final String PUBLISH_CACHE_PATH = "photo_file_cache";

    private static CacheManager instance;

    private Context context;
    private List<String> pathList;


    public static CacheManager newInstance(Context context) {
        if (instance == null) {
            instance = new CacheManager(context);
        }
        return instance;
    }

    private CacheManager(Context context) {
        this.context = context;
        this.pathList = new ArrayList<>();
    }

    public boolean savePublishCache(Object obj) {
        return saveObject(obj, PUBLISH_CACHE_PATH);
    }

    public Object readPublishCache() {
        return readObject(PUBLISH_CACHE_PATH);
    }

    public void clearPublishCache() {
        clearCache(PUBLISH_CACHE_PATH);
    }

    /**
     * @param obj  要保存的数据
     * @param file 保存路径
     */
    public boolean saveObject(Object obj, String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = context.openFileOutput(file, 0);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.flush();
            if (pathList != null && !pathList.contains(file)) {
                pathList.add(file);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                oos.close();
            } catch (Exception e) {

            }
            try {
                fos.close();
            } catch (Exception e) {

            }
        }
    }

    /**
     * 读取Object数据
     *
     * @param file 读取file路径下的数据
     */
    public Object readObject(String file) {
        if (!isExistDataCache(file))
            return null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = context.openFileInput(file);
            ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof InvalidClassException) {
                File data = context.getFileStreamPath(file);
                data.delete();
            }
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    public void clearAllCache() {
        if (pathList != null) {
            for (String path : pathList) {
                File file = context.getFileStreamPath(path);
                if (file.isFile() && file.exists()) file.delete();
                Log.i(TAG, "delete cache file:" + path);
            }
        }
    }

    public void clearCache(String path) {
        if (pathList != null && pathList.contains(path)) {
            File file = context.getFileStreamPath(path);
            if (file.isFile() && file.exists()) {
                if (file.delete()) {
                    pathList.remove(path);
                    Log.i("TAG", "delete cache file:" + path);
                }
            }
        }
    }

    /**
     * 缓存文件是否存在
     *
     * @param cachefile
     * @return
     */
    private boolean isExistDataCache(String cachefile) {
        boolean exist = false;
        if (TextUtils.isEmpty(cachefile)) return exist;
        File data = context.getFileStreamPath(cachefile);
        if (data.exists())
            exist = true;
        return exist;
    }

}

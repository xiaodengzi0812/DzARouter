package com.business.base.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import com.business.base.BaseUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title:崩溃相关工具类
 * @Author: djk
 * @Time: 2017/8/2
 * @Version:1.0.0
 */
public final class CrashUtils {
    private static String defaultDir;
    private static final String FILE_SEP = System.getProperty("file.separator");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String CRASH_HEAD;
    private static final UncaughtExceptionHandler DEFAULT_UNCAUGHT_EXCEPTION_HANDLER;
    private static final UncaughtExceptionHandler UNCAUGHT_EXCEPTION_HANDLER;

    static {
        String versionName = null;
        int versionCode = 0;
        try {
            PackageInfo pi = BaseUtil.getContext().getPackageManager().getPackageInfo(BaseUtil.getContext().getPackageName(), 0);
            if (pi != null) {
                versionName = pi.versionName;
                versionCode = pi.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        CRASH_HEAD = "nDevice Model: " + Build.MODEL +// 设备型号
                "\nAndroid Version: " + Build.VERSION.RELEASE +// 系统版本
                "\nAndroid SDK: " + Build.VERSION.SDK_INT +// SDK版本
                "\nApp VersionName: " + versionName +
                "\nApp VersionCode: " + versionCode;

        DEFAULT_UNCAUGHT_EXCEPTION_HANDLER = Thread.getDefaultUncaughtExceptionHandler();

        UNCAUGHT_EXCEPTION_HANDLER = new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                if (e == null) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        saveErrorLog(e);
                    }
                }).start();
                if (DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                    DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(t, e);
                }
            }
        };
    }

    private CrashUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>}</p>
     *
     * @return {@code true}: 初始化成功<br>{@code false}: 初始化失败
     */
    public static void init() {
        String crashDir = BaseUtil.getContext().getPackageName() + "_crash";
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
            defaultDir = Environment.getExternalStorageDirectory() + FILE_SEP + crashDir;
        else {
            defaultDir = BaseUtil.getContext().getFilesDir() + FILE_SEP + crashDir;
        }
        Thread.setDefaultUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
    }

    /**
     * 设置异常存储目录
     *
     * @param crashDir
     */
    public static void setCrashDir(String crashDir) {
        if (TextUtils.isEmpty(crashDir)) return;

        File directory = new File(crashDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        defaultDir = crashDir;
    }

    /**
     * 存储异常错误到本地文件中
     *
     * @param ex
     * @Description:
     */
    private static void saveErrorLog(Throwable ex) {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            File directory = new File(defaultDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File logFile = new File(defaultDir + FILE_SEP + "error_log.txt");
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            fw = new FileWriter(logFile, true);
            pw = new PrintWriter(fw);
            String nowTime = sdf.format(new Date());
            pw.println("-----" + nowTime + "-----");
            pw.println(CRASH_HEAD);
            ex.printStackTrace(pw);
        } catch (IOException e) {
        } finally {
            pw.close();
            try {
                fw.close();
            } catch (IOException e) {
            }
        }
    }
}

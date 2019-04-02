package com.example.wh.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.util.Log;

public class NetWorkUtils {
    private static final String TAG = "whwhwh---NetWorkUtils";
    private volatile static NetWorkUtils sInstance;
    private Context mConnect;
    private ConnectivityManager mConnectivityManager;

    private NetWorkUtils(Context context) {
        mConnect = context;
        mConnectivityManager = (ConnectivityManager) mConnect.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    //单例
    public static NetWorkUtils getInstance(Context context) {
        if (sInstance == null) {
            synchronized (NetWorkUtils.class) {
                if (sInstance == null) {
                    sInstance = new NetWorkUtils(context);
                }
            }
        }
        return sInstance;
    }

    public void getNetWorkState() {
        // 以太网络的网络状态
        State state = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET).getState();
        Log.i(TAG, "isConnected: ethernet state = " + state);
        // WiFi网络的网络状态
        state = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        Log.i(TAG, "isConnected: wifi state = " + state);
        // Mobile网络的状态,电视上运行报错
        state = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        Log.i(TAG, "isConnected: mobile state = " + state);

        // 获取当前活跃的网络信息
        NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
        if (info != null) {
            // 网络类型
            Log.i(TAG, "getNetWorkState: type = " + info.getType());
            // 网络类型名称
            Log.i(TAG, "getNetWorkState: typename = " + info.getTypeName());
            // 网络状态
            Log.i(TAG, "getNetWorkState: " + info.getState());
        }
    }

    // 判断是否可用
    public boolean isNetworkAvailable() {
        // 获取可用的网络信息
        NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
        if (info != null) {
            // 判断网络是否可用
            return info.isAvailable();
        }
        return false;
    }

    // 判断网络是否已连接
    public boolean isNetworkConnected() {
        NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
        if (info != null) {
            return info.isConnected();
        }
        return false;
    }

    // 判断是否连接的是以太网
    public boolean isEthernetConnected() {
        // WiFi类型的网络信息
        NetworkInfo info = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        if (info != null) {
            return info.isConnected();
        }
        return false;
    }

    // 判断是否连接的是WiFi网络
    public boolean isWiFiConnected() {
        // WiFi类型的网络信息
        NetworkInfo info = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (info != null) {
            return info.isConnected();
        }
        return false;
    }

    // 判断是否连接的是Mobile网络
    public boolean isMobileConnected() {
        // WiFi类型的网络信息
        NetworkInfo info = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (info != null) {
            return info.isConnected();
        }
        return false;
    }
}


package com.zl.networklivedata

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData


/**
 * @author      zl
 * @Date        2020/4/20
 **/
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class NetworkLiveData : LiveData<Int>() {

    private var networkCallback: ConnectivityManager.NetworkCallback

    private var request: NetworkRequest

    private var manager: ConnectivityManager

    init {
        networkCallback = NetworkCallbackImpl()
        request = NetworkRequest.Builder().build()
        manager = MyApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun onActive() {
        super.onActive()
        manager.registerNetworkCallback(request, networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        manager.unregisterNetworkCallback(networkCallback)
    }

    object NetworkLiveDataHolder {
        val INSTANCE = NetworkLiveData()
    }

    companion object {
        fun getInstance(): NetworkLiveData {
            return NetworkLiveDataHolder.INSTANCE
        }
    }

    class NetworkCallbackImpl : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d("onAvailable: ", "网络已连接")
            getInstance().postValue(NetworkState.CONNECT)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d("onLost: ", "网络断开")
            getInstance().postValue(NetworkState.NONE)
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.d("WIFI: ", "WIFI已连接")
                getInstance().postValue(NetworkState.WIFI)
            } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.d("CELLULAR: ", "移动网络已连接")
                getInstance().postValue(NetworkState.CELLULAR)
            }
        }
    }
}
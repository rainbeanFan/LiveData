package cn.lancet.livedata

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.*

class LiveDataService : Service() {

    private val mTimer = Timer()

    override fun onCreate() {
        super.onCreate()
        initData()
    }

    private fun initData() {
        mTimer.schedule(object : TimerTask() {
            override fun run() {
                ShareLiveData.getInstance().postValue(System.currentTimeMillis())
            }
        },0,1000)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        mTimer.cancel()
    }

}
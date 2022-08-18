package cn.lancet.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * Created by Lancet at 2022/8/18 11:38
 */
class CurrentTimeViewModel : ViewModel() {

    private var mMediatorLiveData: MediatorLiveData<Long>? = null
    private var mCurrentTimeLiveData: LiveData<String>? = null

    private var mTimer = Timer()

    init {
        if (mMediatorLiveData == null) {
            mMediatorLiveData = MediatorLiveData()
        }
        if (mCurrentTimeLiveData == null) {
            mCurrentTimeLiveData = Transformations.map(
                mMediatorLiveData!!
            ) { input -> Date(input!!).toString() }
        }
    }

    fun getCurrentTime(): LiveData<String> {
        return mCurrentTimeLiveData!!
    }

    fun start() {
        mTimer.schedule(object : TimerTask() {
            override fun run() {
                mMediatorLiveData?.postValue(System.currentTimeMillis())
            }
        }, 0, 1000)
    }

    fun stop() {
        mTimer.cancel()
    }

}
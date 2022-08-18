package cn.lancet.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Lancet at 2022/8/18 11:38
 */
class CurrentTimeViewModel: ViewModel() {

    private var mCurrentTime:MutableLiveData<String>?=null

    fun getCurrentTime():MutableLiveData<String>{
        if (mCurrentTime == null) {
            mCurrentTime = MutableLiveData()
        }
        return mCurrentTime!!
    }

    fun setCurrentTime(currentTime:String){
        if (mCurrentTime == null) {
            mCurrentTime = MutableLiveData()
        }
        mCurrentTime!!.postValue(currentTime)
    }

}
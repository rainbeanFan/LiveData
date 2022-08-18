package cn.lancet.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData

/**
 * Created by Lancet at 2022/8/18 15:40
 */
class StorePCLiveData private constructor(): LiveData<Long>() {

    private var mCurrentTimeMillis:Long?=null

    companion object {
        @JvmStatic
        @MainThread
        fun getInstance(): StorePCLiveData {
            return SingletonHolder.instance
        }
    }

    private object SingletonHolder {
        val instance = StorePCLiveData()
    }

    public override fun postValue(value: Long?) {
        super.postValue(value)
        mCurrentTimeMillis = value
    }

    override fun setValue(value: Long?) {
        super.setValue(value)
        mCurrentTimeMillis = value
    }

    fun getProductSpeed() = 1

    override fun getValue(): Long {
        super.getValue()
        return mCurrentTimeMillis!!
    }

}
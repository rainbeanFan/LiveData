package cn.lancet.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData

/**
 * Created by Lancet at 2022/8/18 15:40
 */
class StorePhoneLiveData private constructor(): LiveData<Long>() {

    private var mCurrentTimeMillis:Long?=null

    companion object {
        @JvmStatic
        @MainThread
        fun getInstance(): StorePhoneLiveData {
            return SingletonHolder.instance
        }
    }

    private object SingletonHolder {
        val instance = StorePhoneLiveData()
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
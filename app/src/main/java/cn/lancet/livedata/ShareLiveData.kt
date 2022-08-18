package cn.lancet.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import java.util.*

/**
 * Created by Lancet 2022/8/18 11:57
 */
class ShareLiveData<T> private constructor() : LiveData<T>() {

    private var mCurrentTimeMillis: Long? = null

    private var mDate:String?=null

    companion object {
        @JvmStatic
        @MainThread
        fun getInstance(): ShareLiveData<Any> {
            return SingletonHolder.holder
        }
    }

    private object SingletonHolder {
        val holder = ShareLiveData<Any>()
    }

    public override fun postValue(value: T?) {
        super.postValue(value)
        if (value is Long){
            mCurrentTimeMillis = value
        }
        if (value is String){
            mDate = value
        }
    }

    override fun setValue(value: T?) {
        super.setValue(value)
        if (value is Long){
            mCurrentTimeMillis = value
        }
        if (value is String){
            mDate = value
        }
    }

    override fun getValue(): T? {
        super.getValue()
        mapTime()
        return mDate as T
    }

    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }

    private fun mapTime(){
        val liveData : LiveData<String> = Transformations.map(this
        ) { input -> Date(input!! as Long).toString() }
        liveData.value
    }

}
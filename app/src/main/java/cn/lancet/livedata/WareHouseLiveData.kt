package cn.lancet.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData

/**
 * Created by Lancet at 2022/8/18 15:52
 */
class WareHouseLiveData private constructor(private var initialAmount: Int) : LiveData<Int>() {

    companion object {

        @Volatile
        private var instance: WareHouseLiveData? = null

        @JvmStatic
        @MainThread
        fun getInstance(initialAmount: Int) =
            instance ?: WareHouseLiveData(initialAmount).also {
                instance = it
            }
    }

    override fun postValue(value: Int) {
        super.postValue(value)
        initialAmount = value
    }

    override fun setValue(value: Int) {
        super.setValue(value)
        initialAmount = value
    }

    override fun getValue(): Int {
        super.getValue()
        return initialAmount
    }

    fun product(amount: Int) {
        initialAmount -= amount
        initialAmount = initialAmount.coerceAtLeast(0)
        postValue(initialAmount)
    }

}
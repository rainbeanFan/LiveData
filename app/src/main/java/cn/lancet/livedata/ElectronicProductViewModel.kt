package cn.lancet.livedata

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * Created by Lancet at 2022/8/18 11:38
 */
class ElectronicProductViewModel : ViewModel() {

    private var mPhoneTimer = Timer()
    private var mPCTimer = Timer()

    private var mStoreLiveData = MediatorLiveData<Int>()

    fun start() {
        mPhoneTimer.schedule(object : TimerTask() {
            override fun run() {
                StorePhoneLiveData.getInstance().postValue(System.currentTimeMillis())
            }
        }, 0, 1000)
        mPCTimer.schedule(object : TimerTask() {
            override fun run() {
                StorePCLiveData.getInstance().postValue(System.currentTimeMillis())
            }
        }, 0, 5000)

    }

    private fun getProductPhoneSpeed() = Transformations.switchMap(
        StorePhoneLiveData.getInstance()
    ) {
        WareHouseLiveData.getInstance(2000)
            .product(StorePhoneLiveData.getInstance().getProductSpeed())
        WareHouseLiveData.getInstance(2000)
    }

    private fun getProductPCSpeed() = Transformations.switchMap(
        StorePCLiveData.getInstance()
    ) {
        WareHouseLiveData.getInstance(2000)
            .product(StorePCLiveData.getInstance().getProductSpeed())
        WareHouseLiveData.getInstance(2000)
    }

    fun mediatorData(): MediatorLiveData<Int> {
        val phoneLiveData = getProductPhoneSpeed()
        val pcLiveData = getProductPCSpeed()
        mStoreLiveData.addSource(
            phoneLiveData
        ) { t -> mStoreLiveData.value = t }
        mStoreLiveData.addSource(pcLiveData) { t ->
            mStoreLiveData.value = t
        }
        return mStoreLiveData
    }

    fun stop() {
        mPhoneTimer.cancel()
        mPCTimer.cancel()
    }

}
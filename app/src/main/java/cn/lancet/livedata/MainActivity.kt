package cn.lancet.livedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.lancet.livedata.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private var mViewModel: CurrentTimeViewModel? = null

    private var mElectronicProductViewModel: ElectronicProductViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
//        initData()

        initElectronicProducts()
    }

    private fun initElectronicProducts() {
        mElectronicProductViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[ElectronicProductViewModel::class.java]

        mElectronicProductViewModel?.mediatorData()!!.observe(this) { t ->
            mBinding!!.tvTime.text = "$t"
        }

        mElectronicProductViewModel?.start()

    }


    private fun initData() {
        mViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CurrentTimeViewModel::class.java]

        val currentTime = Observer<String> { shareTime ->
            if (!shareTime.isNullOrBlank()) {
                mBinding!!.tvTime.text = shareTime
            } else {
                mBinding!!.tvTime.text = resources.getString(R.string.app_name)
            }
        }
        mViewModel!!.getCurrentTime().observe(this, currentTime)
        mViewModel?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel?.stop()
        mElectronicProductViewModel?.stop()
    }

}
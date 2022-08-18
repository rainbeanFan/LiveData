package cn.lancet.livedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.lancet.livedata.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)

//        initData()
        initServiceData()

    }

    private fun initServiceData() {
        ShareLiveData.getInstance().observe(this
        ) { time -> mBinding!!.tvTime.text = Date(time as Long).toString() }


        startService(Intent(this,LiveDataService::class.java))

    }

    private fun initData() {
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CurrentTimeViewModel::class.java]

        val currentTime = Observer<String> { str ->
            if (str != null) {
                mBinding!!.tvTime.text = str
            } else {
                mBinding!!.tvTime.text = resources.getString(R.string.app_name)
            }
        }
        viewModel.getCurrentTime().observe(this, currentTime)

        timer.schedule(object : TimerTask() {
            override fun run() {
                viewModel.setCurrentTime(Date().toString())
            }
        }, 0, 1000)

    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

}
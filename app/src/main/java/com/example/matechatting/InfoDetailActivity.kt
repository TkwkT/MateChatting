package com.example.matechatting

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import com.example.matechatting.activity.BaseActivity
import com.example.matechatting.databinding.ActivityInfodetailBinding
import com.example.matechatting.utils.statusbar.StatusBarUtil


class InfoDetailActivity : BaseActivity<ActivityInfodetailBinding>() {

    private lateinit var back: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setRootViewFitsSystemWindows(this, true)
        StatusBarUtil.setStatusBarDarkTheme(this, true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setStatusBarColor(this, this.getColor(R.color.bg_ffffff))
        }
        canSlideFinish(true)
        initBinding()
        initView()
    }

    private fun initView() {
        binding.apply {
            back = icInfodetailBack
        }
        back.setOnClickListener{
            finish()
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_infodetail
    }

}

package com.example.matechatting

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.matechatting.activity.BaseActivity
import com.example.matechatting.databinding.ActivityInfoDetailBinding
import com.example.matechatting.utils.statusbar.StatusBarUtil


class InfoDetailActivity : BaseActivity<ActivityInfoDetailBinding>() {
    private lateinit var back:ImageView

    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.getIntExtra("id",0)
        Log.d("aaa",id.toString())
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

        back = binding.infoDetailBack
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_info_detail
    }

}

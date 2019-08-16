package com.example.matechatting.activity

import android.os.Build
import android.os.Bundle
import com.example.matechatting.R
import com.example.matechatting.databinding.ActivityHomeSearchBinding
import com.example.matechatting.utils.statusbar.StatusBarUtil

class HomeSearchActivity : BaseActivity<ActivityHomeSearchBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setRootViewFitsSystemWindows(this, true)
        StatusBarUtil.setStatusBarDarkTheme(this, true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setStatusBarColor(this, this.getColor(R.color.bg_ffffff))
        }
        initBinding()
        canSlideFinish(true)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home_search
    }
}

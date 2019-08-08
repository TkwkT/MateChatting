package com.example.matechatting

import android.os.Build
import android.os.Bundle
import com.example.matechatting.databinding.ActivityChangePasswordBinding
import com.example.matechatting.myview.StatusBarHeightView
import com.example.matechatting.utils.statusbar.StatusBarUtil

class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_change_password
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        StatusBarUtil.setRootViewFitsSystemWindows(this,true)
        StatusBarUtil.setStatusBarDarkTheme(this,true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setStatusBarColor(this,this.getColor(R.color.bg_ffffff))
        }
        canSlideFinish(true)
    }
}

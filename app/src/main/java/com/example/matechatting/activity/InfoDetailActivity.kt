package com.example.matechatting.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.example.matechatting.R
import com.example.matechatting.activity.BaseActivity
import com.example.matechatting.databinding.ActivityInfoDetailBinding
import com.example.matechatting.utils.InjectorUtils
import com.example.matechatting.utils.statusbar.StatusBarUtil
import com.example.matechatting.viewmodel.BindPhoneViewModel
import com.example.matechatting.viewmodel.InfoDetailViewModel


class InfoDetailActivity : BaseActivity<ActivityInfoDetailBinding>() {
    private lateinit var back:ImageView
    private lateinit var viewModel:InfoDetailViewModel

    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.getIntExtra("id",0)
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
        val factory = InjectorUtils.provideInfoDetailViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, factory).get(InfoDetailViewModel::class.java)
        back = binding.infoDetailBack
        viewModel.getDetail(id)
        binding.viewmodel = viewModel
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_info_detail
    }

}

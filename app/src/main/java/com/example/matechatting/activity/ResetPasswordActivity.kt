//package com.example.matechatting.activity
//
//import android.content.Intent
//import android.os.Build
//import android.os.Bundle
//import android.text.SpannableStringBuilder
//import android.text.method.HideReturnsTransformationMethod
//import android.text.method.PasswordTransformationMethod
//import android.view.View
//import android.widget.Button
//import android.widget.EditText
//import android.widget.ImageView
//import android.widget.TextView
//import com.example.matechatting.R
//import com.example.matechatting.databinding.ActivityResetPasswordBinding
//import com.example.matechatting.fragment.BaseFragment.Companion.account
//import com.example.matechatting.listener.EditTextTextChangeListener
//import com.example.matechatting.utils.statusbar.StatusBarUtil
//
//class ResetPasswordActivity : BaseActivity<ActivityResetPasswordBinding>() {
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        StatusBarUtil.setRootViewFitsSystemWindows(this, true)
//        StatusBarUtil.setStatusBarDarkTheme(this, true)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            StatusBarUtil.setStatusBarColor(this, this.getColor(R.color.bg_ffffff))
//        }
//        canSlideFinish(true)
//        account = intent.getStringExtra("account")?:""
//        initBinding()
//        initView()
//
//    }
//
//    private fun initView(){
//
//    }
//
//
//
//    private fun transferLoginActivity(intent:Intent){
//        startActivityForResult(intent,0x123)
//    }
//
//
//    override fun getLayoutId(): Int {
//        return R.layout.activity_reset_password
//    }
//}

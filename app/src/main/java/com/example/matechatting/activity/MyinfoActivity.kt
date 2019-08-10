package com.example.matechatting.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import com.example.matechatting.utils.statusbar.StatusBarUtil
import android.os.IBinder
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.ImageView
import com.example.matechatting.R
import com.example.matechatting.databinding.ActivityMyInfoBinding

/**
 * 未实现返回时上传数据@link [initBack]
 */
class MyinfoActivity : BaseActivity<ActivityMyInfoBinding>() {
    private lateinit var tv_personsign: TextView
    private lateinit var tv_company: TextView
    private lateinit var tv_location: TextView
    private lateinit var tv_post: TextView
    private lateinit var tv_qq: TextView
    private lateinit var tv_weixin: TextView
    private lateinit var tv_email: TextView
    private lateinit var fl_company: FrameLayout
    private lateinit var fl_post: FrameLayout
    private lateinit var fl_direct: FrameLayout
    private lateinit var fl_qq: FrameLayout
    private lateinit var fl_weixin: FrameLayout
    private lateinit var fl_email: FrameLayout
    private lateinit var fl_personsign: FrameLayout
    private lateinit var fl_location: FrameLayout
    private lateinit var et_location: EditText
    private lateinit var et_post: EditText
    private lateinit var et_company: EditText
    private lateinit var et_qq: EditText
    private lateinit var et_weixin: EditText
    private lateinit var etEmail: EditText
    private lateinit var infoBack:ImageView


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
        initBack()
        //setunClick()
    }

//    private fun setunClick() {
//        et_location.isClickable = false
//        et_post.isClickable = false
//        et_company.isClickable = false
//        et_qq.isClickable = false
//        et_weixin.isClickable = false
//        etEmail.isClickable = false
//    }

    /**
     * 未实现返回时上传数据
     */
    private fun initBack(){
        infoBack.setOnClickListener {
            finish()
        }
    }

    private fun initView() {
        binding.apply {
            tv_personsign = tvMyinfoPersonsign
            tv_company = tvMyinfoCompany
            tv_location = tvMyinfoLocation
            tv_post = tvMyinfoPost
            tv_qq = tvMyinfoQQ
            tv_weixin = tvMyinfoWeixin
            tv_email = tvMyinfoEmail
            fl_company = flMyinfocompany
            fl_post = flMyinfoPost
            fl_direct = flMyinfoDirect
            fl_qq = flMyinfoQQ
            fl_weixin = flMyinfoWeixin
            fl_email = flMyinfoEmail
            fl_personsign = flMyinfoPersonsign
            fl_location = flMyinfoLocation
            et_company = etMyinfoCompany
            et_post = etMyinfoPost
            et_qq = etMyinfoQQ
            et_weixin = etMyinfoWeixin
            etEmail = etMyinfoEmail
            et_location = etMyinfoLocation
            infoBack = myinfoBack
        }

        /**
         * 个性标语跳转
         */
        fl_personsign.setOnClickListener {
            val intent = Intent(this, PersonsignActivity::class.java)
            startActivityForResult(intent, 1)

        }
        /**
         * 现居地点击
         */
        setAdapter(et_location, tv_location, fl_location)
        /**
         * 所在公司点击
         */
        setAdapter(et_company, tv_company, fl_company)
        /**
         *  职业/岗位点击
         */
        setAdapter(et_post, tv_post, fl_post)
        /**
         *  QQ号点击
         */
        setAdapter(et_qq, tv_qq, fl_qq)
        /**
         *  微信号点击
         */
        setAdapter(et_weixin, tv_weixin, fl_weixin)

        /**
         *  邮箱点击
         */
        setAdapter(etEmail, tv_email, fl_email)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
//                var bundle = this.intent.extras
                val personSign: String? = data?.getStringExtra("personSign")
                tv_personsign.text = personSign
            }
        }
    }

    /**
     * 重写焦点的分发
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (isShouldHideKeyboard(v, ev)) {
                v?.clearFocus()
                hideKeyboard(v!!.windowToken)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private fun isShouldHideKeyboard(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val l = intArrayOf(0, 0)
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.height
            val right = left + v.width
            return !(event.x > left && event.x < right
                    && event.y > top && event.y < bottom)
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private fun hideKeyboard(token: IBinder?) {
        if (token != null) {
            val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    /**
     * 获取edittext焦点，并显示软键盘
     */
    private fun showSoftInputFromWindow(editText: EditText) {
        editText.isFocusable = true
        editText.isFocusableInTouchMode = true
        editText.requestFocus()
        val inputManager = editText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(editText, 0)
    }

    /**
     * 封装item的获取点击事情
     */
    private fun setAdapter(et: EditText, tv: TextView, fl: ViewGroup) {
        fl.setOnClickListener {
            tv.visibility = View.GONE
            et.visibility = View.VISIBLE
            showSoftInputFromWindow(et)

        }
        et.setOnClickListener {
            tv.visibility = View.GONE
            et.visibility = View.VISIBLE
            showSoftInputFromWindow(et)

        }

        et.onFocusChangeListener = OnFocusChangeListener { p0, hasFocus ->
            if (!hasFocus) {
                et.visibility = View.GONE
                tv.visibility = View.VISIBLE
                var info: String = et.text.toString()
                if (info.isEmpty())
                    info = "未填写"
                tv.text = info
            }
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_my_info
    }
}

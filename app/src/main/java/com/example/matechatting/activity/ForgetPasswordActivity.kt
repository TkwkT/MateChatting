package com.example.matechatting.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.matechatting.R
import com.example.matechatting.databinding.ActivityForgetPasswordBinding
import com.example.matechatting.listener.EditTextTextChangeListener
import com.example.matechatting.utils.statusbar.StatusBarUtil

/**
 * 未完成
 * 获取验证码
 * 判断是否为可用手机号
 * 输入6位验证码自动跳转或显示错误
 * error的显示未设置
 * 有bug @link[setCountdown]
 * @link[initNextButton]未实现
 */
class ForgetPasswordActivity : BaseActivity<ActivityForgetPasswordBinding>() {
    private lateinit var back: ImageView
    private lateinit var phoneEdit: EditText
    private lateinit var phoneClear: ImageView
    private lateinit var phoneError: TextView
    private lateinit var codeEdit: EditText
    private lateinit var codeClear: ImageView
    private lateinit var sendMessage: Button
    private lateinit var codeError: TextView
    private lateinit var nextButton: Button
    private var phoneNotNull = false
    private var codeNotNull = false

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
        initEditText()
        initSendButton()
        initClear()
        initNextButton()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        binding.apply {
            back = forgetBack
            phoneEdit = forgetPhoneEdit
            phoneClear = forgetPhoneClear
            phoneError = forgetPhoneError
            codeEdit = forgetCheckCodeEdit
            codeClear = forgetCheckCodeClear
            codeError = forgetCheckCodeError
            sendMessage = forgetSendMessage
            nextButton = forgetNextButton
        }
    }

    /**
     * 设置EditText @link [phoneEdit] [codeEdit]的输入监听
     * 设置当输入内容时 显示@link [phoneClear] [codeClear] 可见
     * 设置@link [phoneNotNull] [codeNotNull] 来监听输入是否为空
     * 设置@link [phoneError] [codeError] 内容清空
     * 调用@link [canClick] 来切换@link [nextButton] 背景
     */
    private fun initEditText() {
        phoneEdit.addTextChangedListener(
            EditTextTextChangeListener({
                if (it.isNotEmpty()) {
                    phoneClear.visibility = View.VISIBLE
                    phoneNotNull = true
                } else {
                    phoneClear.visibility = View.GONE
                    phoneNotNull = false
                }
                canClick()
            }, { s: CharSequence, i: Int, i1: Int, i2: Int ->
                phoneError.text = ""
            })
        )
        codeEdit.addTextChangedListener(EditTextTextChangeListener({
            if (it.isNotEmpty()) {
                codeClear.visibility = View.VISIBLE
                codeNotNull = true
            } else {
                codeClear.visibility = View.GONE
                codeNotNull = false
            }
            canClick()
        }, { s: CharSequence, i: Int, i1: Int, i2: Int ->
            codeError.text = ""
        }))
    }

    /**
     * 根据输入是否为空设置@link[nextButton] 是否可点击
     * 调用点击后的操作（未实现）
     */
    private fun canClick() {
        if (phoneNotNull && codeNotNull) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                nextButton.background = this.resources.getDrawable(R.drawable.shape_bt_enable, null)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                nextButton.background = this.resources.getDrawable(R.drawable.shape_bt_enable)
            }
            nextButton.isEnabled = true
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                nextButton.background = this.resources.getDrawable(R.drawable.shape_bt_disable, null)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                nextButton.background = this.resources.getDrawable(R.drawable.shape_bt_disable)
            }
            nextButton.isEnabled = false
        }
    }

    /**
     * 设置@link[sendMessage] 的点击事件
     * 调用@link[setCountdown] 进行倒计时
     */
    private fun initSendButton() {
        sendMessage.setOnClickListener {
            sendMessage.isEnabled = false
            setCountdown()
        }
    }

    private fun initClear(){
        val emptyString = SpannableStringBuilder("")
        phoneClear.setOnClickListener {
            phoneEdit.text = emptyString
        }
        codeClear.setOnClickListener {
            codeEdit.text = emptyString
        }
    }

    /**
     * 设置倒计时
     * 有bug 切换activity后倒计时会清空
     */
    private fun setCountdown() {
        object : CountDownTimer(60 * 1000, 1000) {
            override fun onTick(p0: Long) {
                val s = p0.toInt() / 1000
                val str = "$s 秒后可再次发送"
                sendMessage.text = str
                Log.d("aaa",s.toString())
            }

            override fun onFinish() {
                sendMessage.text = "获取验证码"
                sendMessage.isEnabled = true
            }
        }.start()
    }

    /**
     * 设置@link[nextButton] 的点击事件
     * 未实现
     */
    private fun initNextButton(){
        val intent = Intent(this,ResetPasswordActivity::class.java)
        nextButton.setOnClickListener {
            Log.d("aaa","bbb")
            startActivity(intent)
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_forget_password
    }
}

package com.example.matechatting.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil.setContentView
import com.example.matechatting.R
import com.example.matechatting.databinding.ActivityBindPhoneBinding
import com.example.matechatting.listener.EditTextTextChangeListener
import com.example.matechatting.utils.statusbar.StatusBarUtil

class BindPhoneActivity : BaseActivity<ActivityBindPhoneBinding>() {
    private lateinit var back:ImageView
    private lateinit var phoneEdit:EditText
    private lateinit var phoneClear:ImageView
    private lateinit var phoneError:TextView
    private lateinit var codeEdit:EditText
    private lateinit var codeClear:ImageView
    private lateinit var codeError:TextView
    private lateinit var sendButton: Button
    private lateinit var overButton:Button
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
        initBack()
        initOverButton()
    }

    private fun initView(){
        binding.apply {
            back = bindPhoneBack
            phoneEdit = bindPhoneEdittext
            phoneClear = bindPhoneClear
            phoneError = bindPhoneError
            codeEdit = bindCodeEdittext
            codeClear = bindCodeClear
            codeError = bindCodeError
            sendButton = bindSendMessage
            overButton = bindPhoneButton

        }
    }

    private fun initBack(){
        back.setOnClickListener {
            finish()
        }
    }

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

    private fun canClick() {
        if (phoneNotNull && codeNotNull) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                overButton.background = this.resources.getDrawable(R.drawable.shape_bt_enable, null)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                overButton.background = this.resources.getDrawable(R.drawable.shape_bt_enable)
            }
            overButton.isEnabled = true
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                overButton.background = this.resources.getDrawable(R.drawable.shape_bt_disable, null)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                overButton.background = this.resources.getDrawable(R.drawable.shape_bt_disable)
            }
            overButton.isEnabled = false
        }
    }

    private fun initSendButton() {
        sendButton.setOnClickListener {
            sendButton.isEnabled = false
            setCountdown()
        }
    }

    private fun initOverButton(){
        overButton.setOnClickListener {
            finish()
        }
    }

    private fun setCountdown() {
        object : CountDownTimer(60 * 1000, 1000) {
            override fun onTick(p0: Long) {
                val s = p0.toInt() / 1000
                val str = "$s 秒后可再次发送"
                sendButton.text = str
                Log.d("aaa",s.toString())
            }

            override fun onFinish() {
                sendButton.text = "获取验证码"
                sendButton.isEnabled = true
            }
        }.start()
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


    override fun getLayoutId(): Int {
        return R.layout.activity_bind_phone
    }
}

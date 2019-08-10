package com.example.matechatting.activity

import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.matechatting.R
import com.example.matechatting.databinding.ActivityChangePasswordBinding
import com.example.matechatting.listener.EditTextTextChangeListener
import com.example.matechatting.utils.functionutil.ChangePasswordState.Companion.NEW_ERROR
import com.example.matechatting.utils.functionutil.ChangePasswordState.Companion.NEW_NO6
import com.example.matechatting.utils.functionutil.ChangePasswordState.Companion.NEW_NULL
import com.example.matechatting.utils.functionutil.ChangePasswordState.Companion.NEW_OK
import com.example.matechatting.utils.functionutil.ChangePasswordState.Companion.NEW_TOO_LONG
import com.example.matechatting.utils.functionutil.ChangePasswordUtil
import com.example.matechatting.utils.statusbar.StatusBarUtil

/**
 * 未完成
 * 需获取从上一个activity传来的账号
 * @link [initButton] 功能未实现全 点击结果的反馈未实现
 */
class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding>() {
    private lateinit var oldEdit: EditText
    private lateinit var oldImageView: ImageView
    private lateinit var oldError: TextView
    private lateinit var oldClear: ImageView
    private lateinit var newEdit: EditText
    private lateinit var newImageView: ImageView
    private lateinit var newError: TextView
    private lateinit var newClear: ImageView
    private lateinit var againEdit: EditText
    private lateinit var againImageView: ImageView
    private lateinit var againError: TextView
    private lateinit var againClear: ImageView
    private lateinit var overButton: Button
    private lateinit var back:ImageView
    private var oldNotNull = false
    private var newNotNull = false
    private var againNotNull = false
    private var oldCanSee = false
    private var newCanSee = false
    private var againCanSee = false
    private var account = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        account = intent.getStringExtra("account")?:"null"
        initBinding()
        StatusBarUtil.setRootViewFitsSystemWindows(this, true)
        StatusBarUtil.setStatusBarDarkTheme(this, true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setStatusBarColor(this, this.getColor(R.color.bg_ffffff))
        }
        canSlideFinish(true)
        initView()
        initEditText()
        initCanSeePasswordListener()
        initClear()
        initButton()
        initBack()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        binding.apply {
            oldEdit = changePasswordOldEdittext
            oldImageView = changePasswordOldImage
            oldError = changePasswordOldError
            oldClear = changePasswordOldClear
            newEdit = changePasswordNewEdittext
            newImageView = changePasswordNewImage
            newError = changePasswordNewError
            newClear = changePasswordNewClear
            againEdit = changePasswordAgainEdittext
            againImageView = changePasswordAgainImage
            againError = changePasswordAgainError
            againClear = changePasswordAgainClear
            overButton = changePasswordButton
            back =  changePasswordBack
        }
    }
    private fun initBack(){
        back.setOnClickListener {
            this.finish()
        }
    }

    /**
     * 初始化Edittext @link[oldEdit] [newEdit] [againEdit]
     * 判断Edittext是否输入 @link[oldNotNull] [newNotNull] [againNotNull] 以此
     * 来判断@link[overButton] 是否可点击
     *
     * 是否显示clear @[oldClear] [newClear] [againClear]
     * 调用 @link[ChangePasswordUtil.checkNewPassword] 显示输入的反馈结果
     */
    private fun initEditText() {
        oldEdit.addTextChangedListener(EditTextTextChangeListener({
            if (it.isEmpty()) {
                oldClear.visibility = View.GONE
                oldNotNull = false
            } else {
                oldClear.visibility = View.VISIBLE
                oldNotNull = true
            }
            canClick()
        }, { s: CharSequence, i: Int, i1: Int, i2: Int ->
            oldError.text = ""
        }))
        newEdit.addTextChangedListener(EditTextTextChangeListener({
            if (it.isEmpty()) {
                newClear.visibility = View.GONE
                newNotNull = false
            } else {
                newClear.visibility = View.VISIBLE
                newNotNull = true
            }
            canClick()
            ChangePasswordUtil.checkNewPassword(it.toString()) { state ->
                changeNewError(state)
            }
        }, { s: CharSequence, i: Int, i1: Int, i2: Int ->
            newError.text = ""
        }))
        againEdit.addTextChangedListener(EditTextTextChangeListener({
            if (it.isEmpty()) {
                againClear.visibility = View.GONE
                againNotNull = false
            } else {
                againClear.visibility = View.VISIBLE
                againNotNull = true
            }
            canClick()
        }, { s: CharSequence, i: Int, i1: Int, i2: Int ->
            againError.text = ""
        }))
    }

    /**
     * 初始化眼睛图标的点击事件
     * 点击@link [oldImageView] [newImageView] [againImageView] 显示和隐藏密码
     */
    private fun initCanSeePasswordListener() {
        oldImageView.setOnClickListener {
            if (!oldCanSee) {
                oldCanSee = true
                oldEdit.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                oldCanSee = false
                oldEdit.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
        newImageView.setOnClickListener {
            if (!newCanSee) {
                newCanSee = true
                newEdit.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                newCanSee = false
                newEdit.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
        againImageView.setOnClickListener {
            if (!againCanSee) {
                againCanSee = true
                againEdit.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                againCanSee = false
                againEdit.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }

    /**
     * 判断@link [overButton] 是否可点击
     */
    private fun canClick() {
        if (oldNotNull && newNotNull && againNotNull) {
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

    /**
     * 设置 @link [overButton] 点击事件
     * 再次判断各个输入是否符合规范
     */
    private fun initButton() {
        var old:String
        var new:String
        var again:String
        overButton.setOnClickListener {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                overButton.background = this.resources.getDrawable(R.drawable.shape_bt_click, null)
//            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                overButton.background = this.resources.getDrawable(R.drawable.shape_bt_click)
//            }
            Log.d("aaa", "aaa")
            old = oldEdit.text.toString()
            new = newEdit.text.toString()
            again = againEdit.text.toString()
            ChangePasswordUtil.checkChangePassword(account,old,new,again){

            }
        }
    }

    /**
     * 初始化 clear的点击事件 @link[oldClear] [newClear] [againClear]
     * 点击清除EditText 中的内容
     */
    private fun initClear() {
        val emptyString = SpannableStringBuilder("")
        oldClear.setOnClickListener {
            oldEdit.text = emptyString
        }
        newClear.setOnClickListener {
            newEdit.text = emptyString
        }
        againClear.setOnClickListener {
            againEdit.text = emptyString
        }
    }

    /**
     * 输入新密码的反馈结果
     */
    private fun changeNewError(state: Int) {
        when (state) {
            NEW_NO6 -> newError.text = "至少6位密码"
            NEW_ERROR -> newError.text = "需包含数字，字母，符号中至少2种元素"
            NEW_NULL -> newError.text = "密码不能为空"
            NEW_TOO_LONG -> newError.text = "至多十六位"
            NEW_OK -> newError.text = ""
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_change_password
    }
}

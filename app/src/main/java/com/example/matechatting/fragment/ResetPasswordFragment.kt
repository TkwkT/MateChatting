package com.example.matechatting.fragment


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil

import com.example.matechatting.R
import com.example.matechatting.activity.LoginActivity
import com.example.matechatting.databinding.FragmentResetPasswordBinding
import com.example.matechatting.listener.EditTextTextChangeListener

class ResetPasswordFragment : BaseFragment() {
    private lateinit var binding: FragmentResetPasswordBinding

    private lateinit var newEdit: EditText
    private lateinit var newClear: ImageView
    private lateinit var newError: TextView
    private lateinit var newSee: ImageView
    private lateinit var againEdit: EditText
    private lateinit var againClear: ImageView
    private lateinit var againSee: ImageView
    private lateinit var againError: TextView
    private lateinit var overButton: Button
    private var newNotNull = false
    private var againNotNull = false
    private var newCanSee = false
    private var againCanSee = false
    private var account = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reset_password, container, false)
        init()
        initEdit()
        initClear()
        initCanSeePasswordListener()
//        initOverButton()
        return binding.root
    }


    override fun initView() {
        binding.apply {
            newEdit = resetNewEdit
            newClear = resetNewClear
            newError = resetNewError
            newSee = resetNewImage
            againEdit = resetAgainEdit
            againClear = resetAgainClear
            againSee = resetAgainImage
            againError = resetAgainError
            overButton = resetButton
        }
    }

    private fun initEdit() {
        newEdit.addTextChangedListener(
            EditTextTextChangeListener({
                if (it.isNotEmpty()) {
                    newClear.visibility = View.VISIBLE
                    newNotNull = true
                } else {
                    newClear.visibility = View.GONE
                    newNotNull = false
                }
                canClick()
            }, { s: CharSequence, i: Int, i1: Int, i2: Int ->
                newError.text = ""
            })
        )
        againEdit.addTextChangedListener(EditTextTextChangeListener({
            if (it.isNotEmpty()) {
                againClear.visibility = View.VISIBLE
                againNotNull = true
            } else {
                againClear.visibility = View.GONE
                againNotNull = false
            }
            canClick()
        }, { s: CharSequence, i: Int, i1: Int, i2: Int ->
            againError.text = ""
        }))
    }

    private fun canClick() {
        if (newNotNull && againNotNull) {
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

    private fun initClear() {
        val emptyString = SpannableStringBuilder("")
        newClear.setOnClickListener {
            newEdit.text = emptyString
        }
        againClear.setOnClickListener {
            againEdit.text = emptyString
        }
    }

    private fun initCanSeePasswordListener() {
        newSee.setOnClickListener {
            if (!newCanSee) {
                newCanSee = true
                newEdit.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                newCanSee = false
                newEdit.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
        againSee.setOnClickListener {
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
     * 设置@link[overButton]的点击事件
     * 未实现
     */
//    private fun initOverButton() {
//        val intent = Intent(this, LoginActivity::class.java)
//        overButton.setOnClickListener {
//            transferLoginActivity(intent)
//        }
//        back.setOnClickListener {
//            transferLoginActivity(intent)
//        }
//    }


}

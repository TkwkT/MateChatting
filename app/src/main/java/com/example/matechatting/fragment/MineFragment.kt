package com.example.matechatting.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.matechatting.activity.ChangePasswordActivity
import com.example.matechatting.activity.LoginActivity
import com.example.matechatting.R
import com.example.matechatting.activity.BindPhoneActivity
import com.example.matechatting.activity.MyinfoActivity
import com.example.matechatting.databinding.FragmentMineBinding

class MineFragment : BaseFragment() {
    private lateinit var binding: FragmentMineBinding
    private lateinit var headLayout:ConstraintLayout
    private lateinit var headImage:ImageView
    private lateinit var headName:TextView
    private lateinit var headText:TextView
    private lateinit var functionLayout:ConstraintLayout
    private lateinit var changePassword:ConstraintLayout
    private lateinit var myInformation:ConstraintLayout
    private lateinit var bindPhone:ConstraintLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false)
        //StatusBarUtil.setStatusBarColor(requireActivity(), Color.parseColor("#5879ff"))
        init()
        initMyInfoLayout()
        return binding.root
    }
    override fun initView() {
        binding.apply {
            headLayout = mineHeadLayout
            headImage = mineHeadImage
            headName = mineHeadName
            headText = mineHeadText
            functionLayout = mineFunctionLayout
            changePassword = mineChangePasswordLayout
            myInformation = mineMyInformationLayout
            bindPhone = mineBindPhoneLayout
        }
    }

    override fun initLogin() {
        headImage.isEnabled = true
        changePassword.isEnabled = true
        myInformation.isEnabled = true
        bindPhone.isEnabled = true
        val intentToChangePassword = Intent(requireActivity(), ChangePasswordActivity::class.java)
        changePassword.setOnClickListener {
            transferLoginActivity(intentToChangePassword)
        }
        val intentToMyInfo = Intent(requireActivity(),MyinfoActivity::class.java)
        myInformation.setOnClickListener {
            transferLoginActivity(intentToMyInfo)
        }
        val intentToBindPhone = Intent(requireActivity(),BindPhoneActivity::class.java)
        bindPhone.setOnClickListener {
            transferLoginActivity(intentToBindPhone)
        }
        /**
         * 美工查看用
         */
        val intentToLogin = Intent(requireActivity(), LoginActivity::class.java)
        headLayout.setOnClickListener {
            transferLoginActivity(intentToLogin)
        }
    }

    override fun initNotLogin() {
        headName.text = "未登录"
        headText.text = "快乐生活每一天"
        headImage.isEnabled = false
        changePassword.isEnabled = false
        myInformation.isEnabled = false
        bindPhone.isEnabled = false
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        headLayout.setOnClickListener {
            transferLoginActivity(intent)
        }
        functionLayout.setOnClickListener {
            transferLoginActivity(intent)
        }
    }



    override fun onResume() {
        super.onResume()
        init()
    }

    private fun initMyInfoLayout() {
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        myInformation.setOnClickListener {
            transferLoginActivity(intent)
        }
    }

    private fun transferLoginActivity(intent:Intent) {
        requireActivity().startActivityFromFragment(this, intent, 0x123)
    }


}

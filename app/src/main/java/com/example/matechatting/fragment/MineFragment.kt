package com.example.matechatting.fragment


import android.Manifest
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.matechatting.R
import com.example.matechatting.activity.BindPhoneActivity
import com.example.matechatting.activity.ChangePasswordActivity
import com.example.matechatting.activity.LoginActivity
import com.example.matechatting.activity.MyinfoActivity
import com.example.matechatting.databinding.FragmentMineBinding
import com.example.matechatting.utils.functionutil.ChooseHeadImageUtil

class MineFragment : PermissionFragment() {
    private lateinit var binding: FragmentMineBinding
    private lateinit var headLayout: ConstraintLayout
    private lateinit var headImage: ImageView
    private lateinit var headName: TextView
    private lateinit var headText: TextView
    private lateinit var functionLayout: ConstraintLayout
    private lateinit var changePassword: ConstraintLayout
    private lateinit var myInformation: ConstraintLayout
    private lateinit var bindPhone: ConstraintLayout
    private lateinit var chooseHeadImageCallback:()->Unit



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false)
        init()
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

    override fun onResume() {
        super.onResume()
        init()
    }

    private fun transferLoginActivity(intent: Intent) {
        requireActivity().startActivityFromFragment(this, intent, 0x123)
    }

    /**
     * 处理登陆后的点击事件以及登陆后的逻辑
     */
    override fun initLogin() {
        headImage.isEnabled = true
        changePassword.isEnabled = true
        myInformation.isEnabled = true
        bindPhone.isEnabled = true
        val intentToChangePassword = Intent(requireActivity(), ChangePasswordActivity::class.java)
        changePassword.setOnClickListener {
            transferLoginActivity(intentToChangePassword)
        }
        val intentToMyInfo = Intent(requireActivity(), MyinfoActivity::class.java)
        myInformation.setOnClickListener {
            transferLoginActivity(intentToMyInfo)
        }
        val intentToBindPhone = Intent(requireActivity(), BindPhoneActivity::class.java)
        bindPhone.setOnClickListener {
            transferLoginActivity(intentToBindPhone)
        }
        val chooseHeadImageUtil = ChooseHeadImageUtil()
        initChooseHeadImageCallback()
        headImage.setOnClickListener {
            chooseHeadImageUtil.initChooseHeadImageDialog(requireContext(),chooseHeadImageCallback)
        }

        /**
         * 美工查看用
         */
        val intentToLogin = Intent(requireActivity(), LoginActivity::class.java)
        headLayout.setOnClickListener {
            transferLoginActivity(intentToLogin)
        }
    }

    @SuppressLint("InlinedApi")
    private fun initChooseHeadImageCallback(){
        chooseHeadImageCallback = {
            checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    override fun showDialogTipUserGoToAppSetting(permission: String) {
        super.showDialogTipUserGoToAppSetting(permission)
        if (permission == Manifest.permission.READ_EXTERNAL_STORAGE) {
            dialog = AlertDialog.Builder(requireContext())
                .setTitle("获取访问相册权限")
                .setMessage("请在-应用设置-权限-中，允许应用使用访问相册权限来选择")
                .setPositiveButton("立即开启") { dialog, which ->
                    gotoAppSetting()
                }
                .setNegativeButton("取消") { dialog: DialogInterface, i: Int ->
                }.setCancelable(false).show()
        }
    }



    /**
     * 处理未登陆时的点击事件以及未登陆时的逻辑
     */
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

}

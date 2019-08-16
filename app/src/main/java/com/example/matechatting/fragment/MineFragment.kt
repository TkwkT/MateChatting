package com.example.matechatting.fragment


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.matechatting.R
import com.example.matechatting.activity.*
import com.example.matechatting.constvalue.ALBUM_REQUEST_CODE
import com.example.matechatting.constvalue.CLIP_REQUEST_CODE
import com.example.matechatting.constvalue.LOGIN_REQUEST_CODE
import com.example.matechatting.databinding.FragmentMineBinding
import com.example.matechatting.utils.functionutil.AccessPermissionDialogUtil
import com.example.matechatting.utils.functionutil.ChooseHeadImageDialogUtil

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
    private lateinit var chooseHeadImageCallback: () -> Unit


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

    private fun transferActivity(intent: Intent, requestCode: Int) {
        requireActivity().startActivityFromFragment(this, intent, requestCode)
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
            transferActivity(intentToChangePassword, 0x999)
        }
        val intentToMyInfo = Intent(requireActivity(), MyinfoActivity::class.java)
        myInformation.setOnClickListener {
            transferActivity(intentToMyInfo, 0x999)
        }
        val intentToBindPhone = Intent(requireActivity(), BindPhoneActivity::class.java)
        bindPhone.setOnClickListener {
            transferActivity(intentToBindPhone, 0x999)
        }
        val chooseHeadImageUtil = ChooseHeadImageDialogUtil()
        initChooseHeadImageCallback()
        headImage.setOnClickListener {
            chooseHeadImageUtil.initChooseHeadImageDialog(requireContext(), chooseHeadImageCallback)
        }
    }

    @SuppressLint("InlinedApi")
    private fun initChooseHeadImageCallback() {
        chooseHeadImageCallback = {
            checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == ALBUM_REQUEST_CODE && data != null) {
            val uri = data.data
            if (uri != null) {
                Log.d("bbb", "获得数据：" + uri.path)
                Glide.with(this).load(uri).into(headImage)
            }
        }
    }

    override fun showDialogTipUserGoToAppSetting(permission: String) {
        if (permission == Manifest.permission.READ_EXTERNAL_STORAGE) {
            val accessPermissionDialogUtil = AccessPermissionDialogUtil()
            accessPermissionDialogUtil.initAccessPermissionDialog(requireContext()) {
                gotoAppSetting()
            }
        }
    }

    override fun doOnGetPermission() {
        val intent = Intent(requireActivity(), AlbumActivity::class.java)
        transferActivity(intent, ALBUM_REQUEST_CODE)

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
            transferActivity(intent, LOGIN_REQUEST_CODE)
        }
        functionLayout.setOnClickListener {
            transferActivity(intent, LOGIN_REQUEST_CODE)
        }
    }

}

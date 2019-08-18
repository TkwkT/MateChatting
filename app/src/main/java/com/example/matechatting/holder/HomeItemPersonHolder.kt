package com.example.matechatting.holder

import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.example.matechatting.InfoDetailActivity
import com.example.matechatting.activity.LoginActivity
import com.example.matechatting.bean.HomeItemBean
import com.example.matechatting.constvalue.LOGIN_REQUEST_CODE
import com.example.matechatting.databinding.ItemHomePersonBinding
import com.example.matechatting.fragment.BaseFragment

class HomeItemPersonHolder(private val binding: ItemHomePersonBinding) : BaseHolder(binding) {
    private lateinit var callbackPersonButton: () -> Unit
    private lateinit var callbackPersonLayout: () -> Unit

    override fun <T> bind(t: T) {
        if (t is HomeItemSource) {
            val homeItemBean = t.homeItemBean
            binding.apply {
                itemPersonName.text = homeItemBean.name
                itemPersonGraduate.text = homeItemBean.graduation
                itemPersonMajor.text = homeItemBean.direction
                itemPersonCompany.text = homeItemBean.company
                if (!homeItemBean.headImage.isNullOrEmpty()) {
                    Glide.with(context).load(homeItemBean.headImage).into(itemPersonHead)
                }
            }
        }
    }

    fun getLayout(): LinearLayout {
        return binding.itemPersonLayout
    }

    fun getButton(): Button {
        return binding.itemPersonChangeButton
    }

//    private fun init(callback: (Intent, Int) -> Unit) {
//        val isLogin = BaseFragment.isLogin
//        if (isLogin) {
//            initLogin(callback)
//        } else {
//            initNotLogin(callback)
//        }
//    }
//
//    private fun initLogin(callback: (Intent, Int) -> Unit) {
//        callbackPersonButton = {}
//        callbackPersonLayout = {
//            val intent = Intent(context, InfoDetailActivity::class.java)
//            callback(intent, 0x999)
//        }
//    }
//
//    private fun initNotLogin(callback: (Intent, Int) -> Unit) {
//        val intent = Intent(context, LoginActivity::class.java)
//        callbackPersonButton = {
//            callback(intent, LOGIN_REQUEST_CODE)
//        }
//        callbackPersonLayout = {
//            callback(intent, LOGIN_REQUEST_CODE)
//        }
//    }


}

data class HomeItemSource(
    val homeItemBean: HomeItemBean
)
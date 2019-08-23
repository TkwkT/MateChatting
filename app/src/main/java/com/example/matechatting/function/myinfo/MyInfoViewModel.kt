package com.example.matechatting.function.myinfo

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.matechatting.bean.UserBean

class MyInfoViewModel(private val repository: MyInfoRepository) : ViewModel() {
    val myInfoName = ObservableField<String>("未填写")
    val myInfoMajor = ObservableField<String>("未填写")
    val myInfoGraduate = ObservableField<String>("未填写")
    val myInfoCompany = ObservableField<String>("未填写")
    val myInfoJob = ObservableField<String>("未填写")
    val myInfoDirection = ObservableField<String>("未填写")
    val myInfoQQ = ObservableField<String>("未填写")
    val myInfoWeixin = ObservableField<String>("未填写")
    val myInfoEmile = ObservableField<String>("未填写")
    val myInfoCity = ObservableField<String>("未填写")
    val myInfoSlogan = ObservableField<String>("未填写")
    val myInfoString = "未填写"
    val myInfoDefailtSlogan = "快乐生活每一天"

    fun getMyInfo(callback: (UserBean) -> Unit) {
        repository.getMyInfo {
            it.apply {
                myInfoName.set(name)
                myInfoMajor.set(majorName)
                myInfoGraduate.set(graduation)
                myInfoCompany.set(company)
                myInfoJob.set(job)
                myInfoDirection.set(direction)
                myInfoQQ.set(qqAccount.toString())
                myInfoWeixin.set(wechatAccount)
                myInfoEmile.set(email)
                myInfoCity.set(city)
                myInfoSlogan.set(slogan)
            }
            callback(it)
        }
    }

    fun saveData(userBean: UserBean){

    }
}
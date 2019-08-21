package com.example.matechatting.function.mine

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class MineViewModel(private val repository: MineRepository) : ViewModel() {
    val mineName = ObservableField<String>("未登录")
    val mineSlogan = ObservableField<String>("快乐生活每一天")
    val defaultSlogan = "快乐生活每一天"
    val defaultName = "未登录"

    fun getMine() {
        repository.getMine {
            it.apply {
                if (name.isNullOrEmpty()){
                    mineName.set(defaultName)
                }
                mineSlogan.set(slogan)
            }
        }
    }
}
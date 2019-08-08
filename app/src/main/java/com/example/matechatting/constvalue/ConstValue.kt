package com.example.matechatting.constvalue

import com.example.matechatting.R

interface MainConstValue {

    val tabSelectedDrawableIdList: Array<Int> get() = arrayOf(
        R.drawable.main_home_selected,
        R.drawable.main_milelist_selected,
        R.drawable.main_mine_selected
    )

    val tabUnselectedDrawableList: Array<Int> get() = arrayOf(
        R.drawable.main_home_unselected,
        R.drawable.main_milelist_unselected,
        R.drawable.main_mine_unselected
    )

    val tabText: Array<String> get() = arrayOf("首页","名片夹","我的")
}
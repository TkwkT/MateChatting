package com.example.matechatting.constvalue

import com.example.matechatting.R

//从相册选择页面返回数据的请求码
const val ALBUM_REQUEST_CODE = 0x111
//从裁剪页面返回给选择页面的请求码
const val CLIP_REQUEST_CODE = 0x112
//登陆界面返回登陆状态的请求码
const val LOGIN_REQUEST_CODE = 0x120


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
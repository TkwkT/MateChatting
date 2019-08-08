package com.example.matechatting.utils

import android.content.Context
import android.widget.Toast

fun setToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


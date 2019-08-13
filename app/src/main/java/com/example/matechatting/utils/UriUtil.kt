package com.example.matechatting.utils

import android.provider.MediaStore.Images.ImageColumns
import androidx.room.util.CursorUtil.getColumnIndex
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore.Images
import android.util.Log
import androidx.room.util.CursorUtil.getColumnIndex






object UriUtil {

    fun getRealFilePath(context: Context, uri: Uri?): String? {
        if (null == uri) return null
        val scheme = uri!!.scheme
        var data: String? = null
        if (scheme == null)
            data = uri!!.getPath()
        else if (ContentResolver.SCHEME_FILE == scheme) {
            data = uri!!.getPath()
        } else if (ContentResolver.SCHEME_CONTENT == scheme) {
            val cursor = context.contentResolver.query(uri, arrayOf<String>(ImageColumns.DATA), null, null, null)
            if (null != cursor) {
                if (cursor!!.moveToFirst()) {
                    val index = cursor!!.getColumnIndex(ImageColumns.DATA)
                    if (index > -1) {
                        data = cursor!!.getString(index)
                    }
                }
                cursor!!.close()
            }
        }
        return data
    }

    fun getUriFromRealPath(context: Context,path:String){

    }

}
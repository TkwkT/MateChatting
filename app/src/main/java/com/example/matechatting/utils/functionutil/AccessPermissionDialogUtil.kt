package com.example.matechatting.utils.functionutil

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.matechatting.R

class AccessPermissionDialogUtil {
    lateinit var dialog: Dialog
    private lateinit var view: View
    private lateinit var accessPermissionTitle: TextView
    private lateinit var accessPermissionMessage: TextView
    private lateinit var accessPermissionCancel: TextView
    private lateinit var accessPermissionJump: TextView


    /**
     * 1
     */
    fun initAccessPermissionDialog(context: Context, callback: () -> Unit) {
        dialog = Dialog(context, R.style.AccessPermissionDialog)
        view = LayoutInflater.from(context).inflate(R.layout.temp_access_permission_dialog, null)
        accessPermissionTitle = view.findViewById(R.id.access_title)
        accessPermissionMessage = view.findViewById(R.id.access_message)
        accessPermissionCancel = view.findViewById(R.id.access_cancel)
        accessPermissionJump = view.findViewById(R.id.access_jump)
        dialog.setContentView(view)
        val dialogWindow = dialog.window
        val windowManager = dialogWindow?.windowManager
        val display = windowManager?.defaultDisplay
        val layoutParams = dialogWindow?.attributes
        val point = Point()
        display?.getSize(point)
        layoutParams?.width = point.x
        dialogWindow?.setGravity(Gravity.CENTER)
        dialogWindow?.attributes = layoutParams
        Log.d("aaa","isShowing"+isShowing.toString())
        if (!isShowing){
            isShowing = true
            dialog.show()
        }
        initCancel()
        initJump(callback)
    }

    /**
     * 2
     */
    fun setTitle(title:String){
        accessPermissionTitle.text = title
    }

    /**
     * 3
     */
    fun setMessage(message:String){
        accessPermissionMessage.text = message
    }

    private fun initCancel(){
        accessPermissionCancel.setOnClickListener {
            dialog.dismiss()
            isShowing = false
        }
    }

    private fun initJump(callback:()->Unit){
        accessPermissionJump.setOnClickListener {
            callback()
        }
    }

    companion object{
        private var isShowing = false
    }
}
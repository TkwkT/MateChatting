package com.example.matechatting.fragment

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

abstract class PermissionFragment : BaseFragment() {
    private val code = 0x1234
    private var currentPermission = ""
    private var dialog: AlertDialog? = null

    open fun setDialog(dialog: AlertDialog) {
        this.dialog = dialog
    }

    protected fun addAndCheckPermission(permission: String) {
        checkPermission(permission)
    }

    protected fun addAndCheckPermissions(permissions: Array<out String>) {
        for (i: Int in 0 until permissions.size) {
            checkPermission(permissions[i])
        }
    }

    private fun checkPermission(permission: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val i = ContextCompat.checkSelfPermission(requireActivity(), permission)
            if (i != PackageManager.PERMISSION_GRANTED) {
                getPermission(permission)
            }
        }
    }

    private fun getPermission(permission: String) {
        currentPermission = permission
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), code)
    }

    open fun showDialogTipUserGoToAppSetting(permission: String) {

    }

    protected fun gotoAppSetting() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", requireContext().packageName, null)
        intent.data = uri
        startActivityForResult(intent, code)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (requestCode == code && grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                val b = shouldShowRequestPermissionRationale(permissions[0])
                Log.d("aaaa", b.toString())
                if (!b) {
                    Log.d("aaaa", "run")
                    showDialogTipUserGoToAppSetting(permissions[0])
                } else {
                    currentPermission = ""
                }
            } else {
                currentPermission = ""
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (requestCode == code) {
                val i = ContextCompat.checkSelfPermission(requireContext(), currentPermission)
                if (i != PackageManager.PERMISSION_GRANTED) {
                    showDialogTipUserGoToAppSetting(currentPermission)
                } else {
                    if (dialog != null && dialog!!.isShowing) {
                        dialog!!.dismiss()
                    }
                    currentPermission = ""
                }
            }
        }
    }
}
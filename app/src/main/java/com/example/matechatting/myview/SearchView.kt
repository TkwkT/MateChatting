//package com.example.matechatting.myview
//
//import android.content.Context
//import android.graphics.Paint
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.view.View
//import android.widget.Button
//import android.widget.EditText
//import android.widget.ImageView
//import androidx.constraintlayout.widget.ConstraintLayout
//import com.example.matechatting.R
//
//class SearchView :ConstraintLayout, View.OnClickListener{
//    private lateinit var editTextRight: EditText
//    private lateinit var editTextLeft: EditText
//    private lateinit var button:Button
//    private lateinit var imageViewRight:ImageView
//    private lateinit var imageViewLeft:ImageView
//
//    constructor(context: Context) : super(context) {}
//
//    constructor(mContext: Context, attributeSet: AttributeSet?) : super(mContext, attributeSet) {
//        LayoutInflater.from(mContext).inflate(R.layout.temp_search_view_layout,this)
//        initView()
//    }
//
//    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
//        context,
//        attributeSet,
//        defStyleAttr
//    ) {
//    }
//
//    private fun initView(){
//        editTextLeft = findViewById(R.id.search_view_edittext_left)
//        editTextRight = findViewById(R.id.search_view_edittext_right)
//        button = findViewById(R.id.search_view_button_search)
//        imageViewLeft = findViewById(R.id.search_view_image_left)
//        imageViewRight = findViewById(R.id.search_view_image_right)
//        editTextLeft.visibility = View.GONE
//        editTextRight.visibility = View.GONE
//        button.visibility = View.VISIBLE
//        imageViewLeft.visibility = View.GONE
//        imageViewRight.visibility = View.VISIBLE
//    }
//
//    private fun a(){
////        Log.editTextLeft.x
////        editTextLeft.y
//    }
//
//    override fun onClick(p0: View?) {
//
//    }
//
//
//}
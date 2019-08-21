package com.example.matechatting.function.album

import com.example.matechatting.R
import com.example.matechatting.base.BaseRecyclerAdapter
import com.example.matechatting.databinding.ItemAlbumImageBinding


class AlbumRecyclerAdapter(private val callback: (url: String) -> Unit) :
    BaseRecyclerAdapter<ItemAlbumImageBinding, String, AlbumHolder, AlbumSource>() {
    override fun freshData(list: List<String>) {
        dataList.addAll(list)
    }

    override fun onCreate(binding: ItemAlbumImageBinding): AlbumHolder {
        return AlbumHolder(binding)
    }

    override fun getLayoutId(): Int {
        return R.layout.item_album_image
    }

    override fun getItem(position: Int): AlbumSource {
        return AlbumSource(dataList[position])
    }

    override fun onBind(holder: AlbumHolder, position: Int) {
        holder.bind(getItem(position))
        holder.getView().setOnClickListener {
            callback(dataList[position])
        }
    }
}
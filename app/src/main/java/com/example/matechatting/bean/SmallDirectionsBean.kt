package com.example.matechatting.bean
import com.google.gson.annotations.SerializedName


data class SmallDirectionBean(
    @SerializedName("children")
    var children: List<Children>,
    @SerializedName("direction")
    var direction: Direction
) {
    data class Direction(
        @SerializedName("id")
        var id: Int
    )

    data class Children(
        @SerializedName("children")
        var children: List<Any>,
        @SerializedName("direction")
        var direction: Direction
    ) {
        data class Direction(
            @SerializedName("direction_name")
            var directionName: String,
            @SerializedName("id")
            var id: Int
        )
    }
}
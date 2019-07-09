package com.ppizil.renderactivityitem_from_recyclerview

import java.util.ArrayList

class test {

    private var items: MutableList<ItemEntity>? = null

    fun aaa() {
        items = ArrayList()
        for (i in 0..19) {
            val itemEntity = ItemEntity()
            itemEntity.contents = ""
            items!!.add(itemEntity)
        }
    }
}

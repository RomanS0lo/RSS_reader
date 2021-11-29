package com.dts.retrofit.data

import org.simpleframework.xml.Element

data class UkrChannel(
    @field:Element(name = "image", required = false)
    var image: Image? = null,
    override var description: String? = null,
    override var items: List<Item>? = null
) : Channel(description, items)

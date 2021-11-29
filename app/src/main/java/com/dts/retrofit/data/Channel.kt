package com.dts.retrofit.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
open class Channel(

    @field:Element(name = "description", required = false)
    open var description: String? = null,

    @field:ElementList(name = "entry", inline = true)
    open var items: List<Item>? = null
)

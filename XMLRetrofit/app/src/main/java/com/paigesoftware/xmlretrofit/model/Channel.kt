package com.paigesoftware.xmlretrofit.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
class Channel @JvmOverloads constructor(
//    @field:Element(name = "title")
//    var title: String = ""

    @field:ElementList(entry = "item", inline = true)
    var itemList: List<Item>? = null
)
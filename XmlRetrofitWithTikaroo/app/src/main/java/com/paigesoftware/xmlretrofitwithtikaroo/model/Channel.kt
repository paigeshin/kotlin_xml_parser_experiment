package com.paigesoftware.xmlretrofitwithtikaroo.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml
class Channel {

    @Element(name = "item")
    var itemList: ArrayList<Item>? = null

}
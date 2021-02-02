package com.paigesoftware.xmlretrofitwithtikaroo.model

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
class Item(
    @PropertyElement(name = "word")
    var word: String? = null,
    @PropertyElement(name = "origin")
    var origin: String? = null,
    @PropertyElement(name = "link")
    var link: String? = null,
//    @Element(name = "sense")
//    var sense: Sense? = null

)



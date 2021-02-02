package com.paigesoftware.xmlretrofitwithtikaroo.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class Sense (
    @PropertyElement(name = "definition")
    var definition: String? = null,
    @Element(name = "translation")
    var translation: Translation? = null
)
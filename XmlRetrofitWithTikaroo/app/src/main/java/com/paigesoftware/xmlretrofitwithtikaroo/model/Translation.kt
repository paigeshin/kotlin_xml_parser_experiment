package com.paigesoftware.xmlretrofitwithtikaroo.model

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "translation")
data class Translation (
    @PropertyElement(name = "trans_lang")
    var trans_lang: String? = null,
    @PropertyElement(name = "trans_word")
    var trans_word : String? = null,
    @PropertyElement(name = "trans_dfn")
    var trans_dfn: String? = null
)
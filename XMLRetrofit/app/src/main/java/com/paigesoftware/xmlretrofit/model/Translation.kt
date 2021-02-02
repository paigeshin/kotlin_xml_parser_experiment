package com.paigesoftware.xmlretrofit.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "translation", strict = false)
data class Translation @JvmOverloads constructor(
    @field:Element(name = "trans_lang")
    var trans_lang: String? = null,
    @field:Element(name = "trans_word")
    var trans_word : String? = null,
    @field:Element(name = "trans_dfn")
    var trans_dfn: String? = null
)
package com.paigesoftware.xmlretrofit.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "sense", strict = false)
data class Sense @JvmOverloads constructor(
    @field:Element(name = "definition")
    var definition: String? = null,
    @field:Element(name = "translation")
    var translation: Translation? = null
)
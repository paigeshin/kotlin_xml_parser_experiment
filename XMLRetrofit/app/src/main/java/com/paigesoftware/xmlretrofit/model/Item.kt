package com.paigesoftware.xmlretrofit.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class Item @JvmOverloads constructor(
    @field:Element(name = "word")
    var word: String? = null,

    @field:Element(name = "origin")
    var origin: String? = null,

    @field:Element(name = "link")
    var link: String? = null,

    @field:Element(name = "sense", type = Sense::class)
    var sense: Sense? = null
)
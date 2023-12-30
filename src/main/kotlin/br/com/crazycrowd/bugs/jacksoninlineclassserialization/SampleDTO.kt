package br.com.crazycrowd.bugs.jacksoninlineclassserialization

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

object SampleDTO {

  data class WithInlineClass(
      @JsonProperty("testField1") val otherName1: SampleInline,
      @JsonProperty("testField2") val otherName2: OtherClass,
  )

  data class WithoutInlineClass(
      @JsonProperty("testField1") val otherName1: BigDecimal,
      @JsonProperty("testField2") val otherName2: OtherClass,
  )

  data class OtherClass(
      @JsonProperty("foo1") val bar1: Int,
      @JsonProperty("foo2") val bar2: String,
  )
}

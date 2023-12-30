package br.com.crazycrowd.bugs.jacksoninlineclassserialization

import java.math.BigDecimal
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.boot.test.json.JsonContent

@JsonTest
class SampleDTOTest
@Autowired
constructor(
    private val withInlineTester: JacksonTester<SampleDTO.WithInlineClass>,
    private val withoutInlineTester: JacksonTester<SampleDTO.WithoutInlineClass>,
) {

  private val testOtherClass = SampleDTO.OtherClass(bar1 = 1, bar2 = "2")

  @Test
  fun whenUsingDTOWithInline_ItDoesNotRespectJsonPropertyAnnotation() {
    val dto =
        SampleDTO.WithInlineClass(
            otherName1 = SampleInline(BigDecimal.ONE), otherName2 = testOtherClass)

    val json: JsonContent<SampleDTO.WithInlineClass> = withInlineTester.write(dto)
    println(json.json)

    assertThat(json).extractingJsonPathNumberValue("$.testField1").isEqualTo(1)
    assertThat(json).extractingJsonPathNumberValue("$.testField2.foo1").isEqualTo(1)
    assertThat(json).extractingJsonPathStringValue("$.testField2.foo2").isEqualTo("2")
  }

  @Test
  fun whenUsingDTOWithoutInline_ItRespectsJsonPropertyAnnotation() {
    val dto = SampleDTO.WithoutInlineClass(otherName1 = BigDecimal.ONE, otherName2 = testOtherClass)

    val json: JsonContent<SampleDTO.WithoutInlineClass> = withoutInlineTester.write(dto)
    println(json.json)

    assertThat(json).extractingJsonPathNumberValue("$.testField1").isEqualTo(1)
    assertThat(json).extractingJsonPathNumberValue("$.testField2.foo1").isEqualTo(1)
    assertThat(json).extractingJsonPathStringValue("$.testField2.foo2").isEqualTo("2")
  }
}

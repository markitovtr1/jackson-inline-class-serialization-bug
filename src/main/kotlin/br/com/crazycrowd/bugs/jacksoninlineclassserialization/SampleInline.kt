package br.com.crazycrowd.bugs.jacksoninlineclassserialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import java.math.BigDecimal

@JvmInline
@JsonSerialize(using = SampleInline.Serializer::class)
@JsonDeserialize(using = SampleInline.Deserializer::class)
value class SampleInline(val value: BigDecimal) {
  class Serializer : JsonSerializer<SampleInline>() {
    override fun handledType() = SampleInline::class.java

    override fun serialize(
        value: SampleInline,
        gen: JsonGenerator,
        serializers: SerializerProvider
    ) {
      gen.writeNumber(value.value)
    }
  }

  class Deserializer : JsonDeserializer<SampleInline>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): SampleInline {
      return SampleInline(BigDecimal(p.valueAsString))
    }
  }

  override fun toString(): String = value.toString()
}

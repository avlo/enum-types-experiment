package com.prosilion.nostr;

import com.prosilion.nostr.event.Encoder;
import com.prosilion.nostr.event.GenericEventRecord;
import com.prosilion.nostr.event.GenericTag;
import com.prosilion.nostr.event.IDecoder;
import com.prosilion.nostr.event.PublicKey;
import com.prosilion.nostr.event.Signature;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JsonTest
@ActiveProfiles("test")
public class GenericEventRecordGenericTagDeserializerTest {
  @Autowired
  JacksonTester<GenericEventRecord> tester;
  GenericEventRecord genericEventRecord;

  public GenericEventRecordGenericTagDeserializerTest(@Value("classpath:generic_tag.json") Resource resourceJson) throws IOException {
    implicitTestDeserializer(resourceJson);
  }

  private void implicitTestDeserializer(Resource resource) throws IOException {
//    deserializer/unmarshall == string/json -> object    
    this.genericEventRecord = Encoder.ENCODER_MAPPED_AFTERBURNER.readValue(
        new String(
            Files.readAllBytes(
                resource.getFile().toPath())),
        GenericEventRecord.class);
  }

  @Test
  void testSerializer() throws IOException {
    GenericEventRecord methodInstanceGenericEventRecord = new GenericEventRecord(
        "5f66a36101d3d152c6270e18f5622d1f8bce4ac5da9ab62d7c3cc0006e590001",
        new PublicKey("bbbd79f81439ff794cf5ac5f7bff9121e257f399829e472c7a14d3e86fe76984"),
        1111111111111L,
        Kind.valueOf(31923),
        List.of(
            GenericTag.create("generic-tag", "superconductor_subscriber_id-0")),
        "matching kind, author, identity-tag filter test",
        Signature.fromString("86f25c161fec51b9e441bdb2c09095d5f8b92fdce66cb80d9ef09fad6ce53eaa14c5e16787c42f5404905536e43ebec0e463aee819378a4acbe412c533e60546"));


    String expectedJson = """
        [
          "EVENT",
          {
            "content": "matching kind, author, identity-tag filter test",
            "id": "5f66a36101d3d152c6270e18f5622d1f8bce4ac5da9ab62d7c3cc0006e590001",
            "kind": 31923,
            "created_at": 1111111111111,
            "pubkey": "bbbd79f81439ff794cf5ac5f7bff9121e257f399829e472c7a14d3e86fe76984",
            "tags": [
              [
                "generic-tag",
                "superconductor_subscriber_id-0"
              ]
            ],
            "sig": "86f25c161fec51b9e441bdb2c09095d5f8b92fdce66cb80d9ef09fad6ce53eaa14c5e16787c42f5404905536e43ebec0e463aee819378a4acbe412c533e60546"
          }
        ]
        """;

    assertEquals(genericEventRecord, methodInstanceGenericEventRecord);

// serializer/marshall == object -> string/json
// spring JsonTester confirming valid json created when serializing genericEventRecord     
    JsonContent<GenericEventRecord> write = tester.write(methodInstanceGenericEventRecord);
    assertThat(write).isEqualToJson(expectedJson);

// using spring JsonText to further validate IDecoder.I_DECODER_MAPPER_AFTERBURNER serialization works as expected 
    String afterBurnerDecoded = IDecoder.I_DECODER_MAPPER_AFTERBURNER.writeValueAsString(methodInstanceGenericEventRecord);
    assertThat(write).isEqualToJson(afterBurnerDecoded);
//    JsonComparator.isEquivalentJson(jsonContent, afterBurnerDecoded);
  }
}

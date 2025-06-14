package com.prosilion.nostr.event;

import com.prosilion.nostr.Kind;
import java.util.List;
import org.springframework.lang.NonNull;

public interface BaseEventIF {
  GenericEventRecord getGenericEventRecord();
  String getId();
  PublicKey getPublicKey();
  Long getCreatedAt();
  Kind getKind();
  List<BaseTag> getTags();
  String getContent();
  String getSignature();
}

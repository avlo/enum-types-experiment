package com.prosilion.enumtypesexperiment.event;

import com.prosilion.enumtypesexperiment.Kind;
import com.prosilion.enumtypesexperiment.NostrException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.lang.NonNull;

//TODO: remove this impl, doesn't do anything unique, all superclasses can call right through to GenericEventEntity
public abstract class NIP01EventRxR implements NIP01EventRxRIF {
  private final GenericEventRecord genericEventRecord;

  public NIP01EventRxR(@NonNull Identity identity, @NonNull Kind kind) throws NostrException, NoSuchAlgorithmException {
    this.genericEventRecord = GenericEventEntityFactory.createInstance(identity, kind, List.of(), "");
  }

  public NIP01EventRxR(@NonNull Identity identity, @NonNull Kind kind, @NonNull String content) throws NostrException, NoSuchAlgorithmException {
    this.genericEventRecord = GenericEventEntityFactory.createInstance(identity, kind, List.of(), content);
  }

  public NIP01EventRxR(@NonNull Identity identity, @NonNull Kind kind, @NonNull List<BaseTag> tags) throws NostrException, NoSuchAlgorithmException {
    this.genericEventRecord = GenericEventEntityFactory.createInstance(identity, kind, List.of(), "");
  }

  public NIP01EventRxR(@NonNull Identity identity, @NonNull Kind kind, List<BaseTag> tags, String content) throws NostrException, NoSuchAlgorithmException {
    this.genericEventRecord = GenericEventEntityFactory.createInstance(identity, kind, List.of(), content);
  }

    @Override
    public Kind getKind() {
        return genericEventRecord.kind();
    }
}

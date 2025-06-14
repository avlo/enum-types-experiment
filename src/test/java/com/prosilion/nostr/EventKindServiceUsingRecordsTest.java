package com.prosilion.nostr;

import com.prosilion.nostr.event.AbstractBadgeAwardEventRxR;
import com.prosilion.nostr.event.BadgeAwardDownvoteEventRxR;
import com.prosilion.nostr.event.BadgeAwardUpvoteEventRxR;
import com.prosilion.nostr.event.Identity;
import com.prosilion.nostr.event.TextNoteEvent;
import com.prosilion.nostr.event.Type;
import com.prosilion.nostr.service.EventKindServiceRxRIF;
import com.prosilion.nostr.service.EventKindTypeServiceRxRIF;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class EventKindServiceUsingRecordsTest {
  private static final Log log = LogFactory.getLog(EventKindServiceUsingRecordsTest.class);

  private final EventKindTypeServiceRxRIF<Type, AbstractBadgeAwardEventRxR<Type>> eventKindTypeService;
  private final EventKindServiceRxRIF<TextNoteEvent> eventKindService;

  @Autowired
  public EventKindServiceUsingRecordsTest(
      EventKindTypeServiceRxRIF<Type, AbstractBadgeAwardEventRxR<Type>> eventKindTypeService,
      EventKindServiceRxRIF<TextNoteEvent> eventKindService) {
    this.eventKindTypeService = eventKindTypeService;
    this.eventKindService = eventKindService;
  }

  @Test
  void testUpvoteEvent() throws NostrException, NoSuchAlgorithmException {
    Identity identity = Identity.generateRandomIdentity();
    Identity upvotedUser = Identity.generateRandomIdentity();

    BadgeAwardUpvoteEventRxR<Type> typeBadgeAwardUpvoteEvent = new BadgeAwardUpvoteEventRxR<>(identity, upvotedUser, "UPVOTE event text content");
    eventKindTypeService.processIncomingEvent(typeBadgeAwardUpvoteEvent);
  }

  @Test
  void testDownvoteEvent() throws NostrException, NoSuchAlgorithmException {
    Identity identity = Identity.generateRandomIdentity();
    Identity downvotedUser = Identity.generateRandomIdentity();

    BadgeAwardDownvoteEventRxR<Type> typeBadgeAwardDownvoteEvent = new BadgeAwardDownvoteEventRxR<>(identity, downvotedUser, "DOWN vote event text content");
    eventKindTypeService.processIncomingEvent(typeBadgeAwardDownvoteEvent);
  }

  @Test
  void testTextNoteEvent() throws NostrException, NoSuchAlgorithmException {
    Identity identity = Identity.generateRandomIdentity();

    TextNoteEvent textNoteEvent = new TextNoteEvent(identity, "TEXT note event text content");
    eventKindService.processIncomingEvent(textNoteEvent);
  }

  @Test
  void testAll() throws NostrException, NoSuchAlgorithmException {
    Identity identity = Identity.generateRandomIdentity();
    Identity upvotedUser = Identity.generateRandomIdentity();

    BadgeAwardUpvoteEventRxR<Type> typeBadgeAwardUpvoteEvent = new BadgeAwardUpvoteEventRxR<>(identity, upvotedUser, "UPVOTE event text content");
    eventKindTypeService.processIncomingEvent(typeBadgeAwardUpvoteEvent);

    Identity downvotedUser = Identity.generateRandomIdentity();

    BadgeAwardDownvoteEventRxR<Type> typeBadgeAwardDownvoteEvent = new BadgeAwardDownvoteEventRxR<>(identity, downvotedUser, "DOWN vote event text content");
    eventKindTypeService.processIncomingEvent(typeBadgeAwardDownvoteEvent);

    TextNoteEvent textNoteEvent = new TextNoteEvent(identity, "TEXT note event text content");
    eventKindService.processIncomingEvent(textNoteEvent);
  }
}

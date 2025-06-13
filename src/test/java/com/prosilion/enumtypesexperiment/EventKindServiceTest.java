package com.prosilion.enumtypesexperiment;

import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;
import com.prosilion.enumtypesexperiment.event.BadgeAwardDownvoteEvent;
import com.prosilion.enumtypesexperiment.event.BadgeAwardUpvoteEvent;
import com.prosilion.enumtypesexperiment.event.GenericEventEntity;
import com.prosilion.enumtypesexperiment.event.Identity;
import com.prosilion.enumtypesexperiment.event.TextNoteEvent;
import com.prosilion.enumtypesexperiment.event.Type;
import com.prosilion.enumtypesexperiment.service.EventKindServiceIF;
import com.prosilion.enumtypesexperiment.service.EventKindTypeServiceIF;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class EventKindServiceTest {
  private static final Log log = LogFactory.getLog(EventKindServiceTest.class);

  private final EventKindTypeServiceIF<Type, AbstractBadgeAwardEvent<Type>> eventKindTypeService;
  private final EventKindServiceIF<GenericEventEntity> eventKindService;

  @Autowired
  public EventKindServiceTest(
      EventKindTypeServiceIF<Type, AbstractBadgeAwardEvent<Type>> eventKindTypeService,
      EventKindServiceIF<GenericEventEntity> eventKindService) {
    this.eventKindTypeService = eventKindTypeService;
    this.eventKindService = eventKindService;
  }

  @Test
  void testUpvoteEvent() throws NostrException, NoSuchAlgorithmException {
    Identity identity = Identity.generateRandomIdentity();
    Identity upvotedUser = Identity.generateRandomIdentity();

    BadgeAwardUpvoteEvent<Type> typeBadgeAwardUpvoteEvent = new BadgeAwardUpvoteEvent<>(identity, upvotedUser, "UPVOTE event text content");
    eventKindTypeService.processIncomingEvent(typeBadgeAwardUpvoteEvent);
  }

  @Test
  void testDownvoteEvent() throws NostrException, NoSuchAlgorithmException {
    Identity identity = Identity.generateRandomIdentity();
    Identity downvotedUser = Identity.generateRandomIdentity();

    BadgeAwardDownvoteEvent<Type> typeBadgeAwardDownvoteEvent = new BadgeAwardDownvoteEvent<>(identity, downvotedUser, "DOWN vote event text content");
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

    BadgeAwardUpvoteEvent<Type> typeBadgeAwardUpvoteEvent = new BadgeAwardUpvoteEvent<>(identity, upvotedUser, "UPVOTE event text content");
    eventKindTypeService.processIncomingEvent(typeBadgeAwardUpvoteEvent);

    Identity downvotedUser = Identity.generateRandomIdentity();

    BadgeAwardDownvoteEvent<Type> typeBadgeAwardDownvoteEvent = new BadgeAwardDownvoteEvent<>(identity, downvotedUser, "DOWN vote event text content");
    eventKindTypeService.processIncomingEvent(typeBadgeAwardDownvoteEvent);

    TextNoteEvent textNoteEvent = new TextNoteEvent(identity, "TEXT note event text content");
    eventKindService.processIncomingEvent(textNoteEvent);
  }
}

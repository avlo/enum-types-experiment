package com.prosilion.enumtypesexperiment;

import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;
import com.prosilion.enumtypesexperiment.event.BadgeAwardDownvoteEvent;
import com.prosilion.enumtypesexperiment.event.BadgeAwardUpvoteEvent;
import com.prosilion.enumtypesexperiment.event.Identity;
import com.prosilion.enumtypesexperiment.event.Type;
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
class EventRecordKindServiceTest {
  private static final Log log = LogFactory.getLog(EventRecordKindServiceTest.class);

  private final EventKindTypeServiceIF<Type, AbstractBadgeAwardEvent<Type>> eventTypeService;

  @Autowired
  public EventRecordKindServiceTest(EventKindTypeServiceIF<Type, AbstractBadgeAwardEvent<Type>> eventTypeService) {
    this.eventTypeService = eventTypeService;
  }

  @Test
  void testUpvoteEvent() throws NostrException, NoSuchAlgorithmException {
    Identity identity = Identity.generateRandomIdentity();
    Identity upvotedUser = Identity.generateRandomIdentity();

    BadgeAwardUpvoteEvent<Type> typeBadgeAwardUpvoteEvent = new BadgeAwardUpvoteEvent<>(identity, upvotedUser, "UPVOTE event text content");
    eventTypeService.processIncomingEvent(typeBadgeAwardUpvoteEvent);

    log.info("00000000000000000000000000");
    log.info("00000000000000000000000000");
    log.info(typeBadgeAwardUpvoteEvent.toString());
    log.info("00000000000000000000000000");
    log.info("00000000000000000000000000");

  }

  @Test
  void testDownvoteEvent() throws NostrException, NoSuchAlgorithmException {
    Identity identity = Identity.generateRandomIdentity();
    Identity downvotedUser = Identity.generateRandomIdentity();

    BadgeAwardDownvoteEvent<Type> typeBadgeAwardUpvoteEvent = new BadgeAwardDownvoteEvent<>(identity, downvotedUser, "DOWN vote event text content");
    eventTypeService.processIncomingEvent(typeBadgeAwardUpvoteEvent);

    log.info("11111111111111111111111111");
    log.info("11111111111111111111111111");
    log.info(typeBadgeAwardUpvoteEvent.toString());
    log.info("11111111111111111111111111");
    log.info("11111111111111111111111111");
  }
}

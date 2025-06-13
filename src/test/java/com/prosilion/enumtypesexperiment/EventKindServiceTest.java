package com.prosilion.enumtypesexperiment;

import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;
import com.prosilion.enumtypesexperiment.event.AddressTag;
import com.prosilion.enumtypesexperiment.event.BadgeAwardUpvoteEvent;
import com.prosilion.enumtypesexperiment.event.IdentifierTag;
import com.prosilion.enumtypesexperiment.event.Identity;
import com.prosilion.enumtypesexperiment.event.PubKeyTag;
import com.prosilion.enumtypesexperiment.event.Type;
import com.prosilion.enumtypesexperiment.event.UpvoteTags;
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

  private final EventKindTypeServiceIF<Type, AbstractBadgeAwardEvent<Type>> eventTypeService;

  @Autowired
  public EventKindServiceTest(EventKindTypeServiceIF<Type, AbstractBadgeAwardEvent<Type>> eventTypeService) {
    this.eventTypeService = eventTypeService;
  }

  @Test
  void processIncomingEvent() throws NostrException, NoSuchAlgorithmException {
    Identity identity = Identity.generateRandomIdentity();
    IdentifierTag identifierTag = new IdentifierTag(Type.UPVOTE.getName());

    AddressTag addressTag = new AddressTag(
        Kind.BADGE_AWARD_EVENT,
        identity.getPublicKey(),
        identifierTag);

    UpvoteTags upvoteTags = new UpvoteTags(addressTag, new PubKeyTag(identity.getPublicKey()));
    String content = "1";

    BadgeAwardUpvoteEvent<Type> typeBadgeAwardUpvoteEvent = new BadgeAwardUpvoteEvent<>(identity, upvoteTags, content);
    eventTypeService.processIncomingEvent(typeBadgeAwardUpvoteEvent);
    
    log.info("00000000000000000000000000");
    log.info("00000000000000000000000000");
    log.info(typeBadgeAwardUpvoteEvent.toString());
    log.info("00000000000000000000000000");
    log.info("00000000000000000000000000");
//    eventTypeService.processIncomingEvent(new BadgeAwardDownvoteEvent());
  }
}

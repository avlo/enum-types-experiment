package com.prosilion.enumtypesexperiment;

import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;
import com.prosilion.enumtypesexperiment.event.Type;
import com.prosilion.enumtypesexperiment.event.BadgeAwardDownvoteEvent;
import com.prosilion.enumtypesexperiment.event.BadgeAwardUpvoteEvent;
import com.prosilion.enumtypesexperiment.service.EventKindServiceIF;
import com.prosilion.enumtypesexperiment.service.EventKindTypeServiceIF;
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

  private final EventKindTypeServiceIF<AbstractBadgeAwardEvent<Kind, Type>> eventTypeService;

  @Autowired
  public EventKindServiceTest(EventKindTypeServiceIF<AbstractBadgeAwardEvent<Kind, Type>> eventTypeService) {
    this.eventTypeService = eventTypeService;
  }

  @Test
  void processIncomingEvent() {
    eventTypeService.processIncomingEvent(new BadgeAwardUpvoteEvent());
    eventTypeService.processIncomingEvent(new BadgeAwardDownvoteEvent());
  }
}

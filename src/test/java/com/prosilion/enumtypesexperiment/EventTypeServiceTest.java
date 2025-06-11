package com.prosilion.enumtypesexperiment;

import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent;
import com.prosilion.enumtypesexperiment.event.AbstractBadgeAwardEvent.Type;
import com.prosilion.enumtypesexperiment.event.BadgeAwardDownvoteEvent;
import com.prosilion.enumtypesexperiment.event.BadgeAwardUpvoteEvent;
import com.prosilion.enumtypesexperiment.service.EventTypeServiceIF;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class EventTypeServiceTest {
  private static final Log log = LogFactory.getLog(EventTypeServiceTest.class);

  private final EventTypeServiceIF<AbstractBadgeAwardEvent<Kind, Type>> eventTypeService;

  @Autowired
  public EventTypeServiceTest(EventTypeServiceIF<AbstractBadgeAwardEvent<Kind, Type>> eventTypeService) {
    this.eventTypeService = eventTypeService;
  }

  @Test
  void processIncomingEvent() {
    eventTypeService.processIncomingEvent(new BadgeAwardUpvoteEvent());
    eventTypeService.processIncomingEvent(new BadgeAwardDownvoteEvent());
  }
}

package ca.ulaval.glo4002.booking.program.events.domain;

import ca.ulaval.glo4002.booking.profits.domain.ProfitReport;
import ca.ulaval.glo4002.booking.program.activities.domain.Activities;
import ca.ulaval.glo4002.booking.program.artists.domain.Artist;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class EventTest {

    @Test
    void updateProfit_shouldCallUpdateProfitOfArtist() {
        ProfitReport profitReport = new ProfitReport();
        EventDate eventDate = mock(EventDate.class);
        Activities activity = Activities.CARDIO;
        Artist artist = mock(Artist.class);
        Event event = new Event(eventDate, activity, artist);

        event.updateProfit(profitReport);

        verify(artist).updateProfit(eq(profitReport));
    }
}
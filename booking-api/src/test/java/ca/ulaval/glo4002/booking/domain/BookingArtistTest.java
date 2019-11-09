package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.domain.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BookingArtistTest {

    private BookingArtist bookingArtist;

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotArtist() {
        bookingArtist = new BookingArtist(1, "aArtist", mock(Money.class), 1, "aMusicStyle", 1);
        Object object = new Object();

        boolean result = bookingArtist.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenArtistHasSameValues() {
        Integer id = 1;
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        bookingArtist = new BookingArtist(id, name, cost, membersAmount, musicStyle, popularityRank);
        BookingArtist other = new BookingArtist(id, name, cost, membersAmount, musicStyle, popularityRank);

        boolean result = bookingArtist.equals(other);

        assertTrue(result);
    }

    @Test
    void equals_shouldReturnFalse_whenArtistHasDifferentIds() {
        Integer id = 1;
        Integer otherId = 2;
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        bookingArtist = new BookingArtist(id, name, cost, membersAmount, musicStyle, popularityRank);
        BookingArtist other = new BookingArtist(otherId, name, cost, membersAmount, musicStyle, popularityRank);

        boolean result = bookingArtist.equals(other);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnFalse_whenArtistHasDifferentNames() {
        Integer id = 1;
        String name = "aArtist";
        String otherName = "anotherArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        bookingArtist = new BookingArtist(id, name, cost, membersAmount, musicStyle, popularityRank);
        BookingArtist other = new BookingArtist(id, otherName, cost, membersAmount, musicStyle, popularityRank);

        boolean result = bookingArtist.equals(other);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnFalse_whenArtistHasDifferentCosts() {
        Integer id = 1;
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Money otherCost = new Money(new BigDecimal(200));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        bookingArtist = new BookingArtist(id, name, cost, membersAmount, musicStyle, popularityRank);
        BookingArtist other = new BookingArtist(id, name, otherCost, membersAmount, musicStyle, popularityRank);

        boolean result = bookingArtist.equals(other);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnFalse_whenArtistHasDifferentMembersAmounts() {
        Integer id = 1;
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        Integer otherMembersAmount = 2;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        bookingArtist = new BookingArtist(id, name, cost, membersAmount, musicStyle, popularityRank);
        BookingArtist other = new BookingArtist(id, name, cost, otherMembersAmount, musicStyle, popularityRank);

        boolean result = bookingArtist.equals(other);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnFalse_whenArtistHasDifferentMusicStyles() {
        Integer id = 1;
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        String otherMusicStyle = "anotherMusicStyle";
        Integer popularityRank = 1;
        bookingArtist = new BookingArtist(id, name, cost, membersAmount, musicStyle, popularityRank);
        BookingArtist other = new BookingArtist(id, name, cost, membersAmount, otherMusicStyle, popularityRank);

        boolean result = bookingArtist.equals(other);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnFalse_whenArtistHasDifferentPopularityRanks() {
        Integer id = 1;
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        Integer otherPopularityRank = 2;
        bookingArtist = new BookingArtist(id, name, cost, membersAmount, musicStyle, popularityRank);
        BookingArtist other = new BookingArtist(id, name, cost, membersAmount, musicStyle, otherPopularityRank);

        boolean result = bookingArtist.equals(other);

        assertFalse(result);
    }

    @Test
    void hashCode_shouldReturnValuesHashcode() {
        Integer id = 1;
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        bookingArtist = new BookingArtist(id, name, cost, membersAmount, musicStyle, popularityRank);
        int expectedHashCode = id.hashCode() + name.hashCode() + cost.hashCode() + membersAmount.hashCode() + musicStyle.hashCode() + popularityRank.hashCode();

        int hashCode = bookingArtist.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }
}
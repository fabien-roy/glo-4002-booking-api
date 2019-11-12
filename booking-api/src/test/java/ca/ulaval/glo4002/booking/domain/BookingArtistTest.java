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
        bookingArtist = new BookingArtist(new Number(1L), "aArtist", mock(Money.class), 1, "aMusicStyle", 1);
        Object object = new Object();

        boolean result = bookingArtist.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenArtistHasSameValues() {
        Number id = new Number(1L);
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
        Number id = new Number(1L);
        Number otherId = new Number(2L);
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
        Number id = new Number(1L);
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
        Number id = new Number(1L);
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
        Number id = new Number(1L);
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
        Number id = new Number(1L);
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
        Number id = new Number(1L);
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
        Number id = new Number(1L);
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
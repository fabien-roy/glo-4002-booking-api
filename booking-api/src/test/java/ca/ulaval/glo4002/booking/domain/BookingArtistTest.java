package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.domain.artist.BookingArtist;
import ca.ulaval.glo4002.booking.domain.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BookingArtistTest {

    private BookingArtist bookingArtist;

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotArtist() {
        bookingArtist = new BookingArtist("aArtist", mock(Money.class), 1, "aMusicStyle", 1, new ArrayList<>());
        Object object = new Object();

        boolean result = bookingArtist.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenArtistHasSameValues() {
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        bookingArtist = new BookingArtist(name, cost, membersAmount, musicStyle, popularityRank, new ArrayList<>());
        BookingArtist other = new BookingArtist(name, cost, membersAmount, musicStyle, popularityRank, new ArrayList<>());

        boolean result = bookingArtist.equals(other);

        assertTrue(result);
    }

    @Test
    void equals_shouldReturnFalse_whenArtistHasDifferentName() {
        String name = "aArtist";
        String otherName = "anotherArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        bookingArtist = new BookingArtist(name, cost, membersAmount, musicStyle, popularityRank, new ArrayList<>());
        BookingArtist other = new BookingArtist(otherName, cost, membersAmount, musicStyle, popularityRank, new ArrayList<>());

        boolean result = bookingArtist.equals(other);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnFalse_whenArtistHasDifferentCost() {
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Money otherCost = new Money(new BigDecimal(200));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        bookingArtist = new BookingArtist(name, cost, membersAmount, musicStyle, popularityRank, new ArrayList<>());
        BookingArtist other = new BookingArtist(name, otherCost, membersAmount, musicStyle, popularityRank, new ArrayList<>());

        boolean result = bookingArtist.equals(other);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnFalse_whenArtistHasDifferentMembersAmount() {
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        Integer otherMembersAmount = 2;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        bookingArtist = new BookingArtist(name, cost, membersAmount, musicStyle, popularityRank, new ArrayList<>());
        BookingArtist other = new BookingArtist(name, cost, otherMembersAmount, musicStyle, popularityRank, new ArrayList<>());

        boolean result = bookingArtist.equals(other);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnFalse_whenArtistHasDifferentMusicStyle() {
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        String otherMusicStyle = "anotherMusicStyle";
        Integer popularityRank = 1;
        bookingArtist = new BookingArtist(name, cost, membersAmount, musicStyle, popularityRank, new ArrayList<>());
        BookingArtist other = new BookingArtist(name, cost, membersAmount, otherMusicStyle, popularityRank, new ArrayList<>());

        boolean result = bookingArtist.equals(other);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnFalse_whenArtistHasDifferentPopularityRank() {
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        Integer otherPopularityRank = 2;
        bookingArtist = new BookingArtist(name, cost, membersAmount, musicStyle, popularityRank, new ArrayList<>());
        BookingArtist other = new BookingArtist(name, cost, membersAmount, musicStyle, otherPopularityRank, new ArrayList<>());

        boolean result = bookingArtist.equals(other);

        assertFalse(result);
    }

    @Test
    void hashCode_shouldReturnValuesHashcode() {
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        bookingArtist = new BookingArtist(name, cost, membersAmount, musicStyle, popularityRank, new ArrayList<>());
        int expectedHashCode = name.hashCode() + cost.hashCode() + membersAmount.hashCode() + musicStyle.hashCode() + popularityRank.hashCode();

        int hashCode = bookingArtist.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }
}
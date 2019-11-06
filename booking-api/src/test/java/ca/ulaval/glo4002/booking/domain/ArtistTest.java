package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.domain.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ArtistTest {

    private BookingArtist artist;
/*
    @Test
    void equals_shouldReturnFalse_whenObjectIsNotArtist() {
        artist = new BookingArtist("aArtist", mock(Money.class), 1);
        Object object = new Object();

        boolean result = artist.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenArtistHasSameValues() {
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        artist = new BookingArtist(name, cost, membersAmount);
        BookingArtist other = new BookingArtist(name, cost, membersAmount);

        boolean result = artist.equals(other);

        assertTrue(result);
    }

    @Test
    void equals_shouldReturnFalse_whenArtistHasDifferentName() {
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        artist = new BookingArtist(name, cost, membersAmount);
        BookingArtist other = new BookingArtist("anotherArtist", cost, membersAmount);

        boolean result = artist.equals(other);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnFalse_whenArtistHasDifferentCost() {
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        artist = new BookingArtist(name, cost, membersAmount);
        BookingArtist other = new BookingArtist(name, new Money(new BigDecimal(200)), membersAmount);

        boolean result = artist.equals(other);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnFalse_whenArtistHasDifferentMembersAmount() {
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        artist = new BookingArtist(name, cost, membersAmount);
        BookingArtist other = new BookingArtist(name, cost, 2);

        boolean result = artist.equals(other);

        assertFalse(result);
    }


    @Test
    void hashCode_shouldReturnValuesHashcode() {
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        int expectedHashCode = name.hashCode() + cost.hashCode() + membersAmount.hashCode();
        artist = new BookingArtist(name, cost, membersAmount);

        int hashCode = artist.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }
    */
}
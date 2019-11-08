package ca.ulaval.glo4002.booking.domain;

import ca.ulaval.glo4002.booking.domain.money.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ArtistTest {

    private Artist artist;

    @Test
    void equals_shouldReturnFalse_whenObjectIsNotArtist() {
        artist = new Artist("aArtist", mock(Money.class), 1, "aMusicStyle", 1);
        Object object = new Object();

        boolean result = artist.equals(object);

        assertFalse(result);
    }

    @Test
    void equals_shouldReturnTrue_whenArtistHasSameValues() {
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        artist = new Artist(name, cost, membersAmount, musicStyle, popularityRank);
        Artist other = new Artist(name, cost, membersAmount, musicStyle, popularityRank);

        boolean result = artist.equals(other);

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
        artist = new Artist(name, cost, membersAmount, musicStyle, popularityRank);
        Artist other = new Artist(otherName, cost, membersAmount, musicStyle, popularityRank);

        boolean result = artist.equals(other);

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
        artist = new Artist(name, cost, membersAmount, musicStyle, popularityRank);
        Artist other = new Artist(name, otherCost, membersAmount, musicStyle, popularityRank);

        boolean result = artist.equals(other);

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
        artist = new Artist(name, cost, membersAmount, musicStyle, popularityRank);
        Artist other = new Artist(name, cost, otherMembersAmount, musicStyle, popularityRank);

        boolean result = artist.equals(other);

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
        artist = new Artist(name, cost, membersAmount, musicStyle, popularityRank);
        Artist other = new Artist(name, cost, membersAmount, otherMusicStyle, popularityRank);

        boolean result = artist.equals(other);

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
        artist = new Artist(name, cost, membersAmount, musicStyle, popularityRank);
        Artist other = new Artist(name, cost, membersAmount, musicStyle, otherPopularityRank);

        boolean result = artist.equals(other);

        assertFalse(result);
    }

    @Test
    void hashCode_shouldReturnValuesHashcode() {
        String name = "aArtist";
        Money cost = new Money(new BigDecimal(100));
        Integer membersAmount = 1;
        String musicStyle = "aMusicStyle";
        Integer popularityRank = 1;
        artist = new Artist(name, cost, membersAmount, musicStyle, popularityRank);
        int expectedHashCode = name.hashCode() + cost.hashCode() + membersAmount.hashCode() + musicStyle.hashCode() + popularityRank.hashCode();

        int hashCode = artist.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }
}
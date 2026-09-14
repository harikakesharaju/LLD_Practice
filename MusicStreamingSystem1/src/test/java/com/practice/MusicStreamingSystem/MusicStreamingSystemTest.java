package com.practice.MusicStreamingSystem;

import com.practice.MusicStreamingSystem.entities.Album;
import com.practice.MusicStreamingSystem.entities.Artist;
import com.practice.MusicStreamingSystem.entities.Player;
import com.practice.MusicStreamingSystem.entities.Song;
import com.practice.MusicStreamingSystem.entities.User;
import com.practice.MusicStreamingSystem.enums.PlayerStatus;
import com.practice.MusicStreamingSystem.enums.SubscriptionTier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicStreamingSystemTest {

    @Test
    void artistShouldNotifyFollowersWhenAlbumIsCreated() {
        Artist artist = new Artist("Artist");
        User user = new User("User", SubscriptionTier.FREE);

        artist.addObserver(user);
        Album album = artist.createAlbum("Album 1");

        assertEquals("Album 1", album.getName());
        assertEquals(1, user.getNotifications().size());
        assertTrue(user.getNotifications().get(0).contains("created a new album"));
    }

    @Test
    void artistShouldNotifyFollowersWhenTrackIsAdded() {
        Artist artist = new Artist("Artist");
        User user = new User("User", SubscriptionTier.PREMIUM);
        artist.addObserver(user);

        Album album = artist.createAlbum("Album 1");
        Song song = new Song("Song 1", artist, "Pop", 200);
        artist.addTrackToAlbum(album, song);

        assertEquals(2, user.getNotifications().size());
        assertTrue(user.getNotifications().get(1).contains("added track"));
    }

    @Test
    void playerStateShouldChangeCorrectly() {
        Artist artist = new Artist("Artist");
        User user = new User("User", SubscriptionTier.PREMIUM);
        Album album = artist.createAlbum("Album");
        Song song = new Song("Song", artist, "Pop", 200);
        artist.addTrackToAlbum(album, song);

        Player player = new Player();
        player.load(album, user);

        assertEquals(PlayerStatus.STOPPED, player.getStatus());

        player.clickPlay();
        assertEquals(PlayerStatus.PLAYING, player.getStatus());

        player.clickPause();
        assertEquals(PlayerStatus.PAUSED, player.getStatus());

        player.clickPlay();
        assertEquals(PlayerStatus.PLAYING, player.getStatus());
    }

    @Test
    void premiumAndFreeUsersGetDifferentPlaybackStrategies() {
        Artist artist = new Artist("Artist");
        Album album = artist.createAlbum("Album");
        Song song = new Song("Song", artist, "Rock", 180);
        artist.addTrackToAlbum(album, song);

        User premium = new User("Premium", SubscriptionTier.PREMIUM);
        User free = new User("Free", SubscriptionTier.FREE);

        Player premiumPlayer = new Player();
        premiumPlayer.load(album, premium);

        Player freePlayer = new Player();
        freePlayer.load(album, free);

        assertEquals(
                "PremiumPlaybackStrategy",
                premiumPlayer.getPlaybackStrategy().getClass().getSimpleName()
        );
        assertEquals(
                "FreePlaybackStrategy",
                freePlayer.getPlaybackStrategy().getClass().getSimpleName()
        );
    }
}

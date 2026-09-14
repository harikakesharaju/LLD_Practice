package com.practice.MusicStreamingSystem;

import com.practice.MusicStreamingSystem.command.NextTrackCommand;
import com.practice.MusicStreamingSystem.command.PauseCommand;
import com.practice.MusicStreamingSystem.command.PlayCommand;
import com.practice.MusicStreamingSystem.entities.Album;
import com.practice.MusicStreamingSystem.entities.Artist;
import com.practice.MusicStreamingSystem.entities.Player;
import com.practice.MusicStreamingSystem.entities.Playlist;
import com.practice.MusicStreamingSystem.entities.Song;
import com.practice.MusicStreamingSystem.entities.User;
import com.practice.MusicStreamingSystem.enums.SubscriptionTier;
import com.practice.MusicStreamingSystem.services.RecommendationService;

import java.util.List;

public class App {

    public static void main(String[] args) {
        MusicStreamingSystem system = new MusicStreamingSystem();

        Artist arijit = system.createArtist("Arijit Singh");
        Artist anirudh = system.createArtist("Anirudh Ravichander");

        User premiumUser = system.createUser("Harika", SubscriptionTier.PREMIUM);
        User freeUser = system.createUser("Rahul", SubscriptionTier.FREE);

        system.followArtist(premiumUser, arijit);
        system.followArtist(freeUser, arijit);

        Album album = system.createAlbum(arijit, "Melody Collection");

        Song song1 = system.createSong("Tum Hi Ho", arijit, "Bollywood", 262);
        Song song2 = system.createSong("Kesariya", arijit, "Bollywood", 268);
        Song song3 = system.createSong("Why This Kolaveri Di", anirudh, "Tamil", 270);

        system.addTrackToAlbum(arijit, album, song1);
        system.addTrackToAlbum(arijit, album, song2);

        Playlist playlist = new Playlist("My Favorites");
        playlist.addSong(song1);
        playlist.addSong(song2);
        playlist.addSong(song3);

        Player premiumPlayer = new Player();
        premiumPlayer.load(playlist, premiumUser);

        System.out.println("\n--- COMMAND + STATE PATTERN ---");
        new PlayCommand(premiumPlayer).execute();
        new NextTrackCommand(premiumPlayer).execute();
        new PauseCommand(premiumPlayer).execute();
        premiumPlayer.clickPlay();

        System.out.println("\n--- RECOMMENDATION STRATEGY ---");
        RecommendationService recommendationService =
                new RecommendationService(
                        new com.practice.MusicStreamingSystem.strategies.recommendation.GenreBasedRecommendationStrategy()
                );

        List<Song> recommendations =
                recommendationService.recommend(premiumUser, List.of(song1, song2, song3));

        System.out.println("Recommendations for " + premiumUser.getName() + ":");
        recommendations.forEach(song -> System.out.println(" - " + song.getTitle()));

        System.out.println("\n--- FREE PLAYBACK STRATEGY ---");
        Player freePlayer = new Player();
        freePlayer.load(playlist, freeUser);
        new PlayCommand(freePlayer).execute();

        System.out.println("\n--- ARTIST -> OBSERVER NOTIFICATIONS ---");
        Album secondAlbum = system.createAlbum(arijit, "New Songs");
        Song newSong = system.createSong("New Release", arijit, "Bollywood", 240);
        system.addTrackToAlbum(arijit, secondAlbum, newSong);

        System.out.println("\n--- PLAY HISTORY ---");
        System.out.println(premiumUser.getPlayHistory());
    }
}

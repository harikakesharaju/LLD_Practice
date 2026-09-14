# Music Streaming System — Java LLD

A complete Low-Level Design practice project demonstrating:

- State Design Pattern — player states: `Stopped`, `Playing`, `Paused`
- Strategy Design Pattern — recommendation and playback rules
- Observer Design Pattern — users are notified when an artist creates an album or adds a track
- Command Design Pattern — Play, Pause, Next Track
- Maven + Java 17
- Playlists, albums, artists, users, subscriptions and playback history
- Free vs Premium playback restrictions
- Genre-based recommendations

## Package structure

```text
src/main/java/com/practice/MusicStreamingSystem
├── App.java
├── MusicStreamingSystem.java
├── command
│   ├── Command.java
│   ├── NextTrackCommand.java
│   ├── PauseCommand.java
│   └── PlayCommand.java
├── entities
│   ├── Album.java
│   ├── Artist.java
│   ├── Playable.java
│   ├── Player.java
│   ├── Playlist.java
│   ├── Song.java
│   └── User.java
├── enums
│   ├── PlayerStatus.java
│   └── SubscriptionTier.java
├── observer
│   ├── ArtistObserver.java
│   └── Subject.java
├── services
│   └── RecommendationService.java
├── state
│   ├── PlayerState.java
│   ├── PlayingState.java
│   ├── PausedState.java
│   └── StoppedState.java
└── strategies
    ├── recommendation
    │   ├── RecommendationStrategy.java
    │   └── GenreBasedRecommendationStrategy.java
    └── playback
        ├── PlaybackStrategy.java
        ├── FreePlaybackStrategy.java
        └── PremiumPlaybackStrategy.java
```

## Run

```bash
mvn clean test
mvn package
```

Run `com.practice.MusicStreamingSystem.App` from Eclipse/IntelliJ.

## Design pattern mapping

### State
`Player` owns a `PlayerState`. `clickPlay()`, `clickPause()` and `clickNext()` delegate to the current state. The behavior changes without a large `if/else` block.

### Strategy
`Player` owns a `PlaybackStrategy`, which is selected based on the user's subscription. `RecommendationService` owns a `RecommendationStrategy`, allowing recommendation algorithms to be replaced.

### Observer
`Artist` is a `Subject`. Users who follow an artist are `ArtistObserver`s. When the artist creates an album or adds a track, every follower receives a notification.

### Command
The UI/client can create `PlayCommand`, `PauseCommand`, and `NextTrackCommand` objects and execute them against a player.

## Important interview points

- `Playable` lets both `Song` and `Playlist` be loaded by `Player`.
- `Artist` does not directly depend on `User`; it depends on the observer interface.
- `Player` delegates state-specific behavior to state objects.
- Playback restrictions are isolated inside playback strategies.
- Recommendation logic is isolated inside recommendation strategies.

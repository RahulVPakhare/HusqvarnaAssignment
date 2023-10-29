# HusqvarnaAssignment
This is a solution to the assignment of Husqvarna for the role of Android Developer.

This Android application utilizes The Movie Database (TMDb) API to provide users with information about popular movies. This app allows users to explore a list of the top 10 popular movies, view their posters, and access detailed information about each movie.

This app is built using Kotlin, incorporates Coroutines for asynchronous programming, follows Clean Architecture principles, and uses Hilt for dependency injection.

# Features
The app provides the following features:

1. List of Top 10 Popular Movies: The app displays a list of the top 10 popular movies, each showing the movie poster.
2. Movie Details: When a user selects a movie from the list, the app displays detailed information about that movie, including its title, release date, overview, and average rating.

# Project Structure
The project is organized following Clean Architecture principles, which consist of the following layers:

1. `common`: Contains utility classes and functions.
2. `data`: Manages data retrieval from the TMDb API using Coroutines and Retrofit. It also contains the data models.
3. `di`: Handles dependency injection using Hilt.
4. `domain`: Contains use cases and business logic.
5. `presentation`: Defines the user interface using ViewModels and Activities.

# Dependencies
The app relies on the following important dependencies:

Retrofit: For making network requests to TMDb API.
Hilt: For dependency injection.
Coroutines: For asynchronous programming.
Glide: For loading and caching images.

# Acknowledgments
The Movie Database (TMDb) for providing the API to access movie information.

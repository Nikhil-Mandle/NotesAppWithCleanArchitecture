# Notes App With Clean Architecture

This is a simple Notes App built using Android's Clean Architecture principles using Jetpack Compose. The app allows users to create, read, update, and delete notes. It uses Room DB for local data storage and Hilt Dagger for dependency injection.

# Table of Contents
1. Features
2. Architecture
3. Technologies Used
4. Installation
6. Requirements

# Features
- Create, read, update, and delete notes
- Persistent local storage using Room Database
- Dependency Injection with Hilt Dagger
- Clean Architecture with MVVM pattern
  
# Architecture
This app follows the principles of Clean Architecture, which ensures separation of concerns and testability.

# Layers:
# Presentation Layer:
- Contains the UI code (Activities, Fragments, ViewModels).
- Uses MVVM (Model-View-ViewModel) architecture to handle UI-related data.
- Communicates with the Domain layer.

# Domain Layer:
- Contains the business logic.
- Includes Use Cases that coordinate between the Presentation and Data layers.
- Defines Interfaces for the repositories.

# Data Layer:
- Manages data sources, including Room Database.
- Implements the repository interfaces defined in the Domain layer.
- Converts data models to and from domain models.

# Technologies Used
- Kotlin: Primary language for Android development.
- Jetpack Components: ViewModel, LiveData, Navigation.
- Room Database: Local persistence library to store notes.
- Hilt Dagger: Dependency injection library to manage object creation and dependencies.
- MVVM: Design pattern for organizing UI code.
- Coroutines: For managing background tasks and threading.

# Requirements
- Android Studio
- Android SDK min version 24 or newer
- Kotlin version 1.9.0
- Dagger Hilt version 2.50
- Jetpack Compose version 1.0.0 or newer    
- room Db version 2.6.1

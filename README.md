# Product Demo Application

## Overview

This project is a Kotlin-based application that demonstrates the implementation of a product management system. It uses modern Android development practices, including dependency injection, coroutines, and reactive programming with `Flow`. The project is built using Gradle and follows a clean architecture approach.

## Features

- **Fetch Products from API**: Retrieves a list of products from a remote API.
- **Local Database Integration**: Stores the fetched products in a local database for offline access.
- **Reactive Data Flow**: Uses Kotlin `Flow` to observe and react to changes in the local database.
- **Dependency Injection**: Implements dependency injection using `@Inject` annotations for better modularity and testability.

## Project Structure

### Domain Layer
- **Models**: Contains the `Product` data model.
- **Repository**: Defines the `ProductRepository` interface for data operations.
- **Use Cases**: Implements the `FetchProductsUseCase` to handle business logic for fetching products.

### Data Layer
- **API Integration**: Fetches product data from a remote API.
- **Local Database**: Stores and retrieves product data using a local database.

### Presentation Layer
- Not implemented in the provided code but can include UI components like Activities, Fragments, or Jetpack Compose.

## Key Classes

- `ProductRepository`: Interface defining methods for fetching and storing product data.
- `FetchProductsUseCase`: Use case that handles fetching products from the API and storing them in the local database if the database is empty.

## Technologies Used

- **Kotlin**: Primary programming language.
- **Coroutines**: For asynchronous programming.
- **Flow**: For reactive data streams.
- **Dependency Injection**: Using `@Inject` annotations.
- **Gradle**: Build system.

## How It Works

1. The `FetchProductsUseCase` is invoked to fetch products.
2. It first checks the local database for existing products.
3. If the database is empty, it fetches products from the API and stores them in the local database.
4. The updated product list is then observed via `Flow`.

## Prerequisites

- Android Studio Meerkat | 2024.3.1 Patch 2 or later.
- Kotlin 1.8 or later.
- Gradle 8.0 or later.

## Setup

1. Clone the repository.
2. Open the project in Android Studio.
3. Sync the Gradle files.
4. Run the application on an emulator or physical device.

# FoodShare Mobile

FoodShare is an Android application that connects hotels and restaurants that have surplus food with beneficiaries in need, helping to reduce food wastage.

## Features

- **Multi-role support** – Users can sign up as a Beneficiary, Hotel, or Guest
- **Food discovery** – Browse available food items with real-time availability countdowns
- **Hotel listings** – Explore nearby hotels and view their details
- **Shopping cart** – Add food items, manage quantities, and place orders
- **Order tracking** – Monitor pending and completed orders in one place
- **User profile** – Manage account details and KYC verification status
- **Notifications** – Stay informed with in-app notifications

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Kotlin |
| UI | Jetpack Compose + Material Design 3 |
| Navigation | Jetpack Navigation Compose |
| State management | ViewModel + StateFlow |
| Build system | Gradle (Kotlin DSL) |
| Min SDK | 24 (Android 7.0) |
| Target SDK | 36 |

## Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) (Ladybug or later recommended)
- JDK 11 or higher
- Android SDK with API level 24+

### Running the app

1. Clone the repository:
   ```bash
   git clone https://github.com/toonshi/foodshare.git
   ```
2. Open the project in Android Studio.
3. Let Gradle sync and download all dependencies.
4. Select a device or emulator (API 24+) and click **Run**.

## Project Structure

```
app/
└── src/
    └── main/
        ├── java/com/example/foodshare_mobile/
        │   ├── MainActivity.kt          # Entry point & NavHost setup
        │   ├── screens/                 # All Compose screen files
        │   └── viewmodels/              # ViewModel classes
        └── res/                         # Resources (layouts, drawables, strings)
```

## Contributing

Pull requests are welcome. Please open an issue first to discuss any significant changes.

## License

This project is licensed under the [MIT License](LICENSE).

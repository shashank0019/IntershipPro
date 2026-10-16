
<div align="center">

# 🐾 Karunada-Vanya

### 🌿 Wildlife Education & Community Sighting Alerts

**Helping forest-edge communities stay informed, learn wildlife safety, and coexist with nature.**

![Flutter](https://img.shields.io/badge/Flutter-Framework-02569B?logo=flutter&logoColor=white)
![Dart](https://img.shields.io/badge/Dart-Language-0175C2?logo=dart&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-Backend-FFCA28?logo=firebase&logoColor=black)
![Riverpod](https://img.shields.io/badge/Riverpod-State_Management-00A98F)

</div>

---

## 📌 About the Project

**Karunada-Vanya** is a wildlife education and community alert application developed using Flutter, Dart, Riverpod, and Firebase.

The application is designed for communities living near forest areas. It combines wildlife safety education with location-aware community sighting alerts to help people stay informed about recent wildlife activity.

Users can explore educational content, learn wildlife safety practices, report animal sightings, view alerts on maps, and manage their profiles.

## ✨ Features

### 🔐 Authentication
- Email and password login.
- User account registration.
- Anonymous sign-in through the Community User option.
- Authentication-based navigation.
- Sign-out functionality.

### 🐅 Wildlife Education
- Learn about different wildlife species.
- Explore animal habitats and behavior.
- Read descriptions and wildlife safety tips.
- Access locally bundled educational content.

**Animals covered:**
- Tiger
- Elephant
- Leopard
- Birds
- Lion
- Bear
- Wild boar
- Deer
- Crocodile
- Cobra

### 🦺 Wildlife Coexistence Guide
- Elephant encounters and herd crossings.
- Tiger, leopard, bear, and snake safety.
- River and wetland safety.
- General community safety guidelines.

### 🚨 Community Wildlife Alerts
- View active wildlife sighting alerts.
- Report animal sightings with descriptions.
- Edit and delete alerts.
- Include optional location coordinates.
- Attach optional sighting images.
- Automatically expire alerts after six hours.
- Store alert information in Cloud Firestore.

### 🗺️ Map & Location Support
- Visualize wildlife alerts on a map.
- Select and share sighting locations.
- Support reports without coordinates.

### 🔊 Audio Learning
- Play and pause audio.
- Resume audio playback.
- Seek to the beginning.
- Support bundled audio assets and in-memory WAV data.

### 👤 User Profiles
Authenticated non-anonymous users can manage:
- Display name
- Email address
- Phone number
- Village
- Emergency contact

### 🎨 User Interface
- Consistent application theme.
- Centralized colors and styling.
- Feature-based application architecture.

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Flutter | Cross-platform application framework |
| Dart | Programming language |
| Riverpod | State management |
| Firebase Authentication | User authentication |
| Cloud Firestore | Cloud database |
| Firebase Storage | Image storage |
| flutter_map | Map visualization |
| latlong2 | Geographic coordinates |
| just_audio | Audio playback |
| image_picker | Image selection |
| intl | Date and formatting utilities |
| Gradle, Kotlin, Java 17 | Android build environment |

## 📂 Project Structure

```text
lib/
├── main.dart
├── app.dart
├── firebase_options.dart
├── core/
│   ├── constants/
│   ├── models/
│   ├── providers/
│   ├── services/
│   └── theme/
└── features/
    ├── alerts/
    ├── audio/
    ├── auth/
    ├── education/
    ├── guide/
    ├── home/
    ├── profile/
    └── shell/

assets/
├── audio/
├── data/
│   ├── wildlife.json
│   └── coexistence_guide.json
└── images/

test/
pubspec.yaml
analysis_options.yaml
```

## 🚀 Getting Started

### Prerequisites

- Flutter SDK with Dart 3.3 or newer.
- Android Studio and Android SDK.
- Java 17.
- Android emulator or physical Android device.
- Firebase project for cloud features.

### 1. Clone the Repository

```bash
git clone <YOUR_REPOSITORY_URL>
cd <YOUR_PROJECT_DIRECTORY>
```

Replace the placeholders with your repository URL and project directory.

### 2. Install Dependencies

```bash
flutter pub get
```

### 3. Configure Firebase

1. Create a project in the [Firebase Console](https://console.firebase.google.com/).
2. Enable Email/Password authentication.
3. Enable Anonymous authentication if required.
4. Create a Cloud Firestore database.
5. Enable Firebase Storage.
6. Configure Firebase for your application.

Install FlutterFire CLI and configure the project:

```bash
dart pub global activate flutterfire_cli
flutterfire configure
```

### 4. Run the Application

```bash
flutter run
```

### 5. Build the Android APK

```bash
flutter build apk
```

## 🔥 Firebase Data Model

### Collections

- `alerts`
- `users`

### Alert Fields

| Field | Description |
|---|---|
| `animalType` | Animal involved in the sighting |
| `description` | Sighting details |
| `createdAt` | Report creation time |
| `expiresAt` | Alert expiration time |
| `reportedBy` | Reporter information |
| `imageUrl` | Optional image reference |
| `latitude` | Optional latitude |
| `longitude` | Optional longitude |

New alerts expire six hours after creation.

### User Profile Fields

| Field | Description |
|---|---|
| `uid` | Firebase user ID |
| `email` | User email |
| `displayName` | Display name |
| `phoneNumber` | Optional phone number |
| `village` | Village or community |
| `emergencyContact` | Emergency contact information |

## 🧪 Testing & Development

Run the following commands before submitting changes:

```bash
# Install dependencies
flutter pub get

# Format code
dart format lib test

# Analyze the project
flutter analyze

# Run tests
flutter test
```

## 🔒 Safety & Privacy

Karunada-Vanya provides general wildlife education and community reporting. It does not replace official forest-department instructions, emergency services, or trained wildlife responders.

- Keep a safe distance from wild animals.
- Never chase, feed, trap, or handle wildlife.
- Avoid sharing sensitive personal information in public reports.
- Treat exact wildlife locations as potentially sensitive.
- Contact the appropriate local authority during dangerous encounters.

**Security note:** Firebase Firestore and Storage security rules must be reviewed and tested before production deployment. Never commit private service-account keys or credentials.

## 🚧 Future Improvements

- Improved Firebase security rules.
- Wildlife alert moderation.
- Duplicate-report prevention.
- Reliable server-side alert expiration.
- Push notifications for nearby sightings.
- Offline alert caching.
- Additional wildlife images and audio content.
- Expanded automated testing.
- Accessibility and localization improvements.
- Emergency escalation features.

## 👨‍💻 Developer

**Project:** Karunada-Vanya  
**Application:** Wildlife Education & Community Sighting Alerts  
**Framework:** Flutter  
**Backend:** Firebase

---

<div align="center">

### 🌱 Building safer communities through wildlife awareness.

**Made with Flutter, Dart, Riverpod, and Firebase 🐾**

</div>

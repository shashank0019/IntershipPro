:: {align="center"}
# 🌿 Karunada-Vanya 🐘

### Wildlife Awareness • Community Sightings • Safer Coexistence

**A Flutter and Firebase mobile application helping forest-edge
communities learn about wildlife and share recent animal sightings.**

```{=html}
<p>
```
`<img src="https://img.shields.io/badge/Flutter-Mobile_App-02569B?style=for-the-badge&logo=flutter&logoColor=white" alt="Flutter" />`{=html}
`<img src="https://img.shields.io/badge/Dart-3.x-0175C2?style=for-the-badge&logo=dart&logoColor=white" alt="Dart" />`{=html}
`<img src="https://img.shields.io/badge/Firebase-Backend-FFCA28?style=for-the-badge&logo=firebase&logoColor=black" alt="Firebase" />`{=html}
`<img src="https://img.shields.io/badge/Riverpod-State_Management-5A5A5A?style=for-the-badge" alt="Riverpod" />`{=html}
```{=html}
</p>
```
```{=html}
<p>
```
`<img src="https://img.shields.io/badge/Version-1.0.0%2B1-2E7D32?style=flat-square" alt="Version 1.0.0+1" />`{=html}
`<img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=flat-square&logo=android&logoColor=white" alt="Android" />`{=html}
`<img src="https://img.shields.io/badge/License-Not_Specified-lightgrey?style=flat-square" alt="License not specified" />`{=html}
```{=html}
</p>
```
:::🌱 About the Project
Karunada-Vanya is a wildlife education and community alert
application designed for people living in and around forest areas. It
brings together practical wildlife-safety information and recent
community-reported sightings in one mobile app.
Users can learn about animal behavior and safety, read coexistence
guidance, explore active sighting alerts, and submit reports with
descriptions, optional coordinates, and optional photos. Firebase
supports authentication, cloud-based alert data, profiles, and optional
image storage, while educational content is bundled locally for access
without an education-content network request.
Project details - Application name: Karunada-Vanya - Package
name: internpro - Version: 1.0.0+1 - Platform: Flutter
mobile application

✨ Features
🔐 Authentication
- Sign in using email and password.
- Create an account with email and password.
- Continue as a community user using anonymous sign-in.
- Navigate based on Firebase authentication state.
- Sign out of the application.
🐅 Wildlife Education
Learn about wildlife through structured content bundled with the app.
Current entries cover:
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
Each record can include an overview, habitat, behavior, and safety tips.
🚨 Community Wildlife Alerts
- View active wildlife alerts streamed from Cloud Firestore.
- Report a recent animal sighting.
- Edit or delete an alert.
- Include an animal type and description.
- Add optional latitude and longitude for map visualization.
- Attach an optional image using Firebase Storage.
- Keep alerts active for six hours after creation.
- Clean up expired alerts through the app's cleanup service.
🗺️ Map and Location Support
- Visualize location-aware alerts using flutter_map and latlong2.
- Select locations for reports.
- Allow reports without coordinates to appear as regular feed items.
🌳 Coexistence Guide
The bundled guide provides practical information about: - Elephant
encounters and herd crossings. - Tiger, leopard, bear, and snake
sightings. - River and wetland safety. - General wildlife and community
safety.
🔊 Audio Learning
The audio service supports: - Playing bundled audio assets. - Play and
pause controls. - Resuming playback. - Seeking back to the beginning. -
Playing WAV data held in memory.
The audio directory is prepared for content; bundled audio files are
not currently included.
👤 User Profiles
Authenticated users who are not anonymous can maintain profile
information in Firestore, including: - Display name - Email address -
Phone number - Village - Emergency contact
🎨 Consistent UI Theme
A shared light theme and centralized color definitions help maintain a
consistent interface across the app.
🧰 Technology Stack
  Area                   Technology
  Mobile UI              Flutter
  Programming language   Dart
  State management       Riverpod
  Authentication         Firebase Authentication
  Cloud database         Cloud Firestore
  Image storage          Firebase Storage
  Maps                   flutter_map, latlong2
  Audio playback         just_audio
  Image selection        image_picker
  Date and formatting    intl
  Android build          Gradle, Kotlin, Java 17
  Testing and linting    flutter_test, flutter_lints
Dart SDK constraint: >=3.3.0 <4.0.0
🏛️ Application Architecture
main.dart
   │
   ├── Initialize Firebase
   │
   └── ProviderScope
         │
         └── KarunadaVanyaApp
               │
               └── AuthGate
                     │
                     ├── AuthScreen (signed out)
                     │
                     └── MainShell (signed in)
                           ├── Home
                           ├── Wildlife Education
                           ├── Community Alerts
                           ├── Coexistence Guide
                           ├── Audio Learning
                           └── Profile
Data flow at a glance
- Local content: Wildlife and coexistence-guide records are loaded
  from JSON files in assets/data/.
- Authentication: Firebase Authentication provides email/password
  and anonymous sign-in.
- Community alerts: Cloud Firestore streams active alert records.
- Profiles: Firestore stores profile information for eligible
  authenticated users.
- Images: Firebase Storage handles sighting image uploads.
- State management: Riverpod wires Firebase clients, services, and
  providers into the application.
📁 Project Structure
.
├── android/                         # Android host and Gradle configuration
├── assets/
│   ├── audio/                       # Prepared for bundled audio
│   ├── data/
│   │   ├── wildlife.json            # Wildlife education records
│   │   └── coexistence_guide.json   # Safety guide content
│   └── images/                      # Image assets
├── lib/
│   ├── main.dart                    # Firebase initialization and entry point
│   ├── app.dart                     # Root application and theme
│   ├── firebase_options.dart        # Firebase platform configuration
│   ├── core/
│   │   ├── constants/               # Shared colors and strings
│   │   ├── models/                  # Data models
│   │   ├── providers/               # Riverpod providers
│   │   ├── services/                # Auth, alerts, profiles, storage, audio, data
│   │   └── theme/                   # Theme definitions
│   └── features/
│       ├── alerts/                  # Alert feed, map, reporting, location picker
│       ├── audio/                   # Audio learning
│       ├── auth/                    # Authentication and auth gate
│       ├── education/               # Wildlife education
│       ├── guide/                   # Coexistence guide
│       ├── home/                    # Home dashboard
│       ├── profile/                 # User profile
│       └── shell/                   # Main navigation shell
├── test/                            # Flutter tests
├── pubspec.yaml                     # Dependencies and asset declarations
├── analysis_options.yaml            # Analyzer and lint configuration
└── README.md
🚀 Getting Started
Prerequisites
Install and configure:
- Flutter stable with Dart 3.3 or newer
- Android Studio and Android SDK
- Java 17 for Android builds
- An Android emulator or physical Android device
- A Firebase project for cloud-backed features
Verify your setup:
flutter doctor
flutter --version
1. Get the code
git clone <YOUR_GITHUB_REPOSITORY_URL>
cd <YOUR_REPOSITORY_FOLDER>
Replace the placeholders with your actual repository URL and folder
name.
2. Install dependencies
From the project root:
flutter pub get
3. Configure Firebase
1. Create or select a project in the Firebase Console.
2. Enable Email/Password authentication.
3. Enable Anonymous authentication if community sign-in is needed.
4. Create a Cloud Firestore database.
5. Enable Firebase Storage.
6. Configure the app with your own Firebase project.
Install and run FlutterFire CLI if needed:
dart pub global activate flutterfire_cli
flutterfire configure
This generates platform configuration for your Firebase project. Verify
lib/firebase_options.dart before running the app.
4. Run the application
flutter run
To choose a device:
flutter devices
flutter run -d <device-id>
5. Build an Android APK
flutter build apk
Release note: The current Android release signing setup uses debug
signing. Configure a proper release keystore before distributing the
application.

🔥 Firebase Data Model
alerts/{alertId}
  Field           Type            Purpose
  animalType    String          Animal involved in the sighting
  description   String          Report details
  createdAt     Timestamp       Creation time
  expiresAt     Timestamp       Expiration time
  reportedBy    String          Reporter display name or community label
  imageUrl      String          Optional image URL or fallback data URL
  latitude      Number / null   Optional latitude
  longitude     Number / null   Optional longitude
New alerts expire six hours after creation. Active alerts are read from
Firestore and ordered by expiration time.
users/{uid}
  Field                Type     Purpose
  uid                String   Firebase Authentication user ID
  email              String   Account email
  displayName        String   User display name
  phoneNumber        String   Optional phone number
  village            String   Village or community
  emergencyContact   String   Emergency contact information
Anonymous users do not receive the regular persisted profile stream.
🧩 State Management and Services
- app_providers.dart connects Firebase clients and app services
  through Riverpod.
- AuthService handles sign-in, anonymous authentication, auth-state
  changes, and sign-out.
- AlertService creates, reads, updates, deletes, and cleans up alert
  records.
- ProfileService watches and updates user profiles.
- StorageService uploads sighting images.
- LocalDataService reads and decodes bundled JSON content.
- AudioService manages the shared just_audio player and its
  lifecycle.
- AppTheme and AppColors centralize visual styling.
🧪 Development and Quality Checks
Run these commands before sharing changes:
# Format Dart code
dart format lib test

# Analyze the project
flutter analyze

# Run tests
flutter test

# Run all useful pre-PR checks
dart format --output=none --set-exit-if-changed lib test
flutter analyze
flutter test
The project uses flutter_test and flutter_lints. Tests can be
expanded to cover authentication transitions, local JSON parsing, alert
serialization and expiry, profile updates, and navigation.
⚙️ Configuration Notes
- Android application ID: com.example.internpro
- Android compile and target SDK: 34
- Android Java and Kotlin compilation targets: Java 17
- Firebase configuration includes Android and web platform entries.
- Map functionality may require provider-specific configuration or API
  keys.
- Image selection may require camera or photo permissions depending on
  enabled functionality.
- The app's displayed name is Karunada-Vanya, while its package name
  remains internpro.
- A separate projectone/backend directory contains a Python
  candidate-search prototype; it is not integrated with the Flutter
  app.
🔐 Safety, Privacy, and Production Readiness
This app provides general wildlife-safety education and community
reporting. It does not replace local forest-department instructions,
emergency services, trained wildlife responders, or medical care.
Before production deployment
- Replace permissive development Firestore rules with tested,
  least-privilege rules.
- Restrict profile access so users can only access their own profile.
- Define clear policies for anonymous reporting, moderation, and alert
  deletion.
- Validate uploaded images and review the data-URL fallback behavior.
- Consider a scheduled backend cleanup process for expired alerts.
- Configure production Android signing before release.
- Expand automated tests around Firebase-dependent workflows.
- Review privacy implications before displaying exact wildlife
  coordinates or personal details.
- Add accessibility, localization, offline alert caching, push
  notifications, and emergency escalation as appropriate.
Safety reminders for users - Keep a safe distance from wildlife. -
Do not chase, feed, trap, or attempt to handle animals. - Avoid posting
sensitive personal information in public reports. - Treat exact wildlife
locations as potentially sensitive. - Contact the appropriate local
authority during a dangerous encounter or medical emergency.
🛣️ Future Improvements
- [ ] Strengthen and test Firestore and Storage security rules.
- [ ] Add alert moderation and duplicate-report prevention.
- [ ] Introduce reliable scheduled expiry cleanup.
- [ ] Add bundled wildlife audio and image content.
- [ ] Expand automated tests for core workflows.
- [ ] Improve accessibility and localization.
- [ ] Add offline access to recent alerts.
- [ ] Consider push notifications for relevant nearby alerts.
- [ ] Document map-provider configuration for each target platform.
👨‍💻 Developer
Shashank C A
Built with Flutter, Dart, Riverpod, and Firebase to explore wildlife
education, community reporting, mobile state management, and
cloud-backed application development.
::: {align="center"}
**🌿 Karunada-Vanya --- Learn about wildlife. Share responsibly. Coexist
safely.**
:::

# Karunadu Vanya

Karunadu Vanya is a wildlife awareness and safety Android application designed to help people living near forests, rural communities, and travelers stay informed about local wildlife. The app provides educational animal profiles, safety guidance, alert-style updates, and location-aware support through a friendly mobile interface.

## Abstract
Karunadu Vanya offers a realtime-capable mobile platform for wildlife education and safety. Users can browse animal profiles, read species-specific safety guidance, access alert summaries, and use location-aware resources to improve preparedness and reduce the risk of unsafe encounters with wild animals.

## Introduction
Wild animals and communities frequently share landscapes. People living near forest borders often lack quick access to reliable information about animal behavior and safety. Karunadu Vanya bridges this gap by delivering concise, practical guidance and awareness content on Android devices.

## Problem Statement
Communities near wildlife zones commonly face:

- Lack of timely wildlife safety information
- Unsafe responses to animal sightings
- Delayed reporting and poor situational awareness
- Difficulty finding trustworthy guidance in local languages

Karunadu Vanya addresses these issues with organized educational content, alert-style notices, and location-aware features.

## Project Objectives
- Build a user-friendly Android app for wildlife awareness
- Provide species profiles and behavior descriptions
- Publish practical safety guidance for common wildlife encounters
- Support location-aware guidance and alert presentation
- Demonstrate modern Kotlin + Android development practices

## Vision
Create a low-friction digital tool that empowers rural and forest-adjacent communities to better understand wildlife, react safely during encounters, and report incidents that improve local conservation and safety outcomes.

## Key Features

- Wildlife Education: Animal profiles with images, behavior notes, and habitat details.
- Safety Guides: Clear, species-specific precautions and do/don't instructions.
- Alerts / Awareness: Sections to surface important notices or locally relevant alerts.
- Location Support: Ability to use device/location picker to make guidance more relevant.
- Sounds & Media: Animal sound resources for recognition and awareness training.
- Authentication: Basic login/signup flows to manage user access and preferences.

## Technologies Used

- Kotlin (Android) — primary language for app code
- Android Studio — IDE and build tooling
- XML Layouts — UI structure and responsive screens
- Material Design Components — modern UI elements
- (Optional/Planned) Firebase — Authentication, Firestore, Storage for cloud data and images

## Project Structure (high level)

- `app/` — Android application module (`com.karunadavanya` namespace)
- `app/src/main/res/` — layouts, drawables, strings (e.g., `strings.xml` includes `animal_types`, `alerts`, `education`)
- `app/src/main/assets/` — bundled animal data (`wildlife_data.json`) used for education and safety tips
- `osmdemo/` — sample/map module (optional OpenStreetMap demo integration)

## Screens & User Flow

- Login Screen — phone/email-based authentication and profile entry
- Home Dashboard — quick access to Education, Alerts, Guide, Sounds
- Education Screen — RecyclerView of animal cards (image, description, safety tips)
- Safety Guide Screen — curated guidance items for common species and situations
- Location Picker — pick a location to make guidance/alerts contextual

## Installation & Run (developer)

1. Open the project in Android Studio.
2. Ensure Android SDK and Kotlin plugin are installed (compileSdk 34 recommended).
3. Build and run on an emulator or device:

```bash
./gradlew assembleDebug
``` 

or use Android Studio Run > App.

## Data & Assets
- `app/src/main/assets/wildlife_data.json` contains bundled species data and safety tips used by the Education and Guide screens.
- Images and sound files are stored under `app/src/main/res/drawable` and `app/src/main/res/raw` respectively.

## Challenges & Solutions

- Firebase configuration and sync timing can be a source of early issues — ensure `google-services.json` and correct dependencies are present.
- RecyclerView performance may require image caching (use libraries such as Glide or Coil) and adapter optimizations.
- Null pointer and navigation exceptions can be reduced with validation, safe navigation patterns, and robust error handling.

## Impact Goals

- Increase public awareness about wildlife behavior and safety.
- Reduce unsafe reactions during animal sightings.
- Support local conservation efforts by simplifying reporting and awareness.

## Future Enhancements

- GPS-based sighting mapping and heatmaps
- AI-based animal recognition (camera/photo)
- Regional language localization and voice guidance
- Offline mode for low-connectivity regions
- Push notifications for urgent local alerts
- Integration with local forest/wildlife authorities for incident reporting

## Contributing
Contributions are welcome. Please open issues or pull requests for bug fixes, content updates (animal data), or new features.

## License & Contact
This repository does not include a license by default. Add a license file if you want to make the project open-source. For questions and collaboration, contact the project maintainer in the repo (e.g., via GitHub profile).

---

File: `README.md` added to project root.

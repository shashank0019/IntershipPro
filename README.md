# 🐘 Karunadu Vanya

**Karunadu Vanya** is an Android wildlife awareness and safety application developed during my **Android App Developer Internship at Mind Matrix**.

The application is designed to help people living near forests, rural communities, and travelers stay informed about local wildlife. It provides wildlife education, safety guidance, alert-style information, and location-aware support through a user-friendly Android interface.

---

## 📌 Internship Project

**Organization:** Mind Matrix  
**Role:** Android App Developer Intern  
**Project:** Karunadu Vanya  
**Platform:** Android

---

## 📖 About the Project

People living near forest areas and wildlife zones may not always have quick access to reliable information about animal behavior and safety precautions.

**Karunadu Vanya** was developed to provide users with simple and practical wildlife information through a mobile application.

The application allows users to explore animal profiles, understand wildlife behavior, read safety instructions, view awareness alerts, and access location-related support.

---

## 🎯 Problem Statement

Communities near wildlife areas may face challenges such as:

- Lack of quick access to wildlife safety information
- Limited awareness about animal behavior
- Unsafe responses during wildlife encounters
- Difficulty accessing reliable safety guidance
- Limited situational awareness during animal sightings

Karunadu Vanya addresses these challenges through organized wildlife education and safety information.

---

## 🚀 Key Features

### 🐘 Wildlife Education
- Animal profiles
- Animal images
- Behavior information
- Habitat details
- Wildlife awareness content

### 🛡️ Safety Guides
- Species-specific safety information
- Do's and Don'ts
- Guidance for common wildlife encounters

### 🚨 Alerts & Awareness
- Awareness notices
- Alert-style information
- Important wildlife-related updates

### 📍 Location Support
- Location-aware features
- Location selection for relevant guidance and information

### 🔊 Sounds & Media
- Animal sound resources
- Wildlife media for awareness and recognition

### 🔐 Authentication
- Login and signup
- User access management

---

## 🛠️ Technologies Used

- **Kotlin** – Android application development
- **Android Studio** – Development environment
- **XML** – UI layouts
- **Material Design** – User interface components
- **Firebase** – Authentication and cloud services
- **Cloud Firestore** – Data management
- **Firebase Storage** – Image/media storage
- **RecyclerView** – Displaying wildlife information

---

## 🏗️ Application Architecture

```text
                Android Application
                        │
                        ▼
              ┌──────────────────┐
              │   XML UI / Views │
              └────────┬─────────┘
                       │
                       ▼
              ┌──────────────────┐
              │ Kotlin App Logic │
              └────────┬─────────┘
                       │
            ┌──────────┼──────────┐
            ▼          ▼          ▼
       Firebase    Firestore   Storage
      Authentication   │          │
                       ▼          ▼
                    Wildlife Data

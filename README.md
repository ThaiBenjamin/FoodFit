# 🥗 FoodFit

An Android health and fitness app that helps users discover meal plans, track nutrition, set goals, and access curated health resources — all from their phone.

![Java](https://img.shields.io/badge/Java-Android-ED8B00?logo=java&logoColor=white)
![Android](https://img.shields.io/badge/Android-API_24%2B-3DDC84?logo=android&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-Auth-FFCA28?logo=firebase&logoColor=black)
![Gradle](https://img.shields.io/badge/Gradle-Build_Tool-02303A?logo=gradle&logoColor=white)

---

## 📱 Demo

[![Watch the demo](https://img.shields.io/badge/YouTube-Demo_Video-FF0000?logo=youtube&logoColor=white)](https://youtu.be/g4tDquv13TQ?si=G4sGuxvuXbFavCMp)

---

## ✨ Features

- **User Authentication** — Secure sign-in and registration via Firebase Authentication
- **Goal Tracking** — Set personal fitness goals and mark them complete as you progress
- **Recipes & Nutrition** — Browse food recipes with nutritional facts and calorie information
- **Health Information** — Access curated general health and fitness guides
- **Additional Resources** — In-app browser links to external health and fitness websites
- **Personalized Onboarding** — Profile setup on first launch to tailor the experience

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java | Primary language |
| Android SDK (API 24+) | Mobile platform |
| Android Studio | IDE |
| Firebase Authentication | User sign-in & registration |
| Glide | Image loading |
| Unirest Java | HTTP API calls |
| Gradle | Build system |

---

## 🚀 Setup & Running

### Prerequisites
- Android Studio (Flamingo or newer)
- Java 17+ (JDK)
- An Android device or emulator running API 24+

### Installation

```bash
git clone https://github.com/ThaiBenjamin/FoodFit.git
```

1. Open Android Studio
2. Select **Open an existing project** and choose the `FoodFit` folder
3. Wait for Gradle sync to complete
4. Run on an emulator or connected device via the **Run** button

> **Note:** Firebase configuration (`google-services.json`) is included in the repo. If you fork this project, replace it with your own Firebase project config.

---

## 🧠 What I Built and Why

I built FoodFit as my first full Android app to learn the full mobile development lifecycle — from designing multi-screen navigation flows to integrating backend services like Firebase Authentication.

The idea came from wanting a lightweight companion app for people just getting started with fitness who don't need a complex calorie-counting tool, but do want a place to track goals and find recipes. Building it taught me how Android Activities communicate, how to manage UI state across screens, and how Firebase Authentication simplifies user management without maintaining a backend server. The most challenging part was wiring up the API calls for nutrition data and rendering it cleanly in a user-friendly layout.

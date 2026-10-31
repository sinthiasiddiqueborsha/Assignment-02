# CSE 489: Mobile Application Development - Assignment 2

An Android application developed in Kotlin using Android Studio, implementing core Android components and Jetpack libraries as specified for Assignment 2.

---

## Features & Implementation Overview

### 1. Navigation Drawer Architecture
- Built using Android Jetpack **Navigation Component** (`NavHostFragment`, `NavController`) and **Material Design** (`DrawerLayout`, `NavigationView`, `Toolbar`).
- Contains four menu items:
  1. **Broadcast Receiver**
  2. **Image Scale**
  3. **Video**
  4. **Audio**
- Each menu item navigates to its corresponding functional screen seamlessly.

### 2. Broadcast Receiver Flow
- **Screen 1 (`BroadcastReceiverFragment`)**: Spinner with options:
  - *Custom Broadcast Receiver*
  - *System Battery Notification Receiver*
  - A "Proceed" button to navigate to the selected second screen.
- **Custom Broadcast Flow**:
  - **Second Activity (`CustomInputActivity`)**: EditText for plain text input and a button to send the message.
  - **Third Activity (`CustomReceiverActivity`)**: Registers a custom `BroadcastReceiver` (using API-safe `ContextCompat.registerReceiver`) to receive and display the custom message.
- **System Battery Notification Flow**:
  - **Second Activity (`BatteryInfoActivity`)**: Dynamically registers a receiver for `Intent.ACTION_BATTERY_CHANGED` (sticky broadcast) to calculate and display the current battery percentage.
  - **Third Activity (`BatterySummaryActivity`)**: Performs no additional operation as specified.

### 3. Image Scale
- **Screen (`ImageScaleFragment`)**:
  - Loads an image from the internet using **Glide**.
  - Displays the image inside a **PhotoView**, allowing users to smoothly zoom in/out using a two-finger pinch gesture, pan, and double-tap zoom.
  - Handles loading states and internet errors gracefully.

### 4. Video Player
- **Screen (`VideoFragment`)**:
  - Plays a sample video using `VideoView` and Android's built-in `MediaController`.
  - Includes basic playback controls: play, pause, and seek.
  - Properly manages playback lifecycle (`onPause`, `onDestroyView`).

### 5. Audio Player
- **Screen (`AudioFragment`)**:
  - Plays a sample audio stream using `MediaPlayer`.
  - Includes Play and Pause buttons, along with a real-time `SeekBar` for tracking and seeking audio progress.
  - Properly releases media resources on exit.

---

## Tech Stack & Libraries
- **Language**: Kotlin 1.9
- **UI Architecture**: XML Layouts, Material 3, ViewBinding, Fragments
- **Navigation**: AndroidX Navigation Component (Navigation Drawer)
- **Media**: MediaPlayer, VideoView, MediaController
- **Image Loading**: Glide
- **Zooming**: PhotoView (`com.github.chrisbanes:PhotoView`)

---

## How to Run
1. Open the project folder in **Android Studio**.
2. Click **"Sync Now"** when prompted to download Gradle dependencies.
3. Select an Android Emulator or connected physical device.
4. Click the green **Run (▶)** button to build and launch the app.

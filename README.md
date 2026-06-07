# First30 – Game-Based College Students Management System 🎮🎓

**First30** is an immersive, gamified mobile application designed to transform the first 30 days of college into a guided adventure journey. This app was created especially for freshers to guide them and relieve their stress during their first year at **Dr. N.G.P. Institute of Technology (DRNGPIT)**.

The app helps students navigate the campus, understand complex procedures, meet peers with similar interests, and earn rewards while building confidence and independence.

## 🌟 Key Features

### 🕹️ Gamified Dashboard
- **Character Stats**: Track your XP, Level (Explorer → Survivor → Achiever), and Rank.
- **Quest Log**: Daily missions like "Visit Block B" or "Library Sync" to help you adapt.
- **Badges**: Unlock special military-style badges for completing milestones.
- **Potential Allies**: Find friends with similar interests like Coding, AI, Music, or Sports.

### 🗺️ Tactical World Map (30-Day Journey)
- Visual 30-day progression across four regions: **Survival, Connection, Growth, and Future**.
- Interactive level nodes that reveal daily objectives and mission logs.

### 📍 Tactical Overlay (Campus Navigation)
- Integrated **OpenStreetMap (OSM)** centered on the DRNGPIT campus.
- Real-time markers for Block A, Block B, Central Library, Auditorium, and Canteens.
- **Emergency "I AM LOST" Protocol**: Quick-access rescue button for immediate navigation help.

### 📚 Knowledge Base (Procedural Guides)
- Detailed "Mission Briefings" for college procedures like Fee Payment, Scholarship applications, and ID Card protocols.
- Lists required "Loot" (documents) and "Time Limits" (deadlines).

### 🤖 NGP-Droid (AI Assistant)
- Context-aware AI assistant to answer questions like "Where is my classroom?" or "How do I apply for a scholarship?".
- Quick-tap suggestion chips for common queries.

## 🛠️ Tech Stack

- **Frontend**: Kotlin, Jetpack Compose (Modern Declarative UI)
- **Navigation**: Jetpack Navigation Compose
- **Design**: Material 3 (Custom "Gamer" Theme)
- **Maps**: OSMDroid (OpenStreetMap)
- **Data**: Centralized Mock System (Ready for API integration)

## 🚀 Getting Started

### Prerequisites
- Android Studio Ladybug or later
- JDK 17+
- Android Device/Emulator (API 24+)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Kishkindhan-A/game-based-college-students-management-system.git
   ```
2. Open the project in **Android Studio**.
3. Sync the project with **Gradle files**.
4. Run the app on your device/emulator.

## 📂 Project Structure
- `ui/screens/dashboard`: Player stats, active quests, and friend finder.
- `ui/screens/journey`: The 30-day World Map and daily mission logs.
- `ui/screens/map`: OpenStreetMap tactical overlay with campus nodes.
- `ui/screens/assistant`: AI Chat interface (NGP-Droid).
- `ui/screens/procedures`: Detailed knowledge base for college protocols.
- `data/`: Models and DRNGPIT-specific mock data.

## 🎯 Success Metrics
- 80% onboarding completion rate.
- 50% reduction in student confusion.
- Enhanced student engagement through gamification.

---
*Developed to make the first 30 days of college an epic win.* 🏆

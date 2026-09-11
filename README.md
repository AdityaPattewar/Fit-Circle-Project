<p align="center">
  <img src="fit-circle-banner.png" alt="Fit-Circle Banner">
</p>

# 🏋️ Fit-Circle

> A fitness and community platform that connects users with fitness clubs, activities, challenges, and a supportive fitness community.

## 📌 About the Project

**Fit-Circle** is a JavaFX-based fitness and community platform designed to bring users, fitness clubs, and administrators together in one place.

The platform provides an interactive environment where users can explore clubs, participate in activities and challenges, track their progress, and connect with the fitness community.

## ✨ Key Features

- 👤 User registration and login
- 🏋️ Explore and join fitness clubs
- 👥 Fitness community
- 🏆 Challenges and progress tracking
- 📊 User activity and leaderboard
- 📢 Club announcements
- 📅 Club activities and events
- 💳 Online membership/payment integration
- 🤖 AI-powered assistance
- 🛡️ Admin management system
- 🏢 Club Owner management system
- ☁️ Cloudinary image upload
- 🔥 Firebase/Firestore integration

## 🤖 AI Assistant

Fit-Circle includes an AI-powered assistant "Kaira" that can provide users with fitness-related guidance and recommendations.

The AI feature is integrated using the **Grok API**.

## 👥 Platform Modules

### 👤 User Side

Users can:

- Explore fitness clubs
- Join clubs
- View activities and events
- Participate in challenges
- Track progress
- View leaderboard
- Interact with the community
- Get AI-powered assistance
- Manage their profile

### 🏢 Club Owner Side

Club owners can:

- Create and manage clubs
- Manage club members
- Create activities
- Create events
- Post announcements
- Manage staff
- View member information

### 🛡️ Admin Side

Administrators can:

- Manage users
- Manage clubs
- Monitor platform activities
- Manage club owners
- View reports
- Manage platform content

## 💳 Payment Integration

Fit-Circle integrates **Razorpay** for online payments and membership-related transactions.

API credentials are stored using environment variables and are **not included in this repository**.

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Core application development |
| JavaFX | Desktop UI |
| Maven | Dependency management |
| Firebase / Firestore | Database and backend services |
| Razorpay | Payment integration |
| Cloudinary | Image storage |
| Grok API | AI assistance |
| Git & GitHub | Version control |

## 📂 Project Structure

```text
Fit-Circle-Project/
│
├── fit-circle/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/flexforce/
│   │       │       ├── Services/
│   │       │       ├── config/
│   │       │       ├── controller/
│   │       │       ├── dao/
│   │       │       ├── model/
│   │       │       └── view/
│   │       │
│   │       └── resources/
│   │           └── assets/
│   │
│   └── pom.xml
│
├── .gitignore
└── README.md

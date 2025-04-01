
```markdown
# 🍽️ Restaurant Rating System

A full-stack restaurant review and rating platform that offers a clean experience for diners and powerful moderation tools for administrators. The project features a native Android app for users, a Vue-based admin dashboard, and a Spring Boot + MySQL backend.

---

## 📲 Features Overview

### 🧑‍🍳 User-Side Mobile App (Android)
- User registration and login
- Browse nearby restaurants
- Search by keyword
- View details, images, ratings, and categories
- Post star ratings and text reviews
- Reply to comments
- Report inappropriate content
- Manage favorite restaurants

### 🛠️ Admin Web Panel (Vue.js)
- Admin login with role-based access
- View and manage restaurant entries
- Moderate user reviews and handle reports
- Ban users or delete inappropriate comments
- Import data from Yelp Fusion API
- Export restaurant and review data to Excel

### ⚙️ Backend API (Spring Boot)
- Secure RESTful API using Spring Boot and MyBatis
- MySQL for relational data storage
- CSRF protection
- JWT-based authentication
- BCrypt password hashing
- Integration with Yelp API (with duplicate check)

---

```
```
## 📁 Project Structure
restaurant-rating-system/
├── android-client/           # Android app source code
├── admin-dashboard-vue/      # Vue.js admin panel
├── backend-springboot/       # Spring Boot backend
├── database/                 # SQL schema and test data
├── screenshots/              # App images and UI captures
└── README.md
```
```
---

## 🚀 How to Run the Project

### ✅ 1. Start MySQL
- 1. Start the programs Android studio, IntelliJ IDEA Ultimate, Navicat in order
- Open admin-server and admin-ui in the Yelp folder in IntelliJ and run them respectively
- Open android in the Yelp folder in Android studio and change the IP address in String before running
- String IP = "http://192.168.56.1:8080/renren-admin";

- Ensure MySQL server is running
- Default port: `3306`
- Username: `root`
- Password: `root`

You can use Navicat or any MySQL GUI tool to connect:

![Navicat Connection Example](./image.png)

---

### ✅ 2. Run Backend Server

```bash
cd backend-springboot
mvn clean install
java -jar target/restaurant-api.jar
```

---

### ✅ 3. Launch Admin Dashboard

```bash
cd admin-dashboard-vue
npm install
npm run dev
```

---

### ✅ 4. Start Android App

- Open `android-client/` in Android Studio
- Run on emulator or real Android device (Android 6.0+)

📌 **Set proper IP address in Android app:**

1. Press `Win + R`, type `cmd`
2. In terminal:  
   ```bash
   ipconfig
   ```
3. Find your **IPv4 address**, e.g., `http://192.168.56.1:8080/renren-admin`
4. Replace `localhost` in `HttpManager.java` with that IP

> Android physical devices **cannot connect to localhost**, so IP is required.

- Open the android in the Yelp folder in Android studio and change the IP address in String: String IP = "http://192.168.56.1:8080/renren-admin"; then run

---

## 🔐 Default Login Credentials

| Role                | Username | Password |
|---------------------|----------|----------|
| 🛡️ Admin            | admin    | admin    |
| 🍽️ Restaurant Admin | xxxxx    | 123456   |
| 👤 User             | (register) | (register) |
| 🗄️ MySQL Root       | root     | root     |

---

## 🧰 Tech Stack

| Layer         | Technology                             |
|---------------|-----------------------------------------|
| Android App   | Java, Retrofit, Glide                   |
| Web Panel     | Vue 3, Element Plus, Axios              |
| Backend       | Spring Boot, MyBatis, JWT, BCrypt       |
| Database      | MySQL                                   |
| External API  | [Yelp Fusion API](https://www.yelp.com/developers) |

---

## 💡 Future Enhancements

- 🤖 AI/NLP to auto-moderate offensive reviews
- 🧠 Personalized recommendations
- 🗣️ Owner replies to user feedback
- 🔔 Real-time notifications
- 🌍 Multi-language support

---

## 👨‍💻 Contributors

| Name              | Role                                |
|-------------------|-------------------------------------|
| Haozheng Wang     | Integration, Testing, Documentation |
| Ziqi Zhao         | Backend Development, Yelp API       |
| Jietang Hou       | Admin Dashboard (Vue.js)            |
| Roy (Zijie) Zhang | Android UI and Feature Development  |

---

## 📜 License & Data Usage

This project was developed for academic and educational purposes only.  
No real user data is collected or stored.  
Yelp data is accessed via the [Yelp Fusion API](https://www.yelp.com/developers) and used for non-commercial testing only.

---

## 🔗 GitHub Repository

> Feel free to fork, star, or contribute:

📎 [https://github.com/Git-Exercise2/Restaurant-Rating-System](https://github.com/Git-Exercise2/Restaurant-Rating-System)
```

# 🎬 PekkaTV - Movie Database Platform

> A comprehensive movie review and tracking web application developed with Spring Boot and MVC architecture.

![License](https://img.shields.io/badge/License-MIT-blue.svg)
![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green)
![Database](https://img.shields.io/badge/Database-SQL%20Server-red)

## 📖 Abstract

**PekkaTV** is a database-driven movie review web application designed to provide users with a structured platform to browse movies, view detailed analytics, and manage personal interactions. The system allows users to track their watching history, rate movies, and write reviews with spoiler protection features.

Built using a layered **Model-View-Controller (MVC)** architecture, the project emphasizes modular design, data consistency, and extensibility.

## ✨ Key Features

### 🎥 Movie Management
* **Browsing & Discovery:** Browse a vast catalog of movies with efficient pagination.
* **Advanced Filtering:** Filter movies by **Genre**, **Release Year**, and **Duration** using a dynamic side panel.
* **Search:** Quick search functionality by movie title.
* **Actor Profiles:** Detailed views for actors including their biography and filmography.

### 👤 User Interaction & Personalization
* **Authentication System:** Secure Login/Register and Password Management functionality.
* **Personal Lists:**
    * ⭐ **Favorites List**
    * ✅ **Watched List**
    * ⏰ **Watch Later List**
* **Review System:** Users can rate and review movies.
* **Spoiler Protection:** Innovative spoiler management feature where reviews containing spoilers are blurred by default.

### 🎨 User Interface
* **Responsive Design:** Clean and user-friendly interface using Thymeleaf templates.
* **Theme Support:** Toggle between **Light** and **Dark** modes for better accessibility.

## 🏗️ System Architecture & Diagrams

The system architecture is documented through various UML diagrams designed during the development phase.

### 🗄️ Data Modeling
**ER Diagram** & **Database Diagram**
Visualizes the relationships between core entities like Users, Movies, Reviews, and Actors.

![ER Diagram](https://github.com/user-attachments/assets/7aaabbf6-5501-4dae-b9a9-846ca21cd041)

![Database Diagram](https://github.com/user-attachments/assets/3d832eb6-ff39-4fa5-a6ed-a26b420aa21d)

### 🧩 System Design
**Class Diagram** & **Use Case Diagram**
Illustrates the backend structure, service layer interactions, and user interaction flows.

![Class Diagram](https://github.com/user-attachments/assets/b7f70fe2-4fce-401a-9e7c-8d98e2ecc2a1)

![Use Case Diagram](https://github.com/user-attachments/assets/7f6b4faf-9b37-45cc-a40d-af27c1c77709)

### 🔄 Behavioral Views
**Sequence Diagram** & **Activity Diagram**
Details the end-to-end request flow and user navigation paths.

![Sequence Diagram](https://github.com/user-attachments/assets/42195ef7-b89c-4b66-b8f1-0c5066feaa81)

![Activity Diagram](https://github.com/user-attachments/assets/5ce646a7-8a6a-4b61-bc2b-53c01cd67fc9)

## 🛠️ Technology Stack

* **Backend:** Java, Spring Boot (Web, Data JPA, Security).
* **Frontend:** Thymeleaf, HTML5, CSS3, JavaScript.
* **Database:** Microsoft SQL Server.
* **Build Tool:** Maven.

## 📸 Application Screenshots

A glimpse into the user interface and core functionalities.

| Home Page | Movie Detail View |
|:---:|:---:|
| ![Home Page](https://github.com/user-attachments/assets/326b35be-ff6c-4445-964c-ede770b52dda) | ![Movie Detail](https://github.com/user-attachments/assets/0a654a19-4ff4-42e7-bf8e-85c4e11b2d68) |

| Register | User Profile & Lists |
|:---:|:---:|
| ![Register Page](https://github.com/user-attachments/assets/58fef695-1545-4aa6-a9e0-585b14bb1cc4) | ![User Profile](https://github.com/user-attachments/assets/cacee9f0-7420-480c-8917-949ab5b18e5d) |



---

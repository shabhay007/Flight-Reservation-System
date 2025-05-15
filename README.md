# Flight Reservation System

A full-stack Java-based Flight Reservation System with user authentication, seat booking, payment gateway integration, and cloud deployment.

---

## 🚀 Features

* **Role-based login & session management**
* **Flight search and booking** with real-time seat availability
* **Secure Razorpay payment integration**
* **15+ JSP views** and **20+ backend controllers** using MVC architecture
* **Secure logout with cache-control headers**
* **Hibernate ORM** for database operations
* **Externalized configuration** using `.env` and `config.properties`
* **Deployed on AWS Elastic Beanstalk (EC2)** with **MySQL on RDS**
* **100% test coverage** on booking and payment flows

---

## 🛠️ Tech Stack

* **Frontend**: JSP, HTML, CSS
* **Backend**: Java Servlets
* **ORM**: Hibernate
* **Database**: MySQL
* **Payment Gateway**: Razorpay
* **Deployment**: AWS Elastic Beanstalk (EC2 + RDS)

---

## 📦 Setup and Run Locally

### Prerequisites

* JDK 8+
* Maven
* MySQL
* Tomcat server (for local testing)

### 1. Clone the repository

```bash
git clone https://github.com/your-username/FlightReservationSystem.git
cd FlightReservationSystem
```

### 2. Create `.env` file in the root

```env
DB_URL=jdbc:mysql://localhost:3306/flight_booking_db
DB_USERNAME=your_db_user
DB_PASSWORD=your_db_pass
RAZORPAY_KEY_ID=your_razorpay_key
RAZORPAY_KEY_SECRET=your_razorpay_secret
```

### 3. MySQL Setup

* Import the provided SQL dump (if included)
* Or manually create `flight_booking_db` and add necessary tables

### 4. Build and Run

```bash
mvn clean package
```

* Deploy the generated WAR file (`target/*.war`) to Tomcat or AWS Beanstalk

---

## ☁️ Deployment

* Deployed on **AWS Elastic Beanstalk**
* Uses **EC2** for hosting and **RDS (MySQL)** for cloud database
* Environment variables managed in Elastic Beanstalk config

---

## 📌 Why JSP, Servlets, and Hibernate?

This project was built with core Java web technologies to build a strong foundation before transitioning to Spring/Spring Boot.

---

## 🤝 Contributing

Want to contribute? You're welcome!

* Fork this repo
* Create a new branch
* Submit a pull request

---

## 📬 Feedback

Feel free to share suggestions or improvements. Let's build better together.

---

## 📜 License

MIT

---

Made with ❤️ by \Abhay Kumar Sharma – *open for collaboration and learning.*

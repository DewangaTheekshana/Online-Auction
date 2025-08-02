# 🛒 Distributed Online Auction System (Java EE)

This is a real-time, distributed online auction system built using **Java EE** technologies including **EJB**, **JMS**, **WebSockets**, and **Servlets**. It supports manual and auto-bidding features, real-time bid broadcasting, and session-based user authentication — all backed by in-memory data structures.

## 🚀 Features

- User login and session tracking
- Product listing with bid history
- Real-time bid broadcasting using WebSocket
- Auto-bid functionality with max bid logic
- Asynchronous messaging with JMS and Message-Driven Beans
- In-memory data handling using Singleton Session Beans

## 🧰 Technologies Used

- Java EE (Jakarta EE)
- EJB (Stateless, Singleton, Message-Driven)
- JMS (Topic-based messaging)
- WebSocket
- Servlets + JSP
- Gson (for JSON handling)
- NetBeans 21
- Payara Server 6
- JDK 21

## 📁 Project Structure

- `core/` – Contains model classes like `Bid`, `User`, `Product`, `AutoBid`, etc.
- `ejb/` – Stateless and singleton session beans for business logic
- `web/` – JSP pages, servlets, WebSocket broadcasting, and frontend logic

## 🔧 Setup Instructions

1. Clone the repo:
   ```bash
   git clone https://github.com/DewangaTheekshana/Online-Auction.git

Open in NetBeans 21 as an EAR (Enterprise Application) project.

Start Payara Server 6.2024.5 and deploy the EAR.

Access the application via http://localhost:8080/Online-Auction-web/

🧪 Test Credentials
Use the following test users from DataStorageBean:
- Email: kamal@gmail.com | Password: 123
- Email: nimal5@gmail.com | Password: 456  
- Email: kasun@gmail.com  | Password: 789

📝 Sample Auto-Bid Use Case
- Login as one user and enable auto-bid on a product.

- Login as another user and place a manual bid.

- Watch the auto-bidder automatically counter with the next valid bid.

📦 Deployment Environment

- JDK 21

- NetBeans 21

- Payara Server 6.2024.5

- Chrome DevTools (for performance profiling)

- JMeter (for load testing)

📸 Screenshots

Login
<img width="1907" height="950" alt="image" src="https://github.com/user-attachments/assets/0b59c685-2b9b-489a-875f-d6afa370a5c2" />

Home
<img width="1903" height="955" alt="image" src="https://github.com/user-attachments/assets/c538b1cf-454a-4178-a823-44843c2974cc" />

Bid Page
<img width="1901" height="955" alt="image" src="https://github.com/user-attachments/assets/a980dd35-9927-4d80-8d01-211522e75c85" />
<img width="1895" height="851" alt="image" src="https://github.com/user-attachments/assets/e3e9ac6b-689e-4a72-be8a-ec7916fe021f" />

🤝 Contributions
Pull requests are welcome. For major changes, please open an issue first to discuss what you'd like to change.

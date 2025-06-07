<%--
  Created by IntelliJ IDEA.
  User: HUNT GADGETS
  Date: 5/29/2025
  Time: 7:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="lk.jiat.ee.core.model.User" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BidMaster | Premium Auction Platform</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&family=Montserrat:wght@700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body onload="loadProduct();">
<!-- Header -->
<header>
    <div class="header-container">
        <div class="logo">
            <i class="fas fa-gavel"></i>
            <span>BidZone</span>
        </div>
        <nav>
            <ul>
                <li><a href="home.jsp" class="active">Home</a></li>
                <li><a href="#" id="view-auctions">Auctions</a></li>
                <li><a href="#">Categories</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
        </nav>
        <div class="header-actions">
            <div class="search-bar">
                <i class="fas fa-search"></i>
                <input type="text" placeholder="Search auctions...">
            </div>
            <div class="user-actions">
                <a href="#"><i class="fas fa-heart"></i></a>
                <a href="#"><i class="fas fa-bell"></i></a>

                <% if (user == null) { %>
                <!-- Show login button -->
                <a href="index.jsp"><button>Log In</button></a>
                <% } else { %>
                <!-- Show logout button -->
                <form action="SignOut" method="get" style="display:inline;">
                    <button type="submit">Log Out</button>
                </form>
                <% } %>
            </div>
        </div>
    </div>
</header>

<!-- Home Page Content -->
<div id="home-page">
    <!-- Hero Section -->
    <section class="hero">
        <div class="hero-content">
            <h1>Discover Unique Items & Place Your Bids</h1>
            <p>Join thousands of users in our exclusive auction platform where you can find rare items and great deals.</p>
            <a href="#" class="btn">Explore Auctions</a>
            <a href="#" class="btn btn-outline">Start Selling</a>
        </div>
    </section>

    <!-- Featured Auctions -->
    <section class="section">
        <div class="section-header">
            <h2 class="section-title">Featured Auctions</h2>
            <p class="section-subtitle">Discover exclusive items with competitive bidding</p>
        </div>
        <div class="auctions-grid" id="product-main">
            <!-- Auction Card 1 -->
            <div class="auction-card" id="product">
                <div class="card-img">
                    <img id="product-image1" src="" alt="Rolex Watch">
                    <div class="time-left" id="product-time"><i class="fas fa-clock"></i></div>
                </div>
                <div class="card-content">
                    <h3 class="card-title" id="product-name"></h3>
                    <div class="card-meta">
                        <div class="current-bid" id="product-bid-price"></div>
                        <div class="bids-count"></div>
                    </div>
                    <div class="card-actions">
                        <a href="#" id="product-a1" class="btn btn-card btn-primary" data-id="1">Place Bid</a>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Categories -->
    <section class="section categories">
        <div class="section-header">
            <h2 class="section-title">Browse Categories</h2>
            <p class="section-subtitle">Find items you love in our specialized categories</p>
        </div>
        <div class="categories-grid">
            <div class="category-card">
                <i class="fas fa-car"></i>
                <h3>Vehicles</h3>
            </div>
            <div class="category-card">
                <i class="fas fa-home"></i>
                <h3>Real Estate</h3>
            </div>
            <div class="category-card">
                <i class="fas fa-gem"></i>
                <h3>Jewelry</h3>
            </div>
            <div class="category-card">
                <i class="fas fa-paint-brush"></i>
                <h3>Art</h3>
            </div>
            <div class="category-card">
                <i class="fas fa-music"></i>
                <h3>Collectibles</h3>
            </div>
            <div class="category-card">
                <i class="fas fa-laptop"></i>
                <h3>Electronics</h3>
            </div>
        </div>
    </section>

    <!-- How It Works -->
    <section class="section">
        <div class="section-header">
            <h2 class="section-title">How BidMaster Works</h2>
            <p class="section-subtitle">Start bidding in just a few simple steps</p>
        </div>
        <div class="steps">
            <div class="step">
                <div class="step-number">1</div>
                <h3>Create Account</h3>
                <p>Register for free and complete your profile to start bidding.</p>
            </div>
            <div class="step">
                <div class="step-number">2</div>
                <h3>Find Items</h3>
                <p>Browse thousands of items across various categories.</p>
            </div>
            <div class="step">
                <div class="step-number">3</div>
                <h3>Place Your Bid</h3>
                <p>Enter your maximum bid and let our system bid for you.</p>
            </div>
            <div class="step">
                <div class="step-number">4</div>
                <h3>Win & Collect</h3>
                <p>If you have the highest bid when time runs out, you win!</p>
            </div>
        </div>
    </section>
</div>

<!-- Footer -->
<footer>
    <div class="footer-grid">
        <div class="footer-col">
            <h3>About BidMaster</h3>
            <p>BidMaster is a premium auction platform connecting buyers and sellers of unique items across the globe. Join our community today!</p>
            <div class="social-links">
                <a href="#"><i class="fab fa-facebook-f"></i></a>
                <a href="#"><i class="fab fa-twitter"></i></a>
                <a href="#"><i class="fab fa-instagram"></i></a>
                <a href="#"><i class="fab fa-linkedin-in"></i></a>
            </div>
        </div>
        <div class="footer-col">
            <h3>Quick Links</h3>
            <ul class="footer-links">
                <li><a href="#">Home</a></li>
                <li><a href="#">Auctions</a></li>
                <li><a href="#">Categories</a></li>
                <li><a href="#">How It Works</a></li>
                <li><a href="#">Sell on BidMaster</a></li>
            </ul>
        </div>
        <div class="footer-col">
            <h3>Categories</h3>
            <ul class="footer-links">
                <li><a href="#">Vehicles</a></li>
                <li><a href="#">Real Estate</a></li>
                <li><a href="#">Jewelry</a></li>
                <li><a href="#">Art & Collectibles</a></li>
                <li><a href="#">Electronics</a></li>
            </ul>
        </div>
        <div class="footer-col">
            <h3>Contact Us</h3>
            <ul class="footer-links">
                <li><i class="fas fa-map-marker-alt"></i> 123 Auction St, New York, NY</li>
                <li><i class="fas fa-phone"></i> +1 (555) 123-4567</li>
                <li><i class="fas fa-envelope"></i> info@bidzone.com</li>
            </ul>
        </div>
    </div>
    <div class="copyright">
        <p>&copy; 2023 BidZone. All rights reserved.</p>
    </div>
</footer>

<script>
    // Page Navigation
    document.querySelectorAll('.btn-card[data-id]').forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            document.getElementById('home-page').style.display = 'none';
            document.getElementById('single-bid-view').style.display = 'block';
            window.scrollTo(0, 0);
        });
    });

    document.getElementById('back-to-home').addEventListener('click', function(e) {
        e.preventDefault();
        document.getElementById('single-bid-view').style.display = 'none';
        document.getElementById('home-page').style.display = 'block';
    });

    document.getElementById('view-auctions').addEventListener('click', function(e) {
        e.preventDefault();
        document.getElementById('home-page').style.display = 'none';
        document.getElementById('single-bid-view').style.display = 'block';
        window.scrollTo(0, 0);
    });

    // Thumbnail Gallery
    document.querySelectorAll('.thumbnail').forEach(thumb => {
        thumb.addEventListener('click', function() {
            // Update main image
            document.getElementById('main-image').src = this.dataset.src;

            // Update active class
            document.querySelectorAll('.thumbnail').forEach(t => {
                t.classList.remove('active');
            });
            this.classList.add('active');
        });
    });

    // Countdown Timer
    function updateCountdown() {
        // Set end time (3 days from now)
        const endTime = new Date();
        endTime.setDate(endTime.getDate() + 3);

        const now = new Date();
        const diff = endTime - now;

        if (diff <= 0) {
            document.getElementById('time-left').textContent = 'Auction Ended';
            return;
        }

        const days = Math.floor(diff / (1000 * 60 * 60 * 24));
        const hours = Math.floor((diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        const minutes = Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));

        document.getElementById('time-left').textContent =
            `${days}d ${hours}h ${minutes}m`;
    }

    // Update countdown every minute
    updateCountdown();
    setInterval(updateCountdown, 60000);

    // Auction card hover effect enhancement
    document.querySelectorAll('.auction-card').forEach(card => {
        card.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-10px)';
        });

        card.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0)';
        });
    });
</script>
<script src="js/script.js"></script>
</body>
</html>

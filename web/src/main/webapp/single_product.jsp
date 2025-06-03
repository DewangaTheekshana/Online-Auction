<%--
  Created by IntelliJ IDEA.
  User: HUNT GADGETS
  Date: 5/30/2025
  Time: 11:42 AM
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
    <title>BidMaster | Vintage Rolex Submariner</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&family=Montserrat:wght@700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/singleProduct.css">
</head>
<body onload="loadSingleProduct();">
<!-- Header -->
<header>
    <div class="header-container">
        <div class="logo">
            <i class="fas fa-gavel"></i>
            <span>BidMaster</span>
        </div>
        <nav>
            <ul>
                <li><a href="#">Home</a></li>
                <li><a href="#" class="active">Auctions</a></li>
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

<!-- Single Product View Content -->
<div class="single-bid-view">
    <div class="breadcrumb">
        <a href="#"><i class="fas fa-home"></i> Home</a> <span>/</span>
    </div>

    <div class="bid-container">
        <div class="bid-gallery">
            <div class="main-image">
                <div class="auction-badge">Premium Auction</div>
                <img id="main-image" src="" alt="Vintage Rolex Submariner">
            </div>
        </div>

        <div class="bid-details">
            <h1 class="bid-title" id="single-product-title">Vintage Rolex Submariner</h1>
            <p class="bid-subtitle">Reference 5513, Circa 1965 - Original Condition</p>

            <div class="countdown">
                <div class="countdown-item">
                    <div class="countdown-value" id="days">00</div>
                    <div class="countdown-label">Days</div>
                </div>
                <div class="countdown-item">
                    <div class="countdown-value" id="hours">00</div>
                    <div class="countdown-label">Hours</div>
                </div>
                <div class="countdown-item">
                    <div class="countdown-value" id="minutes">00</div>
                    <div class="countdown-label">Minutes</div>
                </div>
                <div class="countdown-item">
                    <div class="countdown-value" id="seconds">00</div>
                    <div class="countdown-label">Seconds</div>
                </div>
            </div>


            <div class="bid-meta">
                <div class="meta-item">
                    <i class="fas fa-tag"></i> Item #: RW-1965-ROLEX
                </div>
                <div class="meta-item">
                    <i class="fas fa-map-marker-alt"></i> London, UK
                </div>
                <div class="meta-item">
                    <i class="fas fa-eye"></i> 245 Views
                </div>
                <div class="meta-item">
                    <i class="fas fa-gavel"></i> 24 Bids
                </div>
            </div>

            <div class="bid-description">
                <h3>Product Description</h3>
                <p>This exceptional vintage Rolex Submariner reference 5513 dates back to approximately 1965. The watch is in remarkable original condition with a beautifully aged patina on the dial and hands. The case remains unpolished with sharp edges, and the bezel insert shows a desirable faded appearance.</p>
                <p>The watch features a glossy gilt dial with gilt chapter ring and meters first depth rating. It comes with its original riveted bracelet, end links, and clasp. The movement is the reliable Rolex Caliber 1530, which has been recently serviced and is keeping excellent time.</p>
            </div>

            <div class="bid-info">
                <div class="info-grid">
                    <div class="info-item">
                        <h4>Current Bid</h4>
                        <p id="currunt-bid-price">$8,450</p>
                    </div>
                    <div class="info-item">
                        <h4>Starting Bid</h4>
                        <p id="start-bid">$5,000</p>
                    </div>
                </div>

                <div class="current-bid-value" id="currunt-bid-price1"><p>$8,450</p></div>

                <div class="bid-tabs">
                    <div class="tab active" data-tab="manual-bid">Manual Bid</div>
                    <div class="tab" data-tab="auto-bid">Auto Bid</div>
                </div>

                <!-- Manual Bid Form -->
                <div class="tab-content active" id="manual-bid">
                    <div class="bid-form">
                        <div class="form-group">
                            <label for="manual-bid-amount" id="messages">Enter Your Bid (Minimum $8,700)</label>
                            <input type="number" id="manual-bid-amount" min="8700" value="8700">
                            <div class="form-note">Your bid must be at least 10 more than the current bid</div>
                        </div>
                        <div class="bid-actions">
                            <% if (user == null) { %>
                                <button class="btn btn-accent" id="bidbutton" onclick="notloging();">Place Bid Now</button>
                            <% } else { %>
                                <button class="btn btn-accent" id="bidbutton" onclick="sendMessage();">Place Bid Now</button>
                            <% } %>
                        </div>
                    </div>
                </div>

                <!-- Auto Bid Form -->
                <div class="tab-content" id="auto-bid">
                    <div class="auto-bid-info">
                        <h4><i class="fas fa-robot"></i> How Auto-Bid Works</h4>
                        <p>Set your maximum bid amount and our system will automatically place bids for you up to that amount. You'll only pay the minimum required to be the highest bidder.</p>
                    </div>
                    <div class="bid-form">
                        <div class="form-group">
                            <label for="auto-bid-amount">Your Maximum Bid</label>
                            <input type="number" id="auto-bid-amount" min="8700" value="10000">
                            <div class="form-note">This is the maximum amount you're willing to pay</div>
                        </div>
                        <div class="bid-actions">
                            <button class="btn btn-accent">Activate Auto-Bid</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="bid-history">
        <h3 class="history-title">Bid History <span>24 bids so far</span></h3>
        <div id="bid-history">
        <div class="history-item">
            <div class="bidder-info">
                <div class="bidder-avatar">JD</div>
                <div>
                    <div class="bidder-name">John Doe <span class="auto-bid-tag">Auto-Bid</span></div>
                    <div class="bid-time">Today, 14:32</div>
                </div>
            </div>
            <div class="bid-amount">$8,450</div>
        </div>
        </div>
    </div>
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
                <li><a href="#">Luxury Watches</a></li>
                <li><a href="#">Fine Art</a></li>
                <li><a href="#">Collectibles</a></li>
                <li><a href="#">Jewelry</a></li>
                <li><a href="#">Vintage Cars</a></li>
            </ul>
        </div>
        <div class="footer-col">
            <h3>Contact Us</h3>
            <ul class="footer-links">
                <li><i class="fas fa-map-marker-alt"></i> 123 Auction St, New York, NY</li>
                <li><i class="fas fa-phone"></i> +1 (555) 123-4567</li>
                <li><i class="fas fa-envelope"></i> info@bidmaster.com</li>
            </ul>
        </div>
    </div>
    <div class="copyright">
        <p>&copy; 2023 BidMaster. All rights reserved.</p>
    </div>
</footer>

<script>
    // Bid form tabs
    document.querySelectorAll('.tab').forEach(tab => {
        tab.addEventListener('click', function() {
            const tabId = this.dataset.tab;

            // Update active tab
            document.querySelectorAll('.tab').forEach(t => {
                t.classList.remove('active');
            });
            this.classList.add('active');

            // Show active content
            document.querySelectorAll('.tab-content').forEach(content => {
                content.classList.remove('active');
            });
            document.getElementById(tabId).classList.add('active');
        });
    });
</script>

<script src="js/script.js"></script>
</body>

</html>

// Form submission
const signinForm = document.getElementById('signinForm');

signinForm.addEventListener('submit', async function (e) {
    e.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const formData = new URLSearchParams();
    formData.append("email", email);
    formData.append("password", password);

    try {
        const response = await fetch("/ee-app/SignIn", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formData.toString()
        });

        const responseText = await response.text();
        console.log("Server response:", responseText);

        if (responseText.trim() === "success") {
            alert("Login successful!");
            window.location = "./home.jsp";
        } else {
            alert(responseText);
        }
    } catch (error) {
        console.error("Fetch error:", error);
        alert("Something went wrong. Try again.");
    }

    // Reset form
    signinForm.reset();
});

function parseEndTimeString(endTimeString) {
    // Remove invalid characters like "?" and ensure it's a parsable format
    const cleaned = endTimeString.replace("?", "").replace("PM", " PM").replace("AM", " AM");
    return new Date(cleaned);
}

async function loadProduct() {

    let ProductHtml = document.getElementById("product");
    document.getElementById("product-main").innerHTML = " ";

    const response = await fetch("/ee-app/LoadProduct");
    if (response.ok) {
        const json = await response.json();
        console.log(json.productList);

        json.productList.forEach(item => {

            let ProductCloneHtml = ProductHtml.cloneNode(true);


            ProductCloneHtml.querySelector("#product-image1").src = item.image;
            ProductCloneHtml.querySelector("#product-name").innerHTML = item.name;
            ProductCloneHtml.querySelector("#product-bid-price").innerHTML = "$" + item.basePrice;
            ProductCloneHtml.querySelector("#product-a1").href = "single_product.jsp?id=" + item.id;

            document.getElementById("product-main").appendChild(ProductCloneHtml);

            const countdownId = `countdown-${item.id}`;
            const countdownElement = ProductCloneHtml.querySelector("#product-time");
            countdownElement.id = countdownId;

            const endTimeStr = item.endTime;
            const parsedEndTime = parseEndTimeString(endTimeStr);
            startCountdownForElement(parsedEndTime, countdownId);
        });
    }
}

function startCountdownForElement(endTime, elementId) {
    const element = document.getElementById(elementId);

    const interval = setInterval(() => {
        const now = new Date();
        const distance = endTime - now;

        if (distance < 0) {
            clearInterval(interval);
            element.innerHTML = `<i class="fas fa-clock">Expired</i>`;
            return;
        }

        const days = Math.floor(distance / (1000 * 60 * 60 * 24));
        const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        const seconds = Math.floor((distance % (1000 * 60)) / 1000);

        element.innerHTML = `<i class="fas fa-clock" style="font-size: 12px"> ${days} d : ${hours} h : ${minutes} m : ${seconds} s </i>`;
    }, 1000);
}


var pid = 0;

async  function loadSingleProduct(){

    const parameters = new URLSearchParams(window.location.search);

    if (parameters.has("id")) {
        const productId = parameters.get("id");

        const response = await  fetch("/ee-app/LoadToSingleProduct?id=" + productId);

        if (response.ok) {

            const json = await response.json();  // Assuming response is fetched correctly
            console.log(json);

            document.getElementById("main-image").src = json.product.image;
            document.getElementById("single-product-title").innerHTML = json.product.name;
            document.getElementById("start-bid").innerHTML = "$" + json.product.basePrice;
            document.getElementById("currunt-bid-price").innerHTML = "$" + json.product.maxBid;
            document.getElementById("currunt-bid-price1").innerHTML = "$" + json.product.maxBid;
            document.getElementById("manual-bid-amount").value = json.product.maxBid + 10;
            document.getElementById("messages").innerText = "Enter Your Bid (Minimum " + (json.product.maxBid + 10) + ")";
            pid = json.product.id;

            const endTimeStr = json.product.endTime;
            console.log("Received endTime:", endTimeStr);

            const parsedEndTime = parseEndTimeString(endTimeStr);
            console.log("Parsed endTime:", parsedEndTime);

            startCountdown(parsedEndTime);

        } else {
            alert("unsuccess");
        }

    } else {
        alert("Select Product");
    }

    loadBidHistory();

    const socket = new WebSocket(`ws://${window.location.host}/ee-app/bidsocket`);


    socket.onopen = ()=>{
        console.log("Connected to WebSocket");
    }

// This is triggered when a message is received from the server
    socket.onmessage = function(event) {
        const bid = JSON.parse(event.data);
        console.log("Message from server:", event.data);
        console.log("socket on message"+ bid);
        document.getElementById("currunt-bid-price").innerText = "$" + bid.bidAmount;
        document.getElementById("currunt-bid-price1").innerText = "$" + bid.bidAmount;
        document.getElementById("manual-bid-amount").value = bid.bidAmount + 10;
        document.getElementById("messages").innerText = "Enter Your Bid (Minimum " + (bid.bidAmount + 10) + ")";

        const listItem = document.createElement("li");
        listItem.textContent = `User ${bid.userId} bid $${bid.bidAmount}`;
        const history = document.getElementById("bid-history");
        history.prepend(listItem); // add to top
        loadBidHistory();
    };

}

function loadBidHistory() {
    fetch(`/ee-app/bid-history?productId=${pid}`)
        .then(response => response.json())
        .then(bids => {
            const historyDiv = document.getElementById('bid-history');
            historyDiv.innerHTML = ''; // Clear previous history

            if (bids.length === 0) {
                historyDiv.innerHTML = "<p>No bids yet.</p>";
                return;
            }

            // Sort by timestamp descending (latest first)
            bids.sort((a, b) => new Date(b.timestamp) - new Date(a.timestamp));

            bids.forEach(bid => {
                const date = new Date(bid.timestamp);
                const time = date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
                const today = new Date().toDateString() === date.toDateString() ? "Today" : date.toLocaleDateString();
                const formattedTime = `${today}, ${time}`;

                const initials = bid.userName
                    .split(' ')
                    .map(n => n[0])
                    .join('')
                    .toUpperCase();

                const entry = document.createElement('div');
                entry.className = 'history-item';
                entry.innerHTML = `
                    <div class="bidder-info">
                        <div class="bidder-avatar">${initials}</div>
                        <div>
                            <div class="bidder-name">${bid.userName}</div>
                            <div class="bid-time">${formattedTime}</div>
                        </div>
                    </div>
                    <div class="bid-amount">$${bid.bidAmount.toLocaleString()}</div>
                `;
                historyDiv.appendChild(entry);
            });
        })
        .catch(error => {
            console.error("Error loading bid history:", error);
        });
}



async function sendMessage() {


    const bidAmount = document.getElementById("manual-bid-amount").value;
    // const productId = 1; // Replace with actual product ID

    const bidData = new URLSearchParams();
    bidData.append("bidAmount", parseFloat(bidAmount));
    bidData.append("productId", pid);

    console.log(bidAmount);

    const response = await fetch("PlaceBidServlet", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: bidData.toString()
    });

    const responseText = await response.text();

}


function startCountdown(endTime) {
    const interval = setInterval(() => {
        const now = new Date();
        const distance = endTime - now;

        if (distance < 0) {
            clearInterval(interval);
            document.getElementById("days").textContent = "00";
            document.getElementById("hours").textContent = "00";
            document.getElementById("minutes").textContent = "00";
            document.getElementById("seconds").textContent = "00";
            // document.getElementById("bidbutton").disabled = true;
            document.getElementById("bidbutton").style.backgroundColor = "#a1a09f";
            document.getElementById("bidbutton").onclick = function() {
                alert("This Bid Event Expired");
            };;
            return;
        }

        const days = Math.floor(distance / (1000 * 60 * 60 * 24));
        const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        const seconds = Math.floor((distance % (1000 * 60)) / 1000);

        document.getElementById("days").textContent = String(days).padStart(2, '0');
        document.getElementById("hours").textContent = String(hours).padStart(2, '0');
        document.getElementById("minutes").textContent = String(minutes).padStart(2, '0');
        document.getElementById("seconds").textContent = String(seconds).padStart(2, '0');


    }, 1000);
}


function notloging(){
    window.location = "./index.jsp";
}
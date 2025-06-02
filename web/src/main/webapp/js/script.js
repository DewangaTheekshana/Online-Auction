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
            ProductCloneHtml.querySelector("#product-time").innerHTML = "2d 1h";
            ProductCloneHtml.querySelector("#product-name").innerHTML = item.name;
            ProductCloneHtml.querySelector("#product-bid-price").innerHTML = "$" + item.basePrice;
            ProductCloneHtml.querySelector("#product-a1").href = "single_product.jsp?id=" + item.id;

            document.getElementById("product-main").appendChild(ProductCloneHtml);
        });
    }
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
            pid = json.product.id;

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
            console.log("resbid"+bids);
            const historyDiv = document.getElementById('bid-history');
            historyDiv.innerHTML = ''; // Clear previous content

            if (bids.length === 0) {
                historyDiv.innerHTML = "<p>No bids yet.</p>";
                return;
            }

            bids.forEach(bid => {
                const entry = document.createElement('div');
                entry.textContent = `User ${bid.userId} placed $${bid.bidAmount}`;
                historyDiv.appendChild(entry);
            });
        })
        .catch(error => {
            console.error("Error loading bid history:", error);
        });
}

async function sendMessage() {


    const bidAmount = document.getElementById("manual-bid-amount").value;
    const productId = 1; // Replace with actual product ID

    const bidData = new URLSearchParams();
    bidData.append("bidAmount", parseFloat(bidAmount));
    bidData.append("productId", productId);

    console.log(bidAmount);
    console.log(productId);

    const response = await fetch("PlaceBidServlet", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: bidData.toString()
    });

    const responseText = await response.text();

}

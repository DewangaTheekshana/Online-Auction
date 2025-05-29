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

            document.getElementById("product-main").appendChild(ProductCloneHtml);
        });
    }
}

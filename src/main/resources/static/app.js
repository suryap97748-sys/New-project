let products = [];
let cart = JSON.parse(localStorage.getItem("cart") || "[]");

async function loadProducts() {
  const q = document.getElementById("search").value;
  const url = q ? "/api/products?search=" + encodeURIComponent(q) : "/api/products";
  const response = await fetch(url);
  products = await response.json();
  render();
}

function render() {
  const box = document.getElementById("products");
  box.innerHTML = products.map(p => `
    <div class="card">
      <h2>${p.name}</h2>
      <p>${p.category || ""}</p>
      <p class="price">₹${Number(p.price).toFixed(2)}</p>
      <p>Stock: ${p.stock}</p>
      <button onclick="addToCart(${p.id})">Add to Cart</button>
    </div>
  `).join("");
  updateCount();
}

function addToCart(id) {
  const p = products.find(x => x.id === id);
  if (!p) return;
  const item = cart.find(x => x.id === id);
  if (item) item.qty++;
  else cart.push({id:p.id, name:p.name, price:p.price, qty:1});
  saveCart();
}

function saveCart() {
  localStorage.setItem("cart", JSON.stringify(cart));
  updateCount();
}

function updateCount() {
  document.getElementById("count").textContent =
    cart.reduce((sum, x) => sum + x.qty, 0);
}

function showCart() {
  document.getElementById("cart").style.display = "block";
  let total = 0;
  const html = cart.map(x => {
    total += x.price * x.qty;
    return `<div class="item">${x.name} × ${x.qty} = ₹${(x.price*x.qty).toFixed(2)}</div>`;
  }).join("");
  document.getElementById("cartItems").innerHTML = html || "Cart is empty";
  document.getElementById("total").textContent = total.toFixed(2);
}

function closeCart() {
  document.getElementById("cart").style.display = "none";
}

function checkout() {
  if (!cart.length) return alert("Cart is empty");
  alert("Order placed successfully!");
  cart = [];
  saveCart();
  closeCart();
}

loadProducts();

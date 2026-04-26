const BASE_URL = "http://localhost:8080/api";

// Save token
function setToken(token) {
    localStorage.setItem("token", token);
}

// Get token
function getToken() {
    return localStorage.getItem("token");
}

// Logout
function logout() {
    localStorage.removeItem("token");
    window.location.href = "index.html";
}
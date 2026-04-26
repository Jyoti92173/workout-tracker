// ================= AUTH GUARD =================
if (window.location.pathname.includes("dashboard.html")) {
    if (!getToken()) {
        window.location.href = "index.html";
    } else {
        loadWorkouts();
    }
}

// ================= LOAD WORKOUTS =================
async function loadWorkouts() {
    const token = getToken();

    try {
        const response = await fetch(`${BASE_URL}/workouts`, {
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        const workouts = await response.json();

        const container = document.getElementById("workoutList");
        container.innerHTML = "";

        workouts.forEach(w => {
            container.innerHTML += `
                <div style="background:white; padding:10px; margin:10px 0;">
                    <h4>${w.name}</h4>
                    <p>Duration: ${w.duration} mins</p>
                    <button onclick="deleteWorkout(${w.id})">Delete</button>
                </div>
            `;
        });

    } catch (error) {
        console.error("Error loading workouts", error);
    }
}

// ================= ADD WORKOUT =================
async function addWorkout() {
    const token = getToken();

    const name = document.getElementById("name").value;
    const duration = document.getElementById("duration").value;

    try {
        await fetch(`${BASE_URL}/workouts`, {
            method: "POST",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ name, duration })
        });

        loadWorkouts();

    } catch (error) {
        console.error("Error adding workout", error);
    }
}

// ================= DELETE WORKOUT =================
async function deleteWorkout(id) {
    const token = getToken();

    try {
        await fetch(`${BASE_URL}/workouts/${id}`, {
            method: "DELETE",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        loadWorkouts();

    } catch (error) {
        console.error("Error deleting workout", error);
    }
}
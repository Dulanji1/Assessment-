const API_URL = "http://localhost:8080/api/tasks";

const tasksContainer = document.getElementById("tasksContainer");
const addBtn = document.getElementById("addBtn");
const titleInput = document.getElementById("title");
const descInput = document.getElementById("description");

// Fetch latest 5 tasks
async function fetchTasks() {
  try {
    const res = await fetch(API_URL);
    const tasks = await res.json();

    // Show only latest 5
    const recentTasks = tasks.slice(-5).reverse();

    tasksContainer.innerHTML = "";
    recentTasks.forEach(task => {
      const taskEl = document.createElement("div");
      taskEl.className = "task";
      taskEl.innerHTML = `
        <div class="task-info">
          <strong>${task.title}</strong>
          <small>${task.description}</small>
        </div>
        <button onclick="markDone(${task.id})">Done</button>
      `;
      tasksContainer.appendChild(taskEl);
    });
  } catch (err) {
    console.error("Error fetching tasks:", err);
  }
}

// Add new task
addBtn.addEventListener("click", async () => {
  const title = titleInput.value.trim();
  const description = descInput.value.trim();

  if (!title || !description) {
    alert("Please enter title and description");
    return;
  }

  try {
    await fetch(API_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ title, description })
    });

    titleInput.value = "";
    descInput.value = "";
    fetchTasks();
  } catch (err) {
    console.error("Error adding task:", err);
  }
});

// Mark task as done
async function markDone(id) {
  try {
    await fetch(`${API_URL}/${id}/done`, { method: "POST" });
    fetchTasks();
  } catch (err) {
    console.error("Error marking done:", err);
  }
}

// Load on start
fetchTasks();

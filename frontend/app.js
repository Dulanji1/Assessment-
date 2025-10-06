const API = "/api/tasks";

async function fetchTasks(){
  const res = await fetch(API);
  const data = await res.json();
  const container = document.getElementById('tasks');
  container.innerHTML = '';
  data.forEach(t=>{
    const el = document.createElement('div');
    el.className = 'task';
    el.innerHTML = `<div>
      <h3>${escapeHtml(t.title)}</h3>
      <p class="small">${escapeHtml(t.description || '')}</p>
      <div class="small">Created: ${new Date(t.createdAt).toLocaleString()}</div>
    </div>
    <div class="card-actions">
      <button data-id="${t.id}">Done</button>
    </div>`;
    container.appendChild(el);
  });
  document.querySelectorAll('.card-actions button').forEach(b=>{
    b.onclick = async (e)=>{
      const id = b.getAttribute('data-id');
      await fetch(API + "/" + id + "/done", { method: 'POST' });
      await fetchTasks();
    }
  });
}

function escapeHtml(s){ return s.replaceAll('&','&amp;').replaceAll('<','&lt;').replaceAll('>','&gt;'); }

document.getElementById('task-form').onsubmit = async (e)=>{
  e.preventDefault();
  const title = document.getElementById('title').value.trim();
  const description = document.getElementById('description').value.trim();
  if(!title) return alert('Title required');
  await fetch(API, { method:'POST', headers: {'Content-Type':'application/json'}, body: JSON.stringify({ title, description })});
  document.getElementById('title').value = '';
  document.getElementById('description').value = '';
  await fetchTasks();
}

fetchTasks();

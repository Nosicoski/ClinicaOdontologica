// Referencias a elementos del DOM
const tableBody = document.querySelector("#TurnosTable tbody");
const editModal = new bootstrap.Modal(document.getElementById("editModal"));
const editForm = document.getElementById("editForm");
const fechaInput = document.getElementById("fecha");

let currentTurnoId;

// Función para listar turnos
function fetchTurnos() {
  fetch("Turno/listar")
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error en la respuesta del servidor: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      tableBody.innerHTML = ""; // Limpiar la tabla

      data.forEach((turno) => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${turno.pacienteId || "N/A"}</td>
          <td>${turno.odontologoId || "N/A"}</td>
          <td>${turno.fecha || "N/A"}</td>
          <td>${turno.id || "N/A"}</td>
          <td>
            <button class="btn btn-primary btn-sm" onclick="editTurno(${turno.id}, '${turno.pacienteId}', '${turno.odontologoId}', '${turno.fecha}')">Modificar</button>
            <button class="btn btn-danger btn-sm" onclick="deleteTurno(${turno.id})">Eliminar</button>
          </td>
        `;
        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error al listar los turnos:", error);
      alert("Hubo un error al listar los turnos. Verifique el servidor.");
    });
}

// Función para abrir el modal y cargar datos del turno
function editTurno(id, pacienteId, odontologoId, fecha) {
  currentTurnoId = id;
  document.getElementById("editApellido").value = pacienteId || "";
  document.getElementById("editNombre").value = odontologoId || "";
  fechaInput.value = fecha || "";
  editModal.show();
}

// Manejo del formulario de edición
editForm.addEventListener("submit", function (event) {
  event.preventDefault();

  const pacienteId = document.getElementById("editApellido").value;
  const odontologoId = document.getElementById("editNombre").value;
  const fecha = fechaInput.value;

  fetch(`Turno/actualizar/${currentTurnoId}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ pacienteId, odontologoId, fecha }),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error al modificar el turno: ${response.status}`);
      }
      return response.json();
    })
    .then(() => {
      alert("Turno modificado con éxito");
      fetchTurnos(); // Actualizar la tabla
      editModal.hide();
    })
    .catch((error) => {
      console.error("Error al modificar el turno:", error);
      alert("Hubo un error al modificar el turno. Intente nuevamente.");
    });
});

// Función para eliminar un turno
function deleteTurno(id) {
  if (confirm("¿Está seguro de que desea eliminar este turno?")) {
    fetch(`Turno/eliminar/${id}`, { method: "DELETE" })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Error al eliminar el turno: ${response.status}`);
        }
        return response.json();
      })
      .then(() => {
        alert("Turno eliminado con éxito");
        fetchTurnos(); // Actualizar la tabla
      })
      .catch((error) => {
        console.error("Error al eliminar el turno:", error);
        alert("Hubo un error al eliminar el turno. Intente nuevamente.");
      });
  }
}

// Inicializar la tabla de turnos al cargar la página
document.addEventListener("DOMContentLoaded", fetchTurnos);

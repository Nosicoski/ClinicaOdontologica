// Obtener la referencia a la tabla y al modal
const tableBody = document.querySelector("#OdontologoTable tbody");
const editModal = new bootstrap.Modal(document.getElementById("editModal"));
const editForm = document.getElementById("editForm");
let currentOdontologoId;

function fetchOdontologo() {
  // Listar los odontólogos
  fetch(`Odontologo/ListarTodos`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      // Limpiar el contenido actual de la tabla
      tableBody.innerHTML = "";

      // Insertar los datos en la tabla
      data.forEach((odontologo) => {
        const row = document.createElement("tr");

        row.innerHTML = `
          <td>${odontologo.apellido}</td>
          <td>${odontologo.nombre}</td>
          <td>${odontologo.nroMatricula}</td>
          <td>${odontologo.id}</td>
          <td>
            <button class="btn btn-primary btn-sm" onclick="editOdontologo(${odontologo.id}, '${odontologo.apellido}', '${odontologo.nombre}', '${odontologo.nroMatricula}')">Modificar</button>
            <button class="btn btn-danger btn-sm" onclick="deleteOdontologo(${odontologo.id})">Eliminar</button>
          </td>
        `;

        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

// Función para abrir el modal y cargar los datos del odontólogo
function editOdontologo(id, apellido, nombre, nroMatricula) {
  currentOdontologoId = id;
  document.getElementById("editApellido").value = apellido;
  document.getElementById("editNombre").value = nombre;
  document.getElementById("editnroMatricula").value = nroMatricula;
  //document.getElementById("editId").value = id;
  editModal.show();
}

// Función para editar un odontólogo
editForm.addEventListener("submit", function (event) {
  event.preventDefault();
  const apellido = document.getElementById("editApellido").value;
  const nombre = document.getElementById("editNombre").value;
  const nroMatricula = document.getElementById("editnroMatricula").value;
//  const id = document.getElementById("editId").value;

  // Modificar un odontólogo
  fetch(`Odontologo/actualizar/${currentOdontologoId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      nombre,
      apellido,
      nroMatricula,
    }),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      alert("Odontólogo modificado con éxito");
      fetchOdontologo();
      editModal.hide();
    })
    .catch((error) => {
      console.error("Error editando odontólogo:", error);
    });
});

// Función para eliminar un odontólogo
function deleteOdontologo(id) {
  if (confirm("¿Está seguro de que desea eliminar este odontólogo?")) {
    // Eliminar el odontólogo
    fetch(`Odontologo/eliminar/${id}`, {
      method: "DELETE",
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        alert("Odontólogo eliminado con éxito");
        fetchOdontologo();
      })
      .catch((error) => {
        console.error("Error borrando odontólogo:", error);
      });
  }
}

// Llamar a fetchOdontologo al cargar la página para listar los odontólogos
document.addEventListener("DOMContentLoaded", fetchOdontologo);

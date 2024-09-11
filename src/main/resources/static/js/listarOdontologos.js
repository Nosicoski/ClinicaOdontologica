// Obtener la referencia a la tabla y al modal
const tableBody = document.querySelector("#odontologoTable tbody");
const editModal = new bootstrap.Modal(document.getElementById("editModal"));
const editForm = document.getElementById("editForm");
let currentOdontologoId;


function fetchOdontologos() {
  // listar los odontologos
  fetch(`Odontologo/buscartodos`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      // Limpiar el contenido actual de la tabla
      tableBody.innerHTML = "";

      // Insertar los datos en la tabla
      data.forEach((odontologoTable, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
              <td>${odontologo.id}</td>
              <td>${odontologo.apellido}</td>
              <td>${odontologo.nombre}</td>
              <td>${odontologo.nroMatricula}</td>

                <button class="btn btn-primary btn-sm" onclick="editOdontologo(${Odontologo.id}, '${Odontologo.apellido}','${Odontologo.nombre}', '${Odontologo.nroMatricula}',">Modificar</button>
                <button class="btn btn-danger btn-sm" onclick="deleteOdontologo(${Odontologo.id})">Eliminar</button>
              </td>
            `;

        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

// Función para abrir el modal y cargar los datos del paciente
editPaciente = function (
  apellido,
  nombre,
  nroMatricula,
  Id,
) {
  currentodontologoId = id;
  currentDomicilioId = idDomicilio;
  document.getElementById("editApellido").value = apellido;
  document.getElementById("editNombre").value = nombre;
  document.getElementById("editnroMatricula").value = dni;
  document.getElementById("editId").value = fechaIngreso;

};

// Función para editar un paciente
editForm.addEventListener("submit", function (event) {
  event.preventDefault();
  const apellido = document.getElementById("editApellido").value;
  const nombre = document.getElementById("editNombre").value;
  const nroMatricula = document.getElementById("editnroMatricula").value;
  const Id = document.getElementById("editId").value;

  //modificar un paciente
  fetch(`odontologo/modificar`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      id: currentodontologoIdId,
      nombre,
      apellido,
      nroMatricula,

      },)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      alert("odontologo modificado con éxito");
      fetchPacientes();
      editModal.hide();
    })
    .catch((error) => {
      console.error("Error editando odontologo:", error);
    })
});

// Función para eliminar un paciente
deleteOdontologo = function (id) {
  if (confirm("¿Está seguro de que desea eliminar este odontologo?")) {
    // eliminar el paciente
    fetch(`odontologo/eliminar/${id}`, {
      method: "DELETE",
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        alert("odontologo eliminado con éxito");
        fetchPacientes();
      })
      .catch((error) => {
        console.error("Error borrando odontologo:", error);
      });
  }
};
})

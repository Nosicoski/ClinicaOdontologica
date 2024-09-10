document.addEventListener("DOMContentLoaded", function () {
    fetchOdontologos();
});

const odontologoForm = document.getElementById("odontologoForm");
const tableBody = document.querySelector("#odontologoTable tbody");

// Función para listar los odontólogos
function fetchOdontologos() {
    fetch(`/odontologos/buscartodos`)
        .then((response) => response.json())
        .then((data) => {
            tableBody.innerHTML = ""; // Limpiar la tabla

            data.forEach((odontologo, index) => {
                const row = document.createElement("tr");

                row.innerHTML = `
                    <td>${index + 1}</td>
                    <td>${odontologo.nombre}</td>
                    <td>${odontologo.apellido}</td>
                    <td>${odontologo.matricula}</td>
                    <td>
                        <button class="btn btn-danger" onclick="eliminarOdontologo(${odontologo.id})">Eliminar</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch((error) => console.error("Error al listar odontólogos:", error));
}

// Función para agregar un nuevo odontólogo
odontologoForm.addEventListener("submit", function (event) {
    event.preventDefault();

    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const matricula = document.getElementById("matricula").value;

    fetch(`/Odontologo/guardar`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            nombre: nombre,
            apellido: apellido,
            matricula: matricula,
        }),
    })
    .then((response) => response.json())
    .then((data) => {
        console.log("Odontólogo agregado:", data);
        alert("Odontólogo agregado exitosamente");

        // Agregar odontólogo a la tabla después de ser agregado exitosamente
        const newRow = document.createElement("tr");

        newRow.innerHTML = `
            <td>${tableBody.children.length + 1}</td>
            <td>${data.nombre}</td>
            <td>${data.apellido}</td>
            <td>${data.matricula}</td>
            <td>
                <button class="btn btn-danger" onclick="eliminarOdontologo(${data.id})">Eliminar</button>
            </td>
        `;

        tableBody.appendChild(newRow);

        // Limpiar formulario
        odontologoForm.reset();
    })
    .catch((error) => console.error("Error al agregar odontólogo:", error));
});

// Función para eliminar un odontólogo
function eliminarOdontologo(id) {
    if (confirm("¿Está seguro que desea eliminar este odontólogo?")) {
        fetch(`/odontologos/eliminar/${id}`, {
            method: "DELETE",
        })
        .then(() => {
            alert("Odontólogo eliminado exitosamente");
            fetchOdontologos(); // Actualizar la lista después de eliminar
        })
        .catch((error) => console.error("Error al eliminar odontólogo:", error));
    }
}

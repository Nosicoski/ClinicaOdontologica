const form = document.getElementById("agregarForm");

form.addEventListener("submit", function (event) {
  event.preventDefault();

  // Obtener valores del formulario
  const paciente_id = document.getElementById("nombre").value; // Id del paciente
  const odontologo_id = document.getElementById("apellido").value; // Id del doctor
  const fecha = document.getElementById("fecha").value; // Fecha del turno

  // Crear objeto con los datos requeridos
  const datosFormulario = {
    paciente_id,
    odontologo_id,
    fecha,
  };

  // Llamada al endpoint para agregar el turno
  fetch("http://localhost:8080/Turno/guardar", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datosFormulario),
  })
    .then((response) => {
      if (!response.ok) {
        return response.text().then((text) => {
          throw new Error(`Error en la respuesta del servidor: ${response.status} - ${text}`);
        });
      }
      return response.json();
    })
    .then((data) => {
      console.log("Turno agregado con éxito:", data);
      alert("Turno agregado con éxito.");
      form.reset(); // Resetear el formulario
    })
    .catch((error) => {
      console.error("Error agregando turno:", error.message);
      alert(`Hubo un problema al agregar el turno: ${error.message}`);
    });
});

const form = document.getElementById("agregarForm");

form.addEventListener("submit", function (event) {
  event.preventDefault();

  const apellido = document.getElementById("apellido").value;
  const nombre = document.getElementById("nombre").value;
  const nroMatricula = document.getElementById("nroMatricula").value;
  const Id = document.getElementById("Id").value;


  // llamando al endpoint de agregar
  const datosFormulario = {
    nombre,
    apellido,
    nroMatricula,
    Id
    };



  fetch("http://localhost:8080/Odontologo/guardar", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datosFormulario),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      alert("Odontolgo agregado con éxito");
      form.reset(); // Resetear el formulario
    })
    .catch((error) => {
      console.error("Error agregando Odontologo:", error);
    });
});

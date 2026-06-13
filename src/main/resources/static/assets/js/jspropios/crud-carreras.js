function listar() {
  $.ajax({
    method: "GET",
    url: "/carreras/api/carreras",
    data: {},
    success: function (carreras) {
      let tabla = $("#add-row").DataTable();
      carreras.forEach(carrera=>{

        let botones = '<button type="button" class="btn btn-link btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#modal-actualizar" onclick="identificaActualizar('+carrera.id+')"><i class="fa fa-edit fa-lg"></i></button>';
        botones = botones + ' <button type="button" class="btn btn-link btn-danger" data-bs-toggle="modal" data-bs-target="#modal-eliminar" onclick="identificaEliminar('+carrera.id+')"><i class="fa fa-times fa-lg"></i></button>';

        let rowNode = tabla.row
            .add([carrera.nombre, carrera.semestres, botones])
            .draw()
            .node().id = 'renglon_' + carrera.id;
      })
    }
  });
}

function guardar() {
  let nombreCarrera = document.getElementById('nombre').value.trim();
  let semestresCarrera = document.getElementById('semestres').value;
  let observacionesCarrera = document.getElementById('observaciones').value;

  if (nombreCarrera === "") {
    alert("El nombre de la carrera es obligatorio");
    return;
  }
  if (semestresCarrera === "" || semestresCarrera < 1) {
    alert("Ingrese un número de semestres válido");
    return;
  }

  $.ajax({
    method:'POST',
    url: "/carreras/api/carreras",
    contentType:"application/json",
    data: JSON.stringify({
        nombre: nombreCarrera,
        semestres: semestresCarrera,
        observaciones: observacionesCarrera
      })
    ,
    success: function (carrera) {
      let botones = '<button type="button" class="btn btn-link btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#modal-actualizar" onclick="identificaActualizar('+carrera.id+')"><i class="fa fa-edit fa-lg"></i></button>';
      botones = botones + ' <button type="button" class="btn btn-link btn-danger" data-bs-toggle="modal" data-bs-target="#modal-eliminar" onclick="identificaEliminar('+carrera.id+')"><i class="fa fa-times fa-lg"></i></button>';

      let tabla = $("#add-row").DataTable();
      var rowNode = tabla.row
          .add([carrera.nombre, carrera.semestres, botones])
          .draw()
          .node().id='renglon_'+carrera.id;

      alert("Carrera Guardada Correctamente");
      limpiarFormulario();
      $('#modal-agregar').modal('hide');
    }
  });
}

function limpiarFormulario(){
  document.getElementById('nombre').value="";
  document.getElementById('semestres').value="";
  document.getElementById('observaciones').value="";
  document.getElementById('nombre').focus();
}

function identificaActualizar(id) {

  $.ajax({
    method:'GET',
    url: "/carreras/api/carreras/"+id,
    data: {},
    success: function( carrera ) {
      document.getElementById('carrera-update').value=carrera.id
      document.getElementById('nombre-update').value=carrera.nombre;
      document.getElementById('semestres-update').value=carrera.semestres;
      document.getElementById('observaciones-update').value=carrera.observaciones;
    }
  });
}

function actualizar() {
  let idCarrera = document.getElementById('carrera-update').value;
  let nombreCarrera = document.getElementById('nombre-update').value.trim();
  let semestresCarrera = document.getElementById('semestres-update').value;
  let observacionesCarrera = document.getElementById('observaciones-update').value;

  if (nombreCarrera === "") {
    alert("El nombre de la carrera es obligatorio");
    return;
  }
  if (semestresCarrera === "" || semestresCarrera < 1) {
    alert("Ingrese un número de semestres válido");
    return;
  }

  $.ajax({
    method:'PATCH',
    contentType:'application/json',
    url: "/carreras/api/carreras/"+idCarrera,
    data: JSON.stringify({
      nombre: nombreCarrera,
      semestres: semestresCarrera,
      observaciones: observacionesCarrera
    }),
    success: function( carrera ) {
      let tabla = $("#add-row").DataTable();
      var datos = tabla.row("#renglon_"+idCarrera).data()
      datos[0]=nombreCarrera;
      datos[1]=semestresCarrera;
      tabla.row("#renglon_"+idCarrera).data(datos)
      tabla.draw();
      alert('Carrera actualizada');
      $('#modal-actualizar').modal('hide');
    }
  });
}


function identificaEliminar(id) {
  $.ajax({
    method:'GET',
    url: "/carreras/api/carreras/"+id,
    data: {},
    success: function( carrera ) {
      document.getElementById('carrera-delete').value=carrera.id;
      document.getElementById('nombre-delete').innerText=carrera.nombre;
    }
  });
}

function eliminar() {
  const idEliminar=document.getElementById('carrera-delete').value;
  $.ajax({
    method:'DELETE',
    url: "/carreras/api/carreras/"+idEliminar,
    data: {},
    success: function( carrera ) {
      alert('Carrera Eliminada')
      let tabla = $("#add-row").DataTable();

      let rows = tabla
          .row('#renglon_'+idEliminar)
          .remove()
          .draw();

      $('#modal-eliminar').modal('hide');
    }
  });
}
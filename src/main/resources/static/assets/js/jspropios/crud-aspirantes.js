function listar()
{
    $.ajax({
        method : "GET",
        url : "/Aspirantes/api/Aspirantes",
        data : {},

        success : function(aspirantes)

        {
            let tabla = $("#Aspirantes").DataTable();
            aspirantes.forEach(aspirante =>
            {
                let botones = '<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modal-correo-individual"><i class ="icon-envelope-letter"></i></button>';
                botones = botones + '<button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#modal-informacion-alumno"><i class="icon-user"></i></button>';
                botones = botones + '<button type="button" class="btn btn-default" data-bs-toggle="modal" data-bs-target="#modal-actualizar"><i class="icon-printer"></i></button>';
                
                let rowNode = tabla.row
                    .add([aspirante.nombre, aspirante.email, botones])
                    .draw()
                    .node().id = "renglon_"+ aspirante.id;
            }
            )
        }
    })
}

function guardar()
{
    let nombreAspirante = document.getElementById('nombre').value;
    let telefono = document.getElementById('telefono').value;
    let correoElectronico = document.getElementById('email').value;
    let carreraAspirante = document.getElementById('carrera-aspirantes').value;

    $.ajax({
        method : "POST",
        url : "/aspirantes/api/aspirantes",
        contentType : "application/json",

        data : JSON.stringify({
            nombre : nombreAspirante,
            telefono : telefono,
            email : correoElectronico,
            carrera : { 
                id : carreraAspirante // Asegúrate de que "id" coincida con el nombre de la llave primaria en tu CarreraEntity
            }
        }),
        success : function(mascota)
        {

                alert("Aspirante guardado exitosamente");
                limpiarFormulario();
        }
    })
}

function limpiarFormulario()
{
    document.getElementById('nombre').value = "";
    document.getElementById('telefono').value = "";
    document.getElementById('email').value = "";
}

function cargarCarreras()
{
    $('#carrera-aspirantes').removeAttr('onclick')
    $.ajax({
        method : "GET",
        url : "/carreras/api/carreras",
        data : {},

        success : function(carreras)
        {
            $.each(carreras, function(index, carrera)
        {
            $('#carrera-aspirantes').append((
                $('<option>',
                    {
                        value: carrera.id,
                        text : carrera.nombre
                    }
                )
            ))
        })
        }
    })
}


function correoIndividual()
{
    $.ajax({
        method : "GET",
        url : "/Aspirantes/correo/Individual",
        data : {}
    })
}

function informacionPersonal()
{
    $.ajax({
        method : "GET",
        url : "/Aspirantes/informacion/Personal",
        data : {}
    })
}
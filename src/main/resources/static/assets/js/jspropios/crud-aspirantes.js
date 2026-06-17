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
                    .add([aspirante.nombreUsuario, aspirante.email, botones])
                    .draw()
                    .node().id = "renglon_"+ aspirante.id;
            }
            )
        }
    })
}

function correoMasivo()
{
    $.ajax({
        method : "GET",
        url : "/Aspirantes/correo/Masivo",
        data : {}
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
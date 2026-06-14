function listar()
{
    $.ajax({
        method : "GET",
        url : "/Aspirantes/api/Aspirantes",
        data : {},

        success : function(aspirantes)

        {
            let tabla = new DataTable("#Aspirantes")
            aspirantes.forEach(aspirante =>
            {
                let botones = '<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modal-actualizar"><i class ="icon-envelope-letter"></i></button>';
                botones = botones + '<button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#modal-actualizar"><i class="icon-user">';
                botones = botones + '<button type="button" class="btn btn-default" data-bs-toggle="modal" data-bs-target="#modal-actualizar"><i class="icon-printer">';
                
                let rowNode = tabla.row
                    .add([aspirante.nombre, aspirante.correo, botones])
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
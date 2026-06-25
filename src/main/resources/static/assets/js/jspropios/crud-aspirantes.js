// ─── Listar aspirantes al cargar ────────────────────────────────────────────
function listar() {
    $.ajax({
        method: "GET",
        url: "/Aspirantes/api/Aspirantes",
        success: function (aspirantes) {
            let tabla = $("#Aspirantes").DataTable();
            tabla.clear();

            aspirantes.forEach(function (aspirante) {
                let botones =
                    '<button type="button" class="btn btn-success btn-sm me-1" ' +
                    'onclick="abrirCorreoIndividual(' + aspirante.id + ', \'' + aspirante.nombre + '\')" >' +
                    '<i class="icon-envelope-letter"></i></button>' +
                    '<button type="button" class="btn btn-warning btn-sm me-1" ' +
                    'onclick="verInformacion(' + aspirante.id + ')">' +
                    '<i class="icon-user"></i></button>';

                let row = tabla.row.add([aspirante.nombre, aspirante.email, botones]).draw();
                row.node().id = "renglon_" + aspirante.id;
            });
        },
        error: function () {
            alert("Error al cargar los aspirantes.");
        }
    });
}

// ─── Validar email (AJAX al perder el foco) ──────────────────────────────────
$('#email').on('blur', function () {
    let email = $(this).val().trim();
    if (!email) return;

    $.ajax({
        method: "GET",
        url: "/Aspirantes/api/verificar-email",
        data: { email: email },
        success: function (existe) {
            if (existe) {
                $('#email-error').show();
                $('#btn-guardar').prop('disabled', true);
            } else {
                $('#email-error').hide();
                $('#btn-guardar').prop('disabled', false);
            }
        }
    });
});

// ─── Guardar aspirante ────────────────────────────────────────────────────────
$('#btn-guardar').on('click', function () {
    let nombre = $('#nombre').val().trim();
    let email  = $('#email').val().trim();

    // Validación HTML5 / regex en JS
    let soloLetras = /^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$/;
    if (!soloLetras.test(nombre)) {
        alert("El nombre solo debe contener letras.");
        return;
    }
    let regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!regexEmail.test(email)) {
        alert("Ingresa un correo válido.");
        return;
    }
    if ($('#email-error').is(':visible')) {
        alert("El correo ya está registrado.");
        return;
    }

    let aspirante = { nombre: nombre, email: email };

    $.ajax({
        url: "/Aspirantes/api/guardar",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(aspirante),
        success: function () {
            alert("Aspirante registrado correctamente.");
            $('#modal-agregar-aspirante').modal('hide');
            $('#form-nuevo-aspirante')[0].reset();
            $('#btn-guardar').prop('disabled', false);
            listar();
        },
        error: function (xhr) {
            let resp = xhr.responseJSON;
            alert(resp && resp.error ? resp.error : "Error al registrar el aspirante.");
        }
    });
});

// ─── Correo masivo ────────────────────────────────────────────────────────────
function correoMasivo() {
    let asunto  = $('#asunto-masivo').val().trim();
    let mensaje = $('#mensaje-masivo').val().trim();

    if (!asunto || !mensaje) {
        alert("Completa el asunto y el mensaje.");
        return;
    }

    $.ajax({
        method: "POST",
        url: "/Aspirantes/api/correo/masivo",
        contentType: "application/json",
        data: JSON.stringify({ asunto: asunto, mensaje: mensaje }),
        success: function (resp) {
            alert(resp.mensaje);
            $('#modal-correo-masivo').modal('hide');
        },
        error: function () {
            alert("Error al enviar el correo masivo.");
        }
    });
}

// ─── Correo individual ────────────────────────────────────────────────────────
let aspiranteIdSeleccionado = null;

function abrirCorreoIndividual(id, nombre) {
    aspiranteIdSeleccionado = id;
    $('#modal-correo-individual .modal-title span').text("Enviar correo a " + nombre);
    $('#asunto-individual').val('');
    $('#mensaje-individual').val('');
    $('#modal-correo-individual').modal('show');
}

function correoIndividual() {
    let asunto  = $('#asunto-individual').val().trim();
    let mensaje = $('#mensaje-individual').val().trim();

    if (!asunto || !mensaje) {
        alert("Completa el asunto y el mensaje.");
        return;
    }

    $.ajax({
        method: "POST",
        url: "/Aspirantes/api/correo/individual/" + aspiranteIdSeleccionado,
        contentType: "application/json",
        data: JSON.stringify({ asunto: asunto, mensaje: mensaje }),
        success: function (resp) {
            alert(resp.mensaje);
            $('#modal-correo-individual').modal('hide');
        },
        error: function () {
            alert("Error al enviar el correo.");
        }
    });
}
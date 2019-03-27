$(document).ready(function () {
    $.ajax({
        url: '/inicio/1', success: function (data) {
            $('#lista-articulos').html(data);
        }
    });
});
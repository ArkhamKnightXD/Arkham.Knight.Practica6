var webSocket;
var tiempoReconectar = 5000;
var usuarioPrivilegiadoSeleccionado;

$(document).ready(function () {
    var contador = 0;

    $(".chat > b").each(function () {
        $("#mensajes").append("\n" +
            "            <div class=\"col-12 lista-mensajes " + $(this).text() + "\">\n" +
            "                <div class=\"row p-2\">\n" +
            "                    <input id=\"mensaje-" + $(this).text() + "\" class=\"mensaje-enviar chat-input col-10\" placeholder=\"Escribe tu mensaje\" type=\"text\"/>\n" +
            "                    <button class=\"btn btn-secondary col-2 enviar-mensaje\">\n" +
            "                        <i class=\"fas fa-paper-plane\"></i> Enviar\n" +
            "                    </button>\n" +
            "                </div>\n" +
            "            </div>");
    });

    usuarioPrivilegiadoSeleccionado = $(".chat:first > b").html();

    $(".chat:first").addClass("chat-activo");

    $(".mensaje-enviar").attr("disabled", "disabled");

    $("#bloquear-usuario").unbind().click(function () {
        $("#mensaje-usuario").attr("disabled", "disabled");
        $(".mensaje-enviar").removeAttr("disabled");
    });

    $(".lista-mensajes").hide();
    $(".lista-mensajes:first").show()

    $(".chat").click(function () {
        $(".chat").removeClass("chat-activo");
        $(this).addClass("chat-activo");

        usuarioPrivilegiadoSeleccionado = $(this).children("b").html();
        $(".lista-mensajes").hide();
        $(".lista-mensajes." + usuarioPrivilegiadoSeleccionado).show();
    });

    console.info("Iniciando conexion con WebSocket...");

    conectar();

    $(".enviar-mensaje").click(function () {
        var data = {
            desde: $("#mensaje-usuario").val(),
            hasta: usuarioPrivilegiadoSeleccionado,
            mensaje: $("#mensaje-" + usuarioPrivilegiadoSeleccionado).val(),
            inicio: true
        };

        console.log(data);
        webSocket.send(JSON.stringify(data));

        $(".lista-mensajes." + usuarioPrivilegiadoSeleccionado).prepend("" +
            "\n" +
            "<div class=\"col-10 d-block my-2\">\n" +
            "	<div class=\"card bg-primary text-white\">\n" +
            "	<div class=\"card-body\">\n" +
            "		<small>\n" +
            "			<h6 class=\"card-title text-white\">\n" +
            "				" + $("#mensaje-usuario").val() + "\n" +
            "			</h6>\n" +
            "			" + $("#mensaje-" + usuarioPrivilegiadoSeleccionado).val() + "\n" +
            "			</small>\n" +
            "		</div>\n" +
            "	</div>\n" +
            "</div>" +
            "");

        $("#mensaje-" + usuarioPrivilegiadoSeleccionado).val("");
    });
});

function recibirInformacionServidor(mensaje) {
    $(".lista-mensajes." + JSON.parse(mensaje.data).desde).prepend("" +
        "\n" +
        "<div class=\"col-10 d-block my-2 ml-5\">\n" +
        "	<div class=\"card bg-secondary text-white\">\n" +
        "	<div class=\"card-body\">\n" +
        "		<small>\n" +
        "			<h6 class=\"card-title text-white\">\n" +
        "				" + JSON.parse(mensaje.data).desde + "\n" +
        "			</h6>\n" +
        "			" + JSON.parse(mensaje.data).mensaje + "\n" +
        "			</small>\n" +
        "		</div>\n" +
        "	</div>\n" +
        "</div>" +
        "");

    $("#mensaje-" + usuarioPrivilegiadoSeleccionado).val("");
}

function conectar() {
    webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat");

    webSocket.onopen = function (e) {
        console.log("Conectado, con el estado: " + this.readyState);
    };

    webSocket.onmessage = function (data) {
        recibirInformacionServidor(data);
    };

    webSocket.onclose = function (e) {
        console.log("Desconectado, con el estado: " + this.readyState);
    };
}

function verificarConexion() {
    if (!webSocket || webSocket.readyState === 3) {
        conectar();
    }
}

setInterval(verificarConexion, tiempoReconectar);
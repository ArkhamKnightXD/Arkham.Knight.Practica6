<div class="row">
    <#list articulos as articulo>
        <div class="col-6 mx-auto p-0">
            <div class="card mx-2 mb-1">
                <div class="card-body">
                    <h5 class="card-title">${articulo.titulo}</h5>
                    <p class="card-text text-muted m-0 cuerpo-corto">${articulo.cuerpoCorto}</p>
                    <a href="/articulo/${articulo.id}" class="text-danger float-right"><strong>Leer más...</strong></a>
                </div>
                <div class="card-footer p-2">
                    <strong class="text-danger m-0">
                    <span class="text-warning">
                        <i class="fas fa-calendar-alt"></i> ${articulo.fecha}
                    </span>
                        <span class="text-success ml-5">
                        <i class="fas fa-comments"></i> ${articulo.listaComentarios?size}
                    </span>
                    <#if articulo.listaEtiquetas?size gt 0>
                        <span class="text-primary ml-5">
                            <i class="fas fa-hashtag"></i>
                            <#list articulo.listaEtiquetas as etiqueta>
                                <a href="/etiqueta/${etiqueta.id}">${etiqueta.etiqueta}</a>
                            </#list>
                        </span>
                    </#if>
                    </strong>
                </div>
            </div>
        </div>
    </#list>
</div>
<div class="col">
    <div class="row">
        <nav>
            <ul class="pagination">
                <#if paginaActual gt 1>
                    <li class="page-item">
                        <button class="page-link" onclick="paginaAnterior()" data-pagina="${paginaActual - 1}" aria-label="Anterior">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Anterior</span>
                        </button>
                    </li>
                <#else>
                    <li class="page-item disabled">
                        <button class="page-link" data-pagina="${paginaActual - 1}" aria-label="Anterior">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Anterior</span>
                        </button>
                    </li>
                </#if>
                <#if paginaMaxima gt paginaActual>
                    <li class="page-item">
                        <button class="page-link" onclick="paginaSiguiente()" data-pagina="${paginaActual + 1}" aria-label="Siguiente">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Siguiente</span>
                        </button>
                    </li>
                <#else>
                    <li class="page-item disabled">
                        <button class="page-link" data-pagina="${paginaActual + 1}" aria-label="Siguiente">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Siguiente</span>
                        </button>
                    </li>
                </#if>
            </ul>
        </nav>
    </div>
</div>

<form action="/inicio/${paginaActual + 1}" method="get" id="pagina-siguiente">
</form>
<form action="/inicio/${paginaActual - 1}" method="get" id="pagina-anterior">
</form>

<script src="/js/jquery-3.2.1.min.js"></script>
<script>
    function paginaAnterior() {
        var ruta = $("#pagina-anterior").attr("action");
        $.ajax({
            data: $(this).data("pagina"),
            type: "GET",
            url: ruta,
            success: function (data) {
                $('#lista-articulos').html(data);
            }
        });
    }

    function paginaSiguiente() {
        var ruta = $("#pagina-siguiente").attr("action");
        $.ajax({
            data: $(this).data("pagina"),
            type: "GET",
            url: ruta,
            success: function (data) {
                $('#lista-articulos').html(data);
            }
        });
    }
</script>
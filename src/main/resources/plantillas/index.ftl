<#import "/plantillas/base.ftl" as base>
<@base.pagina logueado=estaLogueado usuario=nombreUsuario permisos=tienePermisos admin=esAdmin>
<script src="/js/paginacion.js"></script>
<div class="col-12 p-2">
    <div id="lista-articulos">
    </div>
</div>
    <#include "modal-chat-normal.ftl">
</@base.pagina>
<main class="row" id="chat">
    <aside class="col-3 p-0">
        <h6><i class="fas fa-comments"></i> Masters</h6>
        <ul class="list-group list-group-flush">
            <#list listaUsuariosPrivilegiados as usuario>
                <li class="list-group-item chat">
                    <b>${usuario.username}</b>
                    <br>
                </li>
            </#list>
        </ul>
    </aside>
    <div class="col-9">
        <section class="row" id="mensajes">
            <div class="col-12">
                <div class="row p-2">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <i class="input-group-text fas fa-user"></i>
                        </div>
                        <input type="text" name="usuario" class="chat-input col-10" placeholder="Escribe tu nombre" id="mensaje-usuario">
                        <button class="btn btn-secondary" id="bloquear-usuario"><i class="fas fa-lock"></i></button>
                    </div>
                </div>
            </div>
        </section>
    </div>
</main>
<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/websocket.js"></script>
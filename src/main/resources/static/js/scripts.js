function confirmarEliminar(usuarioId) {
    if (confirm("¿Estás seguro de que quieres eliminar este usuario?")) {
        window.location.href = "/usuarios/eliminar/" + usuarioId;
    }
}
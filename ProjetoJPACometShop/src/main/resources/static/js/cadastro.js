document.addEventListener("DOMContentLoaded", function () {
    // Seleciona o input de telefone corretamente pelo ID
    const telefoneInput = document.getElementById("telefone");

    if (!telefoneInput) {
        console.error("Elemento de telefone não encontrado!");
        return; // Evita erros caso o input não exista
    }

    telefoneInput.addEventListener("input", function (event) {
        let telefone = event.target.value.replace(/\D/g, ""); // Remove caracteres não numéricos

        if (telefone.length > 11) telefone = telefone.slice(0, 11); // Máximo 11 dígitos

        // Aplica máscara (XX) XXXX-XXXX ou (XX) XXXXX-XXXX
        telefone = telefone.length <= 10
            ? telefone.replace(/^(\d{2})(\d{4})(\d{0,4})$/, "($1) $2-$3")
            : telefone.replace(/^(\d{2})(\d{5})(\d{0,4})$/, "($1) $2-$3");

        event.target.value = telefone;
    });

    telefoneInput.addEventListener("blur", function () {
        let telefone = telefoneInput.value.replace(/\D/g, "");
        if (telefone.length < 10 || telefone.length > 11) {
            alert("Número de telefone inválido!");
            telefoneInput.value = "";
        }
    });

    // Restrição para entrada apenas de números
    telefoneInput.addEventListener("keydown", function (event) {
        if (!/^[0-9]$/.test(event.key) &&
            !["Backspace", "Delete", "ArrowLeft", "ArrowRight"].includes(event.key)) {
            event.preventDefault();
        }
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const telefoneInput = document.getElementById("telefone");
    if (!telefoneInput) {
        console.error("Elemento de telefone não encontrado!");
        return;
    }

    telefoneInput.addEventListener("input", function (event) {
        let telefone = event.target.value.replace(/\D/g, "");
        if (telefone.length > 11) telefone = telefone.slice(0, 11);
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

    telefoneInput.addEventListener("keydown", function (event) {
        if (!/^[0-9]$/.test(event.key) &&
            !["Backspace", "Delete", "ArrowLeft", "ArrowRight"].includes(event.key)) {
            event.preventDefault();
        }
    });

    const nomeInput = document.getElementById("nome");
    if (!nomeInput) {
        console.error("Elemento de nome não encontrado!");
        return;
    }

    nomeInput.addEventListener("keydown", function (event) {
        const key = event.key;
        const isLetter = /^[a-zA-ZÀ-ÿ ]$/.test(key);
        const allowedKeys = ["Backspace", "Delete", "ArrowLeft", "ArrowRight", "Tab"];
        if (!isLetter && !allowedKeys.includes(key)) {
            event.preventDefault();
        }
    });

    nomeInput.addEventListener("paste", function (event) {
        const pasted = (event.clipboardData || window.clipboardData).getData("text");
        if (!/^[a-zA-ZÀ-ÿ\s]+$/.test(pasted)) {
            event.preventDefault();
            alert("Cole apenas letras no campo de nome.");
        }
    });

    const btnCadastrar = document.getElementById("btnCadastrar");
    btnCadastrar.addEventListener("click", function () {
        const nome     = document.getElementById("nome").value.trim();
        const email    = document.getElementById("email").value.trim();
        const senha    = document.getElementById("senha").value.trim();
        const telefone = document.getElementById("telefone").value.replace(/\D/g, "");

        console.log("Dados a enviar:", { nome, email, senha, telefone });

        if (!nome || !email || !senha || telefone.length < 10 || telefone.length > 11) {
            alert("Preencha todos os campos corretamente!");
            return;
        }

        fetch("http://localhost:8080/usuarios", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ nome, email, senha })
        })
            .then(response => {
                if (!response.ok) throw new Error("Status " + response.status);
                return response.json();
            })
            .then(data => {
                alert("Cadastro realizado com sucesso!");
                const currentUrl = window.location.href;
                // troca "cadastro.html" (e tudo depois) por "home.html"
                const newUrl = currentUrl.replace(/cadastro\.html.*$/, "home.html");
                window.location.href = newUrl;
            })
            .catch(error => {
                alert("Ocorreu um erro ao cadastrar. Tente novamente.");
            });
    });
});

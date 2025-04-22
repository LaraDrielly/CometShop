//API de CEP

async function buscarCep() {
    let cep = document.getElementById("cep").value.replace(/\D/g, "");

    if (cep.length !== 8) {
        console.warn("CEP inválido! Deve ter 8 números.");
        return;
    }

    try {
        const resposta = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
        if (!resposta.ok) throw new Error("Erro na resposta da API");

        const dados = await resposta.json();
        if (dados.erro) {
            alert("CEP não encontrado!");
            return;
        }

        document.getElementById("endereco").value = dados.logradouro || "";
        document.getElementById("bairro").value = dados.bairro || "";


    } catch (error) {
        console.error("Erro ao buscar CEP:", error);
        alert("Erro ao buscar o CEP. Tente novamente.");
    }
}

//Check único de lista
document.addEventListener("DOMContentLoaded", function () {
    const checkboxes = document.querySelectorAll('input[name="pagamento"]');

    checkboxes.forEach(checkbox => {
        checkbox.addEventListener("change", function () {
            if (this.checked) {
                checkboxes.forEach(cb => {
                    if (cb !== this) {
                        cb.checked = false; // Desmarca os outros checkboxes
                    }
                });
            }
        });
    });
});

//Validação e máscara para telefone
const telefoneInput = document.querySelector('input[placeholder="Telefone de contato"]');
telefoneInput.addEventListener("input", function (event) {
    let telefone = event.target.value.replace(/\D/g, ""); // Remove não numéricos

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


// Restrição para campos numéricos
document.addEventListener("DOMContentLoaded", function () {
    const camposNumericos = ["campoNumerico", "cartaoNumero", "cartaoCVV"];
    const cvvErro = document.getElementById("cvvErro"); // Mensagem de erro do CVV

    camposNumericos.forEach(id => {
        const campo = document.getElementById(id);
        if (campo) {
            campo.addEventListener("input", function () {
                this.value = this.value.replace(/\D/g, ""); // Remove caracteres não numéricos

                // Validação específica para CVV
                if (id === "cartaoCVV") {
                    if (this.value.length > 4) {
                        this.value = this.value.slice(0, 4); // Limita a 4 números
                    }

                    // Exibe a mensagem de erro se não tiver 3 ou 4 números
                    if (this.value.length < 3 || this.value.length > 4) {
                        alert("O CVV precisa de 3 ou 4 dígitos");
                    }
                } // <-- Fechando corretamente este bloco
            });

            campo.addEventListener("keydown", function (event) {
                if (!/^[0-9]$/.test(event.key) && !["Backspace", "Delete", "ArrowLeft", "ArrowRight"].includes(event.key)) {
                    event.preventDefault();
                }
            });
        }
    });
});

document.addEventListener("DOMContentLoaded", () => {
    const qtd = localStorage.getItem("quantidadeProdutos");
    const preco = localStorage.getItem("precoTotal");

    if (qtd && preco) {
        const qtdEl = document.getElementById("resumo-qtd");
        const precoEl = document.getElementById("resumo-total");

        if (qtdEl && precoEl) {
            qtdEl.textContent = qtd;
            precoEl.textContent = preco;
        }
    }
});

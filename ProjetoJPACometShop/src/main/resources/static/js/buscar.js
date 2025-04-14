document.addEventListener('DOMContentLoaded', function() {
    const botaoBusca = document.getElementById('botaoBusca');
    const campoBusca = document.getElementById('campoBusca');

    if (botaoBusca && campoBusca) {
        botaoBusca.addEventListener('click', function() {
            const termo = campoBusca.value.trim().toLowerCase();
            if (termo.length === 0) {
                // Se o campo estiver vazio, mostra todos os produtos
                mostrarTodosProdutos();
                return;
            }
            buscarProdutos(termo);
        });

        // Opcional: permitir busca ao pressionar Enter
        campoBusca.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                botaoBusca.click();
            }
        });
    }

    function buscarProdutos(termo) {
        const areaBusca = document.getElementById('areaBusca');
        if (!areaBusca) return;

        // Obtém todos os cards de produto na página
        const cards = areaBusca.querySelectorAll('.product-card');
        let encontrouResultados = false;

        cards.forEach(card => {
            const nomeProduto = card.querySelector('p').textContent.toLowerCase();

            if (nomeProduto.includes(termo)) {
                card.style.display = 'block';
                encontrouResultados = true;

                // Destaca o termo buscado
                const textoOriginal = card.querySelector('p').textContent;
                const regex = new RegExp(`(${termo})`, 'gi');
                card.querySelector('p').innerHTML = textoOriginal.replace(regex, '<mark>$1</mark>');
            } else {
                card.style.display = 'none';
            }
        });

        // Mostra mensagem se não encontrar resultados
        const mensagemSemResultados = areaBusca.querySelector('.sem-resultados') || document.createElement('p');
        if (!encontrouResultados) {
            mensagemSemResultados.textContent = 'Nenhum produto encontrado com esse nome.';
            mensagemSemResultados.className = 'sem-resultados';
            if (!areaBusca.contains(mensagemSemResultados)) {
                areaBusca.appendChild(mensagemSemResultados);
            }
            mensagemSemResultados.style.display = 'block';
        } else {
            mensagemSemResultados.style.display = 'none';
        }
    }

    function mostrarTodosProdutos() {
        const areaBusca = document.getElementById('areaBusca');
        if (!areaBusca) return;

        const cards = areaBusca.querySelectorAll('.product-card');
        const mensagemSemResultados = areaBusca.querySelector('.sem-resultados');

        if (mensagemSemResultados) {
            mensagemSemResultados.style.display = 'none';
        }

        cards.forEach(card => {
            card.style.display = 'block';
            // Remove os highlights se houver
            const p = card.querySelector('p');
            p.innerHTML = p.textContent;
        });
    }
});
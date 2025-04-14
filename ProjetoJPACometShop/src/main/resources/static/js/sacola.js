document.addEventListener('DOMContentLoaded', () => {
    // Verifica se é a página da sacola para evitar erros
    if (window.location.pathname.includes('sacola') || window.location.pathname.includes('sacolaVazia')) {
        const controles = document.querySelectorAll('.quantidade-container');
        let totalItens = 0;

        controles.forEach(container => {
            const botaoMais = container.querySelector('.mais');
            const botaoMenos = container.querySelector('.menos');
            const quantidadeEl = container.querySelector('.quantidade');
            let quantidade = parseInt(quantidadeEl.textContent);
            totalItens += quantidade;

            botaoMais.addEventListener('click', () => {
                quantidade++;
                quantidadeEl.textContent = quantidade;
                totalItens++;
                atualizarResumo();
            });

            botaoMenos.addEventListener('click', () => {
                if (quantidade > 1) {
                    quantidade--;
                    quantidadeEl.textContent = quantidade;
                    totalItens--;
                    atualizarResumo();
                }
            });
        });

        function atualizarResumo() {
            const resumoQuantidade = document.querySelector('.resumo strong:first-child');
            const resumoTotal = document.querySelector('.resumo strong:last-child');

            if (resumoQuantidade) {
                resumoQuantidade.textContent = totalItens;
                // Aqui você pode calcular o total também se necessário
            }
        }

        atualizarResumo();
    }
});
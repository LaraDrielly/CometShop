document.addEventListener('DOMContentLoaded', () => {
    const controles = document.querySelectorAll('.quantidade-container');

    controles.forEach(container => {
        const botaoMais = container.querySelector('.mais');
        const botaoMenos = container.querySelector('.menos');
        const quantidadeEl = container.querySelector('.quantidade');

        let quantidade = parseInt(quantidadeEl.textContent);

        botaoMais.addEventListener('click', () => {
            quantidade++;
            quantidadeEl.textContent = quantidade;
        });

        botaoMenos.addEventListener('click', () => {
            if (quantidade > 1) {
                quantidade--;
                quantidadeEl.textContent = quantidade;
            }
        });
    });
});

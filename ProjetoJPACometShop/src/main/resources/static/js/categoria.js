document.addEventListener('DOMContentLoaded', () => {
    const sacolaIcon = document.querySelector('.sacola');
    if (!sacolaIcon) return;

    // Cria ou seleciona o span do contador
    let contadorElement = document.querySelector('.contador-sacola');
    if (!contadorElement) {
        contadorElement = document.createElement('span');
        contadorElement.classList.add('contador-sacola');
        sacolaIcon.parentElement.appendChild(contadorElement);

        // Estilo do contador
        contadorElement.style.position = 'absolute';
        contadorElement.style.top = '10px';
        contadorElement.style.right = '10px';
        contadorElement.style.backgroundColor = 'brown';
        contadorElement.style.color = 'white';
        contadorElement.style.borderRadius = '50%';
        contadorElement.style.padding = '2px 8px';
        contadorElement.style.fontSize = '14px';
        contadorElement.style.fontWeight = 'bold';
    }

    // Lê o carrinho atual do localStorage
    const carrinho = JSON.parse(localStorage.getItem('carrinho')) || [];

    // Soma todas as quantidades dos produtos
    const totalQuantidade = carrinho.reduce((soma, item) => soma + item.quantidade, 0);

    contadorElement.textContent = totalQuantidade;
    contadorElement.style.display = totalQuantidade > 0 ? 'inline-block' : 'none';
});

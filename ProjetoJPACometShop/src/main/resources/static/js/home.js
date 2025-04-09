let contadorSacola = 0;

// Cria e adiciona o contador na sacola
const sacolaIcon = document.querySelector('.sacola');
const contadorElement = document.createElement('span');
contadorElement.classList.add('contador-sacola');
contadorElement.textContent = contadorSacola;
sacolaIcon.parentElement.style.position = 'relative'; // para posicionar corretamente
sacolaIcon.parentElement.appendChild(contadorElement);

// Esconde o contador inicialmente
contadorElement.style.display = 'none';

// Seleciona todos os botões de "Adicionar à sacola"
const botoes = document.querySelectorAll('button');

botoes.forEach(botao => {
    if (botao.textContent.includes('Adicionar à sacola')) {
        botao.addEventListener('click', () => {
            contadorSacola++;
            contadorElement.textContent = contadorSacola;
            contadorElement.style.display = 'inline-block';
        });
    }
});
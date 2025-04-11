//método buscar
document.getElementById('buscaTexto').addEventListener('input', function () {
    const termo = this.value.trim().toLowerCase();
    const areaBusca = document.getElementById('areaBusca');

    if (!areaBusca) return;

    // Limpa os destaques antigos mantendo o texto original
    const cards = areaBusca.querySelectorAll('.product-card');
    cards.forEach(card => {
        const p = card.querySelector('p');
        const textoOriginal = p.getAttribute('data-original') || p.textContent;
        p.setAttribute('data-original', textoOriginal); // salva texto original uma vez
        p.innerHTML = textoOriginal;

        if (termo.length > 0) {
            const regex = new RegExp(`(${termo})`, 'gi');
            p.innerHTML = textoOriginal.replace(regex, '<mark>$1</mark>');
        }
    });
});


//adicionar toda a area que deseja que seja feita a busca nessa dv
//    <div id="areaBusca"> </div>

// Recupera a contagem armazenada ou começa do zero
let contadorSacola = parseInt(localStorage.getItem('contadorSacola')) || 0;

// Cria o badge de contagem e adiciona à sacola
const sacolaIcon = document.querySelector('.sacola');
const contadorElement = document.createElement('span');
contadorElement.classList.add('contador-sacola');
contadorElement.textContent = contadorSacola;
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

// Exibe ou esconde dependendo da contagem
contadorElement.style.display = contadorSacola > 0 ? 'inline-block' : 'none';

// Seleciona os botões e adiciona o comportamento
const botoes = document.querySelectorAll('button');

botoes.forEach(botao => {
    if (botao.textContent.includes('Adicionar à sacola')) {
        botao.addEventListener('click', () => {
            contadorSacola++;
            localStorage.setItem('contadorSacola', contadorSacola); // Salva no localStorage
            contadorElement.textContent = contadorSacola;
            contadorElement.style.display = 'inline-block';
        });
    }
});




//método buscar
//conectar em todas as telas que precisar
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
   <div id="areaBusca"> </div>




document.addEventListener('DOMContentLoaded', function() {
    // Configuração da sacola
    let contadorSacola = parseInt(localStorage.getItem('contadorSacola')) || 0;
    const sacolaIcon = document.querySelector('.sacola');

    if (sacolaIcon) {
        const contadorElement = document.createElement('span');
        contadorElement.classList.add('contador-sacola');
        contadorElement.textContent = contadorSacola;
        sacolaIcon.parentNode.appendChild(contadorElement);

        // Estilo do contador (igual ao anterior)
        contadorElement.style.position = 'absolute';
        contadorElement.style.top = '10px';
        contadorElement.style.right = '10px';
        contadorElement.style.backgroundColor = '#620246';
        contadorElement.style.color = 'white';
        contadorElement.style.borderRadius = '50%';
        contadorElement.style.padding = '2px 8px';
        contadorElement.style.fontSize = '14px';
        contadorElement.style.fontWeight = 'bold';
        contadorElement.style.display = contadorSacola > 0 ? 'inline-block' : 'none';

        sacolaIcon.addEventListener('click', (e) => {
            e.preventDefault();
            const currentUrl = window.location.href;
            const newUrl = contadorSacola > 0
                ? currentUrl.replace(/[^/]*$/, 'sacola.html')
                : currentUrl.replace(/[^/]*$/, 'sacolaVazia.html');
            window.location.href = newUrl;
        });
    }

    // Configura o botão "Adicionar à sacola"
    const btnAdicionar = document.querySelector('.add-to-cart');
    if (btnAdicionar) {
        btnAdicionar.addEventListener('click', function() {
            // Obter o ID do produto da URL ou de outro lugar
            const urlParams = new URLSearchParams(window.location.search);
            const produtoId = urlParams.get('id') || '1';

            let contadorSacola = parseInt(localStorage.getItem('contadorSacola')) || 0;
            contadorSacola++;
            localStorage.setItem('contadorSacola', contadorSacola);

            const contadorElement = document.querySelector('.contador-sacola');
            if (contadorElement) {
                contadorElement.textContent = contadorSacola;
                contadorElement.style.display = 'inline-block';
            }

            // Armazena o produto na sacola
            let sacola = JSON.parse(localStorage.getItem('sacolaItens') || '[]');
            sacola.push(produtoId);
            localStorage.setItem('sacolaItens', JSON.stringify(sacola));
        });
    }
});
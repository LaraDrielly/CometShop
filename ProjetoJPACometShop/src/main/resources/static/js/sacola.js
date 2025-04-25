document.addEventListener('DOMContentLoaded', () => {
    let carrinho = JSON.parse(localStorage.getItem('carrinho')) || [];

    const containerCarrinho = document.querySelector('.product-cards-container:first-child');
    const quantidadeTotalEl = document.getElementById('qtd-total');
    const totalPrecoEl = document.getElementById('preco-total');
    const sacolaIcon = document.querySelector('.sacola');

    let quantidadeTotal = 0;
    let valorTotal = 0;

    // Criar contador no ícone da sacola
    let contadorElement = document.querySelector('.contador-sacola');
    if (!contadorElement) {
        contadorElement = document.createElement('span');
        contadorElement.classList.add('contador-sacola');
        sacolaIcon?.parentElement?.appendChild(contadorElement);

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

    // Se houver produtos no carrinho, carregar na tela
    if (containerCarrinho) {
        if (carrinho.length > 0) removerSacolaVazia();

        carrinho.forEach(produto => {
            adicionarProdutoNoHTML(produto);
            quantidadeTotal += produto.quantidade;
            valorTotal += produto.preco * produto.quantidade;
        });

        atualizarResumo();
    }

    atualizarContadorSacola();

    // Lógica para todos os botões "Adicionar à sacola"
    const botoes = Array.from(document.querySelectorAll('button')).filter(btn =>
        btn.textContent.trim().toLowerCase() === 'adicionar à sacola'
    );

    botoes.forEach(botao => {
        botao.classList.add('add-to-cart');
        botao.addEventListener('click', () => {
            const card = botao.closest('.product-card, .product-card2');
            if (!card) return;

            const nome = card.querySelector('h1, p')?.textContent?.trim() || 'Produto sem nome';
            const imagem = card.querySelector('img')?.getAttribute('src') || '/Imagens/produto.png';
            const precoStr = card.querySelector('.preco')?.textContent?.replace('R$', '').replace(',', '.').trim();
            const preco = precoStr ? parseFloat(precoStr) : 135.00;

            const produto = {
                id: gerarID(nome),
                nome: nome,
                preco: preco,
                imagem: imagem,
                quantidade: 1
            };

            const existente = carrinho.find(p => p.id === produto.id);
            if (existente) {
                existente.quantidade++;
            } else {
                carrinho.push(produto);
                if (containerCarrinho) {
                    removerSacolaVazia();
                    adicionarProdutoNoHTML(produto);
                }
            }

            quantidadeTotal++;
            valorTotal += produto.preco;

            atualizarCarrinho();
            if (containerCarrinho) atualizarResumo();
            atualizarContadorSacola();
        });
    });

    // Gera um ID a partir do nome do produto
    function gerarID(nome) {
        return nome.toLowerCase().replace(/\s+/g, '-').replace(/[^\w-]/g, '');
    }

    // Remove mensagem de sacola vazia
    function removerSacolaVazia() {
        const sacolaVazia = document.querySelector('.sacola-vazia');
        if (sacolaVazia) sacolaVazia.remove();
    }

    // Adiciona o produto no HTML da sacola
    function adicionarProdutoNoHTML(produto) {
        const novoCard = document.createElement('div');
        novoCard.classList.add('product-card');
        novoCard.innerHTML = `
            <a href="#">
                <img class="product-image" src="${produto.imagem}" alt="${produto.nome}">
            </a>
            <div class="product-info">
                <div class="product-title">${produto.nome}</div>
                <div class="product-price">R$ ${produto.preco.toFixed(2).replace('.', ',')}</div>
                <div class="quantidade-container">
                    <button class="menos">−</button>
                    <span class="quantidade">${produto.quantidade}</span>
                    <button class="mais">+</button>
                </div>
            </div>
        `;
        containerCarrinho.appendChild(novoCard);
        adicionarEventos(novoCard, produto);
    }

    // Adiciona os eventos dos botões + e -
    function adicionarEventos(card, produto) {
        const btnMais = card.querySelector('.mais');
        const btnMenos = card.querySelector('.menos');
        const qtdEl = card.querySelector('.quantidade');

        btnMais.addEventListener('click', () => {
            produto.quantidade++;
            qtdEl.textContent = produto.quantidade;
            quantidadeTotal++;
            valorTotal += produto.preco;

            atualizarCarrinho();
            atualizarResumo();
            atualizarContadorSacola();
        });

        btnMenos.addEventListener('click', () => {
            if (produto.quantidade > 1) {
                produto.quantidade--;
                qtdEl.textContent = produto.quantidade;
                quantidadeTotal--;
                valorTotal -= produto.preco;
            } else {
                quantidadeTotal--;
                valorTotal -= produto.preco;
                card.remove();
                carrinho = carrinho.filter(item => item.id !== produto.id);
            }

            atualizarCarrinho();
            atualizarResumo();
            atualizarContadorSacola();

            if (carrinho.length === 0) mostrarSacolaVazia();
        });
    }

    // Atualiza o resumo da compra e salva no localStorage
    function atualizarResumo() {
        if (quantidadeTotalEl && totalPrecoEl) {
            quantidadeTotalEl.textContent = quantidadeTotal;
            totalPrecoEl.textContent = `R$ ${valorTotal.toFixed(2).replace('.', ',')}`;
        }

        localStorage.setItem("quantidadeProdutos", quantidadeTotal);
        localStorage.setItem("precoTotal", `R$ ${valorTotal.toFixed(2).replace('.', ',')}`);
    }

    // Salva o carrinho no localStorage
    function atualizarCarrinho() {
        localStorage.setItem('carrinho', JSON.stringify(carrinho));
    }

    // Mostra a mensagem de sacola vazia
    function mostrarSacolaVazia() {
        const sacolaVaziaHTML = `
            <div class="sacola-vazia">
                <img src="/Imagens/Sacola Icon.png" alt="Sacola Vazia">
                <div>
                    <p>Sua sacola está vazia</p>
                    <a href="#">Confira nossos produtos</a>
                </div>
            </div>
        `;
        containerCarrinho.innerHTML = sacolaVaziaHTML;
    }

    // Atualiza o contador de quantidade no ícone da sacola
    function atualizarContadorSacola() {
        const total = carrinho.reduce((soma, item) => soma + item.quantidade, 0);
        contadorElement.textContent = total;
        contadorElement.style.display = total > 0 ? 'inline-block' : 'none';
    }
});

document.addEventListener('DOMContentLoaded', () => {
    // Lê o carrinho do localStorage
    let carrinho = JSON.parse(localStorage.getItem('carrinho')) || [];

    // Verifica se o carrinho está vazio
    if (carrinho.length === 0) {
        // Redireciona para a página de "Sacola Vazia"
        window.location.href = '/sacolaVazia.html';  // Substitua com o caminho correto para o seu HTML
        return;  // Interrompe o código restante, não carregando mais nada
    }

    const containerCarrinho = document.querySelector('.product-cards-container:first-child');
    const quantidadeTotalEl = document.getElementById('qtd-total');
    const totalPrecoEl = document.getElementById('preco-total');

    let quantidadeTotal = 0;
    let valorTotal = 0;

    // Atualiza o carrinho com base no que já está no localStorage (Carrinho já exibido)
    carrinho.forEach(produto => {
        const novoCard = document.createElement('div');
        novoCard.classList.add('product-card');
        novoCard.innerHTML = `
            <a href="#">
                <img class="product-image" src="${produto.imagem}" alt="${produto.nome}">
            </a>
            <div class="product-info">
                <div class="product-title">${produto.nome}</div>
                <div class="product-price">R$ ${produto.preco.toFixed(2)}</div>
                <div class="quantidade-container">
                    <button class="menos">−</button>
                    <span class="quantidade">${produto.quantidade}</span>
                    <button class="mais">+</button>
                </div>
            </div>
        `;
        containerCarrinho.appendChild(novoCard);

        quantidadeTotal += produto.quantidade;
        valorTotal += produto.preco * produto.quantidade;

        adicionarEventos(novoCard, produto);
    });

    // Atualiza os totais
    atualizarResumo();

    // Função para adicionar eventos nos botões de quantidade
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
        });

        btnMenos.addEventListener('click', () => {
            if (produto.quantidade > 1) {
                produto.quantidade--;
                qtdEl.textContent = produto.quantidade;
                quantidadeTotal--;
                valorTotal -= produto.preco;

                atualizarCarrinho();
                atualizarResumo();
            } else {
                quantidadeTotal--;
                valorTotal -= produto.preco;
                card.remove();

                // Remove o produto do carrinho no localStorage
                carrinho = carrinho.filter(item => item !== produto);
                localStorage.setItem('carrinho', JSON.stringify(carrinho));

                atualizarResumo();
            }
        });
    }

    // Função para atualizar o carrinho no localStorage
    function atualizarCarrinho() {
        localStorage.setItem('carrinho', JSON.stringify(carrinho));
    }

    // Função para atualizar os totais
    function atualizarResumo() {
        quantidadeTotalEl.textContent = quantidadeTotal;
        totalPrecoEl.textContent = `R$ ${valorTotal.toFixed(2).replace('.', ',')}`;
    }

    // Lógica para adicionar produtos na sacola (quando clicar nos botões "Adicionar à sacola")
    const botoesAdicionarSacola = document.querySelectorAll('.add-to-cart');
    botoesAdicionarSacola.forEach(botao => {
        botao.addEventListener('click', () => {
            const produto = {
                nome: 'Soft Pinch Tinted Lip Oil', // Substitua com os dados do produto real
                preco: 135.00, // Substitua com o preço real
                imagem: 'caminho-da-imagem-do-produto.jpg', // Substitua com o caminho da imagem
                quantidade: 1
            };

            // Adiciona o produto ao carrinho
            carrinho.push(produto);
            quantidadeTotal++;
            valorTotal += produto.preco;

            // Atualiza o localStorage
            atualizarCarrinho();
            atualizarResumo();

            // Exibe o contador de sacola
            const contadorSacola = document.querySelector('.contador-sacola');
            if (contadorSacola) {
                contadorSacola.textContent = quantidadeTotal;
                contadorSacola.style.display = 'inline-block';
            }
        });
    });
});

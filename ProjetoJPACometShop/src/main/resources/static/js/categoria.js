
document.addEventListener('DOMContentLoaded', async function() {
    // 1) contador da sacola (igual ao anterior)
    let contadorSacola = parseInt(localStorage.getItem('contadorSacola')) || 0;
    const sacolaIcon = document.querySelector('.sacola');
    if (sacolaIcon) {
        const contadorElement = document.createElement('span');
        contadorElement.classList.add('contador-sacola');
        contadorElement.textContent = contadorSacola;
        sacolaIcon.parentNode.appendChild(contadorElement);
        Object.assign(contadorElement.style, {
            position: 'absolute',
            top: '10px',
            right: '10px',
            backgroundColor: '#620246',
            color: 'white',
            borderRadius: '50%',
            padding: '2px 8px',
            fontSize: '14px',
            fontWeight: 'bold',
            display: contadorSacola > 0 ? 'inline-block' : 'none'
        });
        sacolaIcon.addEventListener('click', e => {
            e.preventDefault();
            window.location.href = contadorSacola > 0 ? '/sacola' : '/sacolaVazia';
        });
    }

    // 2) busca e renderização dos produtos
    try {
        const response = await fetch('/admin/produtos');
        const html = await response.text();
        const produtos = extrairProdutosDoHTML(html);
        const produtosCabelos = produtos.filter(p =>
            p.categorias && p.categorias.some(cat => cat.id === 1)
        );
        renderizarProdutos(produtosCabelos);
    } catch (error) {
        console.error('Erro ao carregar produtos:', error);
        const produtosMockados = [
            { id: 1, nome: 'Shampoo Hidratante' },
            { id: 2, nome: 'Condicionador Reparador' }
        ];
        renderizarProdutos(produtosMockados);
    }

    // 3) configuração da busca
    const botaoBusca = document.getElementById('botaoBusca');
    const campoBusca = document.getElementById('campoBusca');
    if (botaoBusca && campoBusca) {
        botaoBusca.addEventListener('click', () => buscarProdutos(campoBusca.value.trim()));
        campoBusca.addEventListener('keypress', e => {
            if (e.key === 'Enter') botaoBusca.click();
=======
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
            // Detalhes do produto
            const card = botao.closest('.product-card');
            const nomeProduto = card.querySelector('p').textContent;
            const imagemProduto = card.querySelector('img').getAttribute('src');
            const precoProduto = 135.00; // Preço fixo para este exemplo

            // Adiciona o produto ao carrinho no localStorage
            let carrinho = JSON.parse(localStorage.getItem('carrinho')) || [];
            carrinho.push({
                nome: nomeProduto,
                imagem: imagemProduto,
                preco: precoProduto,
                quantidade: 1
            });
            localStorage.setItem('carrinho', JSON.stringify(carrinho));

            // Atualiza o contador
            contadorSacola++;
            localStorage.setItem('contadorSacola', contadorSacola); // Salva no localStorage
            contadorElement.textContent = contadorSacola;
            contadorElement.style.display = 'inline-block';
>>>>>>> 3d9404405c77bf0e25b0b75004acac0239a28469
        });
    }
});

<<<<<<< HEAD
// extrai produtos do HTML de /admin/produtos
function extrairProdutosDoHTML(html) {
    const parser = new DOMParser();
    const doc = parser.parseFromString(html, 'text/html');
    const rows = doc.querySelectorAll('table tr');
    const produtos = [];
    rows.forEach(row => {
        const cols = row.querySelectorAll('td');
        if (cols.length >= 4) {
            const produto = {
                id: row.getAttribute('data-id') || Math.random().toString(36).substr(2, 9),
                nome: cols[0].textContent.trim(),
                categorias: []
            };
            if (/Cabelos|1/.test(cols[3].textContent)) {
                produto.categorias.push({ id: 1, nome: 'Cabelos' });
            }
            produtos.push(produto);
        }
    });
    return produtos;
}

// renderiza produtos em linhas de 4
function renderizarProdutos(produtos) {
    const area = document.getElementById('areaBusca');
    if (!area) return;
    area.innerHTML = '';

    if (produtos.length === 0) {
        area.innerHTML = '<p class="sem-resultados">Nenhum produto nesta categoria.</p>';
        return;
    }

    // Agrupar em linhas de 4
    for (let i = 0; i < produtos.length; i += 4) {
        const productBox = document.createElement('div');
        productBox.className = 'product-box';
        // no seu CSS, .product-box pode ser display: flex; gap: 20px; flex-wrap: wrap; etc.

        produtos.slice(i, i + 4).forEach(produto => {
            const card = document.createElement('div');
            card.className = 'product-card';
            card.innerHTML = `
        <a href="/produto?id=${produto.id}">
          <img src="../static/Imagens/produto2.webp" alt="${produto.nome}">
        </a>
        <p>${produto.nome}</p>
        <button class="btn-adicionar" data-produto-id="${produto.id}">Adicionar à sacola</button>
      `;
            productBox.appendChild(card);
        });

        area.appendChild(productBox);
    }

    // eventos dos botões "Adicionar à sacola"
    document.querySelectorAll('.btn-adicionar').forEach(btn => {
        btn.addEventListener('click', () => {
            let count = parseInt(localStorage.getItem('contadorSacola')) || 0;
            count++;
            localStorage.setItem('contadorSacola', count);
            const el = document.querySelector('.contador-sacola');
            if (el) {
                el.textContent = count;
                el.style.display = 'inline-block';
            }
            btn.textContent = 'Adicionado!';
            setTimeout(() => btn.textContent = 'Adicionar à sacola', 1000);
        });
    });
}

// busca simples por nome
function buscarProdutos(termo) {
    const cards = document.querySelectorAll('.product-card');
    let encontrou = false;
    cards.forEach(card => {
        const nome = card.querySelector('p').textContent.toLowerCase();
        if (!termo || nome.includes(termo.toLowerCase())) {
            card.style.display = '';
            encontrou = true;
            if (termo) {
                const regex = new RegExp(`(${termo})`, 'gi');
                card.querySelector('p').innerHTML =
                    card.querySelector('p').textContent.replace(regex, '<mark>$1</mark>');
            }
        } else {
            card.style.display = 'none';
        }
    });

    const area = document.getElementById('areaBusca');
    let msg = area.querySelector('.sem-resultados');
    if (!encontrou && termo) {
        if (!msg) {
            msg = document.createElement('p');
            msg.className = 'sem-resultados';
            area.appendChild(msg);
        }
        msg.textContent = 'Nenhum produto encontrado com esse termo.';
    } else if (msg) {
        msg.remove();
    }
}
=======
// Método de busca
document.getElementById('buscaTexto').addEventListener('input', function () {
    const termo = this.value.trim().toLowerCase();
    const areaBusca = document.getElementById('areaBusca');

    if (!areaBusca) return;

    const cards = areaBusca.querySelectorAll('.product-card');
    cards.forEach(card => {
        const p = card.querySelector('p');
        const textoOriginal = p.getAttribute('data-original') || p.textContent;
        p.setAttribute('data-original', textoOriginal);
        p.innerHTML = textoOriginal;

        if (termo.length > 0) {
            const regex = new RegExp(`(${termo})`, 'gi');
            p.innerHTML = textoOriginal.replace(regex, '<mark>$1</mark>');
        }
    });
});
>>>>>>> 3d9404405c77bf0e25b0b75004acac0239a28469

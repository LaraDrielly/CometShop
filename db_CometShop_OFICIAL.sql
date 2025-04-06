-- Tabela de administradores
CREATE TABLE administrador (
    id_administrador SERIAL PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    status BOOLEAN DEFAULT FALSE
);

-- Tabela de usuários
CREATE TABLE usuario (
    id_usuario SERIAL PRIMARY KEY,
    senha VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    nome_completo VARCHAR(200) NOT NULL,
    telefone VARCHAR(11),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Endereços dos usuários
CREATE TABLE endereco (
    id_endereco SERIAL PRIMARY KEY,
    cod_usuario INTEGER NOT NULL REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    telefoneContato VARCHAR(11) NOT NULL,
    numero VARCHAR(10),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    rua VARCHAR(100) NOT NULL,
    cep VARCHAR(10) NOT NULL
);

-- Categorias de produtos
CREATE TABLE categoria (
    id_categoria SERIAL PRIMARY KEY,
    nome VARCHAR(100) UNIQUE NOT NULL
);

-- Produtos
CREATE TABLE produto (
    id_produto SERIAL PRIMARY KEY,
    cod_categoria INTEGER REFERENCES categoria(id_categoria),
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(1000) NOT NULL,
    preco NUMERIC(10,2) NOT NULL CHECK(preco > 0.00),
);

-- Imagens dos produtos
CREATE TABLE imagem_produto (
    id_imagem SERIAL PRIMARY KEY,
    cod_produto INTEGER NOT NULL REFERENCES produto(id_produto) ON DELETE CASCADE,
    url TEXT NOT NULL,
);

-- Tabela de movimentações de estoque
CREATE TABLE estoque (
    id_movimentacao SERIAL PRIMARY KEY,
    cod_produto INTEGER NOT NULL REFERENCES produto(id_produto) ON DELETE CASCADE,
    tipo_movimentacao VARCHAR(20) NOT NULL CHECK (tipo_movimentacao IN ('ENTRADA', 'SAIDA')),
    quantidade INTEGER NOT NULL CHECK (quantidade > 0),
    data_movimentacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    observacao TEXT
);

-- Pedidos
CREATE TABLE pedido (
    id_pedido SERIAL PRIMARY KEY,
    cod_usuario INTEGER NOT NULL REFERENCES usuario(id_usuario),
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'PENDENTE'
);

-- Itens dos pedidos
CREATE TABLE item_pedido (
    id_item SERIAL PRIMARY KEY,
    cod_pedido INTEGER NOT NULL REFERENCES pedido(id_pedido) ON DELETE CASCADE,
    cod_produto INTEGER NOT NULL REFERENCES produto(id_produto),
    quantidade INTEGER NOT NULL CHECK(quantidade > 0),
    preco NUMERIC(100,2) NOT NULL CHECK(preco_unitario > 0)
);

-- Pagamento de pedidos
CREATE TABLE pagamento (
    id_pagamento SERIAL PRIMARY KEY,
    cod_pedido INTEGER NOT NULL REFERENCES pedido(id_pedido) ON DELETE CASCADE,
    valor NUMERIC(10,2) NOT NULL CHECK (valor > 0),
    metodo_pagamento VARCHAR(50) NOT NULL,
    data_pagamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Sacola (carrinho de compras)
CREATE TABLE sacola (
    id_sacola SERIAL PRIMARY KEY,
    cod_usuario INTEGER NOT NULL REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    cod_item_pedido INTEGER NOT NULL REFERENCES item_pedido(id_item),
    quantidade INTEGER NOT NULL CHECK(quantidade > 0),
    preco_total NUMERIC(100,2) NOT NULL CHECK(preco_total > 0)
    UNIQUE (cod_usuario, cod_item_pedido)
);

-- Cartão de crédito
CREATE TABLE cartao_credito (
    id_cartao SERIAL PRIMARY KEY,
    cod_usuario INTEGER NOT NULL REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    numero_cartao VARCHAR(20) NOT NULL,
    validade DATE NOT NULL,
    cvv VARCHAR(4) NOT NULL,
);
-- CometShop  - DAD

-- Tabela que armazena os dados dos administradores
CREATE TABLE administrador ( 
    id_administrador SERIAL PRIMARY KEY,  
    nome_completo VARCHAR(100) NOT NULL,  
    email VARCHAR(255) UNIQUE NOT NULL,  
    senha VARCHAR(255) NOT NULL,  
    status BOOLEAN DEFAULT FALSE
); 

-- Tabela que armazena as informações dos usuários
CREATE TABLE usuario ( 
    id_usuario SERIAL PRIMARY KEY,  
    nome_perfil VARCHAR(50) UNIQUE NOT NULL,  
    senha VARCHAR(255) NOT NULL,  
    email VARCHAR(255) UNIQUE NOT NULL,  
    nome_completo VARCHAR(200) NOT NULL,  
    telefone VARCHAR(11),
); 

-- Tabela dos produtos anunciados
CREATE TABLE produtos (
    id_produto SERIAL PRIMARY KEY,
	cod_usuario INTEGER NOT NULL REFERENCES usuario (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    descricao VARCHAR(1000) NOT NULL,  
    nome VARCHAR(255) NOT NULL,  
    preco NUMERIC(10,2) NOT NULL CHECK(preco > 0.00),
);

-- Tabela que armazena a quantidade de produtos em estoque
CREATE TABLE estoque ( 
    id_estoque SERIAL PRIMARY KEY,  
    cod_produto INTEGER NOT NULL REFERENCES produtos (id_produto) ON DELETE CASCADE ON UPDATE CASCADE,
    quant_estoque INT NOT NULL
); 

-- Tabela de categorias dos produtos
CREATE TABLE categoria (
    id_categoria SERIAL PRIMARY KEY,
    categoria VARCHAR(100) NOT NULL
);

-- Tabela de associação entre produtos e categorias
CREATE TABLE produtos_categorias ( 
    cod_categoria INTEGER NOT NULL,  
    cod_produto INTEGER NOT NULL,  
    PRIMARY KEY (cod_categoria, cod_produto),
    FOREIGN KEY (cod_categoria) REFERENCES categoria (id_categoria) ON DELETE CASCADE ON UPDATE CASCADE
); 

-- Tabela de cartões
CREATE TABLE cartao ( 
    id_cartao SERIAL PRIMARY KEY,  
    cod_usuario INTEGER NOT NULL REFERENCES usuario (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,  
    numero_cartao CHAR(16) UNIQUE NOT NULL,  
    cvv CHAR(3) UNIQUE NOT NULL,  
    dt_validade DATE NOT NULL CHECK(dt_validade > CURRENT_DATE)
); 

-- Tabela de carrinho de compras
CREATE TABLE carrinho ( 
    id_carrinho SERIAL PRIMARY KEY,  
    cod_usuario INTEGER NOT NULL REFERENCES usuario (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
); 

-- Tabela de associação entre carrinhos e produtos
CREATE TABLE carrinho_produtos (
    cod_carrinho INTEGER NOT NULL,
    cod_produto INTEGER NOT NULL,
    PRIMARY KEY (cod_carrinho, cod_produto),
    FOREIGN KEY (cod_carrinho) REFERENCES carrinho (id_carrinho) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (cod_produto) REFERENCES produtos (id_produto) ON DELETE CASCADE ON UPDATE CASCADE
); 

-- Tabela de tipos de pagamento
CREATE TABLE tipo_pagamento ( 
    id_tipo_pagamento SERIAL PRIMARY KEY,  
    tipo VARCHAR(20) NOT NULL,
);

-- Tabela que armazena as informações dos pedidos
CREATE TABLE pedido ( 
    id_pedido SERIAL PRIMARY KEY,
    cod_carrinho INTEGER NOT NULL REFERENCES carrinho (id_carrinho) ON DELETE CASCADE ON UPDATE CASCADE,
    cod_tipo_pagamento INTEGER NOT NULL REFERENCES tipo_pagamento (id_tipo_pagamento) ON UPDATE CASCADE,
	dt_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  
    valor_total DECIMAL(10,2) NOT NULL CHECK (valor_total > 0.00),  
    cep_usuario CHAR(8) CHECK(LENGTH(cep_usuario) = 8),
    bairro VARCHAR(255) NOT NULL,  
    rua_avenida VARCHAR(255) NOT NULL,  
    complemento VARCHAR(255) NOT NULL,  
    numero INTEGER NOT NULL,
    telefone VARCHAR(11),
); 

-- Tabela de URLs de imagens
CREATE TABLE url_imagem ( 
    id_imagem SERIAL PRIMARY KEY,
    cod_produto INTEGER REFERENCES produtos (id_produto) ON DELETE CASCADE ON UPDATE CASCADE,
    url VARCHAR(255) NOT NULL
); 

CREATE TABLE avaliacao(
    id_avaliacao SERIAL PRIMARY KEY,
    avaliacao INTEGER NOT NULL,
    cod_usuario INTEGER NOT NULL REFERENCES usuario (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    cod_produto INTEGER NOT NULL REFERENCES produtos (id_produto) ON DELETE CASCADE ON UPDATE CASCADE,  


)

CREATE TABLE comentario(
    cod_usuario INTEGER NOT NULL REFERENCES usuario (id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
    comentario VARCHAR(255) NOT NULL,
    dt_comentario TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cod_avaliacao  INTEGER NOT NULL REFERENCES avaliacao (id_avaliacao)
);
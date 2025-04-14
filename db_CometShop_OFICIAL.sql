
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
    preco NUMERIC(10,2) NOT NULL CHECK(preco > 0.00)
);

-- Imagens dos produtos
CREATE TABLE imagem_produto (
    id_imagem SERIAL PRIMARY KEY,
    cod_produto INTEGER NOT NULL REFERENCES produto(id_produto) ON DELETE CASCADE,
    url VARCHAR(3050) NOT NULL
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
    preco NUMERIC(100,2) NOT NULL CHECK(preco > 0)
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
    preco_total NUMERIC(100,2) NOT NULL CHECK(preco_total > 0),
    UNIQUE (cod_usuario, cod_item_pedido)
);

-- Cartão de crédito
CREATE TABLE cartao_credito (
    id_cartao SERIAL PRIMARY KEY,
    cod_usuario INTEGER NOT NULL REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    numero_cartao VARCHAR(20) NOT NULL,
    validade DATE NOT NULL,
    cvv VARCHAR(4) NOT NULL
);



--Dataload
INSERT INTO administrador (nome_completo, email, senha, status)
VALUES 
('Carolina Teraoka', 'carolina.teraoka@germinare.org.br', '12345', TRUE),
('Lara Silva', 'lara.silva@germinare.org.br', '23456', TRUE),
('Gustavo Munhoz', 'gustavo.jovelli@germinare.org.br', '34567', TRUE);

INSERT INTO categoria (nome)
VALUES 
('Cabelos'), 
('Perfumes'),
('Unhas'), 
('Maquiagens'), 
('Skin care'), 
('Corpo e banho');

INSERT INTO produto (cod_categoria, nome, descricao, preco)
VALUES 
(1, 'Ricca Love Is In The Hair', 'Shampoo a Seco 150ml', 26.90),
(1, 'L''Oréal Professionnel Serie Expert Pro Longer', 'Máscara Capilar 500g', 214.90),
(1, 'Lola Cosmetics Argan Oil', 'Óleo Capilar 50ml', 17.90),
(1, 'Wella Professionals Invigo Aqua Pure', 'Shampoo Antirresíduos 250ml', 72.90),
(1, 'Braé Hair Protein', 'Leave-in 80g', 65.80),
(1, 'Alfaparf Semi di Lino Sublime Cristalli Liquidi', 'Óleo Capilar 30ml', 52.70),
(1, 'Lola From Rio Densidade', 'Acidificante 250g', 40.90),
(1, 'Braé Stages Hydration', 'Shampoo 250ml', 37.90),

(2, '212 VIP Rosé Carolina Herrera Eau de Parfum', 'Perfume Feminino 125ml', 708.90),
(2, '1 Million Rabanne Eau de Toilette', 'Perfume Masculino 200ml', 774.90),
(2, 'Idôle Lancôme Eau de Parfum', 'Perfume Feminino 50ml', 505.85),
(2, 'Chloé Eau de Parfum', 'Perfume Feminino 75ml', 660.90),
(2, 'Miss Dior Blooming Bouquet Dior Eau de Toilette', 'Perfume Feminino 100ml', 915.00),
(2, 'Good Girl Carolina Herrera Eau de Parfum', 'Perfume Feminino 30ml', 370.90),
(2, 'Tradicional Bebê Granado Eau de Cologne', 'Perfume Infantil 100ml', 65.90),
(2, 'Pingucho Ciclo Mini Ciclo Cosméticos Deo Colônia', 'Perfume Infantil 100ml', 53.90),

(3, 'Mavala Mini Colours Black Cherry', 'Esmalte Cintilante 5ml', 43.90),
(3, 'Dailus 215 Pipoca Doce', 'Esmalte Cremoso 8ml', 10.90),
(3, 'Granado Pink Rebecca', 'Esmalte 10ml', 18.90),
(3, 'OPI We The Female', 'Esmalte Cremoso 15ml', 35.90),
(3, 'Kiss New York Bloco Preta', 'Lixa de Unha (24 lixas)', 29.90),
(3, 'Blant Base Amarga 2x1', 'Base 8,5ml', 22.50),
(3, 'Granado Pink', 'Removedor de Esmalte 75ml', 30.90),
(3, 'Granado Pink Cera Nutritiva', 'Hidratante para Unhas e Cutículas 7g', 33.90),

(4, 'Dior Forever Skin Glow 2.5N', 'Base Líquida 30ml', 415.00),
(4, 'M·A·C Retro Matte Liquid Lipcolour Feels So Grand','Batom Líquido 5ml', 144.90),
(4, 'Maybelline The Colossal VolumExpress Lavável Preto','Máscara de Cílios 9,2ml', 57.90),
(4, 'Fran by Franciny Ehlke LipHoney','Gloss Labial 3,5ml', 69.90),
(4, 'Vizzela Brow Up Fix Incolor','Gel Fixador para Sobrancelhas 3g', 47.90),
(4, 'Catharine Hill Sombras Variadas 1017','Paleta de Sombras 24g', 124.90),
(4, 'Catharine Hill Angel Magic Pri Lessa Blindagem para Pele e Olhos','Fixador de Maquiagem 30ml', 56.90),
(4, 'Real Techniques Expert Face','Pincel para Base', 95.90),

(5, 'L''Oréal Paris Dermo Expertise Solução de Limpeza 5 em 1','Água Micelar 200ml', 26.90),
(5, 'Eucerin Anti-Pigment Dual','Sérum Facial Uniformizador 30ml', 374.99),
(5, 'Sallve FPS60 Cor 70','Protetor Solar com Cor 40g', 34.90),
(5, 'La Roche-Posay Effaclar Concentrado','Gel de Limpeza Facial 300g', 88.90),
(5, 'Neutrogena Hydro Boost Water Gel','Hidratante Facial 50g', 59.90),
(5, 'Vichy Minéral 89','Hidratante Facial 50ml', 160.90),
(5, 'Clinique Oily Skin Formula','Sabonete Líquido Facial 200ml', 238.90),
(5, 'Bioderma Sensibio H2O Calmante','Água Micelar 100ml', 41.90),

(6, 'Kit Phebo Mediterrâneo Alfazema Provençal','Sabonetes em Barra 3x100g', 17.70),
(6, 'Hidratante Cuide-se Bem Doçura na Pessegura','Loção Desodorante 400ml', 69.90),
(6, 'Dior Miss Dior','Óleo Corporal Esfoliante 175ml', 449.00),
(6, 'Neutrogena Sun Fresh FPS70','Protetor Solar 200ml', 107.90),
(6, 'Australian Gold Dark Tanning Accelerator','Spray Bronzeador 236g', 62.90),
(6, 'Lola Cosmetics Be(M) Dita Praia Geléia Corporal','Gel Pós-Sol 200ml', 32.90),
(6, 'ISDIN Ureadin','Gel de Banho 424g', 72.90),
(6, 'NIVEA Óleo de Banho','Sabonete Líquido 200ml', 30.90);



INSERT INTO imagem_produto (cod_produto, url)
VALUES
(1, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/20047563/58ad3dae-cdac-4d60-a7d0-45f71f48f479-20047563-ricca-shampoo-a-seco-maca-do-amor-150ml.png'), 
(2, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/80398/96deabd9-489c-49a4-91f2-5ae6617b55d0-loreal-professionnel-serie-expert-pro-longer-mascara-capilar-500g.png'),
(3, 'https://amobeleza.vtexassets.com/arquivos/ids/229436-1200-auto?v=638004888395330000&width=1200&height=auto&aspect=true'),
(4, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/products/57391/57391.png'),
(5, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/MP159125/6bf99e86-cd36-4e83-83ed-31991ee394c7-mp159125-brae-proteina-condicionante-concentrada-80g.png'),
(6, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/60571/accc282f-a8cd-44ea-bc9e-6c0deaf7f2ce-alfaparf-semi-di-lino-sublime-cristalli-liquidi-oleo-capilar-30ml.png'),
(7, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/20044165/9fc09e04-325f-4835-9457-f28ba320a9f5-lola-cosmetics-densidade-acidificante-250g.png'),
(8, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/MP285673/a56fd8e4-90f8-4f42-bcdd-98c5b0dc1096-2024-08-29t114425608821185-66041384a8465e50c7b41a5a-6744151916520980842mp285673.jpg'),

(9, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/38987/50a9d0ab-4f70-45c1-b2dd-79c29ed0acc4-212-vip-rose-carolina-herrera-eau-de-parfum-perfume-feminino-125ml.png'),
(10, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/18183/938bd213-2b54-4ae6-a9f0-d997882daac2-1-million-paco-rabanne-eau-de-toilette-perfume-masculino-200ml.png'),
(11, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/38987/50a9d0ab-4f70-45c1-b2dd-79c29ed0acc4-212-vip-rose-carolina-herrera-eau-de-parfum-perfume-feminino-125ml.png'),
(12, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/9995/7d9ee408-ba43-473a-8ca3-d0c3bb6cd704-chloe-eau-de-parfum-perfume-feminino-75ml.png'),
(13, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/20054490/a27bdeee-f494-4495-b7f1-c9187ee02ef9-miss-dior-blooming-bouquet-dior-eau-de-toilette-perfume-feminino-100ml.png'),
(14, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/38273/4f792c37-87ca-4624-b94a-f979c765356e-good-girl-carolina-herrera-eau-de-parfum-perfume-feminino-30ml.png'),
(15, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/71862/10505d3c-ae75-4d5f-ab08-cfea5564c186-71862-granado-bebe-tradicional-colonia-100ml.png'),
(16, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/80529/9bd7f267-bfd0-4d74-b9f9-ec6026192758-80529.png'),

(17, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/5/mavala-mini-colours-black-cherry-esmalte-cintilante-5ml-6042-7455798994943456586.jpg'),
(18, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/59281/f9f97d3b-1b0a-4728-97ff-de12f3dc0f28-dailus-215-pipoca-doce-esmalte-cremoso-8ml.png'),
(19, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/68922/412eda4d-993f-4b9d-8b20-d3f104b4468b-68922-granado-pink-rebecca-esmalte-fortalecedor-10ml.png'),
(20, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/68585/413f5d0e-6c4d-48b9-b979-aee3887b196c-opi-we-the-female-esmalte-cremoso-15ml.png'),
(21, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/82864/faa76f81-30f6-4956-97ee-667f4fca24ae-82864-kiss-new-york-kiss-lixa-bloco-preta-60-100-24un.png'),
(22, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/81400/bf64b5db-29cc-4a8e-9113-4582bc76dade-81400-blant-4-free-base-para-unhas-85ml.png'),
(23, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/62745/879c1543-ceaa-4036-b635-70d149930795-granado-pink-removedor-de-esmalte-75ml.png'),
(24, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/5/granado-pink-cera-nutritiva-hidratante-para-unhas-e-cuticulas-7g-27771-8140027448484999338.png'),

(25, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/20044794/cc6bbd6c-fe50-4e75-a5ab-efed29c6e10a-dior-forever-skin-glow-25n-base-liquida-30ml.png'),
(26, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/5/mac-retro-matte-lipcolour-feels-so-grand-batom-liquido-5ml-33856-3833785550739883955.png'),
(27, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/24736/419f3692-c512-4a76-a136-ed36d11f1c31-maybelline-the-colossal-volumexpress-lavavel-preto-mascara-de-cilios-92ml.png'),
(28, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/20071208/26b66cda-a968-42f4-a359-26e9e393fc4e-fran-by-franciny-ehlke-liphoney-gloss-labial-35ml.png'),
(29, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/MP242173/320c3328-d637-4f7e-8af0-9cff8c96f420-2024-05-29t14393335918851-6421e55ea56ab95773b4d583-11569271733551762347.jpg'),
(30, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/38352/d1c64771-6749-4154-bf60-5b82753f1eaa-catharine-hill-sombras-variadas-1017-paleta-de-sombras-24g.png'),
(31, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/92143/c8c01182-9cdd-4ebe-9698-442f5429e560-catharine-hill-angel-magic-pri-lessa-blindagem-para-pele-e-olhos-fixador-de-maquiagem-30ml.png'),
(32, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/29222/a731b678-ad84-48c1-ad9e-4bcd5e361f5b-real-techniques-expert-face-pincel-para-base.png'),

(33, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/33695/e0f2592e-63db-4f80-918f-204da9f269d9-loreal-paris-dermo-expertise-solucao-de-limpeza-5-em-1-agua-micelar-200ml.png'),
(34, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/90681/188c1ff9-ec2e-46d7-b48e-38722bc0f61c-90681-eucerin-eucerin-anti-pigment-dual-serum-facial-30ml.png'),
(35, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/20062557/3b54fdc9-a620-4254-8b1a-8f7ee3918572-sallve-fps60-cor-70-protetor-solar-com-cor-40g.png'),
(36, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/83147/9d10096e-fd4e-42c5-8c69-946ad1d6ed59-la-rocheposay-effaclar-concentrado-gel-de-limpeza-facial-300g.png'),
(37, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/38891/c3e1113a-2a81-4f49-9130-5ce6f0e999de-neutrogena-hydro-boost-water-gel-hidratante-facial-50g.png'),
(38, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/48583/9652144a-b838-408b-a6d2-745695c9a476-vichy-mineral-89-hidratante-facial-50ml.png'),
(39, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/products/15650/15650_a.png'),
(40, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/40267/9ed5ed3c-2954-4c31-b2b7-03b41d243625-bioderma-sensibio-h2o-calmante-agua-micelar-100ml.png'),

(41, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/38167/21f1a594-346a-423e-ab2e-5d5b4d90b4b5-kit-phebo-mediterraneo-alfazema-provencal-sabonetes-em-barra-3x100g.png'),
(42, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/B57042/9b874156-e0ca-4980-b611-b6b489f1980c-bot-57042-cuide-se-bem-docura-na-pessegura-locao-hidratante-400ml-frontal-01.jpg'),
(43, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/20066483/4c7d7559-0705-489c-91d9-4cec5b9d0da3-dior-miss-dior-oleo-corporal-esfoliante-175ml.png'),
(44, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/30454/efb79afa-94ab-4133-ac4d-634a968b16b5-neutrogena-sun-fresh-fps-70-protetor-solar-200ml.png'),
(45, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/10805/1c95ccef-44ae-4799-9e3a-d9207d2baff1-australian-gold-dark-tanning-accelerator-spray-with-instant-bronzer-spray-bronzeador-237g.png'),
(46, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/5/lola-cosmetics-bem-dita-praia-geleia-corporal-gel-pos-sol-200ml-51058-2524020583990736166.jpg'),
(47, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/37605/f5f23b16-de5b-4073-a2ce-45763d1466c4-isdin-ureadin-bath-gel-de-banho-500ml.png'),
(48, 'https://res.cloudinary.com/beleza-na-web/image/upload/w_1500,f_auto,fl_progressive,q_auto:eco,w_800/v1/imagens/product/75412/1ce8fbc8-e5af-4d98-90a7-6e3b0fd4d6cd-nivea-natural-oil-sabonete-liquido-200ml.png');



INSERT INTO estoque (cod_produto, tipo_movimentacao, quantidade)
VALUES
(1, 'ENTRADA', 34),
(2, 'ENTRADA', 78),
(3, 'ENTRADA', 52),
(4, 'ENTRADA', 23),
(5, 'ENTRADA', 67),
(6, 'ENTRADA', 42),
(7, 'ENTRADA', 59),
(8, 'ENTRADA', 96),
(9, 'ENTRADA', 38),
(10, 'ENTRADA', 71),
(11, 'ENTRADA', 45),
(12, 'ENTRADA', 29),
(13, 'ENTRADA', 87),
(14, 'ENTRADA', 65),
(15, 'ENTRADA', 53),
(16, 'ENTRADA', 76),
(17, 'ENTRADA', 39),
(18, 'ENTRADA', 88),
(19, 'ENTRADA', 47),
(20, 'ENTRADA', 61),
(21, 'ENTRADA', 25),
(22, 'ENTRADA', 58),
(23, 'ENTRADA', 32),
(24, 'ENTRADA', 92),
(25, 'ENTRADA', 48),
(26, 'ENTRADA', 94),
(27, 'ENTRADA', 73),
(28, 'ENTRADA', 56),
(29, 'ENTRADA', 27),
(30, 'ENTRADA', 99),
(31, 'ENTRADA', 43),
(32, 'ENTRADA', 19),
(33, 'ENTRADA', 85),
(34, 'ENTRADA', 90),
(35, 'ENTRADA', 66),
(36, 'ENTRADA', 21),
(37, 'ENTRADA', 64),
(38, 'ENTRADA', 70),
(39, 'ENTRADA', 31),
(40, 'ENTRADA', 91),
(41, 'ENTRADA', 36),
(42, 'ENTRADA', 84),
(43, 'ENTRADA', 97),
(44, 'ENTRADA', 16),
(45, 'ENTRADA', 55),
(46, 'ENTRADA', 62),
(47, 'ENTRADA', 17),
(48, 'ENTRADA', 69);


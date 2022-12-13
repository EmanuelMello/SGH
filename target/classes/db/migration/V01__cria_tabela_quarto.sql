CREATE TABLE public.quarto
(
    codigo bigserial NOT NULL,
    numero text,
    descricao text,
    valor real,
    status text DEFAULT 'ATIVO',
    statusQuarto text DEFAULT 'DISPONIVEL',
    PRIMARY KEY (codigo)
);
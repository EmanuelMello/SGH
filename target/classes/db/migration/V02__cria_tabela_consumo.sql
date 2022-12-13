CREATE TABLE public.consumo
(
    codigo bigserial NOT NULL,
    quantidade int,
    descricao text,
    valorunitario real,
    status text DEFAULT 'ATIVO',
    PRIMARY KEY (codigo)
);
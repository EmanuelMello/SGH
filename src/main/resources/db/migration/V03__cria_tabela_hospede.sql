CREATE TABLE public.hospede
(
    codigo bigserial NOT NULL,
    nome text,
    cpf text,
    rg text,
    telefone text,
    status text DEFAULT 'ATIVO',
    PRIMARY KEY (codigo)
);
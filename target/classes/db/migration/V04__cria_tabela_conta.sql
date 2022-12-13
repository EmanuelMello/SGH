CREATE TABLE public.conta
(
    codigo bigserial NOT NULL,
    valortotal real,
    codigo_consumo integer,
    status text DEFAULT 'ATIVO',
    PRIMARY KEY (codigo)
);

ALTER TABLE public.conta
    ADD FOREIGN KEY (codigo_consumo)
    REFERENCES public.consumo (codigo)
    NOT VALID;
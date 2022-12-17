CREATE TABLE public.checkout
(
    codigo serial NOT NULL,
    valortotal real,
    codigo_checkin integer,
    status text DEFAULT 'ATIVO',
    PRIMARY KEY (codigo)
);

ALTER TABLE public.checkout
    ADD FOREIGN KEY (codigo_checkin)
    REFERENCES public.checkin(codigo)
    NOT VALID;
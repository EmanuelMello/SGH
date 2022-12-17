CREATE TABLE public.checkin
(
    codigo serial NOT NULL,
    dataCheckIN date,
    dataCheckOut date,
    codigo_hospede integer,
    codigo_quarto integer,
    status text DEFAULT 'ATIVO',
    statusPago text DEFAULT 'NAO PAGO',
    PRIMARY KEY (codigo)
);

ALTER TABLE public.checkin
    ADD FOREIGN KEY (codigo_hospede)
    REFERENCES public.hospede(codigo)
    NOT VALID;


ALTER TABLE public.checkin
    ADD FOREIGN KEY (codigo_quarto)
    REFERENCES public.quarto (codigo)
    NOT VALID;
    
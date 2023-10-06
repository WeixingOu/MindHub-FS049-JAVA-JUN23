--
-- PostgreSQL database dump
--

-- Dumped from database version 12.3
-- Dumped by pg_dump version 12.3

-- Started on 2020-07-20 17:46:28

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2861 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 208 (class 1255 OID 16881)
-- Name: f_tienda_antiguedad(date); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.f_tienda_antiguedad(fecha_creacion date) RETURNS integer
    LANGUAGE plpgsql IMMUTABLE
    AS $$BEGIN
    RETURN extract( year FROM CURRENT_DATE )
           - extract( year FROM fecha_creacion );
END
$$;


ALTER FUNCTION public.f_tienda_antiguedad(fecha_creacion date) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 204 (class 1259 OID 16871)
-- Name: detalles_juego; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detalles_juego (
    nombre character varying(20) NOT NULL,
    serial character varying(20) NOT NULL,
    numero_jugadores integer NOT NULL,
    edad_minima integer NOT NULL
);


ALTER TABLE public.detalles_juego OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16856)
-- Name: genero_juego; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.genero_juego (
    nombre character varying(20) NOT NULL,
    descripcion character varying(50) NOT NULL
);


ALTER TABLE public.genero_juego OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16861)
-- Name: juego; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.juego (
    nombre character varying(20) NOT NULL,
    descripcion character varying(50) NOT NULL,
    nombre_genero_juego character varying(20) NOT NULL
);


ALTER TABLE public.juego OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16901)
-- Name: juego_tienda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.juego_tienda (
    nombre character varying(20) NOT NULL,
    rut character varying(20) NOT NULL
);


ALTER TABLE public.juego_tienda OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16882)
-- Name: tienda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tienda (
    rut character varying(20) NOT NULL,
    nombre character varying(20) NOT NULL,
    fecha_creacion date NOT NULL,
    antiguedad integer GENERATED ALWAYS AS (public.f_tienda_antiguedad(fecha_creacion)) STORED
);


ALTER TABLE public.tienda OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16891)
-- Name: tienda_telefono; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tienda_telefono (
    rut character varying(20) NOT NULL,
    telefono character varying(15) NOT NULL
);


ALTER TABLE public.tienda_telefono OWNER TO postgres;

--
-- TOC entry 2852 (class 0 OID 16871)
-- Dependencies: 204
-- Data for Name: detalles_juego; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.detalles_juego (nombre, serial, numero_jugadores, edad_minima) FROM stdin;
Tomb Raider	123456	1	8
Fornite	123456	100	8
Halo	225548	1	12
Mario Bross	233598	2	5
\.


--
-- TOC entry 2850 (class 0 OID 16856)
-- Dependencies: 202
-- Data for Name: genero_juego; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.genero_juego (nombre, descripcion) FROM stdin;
Aventura	Exploración y puzzles
FPS	Disparos en primera persona
Plataforma	Avanzar saltando en distintas plataformas
RPG	Juego de roleo
\.


--
-- TOC entry 2851 (class 0 OID 16861)
-- Dependencies: 203
-- Data for Name: juego; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.juego (nombre, descripcion, nombre_genero_juego) FROM stdin;
Tomb Raider	Una mujer explora tumbas buscando tesoros	Aventura
Fornite	Último jugador con vida gana	FPS
Halo	Soldado de elite salva a la humanidad	FPS
Mario Bross	Un plomero debe rescatar a una princesa	Plataforma
Pokémon	Atrapar y entrenar animales fantasticos	Aventura
\.


--
-- TOC entry 2855 (class 0 OID 16901)
-- Dependencies: 207
-- Data for Name: juego_tienda; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.juego_tienda (nombre, rut) FROM stdin;
Fornite	RUT12341
Mario Bross	RUT12341
Tomb Raider	RUT12343
Halo	RUT12343
Fornite	RUT12343
Halo	RUT12344
Fornite	RUT12344
\.


--
-- TOC entry 2853 (class 0 OID 16882)
-- Dependencies: 205
-- Data for Name: tienda; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tienda (rut, nombre, fecha_creacion) FROM stdin;
RUT12342	MercaBato	1995-10-05
RUT12343	La ponderosa	2008-10-06
RUT12344	Rokutx	2015-05-04
RUT12341	Las tres Marías	2000-05-20
\.


--
-- TOC entry 2854 (class 0 OID 16891)
-- Dependencies: 206
-- Data for Name: tienda_telefono; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tienda_telefono (rut, telefono) FROM stdin;
RUT12343	119995566
RUT12343	112223333
RUT12343	153334488
\.


--
-- TOC entry 2712 (class 2606 OID 16875)
-- Name: detalles_juego detalles_juego_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalles_juego
    ADD CONSTRAINT detalles_juego_pkey PRIMARY KEY (nombre, serial);


--
-- TOC entry 2708 (class 2606 OID 16860)
-- Name: genero_juego genero_juego_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genero_juego
    ADD CONSTRAINT genero_juego_pkey PRIMARY KEY (nombre);


--
-- TOC entry 2710 (class 2606 OID 16865)
-- Name: juego juego_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.juego
    ADD CONSTRAINT juego_pkey PRIMARY KEY (nombre);


--
-- TOC entry 2718 (class 2606 OID 16905)
-- Name: juego_tienda juego_tienda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.juego_tienda
    ADD CONSTRAINT juego_tienda_pkey PRIMARY KEY (nombre, rut);


--
-- TOC entry 2714 (class 2606 OID 16887)
-- Name: tienda tienda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tienda
    ADD CONSTRAINT tienda_pkey PRIMARY KEY (rut);


--
-- TOC entry 2716 (class 2606 OID 16895)
-- Name: tienda_telefono tienda_telefono_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tienda_telefono
    ADD CONSTRAINT tienda_telefono_pkey PRIMARY KEY (rut, telefono);


--
-- TOC entry 2720 (class 2606 OID 16876)
-- Name: detalles_juego detalles_juego_nombre_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalles_juego
    ADD CONSTRAINT detalles_juego_nombre_fkey FOREIGN KEY (nombre) REFERENCES public.juego(nombre);


--
-- TOC entry 2719 (class 2606 OID 16866)
-- Name: juego juego_nombre_genero_juego_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.juego
    ADD CONSTRAINT juego_nombre_genero_juego_fkey FOREIGN KEY (nombre_genero_juego) REFERENCES public.genero_juego(nombre);


--
-- TOC entry 2722 (class 2606 OID 16906)
-- Name: juego_tienda juego_tienda_nombre_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.juego_tienda
    ADD CONSTRAINT juego_tienda_nombre_fkey FOREIGN KEY (nombre) REFERENCES public.juego(nombre);


--
-- TOC entry 2723 (class 2606 OID 16911)
-- Name: juego_tienda juego_tienda_rut_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.juego_tienda
    ADD CONSTRAINT juego_tienda_rut_fkey FOREIGN KEY (rut) REFERENCES public.tienda(rut);


--
-- TOC entry 2721 (class 2606 OID 16896)
-- Name: tienda_telefono tienda_telefono_rut_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tienda_telefono
    ADD CONSTRAINT tienda_telefono_rut_fkey FOREIGN KEY (rut) REFERENCES public.tienda(rut);


-- Completed on 2020-07-20 17:46:28

--
-- PostgreSQL database dump complete
--


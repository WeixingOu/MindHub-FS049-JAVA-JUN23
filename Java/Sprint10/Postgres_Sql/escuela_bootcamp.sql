--
-- PostgreSQL database dump
--

-- Dumped from database version 16rc1
-- Dumped by pg_dump version 16rc1

-- Started on 2023-09-08 11:42:08

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 16532)
-- Name: curso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.curso (
    id_curso integer NOT NULL,
    nombre_curso character varying(255),
    fecha_inicio date,
    dni_mentor integer
);


ALTER TABLE public.curso OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16542)
-- Name: estudiante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estudiante (
    dni integer NOT NULL,
    nombre character varying(255),
    apellido character varying(255)
);


ALTER TABLE public.estudiante OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16549)
-- Name: estudiante_curso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estudiante_curso (
    id_estudiante_curso integer NOT NULL,
    dni_estudiante integer,
    id_curso integer
);


ALTER TABLE public.estudiante_curso OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16525)
-- Name: mentor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mentor (
    dni integer NOT NULL,
    nombre character varying(255),
    apellido character varying(255),
    telefono character varying(20)
);


ALTER TABLE public.mentor OWNER TO postgres;

--
-- TOC entry 4800 (class 0 OID 16532)
-- Dependencies: 216
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.curso (id_curso, nombre_curso, fecha_inicio, dni_mentor) FROM stdin;
\.


--
-- TOC entry 4801 (class 0 OID 16542)
-- Dependencies: 217
-- Data for Name: estudiante; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estudiante (dni, nombre, apellido) FROM stdin;
\.


--
-- TOC entry 4802 (class 0 OID 16549)
-- Dependencies: 218
-- Data for Name: estudiante_curso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estudiante_curso (id_estudiante_curso, dni_estudiante, id_curso) FROM stdin;
\.


--
-- TOC entry 4799 (class 0 OID 16525)
-- Dependencies: 215
-- Data for Name: mentor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mentor (dni, nombre, apellido, telefono) FROM stdin;
\.


--
-- TOC entry 4648 (class 2606 OID 16536)
-- Name: curso curso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.curso
    ADD CONSTRAINT curso_pkey PRIMARY KEY (id_curso);


--
-- TOC entry 4652 (class 2606 OID 16553)
-- Name: estudiante_curso estudiante_curso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante_curso
    ADD CONSTRAINT estudiante_curso_pkey PRIMARY KEY (id_estudiante_curso);


--
-- TOC entry 4650 (class 2606 OID 16548)
-- Name: estudiante estudiante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_pkey PRIMARY KEY (dni);


--
-- TOC entry 4646 (class 2606 OID 16531)
-- Name: mentor mentor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mentor
    ADD CONSTRAINT mentor_pkey PRIMARY KEY (dni);


--
-- TOC entry 4653 (class 2606 OID 16537)
-- Name: curso curso_dni_mentor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.curso
    ADD CONSTRAINT curso_dni_mentor_fkey FOREIGN KEY (dni_mentor) REFERENCES public.mentor(dni);


--
-- TOC entry 4654 (class 2606 OID 16554)
-- Name: estudiante_curso estudiante_curso_dni_estudiante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante_curso
    ADD CONSTRAINT estudiante_curso_dni_estudiante_fkey FOREIGN KEY (dni_estudiante) REFERENCES public.estudiante(dni);


--
-- TOC entry 4655 (class 2606 OID 16559)
-- Name: estudiante_curso estudiante_curso_id_curso_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estudiante_curso
    ADD CONSTRAINT estudiante_curso_id_curso_fkey FOREIGN KEY (id_curso) REFERENCES public.curso(id_curso);


-- Completed on 2023-09-08 11:42:08

--
-- PostgreSQL database dump complete
--


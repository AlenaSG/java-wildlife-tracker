--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.3
-- Dumped by pg_dump version 9.6.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animals; Type: TABLE; Schema: public; Owner: alenagolovina
--

CREATE TABLE animals (
    id integer NOT NULL,
    name character varying,
    type character varying,
    health character varying,
    age character varying
);


ALTER TABLE animals OWNER TO alenagolovina;

--
-- Name: animals_id_seq; Type: SEQUENCE; Schema: public; Owner: alenagolovina
--

CREATE SEQUENCE animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE animals_id_seq OWNER TO alenagolovina;

--
-- Name: animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alenagolovina
--

ALTER SEQUENCE animals_id_seq OWNED BY animals.id;


--
-- Name: sightings; Type: TABLE; Schema: public; Owner: alenagolovina
--

CREATE TABLE sightings (
    id integer NOT NULL,
    animal_id integer,
    location character varying,
    ranger_name character varying,
    timeseen timestamp without time zone
);


ALTER TABLE sightings OWNER TO alenagolovina;

--
-- Name: sightings_id_seq; Type: SEQUENCE; Schema: public; Owner: alenagolovina
--

CREATE SEQUENCE sightings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sightings_id_seq OWNER TO alenagolovina;

--
-- Name: sightings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alenagolovina
--

ALTER SEQUENCE sightings_id_seq OWNED BY sightings.id;


--
-- Name: animals id; Type: DEFAULT; Schema: public; Owner: alenagolovina
--

ALTER TABLE ONLY animals ALTER COLUMN id SET DEFAULT nextval('animals_id_seq'::regclass);


--
-- Name: sightings id; Type: DEFAULT; Schema: public; Owner: alenagolovina
--

ALTER TABLE ONLY sightings ALTER COLUMN id SET DEFAULT nextval('sightings_id_seq'::regclass);


--
-- Data for Name: animals; Type: TABLE DATA; Schema: public; Owner: alenagolovina
--

COPY animals (id, name, type, health, age) FROM stdin;
1	Fox	nonendangered	\N	\N
2	Wolf	endangered	\N	\N
3	fox	nonendangered	\N	\N
4	fox	nonendangered	\N	\N
5	bear	endangered	\N	\N
6	Badger	endangered	\N	\N
7	bunny	nonendangered	\N	\N
8	Wale	endangered	\N	\N
9	Wale3	endangered	ok	\N
10	Wale4	endangered	ill	newborn
\.


--
-- Name: animals_id_seq; Type: SEQUENCE SET; Schema: public; Owner: alenagolovina
--

SELECT pg_catalog.setval('animals_id_seq', 10, true);


--
-- Data for Name: sightings; Type: TABLE DATA; Schema: public; Owner: alenagolovina
--

COPY sightings (id, animal_id, location, ranger_name, timeseen) FROM stdin;
1	2	\N	Alena	2017-07-23 15:42:49.136023
2	2	\N		2017-07-23 15:53:19.724715
3	5	\N	Alice	2017-07-23 15:53:59.932874
4	4	\N	Alice	2017-07-23 16:14:22.590636
5	8	Site A	Andrey	2017-07-23 16:38:11.80554
6	9	A	A	2017-07-23 16:56:11.11809
7	4	a	a	2017-07-23 16:59:42.854737
8	10	s	s	2017-07-23 17:42:24.301969
9	10	d	d	2017-07-23 17:42:41.820322
\.


--
-- Name: sightings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: alenagolovina
--

SELECT pg_catalog.setval('sightings_id_seq', 9, true);


--
-- Name: animals animals_pkey; Type: CONSTRAINT; Schema: public; Owner: alenagolovina
--

ALTER TABLE ONLY animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);


--
-- Name: sightings sightings_pkey; Type: CONSTRAINT; Schema: public; Owner: alenagolovina
--

ALTER TABLE ONLY sightings
    ADD CONSTRAINT sightings_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--


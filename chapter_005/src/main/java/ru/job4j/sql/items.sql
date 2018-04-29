--
-- PostgreSQL database dump
--

-- Dumped from database version 10.3
-- Dumped by pg_dump version 10.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
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


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: attaches; Type: TABLE; Schema: public; Owner: SHMUR
--

CREATE TABLE public.attaches (
    file_id integer NOT NULL,
    item_id integer,
    file_path text
);


ALTER TABLE public.attaches OWNER TO "SHMUR";

--
-- Name: attaches_file_id_seq; Type: SEQUENCE; Schema: public; Owner: SHMUR
--

CREATE SEQUENCE public.attaches_file_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.attaches_file_id_seq OWNER TO "SHMUR";

--
-- Name: attaches_file_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SHMUR
--

ALTER SEQUENCE public.attaches_file_id_seq OWNED BY public.attaches.file_id;


--
-- Name: categories; Type: TABLE; Schema: public; Owner: SHMUR
--

CREATE TABLE public.categories (
    category_name text NOT NULL,
    description text
);


ALTER TABLE public.categories OWNER TO "SHMUR";

--
-- Name: comments; Type: TABLE; Schema: public; Owner: SHMUR
--

CREATE TABLE public.comments (
    comment_id integer NOT NULL,
    item_id integer,
    body text
);


ALTER TABLE public.comments OWNER TO "SHMUR";

--
-- Name: comments_comment_id_seq; Type: SEQUENCE; Schema: public; Owner: SHMUR
--

CREATE SEQUENCE public.comments_comment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comments_comment_id_seq OWNER TO "SHMUR";

--
-- Name: comments_comment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SHMUR
--

ALTER SEQUENCE public.comments_comment_id_seq OWNED BY public.comments.comment_id;


--
-- Name: items; Type: TABLE; Schema: public; Owner: SHMUR
--

CREATE TABLE public.items (
    item_id integer NOT NULL,
    user_id integer,
    state_name text,
    category_name text,
    description text
);


ALTER TABLE public.items OWNER TO "SHMUR";

--
-- Name: items_item_id_seq; Type: SEQUENCE; Schema: public; Owner: SHMUR
--

CREATE SEQUENCE public.items_item_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.items_item_id_seq OWNER TO "SHMUR";

--
-- Name: items_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SHMUR
--

ALTER SEQUENCE public.items_item_id_seq OWNED BY public.items.item_id;


--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    role_name character varying(5) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- Name: roles_rules; Type: TABLE; Schema: public; Owner: SHMUR
--

CREATE TABLE public.roles_rules (
    role_name character varying(5) NOT NULL,
    rule_id integer NOT NULL
);


ALTER TABLE public.roles_rules OWNER TO "SHMUR";

--
-- Name: rules; Type: TABLE; Schema: public; Owner: SHMUR
--

CREATE TABLE public.rules (
    rule_id integer NOT NULL,
    description text
);


ALTER TABLE public.rules OWNER TO "SHMUR";

--
-- Name: rules_rule_id_seq; Type: SEQUENCE; Schema: public; Owner: SHMUR
--

CREATE SEQUENCE public.rules_rule_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rules_rule_id_seq OWNER TO "SHMUR";

--
-- Name: rules_rule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SHMUR
--

ALTER SEQUENCE public.rules_rule_id_seq OWNED BY public.rules.rule_id;


--
-- Name: states; Type: TABLE; Schema: public; Owner: SHMUR
--

CREATE TABLE public.states (
    state_name text NOT NULL,
    description text
);


ALTER TABLE public.states OWNER TO "SHMUR";

--
-- Name: users; Type: TABLE; Schema: public; Owner: SHMUR
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    login character varying(20) NOT NULL,
    pasword character varying(20),
    role_name character varying(5)
);


ALTER TABLE public.users OWNER TO "SHMUR";

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: SHMUR
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_user_id_seq OWNER TO "SHMUR";

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: SHMUR
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- Name: attaches file_id; Type: DEFAULT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.attaches ALTER COLUMN file_id SET DEFAULT nextval('public.attaches_file_id_seq'::regclass);


--
-- Name: comments comment_id; Type: DEFAULT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.comments ALTER COLUMN comment_id SET DEFAULT nextval('public.comments_comment_id_seq'::regclass);


--
-- Name: items item_id; Type: DEFAULT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.items ALTER COLUMN item_id SET DEFAULT nextval('public.items_item_id_seq'::regclass);


--
-- Name: rules rule_id; Type: DEFAULT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.rules ALTER COLUMN rule_id SET DEFAULT nextval('public.rules_rule_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- Data for Name: attaches; Type: TABLE DATA; Schema: public; Owner: SHMUR
--

COPY public.attaches (file_id, item_id, file_path) FROM stdin;
\.


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: SHMUR
--

COPY public.categories (category_name, description) FROM stdin;
\.


--
-- Data for Name: comments; Type: TABLE DATA; Schema: public; Owner: SHMUR
--

COPY public.comments (comment_id, item_id, body) FROM stdin;
\.


--
-- Data for Name: items; Type: TABLE DATA; Schema: public; Owner: SHMUR
--

COPY public.items (item_id, user_id, state_name, category_name, description) FROM stdin;
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (role_name) FROM stdin;
\.


--
-- Data for Name: roles_rules; Type: TABLE DATA; Schema: public; Owner: SHMUR
--

COPY public.roles_rules (role_name, rule_id) FROM stdin;
\.


--
-- Data for Name: rules; Type: TABLE DATA; Schema: public; Owner: SHMUR
--

COPY public.rules (rule_id, description) FROM stdin;
\.


--
-- Data for Name: states; Type: TABLE DATA; Schema: public; Owner: SHMUR
--

COPY public.states (state_name, description) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: SHMUR
--

COPY public.users (user_id, login, pasword, role_name) FROM stdin;
\.


--
-- Name: attaches_file_id_seq; Type: SEQUENCE SET; Schema: public; Owner: SHMUR
--

SELECT pg_catalog.setval('public.attaches_file_id_seq', 1, false);


--
-- Name: comments_comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: SHMUR
--

SELECT pg_catalog.setval('public.comments_comment_id_seq', 1, false);


--
-- Name: items_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: SHMUR
--

SELECT pg_catalog.setval('public.items_item_id_seq', 1, false);


--
-- Name: rules_rule_id_seq; Type: SEQUENCE SET; Schema: public; Owner: SHMUR
--

SELECT pg_catalog.setval('public.rules_rule_id_seq', 1, false);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: SHMUR
--

SELECT pg_catalog.setval('public.users_user_id_seq', 1, false);


--
-- Name: attaches attaches_pkey; Type: CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.attaches
    ADD CONSTRAINT attaches_pkey PRIMARY KEY (file_id);


--
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (category_name);


--
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (comment_id);


--
-- Name: items items_pkey; Type: CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_pkey PRIMARY KEY (item_id);


--
-- Name: items items_user_id_key; Type: CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_user_id_key UNIQUE (user_id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role_name);


--
-- Name: roles_rules roles_rules_pkey; Type: CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.roles_rules
    ADD CONSTRAINT roles_rules_pkey PRIMARY KEY (role_name, rule_id);


--
-- Name: rules rules_pkey; Type: CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.rules
    ADD CONSTRAINT rules_pkey PRIMARY KEY (rule_id);


--
-- Name: states states_pkey; Type: CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.states
    ADD CONSTRAINT states_pkey PRIMARY KEY (state_name);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: attaches attaches_item_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.attaches
    ADD CONSTRAINT attaches_item_id_fkey FOREIGN KEY (item_id) REFERENCES public.items(item_id);


--
-- Name: comments comments_item_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_item_id_fkey FOREIGN KEY (item_id) REFERENCES public.items(item_id);


--
-- Name: items items_category_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_category_name_fkey FOREIGN KEY (category_name) REFERENCES public.categories(category_name);


--
-- Name: items items_state_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_state_name_fkey FOREIGN KEY (state_name) REFERENCES public.states(state_name);


--
-- Name: items items_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: roles_rules roles_rules_role_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.roles_rules
    ADD CONSTRAINT roles_rules_role_name_fkey FOREIGN KEY (role_name) REFERENCES public.roles(role_name);


--
-- Name: roles_rules roles_rules_rule_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.roles_rules
    ADD CONSTRAINT roles_rules_rule_id_fkey FOREIGN KEY (rule_id) REFERENCES public.rules(rule_id);


--
-- Name: users users_role_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: SHMUR
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_role_name_fkey FOREIGN KEY (role_name) REFERENCES public.roles(role_name);


--
-- PostgreSQL database dump complete
--


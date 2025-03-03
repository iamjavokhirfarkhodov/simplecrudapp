PGDMP  (                    |         	   education    16.1    16.1 "               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                        0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            !           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            "           1262    17981 	   education    DATABASE     �   CREATE DATABASE education WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE education;
                postgres    false                        3079    18063 	   uuid-ossp 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;
    DROP EXTENSION "uuid-ossp";
                   false            #           0    0    EXTENSION "uuid-ossp"    COMMENT     W   COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';
                        false    2            �            1259    18003    faculty    TABLE     d   CREATE TABLE public.faculty (
    id smallint NOT NULL,
    name character varying(100) NOT NULL
);
    DROP TABLE public.faculty;
       public         heap    postgres    false            �            1259    18002    faculty_id_seq    SEQUENCE     �   CREATE SEQUENCE public.faculty_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.faculty_id_seq;
       public          postgres    false    217            $           0    0    faculty_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.faculty_id_seq OWNED BY public.faculty.id;
          public          postgres    false    216            �            1259    18048    roles    TABLE     ]   CREATE TABLE public.roles (
    id smallint NOT NULL,
    name character varying NOT NULL
);
    DROP TABLE public.roles;
       public         heap    postgres    false            �            1259    18047    roles_id_seq    SEQUENCE     �   CREATE SEQUENCE public.roles_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    221            %           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    220            �            1259    18090    student    TABLE     Q  CREATE TABLE public.student (
    user_id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    firstname character varying(40) NOT NULL,
    lastname character varying(40) NOT NULL,
    birth_date date NOT NULL,
    faculty smallint NOT NULL,
    year_of_study smallint DEFAULT 1 NOT NULL,
    address character varying(100) NOT NULL
);
    DROP TABLE public.student;
       public         heap    postgres    false    2            �            1259    18034 
   user_roles    TABLE     \   CREATE TABLE public.user_roles (
    user_id uuid NOT NULL,
    role_id integer NOT NULL
);
    DROP TABLE public.user_roles;
       public         heap    postgres    false            �            1259    18014    users    TABLE     �  CREATE TABLE public.users (
    id uuid DEFAULT public.uuid_generate_v4() NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL,
    is_account_non_expired boolean DEFAULT true NOT NULL,
    is_account_non_locked boolean DEFAULT true NOT NULL,
    is_credentials_non_expired boolean DEFAULT true NOT NULL,
    is_enabled boolean DEFAULT true NOT NULL,
    created_at date DEFAULT CURRENT_DATE NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false    2            l           2604    18006 
   faculty id    DEFAULT     h   ALTER TABLE ONLY public.faculty ALTER COLUMN id SET DEFAULT nextval('public.faculty_id_seq'::regclass);
 9   ALTER TABLE public.faculty ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            s           2604    18051    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    221    221                      0    18003    faculty 
   TABLE DATA           +   COPY public.faculty (id, name) FROM stdin;
    public          postgres    false    217   Z%                 0    18048    roles 
   TABLE DATA           )   COPY public.roles (id, name) FROM stdin;
    public          postgres    false    221   �%                 0    18090    student 
   TABLE DATA           l   COPY public.student (user_id, firstname, lastname, birth_date, faculty, year_of_study, address) FROM stdin;
    public          postgres    false    222   L&                 0    18034 
   user_roles 
   TABLE DATA           6   COPY public.user_roles (user_id, role_id) FROM stdin;
    public          postgres    false    219   �(                 0    18014    users 
   TABLE DATA           �   COPY public.users (id, username, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, created_at) FROM stdin;
    public          postgres    false    218   �)       &           0    0    faculty_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.faculty_id_seq', 14, true);
          public          postgres    false    216            '           0    0    roles_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.roles_id_seq', 12, true);
          public          postgres    false    220            w           2606    18008    faculty faculty_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.faculty
    ADD CONSTRAINT faculty_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.faculty DROP CONSTRAINT faculty_pkey;
       public            postgres    false    217                       2606    18057    roles role_name_unique 
   CONSTRAINT     Q   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT role_name_unique UNIQUE (name);
 @   ALTER TABLE ONLY public.roles DROP CONSTRAINT role_name_unique;
       public            postgres    false    221            �           2606    18055    roles role_pk 
   CONSTRAINT     K   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT role_pk PRIMARY KEY (id);
 7   ALTER TABLE ONLY public.roles DROP CONSTRAINT role_pk;
       public            postgres    false    221            �           2606    18096    student student_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (user_id);
 >   ALTER TABLE ONLY public.student DROP CONSTRAINT student_pkey;
       public            postgres    false    222            }           2606    18106 !   user_roles user_id_role_id_unique 
   CONSTRAINT     h   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_id_role_id_unique UNIQUE (user_id, role_id);
 K   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT user_id_role_id_unique;
       public            postgres    false    219    219            y           2606    18018    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    218            {           2606    18080    users users_username_unique 
   CONSTRAINT     Z   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_unique UNIQUE (username);
 E   ALTER TABLE ONLY public.users DROP CONSTRAINT users_username_unique;
       public            postgres    false    218            �           2606    18058    user_roles role_id_fk    FK CONSTRAINT     ~   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT role_id_fk FOREIGN KEY (role_id) REFERENCES public.roles(id) NOT VALID;
 ?   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT role_id_fk;
       public          postgres    false    221    219    4737            �           2606    18037    user_roles user_id_fk    FK CONSTRAINT     t   ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id);
 ?   ALTER TABLE ONLY public.user_roles DROP CONSTRAINT user_id_fk;
       public          postgres    false    4729    219    218            �           2606    18097    student user_id_student_id    FK CONSTRAINT     y   ALTER TABLE ONLY public.student
    ADD CONSTRAINT user_id_student_id FOREIGN KEY (user_id) REFERENCES public.users(id);
 D   ALTER TABLE ONLY public.student DROP CONSTRAINT user_id_student_id;
       public          postgres    false    222    218    4729               �   x��9�@��W�ƈ#%@���C�A���ˎ�0��g�>^�J]��T�耖:8ʋ+��s�'vt��Aof݃]�8Gq�}�X8aO'��3�����<�c���X�\,��Z�dh����x�*3�Y���P���pY�ä2�         F   x�3���q�wt����2�p�CB]\�B��!\�P��.�\h�kD�D$4�5���5�Ԑ+F��� #|�         A  x�=��n�0Ek�+���(�] )`a���QV�,�6����;�9�s�*���A	Ɓ��z�U��FcT�o��ͳ0JEP	LV8����i��e��c��B��c��+HIG�Ng�L���am�M�E<�r�C�:4#�|���'�6�4����T�C Ē!���m%5j�G䀫��Ӳ�ީNx�E46y�����̝[:o][R���n@�S�)h=Zq�ߜ�nmB�Aq��@-���ȯ˿i�Eۇ�X���eh1 8�	r�\�dM@[��u��}��:�{eI���fm��ֹ�"�	Gy��}�G��͈8Z�5�^v����LɡچQ�𸾉�w��A�U'%O�|{-��?n�10@SlZT�"Z6K�u��f[�M<�\��=���+.ǧ�MX��I�te���gs��ȍ�t�K�Gh�E]��yZ���������;S��D�J�EҼ�����F��
d��*%>Fܯ���m���ͭ����b�.9��[��,�:��U�g�2��G_UQ�S�5�>M�+��ݭ�b����3��q���}��e��lq��           x��ɑe!������ŗ���0�JdTe	�GU�B"*H1Aڜp�fPr�<?��'���z@�@H�;#v�>��?N�2���uZ��h��1����?�4��y�t��c�ഹ��^��*���F���e���ĵ����J�,��@�Y�XpZ]���K[�sI@�&Łc�nf���X�|n���zD]���z�7</���%��Y�Y6[q�=7�Tz�Ba=������)O�&{>��=q$(��i��jA��X+��6\�S`)	Ȭ�<���#S�%�ؿ����A�s�         �  x�m�Iw�H�u�;�a��9�A��7UT!����o��䜾��b���7p�8����`$�2�5Q����<����.���/�Y��Q�;a�!�nBz3����q$� V-2���������1�𥉉C$H8w�&����᠊��������C���j��{h�����A�u�E����UyV�<|��"�����>�������S�PF8��`14P#i_��Һ���q�6�z�����"/ݓr�z��o���S��r0���lo�Ӣ�>��	��hɭ��DԺfH� )f�M��4�a�g��n���#ԕ~oQ��4٠k7�L�e0�����F�����]p�X"�-T�"^7�6
((%&�H�~��F�}nR�V룖^�;�q[μn8����w�'o���]�#�Y�Za���є*1R J�_�1����ƅ�%��Y���ƽ��C�#��!��~�>�尿����w?��-�v�G=	���O����
X�5�X9�� ���'�ks���#���I�q�:�F�(-�n�U�:�l��h�X���w�-��S���3�*���#���^7P6��r)�&�4�ht�z�����@M��:h�Ɲ2�Rx�(�oTu���Y܏�3ߎ���L�)侳c+ �B���΀�ИK,?u��n��Iv�N%�C�>�u��6,7)�W$�&�M�)�Yu�:�b��[<�yc�M�E���&R$�������Y��F?�J�`�m�;)/���؞�#v���۲�_/Su��Kv���r���O��P��P�`\g�E���H
�Q����y���\�������k���s���U�jj���EF�W��ߢ���?7ݿ<��KM���eV��z�9Ԛ�c(7:���b��H!�����|L�.;e�\l3Jϋ���r��´;_����z�/����o���-     
insert into Curso (id, nome, area, tipo) values (1, 'Computacao', 'Exatas', 'GRADUACAO');
insert into Curso (id, nome, area, tipo) values (2, 'Fisica', 'Exatas', 'GRADUACAO');
insert into Curso (id, nome, area, tipo) values (3, 'Letras', 'Humanas', 'GRADUACAO');

insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (1, 'ANAISDECONGRESSO', 'Anais 01', 'Fulano');
insert into Anais (id, tipo, nome_Do_Congresso, ano_De_Publicacao, local) values (1, 'ARTIGO', 'Congresso 01', 2016 , 'Local 01');
insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (2, 'ANAISDECONGRESSO', 'Anais 02', 'Cicrano');
insert into Anais (id, tipo, nome_Do_Congresso, ano_De_Publicacao, local) values (2, 'POSTER', 'Congresso 02', 2017 , 'Local 02');
insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (3, 'ANAISDECONGRESSO', 'Anais 03', 'Beltrano');
insert into Anais (id, tipo, nome_Do_Congresso, ano_De_Publicacao, local) values (3, 'RESUMO', 'Congresso 03', 2018 , 'Local 03');

insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (4, 'JORNAL', 'Jornal 01', 'Fulano');
insert into Jornal (id, data, edicao) values (4, '22-03-16', 1);
insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (5, 'JORNAL', 'Jornal 02', 'Cicrano');
insert into Jornal (id, data, edicao) values (5, '22-04-17', 2);
insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (6, 'JORNAL', 'Jornal 03', 'Beltrano');
insert into Jornal (id, data, edicao) values (6, '22-05-18', 3);

insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (7, 'LIVRO', 'Livro 01', 'Fulano');
insert into Livro (id, isbn, editora, ano_De_Publicacao, edicao, numero_De_Paginas, area, tema) values (7, 6346287, 'Editora 01', 2016, 1, 222, 'Area 01', 'Fulano');
insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (8, 'LIVRO', 'Livro 02', 'Cicrano');
insert into Livro (id, isbn, editora, ano_De_Publicacao, edicao, numero_De_Paginas, area, tema) values (8, 7348227, 'Editora 02', 2017, 2, 333, 'Area 02', 'Cicrano');
insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (9, 'LIVRO', 'Livro 03', 'Beltrano');
insert into Livro (id, isbn, editora, ano_De_Publicacao, edicao, numero_De_Paginas, area, tema) values (9, 9578225, 'Editora 03', 2018, 3, 444, 'Area 03', 'Beltrano');

insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (10, 'MIDIAELETRONICA', 'Midia 01', 'Fulano');
insert into Midia_Eletronica (id, tipo, data_De_Gravacao) values (10, 'CD', '11-02-04');
insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (11, 'MIDIAELETRONICA', 'Midia 02', 'Cicrano');
insert into Midia_Eletronica (id, tipo, data_De_Gravacao) values (11, 'DVD', '15-12-14');
insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (12, 'MIDIAELETRONICA', 'Midia 03', 'Beltrano');
insert into Midia_Eletronica (id, tipo, data_De_Gravacao) values (12, 'CD', '18-10-24');

insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (13, 'REVISTA', 'Revista 01', 'Fulano');
insert into Revista (id, data_De_Publicacao, edicao, numero_De_Paginas) values (13, '12-06-16', 1, 222);
insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (14, 'REVISTA', 'Revista 02', 'Cicrano');
insert into Revista (id, data_De_Publicacao, edicao, numero_De_Paginas) values (14, '12-05-17', 1, 222);
insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (15, 'REVISTA', 'Revista 03', 'Beltrano');
insert into Revista (id, data_De_Publicacao, edicao, numero_De_Paginas) values (15, '12-02-18', 1, 222);


insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (16, 'TRABALHOSDECONCLUSAO', 'TCC 01', 'Fulano');
insert into Trabalho_De_Conclusao (id, orientadores, tipo, ano_De_Defesa, local) values (16, 'Orientador 01', 'MONOGRAFIA', 2015, 'Local 01');
insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (17, 'TRABALHOSDECONCLUSAO', 'TCC 02', 'Cicrano');
insert into Trabalho_De_Conclusao (id, orientadores, tipo, ano_De_Defesa, local) values (17, 'Orientador 02', 'TESE', 2016, 'Local 02');
insert into Item_Do_Acervo (id, item_tipo, titulo, autores) values (18, 'TRABALHOSDECONCLUSAO', 'TCC 03', 'Beltrano');
insert into Trabalho_De_Conclusao (id, orientadores, tipo, ano_De_Defesa, local) values (18, 'Orientador 03', 'DISSERTACAO', 2017, 'Local 03');


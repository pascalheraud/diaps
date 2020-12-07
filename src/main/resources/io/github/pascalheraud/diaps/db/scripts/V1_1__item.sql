create type diaps_item_category as enum  ('EM','EF','OTHER');
create type diaps_item_dysgroup as enum  ('PAGE','MALADRESSE','FORMES_ET_PROPORTIONS');

create table diaps_item (
	id bigserial PRIMARY KEY,
	number integer not null,
	label text not null,
	rate integer not null,
	category diaps_item_category not null,
	dysgroup diaps_item_dysgroup,
	dysrate integer,
	dysnumber integer
);

insert into diaps_item(number, label, rate, category) values (1, 'Surface enfantine',2,'EF');	
insert into diaps_item(number, label, rate, category) values (2, 'Dodue',1,'EF');
insert into diaps_item(number, label, rate, category) values (3, 'Absence de mouvement',2,'EF');
insert into diaps_item(number, label, rate, category) values (4, 'Grande',2,'EF');
insert into diaps_item(number, label, rate, category) values (5, 'm et n scolaire',2,'EF');
insert into diaps_item(number, label, rate, category) values (6, 't scolaire',2,'EF');
insert into diaps_item(number, label, rate, category) values (7, 'p scolaire',1,'EF');
insert into diaps_item(number, label, rate, category) values (8, 'a en deux morceaux',3,'EF');
insert into diaps_item(number, label, rate, category) values (9, 'd, g, q en deux morceaux',2,'EF');
insert into diaps_item(number, label, rate, category) values (10, 'Majuscules (maladroites)',3,'EF');
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (11, 'Points de soudure',3,'EF','MALADRESSE',13,2);
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (12, 'Collages',1,'EF','MALADRESSE',14, 1);
insert into diaps_item(number, label, rate, category) values (13, 'Espaces irreguliers entre les lignes',3,'EF');
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (14, 'Zones mal différenciées',2,'EF','MALADRESSE', 19, 1);


insert into diaps_item(number, label, rate, category) values (15, 'Bâtons descendants repris',3,'EM');
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (16, 'Lettres retouchées',3,'EM','MALADRESSE',9,2);
insert into diaps_item(number, label, rate, category,dysgroup, dysnumber, dysrate) values (17, 'Ensemble sale',3,'EM','PAGE',1, 1);
insert into diaps_item(number, label, rate, category) values (18, 'Arcage d, t, p, q',1,'EM');
insert into diaps_item(number, label, rate, category) values (19, 'Cabossages L rondes intérieures',3,'EM');
insert into diaps_item(number, label, rate, category) values (20, 'Mauvais galbes des boucles',2,'EM');
insert into diaps_item(number, label, rate, category) values (21, 'Tremblements',3,'EM');
insert into diaps_item(number, label, rate, category) values (22, 'Tracé vacillant',2,'EM');
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (23, 'Saccades',2,'EM','MALADRESSE',16,2);
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (24, 'Télescopages',2,'EM','MALADRESSE',15,3);
insert into diaps_item(number, label, rate, category,dysgroup, dysnumber, dysrate) values (25, 'Lignes cassées',2,'EM','PAGE',2,1);
insert into diaps_item(number, label, rate, category,dysgroup, dysnumber, dysrate) values (26, 'Lignes fluctuantes',1,'EM','PAGE',3,2);
insert into diaps_item(number, label, rate, category,dysgroup, dysnumber, dysrate) values (27, 'Lignes descendantes',1,'EM','PAGE',4,1);
insert into diaps_item(number, label, rate, category) values (28, 'Mots dansants sur la ligne',2,'EM');
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (29, 'Irrégularité de dimension',3,'EM','MALADRESSE',18, 2);
insert into diaps_item(number, label, rate, category) values (30, 'Irrégularité de direction',1,'EM');

insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (31, 'Mots serrés',1,'OTHER','PAGE',5,1);
insert into diaps_item(number, label, rate, category,dysgroup, dysnumber, dysrate) values (32, 'Espace entre mots irrégulier',1,'OTHER','PAGE',6,1);
insert into diaps_item(number, label, rate, category,dysgroup, dysnumber, dysrate) values (33, 'Marge insuffisante',1,'OTHER','PAGE',7,1);
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (34, 'Trait de mauvaise qualité',2,'OTHER','MALADRESSE',8,2);
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (35, 'Pochages',1,'OTHER','MALADRESSE',10,1);
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (36, 'Arcages m, n, u, i',1,'OTHER','MALADRESSE',11,1);
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (37, 'Angulation des arcades',1,'OTHER','MALADRESSE',12,1);

insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (38, 'Finales lancées',2,'OTHER','MALADRESSE',17,2);
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (39, 'Lettres atrophiées',2,'OTHER','MALADRESSE',20,2);

insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (40, 'Lettres trop structurées ou trop labiles',2,'OTHER','FORMES_ET_PROPORTIONS',21,2);
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (41, 'Mauvaises formes',1,'OTHER','FORMES_ET_PROPORTIONS',22,1);
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (42, 'Ecriture trop petite ou trop grande',2,'OTHER','FORMES_ET_PROPORTIONS',23,2);
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (43, 'Ecriture trop étalée ou trop étrécie',2,'OTHER','FORMES_ET_PROPORTIONS',24,1);
insert into diaps_item(number, label, rate, category, dysgroup, dysnumber, dysrate) values (44, 'Mauvaises proportions des zones',1,'OTHER','FORMES_ET_PROPORTIONS',25,2);



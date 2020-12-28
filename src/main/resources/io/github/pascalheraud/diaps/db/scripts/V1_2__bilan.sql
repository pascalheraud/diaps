
create table diaps_bilan(
	id bigserial PRIMARY KEY,
	personneid bigint references diaps_personne(id) not null,
	occurencedate date not null,
	writingspeednormal int,
	writingspeedmax int
);

create table diaps_bilan_item (
	id bigserial PRIMARY KEY,
	bilanid bigint references diaps_bilan(id) not null,
	itemid bigint references diaps_item(id) not null,
	note decimal(2,1) not null,
	description text
);

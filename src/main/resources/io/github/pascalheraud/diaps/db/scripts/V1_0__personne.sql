create type diaps_handed as enum ('LEFT','RIGHT','UNKNOWN');

create table diaps_personne (
	id bigserial PRIMARY KEY,
	dateofreport date not null,
	firstname text not null,
	lastname text not null,
	dateofbirth date not null,
	handed diaps_handed not null,
	handedInfo text not null,
	classroom text not null
);

CREATE CAST (varchar AS diaps_handed) WITH INOUT AS IMPLICIT;
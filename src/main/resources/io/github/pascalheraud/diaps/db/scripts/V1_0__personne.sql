create type diaps_handed as enum ('LEFT','RIGHT','UNKNOWN');
create type diaps_classroom as enum ('CP','CE1','CE2','CM1','CM2','SIXIEME','CINQUIEME','QUATRIEME','TROISIEME');
create type diaps_gender as enum ('MALE','FEMALE');

create table diaps_personne (
	id bigserial PRIMARY KEY,
	dateofreport date not null,
	firstname text not null,
	lastname text not null,
	dateofbirth date not null,
	gender diaps_gender not null,
	handed diaps_handed not null,
	handedInfo text not null,
	classroom diaps_classroom not null
);

CREATE CAST (varchar AS diaps_handed) WITH INOUT AS IMPLICIT;
CREATE CAST (varchar AS diaps_classroom) WITH INOUT AS IMPLICIT;
CREATE CAST (varchar AS diaps_gender) WITH INOUT AS IMPLICIT;
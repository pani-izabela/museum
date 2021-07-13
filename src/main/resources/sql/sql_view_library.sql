CREATE VIEW library_view as
select Au.email as email, Bo.title as title, Bo.status as status, Re.rental_time as rental_time, Hd.year as year, Hd.epoch as epoch
FROM APPUSER Au, BOOK Bo, RENTAL Re, HISTORY_DATA Hd, CLIENT Cl
WHERE Bo.id_historydata = Hd.id
and Re.id_book = Bo.id
and Re.id_client = Cl.id

CREATE VIEW library_view4 as
select Bo.title as title, Bo.status as status, Re.rental_time as rental_time, Hd.year as year, Hd.epoch as epoch
FROM BOOK Bo, RENTAL Re, HISTORY_DATA Hd, CLIENT Cl
WHERE Bo.id_historydata = Hd.id
and Re.id_book = Bo.id
and Re.id_client = Cl.id

//ten na razie najbliższy, brakuje client, bo jak ma null to nic nie wyświetla
CREATE VIEW library_view5 as
select Bo.title as title, Bo.status as status, Re.rental_time as rental_time, Hd.year as year, Hd.epoch as epoch
FROM BOOK Bo, RENTAL Re, HISTORY_DATA Hd
WHERE Bo.id_historydata = Hd.id
and Re.id_book = Bo.id


CREATE VIEW library_view8 as
select Bo.title as title, Bo.status as status, Re.rental_time as rental_time, Re.id_client, Hd.year as year, Hd.epoch as epoch
FROM BOOK Bo, RENTAL Re, HISTORY_DATA Hd
WHERE Bo.id_historydata = Hd.id
and Re.id_book = Bo.id

CREATE VIEW library_view9 as
select Bo.title as title, Bo.status as status, Re.rental_time as rental_time, Re.id_client, Hd.year as year, Hd.epoch as epoch, Au.email
FROM BOOK Bo, RENTAL Re, HISTORY_DATA Hd, CLIENT Cl, APPUSER Au
WHERE Bo.id_historydata = Hd.id
and Re.id_book = Bo.id
and Cl.id_appuser = Au.id
and Re.id_client = Cl.id

select id, nullif(id_client, NULL) as www from rental

CREATE VIEW library_view10 as
select Bo.title as title, Bo.status as status, Re.rental_time as rental_time, Hd.year as year, Hd.epoch as epoch, Au.email
FROM RENTAL Re, BOOK Bo, HISTORY_DATA Hd, CLIENT Cl, APPUSER Au
WHERE Bo.id_historydata = Hd.id
and Re.id_book = Bo.id
and Cl.id_appuser = Au.id
and Re.id_client = Cl.id
union
select Bo.title, Bo.status, Re.rental_time, Hd.year, Hd.epoch, null
FROM RENTAL Re, BOOK Bo, HISTORY_DATA Hd, CLIENT Cl, APPUSER Au
WHERE Bo.id_historydata = Hd.id
and Re.id_book = Bo.id
and Cl.id_appuser = Au.id

//SUKCES:
CREATE VIEW library_view11 as
select Bo.title as title, Bo.status as status, Re.rental_time as rental_time, Hd.year as year, Hd.epoch as epoch, Au.email
FROM RENTAL Re, BOOK Bo, HISTORY_DATA Hd, CLIENT Cl, APPUSER Au
WHERE Bo.id_historydata = Hd.id
and Re.id_book = Bo.id
and Cl.id_appuser = Au.id
and Re.id_client = Cl.id
union
select Bo.title, Bo.status, Re.rental_time, Hd.year, Hd.epoch, null
FROM RENTAL Re, BOOK Bo, HISTORY_DATA Hd, CLIENT Cl, APPUSER Au
WHERE Bo.id_historydata = Hd.id
and Re.id_book = Bo.id
and Cl.id_appuser = Au.id
and status=true
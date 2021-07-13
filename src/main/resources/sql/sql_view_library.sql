CREATE VIEW library_view as
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
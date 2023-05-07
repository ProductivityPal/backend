DO
'
DECLARE
BEGIN
    IF (NOT EXISTS (SELECT * FROM task))
    THEN
        insert into task(id, name) values (0, ''Buy new skirt'');
    END IF;

END;
'  LANGUAGE PLPGSQL;
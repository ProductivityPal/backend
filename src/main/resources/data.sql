DO
'
DECLARE
BEGIN
    IF (NOT EXISTS (SELECT * FROM task))
    THEN
        insert into task(id, user_id, category_id, name, priority, difficulty, deadline, time_estimate, completion_time, is_subtask, is_parent)
        values (1, 2, 3, ''Buy new skirt'', 5, 2, ''2019-12-12'', 2, 0, false, false);
    END IF;

    IF (NOT EXISTS(SELECT * FROM app_user))
        THEN
            insert into app_user(id, username, password, email, energy_level)
            values (1, ''Johnny123'', ''password'', ''johny123@gmail.com'', 2);
    END IF;

    IF (NOT EXISTS(SELECT * FROM category))
        THEN
            insert into category(id, user_id, name)
            values (1, 1, ''Work'');
        END IF;
END;
'  LANGUAGE PLPGSQL;
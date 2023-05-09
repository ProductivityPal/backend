DO
'
DECLARE
BEGIN
    IF (NOT EXISTS (SELECT * FROM task))
    THEN
        insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed)
        values (1, ''Buy new skirt'', ''Buy new skirt for prom'', 5, ''HARD'', ''DISLIKE'',  ''2019-12-12'', 2, 0, false, false, false);
    END IF;

    IF (NOT EXISTS(SELECT * FROM subtask))
        THEN
            insert into subtask(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, is_completed)
            values (1,''Find shop'', ''Online would be perfect'', 5, ''HARD'', ''DISLIKE'', ''2019-12-12'', 2,  false);
    END IF;


    IF (NOT EXISTS(SELECT * FROM app_user))
        THEN
            insert into app_user(id, username, password, email, energy_level)
            values (1, ''Johnny123'', ''password'', ''johny123@gmail.com'', ''LOW'');
    END IF;

    IF (NOT EXISTS(SELECT * FROM category))
        THEN
            insert into category(id, name)
            values (1, ''Work'');
        END IF;
END;
'  LANGUAGE PLPGSQL;
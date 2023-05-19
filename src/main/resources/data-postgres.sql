DO
'
DECLARE
BEGIN
    IF (NOT EXISTS (SELECT * FROM task))
    THEN
        insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id)
        values (1, ''Buy new skirt'', ''Buy new skirt for prom'', 5, ''HARD'', ''DISLIKE'',  ''2019-12-12'', 2, 0, false, true, false, null);
        insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id)
        values (2, ''Buy new shoes'', ''Buy new shoes for prom'', 5, ''HARD'', ''DISLIKE'', ''2019-12-12'', 2, 0, true, false, false, 1);
        insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id)
        values (3, ''Buy new clutch'', ''Buy new clutch for prom'', 5, ''HARD'', ''DISLIKE'', ''2019-12-12'', 3, 0, true, false, false, 1);
        insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id)
        values (4, ''Meeting with John'', ''Birthday'', 7, ''MEDIUM'', ''LOVE'', ''2019-11-11'', 4, 0, false, true, false, null);
        insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id)
        values (5, ''Learn for math test'', ''Geometry'', 9, ''HARD'', ''HATE'', ''2019-09-09'', 5, 0, false, false, true, null);
        insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id)
        values (6, ''Clean the kitchen'', ''Dishes, cupboards'', 6, ''MEDIUM'', ''NEUTRAL'', ''2019-08-08'', 2, 0, true, false, false, 1);
        insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id)
        values (7, ''End the task for client'', ''ID-765'', 10, ''EXTRA_HARD'', ''LIKE'', ''2019-07-07'', 2, 0, false, false, false, null);
    END IF;


    IF (NOT EXISTS(SELECT * FROM app_user))
        THEN
            insert into app_user(id, username, password, email, energy_level)
            values (1, ''Johnny123'', ''password'', ''johny123@gmail.com'', ''LOW'');
            insert into app_user(id, username, password, email, energy_level)
            values (2, ''Mike434'', ''password'', ''mikerr@gmail.com'', ''MEDIUM'');
            insert into app_user(id, username, password, email, energy_level)
            values (3, ''KateJ'', ''password'', ''kate123@gmail.com'', ''HIGH'');
            insert into app_user(id, username, password, email, energy_level)
            values (4, ''TimW12'', ''password'', ''timmit@gmail.com'', ''LOW'');
    END IF;

    IF (NOT EXISTS(SELECT * FROM category))
        THEN
            insert into category(id, name)
            values (1, ''Work'');
            insert into category(id, name)
            values (2, ''Uni'');
            insert into category(id, name)
            values (3, ''Home'');
            insert into category(id, name)
            values (4, ''Friends'');
            insert into category(id, name)
            values (1, ''Birthday'');
        END IF;
END;
'  LANGUAGE PLPGSQL;
DO
'
DECLARE
BEGIN
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
            insert into category(id, name, app_user_id)
            values (1, ''Work'', 1);
            insert into category(id, name, app_user_id)
            values (2, ''Uni'', 1);
            insert into category(id, name, app_user_id)
            values (3, ''Home'', 2);
            insert into category(id, name, app_user_id)
            values (4, ''Friends'', 4);
            insert into category(id, name, app_user_id)
            values (5, ''Birthday'', 2);
        END IF;

    IF (NOT EXISTS(SELECT * FROM task))
        THEN
            insert into task(name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category_id)
            values (''Buy new skirt'', ''Buy new skirt for prom'', 5, ''HARD'', ''DISLIKE'', ''2019-12-12'', 2, 0, false, true, false, null, 1, 1);
            insert into task(name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category_id)
            values (''Buy new shoes'', ''Buy new shoes for prom'', 5, ''HARD'', ''DISLIKE'', ''2019-12-12'', 2, 0, true, false, false, 1, 1, 1);
            insert into task(name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category_id)
            values (''Buy new clutch'', ''Buy new clutch for prom'', 5, ''HARD'', ''DISLIKE'', ''2019-12-12'', 3, 0, true, false, false, 1, 1, 1);
            insert into task(name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category_id)
            values (''Meeting with John'', ''Birthday'', 7, ''MEDIUM'', ''LOVE'', ''2019-11-11'', 4, 0, false, true, false, null, 2, 5);
            insert into task(name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category_id)
            values (''Learn for math test'', ''Geometry'', 9, ''HARD'', ''HATE'', ''2019-09-09'', 5, 0, false, false, true, null, 1, 2);
            insert into task(name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category_id)
            values (''Clean the kitchen'', ''Dishes, cupboards'', 6, ''MEDIUM'', ''NEUTRAL'', ''2019-08-08'', 2, 0, true, false, false, 1, 2, 3);
            insert into task(name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category_id)
            values (''End the task for client'', ''ID-765'', 10, ''EXTRA_HARD'', ''LIKE'', ''2019-07-07'', 2, 0, false, false, false, null, 1, 1);
        END IF;

    IF (NOT EXISTS(SELECT * FROM calendar))
        THEN
            insert into calendar(id, name, app_user_id)
            values (1, ''AGH'', 1);
            insert into calendar(id, name, app_user_id)
            values (2, ''Work'', 1);
            insert into calendar(id, name, app_user_id)
            values (3, ''Home'', 2);
            insert into calendar(id, name, app_user_id)
            values (4, ''Friends'', 4);
            insert into calendar(id, name, app_user_id)
            values (5, ''Birthday'', 3);
        END IF;

    IF (NOT EXISTS(SELECT * FROM calendar_task))
        THEN
            insert into calendar_task(start_date, end_date, calendar_id, task_id)
            values (''2019-12-08'', null, 2, 1);
            insert into calendar_task(start_date, end_date, calendar_id, task_id)
            values (''2019-12-06'', null, 2, 2);
            insert into calendar_task(start_date, end_date, calendar_id, task_id)
            values (''2019-12-07'', null, 2, 3);
            insert into calendar_task(start_date, end_date, calendar_id, task_id)
            values (''2019-11-08'', null, 3, 4);
            insert into calendar_task(start_date, end_date, calendar_id, task_id)
            values (''2019-09-08'', null, 1, 5);
        END IF;

END;
'  LANGUAGE PLPGSQL;
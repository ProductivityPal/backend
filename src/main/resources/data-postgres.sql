DO
'
DECLARE
BEGIN
    IF (NOT EXISTS(SELECT * FROM app_user))
        THEN
            insert into app_user(id, username, password, email, current_energy_level, is_email_verified, role)
            values (1, ''Johnny123'', ''$2a$10$/nL8rFQ4w6ZOVU8wBbSTFeJS28NE15P7jOo/OFY7S6nrFoNWQazAC'', ''johny123@gmail.com'', ''LOW'', false, ''USER'');
             insert into app_user(id, username, password, email, current_energy_level, is_email_verified, role)
             values (2, ''Mike434'', ''$2a$10$/nL8rFQ4w6ZOVU8wBbSTFeJS28NE15P7jOo/OFY7S6nrFoNWQazAC'', ''mikerr@gmail.com'', ''MEDIUM'', false, ''USER'');
            insert into app_user(id, username, password, email, current_energy_level, is_email_verified, role)
            values (3, ''KateJ'', ''$2a$10$/nL8rFQ4w6ZOVU8wBbSTFeJS28NE15P7jOo/OFY7S6nrFoNWQazAC'', ''kate123@gmail.com'', ''HIGH'', false, ''USER'');
             insert into app_user(id, username, password, email, current_energy_level, is_email_verified, role)
             values (4, ''TimW12'', ''$2a$10$/nL8rFQ4w6ZOVU8wBbSTFeJS28NE15P7jOo/OFY7S6nrFoNWQazAC'', ''timmit@gmail.com'', ''LOW'', false, ''USER'');
            insert into app_user(id, username, password, email, current_energy_level, is_email_verified, role)
            values (5, ''student'', ''$2a$10$/nL8rFQ4w6ZOVU8wBbSTFeJS28NE15P7jOo/OFY7S6nrFoNWQazAC'', ''student@gmail.com'', ''MEDIUM'', false, ''USER'');
            insert into app_user(id, username, password, email, current_energy_level, is_email_verified, role)
            values (6, ''student2'', ''$2a$10$/nL8rFQ4w6ZOVU8wBbSTFeJS28NE15P7jOo/OFY7S6nrFoNWQazAC'', ''student2@gmail.com'', ''MEDIUM'', false, ''USER'');
    END IF;

    IF (NOT EXISTS(SELECT * FROM task))
        THEN
            insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            values (1, ''Buy new skirt'', ''Buy new skirt for prom'', 5, ''HARD'', ''DISLIKE'', ''2019-12-12'', 2, 0, false, true, false, null, 1);
            insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            values (2, ''Buy new shoes'', ''Buy new shoes for prom'', 5, ''HARD'', ''DISLIKE'', ''2019-12-12'', 2, 0, true, false, false, 100, 1);
            insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            values (3, ''Buy new clutch'', ''Buy new clutch for prom'', 5, ''HARD'', ''DISLIKE'', ''2019-12-12'', 3, 0, true, false, false, 100, 1);
            insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            values (4, ''Meeting with John'', ''Birthday'', 7, ''MEDIUM'', ''LOVE'', ''2019-11-11'', 4, 0, false, true, false, null, 2);
            insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            values (5, ''Learn for math test'', ''Geometry'', 9, ''HARD'', ''HATE'', ''2019-09-09'', 5, 0, false, false, true, null, 1);
            insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            values (6, ''Clean the kitchen'', ''Dishes, cupboards'', 6, ''MEDIUM'', ''NEUTRAL'', ''2019-08-08'', 2, 0, true, false, false, 100, 2);
            insert into task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            values (7, ''End the task for client'', ''ID-765'', 10, ''EXTRA_HARD'', ''LIKE'', ''2019-07-07'', 2, 0, false, false, false, null, 1);

            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (8, ''Complete tax filing'', ''Gather all necessary documents and file income tax return'', 4, ''HARD'', ''LIKE'', ''2023-06-15'', 8, NULL, false, false, false, null, 3);

            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (9, ''Prepare for presentation'', ''Research, create slides, and rehearse presentation'', 3, ''MEDIUM'', ''LOVE'', ''2023-06-01'', 10, NULL, false, false, false, null, 3);

            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (10, ''Grocery shopping'', ''Purchase essential items for the week'', 1, ''EASY'', ''LIKE'', ''2023-05-28'', 2, NULL, false, false, false, NULL, 3);

            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (11, ''Plan weekend trip'', ''Research destinations, book accommodations, and plan activities'', 2, ''MEDIUM'', ''LOVE'', ''2023-06-10'', 6, NULL, false, false, false, NULL, 3);

            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (12, ''Organize workspace'', ''Clean desk, declutter, and organize files'', 5, ''EASY'', ''LIKE'', ''2023-05-31'', 3, NULL, false, false, false, NULL, 3);

            -- Scenario 2
            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (13, ''Review and provide feedback on report'', ''Read through the report, make comments, and suggest improvements'', 3, ''MEDIUM'', ''LIKE'', ''2023-06-02'', 4, NULL, false, false, false, NULL, 4);

            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (14, ''Code refactoring'', ''Improve the code structure and optimize performance'', 2, ''HARD'', ''LIKE'', ''2023-05-29'', 6, NULL, false, false, false, NULL, 4);

            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (15, ''Prepare presentation for client meeting'', ''Create slides and gather relevant information for the upcoming client meeting'', 4, ''MEDIUM'', ''LIKE'', ''2023-06-05'', 5, NULL, false, false, false, NULL, 4);

            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (16, ''Fix critical bug'', ''Investigate and resolve a critical bug affecting the application'', 1, ''EXTRA_HARD'', ''NEUTRAL'', ''2023-05-26'', 8, NULL, false, false, false, NULL, 4);

            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (17, ''Conduct team training session'', ''Prepare materials and deliver a training session to the team'', 5, ''MEDIUM'', ''LIKE'', ''2023-06-15'', 3, NULL, false, false, false, NULL, 4);

            --Scenario 3
            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (18, ''Write research paper'', ''Conduct research, analyze data, and write a research paper'', 2, ''HARD'', ''LOVE'', ''2023-06-20'', 20, NULL, false, false, false, NULL, 5);

            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (19, ''Study for final exams'', ''Review course materials and prepare for upcoming final exams'', 1, ''MEDIUM'', ''LIKE'', ''2023-06-10'', 15, NULL, false, false, false, NULL, 5);

            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (20, ''Complete programming assignment'', ''Implement a program based on the given specifications'', 3, ''EASY'', ''LIKE'', ''2023-06-05'', 8, NULL, false, false, false, NULL, 5);

            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (21, ''Read ray documentation'', ''Read and understand the documentation for the next class'', 5, ''EASY'', ''LIKE'', ''2023-06-01'', 2, NULL, false, false, false, NULL, 5);

            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (22, ''Group project meeting'', ''Coordinate with group members and schedule a meeting to work on the group project'', 4, ''EASY'', ''LOVE'', ''2023-06-08'', 1, NULL, false, false, false, NULL, 5);

            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
            VALUES (23, ''Group project meeting'', ''Coordinate with group members and schedule a meeting to work on the group project'', 4, ''EASY'', ''LOVE'', ''2023-06-08'', 1, NULL, false, false, false, NULL, 5);

--
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
--             VALUES (24, ''Prepare flashcards with WDI'', NULL, 3, ''EASY'', ''LOVE'', ''2023-12-16T00:00:00'', 60, NULL, false, false, false, NULL, 6);
--
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
--             VALUES (25, ''Clean up the files on your laptop'', NULL, 1, ''EASY'', ''HATE'', ''2024-02-16T00:00:00'', 60, NULL, false, false, false, NULL, 6);
--
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
--             VALUES (26, ''Take notes from UNIX lectures'', NULL, 3, ''HARD'', ''DISLIKE'', ''2023-12-17T00:00:00'', 60, NULL, false, false, false, NULL, 6);
--
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id)
--             VALUES (27, ''Arrange with friends the repetitions of Discrete Analysis'', NULL, 5, ''MEDIUM'', ''LOVE'', ''2024-02-24T00:00:00'', 60, NULL, false, false, false, NULL, 6);

        --             -- Zadanie 24
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
--             VALUES (24, ''Task 24'', ''Description for Task 24'', 2, ''EASY'', ''LOVE'', ''2024-12-20'', 40, NULL, false, false, false, NULL, 6, ''grey'');
--
--             -- Zadanie 25
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
--             VALUES (25, ''Task 25'', ''Description for Task 25'', 3, ''MEDIUM'', ''NEUTRAL'', ''2024-12-21'', 60, NULL, false, false, false, NULL, 6, ''grey'');
--
--             -- Zadanie 26
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
--             VALUES (26, ''Task 26'', ''Description for Task 26'', 4, ''EXTRA_HARD'', ''HATE'', ''2024-12-22'', 80, NULL, false, false, false, NULL, 6, ''grey'');
--
--             -- Zadanie 27
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
--             VALUES (27, ''Task 27'', ''Description for Task 27'', 2, ''EXTRA_HARD'', ''LOVE'', ''2024-12-23'', 40, NULL, false, false, false, NULL, 6, ''grey'');
--
            -- Zadanie 28
            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
            VALUES (28, ''Task 28'', ''Description for Task 28'', 3, ''EXTRA_HARD'', ''NEUTRAL'', ''2023-11-20'', 60, NULL, false, false, false, NULL, 6, ''grey'');

            -- Zadanie 29
            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
            VALUES (29, ''Task 29'', ''Description for Task 29'', 4, ''EXTRA_HARD'', ''HATE'', ''2023-10-10'', 80, 60, false, false, true, NULL, 6, ''grey'');

            -- Zadanie 30
            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
            VALUES (30, ''Task 30'', ''Description for Task 30'', 2, ''EXTRA_HARD'', ''LOVE'', ''2023-12-07'', 40, NULL, false, false, false, NULL, 6, ''grey'');

            -- Zadanie 31
            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
            VALUES (31, ''Task 31'', ''Description for Task 31'', 3, ''EXTRA_HARD'', ''NEUTRAL'', ''2023-12-07'', 60, NULL, false, false, false, NULL, 6, ''grey'');

            -- Zadanie 32
            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
            VALUES (32, ''Task 32'', ''Description for Task 32'', 4, ''EXTRA_HARD'', ''HATE'', ''2023-12-08'', 80, NULL, false, false, false, NULL, 6, ''grey'');

            -- Zadanie 33
            INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
            VALUES (33, ''Task 33'', ''Description for Task 33'', 2, ''EXTRA_HARD'', ''LOVE'', ''2023-11-29'', 40, NULL, false, false, false, NULL, 6, ''grey'');

        --Scenario user 6
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
--             VALUES (24, ''Code review for feature A'', ''Review and provide feedback on the code for feature A'', 3, ''MEDIUM'', ''NEUTRAL'', ''2023-12-08'', 60, 73, false, false, true, NULL, 6, ''beige'');
--
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
--             VALUES (25, ''Prepare presentation slides'', ''Create slides for the upcoming project presentation'', 2, ''EASY'', ''DISLIKE'', ''2023-12-07'', 30, 55, false, false, true, NULL, 6, ''green'');
--
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
--             VALUES (26, ''Database schema design'', ''Work on designing the database schema for the new application'', 4, ''MEDIUM'', ''LIKE'', ''2023-11-30'', 45, 40, false, false, true, NULL, 6, ''accent'');
--
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
--             VALUES (27, ''Write unit tests'', ''Create unit tests for the key components of the application'', 3, ''MEDIUM'', ''HATE'', ''2023-12-08'', 50, NULL, false, false, false, NULL, 6, ''grey'');
--
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
--             VALUES (28, ''Project documentation'', ''Document the progress and key decisions made during the project'', 2, ''EASY'', ''LIKE'', ''2023-12-10'', 90, NULL, false, false, false, NULL, 6, ''beige'');
--
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
--             VALUES (29, ''Bug fixing for release'', ''Identify and fix any bugs before the upcoming release'', 4, ''HARD'', ''LOVE'', ''2023-12-15'', 30, NULL, false, false, false, NULL, 6, ''green'');
--
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
--             VALUES (30, ''Client meeting preparation'', ''Prepare for the client meeting to discuss project requirements'', 3, ''EXTRA_HARD'', ''DISLIKE'', ''2023-12-30'', 20, NULL, false, false, false, NULL, 6, ''beige'');
--
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
--             VALUES (31, ''Security audit'', ''Conduct a security audit for the application code and infrastructure'', 4, ''HARD'', ''LOVE'', ''2024-01-11'', 50, NULL, false, false, false, NULL, 6, ''green'');
--
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
--             VALUES (32, ''User training materials'', ''Develop training materials for end-users of the application'', 2, ''EASY'', ''HATE'', ''2024-12-20'', 40, NULL, false, false, false, NULL, 6, ''grey'');
--
--             INSERT INTO task(id, name, description, priority, difficulty, likeliness, deadline, time_estimate, completion_time, is_subtask, is_parent, is_completed, parent_id, app_user_id, category)
--             VALUES (33, ''Final project review'', ''Conduct a final review of the entire project and make any necessary adjustments'', 3, ''MEDIUM'', ''LOVE'', ''2024-01-02'', 60, NULL, false, false, false, NULL, 6, ''grey'');
         END IF;

    IF (NOT EXISTS(SELECT * FROM calendar))
        THEN
            insert into calendar(id, name, app_user_id)
            values (1, ''Default'', 1);
            insert into calendar(id, name, app_user_id)
            values (2, ''Default'', 2);
            insert into calendar(id, name, app_user_id)
            values (3, ''Default'', 3);
            insert into calendar(id, name, app_user_id)
            values (4, ''Default'', 4);
            insert into calendar(id, name, app_user_id)
            values (5, ''Default'', 5);
            insert into calendar(id, name, app_user_id)
            values (6, ''Default'', 6);
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
            insert into calendar_task(start_date, end_date, calendar_id, task_id)
            values (''2023-12-16'', null, 6, 33);
        END IF;

    IF (NOT EXISTS(SELECT * FROM category))
        THEN
            insert into category(default_name, app_user_id)
            values (''beige'', 5);
            insert into category(default_name, app_user_id)
            values (''green'', 5);
            insert into category(default_name, app_user_id)
            values (''accent'', 5);
            insert into category(default_name, app_user_id)
            values (''grey'', 5);
            insert into category(default_name, app_user_id)
            values (''beige'', 6);
            insert into category(default_name, app_user_id)
            values (''green'', 6);
            insert into category(default_name, app_user_id)
            values (''accent'', 6);
            insert into category(default_name, app_user_id)
            values (''grey'', 6);
        END IF;

    IF (NOT EXISTS(SELECT * FROM energy_level_info))
        THEN
            insert into energy_level_info(energy_level, notification_time, app_user_id)
            values (''LOW'', ''2023-12-10T11:59:11.332Z'', 6);
            insert into energy_level_info(energy_level, notification_time, app_user_id)
            values (''LOW'', ''2023-12-11T11:59:11.332Z'', 6);
            insert into energy_level_info(energy_level, notification_time, app_user_id)
            values (''MEDIUM'', ''2023-12-11T08:59:11.332Z'', 6);
        END IF;


END;
'  LANGUAGE PLPGSQL;

DO
'
DECLARE
    tmp   character varying(255);
begin
    tmp = (SELECT setval(''task_id_seq'', (SELECT MAX(id) FROM task)));
    tmp = (SELECT setval(''app_user_id_seq'', (SELECT MAX(id) FROM app_user)));
    tmp = (SELECT setval(''category_id_seq'', (SELECT MAX(id) FROM category)));
    tmp = (SELECT setval(''calendar_id_seq'', (SELECT MAX(id) FROM calendar)));
    tmp = (SELECT setval(''calendar_task_id_seq'', (SELECT MAX(id) FROM calendar_task)));
    tmp = (SELECT setval(''energy_level_info_id_seq'', (SELECT MAX(id) FROM energy_level_info)));
end;
'  LANGUAGE PLPGSQL;
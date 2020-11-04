insert into USERS (id, username, password, role)
values (1, 'admin', '$2y$12$Xoz7C1SWIU5XyE9LonwUe..ibSG6BLRYoAWWiUg1DvtEXc4uOHZw6', 'ADMIN');
insert into USERS (id, username, password, role)
values (2, 'user', '$2y$12$pPqnziz/1raSfCha/WP2/OshpLVXDqV9CQ/txBSm/QxXkPqHjdC2S', 'USER');

insert into COURSES (id, course_audience, course_document, course_goal, course_price, course_subject, course_time, created_at, title, url_segment, course_status)
values (1, 'COURSE_AUDIENCE', 'COURSE_DOCUMENT', 'COURSE_GOAL', 123, 'COURSE_SUBJECT', 123, CURRENT_TIMESTAMP, 'TITLE', 'URL_SEGMENT', 'PUBLISHED');
insert into COURSES (id, course_audience, course_document, course_goal, course_price, course_subject, course_time, created_at, title, url_segment, course_status)
values (2, 'COURSE_AUDIENCE2', 'COURSE_DOCUMENT2', 'COURSE_GOAL2', 1232, 'COURSE_SUBJECT2', 1232, CURRENT_TIMESTAMP, 'TITLE2', 'URL_SEGMENT2', 'UNPUBLISHED');
insert into COURSES (id, course_audience, course_document, course_goal, course_price, course_subject, course_time, created_at, title, url_segment, course_status)
values (3, 'COURSE_AUDIENCE3', 'COURSE_DOCUMENT3', 'COURSE_GOAL3', 1233, 'COURSE_SUBJECT3', 1233, CURRENT_TIMESTAMP, 'TITLE3', 'URL_SEGMENT3', 'UNPUBLISHED');
insert into COURSES (id, course_audience, course_document, course_goal, course_price, course_subject, course_time, created_at, title, url_segment, course_status)
values (4, 'COURSE_AUDIENCE4', 'COURSE_DOCUMENT4', 'COURSE_GOAL4', 1234, 'COURSE_SUBJECT4', 1234, CURRENT_TIMESTAMP, 'TITLE4', 'URL_SEGMENT4', 'UNPUBLISHED');


insert into EDUCATIONAL_PROGRAM_STAGE  (id, DESCRIPTION, TITLE, COURSE_ID)
values (1, 'DESCRIPTION', 'TITLE', 1);
insert into EDUCATIONAL_PROGRAM_STAGE  (id, DESCRIPTION, TITLE, COURSE_ID)
values (2, 'DESCRIPTION2', 'TITLE2', 1);
insert into EDUCATIONAL_PROGRAM_STAGE  (id, DESCRIPTION, TITLE, COURSE_ID)
values (3, 'DESCRIPTION3', 'TITLE3', 1);
insert into EDUCATIONAL_PROGRAM_STAGE  (id, DESCRIPTION, TITLE, COURSE_ID)
values (4, 'DESCRIPTION4', 'TITLE4', 1);

insert into TEACHERS (id, FULL_NAME, TEACHER_OCCUPATION, TEACHER_INFORMATION, teacher_Date_Of_Birth, created_at)
values (1, 'FULL_NAME1', 'TEACHER_OCCUPATION_1', 'TEACHER_INFORMATION1', '1988-10-07',CURRENT_TIMESTAMP);
insert into TEACHERS (id, FULL_NAME, TEACHER_OCCUPATION, TEACHER_INFORMATION, teacher_Date_Of_Birth, created_at)
values (2, 'FULL_NAME2', 'TEACHER_OCCUPATION_2', 'TEACHER_INFORMATION2', '1982-12-31',CURRENT_TIMESTAMP);
insert into TEACHERS (id, FULL_NAME, TEACHER_OCCUPATION, TEACHER_INFORMATION, teacher_Date_Of_Birth, created_at)
values (3, 'FULL_NAME3', 'TEACHER_OCCUPATION_3', 'TEACHER_INFORMATION3', '1958-12-17',CURRENT_TIMESTAMP);
insert into TEACHERS (id, FULL_NAME, TEACHER_OCCUPATION, TEACHER_INFORMATION, teacher_Date_Of_Birth, created_at)
values (4, 'FULL_NAME4', 'TEACHER_OCCUPATION_4', 'TEACHER_INFORMATION4', '1988-10-07',CURRENT_TIMESTAMP);
insert into TEACHERS (id, FULL_NAME, TEACHER_OCCUPATION, TEACHER_INFORMATION, teacher_Date_Of_Birth, created_at)
values (5, 'FULL_NAME5', 'TEACHER_OCCUPATION_5', 'TEACHER_INFORMATION5', '1988-10-07',CURRENT_TIMESTAMP);

insert into COURSE_TEACHER (COURSE_ID, TEACHER_ID)
values (1, 1);
insert into COURSE_TEACHER (COURSE_ID, TEACHER_ID)
values (1, 2);
insert into COURSE_TEACHER (COURSE_ID, TEACHER_ID)
values (1, 3);
insert into COURSE_TEACHER (COURSE_ID, TEACHER_ID)
values (2, 1);
insert into COURSE_TEACHER (COURSE_ID, TEACHER_ID)
values (3, 1);
insert into COURSE_TEACHER (COURSE_ID, TEACHER_ID)
values (2, 3);

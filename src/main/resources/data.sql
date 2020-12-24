insert into USERS (id, username, password, role)
values (1, 'admin', '$2y$12$Xoz7C1SWIU5XyE9LonwUe..ibSG6BLRYoAWWiUg1DvtEXc4uOHZw6', 'ADMIN');
insert into USERS (id, username, password, role)
values (2, 'user', '$2y$12$pPqnziz/1raSfCha/WP2/OshpLVXDqV9CQ/txBSm/QxXkPqHjdC2S', 'USER');


insert into COURSES (VERSION, id, course_audience, course_document, course_goal, course_price, course_subject, course_time, created_at, title, url_segment, course_status, course_competency, course_field, course_form, course_study_location, course_target_entity, course_type, created_By)
values (0,1, 'COURSE_AUDIENCE', 'COURSE_DOCUMENT', 'COURSE_GOAL', 1, 'COURSE_SUBJECT', 123, CURRENT_TIMESTAMP, 'TITLE', 'URL_SEGMENT', 'PUBLISHED', 'PROFESSIONAL_COMPETENCE', 'WORLDSKILLS_RUSSIA', 'OCHNAYA', 'BOLSHAYA_SEMENOVSKAYA', 'INDIVIDUAL', 'TRAINING', 'admin1');

insert into COURSES (VERSION,id, course_audience, course_document, course_goal, course_price, course_subject, course_time, created_at, title, url_segment, course_status, course_competency, course_field, course_form, course_study_location, course_target_entity, course_type, created_By)
values (0,2, 'COURSE_AUDIENCE2', 'COURSE_DOCUMENT', 'COURSE_GOAL', 132, 'COURSE_SUBJECT', 123, CURRENT_TIMESTAMP, 'TITLE2', 'URL_SEGMENT2', 'PUBLISHED', 'PERSONAL_COMPETENCE', 'CHILDREN_TECHNOPARK', 'REMOTE', 'ELECTROZAVODSKAYA', 'INDIVIDUAL', 'PROFESSIONAL_RETRAINING', 'admin1');

insert into COURSES (VERSION,id, course_audience, course_document, course_goal, course_price, course_subject, course_time, created_at, title, url_segment, course_status, course_competency, course_field, course_form, course_study_location, course_target_entity, course_type, created_By)
values (0,3, 'COURSE_AUDIENCE1', 'COURSE_DOCUMENT', 'COURSE_GOAL', 144, 'COURSE_SUBJECT', 123, CURRENT_TIMESTAMP, 'TITLE23', 'URL_SEGMENT3', 'PUBLISHED', 'PERSONAL_COMPETENCE', 'INDUSTRY_AND_ECOLOGICAL_SAFETY', 'OCHNO_ZAOCHNAYA', 'PAVEL_KORCHAGIN', 'PLURAL', 'ADDITIONAL_EDUCATIONAL_PROGRAMS', 'admin1');
insert into COURSES (VERSION,id, course_audience, course_document, course_goal, course_price, course_subject, course_time, created_at, updated_at, title, url_segment, course_status, course_competency, course_field, course_form, course_study_location, course_target_entity, course_type, eddited_By)
values (1,3, 'COURSE_AUDIENCE2', 'COURSE_DOCUMENT', 'COURSE_GOAL', 144, 'COURSE_SUBJECT', 123, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'TITLE23', '2', 'PUBLISHED', 'PERSONAL_COMPETENCE', 'INDUSTRY_AND_ECOLOGICAL_SAFETY', 'OCHNO_ZAOCHNAYA', 'PAVEL_KORCHAGIN', 'PLURAL', 'ADDITIONAL_EDUCATIONAL_PROGRAMS', 'admin1');
insert into COURSES (VERSION,id, course_audience, course_document, course_goal, course_price, course_subject, course_time, created_at, updated_at, title, url_segment, course_status, course_competency, course_field, course_form, course_study_location, course_target_entity, course_type, eddited_By)
values (2,3, 'COURSE_AUDIENCE3', 'COURSE_DOCUMENT', 'COURSE_GOAL', 144, 'COURSE_SUBJECT', 123, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'TITLE23', '3', 'PUBLISHED', 'PERSONAL_COMPETENCE', 'INDUSTRY_AND_ECOLOGICAL_SAFETY', 'OCHNO_ZAOCHNAYA', 'PAVEL_KORCHAGIN', 'PLURAL', 'ADDITIONAL_EDUCATIONAL_PROGRAMS', 'admin1');

insert into COURSES (VERSION,id, course_audience, course_document, course_goal, course_price, course_subject, course_time, created_at, title, url_segment, course_status, course_competency, course_field, course_form, course_study_location, course_target_entity, course_type, created_By)
values (0,4, 'COURSE_AUDIENCE', 'COURSE_DOCUMENT', 'COURSE_GOAL', 111111, 'COURSE_SUBJECT', 123, CURRENT_TIMESTAMP, 'TITLE4', 'URL_SEGMENT4', 'PUBLISHED', 'PROFESSIONAL_COMPETENCE', 'TRANSPORT', 'ZAOCHNAYA', 'DUBROVSKAYA', 'PLURAL', 'ADDITIONAL_EDUCATIONAL_PROGRAMS', 'admin1');

insert into COURSES (VERSION,id, course_audience, course_document, course_goal, course_price, course_subject, course_time, created_at, title, url_segment, course_status, course_competency, course_field, course_form, course_study_location, course_target_entity, course_type, created_By)
values (0,5, 'COURSE_AUDIENCE', 'COURSE_DOCUMENT', 'COURSE_GOAL', 32131, 'COURSE_SUBJECT', 123, CURRENT_TIMESTAMP, 'TITLE5', 'URL_SEGMENT5', 'PUBLISHED', 'PROFESSIONAL_COMPETENCE', 'WORK_WITH_SOFTWARE_PRODUCTS', 'OCHNAYA', 'AVIAMOTORNAYA', 'INDIVIDUAL', 'PROFESSIONAL_RETRAINING', 'admin1');


insert into EDUCATIONAL_PROGRAM_STAGE  (id, DESCRIPTION, TITLE, COURSE_ID, COURSE_VERSION)
values (1, 'Новое описание', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 1, 0);
insert into EDUCATIONAL_PROGRAM_STAGE  (id, DESCRIPTION, TITLE, COURSE_ID, COURSE_VERSION)
values (2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 1, 0);
insert into EDUCATIONAL_PROGRAM_STAGE  (id, DESCRIPTION, TITLE, COURSE_ID, COURSE_VERSION)
values (3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 1, 0);
insert into EDUCATIONAL_PROGRAM_STAGE  (id, DESCRIPTION, TITLE, COURSE_ID, COURSE_VERSION)
values (4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 1, 0);

insert into TEACHERS (id, FULL_NAME, TEACHER_OCCUPATION, TEACHER_INFORMATION, teacher_Date_Of_Birth, created_at)
values (1, 'FULL_NAME1', 'TEACHER_OCCUPATION_1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', '1988-10-07',CURRENT_TIMESTAMP);
insert into TEACHERS (id, FULL_NAME, TEACHER_OCCUPATION, TEACHER_INFORMATION, teacher_Date_Of_Birth, created_at)
values (2, 'FULL_NAME2', 'TEACHER_OCCUPATION_2', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', '1982-12-31',CURRENT_TIMESTAMP);
insert into TEACHERS (id, FULL_NAME, TEACHER_OCCUPATION, TEACHER_INFORMATION, teacher_Date_Of_Birth, created_at)
values (3, 'FULL_NAME3', 'TEACHER_OCCUPATION_3', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', '1958-12-17',CURRENT_TIMESTAMP);
insert into TEACHERS (id, FULL_NAME, TEACHER_OCCUPATION, TEACHER_INFORMATION, teacher_Date_Of_Birth, created_at)
values (4, 'FULL_NAME4', 'TEACHER_OCCUPATION_4', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', '1988-10-07',CURRENT_TIMESTAMP);
insert into TEACHERS (id, FULL_NAME, TEACHER_OCCUPATION, TEACHER_INFORMATION, teacher_Date_Of_Birth, created_at)
values (5, 'FULL_NAME5', 'TEACHER_OCCUPATION_5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', '1988-10-07',CURRENT_TIMESTAMP);

insert into COURSE_TEACHER (COURSE_ID, VERSION, TEACHER_ID)
values (1, 0, 1);
insert into COURSE_TEACHER (COURSE_ID, VERSION, TEACHER_ID)
values (1, 0, 2);
insert into COURSE_TEACHER (COURSE_ID, VERSION, TEACHER_ID)
values (1, 0, 3);
insert into COURSE_TEACHER (COURSE_ID, VERSION, TEACHER_ID)
values (2, 0, 1);
insert into COURSE_TEACHER (COURSE_ID, VERSION, TEACHER_ID)
values (3, 0, 1);
insert into COURSE_TEACHER (COURSE_ID, VERSION, TEACHER_ID)
values (2, 0, 3);

insert into NEWS (id, DESCRIPTION, TITLE, URL_SEGMENT, created_at, NEWS_STATUS)
values (1, 'DESCRIPTION1', 'TITLE1', 'qwe1', CURRENT_TIMESTAMP, 'PUBLISHED');

insert into NEWS (id, DESCRIPTION, TITLE, URL_SEGMENT,  created_at, NEWS_STATUS)
values (2, 'DESCRIPTION2', 'TITLE2', 'qwe2', CURRENT_TIMESTAMP, 'PUBLISHED');
# CREATE DATABASE  emcdb;
# USE emcdb;
#
# drop table if exists patients;
# drop table if exists doctors;
# drop table if exists doctor_patient;


-- fill tables with demo data
insert into patients(first_name, last_name, sex, address, phone_number)
values ('Dmitriy', 'Ivanov', 'm', 'Kyiv', '044-123'),
       ('Nikolay', 'Petrov', 'm', 'Kyiv', '044-234'),
       ('Alex', 'Statnik', 'm', 'Kyiv', '044-345'),
       ('Pavel', 'Pavlov', 'm', 'Kyiv', '044-456'),
       ('Marina', 'Silver', 'f', 'Kyiv', '044-567'),
       ('Darina', 'Kush', 'f', 'Kyiv', '044-678'),
       ('Anna', 'Bondar', 'f', 'Kyiv', '044-789');

insert into doctors(first_name, last_name, sex, address, phone_number, speciality)
values ('Margo', 'Slyva', 'f', 'Kyiv', '8-1587', 'family doctor'),
       ('Timur', 'Baraban', 'm', 'Kyiv', '8-123', 'dentist'),
       ('Artem', 'Poroh', 'm', 'Kyiv', '8-321', 'optometrist'),
       ('Svetlana', 'Malyar', 'f', 'Kyiv', '8-333', 'therapist'),
       ('Veronika', 'Matsiuk', 'f', 'Kyiv', '8-777', 'surgeon');


insert into patients_doctors(doctor_id, patient_id)
values (1,	1),
       (1,	2),
       (1,	3),
       (2,	1),
       (2,	3),
       (2,	4),
       (2,	5),
       (3,	2),
       (4,	3),
       (4,	5),
       (4,	7),
       (5,	1),
       (5,	2),
       (5,	6);



create table 课程
(
    id         int auto_increment
        primary key,
    name       varchar(50) not null,
    studyTime  int         not null,
    studyGrade int         not null
);

INSERT INTO 学生管理.课程 (id, name, studyTime, studyGrade) VALUES (1, '外语', 64, 3);
INSERT INTO 学生管理.课程 (id, name, studyTime, studyGrade) VALUES (2, '数据库', 32, 2);
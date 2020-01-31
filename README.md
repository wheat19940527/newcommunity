##community

##url
[spring doc](https://spring.io/guides)


##tool


##script
'''sql
create table USER
(
    ID         INTEGER auto_increment,
    ACCOUNT_ID VARCHAR(100),
    NAME       VARCHAR(50),
    TOKEN      CHAR(36),
    GMT_CREATE BIGINT,
    GMT_MODIFY BIGINT,
    constraint USER_PK
        primary key (ID)
);

'''

'''bash
mvn flyway:migrate
'''


https://flywaydb.org/getstarted/firststeps/maven
##码匠社区

##资料
[spring 文档](https://spring.io/guides)
[spring web文档](https://spring.io/guides/gs/serving-web-content/)
[bootstrap](https://v3.bootcss.com/getting-started/#download)
[github 认证](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

##工具
[OkHttp](https://square.github.io/okhttp/)

##脚本
```sql
create table user
(
    id           int(10) auto_increment
        primary key,
    account_id   varchar(100) null,
    name         varchar(50)  null,
    token        char(36)     null,
    gmt_create   bigint       null,
    gmt_modified bigint       null,
    bio          varchar(256) null,
    avatar_url   varchar(100) null
);

CREATE TABLE question
(
    id int PRIMARY KEY AUTO_INCREMENT,
    titlle varchar(50),
    description text,
    gmt_create bigint,
    gmt_modified bigint,
    creator int,
    comment_count int DEFAULT 0,
    view_count int DEFAULT 0,
    like_count int DEFAULT 0,
    tag varchar(256)
);
```
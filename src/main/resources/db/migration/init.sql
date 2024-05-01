
create table blacklist (
                           blacklist_id bigserial not null,
                           blocked_member_id varchar(36) not null,
                           member_id varchar(36) not null,
                           primary key (blacklist_id),
                           unique (member_id, blocked_member_id)
);

create table bookmark (
                          bookmark_id bigserial not null,
                          cafe_id varchar(36) not null,
                          member_id varchar(36) not null,
                          primary key (bookmark_id),
                          unique (member_id, cafe_id)
);

create table cafe (
                      created_at timestamp(6) not null,
                      updated_at timestamp(6),
                      phone_num varchar(11) not null,
                      cafe_id varchar(36) not null,
                      name varchar(50) not null unique,
                      address varchar(100) not null unique,
                      primary key (cafe_id)
);

create table cafe_register (
                               member_id varchar(36) not null,
                               register_id varchar(36) not null,
                               name varchar(50) not null,
                               address varchar(100) not null,
                               primary key (register_id)
);

create table comment (
                         is_visible boolean not null,
                         created_at timestamp(6) not null,
                         updated_at timestamp(6),
                         comment_id varchar(36) not null,
                         feed_id varchar(36) not null,
                         member_id varchar(36) not null,
                         parent_comment_id varchar(36),
                         content varchar(200) not null,
                         primary key (comment_id)
);

create table feed (
                      is_visible boolean not null,
                      created_at timestamp(6) not null,
                      grade bigint,
                      hits bigint not null,
                      updated_at timestamp(6),
                      cafe_id varchar(36) not null unique,
                      feed_id varchar(36) not null,
                      member_id varchar(36) not null,
                      content varchar(2000) not null,
                      primary key (feed_id)
);

create table follow (
                        follow_id bigserial not null,
                        following_id varchar(36) not null,
                        member_id varchar(36) not null,
                        primary key (follow_id),
                        unique (member_id, following_id)
);

create table heart (
                       content_type varchar(16) not null check (content_type in ('MEMBER','FEED','CAFE','MENU','COMMENT')),
                       content_id varchar(36) not null,
                       heart_id varchar(36) not null,
                       member_id varchar(36) not null,
                       primary key (heart_id)
);

create table image (
                       content_type varchar(16) not null check (content_type in ('MEMBER','FEED','CAFE','MENU','COMMENT')),
                       content_id varchar(36) not null,
                       image_id varchar(36) not null,
                       image_url varchar(255) not null,
                       primary key (image_id)
);

create table member (
                        is_deleted boolean not null,
                        created_at timestamp(6) not null,
                        updated_at timestamp(6),
                        phone_num varchar(11) not null,
                        role varchar(16) not null check (role in ('MEMBER','VERIFIED_MEMBER','SELLER','ADMIN')),
                        member_id varchar(36) not null,
                        email varchar(255) not null unique,
                        nickname varchar(255) not null unique,
                        password varchar(255) not null,
                        primary key (member_id)
);

create table menu (
                      is_limited boolean not null,
                      menu_type varchar(16) not null check (menu_type in ('COFFEE','BEVERAGE','DESSERT')),
                      cafe_id varchar(36) not null,
                      menu_id varchar(36) not null,
                      allergy varchar(50),
                      details varchar(50),
                      name varchar(255) not null,
                      price varchar(255) not null,
                      volume varchar(255),
                      primary key (menu_id)
);

create table message (
                         is_checked boolean not null,
                         created_at timestamp(6) not null,
                         updated_at timestamp(6),
                         message_id varchar(36) not null,
                         receive_member_id varchar(36) not null,
                         send_member_id varchar(36) not null,
                         content varchar(200) not null,
                         url varchar(255),
                         primary key (message_id)
);

create table opening_hours (
                               close_time time(6),
                               is_holiday boolean not null,
                               open_time time(6),
                               weekday varchar(10) not null check (weekday in ('MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY','SUNDAY')),
                               cafe_id varchar(36) not null,
                               primary key (cafe_id)
);

create table report (
                        content_type varchar(16) not null check (content_type in ('MEMBER','FEED','CAFE','MENU','COMMENT')),
                        content_id varchar(36) not null,
                        member_id varchar(36) not null,
                        report_id varchar(36) not null,
                        primary key (report_id)
);

create table seller_apply (
                              apply_id varchar(36) not null,
                              cafe_id varchar(36) not null unique,
                              member_id varchar(36) not null,
                              image_url varchar(255) not null,
                              primary key (apply_id)
);

create table seller_info (
                             seller_info_id bigserial not null,
                             cafe_id varchar(36) not null,
                             member_id varchar(36) not null,
                             primary key (seller_info_id),
                             unique (member_id, cafe_id)
);

create table tag (
                     feed_id varchar(36) not null,
                     tag_id varchar(36) not null,
                     tag_name varchar(45) not null check (tag_name in ('CLEAN','KIND','GOOD_INTERIOR','GOOD_COFFEE','MANY_SEATS','GOOD_DESSERT','GOOD_VIEW')),
                     primary key (tag_id)
);
-- Member
INSERT INTO member (member_id, email, nickname, password, role, is_deleted, phone_num, created_at, updated_at)
VALUES ('1', 'test41@test.com', 'member1', 'password1', 'MEMBER', FALSE, '1234567890', now(), now()),
       ('2', 'test51@test.com', 'member2', 'password2', 'MEMBER', FALSE, '0987654321', now(), now());

-- Cafe
INSERT INTO cafe (cafe_id, name, address, phone_num, average_grade, reg_image_url, created_at, updated_at)
VALUES ('3', 'Cafe A', '123 Street A', '1112223333', 4, 'https://example.com/cafeA.jpg', now(), now()),
       ('4', 'Cafe B', '456 Street B', '4445556666', 5, 'https://example.com/cafeB.jpg', now(), now());

-- Feed
INSERT INTO feed (feed_id, member_id, cafe_id, content, is_visible, grade, rank, created_at, updated_at)
VALUES
    ('5', '1', '3', 'Great coffee at Cafe A!', TRUE, 4, 0, now(), now()),
    ('6', '2', '4', 'Lovely ambiance at Cafe B!', TRUE, 5, 0, now(), now()),
    ('32', '1', '3', 'Amazing pastries at Cafe C!', TRUE, 4, 0, now(), now()),
    ('33', '2', '4', 'Excellent service at Cafe D!', TRUE, 5, 0, now(), now()),
    ('34', '1', '3', 'Cozy atmosphere at Cafe E!', TRUE, 4, 0, now(), now()),
    ('35', '2', '4', 'Delicious sandwiches at Cafe F!', TRUE, 5, 0, now(), now()),
    ('36', '1', '3', 'Friendly staff at Cafe G!', TRUE, 4, 0, now(), now()),
    ('37', '2', '4', 'Beautiful decor at Cafe H!', TRUE, 5, 0, now(), now()),
    ('38', '1', '3', 'Great music at Cafe I!', TRUE, 4, 0, now(), now()),
    ('39', '2', '4', 'Amazing desserts at Cafe J!', TRUE, 5, 0, now(), now()),
    ('40', '1', '3', 'Wonderful view at Cafe K!', TRUE, 4, 0, now(), now()),
    ('41', '2', '4', 'Fantastic drinks at Cafe L!', TRUE, 5, 0, now(), now()),
    ('42', '1', '3', 'Lovely garden at Cafe M!', TRUE, 4, 0, now(), now()),
    ('43', '2', '4', 'Charming staff at Cafe N!', TRUE, 5, 0, now(), now()),
    ('44', '1', '3', 'Delightful pastries at Cafe O!', TRUE, 4, 0, now(), now()),
    ('45', '2', '4', 'Cozy corners at Cafe P!', TRUE, 5, 0, now(), now()),
    ('46', '1', '3', 'Tasty treats at Cafe Q!', TRUE, 4, 0, now(), now()),
    ('47', '2', '4', 'Warm atmosphere at Cafe R!', TRUE, 5, 0, now(), now()),
    ('48', '1', '3', 'Exciting menu at Cafe S!', TRUE, 4, 0, now(), now()),
    ('49', '2', '4', 'Scenic location at Cafe T!', TRUE, 5, 0, now(), now()),
    ('50', '1', '3', 'Great company at Cafe U!', TRUE, 4, 0, now(), now()),
    ('51', '2', '4', 'Innovative drinks at Cafe V!', TRUE, 5, 0, now(), now()),
    ('52', '1', '3', 'Delicious pastries at Cafe W!', TRUE, 4, 0, now(), now()),
    ('53', '2', '4', 'Warm welcome at Cafe X!', TRUE, 5, 0, now(), now()),
    ('54', '1', '3', 'Relaxing ambiance at Cafe Y!', TRUE, 4, 0, now(), now()),
    ('55', '2', '4', 'Friendly service at Cafe Z!', TRUE, 5, 0, now(), now()),
    ('56', '1', '3', 'Artistic decor at Cafe AA!', TRUE, 4, 0, now(), now()),
    ('57', '2', '4', 'Fantastic coffee at Cafe AB!', TRUE, 5, 0, now(), now()),
    ('58', '1', '3', 'Cozy environment at Cafe AC!', TRUE, 4, 0, now(), now()),
    ('59', '2', '4', 'Refreshing drinks at Cafe AD!', TRUE, 5, 0, now(), now()),
    ('60', '1', '3', 'Delightful snacks at Cafe AE!', TRUE, 4, 0, now(), now()),
    ('61', '2', '4', 'Friendly atmosphere at Cafe AF!', TRUE, 5, 0, now(), now());


-- Comment
INSERT INTO comment (comment_id, parent_comment_id, feed_id, member_id, content, is_visible, created_at, updated_at)
VALUES
    ('7', NULL, '5', '2', 'I agree, Cafe A is fantastic!', TRUE, now(), now()),
    ('8', NULL, '5', '1', 'Thanks for your comment!', TRUE, now(), now()),
    ('9', '8', '5', '1', 'I had a great time at Cafe B too!', TRUE, now(), now()),
    ('10', NULL, '5', '2', 'Glad to hear that! Cafe B is my favorite spot.', TRUE, now(), now()),
    ('11', NULL, '6', '2', 'Cafe As latte is amazing!', TRUE, now(), now()),
    ('12', '11', '6', '1', 'Yes, their latte art is always on point!', TRUE, now(), now()),
    ('13', '11', '6', '1', 'Has anyone tried Cafe Bs pastries?', TRUE, now(), now()),
    ('14', '11', '6', '2', 'Yes, they are delicious! Especially the croissants.', TRUE, now(), now());

-- Tag
INSERT INTO tag (tag_id, feed_id, tag_name)
VALUES ('15', '5', 'CLEAN'),
       ('16', '5', 'GOOD_COFFEE'),

       ('18', '6', 'KIND'),
       ('19', '6', 'GOOD_INTERIOR'),
       ('20', '6', 'GOOD_DESSERT'),

       ('62', '32', 'MANY_SEATS'),
       ('63', '32', 'FAST_WIFI'),
       ('64', '32', 'GOOD_FOR_GROUPS'),

       ('65', '33', 'PARKING_AVAILABLE'),
       ('66', '33', 'PET_FRIENDLY'),
       ('67', '33', 'GOOD_MUSIC'),

       ('68', '34', 'FAMILY_FRIENDLY'),
       ('69', '34', 'QUIET'),

       ('70', '35', 'ACCESSIBLE'),
       ('71', '35', 'GOOD_FOR_DATES'),

       ('72', '36', 'HEALTHY_OPTIONS'),
       ('73', '36', 'OPEN_LATE'),

       ('74', '37', 'HAS_TERRACE'),
       ('75', '37', 'HAS_TV'),

       ('76', '38', 'SMOKING_AREA'),

       ('77', '39', 'GOOD_COFFEE'),
       ('78', '39', 'GOOD_INTERIOR'),
       ('79', '39', 'GOOD_DESSERT'),

       ('80', '40', 'GOOD_VIEW'),
       ('81', '40', 'FAST_WIFI'),

       ('82', '41', 'KIND'),
       ('83', '41', 'FAMILY_FRIENDLY'),

       ('84', '42', 'HEALTHY_OPTIONS'),
       ('85', '42', 'GOOD_FOR_GROUPS'),
       ('86', '42', 'GOOD_FOR_DATES'),

       ('87', '43', 'OPEN_LATE'),
       ('88', '43', 'HAS_TERRACE'),
       ('89', '43', 'GOOD_COFFEE'),

       ('90', '44', 'PET_FRIENDLY'),
       ('91', '44', 'GOOD_VIEW'),
       ('92', '44', 'GOOD_DESSERT'),

       ('93', '45', 'MANY_SEATS'),
       ('94', '45', 'GOOD_MUSIC'),

       ('95', '46', 'ACCESSIBLE'),
       ('96', '46', 'QUIET'),

       ('97', '47', 'HEALTHY_OPTIONS'),
       ('98', '47', 'GOOD_FOR_DATES'),

       ('99', '48', 'KIND'),
       ('100', '48', 'FAMILY_FRIENDLY'),
       ('101', '48', 'GOOD_MUSIC'),

       ('102', '49', 'GOOD_COFFEE'),
       ('103', '49', 'GOOD_INTERIOR'),
       ('104', '49', 'GOOD_DESSERT'),

       ('105', '50', 'GOOD_VIEW'),
       ('106', '50', 'FAST_WIFI'),

       ('107', '51', 'KIND'),
       ('108', '51', 'FAMILY_FRIENDLY'),

       ('109', '52', 'HEALTHY_OPTIONS'),
       ('110', '52', 'GOOD_FOR_GROUPS'),
       ('111', '52', 'GOOD_FOR_DATES'),

       ('112', '53', 'OPEN_LATE'),
       ('113', '53', 'HAS_TERRACE'),
       ('114', '53', 'GOOD_COFFEE'),

       ('115', '54', 'PET_FRIENDLY'),
       ('116', '54', 'GOOD_VIEW'),
       ('117', '54', 'GOOD_DESSERT'),

       ('118', '55', 'MANY_SEATS'),
       ('119', '55', 'GOOD_MUSIC'),

       ('120', '56', 'ACCESSIBLE'),
       ('121', '56', 'QUIET'),

       ('122', '57', 'HEALTHY_OPTIONS'),
       ('123', '57', 'GOOD_FOR_DATES'),

       ('124', '58', 'KIND'),
       ('125', '58', 'FAMILY_FRIENDLY'),
       ('126', '58', 'GOOD_MUSIC'),

       ('127', '59', 'GOOD_COFFEE'),
       ('128', '59', 'GOOD_INTERIOR'),
       ('129', '59', 'GOOD_DESSERT'),

       ('130', '60', 'GOOD_VIEW'),
       ('131', '60', 'FAST_WIFI'),

       ('132', '61', 'KIND'),
       ('133', '61', 'FAMILY_FRIENDLY'),
       ('134', '61', 'GOOD_MUSIC');

INSERT INTO heart (heart_id, member_id, content_id, content_type)
VALUES ('21', '1', '5', 'FEED'),
       ('22', '1', '7', 'COMMENT'),
       ('23', '1', '8', 'COMMENT'),
       ('24', '1', '11', 'COMMENT'),
       ('25', '1', '14', 'COMMENT'),
       ('26', '2', '5', 'FEED'),
       ('27', '2', '6', 'FEED'),
       ('28', '2', '7', 'COMMENT'),
       ('29', '2', '10', 'COMMENT'),
       ('30', '2', '11', 'COMMENT'),
       ('31', '2', '12', 'COMMENT');



INSERT INTO member (is_deleted, created_at, updated_at, phone_num, role, member_id, email, nickname, password) VALUES (false, '2024-05-07 21:25:01.629814', '2024-05-07 21:25:01.629814', '01012345678', 'MEMBER', 'f35fa626-20a6-498d-b50f-6300ac8ea46d', 'test@test.com', 'test-user-1', '$2a$10$1idDJluGsoDvNvQcphRnoOIkkyNuQVQxO.cVK9.wwQaSWnTPr30dS');
INSERT INTO member (is_deleted, created_at, updated_at, phone_num, role, member_id, email, nickname, password) VALUES (false, '2024-05-07 21:25:01.738812', '2024-05-07 21:25:01.738812', '01012345678', 'VERIFIED_MEMBER', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75', 'test1@test.com', 'test-user-2', '$2a$10$A958WxWnZoPJtU0eeNDfce/dy7rh6sd2cLLbmDZ5wldiMtlwkeyHC');
INSERT INTO member (is_deleted, created_at, updated_at, phone_num, role, member_id, email, nickname, password) VALUES (false, '2024-05-07 21:25:01.80533', '2024-05-07 21:25:01.80533', '01012345678', 'VERIFIED_MEMBER', '91240395-40f8-429b-bda1-ac2cc1856b1e', 'test2@test.com', 'test-user-3', '$2a$10$maiB33WdMaUwXltQ39LKOOs5rzF3w6oHkiNKPkDI4j5MZziB1FYuW');
INSERT INTO member (is_deleted, created_at, updated_at, phone_num, role, member_id, email, nickname, password) VALUES (false, '2024-05-07 21:25:01.869333', '2024-05-07 21:25:01.869333', '01012345678', 'MEMBER', '03f97b2a-961c-4b77-b22d-26aa758cb117', 'test3@test.com', 'test-user-4', '$2a$10$u.SmtT9ApL0ybTf4BW86Ou/0LiPiho3qfxXtSTcEgJ9kJ37OdD8Zi');
INSERT INTO member (is_deleted, created_at, updated_at, phone_num, role, member_id, email, nickname, password) VALUES (false, '2024-05-07 21:25:01.934329', '2024-05-07 21:25:01.934329', '01012345678', 'VERIFIED_MEMBER', 'f54e2074-fbff-4527-8a75-48363c3e7633', 'test4@test.com', 'test-user-5', '$2a$10$DfjBz0actiX9wPy6FeqhEuvNE9PiSSDkens7wHBaBBKRTFhd8lvna');
INSERT INTO member (is_deleted, created_at, updated_at, phone_num, role, member_id, email, nickname, password) VALUES (false, '2024-05-07 21:25:02.00333', '2024-05-07 21:25:02.00333', '01012345678', 'SELLER', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', 'test5@test.com', 'test-user-6', '$2a$10$6ZWUZVztT5FHv6nVgR.xzeNAocJHrvsX5XhkHpw6QDRlEytdYKLde');
INSERT INTO member (is_deleted, created_at, updated_at, phone_num, role, member_id, email, nickname, password) VALUES (false, '2024-05-07 21:25:02.066331', '2024-05-07 21:25:02.066331', '01012345678', 'MEMBER', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', 'test6@test.com', 'test-user-7', '$2a$10$SynV9iAhul.DKB4ql99WWO/l8BO87rV3Rt/sDUSh4pDKjckfM1mWi');
INSERT INTO member (is_deleted, created_at, updated_at, phone_num, role, member_id, email, nickname, password) VALUES (false, '2024-05-07 21:25:02.130842', '2024-05-07 21:25:02.130842', '01012345678', 'SELLER', '34d12937-5e09-45b9-a413-9b92568ac020', 'test7@test.com', 'test-user-8', '$2a$10$VOCrAf/9GqUlwp.QqwlHW.fiWwasAZyPA.E8UwvphWiGDI9eo9.o2');
INSERT INTO member (is_deleted, created_at, updated_at, phone_num, role, member_id, email, nickname, password) VALUES (false, '2024-05-07 21:25:02.193355', '2024-05-07 21:25:02.193355', '01012345678', 'MEMBER', '6cff717b-4647-4383-aa34-903e1ea9904b', 'test8@test.com', 'test-user-9', '$2a$10$DVaHok6SMJwAm8LLXmFwnOBjEuvo6vXZebwIgmzTGwKmU/4g5lX1K');
INSERT INTO member (is_deleted, created_at, updated_at, phone_num, role, member_id, email, nickname, password) VALUES (false, '2024-05-07 21:25:02.256867', '2024-05-07 21:25:02.256867', '01012345678', 'ADMIN', '57a63cde-43ff-49f3-a754-2b384f99c6b6', 'test9@test.com', 'test-user-10', '$2a$10$O7MwtwNY1ym0sHws759V.egZU97IdWGinULfy2sShxwRPu.njGaHu');
INSERT INTO member (is_deleted, created_at, updated_at, phone_num, role, member_id, email, nickname, password) VALUES (false, '2024-05-07 21:25:02.319867', '2024-05-07 21:25:02.319867', '01012345678', 'MEMBER', 'c4e3d9bd-e7b5-4974-85fc-8e334f3d2dd4', 'test10@test.com', 'test-user-11', '$2a$10$hmgKTVFBHHI1Ct0tSN6d.eHfHgJ6snOcqrN7pAQU49NjWhMagp/Be');
INSERT INTO member (is_deleted, created_at, updated_at, phone_num, role, member_id, email, nickname, password) VALUES (false, '2024-05-07 21:25:02.38387', '2024-05-07 21:25:02.38387', '01012345678', 'ADMIN', '557c7c44-20e8-4c31-8862-c3ad00f7f002', 'test11@test.com', 'test-user-12', '$2a$10$DIw4R0oZ8WIGGxjXvfx.oOqc2cX7yT4eT3B5tMRu9u/AlNrZjDwjS');


INSERT INTO blacklist (blacklist_id, blocked_member_id, member_id) VALUES (1, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
INSERT INTO blacklist (blacklist_id, blocked_member_id, member_id) VALUES (2, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
INSERT INTO blacklist (blacklist_id, blocked_member_id, member_id) VALUES (3, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
INSERT INTO blacklist (blacklist_id, blocked_member_id, member_id) VALUES (4, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
INSERT INTO blacklist (blacklist_id, blocked_member_id, member_id) VALUES (5, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', '91240395-40f8-429b-bda1-ac2cc1856b1e');
INSERT INTO blacklist (blacklist_id, blocked_member_id, member_id) VALUES (6, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', '91240395-40f8-429b-bda1-ac2cc1856b1e');
INSERT INTO blacklist (blacklist_id, blocked_member_id, member_id) VALUES (7, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', '03f97b2a-961c-4b77-b22d-26aa758cb117');
INSERT INTO blacklist (blacklist_id, blocked_member_id, member_id) VALUES (8, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', '03f97b2a-961c-4b77-b22d-26aa758cb117');
INSERT INTO blacklist (blacklist_id, blocked_member_id, member_id) VALUES (9, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', 'f54e2074-fbff-4527-8a75-48363c3e7633');
INSERT INTO blacklist (blacklist_id, blocked_member_id, member_id) VALUES (10, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', 'f54e2074-fbff-4527-8a75-48363c3e7633');



INSERT INTO cafe (average_grade, created_at, updated_at, phone_num, cafe_id, reg_image_url, name, address) VALUES (1, '2024-05-07 21:25:02.385868', '2024-05-07 21:25:02.385868', '01012345678', 'cafe1', NULL, '그라인더1', '사랑시 고백구 행복동 794-1');
INSERT INTO cafe (average_grade, created_at, updated_at, phone_num, cafe_id, reg_image_url, name, address) VALUES (3, '2024-05-07 21:25:02.390868', '2024-05-07 21:25:02.390868', '01012345678', 'cafe2', NULL, '그라인더2', '사랑시 고백구 행복동 794-2');
INSERT INTO cafe (average_grade, created_at, updated_at, phone_num, cafe_id, reg_image_url, name, address) VALUES (1, '2024-05-07 21:25:02.391868', '2024-05-07 21:25:02.391868', '01012345678', 'cafe3', NULL, '그라인더3', '사랑시 고백구 행복동 794-3');
INSERT INTO cafe (average_grade, created_at, updated_at, phone_num, cafe_id, reg_image_url, name, address) VALUES (3, '2024-05-07 21:25:02.392869', '2024-05-07 21:25:02.392869', '01012345678', 'cafe4', NULL, '그라인더4', '사랑시 고백구 행복동 794-4');
INSERT INTO cafe (average_grade, created_at, updated_at, phone_num, cafe_id, reg_image_url, name, address) VALUES (4, '2024-05-07 21:25:02.393868', '2024-05-07 21:25:02.393868', '01012345678', 'cafe5', NULL, '그라인더5', '사랑시 고백구 행복동 794-5');
INSERT INTO cafe (average_grade, created_at, updated_at, phone_num, cafe_id, reg_image_url, name, address) VALUES (1, '2024-05-07 21:25:02.394869', '2024-05-07 21:25:02.394869', '01012345678', 'cafe6', NULL, '그라인더6', '사랑시 고백구 행복동 794-6');
INSERT INTO cafe (average_grade, created_at, updated_at, phone_num, cafe_id, reg_image_url, name, address) VALUES (3, '2024-05-07 21:25:02.395869', '2024-05-07 21:25:02.395869', '01012345678', 'cafe7', NULL, '그라인더7', '사랑시 고백구 행복동 794-7');
INSERT INTO cafe (average_grade, created_at, updated_at, phone_num, cafe_id, reg_image_url, name, address) VALUES (5, '2024-05-07 21:25:02.39687', '2024-05-07 21:25:02.39687', '01012345678', 'cafe8', NULL, '그라인더8', '사랑시 고백구 행복동 794-8');
INSERT INTO cafe (average_grade, created_at, updated_at, phone_num, cafe_id, reg_image_url, name, address) VALUES (2, '2024-05-07 21:25:02.397869', '2024-05-07 21:25:02.397869', '01012345678', 'cafe9', NULL, '그라인더9', '사랑시 고백구 행복동 794-9');
INSERT INTO cafe (average_grade, created_at, updated_at, phone_num, cafe_id, reg_image_url, name, address) VALUES (4, '2024-05-07 21:25:02.398868', '2024-05-07 21:25:02.398868', '01012345678', 'cafe10', NULL, '그라인더10', '사랑시 고백구 행복동 794-10');
INSERT INTO cafe (average_grade, created_at, updated_at, phone_num, cafe_id, reg_image_url, name, address) VALUES (2, '2024-05-07 21:25:02.400868', '2024-05-07 21:25:02.400868', '01012345678', 'cafe11', NULL, '그라인더11', '사랑시 고백구 행복동 794-11');




-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (1, 'ba74892d-7bb8-4f3b-8057-2f61d0dfbb98', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (2, '321c587e-db79-40e6-afbf-0353909dfed3', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (3, '87442374-5535-4dbd-8734-a6be70d76175', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (4, '9b4f2b5b-15fa-412a-89c5-a280efeda4d1', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (5, 'b46957ce-6781-4d54-9c49-153ac156c597', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (6, '4e69f096-1704-40e4-8c19-5682efd037a6', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (7, 'd8b77b8e-35e2-43e4-88f1-cc34c0b6a19f', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (8, '1e92854a-cfd6-4055-8e82-cd1cca713d53', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (9, 'd9e4e458-2572-403d-9d2b-e5d5e0834c41', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (10, '965f382b-fa3c-4067-a803-df062c93f04c', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (11, 'ba74892d-7bb8-4f3b-8057-2f61d0dfbb98', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (12, '321c587e-db79-40e6-afbf-0353909dfed3', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (13, '87442374-5535-4dbd-8734-a6be70d76175', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (14, '9b4f2b5b-15fa-412a-89c5-a280efeda4d1', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (15, 'b46957ce-6781-4d54-9c49-153ac156c597', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (16, '4e69f096-1704-40e4-8c19-5682efd037a6', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (17, 'd8b77b8e-35e2-43e4-88f1-cc34c0b6a19f', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (18, '1e92854a-cfd6-4055-8e82-cd1cca713d53', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (19, 'd9e4e458-2572-403d-9d2b-e5d5e0834c41', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (20, '965f382b-fa3c-4067-a803-df062c93f04c', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (21, 'ba74892d-7bb8-4f3b-8057-2f61d0dfbb98', '91240395-40f8-429b-bda1-ac2cc1856b1e');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (22, '321c587e-db79-40e6-afbf-0353909dfed3', '91240395-40f8-429b-bda1-ac2cc1856b1e');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (23, '87442374-5535-4dbd-8734-a6be70d76175', '91240395-40f8-429b-bda1-ac2cc1856b1e');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (24, '9b4f2b5b-15fa-412a-89c5-a280efeda4d1', '91240395-40f8-429b-bda1-ac2cc1856b1e');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (25, 'b46957ce-6781-4d54-9c49-153ac156c597', '91240395-40f8-429b-bda1-ac2cc1856b1e');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (26, '4e69f096-1704-40e4-8c19-5682efd037a6', '91240395-40f8-429b-bda1-ac2cc1856b1e');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (27, 'd8b77b8e-35e2-43e4-88f1-cc34c0b6a19f', '91240395-40f8-429b-bda1-ac2cc1856b1e');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (28, '1e92854a-cfd6-4055-8e82-cd1cca713d53', '91240395-40f8-429b-bda1-ac2cc1856b1e');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (29, 'd9e4e458-2572-403d-9d2b-e5d5e0834c41', '91240395-40f8-429b-bda1-ac2cc1856b1e');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (30, '965f382b-fa3c-4067-a803-df062c93f04c', '91240395-40f8-429b-bda1-ac2cc1856b1e');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (31, 'ba74892d-7bb8-4f3b-8057-2f61d0dfbb98', '03f97b2a-961c-4b77-b22d-26aa758cb117');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (32, '321c587e-db79-40e6-afbf-0353909dfed3', '03f97b2a-961c-4b77-b22d-26aa758cb117');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (33, '87442374-5535-4dbd-8734-a6be70d76175', '03f97b2a-961c-4b77-b22d-26aa758cb117');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (34, '9b4f2b5b-15fa-412a-89c5-a280efeda4d1', '03f97b2a-961c-4b77-b22d-26aa758cb117');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (35, 'b46957ce-6781-4d54-9c49-153ac156c597', '03f97b2a-961c-4b77-b22d-26aa758cb117');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (36, '4e69f096-1704-40e4-8c19-5682efd037a6', '03f97b2a-961c-4b77-b22d-26aa758cb117');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (37, 'd8b77b8e-35e2-43e4-88f1-cc34c0b6a19f', '03f97b2a-961c-4b77-b22d-26aa758cb117');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (38, '1e92854a-cfd6-4055-8e82-cd1cca713d53', '03f97b2a-961c-4b77-b22d-26aa758cb117');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (39, 'd9e4e458-2572-403d-9d2b-e5d5e0834c41', '03f97b2a-961c-4b77-b22d-26aa758cb117');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (40, '965f382b-fa3c-4067-a803-df062c93f04c', '03f97b2a-961c-4b77-b22d-26aa758cb117');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (41, 'ba74892d-7bb8-4f3b-8057-2f61d0dfbb98', 'f54e2074-fbff-4527-8a75-48363c3e7633');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (42, '321c587e-db79-40e6-afbf-0353909dfed3', 'f54e2074-fbff-4527-8a75-48363c3e7633');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (43, '87442374-5535-4dbd-8734-a6be70d76175', 'f54e2074-fbff-4527-8a75-48363c3e7633');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (44, '9b4f2b5b-15fa-412a-89c5-a280efeda4d1', 'f54e2074-fbff-4527-8a75-48363c3e7633');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (45, 'b46957ce-6781-4d54-9c49-153ac156c597', 'f54e2074-fbff-4527-8a75-48363c3e7633');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (46, '4e69f096-1704-40e4-8c19-5682efd037a6', 'f54e2074-fbff-4527-8a75-48363c3e7633');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (47, 'd8b77b8e-35e2-43e4-88f1-cc34c0b6a19f', 'f54e2074-fbff-4527-8a75-48363c3e7633');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (48, '1e92854a-cfd6-4055-8e82-cd1cca713d53', 'f54e2074-fbff-4527-8a75-48363c3e7633');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (49, 'd9e4e458-2572-403d-9d2b-e5d5e0834c41', 'f54e2074-fbff-4527-8a75-48363c3e7633');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (50, '965f382b-fa3c-4067-a803-df062c93f04c', 'f54e2074-fbff-4527-8a75-48363c3e7633');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (51, 'ba74892d-7bb8-4f3b-8057-2f61d0dfbb98', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (52, '321c587e-db79-40e6-afbf-0353909dfed3', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (53, '87442374-5535-4dbd-8734-a6be70d76175', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (54, '9b4f2b5b-15fa-412a-89c5-a280efeda4d1', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (55, 'b46957ce-6781-4d54-9c49-153ac156c597', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (56, '4e69f096-1704-40e4-8c19-5682efd037a6', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (57, 'd8b77b8e-35e2-43e4-88f1-cc34c0b6a19f', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (58, '1e92854a-cfd6-4055-8e82-cd1cca713d53', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (59, 'd9e4e458-2572-403d-9d2b-e5d5e0834c41', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (60, '965f382b-fa3c-4067-a803-df062c93f04c', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (61, 'ba74892d-7bb8-4f3b-8057-2f61d0dfbb98', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (62, '321c587e-db79-40e6-afbf-0353909dfed3', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (63, '87442374-5535-4dbd-8734-a6be70d76175', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (64, '9b4f2b5b-15fa-412a-89c5-a280efeda4d1', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (65, 'b46957ce-6781-4d54-9c49-153ac156c597', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (66, '4e69f096-1704-40e4-8c19-5682efd037a6', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (67, 'd8b77b8e-35e2-43e4-88f1-cc34c0b6a19f', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (68, '1e92854a-cfd6-4055-8e82-cd1cca713d53', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (69, 'd9e4e458-2572-403d-9d2b-e5d5e0834c41', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (70, '965f382b-fa3c-4067-a803-df062c93f04c', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (71, 'ba74892d-7bb8-4f3b-8057-2f61d0dfbb98', '34d12937-5e09-45b9-a413-9b92568ac020');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (72, '321c587e-db79-40e6-afbf-0353909dfed3', '34d12937-5e09-45b9-a413-9b92568ac020');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (73, '87442374-5535-4dbd-8734-a6be70d76175', '34d12937-5e09-45b9-a413-9b92568ac020');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (74, '9b4f2b5b-15fa-412a-89c5-a280efeda4d1', '34d12937-5e09-45b9-a413-9b92568ac020');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (75, 'b46957ce-6781-4d54-9c49-153ac156c597', '34d12937-5e09-45b9-a413-9b92568ac020');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (76, '4e69f096-1704-40e4-8c19-5682efd037a6', '34d12937-5e09-45b9-a413-9b92568ac020');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (77, 'd8b77b8e-35e2-43e4-88f1-cc34c0b6a19f', '34d12937-5e09-45b9-a413-9b92568ac020');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (78, '1e92854a-cfd6-4055-8e82-cd1cca713d53', '34d12937-5e09-45b9-a413-9b92568ac020');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (79, 'd9e4e458-2572-403d-9d2b-e5d5e0834c41', '34d12937-5e09-45b9-a413-9b92568ac020');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (80, '965f382b-fa3c-4067-a803-df062c93f04c', '34d12937-5e09-45b9-a413-9b92568ac020');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (81, 'ba74892d-7bb8-4f3b-8057-2f61d0dfbb98', '6cff717b-4647-4383-aa34-903e1ea9904b');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (82, '321c587e-db79-40e6-afbf-0353909dfed3', '6cff717b-4647-4383-aa34-903e1ea9904b');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (83, '87442374-5535-4dbd-8734-a6be70d76175', '6cff717b-4647-4383-aa34-903e1ea9904b');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (84, '9b4f2b5b-15fa-412a-89c5-a280efeda4d1', '6cff717b-4647-4383-aa34-903e1ea9904b');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (85, 'b46957ce-6781-4d54-9c49-153ac156c597', '6cff717b-4647-4383-aa34-903e1ea9904b');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (86, '4e69f096-1704-40e4-8c19-5682efd037a6', '6cff717b-4647-4383-aa34-903e1ea9904b');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (87, 'd8b77b8e-35e2-43e4-88f1-cc34c0b6a19f', '6cff717b-4647-4383-aa34-903e1ea9904b');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (88, '1e92854a-cfd6-4055-8e82-cd1cca713d53', '6cff717b-4647-4383-aa34-903e1ea9904b');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (89, 'd9e4e458-2572-403d-9d2b-e5d5e0834c41', '6cff717b-4647-4383-aa34-903e1ea9904b');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (90, '965f382b-fa3c-4067-a803-df062c93f04c', '6cff717b-4647-4383-aa34-903e1ea9904b');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (91, 'ba74892d-7bb8-4f3b-8057-2f61d0dfbb98', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (92, '321c587e-db79-40e6-afbf-0353909dfed3', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (93, '87442374-5535-4dbd-8734-a6be70d76175', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (94, '9b4f2b5b-15fa-412a-89c5-a280efeda4d1', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (95, 'b46957ce-6781-4d54-9c49-153ac156c597', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (96, '4e69f096-1704-40e4-8c19-5682efd037a6', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (97, 'd8b77b8e-35e2-43e4-88f1-cc34c0b6a19f', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (98, '1e92854a-cfd6-4055-8e82-cd1cca713d53', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (99, 'd9e4e458-2572-403d-9d2b-e5d5e0834c41', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
-- INSERT INTO bookmark (bookmark_id, cafe_id, member_id) VALUES (100, '965f382b-fa3c-4067-a803-df062c93f04c', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
--



INSERT INTO feed (grade, is_visible, rank, created_at, updated_at, cafe_id, feed_id, member_id, content) VALUES (3, true, 0, '2024-05-07 21:25:02.4999', '2024-05-07 21:25:02.4999', 'cafe1', 'feed1', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75', '내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~');
INSERT INTO feed (grade, is_visible, rank, created_at, updated_at, cafe_id, feed_id, member_id, content) VALUES (2, true, 0, '2024-05-07 21:25:02.492899', '2024-05-07 21:25:02.492899', 'cafe2', 'feed2', 'f35fa626-20a6-498d-b50f-6300ac8ea46d', '내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~');
INSERT INTO feed (grade, is_visible, rank, created_at, updated_at, cafe_id, feed_id, member_id, content) VALUES (5, true, 0, '2024-05-07 21:25:02.504901', '2024-05-07 21:25:02.504901', 'cafe3', 'feed3', '91240395-40f8-429b-bda1-ac2cc1856b1e', '내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~');
INSERT INTO feed (grade, is_visible, rank, created_at, updated_at, cafe_id, feed_id, member_id, content) VALUES (4, true, 0, '2024-05-07 21:25:02.5109', '2024-05-07 21:25:02.5109', 'cafe4', 'feed4', '03f97b2a-961c-4b77-b22d-26aa758cb117', '내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~');
INSERT INTO feed (grade, is_visible, rank, created_at, updated_at, cafe_id, feed_id, member_id, content) VALUES (1, true, 0, '2024-05-07 21:25:02.5139', '2024-05-07 21:25:02.5139', 'cafe5', 'feed5', 'f54e2074-fbff-4527-8a75-48363c3e7633', '내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~');
INSERT INTO feed (grade, is_visible, rank, created_at, updated_at, cafe_id, feed_id, member_id, content) VALUES (3, true, 0, '2024-05-07 21:25:02.5169', '2024-05-07 21:25:02.5169', 'cafe6', 'feed6', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', '내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~');
INSERT INTO feed (grade, is_visible, rank, created_at, updated_at, cafe_id, feed_id, member_id, content) VALUES (3, true, 0, '2024-05-07 21:25:02.519899', '2024-05-07 21:25:02.519899', 'cafe7', 'feed7', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', '내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~');
INSERT INTO feed (grade, is_visible, rank, created_at, updated_at, cafe_id, feed_id, member_id, content) VALUES (0, true, 0, '2024-05-07 21:25:02.523902', '2024-05-07 21:25:02.523902', 'cafe8', 'feed8', '34d12937-5e09-45b9-a413-9b92568ac020', '내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~');
INSERT INTO feed (grade, is_visible, rank, created_at, updated_at, cafe_id, feed_id, member_id, content) VALUES (0, true, 0, '2024-05-07 21:25:02.5259', '2024-05-07 21:25:02.5259', 'cafe9', 'feed9', '6cff717b-4647-4383-aa34-903e1ea9904b', '내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~');
INSERT INTO feed (grade, is_visible, rank, created_at, updated_at, cafe_id, feed_id, member_id, content) VALUES (1, true, 0, '2024-05-07 21:25:02.528899', '2024-05-07 21:25:02.528899', 'cafe10', 'feed10', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~');
INSERT INTO feed (grade, is_visible, rank, created_at, updated_at, cafe_id, feed_id, member_id, content) VALUES (4, true, 0, '2024-05-07 21:25:02.5309', '2024-05-07 21:25:02.5309', 'cafe11', 'feed11', 'c4e3d9bd-e7b5-4974-85fc-8e334f3d2dd4', '내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용내용입니다~내용cafe');

INSERT INTO follow (follow_id, following_id, member_id) VALUES (1, 'f35fa626-20a6-498d-b50f-6300ac8ea46d', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (2, 'f35fa626-20a6-498d-b50f-6300ac8ea46d', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (3, 'f35fa626-20a6-498d-b50f-6300ac8ea46d', '91240395-40f8-429b-bda1-ac2cc1856b1e');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (4, 'f35fa626-20a6-498d-b50f-6300ac8ea46d', '03f97b2a-961c-4b77-b22d-26aa758cb117');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (5, 'f35fa626-20a6-498d-b50f-6300ac8ea46d', 'f54e2074-fbff-4527-8a75-48363c3e7633');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (6, 'f35fa626-20a6-498d-b50f-6300ac8ea46d', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (7, 'f35fa626-20a6-498d-b50f-6300ac8ea46d', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (8, 'f35fa626-20a6-498d-b50f-6300ac8ea46d', '34d12937-5e09-45b9-a413-9b92568ac020');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (9, 'f35fa626-20a6-498d-b50f-6300ac8ea46d', '6cff717b-4647-4383-aa34-903e1ea9904b');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (10, 'f35fa626-20a6-498d-b50f-6300ac8ea46d', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (11, 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (12, 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (13, 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75', '91240395-40f8-429b-bda1-ac2cc1856b1e');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (14, 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75', '03f97b2a-961c-4b77-b22d-26aa758cb117');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (15, 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75', 'f54e2074-fbff-4527-8a75-48363c3e7633');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (16, 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (17, 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (18, 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75', '34d12937-5e09-45b9-a413-9b92568ac020');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (19, 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75', '6cff717b-4647-4383-aa34-903e1ea9904b');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (20, 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (21, '91240395-40f8-429b-bda1-ac2cc1856b1e', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (22, '91240395-40f8-429b-bda1-ac2cc1856b1e', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (23, '91240395-40f8-429b-bda1-ac2cc1856b1e', '91240395-40f8-429b-bda1-ac2cc1856b1e');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (24, '91240395-40f8-429b-bda1-ac2cc1856b1e', '03f97b2a-961c-4b77-b22d-26aa758cb117');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (25, '91240395-40f8-429b-bda1-ac2cc1856b1e', 'f54e2074-fbff-4527-8a75-48363c3e7633');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (26, '91240395-40f8-429b-bda1-ac2cc1856b1e', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (27, '91240395-40f8-429b-bda1-ac2cc1856b1e', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (28, '91240395-40f8-429b-bda1-ac2cc1856b1e', '34d12937-5e09-45b9-a413-9b92568ac020');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (29, '91240395-40f8-429b-bda1-ac2cc1856b1e', '6cff717b-4647-4383-aa34-903e1ea9904b');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (30, '91240395-40f8-429b-bda1-ac2cc1856b1e', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (31, '03f97b2a-961c-4b77-b22d-26aa758cb117', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (32, '03f97b2a-961c-4b77-b22d-26aa758cb117', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (33, '03f97b2a-961c-4b77-b22d-26aa758cb117', '91240395-40f8-429b-bda1-ac2cc1856b1e');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (34, '03f97b2a-961c-4b77-b22d-26aa758cb117', '03f97b2a-961c-4b77-b22d-26aa758cb117');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (35, '03f97b2a-961c-4b77-b22d-26aa758cb117', 'f54e2074-fbff-4527-8a75-48363c3e7633');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (36, '03f97b2a-961c-4b77-b22d-26aa758cb117', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (37, '03f97b2a-961c-4b77-b22d-26aa758cb117', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (38, '03f97b2a-961c-4b77-b22d-26aa758cb117', '34d12937-5e09-45b9-a413-9b92568ac020');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (39, '03f97b2a-961c-4b77-b22d-26aa758cb117', '6cff717b-4647-4383-aa34-903e1ea9904b');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (40, '03f97b2a-961c-4b77-b22d-26aa758cb117', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (41, 'f54e2074-fbff-4527-8a75-48363c3e7633', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (42, 'f54e2074-fbff-4527-8a75-48363c3e7633', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (43, 'f54e2074-fbff-4527-8a75-48363c3e7633', '91240395-40f8-429b-bda1-ac2cc1856b1e');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (44, 'f54e2074-fbff-4527-8a75-48363c3e7633', '03f97b2a-961c-4b77-b22d-26aa758cb117');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (45, 'f54e2074-fbff-4527-8a75-48363c3e7633', 'f54e2074-fbff-4527-8a75-48363c3e7633');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (46, 'f54e2074-fbff-4527-8a75-48363c3e7633', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (47, 'f54e2074-fbff-4527-8a75-48363c3e7633', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (48, 'f54e2074-fbff-4527-8a75-48363c3e7633', '34d12937-5e09-45b9-a413-9b92568ac020');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (49, 'f54e2074-fbff-4527-8a75-48363c3e7633', '6cff717b-4647-4383-aa34-903e1ea9904b');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (50, 'f54e2074-fbff-4527-8a75-48363c3e7633', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (51, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (52, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (53, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', '91240395-40f8-429b-bda1-ac2cc1856b1e');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (54, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', '03f97b2a-961c-4b77-b22d-26aa758cb117');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (55, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', 'f54e2074-fbff-4527-8a75-48363c3e7633');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (56, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (57, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (58, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', '34d12937-5e09-45b9-a413-9b92568ac020');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (59, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', '6cff717b-4647-4383-aa34-903e1ea9904b');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (60, 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (61, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (62, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (63, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', '91240395-40f8-429b-bda1-ac2cc1856b1e');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (64, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', '03f97b2a-961c-4b77-b22d-26aa758cb117');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (65, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', 'f54e2074-fbff-4527-8a75-48363c3e7633');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (66, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (67, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (68, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', '34d12937-5e09-45b9-a413-9b92568ac020');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (69, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', '6cff717b-4647-4383-aa34-903e1ea9904b');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (70, 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (71, '34d12937-5e09-45b9-a413-9b92568ac020', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (72, '34d12937-5e09-45b9-a413-9b92568ac020', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (73, '34d12937-5e09-45b9-a413-9b92568ac020', '91240395-40f8-429b-bda1-ac2cc1856b1e');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (74, '34d12937-5e09-45b9-a413-9b92568ac020', '03f97b2a-961c-4b77-b22d-26aa758cb117');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (75, '34d12937-5e09-45b9-a413-9b92568ac020', 'f54e2074-fbff-4527-8a75-48363c3e7633');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (76, '34d12937-5e09-45b9-a413-9b92568ac020', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (77, '34d12937-5e09-45b9-a413-9b92568ac020', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (78, '34d12937-5e09-45b9-a413-9b92568ac020', '34d12937-5e09-45b9-a413-9b92568ac020');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (79, '34d12937-5e09-45b9-a413-9b92568ac020', '6cff717b-4647-4383-aa34-903e1ea9904b');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (80, '34d12937-5e09-45b9-a413-9b92568ac020', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (81, '6cff717b-4647-4383-aa34-903e1ea9904b', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (82, '6cff717b-4647-4383-aa34-903e1ea9904b', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (83, '6cff717b-4647-4383-aa34-903e1ea9904b', '91240395-40f8-429b-bda1-ac2cc1856b1e');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (84, '6cff717b-4647-4383-aa34-903e1ea9904b', '03f97b2a-961c-4b77-b22d-26aa758cb117');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (85, '6cff717b-4647-4383-aa34-903e1ea9904b', 'f54e2074-fbff-4527-8a75-48363c3e7633');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (86, '6cff717b-4647-4383-aa34-903e1ea9904b', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (87, '6cff717b-4647-4383-aa34-903e1ea9904b', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (88, '6cff717b-4647-4383-aa34-903e1ea9904b', '34d12937-5e09-45b9-a413-9b92568ac020');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (89, '6cff717b-4647-4383-aa34-903e1ea9904b', '6cff717b-4647-4383-aa34-903e1ea9904b');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (90, '6cff717b-4647-4383-aa34-903e1ea9904b', '57a63cde-43ff-49f3-a754-2b384f99c6b6');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (91, '57a63cde-43ff-49f3-a754-2b384f99c6b6', 'f35fa626-20a6-498d-b50f-6300ac8ea46d');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (92, '57a63cde-43ff-49f3-a754-2b384f99c6b6', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (93, '57a63cde-43ff-49f3-a754-2b384f99c6b6', '91240395-40f8-429b-bda1-ac2cc1856b1e');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (94, '57a63cde-43ff-49f3-a754-2b384f99c6b6', '03f97b2a-961c-4b77-b22d-26aa758cb117');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (95, '57a63cde-43ff-49f3-a754-2b384f99c6b6', 'f54e2074-fbff-4527-8a75-48363c3e7633');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (96, '57a63cde-43ff-49f3-a754-2b384f99c6b6', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (97, '57a63cde-43ff-49f3-a754-2b384f99c6b6', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (98, '57a63cde-43ff-49f3-a754-2b384f99c6b6', '34d12937-5e09-45b9-a413-9b92568ac020');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (99, '57a63cde-43ff-49f3-a754-2b384f99c6b6', '6cff717b-4647-4383-aa34-903e1ea9904b');
INSERT INTO follow (follow_id, following_id, member_id) VALUES (100, '57a63cde-43ff-49f3-a754-2b384f99c6b6', '57a63cde-43ff-49f3-a754-2b384f99c6b6');

INSERT INTO report (report_id, member_id, content_id, content_type) VALUES('e9ef779d-0df3-4a63-83ea-7e72616f345c', 'f35fa626-20a6-498d-b50f-6300ac8ea46d', '258471e7-46c7-49a0-bdd9-71ddd9e69603', 'FEED') ;
INSERT INTO report (report_id, member_id, content_id, content_type) VALUES('2edda6a1-3a57-450c-9001-f3ebe9f6386d', 'a220c9af-c785-4a9f-a5fc-ec400dcfdc75', '258471e7-46c7-49a0-bdd9-71ddd9e69603', 'FEED') ;
INSERT INTO report (report_id, member_id, content_id, content_type) VALUES('2c8062a2-5d75-4908-aaa7-f0c8475dff54', '91240395-40f8-429b-bda1-ac2cc1856b1e', '258471e7-46c7-49a0-bdd9-71ddd9e69603', 'FEED') ;
INSERT INTO report (report_id, member_id, content_id, content_type) VALUES('5e23a866-0cc9-4c89-90b8-e472e2db1d45', '03f97b2a-961c-4b77-b22d-26aa758cb117', '258471e7-46c7-49a0-bdd9-71ddd9e69603', 'FEED') ;
INSERT INTO report (report_id, member_id, content_id, content_type) VALUES('6272c955-9cce-4eb0-b962-0bddef3de933', 'f54e2074-fbff-4527-8a75-48363c3e7633', '38d5504f-1bc9-41d7-9e05-0109c84afd28', 'FEED') ;
INSERT INTO report (report_id, member_id, content_id, content_type) VALUES('a5751fa9-77f4-419e-9b25-77da6a3b380d', 'f2a3c0b6-754f-4f4f-90c8-05a5ebaed1d8', '38d5504f-1bc9-41d7-9e05-0109c84afd28', 'FEED') ;
INSERT INTO report (report_id, member_id, content_id, content_type) VALUES('d752b8aa-06d7-4314-b4a3-f65b23a1903c', 'a0c126c6-a39f-48b5-8200-7e6f9c0eacae', '38d5504f-1bc9-41d7-9e05-0109c84afd28', 'FEED') ;
INSERT INTO report (report_id, member_id, content_id, content_type) VALUES('5b7e10bf-7327-4726-8f63-655608a15f03', '34d12937-5e09-45b9-a413-9b92568ac020', '38d5504f-1bc9-41d7-9e05-0109c84afd28', 'FEED') ;
INSERT INTO report (report_id, member_id, content_id, content_type) VALUES('0cbdf6b4-bdd8-44cb-a03d-e6c25e5de087', '6cff717b-4647-4383-aa34-903e1ea9904b', 'e98bf11f-9147-4ff8-9fd7-1ec4d6efa1e6', 'FEED') ;
INSERT INTO report (report_id, member_id, content_id, content_type) VALUES('8e064d01-eca3-4423-a7a3-fb493a70820e', '57a63cde-43ff-49f3-a754-2b384f99c6b6', 'e98bf11f-9147-4ff8-9fd7-1ec4d6efa1e6', 'FEED') ;
INSERT INTO report (report_id, member_id, content_id, content_type) VALUES('8e064d01-eca3-4423-a7a3-fb493a70820a', 'c4e3d9bd-e7b5-4974-85fc-8e334f3d2dd4', 'e98bf11f-9147-4ff8-9fd7-1ec4d6efa1e6', 'FEED') ;

INSERT INTO cafe_register(register_id, member_id, name, address, phone_num) VALUES('5520e978-d6a0-4c07-ac01-e53f1b55a2a4', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '새로운카페1', '서울시 마포구 서교동 123-1', '01096541510');
INSERT INTO cafe_register(register_id, member_id, name, address, phone_num) VALUES('66eb75fe-fcdc-42cd-965f-48387e0fe499', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '새로운카페2', '서울시 마포구 서교동 123-2', '01096541511');
INSERT INTO cafe_register(register_id, member_id, name, address, phone_num) VALUES('24db642d-fc25-4648-94b6-f71c8bc5035f', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '새로운카페3', '서울시 마포구 서교동 123-3', '01096541512');
INSERT INTO cafe_register(register_id, member_id, name, address, phone_num) VALUES('5c8557be-ce59-4192-ad8b-31b3b5ad29e9', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '새로운카페4', '서울시 마포구 서교동 123-4', '01096541513');
INSERT INTO cafe_register(register_id, member_id, name, address, phone_num) VALUES('85959acf-fcd2-487f-a969-4602c5a18334', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '새로운카페5', '서울시 마포구 서교동 123-5', '01096541514');
INSERT INTO cafe_register(register_id, member_id, name, address, phone_num) VALUES('167c0c75-1b72-4653-8fca-7c9a1586396f', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '새로운카페6', '서울시 마포구 서교동 123-6', '01096541515');
INSERT INTO cafe_register(register_id, member_id, name, address, phone_num) VALUES('fdcdb8c8-24a1-4d85-897a-8ecff7a38f09', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '새로운카페7', '서울시 마포구 서교동 123-7', '01096541516');
INSERT INTO cafe_register(register_id, member_id, name, address, phone_num) VALUES('5d8407f9-510c-4ef6-b9ad-dbd43d52641d', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '새로운카페8', '서울시 마포구 서교동 123-8', '01096541517');
INSERT INTO cafe_register(register_id, member_id, name, address, phone_num) VALUES('513f395b-5f97-4515-a38d-0310e68208a8', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '새로운카페9', '서울시 마포구 서교동 123-9', '01096541518');
INSERT INTO cafe_register(register_id, member_id, name, address, phone_num) VALUES('acc1b751-ab04-4c8e-b998-7b975423b834', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '새로운카페10', '서울시 마포구 서교동 123-10', '01096541519');
INSERT INTO cafe_register(register_id, member_id, name, address, phone_num) VALUES('587a0989-c499-4984-9587-359afdd036fe', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '새로운카페11', '서울시 마포구 서교동 123-11', '01096541520');
INSERT INTO cafe_register(register_id, member_id, name, address, phone_num) VALUES('9eb68577-8cd0-4021-8a2e-f7663c2f1b2e', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '새로운카페12', '서울시 마포구 서교동 123-12', '01096541521');
INSERT INTO cafe_register(register_id, member_id, name, address, phone_num) VALUES('bb701e33-ac28-4e42-8f19-a4fce1d9fd70', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '새로운카페13', '서울시 마포구 서교동 123-13', '01096541522');
INSERT INTO cafe_register(register_id, member_id, name, address, phone_num) VALUES('a9a8cc2f-aba8-4d97-82f4-15a71a9f22e4', '57a63cde-43ff-49f3-a754-2b384f99c6b6', '새로운카페14', '서울시 마포구 서교동 123-14', '01096541523');

INSERT INTO seller_apply(apply_id, member_id, cafe_id, reg_image_url) VALUES('41775587-b2a6-4491-91ec-90443cb4424a', '57a63cde-43ff-49f3-a754-2b384f99c6b6', 'cafe1', 'test_image_url-1');
INSERT INTO seller_apply(apply_id, member_id, cafe_id, reg_image_url) VALUES('bb3ca829-e4c7-4aa9-9c50-1616b9a0122c', '57a63cde-43ff-49f3-a754-2b384f99c6b6', 'cafe2', 'test_image_url-2');
INSERT INTO seller_apply(apply_id, member_id, cafe_id, reg_image_url) VALUES('5f08966d-7657-4d3f-8b14-9e6a63560278', '57a63cde-43ff-49f3-a754-2b384f99c6b6', 'cafe3', 'test_image_url-3');
INSERT INTO seller_apply(apply_id, member_id, cafe_id, reg_image_url) VALUES('0c9224d4-7a13-4304-bcad-e7fb35635c85', '57a63cde-43ff-49f3-a754-2b384f99c6b6', 'cafe4', 'test_image_url-4');
INSERT INTO seller_apply(apply_id, member_id, cafe_id, reg_image_url) VALUES('ada50e5e-82f7-4262-b471-eda8bb4a1630', '57a63cde-43ff-49f3-a754-2b384f99c6b6', 'cafe5', 'test_image_url-5');
INSERT INTO seller_apply(apply_id, member_id, cafe_id, reg_image_url) VALUES('2e5c393a-fa1b-4d12-ba5f-cf1a417e7864', '57a63cde-43ff-49f3-a754-2b384f99c6b6', 'cafe6', 'test_image_url-6');
INSERT INTO seller_apply(apply_id, member_id, cafe_id, reg_image_url) VALUES('0738ca25-48d6-4423-824e-515f4b92f694', '91240395-40f8-429b-bda1-ac2cc1856b1e', 'cafe7', 'test_image_url-7');
INSERT INTO seller_apply(apply_id, member_id, cafe_id, reg_image_url) VALUES('fb668e9e-40a8-4142-838c-615f02ffe32d', '91240395-40f8-429b-bda1-ac2cc1856b1e', 'cafe8', 'test_image_url-8');
INSERT INTO seller_apply(apply_id, member_id, cafe_id, reg_image_url) VALUES('c5b23156-e7ad-441f-b648-4ced1580883e', '91240395-40f8-429b-bda1-ac2cc1856b1e', 'cafe9', 'test_image_url-9');
INSERT INTO seller_apply(apply_id, member_id, cafe_id, reg_image_url) VALUES('4ad859dc-0ca6-47e6-ae7a-a89b258e9601', '91240395-40f8-429b-bda1-ac2cc1856b1e', 'cafe10', 'test_image_url-10');
INSERT INTO seller_apply(apply_id, member_id, cafe_id, reg_image_url) VALUES('c902fea1-7db2-4c19-bcf6-0adae953eda6', '91240395-40f8-429b-bda1-ac2cc1856b1e', 'cafe11', 'test_image_url-11');

INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag1', 'feed1', 'CLEAN');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag2', 'feed1', 'KIND');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag3', 'feed1', 'GOOD_INTERIOR');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag4', 'feed2', 'CLEAN');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag5', 'feed2', 'GOOD_COFFEE');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag6', 'feed2', 'GOOD_INTERIOR');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag7', 'feed3', 'CLEAN');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag8', 'feed3', 'GOOD_COFFEE');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag9', 'feed3', 'GOOD_INTERIOR');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag10', 'feed4', 'CLEAN');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag11', 'feed4', 'GOOD_COFFEE');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag12', 'feed4', 'GOOD_INTERIOR');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag13', 'feed5', 'CLEAN');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag14', 'feed5', 'GOOD_VIEW');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag15', 'feed5', 'GOOD_INTERIOR');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag16', 'feed6', 'GOOD_VIEW');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag17', 'feed6', 'KIND');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag18', 'feed6', 'GOOD_INTERIOR');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag19', 'feed7', 'CLEAN');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag20', 'feed7', 'GOOD_VIEW');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag21', 'feed7', 'GOOD_INTERIOR');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag22', 'feed8', 'CLEAN');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag23', 'feed8', 'GOOD_VIEW');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag24', 'feed8', 'GOOD_INTERIOR');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag25', 'feed9', 'FAMILY_FRIENDLY');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag26', 'feed9', 'KIND');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag27', 'feed9', 'GOOD_INTERIOR');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag28', 'feed10', 'CLEAN');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag29', 'feed10', 'FAMILY_FRIENDLY');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag30', 'feed10', 'GOOD_INTERIOR');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag31', 'feed11', 'CLEAN');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag32', 'feed11', 'FAMILY_FRIENDLY');
INSERT INTO tag(tag_id, feed_id, tag_name) VALUES ('tag33', 'feed11', 'GOOD_INTERIOR');
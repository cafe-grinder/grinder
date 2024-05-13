-- Member
INSERT INTO member (member_id, email, nickname, password, role, is_deleted, phone_num, created_at, updated_at)
VALUES ('1', 'test@test.com', 'member1', 'password1', 'MEMBER', FALSE, '1234567890', now(), now()),
       ('2', 'test2@test.com', 'member2', 'password2', 'MEMBER', FALSE, '0987654321', now(), now());

-- Cafe
INSERT INTO cafe (cafe_id, name, address, phone_num, average_grade, reg_image_url, created_at, updated_at)
VALUES ('3', 'Cafe A', '123 Street A', '1112223333', 4, 'https://example.com/cafeA.jpg', now(), now()),
       ('4', 'Cafe B', '456 Street B', '4445556666', 5, 'https://example.com/cafeB.jpg', now(), now());

-- Feed
INSERT INTO feed (feed_id, member_id, cafe_id, content, is_visible, grade, rank, created_at, updated_at)
VALUES ('5', '1', '3', 'Great coffee at Cafe A!', TRUE, 4, 0, now(), now()),
       ('6', '2', '4', 'Lovely ambiance at Cafe B!', TRUE, 5, 0, now(), now()),
       ('32', '1', '4', '테스트 1', TRUE, 5, 0, now(), now()),
       ('33', '1', '4', '테스트 2', TRUE, 5, 0, now(), now()),
       ('34', '1', '4', '테스트 3', TRUE, 5, 0, now(), now()),
       ('35', '1', '4', '테스트 4', TRUE, 5, 0, now(), now()),
       ('36', '1', '4', '테스트 5', TRUE, 5, 0, now(), now()),
       ('37', '1', '4', '테스트 6', TRUE, 5, 0, now(), now()),
       ('38', '1', '4', '테스트 7', TRUE, 5, 0, now(), now()),
       ('39', '1', '4', '테스트 8', TRUE, 5, 0, now(), now()),
       ('40', '1', '4', '테스트 9', TRUE, 5, 0, now(), now()),
       ('41', '1', '4', '테스트 10', TRUE, 5, 0, now(), now()),
       ('42', '1', '4', '테스트 11', TRUE, 5, 0, now(), now()),
       ('43', '1', '4', '테스트 12', TRUE, 5, 0, now(), now()),
       ('44', '1', '4', '테스트 13', TRUE, 5, 0, now(), now()),
       ('45', '1', '4', '테스트 14', TRUE, 5, 0, now(), now()),
       ('46', '1', '4', '테스트 15', TRUE, 5, 0, now(), now()),
       ('47', '1', '4', '테스트 16', TRUE, 5, 0, now(), now()),
       ('48', '1', '4', '테스트 17', TRUE, 5, 0, now(), now()),
       ('49', '1', '4', '테스트 18', TRUE, 5, 0, now(), now()),
       ('50', '1', '4', '테스트 19', TRUE, 5, 0, now(), now()),
       ('51', '1', '4', '테스트 20', TRUE, 5, 0, now(), now()),
       ('52', '1', '4', '테스트 21', TRUE, 5, 0, now(), now()),
       ('53', '1', '4', '테스트 22', TRUE, 5, 0, now(), now()),
       ('54', '1', '4', '테스트 23', TRUE, 5, 0, now(), now()),
       ('55', '1', '4', '테스트 24', TRUE, 5, 0, now(), now()),
       ('56', '1', '4', '테스트 25', TRUE, 5, 0, now(), now()),
       ('57', '1', '4', '테스트 26', TRUE, 5, 0, now(), now()),
       ('58', '1', '4', '테스트 27', TRUE, 5, 0, now(), now()),
       ('59', '1', '4', '테스트 28', TRUE, 5, 0, now(), now()),
       ('60', '1', '4', '테스트 29', TRUE, 5, 0, now(), now()),
       ('61', '1', '4', '테스트 30', TRUE, 5, 0, now(), now()),
       ('62', '1', '4', '테스트 31', TRUE, 5, 0, now(), now()),
       ('63', '1', '4', '테스트 32', TRUE, 5, 0, now(), now()),
       ('64', '1', '4', '테스트 33', TRUE, 5, 0, now(), now()),
       ('65', '1', '4', '테스트 34', TRUE, 5, 0, now(), now()),
       ('66', '1', '4', '테스트 35', TRUE, 5, 0, now(), now()),
       ('67', '1', '4', '테스트 36', TRUE, 5, 0, now(), now());

-- Comment
INSERT INTO comment (comment_id, parent_comment_id, feed_id, member_id, content, is_visible, created_at, updated_at)
VALUES ('7', NULL, '5', '2', 'I agree, Cafe A is fantastic!', TRUE, now(), now()),
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
       ('20', '6', 'GOOD_DESSERT');

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

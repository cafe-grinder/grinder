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
       ('6', '2', '4', 'Lovely ambiance at Cafe B!', TRUE, 5, 0, now(), now());

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

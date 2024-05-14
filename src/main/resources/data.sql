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

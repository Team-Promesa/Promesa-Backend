INSERT INTO MEMBER (MEMBER_ID)
VALUES (1), (2), (3), (4), (5);

INSERT INTO ARTIST (ARTIST_ID, DESCRIPTION, INSTA, NAME, PROFILE_IMAGE_URL, WISH_COUNT, MEMBER_ID)
VALUES
    (1, '임시 작가입니다', 'good_artist', '김작가', 'https://example.com/profile.jpg', 0, 1),
    (2, '유명 도예가', 'pottery_queen', '박작가', 'https://example.com/profile2.jpg', 0, 3),
    (3, '신예 작가', 'fresh_pottery', '신작가', 'https://example.com/profile3.jpg', 0, 4);

INSERT INTO CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_CATEGORY_ID)
VALUES
    (1, '머그컵', NULL),
    (2, '접시', NULL),
    (3, '화병', NULL),
    (4, '빈티지 컵', 1),
    (5, '모던 컵', 1),
    (6, '소접시', 2),
    (7, '대접시', 2),
    (8, '장식용 화병', 3),
    (9, '실사용 화병', 3);

INSERT INTO ITEM (
    ITEM_ID, CREATED_AT, UPDATED_AT, DESCRIPTION, NAME, PRICE, SALE_STATUS, STOCK, WISH_COUNT, ARTIST_ID
)
VALUES
    (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '핸드메이드 컵', '도자기 컵', 10000, 'ON_SALE', 10, 2, 1),
    (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '세련된 접시', '도자기 접시', 15000, 'ON_SALE', 5, 1, 2),
    (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '빈티지 화병', '도자기 화병', 20000, 'ON_SALE', 7, 1, 1),
    (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '봄꽃 느낌 머그', '봄 머그', 12000, 'ON_SALE', 8, 2, 3),
    (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '겨울 느낌 컵', '겨울 컵', 13000, 'ON_SALE', 6, 3, 3),
    (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '흙의 느낌 머그', '어스 컵', 9000, 'ON_SALE', 5, 1, 1),
    (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '간결한 접시', '미니멀 접시', 16000, 'ON_SALE', 3, 0, 2),
    (8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '추상화병', '아트 화병', 22000, 'ON_SALE', 2, 1, 2),
    (9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '투톤 머그', '모던 머그', 11000, 'ON_SALE', 6, 0, 3),
    (10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '라인 컵', '라인 컵', 12500, 'ON_SALE', 4, 0, 1);

INSERT INTO ITEM_IMAGE (ITEM_IMAGE_ID, IMAGE_URL, IS_THUMBNAIL, ITEM_ID)
VALUES
    (1, 'https://example.com/image1.jpg', TRUE, 1),
    (2, 'https://example.com/image2.jpg', TRUE, 2),
    (3, 'https://example.com/image3.jpg', TRUE, 3),
    (4, 'https://example.com/image4.jpg', TRUE, 4),
    (5, 'https://example.com/image5.jpg', TRUE, 5),
    (6, 'https://example.com/image6.jpg', TRUE, 6),
    (7, 'https://example.com/image7.jpg', TRUE, 7),
    (8, 'https://example.com/image8.jpg', TRUE, 8),
    (9, 'https://example.com/image9.jpg', TRUE, 9),
    (10, 'https://example.com/image10.jpg', TRUE, 10);

INSERT INTO WISH (WISH_ID, TARGET_ID, TARGET_TYPE, MEMBER_ID)
VALUES
    (1, 1, 'ITEM', 2),
    (2, 2, 'ITEM', 2),
    (3, 3, 'ITEM', 2),
    (4, 4, 'ITEM', 2),
    (5, 4, 'ITEM', 5),
    (6, 5, 'ITEM', 2),
    (7, 5, 'ITEM', 3),
    (8, 5, 'ITEM', 4),
    (9, 6, 'ITEM', 2),
    (10, 8, 'ITEM', 2);

INSERT INTO ITEM_CATEGORY (ITEM_CATEGORY_ID, CATEGORY_ID, ITEM_ID)
VALUES
    (1, 1, 1),
    (2, 2, 2),
    (3, 3, 3),
    (4, 1, 4),
    (5, 1, 5),
    (6, 1, 6),
    (7, 2, 7),
    (8, 3, 8),
    (9, 1, 9),
    (10, 1, 10);

INSERT INTO EXHIBITION (EXHIBITION_ID, CREATED_AT, UPDATED_AT, DESCRIPTION, TITLE)
VALUES
    (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '따뜻한 봄 작품들', '봄 기획전');

INSERT INTO EXHIBITION_ITEM (EXHIBITION_ITEM_ID, EXHIBITION_ID, ITEM_ID)
VALUES
    (1, 1, 1),
    (2, 1, 2),
    (3, 1, 3),
    (4, 1, 4),
    (5, 1, 5),
    (6, 1, 6),
    (7, 1, 7),
    (8, 1, 8),
    (9, 1, 9),
    (10, 1, 10);

INSERT INTO INQUIRY (INQUIRY_ID, QUESTION, ANSWER, ARTIST_ID)
VALUES (1, '배송은 얼마나 걸리나요?', '약 3~5일 소요됩니다.', 1),
       (2, '포장 상태는 어떤가요?', NULL, 1);
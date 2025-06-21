-- ✅ MEMBER: MEMBER_ID 자동 증가! → name, provider, provider_id 만 넣음
INSERT INTO MEMBER (name, provider, provider_id)
VALUES
    ('테스트유저1', 'kakao', '111'),
    ('테스트유저2', 'kakao', '222'),
    ('테스트유저3', 'kakao', '333'),
    ('테스트유저4', 'kakao', '444'),
    ('테스트유저5', 'kakao', '555');

-- ✅ ARTIST
INSERT INTO ARTIST (DESCRIPTION, INSTA, NAME, PROFILE_URL, WISH_COUNT, MEMBER_ID)
VALUES
    ('임시 작가입니다', 'good_artist', '김작가', 'https://example.com/profile.jpg', 0, 1),
    ('유명 도예가', 'pottery_queen', '박작가', 'https://example.com/profile2.jpg', 0, 3),
    ('신예 작가', 'fresh_pottery', '신작가', 'https://example.com/profile3.jpg', 0, 4);

-- ✅ CATEGORY
INSERT INTO CATEGORY (CATEGORY_NAME, PARENT_CATEGORY_ID)
VALUES
    ('머그컵', NULL),
    ('접시', NULL),
    ('화병', NULL),
    ('빈티지 컵', 1),
    ('모던 컵', 1),
    ('소접시', 2),
    ('대접시', 2),
    ('장식용 화병', 3),
    ('실사용 화병', 3);

-- ✅ ITEM
INSERT INTO ITEM (DESCRIPTION, NAME, PRICE, SALE_STATUS, STOCK, WISH_COUNT, REVIEW_COUNT, TOTAL_RATING, ARTIST_ID, CREATED_AT, UPDATED_AT)
VALUES
    ('핸드메이드 컵', '도자기 컵', 10000, 'ON_SALE', 10, 2, 0, 0, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('세련된 접시', '도자기 접시', 15000, 'ON_SALE', 5, 1, 0, 0, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('빈티지 화병', '도자기 화병', 20000, 'ON_SALE', 7, 1, 0, 0, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('봄꽃 느낌 머그', '봄 머그', 12000, 'ON_SALE', 8, 2, 0,0,  3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('겨울 느낌 컵', '겨울 컵', 13000, 'ON_SALE', 6, 3, 0, 0, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('흙의 느낌 머그', '어스 컵', 9000, 'ON_SALE', 5, 1, 0, 0, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('간결한 접시', '미니멀 접시', 16000, 'ON_SALE', 3, 0, 0, 0, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('추상화병', '아트 화병', 22000, 'ON_SALE', 2, 1, 0,0,  2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('투톤 머그', '모던 머그', 11000, 'ON_SALE', 6, 0, 0, 0, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('라인 컵', '라인 컵', 12500, 'ON_SALE', 4, 0, 0, 0, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ✅ ITEM_IMAGE
INSERT INTO ITEM_IMAGE (IMAGE_URL, IS_THUMBNAIL, ITEM_ID)
VALUES
    ('https://example.com/image1.jpg', TRUE, 1),
    ('https://example.com/image2.jpg', TRUE, 2),
    ('https://example.com/image3.jpg', TRUE, 3),
    ('https://example.com/image4.jpg', TRUE, 4),
    ('https://example.com/image5.jpg', TRUE, 5),
    ('https://example.com/image6.jpg', TRUE, 6),
    ('https://example.com/image7.jpg', TRUE, 7),
    ('https://example.com/image8.jpg', TRUE, 8),
    ('https://example.com/image9.jpg', TRUE, 9),
    ('https://example.com/image10.jpg', TRUE, 10);

-- ✅ WISH
INSERT INTO WISH (TARGET_ID, TARGET_TYPE, MEMBER_ID)
VALUES
    (1, 'ITEM', 2),
    (2, 'ITEM', 2),
    (3, 'ITEM', 2),
    (4, 'ITEM', 2),
    (4, 'ITEM', 5),
    (5, 'ITEM', 2),
    (5, 'ITEM', 3),
    (5, 'ITEM', 4),
    (6, 'ITEM', 2),
    (8, 'ITEM', 2);

-- ✅ ITEM_CATEGORY
INSERT INTO ITEM_CATEGORY (CATEGORY_ID, ITEM_ID)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (2, 7),
    (3, 8),
    (1, 9),
    (1, 10);

-- ✅ EXHIBITION
INSERT INTO EXHIBITION (DESCRIPTION, TITLE, CREATED_AT, UPDATED_AT)
VALUES
    ('따뜻한 봄 작품들', '봄 기획전', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ✅ EXHIBITION_ITEM
INSERT INTO EXHIBITION_ITEM (EXHIBITION_ID, ITEM_ID)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (1, 7),
    (1, 8),
    (1, 9),
    (1, 10);

-- ✅ INQUIRY (주의: 테이블명 소문자 inquiry → 대문자 INQUIRY 또는 정확한 테이블명 확인)
INSERT INTO INQUIRY (QUESTION, ANSWER, ARTIST_ID)
VALUES
    ('배송은 얼마나 걸리나요?', '약 3~5일 소요됩니다.', 1)
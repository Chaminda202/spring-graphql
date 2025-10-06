CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE post (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(500) NULL,
    content TEXT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_post_user FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);

-- inset product table
INSERT INTO user (name, email) VALUES ('User1','user1@gmail.com');
INSERT INTO user (name, email) VALUES ('User2','user2@gmail.com');
INSERT INTO user (name, email) VALUES ('User3','user3@gmail.com');

INSERT INTO post (title, content, user_id)
SELECT 'Test Title', 'Test Content', u.id
FROM user u
WHERE u.name = 'User1' and u.email = 'user1@gmail.com';


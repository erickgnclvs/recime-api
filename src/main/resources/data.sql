CREATE TABLE IF NOT EXISTS recipe (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    difficulty VARCHAR(50) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    score INT NOT NULL
);

INSERT INTO recipe (name, difficulty, image_url, score) VALUES
('Chicken Burger', 'EASY', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 100),
('Beef Burger', 'MEDIUM', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 95),
('Veggie Burger', 'EASY', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 90),
('Bacon Burger', 'MEDIUM', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 85),
('Cheese Burger', 'EASY', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 80),
('BBQ Burger', 'MEDIUM', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 75),
('Spicy Burger', 'HARD', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 70),
('Mushroom Burger', 'MEDIUM', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 65),
('Double Cheeseburger', 'HARD', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 60),
('Teriyaki Burger', 'MEDIUM', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 55),
('Guacamole Burger', 'HARD', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 50),
('Hawaiian Burger', 'MEDIUM', 'https://ddg0cip9uom1w.cloudfront.net/code-challenge/burger.jpg', 45);
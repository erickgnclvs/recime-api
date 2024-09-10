CREATE TABLE IF NOT EXISTS recipe (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    difficulty VARCHAR(50) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    score INT NOT NULL,
    recipe_text TEXT
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

UPDATE recipe
SET recipe_text = 'Delicious Homemade Chicken Burger

Ingredients:
- 1 lb ground chicken
- 1/4 cup breadcrumbs
- 1 egg, beaten
- 2 cloves garlic, minced
- 1 tsp dried oregano
- 1 tsp paprika
- Salt and pepper to taste
- 4 hamburger buns
- Lettuce leaves
- Sliced tomatoes
- Sliced red onions
- Mayonnaise
- Dijon mustard

Instructions:
1. In a large bowl, mix ground chicken, breadcrumbs, egg, minced garlic, oregano, paprika, salt, and pepper until well combined.

2. Divide the mixture into 4 equal portions and shape each into a patty about 1/2 inch thick.

3. Preheat your grill or a large skillet over medium-high heat. If using a skillet, add a bit of oil.

4. Cook the chicken patties for about 5-6 minutes on each side, or until they reach an internal temperature of 165°F (74°C).

5. While the patties are cooking, lightly toast the hamburger buns.

6. To assemble the burgers, spread mayonnaise and Dijon mustard on the bottom bun. Add a lettuce leaf, then the cooked chicken patty.

7. Top the patty with sliced tomatoes and red onions. Place the top bun on and serve immediately.

8. Enjoy your homemade chicken burger with your favorite side dish!

Tips:
- For extra flavor, you can add 1/4 cup of grated Parmesan cheese to the chicken mixture.
- If the mixture seems too wet, add a bit more breadcrumbs. If it''s too dry, add a splash of milk.
- Let the patties rest in the refrigerator for 30 minutes before cooking to help them hold their shape better.
- For a healthier option, you can use whole wheat buns and Greek yogurt instead of mayonnaise.'
WHERE name = 'Chicken Burger';


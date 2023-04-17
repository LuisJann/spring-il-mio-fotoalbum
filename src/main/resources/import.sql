INSERT INTO photos (title, description, url, is_visible) VALUES('Tramonto', 'Tramonto alla finestra', 'https://images.pexels.com/photos/1475234/pexels-photo-1475234.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 1);
INSERT INTO photos (title, description, url, is_visible) VALUES('Paesaggio', 'Foschia e bosco', 'https://images.pexels.com/photos/3551207/pexels-photo-3551207.jpeg', 1);
INSERT INTO photos (title, description, url, is_visible) VALUES('Volti per il mondo', 'Volto indiano', 'https://images.pexels.com/photos/2385044/pexels-photo-2385044.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 0);
INSERT INTO photos (title, description, url, is_visible) VALUES('cani', 'Il mio cane', 'https://images.pexels.com/photos/994174/pexels-photo-994174.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1', 1);


INSERT INTO categories (title) VALUES('#tramonti');
INSERT INTO categories (title) VALUES('#paesaggi');
INSERT INTO categories (title) VALUES('#natura');
INSERT INTO categories (title) VALUES('#city');
INSERT INTO categories (title) VALUES('#sport');


INSERT INTO users (email, password, username) VALUES('admin@gmail.com', '{noop}admin', 'admin');

INSERT INTO roles (name) VALUES('ADMIN');

INSERT INTO users_roles (user_id, roles_id) VALUES(1,1);
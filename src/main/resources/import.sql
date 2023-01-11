-- FOR TESTING PURPOSES ONLY
INSERT INTO roles(id, name) VALUES('1','ROLE_USER');
INSERT INTO roles(id, name) VALUES('2','ROLE_MODERATOR');
INSERT INTO roles(id, name) VALUES('3','ROLE_ADMIN');

INSERT INTO users(first_name, last_name, email, username, password) VALUES ('userFirst', 'userLast', 'user@alliance.io', 'user', '$argon2id$v=19$m=16384,t=2,p=1$zID0OV37ztR647L+uPuUxg$a252KJLrE4gnr0tnfCCluHA6Lvjzl61scPnqNcCgepw');
INSERT INTO users(first_name, last_name, email, username, password) VALUES ('modFirst', 'modLast', 'moderator@alliance.io', 'moderator', '$argon2id$v=19$m=16384,t=2,p=1$a6E9ytBmBUrz9ukTI36Ncg$5Uu2/1lrZb3Tq0GjFuBpaN/Jx0ldqkH2rU/puOnc4oQ');
INSERT INTO users(first_name, last_name, email, username, password) VALUES ('adminFirst', 'adminLast', 'admin@alliance.io', 'admin', '$argon2id$v=19$m=16384,t=2,p=1$Z9vriscntXKAqN55dLnL3A$Csuip+WquaPlB94pStlDWCzCPKOunW+rUZNe8dXLS/E');

INSERT INTO user_roles(user_id, role_id) VALUES ('1', '1');
INSERT INTO user_roles(user_id, role_id) VALUES ('2', '1');
INSERT INTO user_roles(user_id, role_id) VALUES ('2', '2');
INSERT INTO user_roles(user_id, role_id) VALUES ('3', '1');
INSERT INTO user_roles(user_id, role_id) VALUES ('3', '2');
INSERT INTO user_roles(user_id, role_id) VALUES ('3', '3');
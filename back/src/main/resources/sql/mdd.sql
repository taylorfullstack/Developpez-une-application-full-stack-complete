CREATE TABLE USERS (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE THEMES (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        title VARCHAR(255) NOT NULL,
                        description VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE ARTICLES (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          title VARCHAR(255) NOT NULL,
                          content TEXT NOT NULL,
                          user_id BIGINT NOT NULL,
                          theme_id BIGINT NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES USERS(id),
                          FOREIGN KEY (theme_id) REFERENCES THEMES(id)
);

CREATE TABLE COMMENTS (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          content TEXT NOT NULL,
                          user_id BIGINT NOT NULL,
                          article_id BIGINT NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES USERS(id),
                          FOREIGN KEY (article_id) REFERENCES ARTICLES(id)
);

-- Inserting user
INSERT INTO USERS(name, email, password) VALUES ('MDD User', 'user@mdd.com', 'pass1234');

-- Inserting themes
INSERT INTO THEMES(title, description) VALUES ('Elixir', 'Elixir is a dynamic, functional language designed for building scalable and maintainable applications.');
INSERT INTO THEMES(title, description) VALUES ('Phoenix', 'Phoenix is a web development framework written in Elixir which implements the server-side Model View Controller (MVC) pattern.');
INSERT INTO THEMES(title, description) VALUES ('Phoenix LiveView', 'Phoenix LiveView is a library for Phoenix Framework that enables rich, real-time user experiences with server-rendered HTML.');
INSERT INTO THEMES(title, description) VALUES ('Angular', 'Angular is a platform for building mobile and desktop web applications.');
INSERT INTO THEMES(title, description) VALUES ('Java', 'Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.');
INSERT INTO THEMES(title, description) VALUES ('JavaScript', 'JavaScript is a text-based programming language used both on the client-side and server-side that allows you to make web pages interactive.');
INSERT INTO THEMES(title, description) VALUES ('TypeScript', 'TypeScript is a strongly typed, object oriented, compiled language.');
INSERT INTO THEMES(title, description) VALUES ('Python', 'Python is an interpreted, high-level and general-purpose programming language.');
INSERT INTO THEMES(title, description) VALUES ('React', 'React is an open-source, front end, JavaScript library for building user interfaces or UI components.');
INSERT INTO THEMES(title, description) VALUES ('Django', 'Django is a high-level Python web framework that enables rapid development of secure and maintainable websites.');
INSERT INTO THEMES(title, description) VALUES ('CSS', 'CSS is a style sheet language used for describing the look and formatting of a document written in HTML.');
INSERT INTO THEMES(title, description) VALUES ('HTML', 'HTML is the standard markup language for documents designed to be displayed in a web browser.');

-- Inserting articles
INSERT INTO ARTICLES(title, content, user_id, theme_id) VALUES ('Elixir Article', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 1, (SELECT id FROM THEMES WHERE title = 'Elixir'));
INSERT INTO ARTICLES(title, content, user_id, theme_id) VALUES ('Phoenix Article', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 1, (SELECT id FROM THEMES WHERE title = 'Phoenix'));
INSERT INTO ARTICLES(title, content, user_id, theme_id) VALUES ('CSS Article', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 1, (SELECT id FROM THEMES WHERE title = 'CSS'));
INSERT INTO ARTICLES(title, content, user_id, theme_id) VALUES ('HTML Article', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 1, (SELECT id FROM THEMES WHERE title = 'HTML'));
INSERT INTO ARTICLES(title, content, user_id, theme_id) VALUES ('TypeScript Article', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 1, (SELECT id FROM THEMES WHERE title = 'TypeScript'));
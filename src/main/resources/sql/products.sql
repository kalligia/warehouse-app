INSERT INTO products(prod_id, created_at, updated_at, color, quantity, type_id, material_id, photo_name, name)
VALUES (1,'2025-01-02 23:39:54.015968','2025-01-02 23:39:54.015968','Red',2,3,1,'red-mug.jpg','Penelope'),
                       (2,'2025-01-02 23:41:30.325974','2025-01-02 23:41:30.325974','Brown',3,6,3,'board.jpg','Solon'),
                       (3,'2025-01-02 23:42:40.153840','2025-01-02 23:42:40.153840','Pink',6,4,1,'minnie.jpg','Minnie'),
                       (4,'2025-01-02 23:44:53.314442','2025-01-02 23:44:53.314442','Blue',1,1,1,'paris.jpg','Paris'),
                       (5,'2025-01-02 23:51:07.426935','2025-01-02 23:51:07.426935','Black',7,5,4,'bowl.webp','Bruno'),
                       (6,'2025-01-02 23:35:48.549260','2025-01-02 23:35:48.549260','White',5,1,1,'aria.png','Aria');

ALTER TABLE products AUTO_INCREMENT = 7;
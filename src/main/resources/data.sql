-- categories テーブルにデータを挿入するクエリ
INSERT INTO categories (id, name)
VALUES
(1, '仕事'),
(2, '個人'),
(3, 'その他');

-- users テーブルにデータを挿入するクエリ
INSERT INTO users (id, email, name, password)
VALUES
(1, 'tanaka@aaa.com', '田中太郎', 'test123'),
(2, 'suzuki@aaa.com', '鈴木一郎', 'test456');

-- tasks テーブルにデータを挿入するクエリ
INSERT INTO tasks (id, category_id, user_id, title, limit_date, importance,progress, memo)
VALUES
(1, 1, 1, '見積もり', '2025-12-31', 0,0,'案件に適した見積もりを取る');

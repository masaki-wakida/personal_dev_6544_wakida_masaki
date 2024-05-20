-- categories テーブルにデータを挿入するクエリ
INSERT INTO categories (id, name)
VALUES
(1, '仕事'),
(2, '個人'),
(3, 'その他');

-- users テーブルにデータを挿入するクエリ
INSERT INTO users (email, name, password)
VALUES
( 'tanaka@aaa.com', '田中太郎', 'test123'),
('suzuki@aaa.com', '鈴木一郎', 'test456'),
('aaa@ezweb.ne.jp','脇田将希','himitu');

-- tasks テーブルにデータを挿入するクエリ
INSERT INTO tasks (category_id, user_id, title, limit_date, importance,progress, memo)
VALUES
( 1, 1, '見積もり', '2025-12-31', 0,2,'案件に適した見積もりを取る'),
( 2, 2, 'ちいかわ', '2025-12-31', 1,0,'ﾜ､ﾜｧ！'),
( 3, 3, 'ハチワレ', '2025-12-31', 2,1,'ってこと...!?');

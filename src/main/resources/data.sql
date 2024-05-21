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
( 1, 1, '見積もり', '2023-11-4', 0,2,'案件に適した見積もりを取る'),
( 2, 1, 'ちいかわ', '2022-12-31', 1,0,'ﾜ､ﾜｧ！'),
( 3, 1, 'ハチワレ', '2022-4-30', 2,1,'ってこと...!?'),
( 1, 2, '見積もり', '2024-10-3', 0,2,'案件に適した見積もりを取る'),
( 2, 2, 'ちいかわ', '2024-6-6', 1,0,'ﾜ､ﾜｧ！'),
( 3, 2, 'ハチワレ', '2023-12-1', 2,1,'ってこと...!?'),
( 1, 3, '見積もり', '2024-10-4', 0,2,'案件に適した見積もりを取る'),
( 2, 3, 'ちいかわ', '2023-4-2', 1,0,'ﾜ､ﾜｧ！'),
( 3, 3, 'ハチワレ', '2022-8-11', 2,1,'ってこと...!?');


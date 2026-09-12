-- =============================================
-- مؤسسة العمار مصر - إنشاء قاعدة البيانات (PostgreSQL)
-- =============================================

-- 1) إنشاء قاعدة البيانات (قم بتنفيذ هذا السطر بشكل منفصل إذا لزم الأمر)
-- CREATE DATABASE alamar_db;
-- \c alamar_db;

-- 2) جدول المستخدمين
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
);

-- 3) جدول المنتجات (الطوب)
CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(200) NOT NULL,
                          price DOUBLE PRECISION NOT NULL,
                          size VARCHAR(100)
);

-- 4) جدول المحافظات
CREATE TABLE governorates (
                              id BIGSERIAL PRIMARY KEY,
                              name VARCHAR(200) NOT NULL,
                              freight DOUBLE PRECISION NOT NULL
);

-- 5) جدول الطلبات
CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(200) NOT NULL,
                        phone VARCHAR(50) NOT NULL,
                        brick_type VARCHAR(200) NOT NULL,
                        quantity INTEGER NOT NULL,
                        governorate VARCHAR(200) NOT NULL,
                        address VARCHAR(500) NOT NULL,
                        total_price VARCHAR(100) NOT NULL
);

-- =============================================
-- إدخال البيانات الأولية
-- =============================================

-- حساب الأدمن
INSERT INTO users (username, password, role)
VALUES ('admin', '123', 'admin');

-- المنتجات
INSERT INTO products (name, price, size) VALUES
                                             ('طوب أحمر مفرغ (مثقب)', 1200, '25×12×6 سم'),
                                             ('طوب أسمنتي مصمت', 1600, '25×12×6 سم'),
                                             ('طوب خفيف إيكولوجي', 2100, '60×20×20 سم');

-- المحافظات
INSERT INTO governorates (name, freight) VALUES
                                             ('القاهرة', 300),
                                             ('الجيزة', 250),
                                             ('القليوبية', 350),
                                             ('الإسكندرية', 550),
                                             ('البحيرة', 500),
                                             ('الفيوم', 400),
                                             ('الشرقية', 400),
                                             ('الدقهلية', 450),
                                             ('الغربية', 420),
                                             ('المنوفية', 380),
                                             ('دمياط', 500),
                                             ('بورسعيد', 550),
                                             ('الإسماعيلية', 480),
                                             ('السويس', 500),
                                             ('كفر الشيخ', 460),
                                             ('بني سويف', 450),
                                             ('المنيا', 550),
                                             ('أسيوط', 650),
                                             ('سوهاج', 750),
                                             ('قنا', 850),
                                             ('الأقصر', 900),
                                             ('أسوان', 1000),
                                             ('مطروح', 800),
                                             ('الوادي الجديد', 950),
                                             ('البحر الأحمر', 850),
                                             ('شمال سيناء', 700),
                                             ('جنوب سيناء', 850);
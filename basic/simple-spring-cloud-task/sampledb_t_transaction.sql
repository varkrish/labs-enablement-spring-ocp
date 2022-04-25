create table if not exists t_transaction
(
    id          int auto_increment
        primary key,
    productname varchar(20) null,
    amount      int         null,
    price_total decimal     null
);

create table if not exists t_trx_history
(
    id          int auto_increment
        primary key,
    productname varchar(20) null,
    amount      int         null,
    price_total decimal     null,
    trx_date    timestamp   null
);



INSERT INTO sampledb.t_transaction (productname, amount, price_total) VALUES ('Some Product Name', 10, 1000);
INSERT INTO sampledb.t_transaction (productname, amount, price_total) VALUES ('Other Product', 4, 400);
INSERT INTO sampledb.t_transaction (productname, amount, price_total) VALUES ('This Product', 3, 305);
INSERT INTO sampledb.t_transaction (productname, amount, price_total) VALUES ('That Product', 2, 100);
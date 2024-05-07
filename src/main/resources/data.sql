--insert products
insert into xprice.product (brand, name, version)
values ('Apple', 'MacBook Air M2', 1);

--insert prices
insert into xprice.product_price (product_id, seller, amount, currency, version)
values (1, 'bestbuy', 80000, 'TRY', 1),
       (1, 'amazon', 79000, 'TRY', 1),
       (1, 'hepsiburada', 79000, 'TRY', 1),
       (1, 'trendyol', 81000, 'TRY', 1),
       (1, 'migros', 78000, 'TRY', 1);
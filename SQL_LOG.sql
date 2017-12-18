
CREATE TABLE product_info
(
   product_id                     VARCHAR(32)                    NOT NULL,
   product_name                   VARCHAR(30),
   product_desc                   VARCHAR(250),
   img_url                        VARCHAR(250),
   price_in                       DECIMAL(12,2),
   price_out                      DECIMAL(12,2),
   product_type                   VARCHAR(20),
   state                          VARCHAR(3),
   created                        DATETIME,
   creator                        VARCHAR(20),
   updator                        VARCHAR(20),
   updated                        DATETIME,
   PRIMARY KEY (product_id)
);


CREATE TABLE product_log
(
   log_id                         NUMERIC(12,0)                  NOT NULL,
   product_id                     NUMERIC(12,0),
   pre_record                     VARCHAR(700),
   oper_time                      DATETIME,
   oper_type                      VARCHAR(3),
   PRIMARY KEY (log_id)
);

CREATE TABLE stock_info
(
   stock_id                       VARCHAR(32)                    NOT NULL,
   product_id                     VARCHAR(32),
   total_amount                   NUMERIC(12,0),
   left_amount                    NUMERIC(12,0),
   PRIMARY KEY (stock_id)
);

CREATE TABLE order_detail
(
   order_id                       VARCHAR(32),
   order_detail_id                VARCHAR(32)                    NOT NULL,
   product_id                     VARCHAR(32),
   amount                         NUMERIC(12,0),
   PRIMARY KEY (order_detail_id)
);

CREATE TABLE order_info
(
   order_id                           VARCHAR(32)                    NOT NULL,
   use_user_id                    VARCHAR(32),
   order_time                     DATETIME,
   pay_time                       DATETIME,
   state                          VARCHAR(3),
   user_id                        NUMERIC(12,0),
   sum_price                            DECIMAL(12,2),
   PRIMARY KEY (order_id)
);

CREATE TABLE user_info
(
   user_id                        VARCHAR(32)                    NOT NULL,
   wx_name                        VARCHAR(50),
   wx_id                          VARCHAR(50),
   phone                          NUMERIC(11,0),
   PRIMARY KEY (user_id)`order_info`
);


INSERT INTO userType(typename) values ('employee'),('affiliate'),('other');

INSERT INTO users (name, typeid) VALUES
  ('Aliko',1),
  ('Bill',2),
  ('Folrunsho',3);
  
  
  
INSERT INTO RETAIL_ITEM_TYPE(typename,discountapplicable) values ('groceries','n'),('stationaries','y'),('other','y');

INSERT INTO Item (name, typeid,price) VALUES 
  ('Sugar',1,20),
  ('pencil',2,10),
   ('Tea',1,25),
  ('notebook',2,10),
   ('rice',1,20),
  ('Rubber',2,10),
  ('handbag',3,30);
  
  
INSERT INTO Discount(discount_percentage,usertypeid) values (30,1),(10,2),(0,3);

INSERT INTO Bill(userid,Amount,DiscountApplicable) values (1,400,'y'),(2,100.5,'y'),(3,50.3,'n');

INSERT INTO Bill_Items(billid,itemId,quantity) values (1,3,4),(1,4,5),(2,1,2),(3,2,6),(2,3,5);
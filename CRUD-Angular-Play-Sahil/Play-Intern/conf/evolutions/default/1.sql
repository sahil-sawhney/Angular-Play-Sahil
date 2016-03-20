# --- !Ups
CREATE TABLE "intern_details"("name" varchar(50) , "email" varchar(50) ,"mobile" varchar(50) , "address" varchar(50) , "alternate_contact" varchar(50)  , "id" SERIAL PRIMARY KEY);

INSERT INTO "intern_details" values ('sahil', 'test1@knoldus.com', '9871211045', 'Delhi','9958870783', 1);
INSERT INTO "intern_details" values ('varun', 'test2@knoldus.com', '9958870783', 'Kanpur','9871211045', 2);
# --- !Downs

DROP TABLE "intern_details";


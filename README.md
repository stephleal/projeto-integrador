## Projeto Integrador

Todo list

US1
Corrigir o PUT Inbound - deve sobrescrever os batches
Response Entity e verificar o status de response
Implementar o URI para devolver na response
Validação de campos
Tratamento de exceções

(Testes)
(Documentação )
(Readme.md)
(Incluir o modelo)
(Collection do Postman)

Refatorarações
- Implementar dtos com interfaces de conversão (?)




Modelo de SQL

//drop database if exists integrator_project;

//create database integrator_project;

//use integrator_project;

insert into warehouses (name) values ("SP01"),("FL01"),("MA01"),("BR01"),("RP01");

insert into sections (total_space, type, warehouse_id) values (1000, 0, 1),(2000, 2, 1),(1000, 1, 2),(2000, 2, 2),(1000, 0, 3),(2000, 2, 3),(2000, 1, 4),(1000, 0, 4),(3000, 1, 5),(1000, 2, 5);

insert into products (name, product_type) values ("presunto", 1),("queijo",1),("maçã", 0),("alface",0),("frango", 2),("batata",2);

insert into sellers (name) values ("Iberê"),("Stephanie"),("Lucas"),("Mauro"),("Matheus");

insert into products_announcements (brand, maximum_temperature, minimum_temperature, name, price, volume, product_id, seller_id) values 
("sadia", 10, 5, "presunto sadia",15,0.5,1, 1),
("três marias", 10, 5, "queijo 3 marias",20,0.4,2, 2),
("turma da mônica", 15, 10, "maçã turma da mônica",10,1,3, 3),
("horta", 15, 10, "alface da horta",5,1,4, 4),
("perdigão", 0, -5, "frango perdigão",25,2,5, 5);

insert into agents (name, section_id) values ("João", 1),("José", 2),("Maria", 3),("Ana", 4),("Paulo", 5),("Francisco", 6),("William", 7),("Maurício", 8),("Gustavo", 9),("Pedro", 10);




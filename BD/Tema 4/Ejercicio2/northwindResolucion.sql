--- DIFICULTAD FACIL ---

--1. Show the category_name and description from the categories table sorted by category_name*

SELECT category_name, description FROM categories
ORDER BY category_name;

-- 2. Show all the contact_name, address, city of all customers which are not from 'Germany', 'Mexico', 'Spain'

SELECT contact_name, address, city FROM customers
WHERE country NOT IN ('Germany', 'Mexico', 'Spain');

-- 3. Show order_date, shipped_date, customer_id, Freight of all orders placed on 2018 Feb 26

SELECT order_date, shipped_date, customer_id, freight FROM orders
WHERE order_date = '2018-02-26';

-- 4. Show the employee_id, order_id, customer_id, required_date, shipped_date from all orders shipped later than the required date

SELECT employee_id, order_id, customer_id, required_date, shipped_date FROM orders
WHERE shipped_date > required_date;

-- 5. Show all the even numbered Order_id from the orders table

SELECT order_id FROM orders
WHERE order_id % 2 = 0;

-- 6. Show the city, company_name, contact_name of all customers from cities which contains the letter 'L' in the city name, sorted by contact_name

SELECT city, company_name, contact_name FROM customers 
WHERE city LIKE '%L%'
ORDER BY contact_name;

-- 7. Show the company_name, contact_name, fax number of all customers that has a fax number. (not null)

SELECT  company_name, contact_name, fax FROM customers 
WHERE fax IS NOT NULL;

-- 8. Show the first_name, last_name. hire_date of the most recently hired employee.

SELECT  first_name, last_name, hire_date from employees
ORDER BY hire_date desc # Tambien lo puedo hacer dentro del hire_date con un "MAX(hire_date)" 
LIMIT 1;

-- 9. Show the average unit price rounded to 2 decimal places, the total units in stock, total discontinued products from the products table.

SELECT ROUND(AVG(unit_price), 2) as "Precio por unidad medio", SUM(units_in_stock) as "Unidades en stock", SUM(discontinued) as "Productos descontinuados" FROM products;
-- Con ROUND se consigue redondear a los decimales que queramos en este caso 2 

--- DIFICULTAD MEDIA ---

--1. Show the ProductName, CompanyName, CategoryName from the products, suppliers, and categories table

SELECT p.product_name, s.company_name, c.category_name FROM products p 
JOIN suppliers s ON s.supplier_id = p.supplier_id
JOIN categories c ON c.category_id = p.category_id;

-- 2. Show the category_name and the average product unit price for each category rounded to 2 decimal places.

SELECT c.category_name, ROUND(AVG(p.unit_price), 2) as "Precio medio producto" FROM products p 
JOIN categories c ON c.category_id = p.category_id
GROUP BY c.category_name; # Sin el GROUP BY no me funciona porque pide de cada categoria, si no me daria el total de todo.

-- 3. Show the city, company_name, contact_name from the customers and suppliers table merged together. Create a column which contains 'customers' or 'suppliers' depending on the table it came from.
--Nos dice que debemos hacer un "merged" completo de las tablas así que deb usar un UNION y en este caso ALL, no he encontrado otra forma de hacerlo aunque no esté en los apuntes
SELECT c.city, c.company_name, c.contact_name, 'Clientes' as "Tipo" FROM customers c
UNION ALL
SELECT s.city, s.company_name, s.contact_name, 'Proveedores' as "Tipo" FROM suppliers s;

-- 4. Show the total amount of orders for each year/month.
-- Tengo que construir a mano las culumna de mes y año, ya que no nos lo dan por separado
SELECT YEAR(order_date) as "Año", MONTH(order_date) as "Mes", count(order_id) as "Total pedidos" FROM orders
GROUP BY YEAR(order_date), month(order_date); 

--- DIFICILTAD DIFICIL ---

-- 1. Show the employees first_name and last_name, a "num_orders" column with a count of the orders taken, and a column called "Shipped" that displays "On Time" if the order shipped_date is less or equal to the required_date, "Late" if the order shipped late, "Not Shipped" if shipped_date is null. Order by employee last_name, then by first_name, and then descending by number of orders.
--No tengo ni idea de como se hace esto

-- 2. Show how much money the company lost due to giving discounts each year, order the years from most recent to least recent. Round to 2 decimal places

-- Empiezo creando la tabla de cada año y luego calculo el descuento de cada producto y lo sumo, tengo que tener en cuenta que el ejercicio me pide redondear a 2 decimales
SELECT YEAR(o.order_date) as Año_Pedidos, ROUND(SUM(p.unit_price * od.quantity * od.discount), 2) as "Cantidad descondata"
FROM orders o join order_details od ON o.order_id = od.order_id 
join products p ON p.product_id = od.product_id
-- Para que la columna sea vea correctamente por año y cada uno distinto le meto un GROUP BY y ya luego lo ordeno por el Año_Pedidos
GROUP BY YEAR(o.order_date)
order by order_date desc;
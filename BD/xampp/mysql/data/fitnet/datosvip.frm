TYPE=VIEW
query=select concat(`u`.`nombre`,\' \',`u`.`apellido`) AS `NombreCompleto`,`u`.`email` AS `email`,`p`.`nombre` AS `tipo`,`m`.`estado` AS `estado` from (((`fitnet`.`usuario` `u` join `fitnet`.`socio` `s` on(`u`.`id_usuario` = `s`.`id_socio`)) join `fitnet`.`membresia` `m` on(`s`.`id_socio` = `m`.`id_socio`)) join `fitnet`.`plan` `p` on(`m`.`id_plan` = `p`.`id_plan`)) where `p`.`nombre` like \'%VIP\' and `m`.`estado` = \'Vigente\'
md5=50c6f0d6a423f95c76dd6645e8c30d4e
updatable=1
algorithm=0
definer_user=root
definer_host=localhost
suid=2
with_check_option=0
timestamp=0001771865151493194
create-version=2
source=SELECT CONCAT(u.nombre, \' \', u.apellido) AS NombreCompleto, u.email, p.nombre AS tipo, m.estado FROM usuario u\nINNER JOIN socio s ON u.id_usuario = s.id_socio\nINNER JOIN membresia m ON s.id_socio = m.id_socio -- Enlazamos la membresía al socio\nINNER JOIN plan p ON m.id_plan = p.id_plan -- Añadimos el catálogo para saber el nombre del plan\nWHERE p.nombre LIKE \'%VIP\' -- De momento no hay usuarios con VIP que no sea Anual pero si en un futuro los hay uso el LIKE igualmente, no concreto con un \'=\'\nAND m.estado = \'Vigente\'
client_cs_name=utf8mb4
connection_cl_name=utf8mb4_unicode_ci
view_body_utf8=select concat(`u`.`nombre`,\' \',`u`.`apellido`) AS `NombreCompleto`,`u`.`email` AS `email`,`p`.`nombre` AS `tipo`,`m`.`estado` AS `estado` from (((`fitnet`.`usuario` `u` join `fitnet`.`socio` `s` on(`u`.`id_usuario` = `s`.`id_socio`)) join `fitnet`.`membresia` `m` on(`s`.`id_socio` = `m`.`id_socio`)) join `fitnet`.`plan` `p` on(`m`.`id_plan` = `p`.`id_plan`)) where `p`.`nombre` like \'%VIP\' and `m`.`estado` = \'Vigente\'
mariadb-version=100432

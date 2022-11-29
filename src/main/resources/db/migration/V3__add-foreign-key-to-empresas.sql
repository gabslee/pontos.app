alter table empresas
add foreign key (id_usuario)
references usuarios (id_usuario);
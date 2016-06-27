insert into role values ('ROLE_ADMIN');

insert into usuario (email, nome, senha)
values ('admin@casadocodigo.com.br', 'Administrador', '$2a$10$lt7pS7Kxxe5JfP.vjLNSyuTRGh/Wbc89FUXxtSMwdAeykeq7mD2M6'); -- 123456

insert into usuario_role (Usuario_email, roles_nome)
values ('admin@casadocodigo.com.br', 'ROLE_ADMIN');
INSERT INTO roles (name)
VALUES ('read'), ('write');

INSERT INTO users_roles (user_id, role_id)
(select users.id as userId, roles.id as rolesId
	from users
	join roles on roles.id = roles.id
	where users.name = 'admin' and roles.name in ('read', 'write'));
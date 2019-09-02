create database server_db; -- Create the new database
create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
grant all on server_db.* to 'springuser'@'%'; -- Gives all the privileges to the new user on the newly created database

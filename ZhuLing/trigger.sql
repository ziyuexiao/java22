DROP TRIGGER IF EXISTS t_after_insert_t_role;
DELIMITER //
CREATE TRIGGER t_after_insert_t_role
AFTER INSERT ON t_role
FOR EACH ROW 
BEGIN
INSERT INTO act_id_group(id_,rev_,name_) VALUES(new.id,1,new.viewname);
END 
//
DELIMITER ;



DROP TRIGGER IF EXISTS `t_after_insert_t_user`;
DELIMITER ;;
CREATE TRIGGER `t_after_insert_t_user` 
AFTER INSERT ON `t_user` 
FOR EACH ROW 
BEGIN

     INSERT INTO act_id_user(id_,rev_,first_,pwd_) VALUES(new.id,1,new.name,new.password);

END
;;
DELIMITER ;


DROP TRIGGER IF EXISTS `t_after_insert_t_user_role`;
DELIMITER //
CREATE TRIGGER `t_after_insert_t_user_role` 
AFTER INSERT ON `t_user_role` 
FOR EACH ROW 
BEGIN

     INSERT INTO act_id_membership(user_id_,group_id_) VALUES(new.user_id,new.role_id);

END
//
DELIMITER /

DROP TRIGGER IF EXISTS `t_before_update_t_role`;
DELIMITER //
CREATE TRIGGER t_before_update_t_role
BEFORE UPDATE ON t_role
FOR EACH ROW
BEGIN
UPDATE act_id_group SET rev_ = rev_ + 1, name_ = new.viewname WHERE id_= old.id;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS t_before_update_t_user;
DELIMITER //
CREATE TRIGGER t_before_update_t_user
BEFORE UPDATE ON t_user
FOR EACH ROW
BEGIN 
UPDATE act_id_user SET rev_ = rev_ + 1,first_= new.name WHERE id_ = old.id;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS t_after_delete_t_role;
DELIMITER //
CREATE TRIGGER t_after_delete_t_role
AFTER DELETE ON t_role
FOR EACH ROW
BEGIN 
DELETE FROM act_id_group WHERE id_=old.id;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS t_after_delete_t_user;
DELIMITER //
CREATE TRIGGER t_after_delete_t_user
AFTER DELETE ON t_user
FOR EACH ROW
BEGIN 
DELETE FROM act_id_user WHERE id_=old.id;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS t_delete_t_role_user;
DELIMITER //
CREATE TRIGGER t_delete_t_user_role
AFTER DELETE ON t_user_role
FOR EACH ROW
BEGIN 
DELETE FROM act_id_membership WHERE user_id_=old.user_id AND group_id_ = old.role_id;
END //
DELIMITER ;
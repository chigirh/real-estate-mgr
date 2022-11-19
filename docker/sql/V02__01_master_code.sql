CREATE TABLE IF NOT EXISTS `master_code`(
    `code_type` CHAR(2) NOT NULL comment 'type is,SEE:com.chigirh.eh.rem.domain.common.CodeMasterType',
    `code_value` VARCHAR(10) NOT NULL comment 'settings value',
    `name` VARCHAR(64) NOT NULL comment 'display name',
    `code_order` INTEGER NOT NULL,
    `not_using_now` BOOLEAN NOT NULL default false,
    PRIMARY KEY(`code_type`, `code_value`),
    INDEX `find_by_type_index` (`code_type`, `code_order`),
    INDEX `find_all_index` (`code_type`, `not_using_now`)
);

INSERT INTO master_code VALUES('01', '1', '未確認', 1, false);
INSERT INTO master_code VALUES('01', '2', '入居可', 2, false);
INSERT INTO master_code VALUES('01', '3', '入居不可', 3, false);
INSERT INTO master_code VALUES('01', '4', '成約済み', 4, false);

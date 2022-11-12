CREATE TABLE IF NOT EXISTS `users`(
    `user_id` VARCHAR(64) comment 'gmail address',
    `user_name` VARCHAR(300) NOT NULL,
    PRIMARY KEY(`user_id`)
);

CREATE TABLE IF NOT EXISTS `roles`(
    `user_id` VARCHAR(64) comment 'user id',
    `role` VARCHAR(10) comment 'ADMIN,CORP,GENE',
    PRIMARY KEY(`user_id`, `role`)
);

CREATE TABLE IF NOT EXISTS `real_estate`(
    `re_id` VARCHAR(64),
    `re_name` VARCHAR(64),
    `reNameKana` VARCHAR(64),
    `initials` CHAR(1),
    `address` VARCHAR(250),
    `rentPrice` INTEGER,
    `mgrCompanyName` VARCHAR(100),
    `mgrCompanyTel` VARCHAR(13),
    `foreigner_live_sts` CHAR(1),
    `note` VARCHAR(1000),
    `pdf` MEDIUMTEXT,
    `update_at` DATETIME NOT NULL,
    PRIMARY KEY(`re_id`),
    INDEX `initials_idx` (`initials`),
    INDEX `foreigner_live_sts_idx_1` (`foreigner_live_sts`, rentPrice),
    INDEX `re_name_idx` (`re_name`)
);

CREATE TABLE IF NOT EXISTS `real_estate_area`(
    `re_id` VARCHAR(64),
    `area` VARCHAR(64),
    PRIMARY KEY(`re_id`, `area`),
    INDEX `area_idx` (`area`),
    FOREIGN KEY `fk_real_estate`(`re_id`) REFERENCES `real_estate`(`re_id`) ON DELETE CASCADE
);

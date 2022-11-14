CREATE TABLE IF NOT EXISTS `users`(
    `user_id` VARCHAR(64) NOT NULL comment 'gmail address',
    `user_name` VARCHAR(300) NOT NULL,
    PRIMARY KEY(`user_id`)
);

CREATE TABLE IF NOT EXISTS `roles`(
    `user_id` VARCHAR(64) NOT NULL comment 'user id',
    `role` VARCHAR(10) NOT NULL comment 'ADMIN,CORP,GENE',
    PRIMARY KEY(`user_id`, `role`)
);

CREATE TABLE IF NOT EXISTS `real_estate`(
    `re_id` VARCHAR(64) NOT NULL,
    `re_name` VARCHAR(64) NOT NULL,
    `re_name_kana` VARCHAR(64) NOT NULL,
    `initials` CHAR(1) NOT NULL,
    `address` VARCHAR(250) NOT NULL,
    `rent_price` INTEGER NOT NULL,
    `mgr_company_name` VARCHAR(100) NOT NULL,
    `mgr_company_tel` VARCHAR(13) NOT NULL,
    `foreigner_live_sts` CHAR(1) NOT NULL,
    `note` VARCHAR(1000) NOT NULL,
    `pdf` MEDIUMTEXT NOT NULL,
    `updated_at` DATETIME NOT NULL,
    PRIMARY KEY(`re_id`),
    INDEX `initials_idx` (`initials`),
    INDEX `foreigner_live_sts_idx_1` (`foreigner_live_sts`, rent_price),
    INDEX `re_name_idx` (`re_name`)
);

CREATE TABLE IF NOT EXISTS `real_estate_area`(
    `re_id` VARCHAR(64),
    `area` VARCHAR(64),
    PRIMARY KEY(`re_id`, `area`),
    INDEX `area_idx` (`area`),
    FOREIGN KEY `fk_real_estate`(`re_id`) REFERENCES `real_estate`(`re_id`) ON DELETE CASCADE
);

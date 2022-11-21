ALTER TABLE `real_estate` ADD `condo_fee` INTEGER comment '共益費';
ALTER TABLE `real_estate` ADD `water_fee` INTEGER comment '水町費';
ALTER TABLE `real_estate` ADD `other_fee` VARCHAR(100) default '' comment 'その他費用';
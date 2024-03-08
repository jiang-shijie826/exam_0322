-- 保证incident_id的唯一性，如表中有重复incident_id，则保留start_date_time最晚的那一条，如start_date_time一样，就保留那个offence_code最大的那一条
CREATE TABLE temp_table AS SELECT
incident_id,
MAX( offence_code ) offence_code,
dispatch_time,
victims,
crime_name1,
crime_name2,
crime_name3,
city,
MAX( start_date_time ) start_date_time 
FROM
    crime_data 
GROUP BY
    incident_id 
HAVING
    start_date_time = MAX( start_date_time ) 
    AND offence_code = MAX( offence_code ) DELETE 
FROM
    crime_data;
    
INSERT INTO crime_data SELECT * FROM temp_table;

DROP TABLE temp_table;

-- -----------------------------------------------------------------------------
-- -----------------------------------------------------------------------------
--  2. 所有字段不能为空。如果 dispatch_time值为空，则以start_date_time的值代替。如其它字段值为空，则删除本条记录。
CREATE TABLE temp_table AS SELECT
incident_id,
offence_code,
IFNULL( dispatch_time, start_date_time ) AS dispatch_time,
victims,
crime_name1,
crime_name2,
crime_name3,
city,
start_date_time 
FROM
    crime_data 
WHERE
    incident_id IS NOT NULL 
    OR offence_code IS NOT NULL 
    OR victims IS NOT NULL 
    OR crime_name1 IS NOT NULL 
    OR crime_name2 IS NOT NULL 
    OR crime_name3 IS NOT NULL 
    OR city IS NOT NULL 
    OR start_date_time IS NOT NULL;
DELETE 
FROM
    crime_data;
    
INSERT INTO crime_data SELECT * FROM temp_table;

DROP TABLE temp_table;

-- -----------------------------------------------------------------------------
-- -----------------------------------------------------------------------------
--  3. 删除start_date_time在 2020年之前的所有记录。
DELETE 
FROM
    crime_data 
WHERE
    start_date_time < "2020-01-01 00:00:00";
    
-- -----------------------------------------------------------------------------
-- -----------------------------------------------------------------------------    
--  4. 确保城市名称都为大写字母
UPDATE crime_data SET city = UPPER( city );

-- crimes表: 以incident_id为主键，包含除crime_name1、crime_name2、crime_name3之外的所有字段
CREATE TABLE crimes AS SELECT
incident_id,
offence_code,
dispatch_time,
victims,
city,
start_date_time 
FROM
    crime_data;
ALTER TABLE crimes ADD CONSTRAINT PK PRIMARY KEY ( incident_id );
    
-- offences表: 以offence_code为主键，包含crime_name1、crime_name2、crime_name3
    CREATE TABLE offences AS 
    SELECT incident_id,
    offence_code,
    crime_name1,
    crime_name2,
    crime_name3 
FROM
    crime_data 
GROUP BY
    offence_code;
    
ALTER TABLE offences ADD CONSTRAINT PK PRIMARY KEY ( incident_id );
UPDATE ACCESS_PROFILE SET ID_PROFILE = ?, ID_SYS_DASHBOARD = ? WHERE ID_ACCESS_PROFILE = ?;
UPDATE ACCOUNT SET NIC = ?, ID_APP_PROFILE = ?, PASSWORD = ?, FIRST_NAME = ?, LAST_NAME = ?, LAST_NAME2 = ?, MAIL_APP = ?, DISCHARGE_DATE = ?, DATE_OF_LOW = ?, APP_PROFILEID_PROFILE = ? WHERE ID_ACCOUNT = ?;
UPDATE APP_PROFILE SET NAME = ?, DESCRIPTION = ? WHERE ID_PROFILE = ?;
UPDATE STUDENT SET FIRST_NAME = ?, LAST_NAME = ?, LAST_NAME2 = ?, MAIL_CONTACT = ?, SEX = ?, WEIGHT = ?, BIRTHDATE = ?, DAY_OF_INCOME = ?, DATE_OF_LOW = ?, STATUS = ?, CATEGORY = ?, TELEPHONE_CONTACT = ?, ADDRESS = ?, DOC_ID = ?, ID_TEACHER = ? WHERE ID_STUDENT = ?;
UPDATE SYSTEM_DASHBOARD SET NAME = ?, DESCRIPTION = ? WHERE ID_SYS_DASHBOARD = ?;
UPDATE TEACHER SET FIRST_NAME = ?, LAST_NAME = ?, LAST_NAME2 = ?, CATEGORY = ?, TELEPHONE_CONTACT = ?, MAIL = ?, DOC_ID = ? WHERE ID_TEACHER = ?;


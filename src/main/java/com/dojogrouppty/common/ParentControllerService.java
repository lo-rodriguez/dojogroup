/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dojogrouppty.common;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;

/**
 *
 * @author lrodriguezn
 */
public class ParentControllerService {

	// Constantes de la aplicacion
	public ParentControllerService() {
	}

	protected static final String HOME_VIEW_NAME = "home/home";
	protected static final String ABOUT_VIEW_NAME = "home/about";
	protected static final String ACCOUNT_VIEW = "account/account";
	protected static final String EDIT_PERSONAL_INFO="account/personalInfo";
	protected static final String LOGIN_VIEW_NAME = "signin/login";
	protected static final String ERROR_VIEW = "error/general";
	protected static final String ERROR_ACTION = "redirect:/generalError";
	protected static final String DASHBOARD_VIEW_NAME = "home/dashboard";
	protected static final String FORM_STUDENT_VIEW_NAME = "student/formStudent";
	protected static final String FORM_EDIT_STUDENT_VIEW_NAME = "student/formEditStudent";
	protected static final String PAGE_STUDENT_SEARCH = "student/studentSearch";
	protected static final String PAGE_CALENDAR_ACTIVITIES = "activities/calendarActivities";
	protected static final String PAGE_PAYMEN = "payments/formPayment";
	protected static final String PAGE_MULTI_PAYMENT = "payments/multiPaymentForm";
	protected static final String PRODUCTS_MAINTENANCE = "products/productsMaintenanceForm";
	protected static final String GENDER_CATALOG = "genders";
	protected static final String EMPLOYMENTS_CATALOG = "employments";
	protected static final String CATEGORIES_CATALOG = "categories";
	protected static final String SIZES_CATALOG = "sizes";
	protected static final String BLOODGROUPS_CATALOG = "bloodGroups";
	protected static final String SYSTEM_CODES_GENDERS = "GENDER";
	protected static final String SYSTEM_CODES_EMPLOYMENT = "EMPLOYMENT";
	protected static final String SYSTEM_CODES_CATEGORY = "CATEGORY";
	protected static final String SYSTEM_CODES_SIZES = "SIZES";
	protected static final String SYSTEM_CODES_BLOODGRO = "BLOODGRO";
	protected static final String SYSTEM_SYSACCION = "SYSACCION";
	protected static final String SYSTEM_GROUP_PRODUCTS = "PRODUCTS";
	protected static final String STUDENTS_DTO = "studentsDto";
	protected static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	protected static final String EMAIL_MESSAGE = "{email.message}";
	protected static final String EMAIL_EXISTS_MESSAGE = "{email-exists.message}";
	/**
	 * Constants of valid form
	 */
	protected static final String VALID_WEIGTH = "{dashboard.student.addstudent.StudentForm.weightdashboard.student.addstudent.StudentForm.weight}";
	protected static final String VALID_MAIL = "{dashboard.student.addstudent.StudentForm.weightdashboard.student.addstudent.StudentForm.mail}";
	protected static final String VALID_NAME = "{dashboard.student.addstudent.StudentForm.weightdashboard.student.addstudent.StudentForm.name}";
	protected static final String VALID_FORMAT_MAIL = "{dashboard.student.addstudent.StudentForm.mail}";
	protected static final String VALID_OPTION_BLOOGTYPE = "{dashboard.student.addstudent.StudentForm.bloodGroup}";
	protected static final String VALID_OPTION_GENDER = "{dashboard.student.addstudent.StudentForm.gender}";
	/**
	 * End
	 */
	protected static final String STUDENT_FORM_OBJ = "studentForm";
	protected static final String PAYMENT_FORM_OBJ = "paymentForm";
	protected static final String PRODUCTS_FORM_OBJ = "productsForm";
	protected static final String REPORT_FORM_OBJ = "reportForm";
	protected static final String ERROR_ADD_STUDENT = "error.student.add";
	protected static final String ADD_STUDENT = "dashboard.student.addstudent.message.correctly";
	/**
	 * Format date in BZK
	 */
	public static final String FORMAT_DATE = "dd/MM/yyyy";
	public static final String DATE_NULL = "31/12/2999";
	public static final String DDMMYYYY_HHMMSS_24h = "dd/MM/yyyy".concat(" ").concat("HH:mm:ss");
	/**
	 * General constants
	 */
	protected final static Long ID_TEACHER_DEFAULT = 0L;
	protected final static String DISABLED__ = "disabled";
	protected final static String GENERAL_MODAL_MESSAGE = "generalModalMessage";
	protected final static int GENERAL_MODAL_MESSAGE_KEY = 77777;
	protected final static String OK_ADD_STUDENT = "OkAddStudent";
	protected final static String OK_EDIT_STUDENT = "OkEditStudent";
	protected final static String OK = "ok";
	protected final static String NO_OK = "noOK";
	protected final static String NO_OK_PDF = "noOKPDF";
	protected final static String EDIT_MODE_STUDENT = "editModeStudent";
	protected final static String PAYMENT_MODE_STUDENT = "paymentModeStudent";
	protected final static String ACCION_EDIT_STUDENT = "/editstudent";
	protected final static String ACCION_READONLY_STUDENT = "/readOnlyStudent";
	protected final static String ACCION_ADD_STUDENT = "/addstudent";
	protected final static String ACCION_PRODUCTS = "/productsMaintenance";
	protected final static String ACCION_PRODUCTS_ADD = "/productsMaintenance";
	protected final static String ACCION_PRODUCTS_UPDATE = "/productsMaintenance";
	protected final static String ACCION_GENERATE_REPORT = "/generateReport";
	protected final static String ACCION_PAYMENT_STUDENT = "/paymentStudent";
	protected final static String ACCION_REPORT_HISTORY_BY_STUDENT_ID = "/getHistoryByStudentID";
	protected final static String FORM_ACCION_PAYMENT = "/payment";
	protected final static String FORM_ACCION = "formAction";
	protected final static String FORM_ACCION_OTHER = "formActionOther";
	protected final static String NEW_STUDENT_REGISTRATIONS = "newStudentRegistrations";
	protected final static String CURRENT_PRODUCT_ACCOUNT = "currentProductAccount";
	protected final static String PAYMENT_NOTIFICATION = "paymentNotification";
	protected final static String ACTIVITIES_NOTIFICATION = "activitiesNotification";
	protected final static String SEARCH_STUDENT_OPTION = "searchStudentOption";
	protected final static String SEARCH_STUDENT_ACCION = "searchStudentAction";
	protected final static String PAY_DATE = "payDate";
	protected final static String BIRTHDATE = "birthdate";
	protected final static String _MODULE = "module";
	protected final static String PRODUCTS = "products";
	protected final static String TYPES_PAYMENTS = "typesPayments";
	protected final static String PAYMENTS = "PAYMENTS";
	protected final static String ID_STUDENT = "idStudent";
	protected final static String OK_PAYMENT_STUDENT = "OkPaymentStudent";
	protected final static String LIST_ERROR_PAYMENT = "listErrorPayment";
	protected final static String LIST_ERROR_LOGIN = "listErrorLogin";
	protected final static String LIST_ACCION_FORM_PRODUCTS = "accions";
	protected final static String VALID_NAME_BANK = "{dashboard.payment.form.validNameBank}";
	protected final static String VALID_NUMBER_OF_TRANSFER = "{dashboard.payment.form.validNumberOfTransfer}";
	protected final static String VALID_DAY_PAYMENT = "{dashboard.payment.form.validDayPayment}";
	protected final static String VALID_TOTAL_PAYMENT_MIN = "{dashboard.payment.form.valid.min.TotalPayment}";
	protected final static String VALID_TOTAL_PAYMENT_MAX = "{dashboard.payment.form.valid.max.TotalPayment}";
	protected final static String VALID_TOTAL_FAX_MIN = "{dashboard.payment.form.valid.min.TotalFax}";
	protected final static String VALID_TOTAL_FAX_MAX = "{dashboard.payment.form.valid.max.TotalFax}";
	protected final static String VALID_MIN_HEIGTH = "{dashboard.student.addstudent.StudentForm.weightdashboard.student.addstudent.StudentForm.height.min}";
	protected final static String VALID_MAX_HEIGTH = "{dashboard.student.addstudent.StudentForm.weightdashboard.student.addstudent.StudentForm.height.max}";
	protected final static String FINAL_MESSAGE = "dashboard.student.disableStudent.finalMessage";
	protected final static String FINAL_MESSAGE_ADD_STUDENT = "dashboard.student.addstudent.message.correctly";
	protected final static String FINAL_MESSAGE_EDIT_STUDENT = "dashboard.student.editstudent.message.correctly";
	protected final static String FINAL_MESSAGE_PAYMENT = "dashboard.studentPayment.message.correctly";
	protected final static String FINAL_MESSAGE_UPDATE_PRODUCT = "dashboard.products.form.update.message";
	protected final static String FINAL_MESSAGE_ADD_PRODUCT = "dashboard.products.form.add.message";
	public static final SimpleDateFormat dateformat = new SimpleDateFormat(FORMAT_DATE);
	protected static final String TYPE_IMAGE_SUPPORTED_PHOTO = "jpg";
	protected static final String CONTENT_TYPE = "image/jpeg";
	protected static final String SHOW_IMAGE = "showImage";
	protected static final String LOCATION = System.getProperty("LOCATION_PHOTO") == null
			? System.getenv("LOCATION_PHOTO")
			: System.getProperty("LOCATION_PHOTO"); // Temporary location where files will be stored
	protected static final String LOCATION_PDF = System.getProperty("LOCATION_FILE_PDF") == null
			? System.getenv("LOCATION_FILE_PDF")
			: System.getProperty("LOCATION_FILE_PDF"); // Temporary location where files will be stored
	protected static final String PDF = "pdf";

	public static final long getMaxFileSize() {
		try {

			return System.getProperty("MAX_FILE_SIZE") == null ? Long.parseLong(System.getenv("MAX_FILE_SIZE"))
					: Long.parseLong(System.getProperty("MAX_FILE_SIZE"));// 5242880; // 5MB : Max file size.
		} catch (NumberFormatException e) {
		}
		return 5242880l;
	}
	protected static final String TYPE_PRODUCTS="typeProducts";
	protected static final String KEY_MAX_FILE_SIZE = "keyMaxFileSize";
	public static final int BUFFER_SIZE = 1024;
	protected static final String MESSAGE_ALERT_SIZE_PHOTO = "dashboard.student.alert.photo.size";
	protected static final String KEY_MESSAGE_ALERT_SIZE_PHOTO = "messageAlertSizePhoto";
	protected static final String MESSAGE_ALERT_ADD_STUDENT = "dashboard.student.addstudent.form.inscription";
	protected static final String KEY_MESSAGE_ALERT_ADD_STUDENT = "messageAlertAddStudent";
	protected static final String MESSAGE_ALERT_EDIT_STUDENT = "dashboard.student.addstudent.form.edit";
	protected static final String MESSAGE_PAYMENT_OK = "message.payment.ok";
	protected static final String MESSAGE_ERROR_AUTHENTICATION = "login.error.user.authentication";
	protected static final String MESSAGE_ERROR_SESSION = "error.payment.session";
	protected static final String LIST_ERR = "listError";
	protected static final String ERROR_EMAIL_STUDENT = "dashboard.student.validEmail";
	protected static final String ERROR_DUPLICATE_PAYMENT = "dashboard.payment.form.valid.duplicatePayment";
	protected static final String REGISTRATION_DATE = "dashboard.student.editstudent.registrationDate";
	protected static final String REGISTRATION_DATE_ATRIBUTE = "registrationDate";
	protected final static Gson gson = new Gson();
	public static final String LOCATION_FILE_SQL = System.getProperty("LOCATION_FILE_SQL") == null
			? System.getenv("LOCATION_FILE_SQL")
			: System.getProperty("LOCATION_FILE_SQL");
	public final static String QUERY_FILE_NAME = "bzk-query.xml";
	/**
	 * SQL OF REPORTS
	 */
	protected static final String SQL_STUDENTS_STATUS_1 = "SQL001";
	protected static final String SQL_RECEIPTS_REPORT = "SQL002";
	protected static final String SQL_RECEIPTS_REPORT_FOOD = "SQL003";
	protected static final String SQL_REPORT_FOR_DOWNLOAD = "SQL004";
	protected static final String SQL_REPORT_FOR_DOWNLOAD_FOOD = "SQL005";
	protected static final String SQL_ADMINISTRATIVE_REPORT = "SQL006";
	protected static final String SQL_ADMINISTRATIVE_REPORT_FOOD = "SQL007";
	protected static final String SQL_STATE_STUDENTS = "SQL008";
	protected static final String SQL_STATE_STUDENTS_FOOD = "SQL009";
	protected static final String SQL_PAYMENT_HISTORY_BY_STUDENT = "SQL010";
	protected static final String SQL_PAYMENT_HISTORY_BY_STUDENT_FOOD = "SQL012";
	protected static final String SQL_PAYMENT_HISTORY_BY_STUDENT_CARD = "SQL011";
	protected static final String SQL_CHART_TYPES_PAYMENT_VS_YEARS = "SQL013";
	protected static final String SQL_GET_TYPES_PRODUCTS = "SQL014";
	protected static final String SQL_STATE_STUDENTS_FOOD_NOT_ACTIVE="SQL015";

	protected static final String DESCPTION_STUDENTS = "descriptionStudents";
	protected static final String REPORT_OPTION = "reportOp";
	protected static final String REPORTS_VIEW = "reports/reports";
	protected static final String REPORT_VIEW_ADM_REPORT = "administrativeReport";
	protected static final String REPORT_VIEW_PAYMENT_HIST_STUDENT = "paymentHistoryBystudent";
	protected static final String REPORT_VIEW_RECEIPTS_REPORT = "receiptsReport";
	protected static final String REPORT_VIEW_REPORT_DOWNLOAD = "reportForDownload";
	protected static final Long START_SECUENCE_PAYMENT_NUMBER = 10000000L;
	protected static final String INITIAL_DATE = "initialDate";
	protected static final String FINAL_DATE = "finalDate";
	protected static final int DEFAULT_DAYS_FOR_SEARCH = -7;
	
	/**
	 * Contans report activate controls
	 */
	protected static final String VIEW_OPTION = "viewOp";
	protected static final String ACTIVATE_DATES_RANGES = "activateDatesRanges";
	protected static final String ACTIVATE_TYPE_PAYMEN_RECEIPTS = "activateTypePayMentRecipts";
	protected static final String ACTIVATE_TYPE_PRODUCT = "activeProducts";
	protected static final String ACTIVATE_PAYMENT_HISTORY_STUDENT = "activePaymentHistoryStudent";
	protected static final String ACTIVATE_STATE_STUDENT = "activeStateStudent";
	protected static final String REPORT_DETAIL_LIST = "reportDetailListtDto";
	protected static final String REPORT_FOOD_LIST = "reportFoodListDto";
	protected static final String REPORT_HEAD = "reportHeaderDTO";
	protected static final String REPORT_FOOD = "reportFood";
	protected static final String STATES_STUDENTS_ACCOUNT="stateStudentAccount";
	protected static final String EMPTY_LIST = "message.report.emptyList";
	protected static final String NAME_RECEIPTS = "dashboard.report.receiptsReport";
	protected static final String TITLE_RECEIPTS = "report.receiptsReport.title";
	protected static final String TITLE_REPORT_FOR_DOWNLOAD = "report.reportForDownload.title";
	protected static final String NAME_REPORT_FOR_DOWNLOAD = "report.reportForDownload.namefile";
	protected static final String TITLE_ADMINISTRATIVE_REPORT = "report.administrativeReport.title";
	protected static final String NAME_REPORT_ADMINISTRATIVE_REPORT = "report.administrativeReport.namefile";
	protected static final String TITLE_PAYMENT_HISTORY = "report.paymentHistory.title";
	protected static final String NAME_PAYMENT_HISTORY = "report.paymentHistory.name";
	protected static final String NAME_STUDENTS_REPORT = "report.stateStudentAccount.reportOfTheStateStudentAccount.namefile";
	protected static final String TITLE_STUDENTS_REPORT = "report.stateStudentAccount.reportOfTheStateStudentAccount.title";
	protected static final String TOTAL_PER_DAY = "report.administrativeReport.totalPerDay";
	protected static final String PROFILE_STUDENT = "profileStudent";
	
	protected static final String MESSAGE_ACTIVE="dashboard.report.stateStudentAccount.active";
	protected static final String MESSAGE_INACTIVE=	"dashboard.report.stateStudentAccount.inactive";
	protected static final String MAP_STATUS="status";

	protected static final String CATEGORY = "report.profile.category";
	protected static final String DOC_ID = "report.profile.docId";
	protected static final String TELEPHONE = "report.profile.telephone";
	protected static final String EMAIL = "report.profile.mail";
	protected static final String REGISTRATION_DATE_PROFILE = "report.profile.registrationDate";
	protected static final String YES_ES="SI";
	protected static final String NO_ES="NO";
	/*
	 * About BZK
	 */
	protected static final String ENABLE_ABOUT = "aboutEnable";
	protected static final String APP_VERSION = "appVersion";
	protected static final String APP_DEVELOPER = "developer";
	protected static final String APP_LICENCE = "licence";
	/**
	 * Cell of table receipt
	 */
	protected static final String NAMES_CELL_TABLE[] = { "dashboard.payment.form.nameStudent",
			"dashboard.payment.form.productSelec", "dashboard.report.administrativeReport.amount",
			"payment.receipt.ITBM", "dashboard.payment.form.productTotal", };
	protected static final String PROOF_OF_PAYMENT = "payment.receipt.proofOfPayment";
	protected static final String DATE_REGISTER = "dashboard.payment.form.dateRegister";
	protected static final String PARAGRAPH_1 = "payment.receipt.paragraph.1";
	protected static final String SUB_TOTAL = "dashboard.payment.form.subTotal";
	protected static final String TOTAL = "dashboard.payment.form.total";
	protected static final String TAX = "dashboard.payment.form.tax";
	/**
	 * System parameter
	 */
	protected static final String PARAMETER_DELETE_PDF = "DELETE_PDF";
	protected static final String PARAMETER_YEAR_AGO = "YEAR_AGO";
	protected static final String PARAMETER_NUMBER_OF_DAYS_SEARCH_HISTORY = "NUMBER_OF_DAYS_SEARCH_HISTORY";
	/**
	 * Error form product
	 */
	protected static final String ERROR_FORM_PRODUCT = "errorProducts";
	/**
	 * System code delete/edit
	 */
	protected static final String CODE_DETELE = "1002";
	protected static final String CODE_EDIT = "1001";
	protected static final String CODE_NOTFOUND="999";
	/**
	 * User account
	 */
	protected  static  final String FORM_ACCION_ACCOUNT_MAINTENANCE = "formActionAccount";
	protected  static  final String ACCOUNT_FORM_OBJ = "accountForm";
	protected  static  final String FORM_EDIT_PERSOANLA_INFO= "formActionEditPersonalInfo";
	protected  static  final String EDIT_PERSOANLA_INFO_FORM_OBJ = "personalInfoForm";
	
	protected  static  final String LIST_ROLES = "listRole";
	protected  static final Short MIN_LENGTH_PASSWORD = 8;
	protected  static final String ACCOUNT_OK = "dashboard.account.add.ok";
	protected  static final String ACCOUNT_OK_UPDATE = "dashboard.account.error.addAccount";
}

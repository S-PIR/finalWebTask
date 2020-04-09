package by.epamlab.constants;

import by.epamlab.model.beans.CategoryType;

public interface Constants {

    String LOGGED_OUT_USER = "Logged out user: ";
    String LOGIN_ATTEMPT_FAILED = "Login attempt failed";
    String LOGGED_IN_USER = "Logged in user: ";
    String NEW_USER_REGISTERED = "New user registered: ";
    String NEW_PRODUCT_REGISTERED = "New product was registered: ";
    String PRODUCT_UPDATED = "Existed product was updated: id = ";
    String PRODUCT_DELETED = "Product was deleted, id = ";
    String NO_PRODUCT_IMAGE = "nophoto.jpg";
    String EMPTY_FILE_STORING_ATTEMPT = "Empty file storing attempt was registered.";

    public static final String NO_FILE = "no file";
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String KEY_EMPTY = "";
    public static final String KEY_DAO = "dao";
    public static final String NULL_ERROR = "Necessary fields are missing.";
    public static final String EMPTY_ERROR = "Necessary fields are empty.";
    public static final String DATE_FORMAT_ERROR = "Wrong date format, yyyy-mm-dd format needed.";
    public static final String LOGIN_PASSWORD_ERROR = "Wrong login or password.";
    public static final String PASSWORD_REPEAT_ERROR = "Passwords a not equal.";
    public static final String LOGIN_EXISTS_ERROR = "This login has already taken. Try again.";
    public static final String SOURCE_ERROR = "Input source proccessing problems.";
    public static final String CLOSE_RESOURCE_ERROR = "problem with closing resource";
    public static final String CONNECTION_ERROR = "problem with connection to DB";
    public static final String NO_FILE_ERROR = "No file selected or file name (path) error.";
    public static final String SAVE_PATH = "aploadFiles";
    public static final String KEY_USER = "user";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EDIT_FUNCTION = "paramEdit";
    public static final String KEY_ID = "id";
    public static final String KEY_FILE_NAME_TASK = "fileName";
    public static final String KEY_FILE = "file";
    public static final String KEY_FILE_ACTION = "fileAction";

    public static final String DRIVER_NAME = "org.gjt.mm.mysql.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost/web";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "";

    public static final String UNUSED_CONSTANT = "Emptyness";

}

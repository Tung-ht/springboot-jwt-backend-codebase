package com.sapo.mock.techshop.common.constant;

public class HttpStatusConstant {
    // success
    public final static String SUCCESS_CODE = "00000";
    public final static String SUCCESS_MESSAGE = "Successful";

    // unknown error
    public final static String UNAVAILABLE_CODE = "99999";
    public final static String UNAVAILABLE_MESSAGE = "Something wrong!";

    // sql exception
    public final static String SQL_CONNECTION_ERROR_CODE = "00001";
    public final static String SQL_CONNECTION_ERROR_MESSAGE = "Some thing wrong with connection of database!";

    // unauthorized
    public final static String UNAUTHORIZED_CODE = "00010";
    public final static String UNAUTHORIZED_MESSAGE = "You are unauthorized!";
    public final static String AUTHENTICATION_FAIL_CODE = "00011";
    public final static String AUTHENTICATION_FAIL_MESSAGE = "Authentication failed!";

    // null pointer exception
    public final static String NULL_POINTER_OR_BAD_REQUEST_CODE = "00020";
    public final static String NULL_POINTER_OR_BAD_REQUEST_MESSAGE = "You passed wrong or blank input data!";

    // not found
    public final static String NOT_FOUND_CODE = "00030";
    public final static String INVENTORY_NOT_FOUND_MESSAGE = "Inventory not found!";
    public final static String CATEGORY_NOT_FOUND_MESSAGE = "Category not found!";
    public final static String PRODUCT_NOT_FOUND_MESSAGE = "Product not found!";

    // invalid data
    public final static String INVALID_DATA_CODE = "00040";
    public final static String INVALID_DATA_MESSAGE = "Please input valid data!";
}
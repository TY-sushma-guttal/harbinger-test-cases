package com.example.courtathome.constant;

public class ExceptionConstant {

	private ExceptionConstant() {
		super();
	}

	public static final String VEHICLE_NOT_FOUND = "Vehicle No not found, please register vehicle first";

	public static final String NO_ACTIVE_CASES = "Something went wrong, please enter valid MobileNo/vehicleNo and status & try again, Ride safely";

	public static final String LOGIN_FAILED = "login failed, Invalid credentials";

	public static final String USER_UPDATE_FAILED = "User Update Failed, Wrong Password ";
	public static final String USER_ALREADY_PRESENT = "User already present, please register with new email";
	public static final String USER_NOT_FOUND = "User not present, please check your mobileNo or register yourself first";

	public static final String INVALID_PASSWORD = "Update failed, please check your password";
	public static final String SOMETHING_WENT_WRONG = "Something Went Wrong, please try again";

	public static final String EXCEPTION_RAISED = "Throwed Exception in else block";
}

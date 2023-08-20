package com.furru.validator;


import com.furru.exception.WanderLustException;

public class UserValidator {

	public static void validateUserForLogin(String contactNumber, String password) throws WanderLustException {

		if (!validateContactNumber(contactNumber))
			throw new WanderLustException("UserValidator.INVALID_CONTACT_NUMBER_FORMAT");

		if (!validatePassword(password))
			throw new WanderLustException("UserValidator.INVALID_PASSWORD_FORMAT");
	}

	public static void validateUserForRegistration(String userName, String emailId, String contactNumber, String password) throws Exception{
		if (!validateUserName(userName))
			throw new Exception("UserValidator.INVALID_USER_NAME_FORMAT");
		else
		if (!validateEmailId(emailId))
			throw new Exception("UserValidator.INVALID_EMAILID_FORMAT");
		else
		if (!validateContactNumber(contactNumber))
			throw new Exception("UserValidator.INVALID_CONTACT_NUMBER_FORMAT");

		else
			throw new Exception("UserValidator.INVALID_PASSWORD_FORMAT");
	}


	public static Boolean validatePassword(String password) {
		if (password == null)
			return false;
		Boolean flag = false;
		if (password.length() >= 7 && password.length() <= 20)
			if (password.matches(".*[A-Z]+.*"))
				if (password.matches(".*[a-z]+.*"))
					if (password.matches(".*[0-9]+.*"))
						if (password.matches(".*[!@#$%^&*].*"))
							flag = true;
		return flag;
	}

	public static Boolean validateContactNumber(String contactNumber) {
		if (contactNumber == null)
			return false;
		Boolean flag = false;
		if (contactNumber.matches("[6-9][0-9]{9}"))
			flag = true;
		return flag;
	}

	public static Boolean validateUserName(String name) {
		if (name == null || name.trim().length() != name.length())
			return false;
		return name.matches("[A-Za-z ]+");
	}

	public static Boolean validateEmailId(String emailId) {
		if (emailId == null)
			return false;
		return emailId.matches("[A-Za-z]+@[A-Za-z]+[.]com");
	}
	

}

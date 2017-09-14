package com.amazonaws.lambda.demo.service;

import com.amazonaws.lambda.demo.dto.User;
import com.amazonaws.services.lambda.runtime.Context;

public interface IUserService {
	boolean updateOrInsertUser(Context context, User user);
}

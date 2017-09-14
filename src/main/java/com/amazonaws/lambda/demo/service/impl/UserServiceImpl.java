package com.amazonaws.lambda.demo.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.amazonaws.lambda.demo.dto.User;
import com.amazonaws.lambda.demo.service.IUserService;
import com.amazonaws.lambda.demo.util.Constant;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class UserServiceImpl implements IUserService {

	@Override
	public boolean updateOrInsertUser(Context context, User user) {
		LambdaLogger logger = context.getLogger();
		if (user.getUserName() == null || user.getUserName().equals("") || user.getPassword() == null)
			return false;
		try {
			Class.forName(Constant.MYSQL_DRIVER);
			Connection conn = DriverManager.getConnection(Constant.MYSQL_URL, Constant.MYSQL_USER,
					Constant.MYSQL_PASSWORD);
			if (!conn.isClosed()) {
				logger.log("[" + new Date() + "]connect to database success.");
			}
			Statement statement = conn.createStatement();
			String sql = "";
			if (user.getUserId() == 0) {
				sql = "insert into user set user_name='" + user.getUserName() + "',user_info='" + user.getUserInfo()
						+ "',password='" + user.getPassword() + "'";
			} else {
				sql = "update user set ";
				if (user.getUserName() != null) {
					sql += " user_name='" + user.getUserName() + "',";
				}
				if (user.getUserInfo() != null) {
					sql += " user_info='" + user.getUserInfo() + "',";
				}
				if (user.getPassword() != null) {
					sql += " password='" + user.getPassword() + "',";
				}
				sql = sql.substring(0, sql.length() - 1);
				sql += " where user_id=" + user.getUserId();
			}
			logger.log("[" + new Date() + "]sql:" + sql);
			statement.execute(sql);
			conn.close();
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}

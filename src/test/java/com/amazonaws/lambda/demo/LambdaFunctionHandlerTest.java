package com.amazonaws.lambda.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.lambda.demo.dto.RequestModel;
import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class LambdaFunctionHandlerTest {

    private static RequestModel input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testLambdaFunctionHandler() {
        LambdaFunctionHandler handler = new LambdaFunctionHandler();
        Context ctx = createContext();
        input = new RequestModel();
//        input.setToken("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MDUyMjUzNjAsInN1YiI6IntcInVzZXJOYW1lXCI6XCJhZG1pblwiLFwidXNlcklkXCI6NH0iLCJleHAiOjE1MDUyMjg5NjB9.9ZiJwmLHfSCvhY5wsLsCsBxdj1eTjzGlD_QUuJGnkrs");
        input.setUserId(0);
        input.setUserName("");
        input.setUserInfo("");
        input.setPassword("");
        String output = handler.handleRequest(input, ctx);
        System.out.println(output);
        // TODO: validate output here if needed.
//        Assert.assertEquals("Hello from Lambda!", output);
    }
}

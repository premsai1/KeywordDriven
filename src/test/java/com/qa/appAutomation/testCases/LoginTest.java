package com.qa.AppAutomation.testCases;

import org.testng.annotations.Test;

import com.qa.AppAutomation.Keyword.engine.KeyWordEngine;
/**
 * @author Premsai Manthani
 * @purpose this class performs the execution of the keyword driven
 * 
 */

public class LoginTest {

	public KeyWordEngine keyWordEngine;
	
	@Test
	public void loginTest() {
		
		keyWordEngine= new KeyWordEngine();
		keyWordEngine.startExecution("login");
		
		
	}
}

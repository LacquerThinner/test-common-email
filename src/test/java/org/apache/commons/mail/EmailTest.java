//Programmer: Kyle Driver
//Date: 3/21/2021

package org.apache.commons.mail;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.mail.Session;
import javax.mail.internet.MimeMultipart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {

	private static final String[] TEST_EMAILS = { "Test@Email.com", "Email@Test.com",
	"TestEmail@EmailTest.org" };
	
	private static final String[] TEST_INVALID_EMAILS = { };
	
	/* Concrete Email Class for testing */
	private EmailConcrete email;
	
	/*creates an email object*/
	@Before
	public void setUpEmailTest() throws Exception {
		
		email = new EmailConcrete();
		
	}
	
	@After
	public void tearDownEmailTest() throws Exception {
		
	}

	/*Tests addBcc with a list of test emails*/
	@Test
	public void testAddBcc() throws Exception {
		
		email.addBcc(TEST_EMAILS);
		
		assertEquals(3, email.getBccAddresses().size());
	}
	/*Tests addBcc throws an email exception if a list of invalid emails is given*/
	@Test(expected=EmailException.class)
	public void testEmailExceptionAddBcc() throws Exception {
		
		email.addBcc(TEST_INVALID_EMAILS);
		
	}
}

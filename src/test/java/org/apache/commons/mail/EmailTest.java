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

	/*Tests addCc with a list of test emails*/
	@Test
	public void testAddCc() throws Exception {
		
		for (int i = 0; i < 3; i++) {
			email.addCc(TEST_EMAILS[i]);
		}
		
		assertEquals(3,email.getCcAddresses().size());
	}

	/*Tests addHeader with a test name and value*/
	@Test
	public void testAddHeader() throws Exception {
		
		email.addHeader("testName", "testValue");
		
	}
	/*Tests that addHeader throws an illegal argument exception if name is given an empty string*/
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyNameAddHeader() throws Exception {
		
		email.addHeader("", "testValue");
		
	}
	/*Tests that addHeader throws an illegal argument exception if value is given an empty string*/
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyValueAddHeader() throws Exception {
		
		email.addHeader("testName", "");
		
	}

	/*Tests addReplyTo with test emails*/
	@Test
	public void testAddReplyTo() throws Exception {
		
		for (int i = 0; i < 3; i++) {
			email.addReplyTo(TEST_EMAILS[i], "ReplyTest");
		}
		
		assertEquals(3,email.getReplyToAddresses().size());
		
	}

	/*Tests a successful buildMimeMessage with with the minimum amount of info to work*/
	@Test
	public void testBuildMimeMessage() throws Exception {
		
		/*sets up host name, sender, and recipients needed to build a mime message*/
		email.setHostName("HostName");
		email.setFrom("test@email.org");
		email.addTo("testemail@emailtest.com");
		
		email.buildMimeMessage();
		
	}
	/*Tests a successful buildMimeMessage with a MimeMultipart body plus extras*/
	@Test
	public void testBodyBuildMimeMessage() throws Exception {
		
		/*sets up host name, sender, recipients, body, Cc list, Bcc list, header, and reply list to build a mime message*/
		email.setHostName("HostName");
		email.setFrom("test@email.org");
		email.addTo("testemail@emailtest.com");
		email.setContent(new MimeMultipart());
		for (int i = 0; i < 3; i++) {
			email.addCc(TEST_EMAILS[i]);
		}
		email.addBcc(TEST_EMAILS);
		email.addHeader("testHeader", "HeaderTest");
		for (int i = 0; i < 3; i++) {
			email.addReplyTo(TEST_EMAILS[i], "ReplyTest");
		}
		
		
		email.buildMimeMessage();
		
	}
	/*Tests a successful buildMimeMessage with an object body*/
	@Test
	public void testContentBuildMimeMessage() throws Exception {
		
		/*sets up host name, sender, and recipients needed to build a mime message*/
		email.setHostName("HostName");
		email.setFrom("test@email.org");
		email.addTo("testemail@emailtest.com");
		email.setContent(new Object(), "String");
		
		email.buildMimeMessage();
		
	}
	/*Tests the if the illegal state exception is thrown if buildMimeMessage() is called twice*/
	@Test(expected=IllegalStateException.class)
	public void testIllegalStateBuildMimeMessage() throws Exception {
		
		/*sets up host name, sender, and recipient needed to build a mime message*/
		email.setHostName("HostName");
		email.setFrom("test@email.org");
		email.addTo("testemail@emailtest.com");
		
		email.buildMimeMessage();
		email.buildMimeMessage();
		
	}
	/*Tests the if the email exception is thrown if buildMimeMessage() is called without initializing a sender*/
	@Test(expected=EmailException.class)
	public void testNoSenderBuildMimeMessage() throws Exception {
		
		/*sets up host name and recipient but no sender*/
		email.setHostName("HostName");
		email.addTo("testemail@emailtest.com");
		
		email.buildMimeMessage();
		
	}
	/*Tests the if the email exception is thrown if buildMimeMessage() is called without initializing a recipient*/
	@Test(expected=EmailException.class)
	public void testNoRecipientBuildMimeMessage() throws Exception {
		
		/*sets up host name and sender but no recipient*/
		email.setHostName("HostName");
		email.setFrom("test@email.org");
		
		email.buildMimeMessage();
		
	}

	/*Tests getHostName with a host name set up first*/
	@Test
	public void testGetHostName() throws Exception {
		
		/*sets up host name*/
		email.setHostName("testHost");
		
		String actualResult = email.getHostName();
		assertEquals("testHost", actualResult);
	}
	/*Tests getHostName without setting up a host name first*/
	@Test
	public void testNullGetHostName() throws Exception {
		
		String actualResult = email.getHostName();
		assertEquals(null, actualResult);
	}
}
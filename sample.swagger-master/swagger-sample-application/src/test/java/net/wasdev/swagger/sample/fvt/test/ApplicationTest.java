package net.wasdev.swagger.sample.fvt.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.junit.Test;


public class ApplicationTest {

	@Test
	public void testContacts(){
		//check if endpoint works, should return empty JSON Array
		assertEquals("[]", getResponseString("/swagger-sample/demo/contacts"));
	}

	@Test
	public void testTasks(){
		//check if endpoint works, should return empty JSON Array
		assertEquals("[]", getResponseString("/swagger-sample/demo/tasks"));
	}

	@Test
	public void verifyDocs() {
		//verify that auto-generated Swagger contains both API's
		String docs = getResponseString("/swagger-sample/swagger.json");

		System.out.println(docs);
		assertNotNull(docs);
		assertTrue(docs.contains("Contacts API"));
		assertTrue(docs.contains("Tasks API"));
	}


	/* Get response as String type */
	@Test
	private String getResponseString(String mappingUrl){
		Client client = ClientBuilder.newClient();
		String port = System.getProperty("liberty.test.port");
		String url = "http://localhost:" + port + mappingUrl;
		System.out.println("Testing " + url);

		Response getResponse = client.target(url).request().get();
		String responseString = getResponse.readEntity(String.class);
		return responseString;
	}
}

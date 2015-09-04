package com.kainos.training.blackbox.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.kainos.training.blackboxinterface.model.person.Person;

public class FriendClient {
	private WebTarget webTarget;

	public FriendClient() {
		Client client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:8910").path("/person");
	}

	public Response addFriend(Person friend) {
		if (null == friend || friend.getName().isEmpty()) {
			return Response.status(400).build();
		}
		return webTarget.request().post(
				Entity.entity(friend, MediaType.APPLICATION_JSON));
	}

	public List<Person> getFriendsList() {
		Response response = webTarget.request().get();
		return response.readEntity(new GenericType<List<Person>>() {
		});
	}

}

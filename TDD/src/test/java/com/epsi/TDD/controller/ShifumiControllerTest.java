package com.epsi.TDD.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@WebMvcTest(ShifumiController.class)
public class ShifumiControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ShifumiController shifumiController;

	@Test
	public void restartTest() throws Exception {
		MvcResult result = mockMvc.perform(post("/game/restart"))
				.andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertEquals("win : 0 / lose : 0 | tie : 0", content);
	}

	@Test
	public void getScoreTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/game/score"))
				.andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertEquals("win : 0 / lose : 0 | tie : 0", content);
	}

	@Test
	void testSetScore() throws Exception{
		MvcResult result = mockMvc.perform(put("/game/score/2/3/1"))
				.andExpect(status().isOk())
				.andReturn();

		assertEquals("win : 2 / lose : 3 | tie : 1", shifumiController.getScore());
	}

	@ParameterizedTest
	@CsvSource( value = {"pierre", "feuille", "ciseau"})
	public void startTest(String choix) throws Exception {


			MvcResult result = mockMvc.perform(get("/game/play/" + choix))
					.andExpect(status().isOk())
					.andReturn();

			// Vérification que le résultat est bien conforme à l'action choisie
			String content = result.getResponse().getContentAsString();
	}


}

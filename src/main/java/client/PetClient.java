package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Category;
import model.Pet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class PetClient {

    public HttpResponse getPetsByStatus(String petStatus) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://petstore.swagger.io/v2/pet/findByStatus?status="+petStatus);
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return httpResponse;
    }

    private String getMockPets() {

        List<Pet> pets= new ArrayList<>();
        Pet pet= new Pet();
        pet.setId((long) 12345678);
        pet.setName("Tommy");
        pet.setStatus(Pet.StatusEnum.AVAILABLE);
        Category category= new Category();
        category.setId((long) 124256);
        category.setName("doggie");

        pet.category(category);
        pets.add(pet);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = null;
        try {
            json = mapper.writeValueAsString(pets);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}



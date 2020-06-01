package steps;

import client.PetClient;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.BasicResponseHandler;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;



public class PetStoreSteps {
    String status;
    PetClient petClient;
    HttpResponse response;

    public static String jsonAsString;

    @Before
    public void initialise() {
        petClient = new PetClient();
    }

    @Given("I status is {string}")
    public void i_status_is(String status) {
        this.status = status;
    }


    @When("I submit the request")
    public void i_submit_the_request() {
        //gets the httpResponse from the client
        response = petClient.getPetsByStatus(status);
        //convert httpResponse into string
        try {
            jsonAsString = new BasicResponseHandler().handleEntity(response.getEntity());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        //sysout the response and statuscode
        System.out.println("Response :" + jsonAsString);
        System.out.println("Status Code :" + response.getStatusLine().getStatusCode());
    }

    @Then("I should see responsecode {int}")
    public void i_should_see_responsecode(int statuscode) {
        //check status code is 200
        Assert.assertTrue("expected statusCode did not match with actual",response.getStatusLine().getStatusCode()==(statuscode));
    }

    @Then("I should see {string} results for {string} with status {string}")
    public void i_should_see_results_for(String count, String petName,String status) {

        //helper method to filter status “available” and name “doggie” from response
        List<String> listOfPets = null;

        try {
            listOfPets = JsonHelper.filterJsonArray(jsonAsString, "name", petName, "status", status);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //print all the filtered values
        listOfPets.forEach(k -> System.out.println(k));

        //validate how many pets have the status “available” and “doggie”
        Assert.assertTrue("Failed as expected count:"+count+ " did not match with actual:"+listOfPets.size(),listOfPets.size()==Integer.parseInt(count));
        System.out.println("total list of array:" + listOfPets.size());

    }

}

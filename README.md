# com.petstore.simple

Uses: Maven project with Cucumber

src->main->java->client- Add the api methods to call where it makes a call to live endpoint and return the httpresponse.

src->main->test->java->steps-jsonhelper - json helper method to filter the key and values within the response array.

Cucumber reports to print the scenarios with the execution status and write any errors if it accurs.

Framework currently depends on the live endpoint hence the test may fail as the expected count does not match with returned results, so best way to enhance this is using wiremock or anythoer mocking libraries so the actual resposne can be stubbed.

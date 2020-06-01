Feature: GetPetsByStatus


@apiTest
  Scenario: verify the number of results for GetPetsByStatus
    Given I status is "available"
    When I submit the request
    Then I should see responsecode 200
    And I should see "1" results for "doggie" with status "available"


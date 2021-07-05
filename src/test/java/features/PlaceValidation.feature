Feature: Validating Place API's

  @AddPlace
  Scenario Outline: Verify the place is successfully added via Google Map API
    Given i sent the request for Add place with "<name>" "<langauge>" "<address>"
    When i calls "AddPlaceAPI" with "Post" http request
    Then i should see the successful response with the status code  200
    And i see the "status" in the response in the body as "OK"
    And i see the "scope" in the response in the body as "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name  | langauge | address                       |
      #| Test123 | Telugu   | dasassadasdas                 |
      | Raghu | Kannada  | Near Regency Pinnacle Heights |

  @DeletePlace
  Scenario: Verify the place is successfully deleted the via Google Map API
    Given DeletePlace Payload
    When i calls "deletePlaceAPI" with "Post" http request
    Then i should see the successful response with the status code  200
    And i see the "status" in the response in the body as "OK"
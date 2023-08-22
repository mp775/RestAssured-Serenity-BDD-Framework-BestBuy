Feature: Testing different request on the products application

  Scenario: Check if the products application can be accessed by users
    When User sends a GET request to list endpoint
    Then User must get back a valid status code 200

  Scenario Outline: Create a new products & verify if the product is added
    When I create a new user by providing the information name "<name>" type "<type>" upc "<upc>" price "<price>"description "<description>"model "<model>"
    Then I verify that the user with "<name>","<type>","<upc>","<price>","<description>","<model>" is created
    Examples:
      | name        | type      | upc       | price | description            | model     |
      | shweta shah | hard good | 123456789 | 99    | This is a placeholder  | NP12345   |
      | sagar shah  | soft good | 234569852 | 55    | This is a manufacturer | NC 125689 |

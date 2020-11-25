@AccountServiceFeature
Feature: Account
  Description of our feature

  Scenario: Account Initial Elements exist
    When I send a GET request to root
    Then I should get the list of elements
    """
   [
    {
        "id": 1,
        "customerId": 1,
        "number": "111111"
    },
    {
        "id": 2,
        "customerId": 2,
        "number": "222222"
    },
    {
        "id": 3,
        "customerId": 3,
        "number": "333333"
    },
    {
        "id": 4,
        "customerId": 4,
        "number": "444444"
    },
    {
        "id": 5,
        "customerId": 1,
        "number": "555555"
    },
    {
        "id": 6,
        "customerId": 2,
        "number": "666666"
    },
    {
        "id": 7,
        "customerId": 2,
        "number": "777777"
    }
]
  """
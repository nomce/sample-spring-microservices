*** Settings ***
Documentation         Robot Framework tests
Library               Collections
Library               RequestsLibrary

*** Test Cases ***
Account Initial Elements exist 03
    [Setup]     Create Accounts-Service session
    ${resp}=          Get request    accounts     /
    Verify Structure   ${resp.json()}
    Verify Accounts Data        ${resp.json()}

Account Retrieve Existing Element On Root Endpoint 04
    [Setup]     Create Accounts-Service session
    When A GET request is sent on endpoint  /1
    Then A JSON account object is received with data    1   1   111111

*** Keywords ***
A GET request is sent on endpoint
    [Arguments]     ${endpoint}
    ${response} =          Get request    accounts     ${endpoint}
    Set Test Variable       ${response}

A JSON account object is received with data
    [Arguments]     ${id}       ${customerId}   ${number}
    Dictionary Should Contain Item   ${response.json()}     id              ${id}
    Dictionary Should Contain Item   ${response.json()}     customerId      ${customerId}
    Dictionary Should Contain Item   ${response.json()}     number          ${number}

Create Accounts-Service session
    Create Session    accounts         http://localhost:2222

Verify Structure
    [Arguments]     ${list}
    FOR     ${item}     IN    @{list}
        Dictionary Should Contain Key   ${item}     id
        Dictionary Should Contain Key   ${item}     customerId
        Dictionary Should Contain Key   ${item}     number
    END

Verify Accounts Data
    [Arguments]     ${list}
    ${values} =     Create List         111111  222222  333333  444444  555555  666666  777777
    FOR     ${item}     IN    @{list}
        List Should Contain Value       ${values}       ${item['number']}
    END
## Assignment #2 - TVVS

---

- Filipe Cerqueira - 202204408
- João Polónia - 201208144

## Black Box Testing

Black box testing is a technique of software testing which examines the functionality of software without peering into its internal structure or coding.

## Description of the Tests produced

1º Function:

- **Name:** stripString
- **Why:** 
- **Purpose:** The main purpose of this method is to concatenate the given string in number of characters given in the parameters.
- **Test Description:** The test was giving the same string with different length values expecting it to match the expected values. 
- **Outcome Description:** The outcome was has expected, for any value besides the size it always concatenated to the length pretended.
- **Did it fail? Why?:**  No.
- **Commit**: [983dfde](https://github.com/jose/jpass/commit/983dfdeb2a649b6e153ec6ebce2e11ed6064f871)


2º Function:

- **Name:** SetClipboardContent
- **Why:** 
- **Purpose:** The main purpose of this method is to set a value on computer clipboard.
- **Description:** On the test it was set a value to the computer clipboard and then compared with the value from the method that gets the computer clipboard to make sure the value was the same.  
- **Outcome:** For any value tested, the result was always success.
- **Did it fail? Why?:** No.
- **Commit**: [87c48b6](https://github.com/jose/jpass/commit/87c48b668ca5933e6bdb9ab1ea5148ea587f00fd)


3º Function:

- **Name:** StripNonValidXMLCharacters
- **Why:** 
- **Purpose:** The main purpose of this method is to confirm whether the string contains any non-valid XML characters, and if so change it to a "?".
- **Description:** During the test it was used a string that was non-valid XML char, and it changed to a question mark and a normal value and as expected it didn't change so the test failed.
- **Outcome:** The outcome for the non-valid XMl was has expected and for the valid XML char it reproduced a failure on the test has we predicted.
- **Did it fail? Why?:** Yes, because during the test we used a valid XML char that did not return a question mark, which caused the assertEquals to not return a positive result.
- **Commit**: [46da58a](https://github.com/jose/jpass/commit/46da58a2f257f4a0fdb2cd06c9f69a5b8f9f5b07)


4º Function:

- **Name:**
- **Why:**
- **Purpose:**
- **Description:**
- **Outcome:**
- **Did it fail? Why?:**
- **Commit**: [6b2216e](https://github.com/jose/jpass/commit/6b2216e2090fc2ed169bc4cb3ce29422b4ebfd5a)


5º Function:

- **Name:**
- **Why:**
- **Purpose:**
- **Description:**
- **Outcome:**
- **Did it fail? Why?:**
- **Commit**: [6b2216e](https://github.com/jose/jpass/commit/6b2216e2090fc2ed169bc4cb3ce29422b4ebfd5a)

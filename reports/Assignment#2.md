## Assignment #2 - TVVS

---
- Which functions have you selected for testing and why.
- What is the purpose of each function.
- Step-by-step of the ‘Category-Partition’ algorithm for each function.
- Brief description of the unit tests generated for each category.
- Brief description of the outcome of each unit test and whether any test results in a failure (and why).


- Filipe Cerqueira - 202204408
- João Polónia - 201208144

## Black Box Testing

Black box testing is a technique of software testing which examines the functionality of software without peering into its internal structure or coding.

## Selected functions for testing

Most functions are not well suited for Black-Box testing because they lack some necessary documentation for example:
 - missing input and output descriptions: (ex: [SearchPanel](https://paginas.fe.up.pt/~jcmc/tvvs/2022-2023/assignments/jpass-javadoc/jpass/ui/SearchPanel.html))
 - exception cases: states the function should generate an exception but omits in which cases (ex: [CryptoUtils](https://paginas.fe.up.pt/~jcmc/tvvs/2022-2023/assignments/jpass-javadoc/jpass/util/CryptUtils.html))

The functions used for testing were selected based on the ones with most documentation available in the [Javadoc](https://paginas.fe.up.pt/~jcmc/tvvs/2022-2023/assignments/jpass-javadoc/index.html)


1º Function:

- **Name:** stripString
- **Purpose:** The main purpose of this method is to concatenate the given string in number of characters given in the parameters.
- **Test Description:** The test was giving the same string with different length values expecting it to match the expected values. 
- **Outcome Description:** The outcome was has expected, for any value besides the size it always concatenated to the length pretended.
- **Did it fail? Why?:**  No.
- **Commit**: [983dfde](https://github.com/jose/jpass/commit/983dfdeb2a649b6e153ec6ebce2e11ed6064f871)


2º Function:

- **Name:** SetClipboardContent
- **Purpose:** The main purpose of this method is to set a value on computer clipboard.
- **Description:** On the test it was set a value to the computer clipboard and then compared with the value from the method that gets the computer clipboard to make sure the value was the same.  
- **Outcome:** For any value tested, the result was always success.
- **Did it fail? Why?:** No.
- **Commit**: [87c48b6](https://github.com/jose/jpass/commit/87c48b668ca5933e6bdb9ab1ea5148ea587f00fd)


3º Function:

- **Name:** StripNonValidXMLCharacters
- **Purpose:** The main purpose of this method is to confirm whether the string contains any non-valid XML characters, and if so change it to a "?".
- **Description:** During the test it was used a string that was non-valid XML char, and it changed to a question mark and a normal value and as expected it didn't change so the test failed.
- **Outcome:** The outcome for the non-valid XMl was has expected and for the valid XML char it reproduced a failure on the test has we predicted.
- **Did it fail? Why?:** Yes, because during the test we used a valid XML char that did not return a question mark, which caused the assertEquals to not return a positive result.
- **Commit**: [46da58a](https://github.com/jose/jpass/commit/46da58a2f257f4a0fdb2cd06c9f69a5b8f9f5b07)


4º Function:

- **Name:** CryptUtils.getSha256Hash
- **Purpose:** Calculate SHA-256 hash
- **Description:**
- **Outcome:**
- **Did it fail? Why?:** No
- **Commit**: [6b2216e](https://github.com/jose/jpass/commit/6b2216e2090fc2ed169bc4cb3ce29422b4ebfd5a)


5º Function:

- **Name:**
- **Purpose:**
- **Description:**
- **Outcome:**
- **Did it fail? Why?:**
- **Commit**: [6b2216e](https://github.com/jose/jpass/commit/6b2216e2090fc2ed169bc4cb3ce29422b4ebfd5a)

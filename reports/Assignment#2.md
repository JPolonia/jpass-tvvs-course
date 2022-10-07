## Assignment #2 - TVVS

---

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

- **Name:** StringUtils.stripString
- **Purpose:** The main purpose of this method is to concatenate the given string in number of characters given in the parameters.
- **Test Description:** The test was giving the same string with different length values expecting it to match the expected values. 
- **Outcome Description:** The outcome was has expected, for any value besides the size it always concatenated to the length pretended.
- **Category Partition Algorithm:**

    - Parameters:
        - String text
        - int length
    - Characteristics:
        - text:
            - null
            - empty
            - not empty
        - length:
            - null
            - zero
            - greater than zero
    - Restrictions:
        - If length is null other inputs are ignored
        - if length is zero other inputs are ignored
    - Combination tests:
        - INPUT length is null -> OUTPUT exception -> PASS
        - INPUT length is zero -> OUTPUT text -> PASS
        - INPUT length > 0 and text is null -> OUTPUT text -> PASS
        - INPUT length > 0 and text is empty -> OUTPUT text -> PASS
        - INPUT length > 0 and text is not empty -> OUTPUT text stripped -> PASS
    
2º Function:

- **Name:** ClipboardContent.SetClipboardContent
- **Purpose:** The main purpose of this method is to set a value on computer clipboard.
- **Category Partition Algorithm:**

    - Parameters:
      - String str 
      - System Clipboard
    - Characteristics:
        - str:
            - null 
            - empty
            - not empty
        - System Clipboard:
            - empty
            - not empty
            - accessible
            - not accessible
    - Restrictions:
        - If the System Clipboard is not accessible other inputs are ignored
        - If str is null other inputs are ignored
    - Combination tests:
        - INPUT System Clipboard is not accessible -> OUTPUT exception -> PASS
        - INPUT str is null -> OUTPUT exception -> PASS
        - INPUT System Clipboard is empty and accessible and str is empty -> Clipboard should be empty
        - INPUT System Clipboard is empty and accessible and str is not empty -> OUTPUT Clipboard should be equal to str -> PASS
        - INPUT System Clipboard is not empty and accessible and str is not empty -> OUTPUT Clipboard should be equal to str -> PASS

3º Function:

- **Name:** StringUtils.StripNonValidXMLCharacters
- **Purpose:** The main purpose of this method is to confirm whether the string contains any non-valid XML characters, and if so change it to a "?".
- **Description:** During the test it was used a string that was non-valid XML char, and it changed to a question mark and a normal value and as expected it didn't change so the test failed.
- **Outcome:** The outcome for the non-valid XMl was has expected and for the valid XML char it reproduced with the question mark.
- **Category Partition Algorithm:**

    - Parameters:
        - String in
    - Characteristics:
        - in:
            - null
            - empty
            - not empty and contains non-valid XML char
            - not empty and contains only valid XML chars
    - Restrictions:
        - No special restrictions
    - Combination tests:
        - INPUT in is null -> OUTPUT empty -> PASS
        - INPUT in is empty -> OUTPUT empty -> PASS
        - INPUT in is not empty and contains non-valid XML char -> OUTPUT Invalid XML changed with "?"  -> PASS
        - INPUT in is not empty and contains only valid XML char -> OUTPUT Valid XML  -> PASS

4º Function:

- **Name:** CryptUtils.getSha256Hash
- **Purpose:** Calculate SHA-256 hash of a given String / Char Array
- **Category Partition Algorithm:** 
  
  - Parameters:
    - String text
  - Characteristics:
    - text: 
      - null 
      - empty
      - not empty
  - Restrictions:
    - No special restrictions
  - Combination tests:
    - INPUT text is null -> OUTPUT exception -> PASS
    - INPUT text is empty -> OUTPUT SHA25(empty) -> PASS 
    - INPUT text is not empty -> OUTPUT SHA25(text) -> PASS

5º Function:

- **Name:** ClipboardContent.clearClipboardContent
- **Purpose:**  Clears contents from System Clipboard
- **Category Partition Algorithm:**

    - Parameters:
        - System Clipboard
    - Characteristics:
        - System Clipboard:
            - empty 
            - not empty
            - accessible
            - not accessible
    - Restrictions:
        - If the System Clipboard is not accessible doesn't need to check if it's empty or not
    - Combination tests:
        - INPUT System Clipboard is not accessible -> OUTPUT exception -> PASS
        - INPUT System Clipboard is empty and accessible -> OUTPUT Clipboard should be empty -> PASS
        - INPUT System Clipboard is not empty and accessible -> OUTPUT Clipboard should be empty -> PASS


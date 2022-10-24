# Assignment #3 - TVVS


- Filipe Cerqueira - 202204408
- João Polónia - 201208144

## Black Box Testing - Boundary Value Analysis

Most functions are not well suited for Black-Box testing because they lack some necessary documentation for example:
- missing input and output descriptions: (ex: [SearchPanel](https://paginas.fe.up.pt/~jcmc/tvvs/2022-2023/assignments/jpass-javadoc/jpass/ui/SearchPanel.html))
- exception cases: states the function should generate an exception but omits in which cases (ex: [CryptoUtils](https://paginas.fe.up.pt/~jcmc/tvvs/2022-2023/assignments/jpass-javadoc/jpass/util/CryptUtils.html))

The functions used for testing were selected based on the ones with most documentation available in the [Javadoc](https://paginas.fe.up.pt/~jcmc/tvvs/2022-2023/assignments/jpass-javadoc/index.html) with numeric inputs.


## **Function:** jpass.crypt.Aes256.encrypt

**Purpose:** Encrypts one block. The input block lies in inBlock starting at the position inIndex. The inBlock won't be modified by this method. The encrypted block will be stored in outBlock starting at position outIndex.

**Category Partition - Boundary Value Analysis:**


Since there was no documentation on expected inputs and its results, an analysis of the source code was performed and revelead that the BLOCK_SIZE used for encrypting a block is 16.

Starting with inBlock we determined that it's length should be greater than zero (no need to test this boundary because it's always true for Strings), but also not lesser than 16 (BLOCK_SIZE) other wise the code will fuction will throw an ArrayOutOfIndex Exception. 2 test cases were devoloped on this boundary to test both on point and off point values.

Next we evaluate inIndex: since it's a index of an Array it should be greater than zero, and because of the fixed BLOCK_SIZE the inIndex should be less than inBlock.length - 16. To test these boundaries 4 test cases were developed for all on and off points.

Next, similiar to inBlock.length, outBlock.length should be obviously greater than zero but also not lesser than inBlock.length (which as we saw above should be greater or equal to 16). 2 test cases were devolped as well on the on/off points.

Finally outIndex, like inIndex, has the same restrictions but relative to the outBlock.length. 4 testes cases were developed.

Category partion was also performed with one test generated per category (E1, E2 and E3) but were discarded as duplicates of the boundaries test cases. After combination of all boundary tests, some were disregarded as duplicates as well.

- Parameters:
    - byte[] inBlock
    - int inIndex
    - byte[] outBlock
    - int outIndex
- Restrictions:
    - inBlock.length >= 16
    - 0 <= inIndex <= inBlock.length - 16
    - outBlock.length >= inBlock.length
    - 0 <= outIndex <= outBlock.length - 16
- Boundaries:
    - ![](https://i.imgur.com/KOgr3Vl.png)
- Combination tests:
    - INPUT inBlock.length = 15  -> OUTPUT OutOfIndex exception -> PASS
    - INPUT inBlock.length = 16  -> OUTPUT encrypted block-> PASS
    - INPUT inIndex = -1 -> OUTPUT OutOfIndex exception -> PASS
    - ~~INPUT inIndex = 0 -> OUTPUT encrypted block -> PASS~~
    - INPUT inIndex = inBlock.length - 16 -> OUTPUT encrypted block -> PASS
    - INPUT inIndex = inBlock.length - 15 -> OUTPUT OutOfIndex exception -> PASS
    - ~~INPUT outBlock.length = inBlock.length  -> OUTPUT encrypted block -> PASS~~
    - INPUT outBlock.length = inBlock.length -1  -> OUTPUT OutOfIndex exception -> PASS
    - INPUT outIndex = -1 -> OUTPUT OutOfIndex exception -> PASS
    - ~~INPUT outIndex = 0 -> OUTPUT encrypted block -> PASS~~
    - ~~INPUT outIndex = outBlock.length - 16 -> OUTPUT encrypted block -> PASS~~
    - INPUT outIndex = outBlock.length - 15 -> OUTPUT OutOfIndex exception -> PASS
- Location of tests:
  - src/test/java/jpass/crypt/Aes256Test.java
- Git Commit:
    - [13f297a](https://github.com/JPolonia/jpass-tvvs-course/commit/13f297a7f7d8e86578eb6ad37877cbe8e7ddfe95)
---

## **Function:** jpass.util.StringUtils.stripStringt

**Purpose:** The main purpose of this method is to concatenate the given string in number of characters given in the parameters.

**Category Partition - Boundary Value Analysis:**

Since of the lack of description and explanation on the documentation about the results/returns of this method, an analysis of the source code was done and it was revealed that the return value of this method could be either a concatenation of the string at the length passed or the return of the string itself if the length was null or less than the string length.
Although this was the purpose of the method, when doing the bounds analysis we came to two conclusions:  
- When text.length - 2 is greater than length, the return value will be greater than the original text size, which goes against the purpose of the method;  
**Then decided to try length 0 to understand what the method would return:**  
- When length is 0, the length of the return value would be 3, even if the purpose was 0.
- Parameters:
    - String text = x
    - int length = y
- Restrictions:
    - text.length >= 0
    - length >= 0
- Boundaries:  
  ![](https://i.imgur.com/zfjTw56.png)
- Combination tests:
    - INPUT text.length(x)  = 7 and length(y) = 3 -> OUTPUT was 6 -> PASS
    - INPUT text.length(x)  = 7 and length(y) = 5 -> OUTPUT was 8 -> LOGIC ERROR, should have returned a shorter text than the one passed.
    - INPUT text.length(x)  = 7 and length(y) = 7 -> OUTPUT was 7 -> PASS
    - INPUT text.length(x)  = 7 and length(y) = 0 -> OUTPUT was 3 -> LOGIC ERROR, should have returned length 0 string.
- Location of tests:
    - src/test/java/jpass/util/SringUtilTests.java
- Git Commit:
    - [b1e2bfc](https://github.com/jose/jpass/commit/b1e2bfc4acf810c9b9c62f3732f0f710ec0826cc)



## **Function:** jpass.util.CryptUtils.getSha256Hash



**Purpose:** This method aligns the first rows * cols components of parent in a grid. Each component is as big as the maximum preferred width and height of the components. The parent is made just big enough to fit them all.

**Category Partition - Boundary Value Analysis:**

For this function we identified in the previous assignment 3 categories for the only input: text could be null, empty or not empty.

We will perform now boundary value analysis for the length of text input: since it's a String the length could never be negative so there is no boundary there. Also there is no limit to the size of the text input (category not empty) which left us with only boundary between category empty and not empty. The string is empty when the length is exactly zero and not empty when it's greater. That leaves us with 2 test cases on on/off points.

- Parameters:
    - String text
- Restrictions:
    - text.length > 0
- Boundaries:
    - ![](https://i.imgur.com/kLiopkL.png)
- Combination tests:
    - INPUT text.length = 0  -> OUTPUT sha256("") -> PASS
    - INPUT text.length = 1  -> OUTPUT sha256(text)-> PASS
- Location of tests:
    - src/test/java/jpass/util/CryptUtilsTests.java
- Git Commit:
    - [d2f88df](https://github.com/jose/jpass/commit/d2f88df791b6e1d18fc6bf3e45dc6355b656b89b)
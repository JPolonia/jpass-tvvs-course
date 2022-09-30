## Assignment #1 - TVVS

---

- Filipe Cerqueira - 202204408
- João Polónia - 201208144


- Brief description of your project. For example, What is it? How is the source-code organized?
- Brief description of what static testing is and why do you think it is import and relevant.
- Brief description of the static testing tool used in this assignment and how was it configured for your project. Your description must explain, e.g., why did you enabled or disabled any default configuration or bug pattern.
- Brief description of the report produced by the static testing tool.
- Brief description of the 5x2 randomly selected bugs.
- Brief description of how those 5x2 bugs were addressed/fixed. Tip: provide examples before and after fixing those bugs.

## Static Testing Tool

JPass is a password management application that allows you to store usernames, passwords, links and notes in an encrypted, password-protected file.

The code is organized into 5 folders:

- **Crypt** is where the SHA256 encryption and decryption logic are.
- **Data** is where the code that writes the XML that contains the user password information.
- **UI** is where the user interface is defined and created.
- **Util** is where it contains methods to help with different parts of the code like stripNonValidXMLCharacters which validates if the output has only valid XML characters or getSha256Hash which calculates the SHA256 hash.
- **XML** which contains the XML method converter that is used in Data.

## Static Testing

Static testing is a verification process used to test the application without executing the application code.

Static Testing Techniques is important because it provides a powerful way to improve the quality and productivity of the software development by helping us recognize and correct different issues like code style, documentation, performance and more early in the software development process.

## Tools Used

- **Spot Bugs** is a defect detection tool for Java that uses static analysis to look for more than 400 bug patterns, such as null pointer dereferences, infinite recursive loops, misuses of Java libraries, and deadlocks, it can identify hundreds of serious defects in large applications
- **PMD** is a source code analyzer. It finds common programming flaws such as unused variables, empty catch blocks, creation of unnecessary objects, and so on.


## Description of the Report produced by the tools

#### SpotBugs

The reported showed multiple bugs, where most of them are in Malicious code vulnerability because most of time the developer returned the internal representation of the object which code be a problem if it’s accessed by untrusted code where it could change the value since it’s a mutable object. The other category was Bad Practice where the report showed the developer using multiple times “/n” when it’s preferable to use “%n” because it’s used in multiploud platforms instead of the “/n” that only works on Unix based systems.

#### PMD

The reported showed most of the violations where on code styling such as short variable names, local variables could be final and wouldn’t need to be declared. There are multiple violations also on design such as Law of Demeter, it consists of objects should not have the knowledge of the internal working of other objects or components, basically if you have three objects A, B, C object A could invoke methods of object B but never of the C and vice versa.

## Description of the selected bugs and their solutions

#### Tool Selected: SpotBugs

First bug:

- **Type:** Performance
- **Title:** String concatenation in loop using + operator
- **Description:** The method seems to be building a String using concatenation in a loop. In each iteration, the String is converted to a StringBuffer/StringBuilder, appended to, and converted back to a String. This can lead to a cost quadratic in the number of iterations, as the growing string is recopied in each iteration and also fill up the heap memory with unwanted intermediate string objects.
- **Solution:** Better performance can be obtained by using a single StringBuffer (or StringBuilder in Java 1.5) explicitly and update it in each iteration:
- **Before & After Commit:** [e9db4af](https://github.com/jose/jpass/commit/e9db4af68b11b14eee55a68df9d3db29bd928c62)

Second bug:

- **Type**: Malicious code vulnerability
- **Title**: May expose internal representation by returning reference to mutable object
- **Description**: Returning a reference to a mutable object value stored in one of the object's fields exposes the internal representation of the object.  If instances are accessed by untrusted code, and unchecked changes to the mutable object would compromise security or other important properties.
- **Solution**: Returning a new copy of the object could prevent the internal representation of the object from being accessed.
- **Before & After Commit**: [6b2216e](https://github.com/jose/jpass/commit/6b2216e2090fc2ed169bc4cb3ce29422b4ebfd5a)

Third Bug:

- **Type:** Bad Practice
- **Title:** Random object created and used only once and Random type used instead of SecureRandom.
- **Description:** This code creates a Random object then use it to generate one random number and then discard the object. Also, the Random type object is guessable and could be predictable when using the same seed.
- **Solution**: The object should be created at the start of the class, so it’s not created every time the method is called. The Random should be replaced to SecureRandom because even with the same seed the values are always different.
- **Before & After Commit**: [062afa9](https://github.com/jose/jpass/commit/062afa935a33d3b227fa2419c328a3f8ac5f9c03)

Fourth Bug:

- **Type:** Bad Practice
- **Title:** Format String Problem
- **Description:** This format string includes a newline character “\n”. This is the Unix family linefeed character but in Windows the linefeed character is “\r\n”
- **Solution:** In format strings, it is preferable to use %n, which will produce the platform-specific line separator.
- **Before & After Commit:** [f5d11e4](https://github.com/jose/jpass/commit/f5d11e40e36b2ba93c4caa69fc89f682ebe0baf3)

Fifth Bug:

- **Type:** Internationalization
- **Title:** Reliance on default encoding
- **Description:** Found a call to a method which will perform a String to byte conversion, and will assume that the default platform encoding is suitable. This will cause the application behaviour to vary between platforms. Use an alternative API and specify a charset name or Charset object explicitly.
- **Solution:** Explicitly specify the charset name to be used as encoding when performing a String to byte conversion.
- **Before & After Commit:** [ee436a6](https://github.com/jose/jpass/commit/ee436a6db7176a8e42afcabf259786389999289e)

#### Tool Selected: PMD – Error Prone

First Bug:

- **Type:** Error Prone
- **Title:** ReturnEmptyCollectionRatherThanNull
- **Description:** For any method that returns an collection it is better to return an empty one rather than a null reference. This removes the need for null checking all results and avoids inadvertent NullPointerExceptions.
- **Solution:** Return empty collection byte[0]
- **Before & After Commit:** [5d67ebf](https://github.com/jose/jpass/commit/5d67ebfa82f2164fcae22449ee555ac0404638b3)

Second Bug:

- **Type:** Best Practices
- **Title:** SimplifiableTestAssertion
- **Description:** Reports test assertions that may be simplified using a more specific assertion method. This enables better error messages, and makes the assertions more readable.
- **Solution:** Convert assertTrue to assertArrayEquals
- **Before & After Commit:** [739b78a](https://github.com/jose/jpass/commit/739b78aa7fafb6ff5129b30b87d0bb5c6e1cf087)

Third Bug:

- **Type:** Best Practices
- **Title:** ArrayIsStoredDirectly
- **Description:** Constructors and methods receiving arrays should clone objects and store the copy. This prevents future changes from the user from affecting the original array.
- **Solution:** Store with a cloned array
- **Before & After Commit:** [2f04698](https://github.com/jose/jpass/commit/2f04698e88ec53ee470adc8a1b8b394f43906c3d)

Fourth Bug:

- **Type:** Error Prone
- **Title:** AvoidDuplicateLiterals
- **Description:** Code containing duplicate String literals can usually be improved by declaring the String as a constant field.
- **Solution:** Declare a String object with the repeated string literals and replace the literals with the String object.
- **Before & After Commit:** [c681801](https://github.com/jose/jpass/commit/c681801c27902b2a99da56fe5d0bdea38f6f06db)

Fifth Bug:

- **Type:** Performance
- **Title:** AppendCharacterWithChar
- **Description:** Avoid concatenating characters as strings in StringBuffer/StringBuilder.append methods.
- **Solution:** Use char delimiter (single quote) instead of string delimiter (double quotes) when appending chars to strings  
- **Before & After Commit:** [d14d072](https://github.com/jose/jpass/commit/d14d072c0a74ac6288afa984f455da5d72f879b0)

 
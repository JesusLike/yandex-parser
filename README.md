# yandex-parser
Simple parser of Yandex search response for Java-internship test problem.
Parses search results recieved by get request to yandsearch url (url is given in test problem description and is declared in code).
Creates a txt file for each result (filename format *i.txt* where *i* is the position of result on the yandsearch page) looks like:

URL: ...

Title: ...

Subtitle: ...

Annotation: ...

Also creates html document for each result named like *i-doc.html* recieved from parsed url.

All parsing results will be located in new directory named like *response\<timestamp\>*.

Used:
- JDK 1.8 (The higher vesrions of JDK are not supported because project was developed on 32 bit OS)
- Apache Maven 3.5.3
- Jsoup open-source html parser

Could be executed as a jar file in target directory:

`java -jar yandex-response-parser-1.0-SNAPSHOT-jar-with-dependencies.jar`

Or with mvn exec plugin:

`mvn exec:java`

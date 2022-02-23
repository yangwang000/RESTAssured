## Groovy

Playground: https://groovyconsole.appspot.com

Example:
```
import groovy.json.JsonSlurper

def object = new JsonSlurper().parseText(
    '''
    {
    "workspaces": [
        {
            "id": "0b033d72-13c1-4dd6-8c3a-0f72aa165a36",
            "name": "My Workspace",
            "type": "personal"
        }
    ]
}
    '''
)

def query = object.workspaces.type
```

## Hamcrest Matchers
### Collection matchers (List, Array, Map, etc.)
hasItem() -> check single element in a collection
not(hasItem()) -> check single element is NOT in a collection
hasItems() -> Check all elements are in a collection
contains() -> Check all elements are in a collection and in a strict order
containsInAnyOrder() -> Check all elements are in a collection and in any order
empty() -> Check if collection is empty
not(emptyArray()) -> Check if the Array is not empty
hasSize() -> Check size of a collection
everyItem(startsWith()) -> Check if every item in a collection starts with specified string

hasKey() -> Map -> Check if Map has the specified key [value is not checked]
hasValue() -> Map -> Check if Map has at least one key matching specified value
hasEntry() -> Maps -> Check if Map has the specified key value pair
equalTo(Collections.EMPTY_MAP) -> Maps [Check if empty]
allOf() -> Matches if all matchers matches
anyOf() -> Matches if any of the matchers matches

### Numbers:
greaterThanOrEqualTo()
lessThan()
lessThanOrEqualTo()

### String:
containsString()
emptyString()

## HTTP Headers
https://www.iana.org/assignments/message-headers/message-headers.xhtml

## Reuse request specification 
#### Default request specification
```
RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
requestSpecBuilder.setBaseUri("https://api.postman.com");
requestSpecBuilder.addHeader("X-Api-Key", testUtils.getString("apiKey"));
requestSpecBuilder.log(LogDetail.ALL);

//this static variable will be global and will retain its value throughout the program unless you overwrite/reset it
RestAssured.requestSpecification = requestSpecBuilder.build();
```
#### Query request specification
```
QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(RestAssured.requestSpecification);
System.out.println(queryableRequestSpecification.getBaseUri());
System.out.println(queryableRequestSpecification.getHeaders());
```

#### URL Encoding
https://developer.mozilla.org/en-US/docs/Glossary/percent-encoding

## Json Schema
https://www.jsonschema.net
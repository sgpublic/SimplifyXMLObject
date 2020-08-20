# SimplifyXMLObject

![GitHub](https://img.shields.io/github/license/sgpublic/SimplifyXMLObject)  
![GitHub Releases (by Release)](https://img.shields.io/github/downloads/sgpublic/SimplifyXMLObject/1.0.0/total)


## [中文](https://github.com/SGPublic/SimplifyXMLObject/tree/master/README.md) | English

SimplifyXMLObject is an open-source light XML Parser. It has the following advantages: easy use, clear logic, and convenient coding.

**Attention!** Recent version is still in development. Its reliability is not widely tested. If you are considering reliability, refer to `DOM4j`  
and other famous XML parsers, please.

## How to use
Adding `SimplifyXMLObject` to the project dependency:

Gradle

```groovy
implementation 'io.github.sgpublic:SimplifyXMLObject:1.0.0'
```

maven

```xml
<dependency>
  <groupId>io.github.sgpublic</groupId>
  <artifactId>SimplifyXMLObject</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Create an SXMLObject Object.
Use XML text with String type to create an `SXMLObecjt` object. If the root node of this XML node does not exist or is incomplete, it will throw `SXMLException`.
```java
String xml = /*XML data*/;
SXMLObject object = new SXMLObject(xml);
```

### Get the child node
Use the tag name to get the child node with a certain name in the current node. If the child node of this XML node does not exist or is incomplete, it will throw `SXMLException`.

```java
object.getXMLObject(/*tag name*/)
```

### Get the attribute value
Use the attribute name to get the attribute value in the node tag. If the node does not has such an attribute, it will throw `SXMLException`.

```java
String stringAttr = object.getStringAttr(/*attribute name*/);
int intAttr = object.getIntAttr(/*attribute name*/);
double doubleAttr = obeject.getDoubleAttr(/*attribute name*/);
long longAttr = object.getLongAttr(/*attribute name*/);
boolean booleanAttr = object.getBooleanAttr(/*attribute name*/);
```

### Get child node group
Use the tag name to get the child node group. If the node has multiple child nodes with the same name, you can create an SXMLArray object using these child nodes. 
If the current XML node does not have such a child node or the child node's tag is incomplete, it will throw `SXMLException`.

```java
SXMLArray array = object.getXMLArray(/*tag name*/)
```

### Get node group by index
Using the node index(start from 0) to get the corresponding SXMLObeject in SXMLArray. If the index is out of bounds, it will throw `SXMLException`.

```java
array.getXMLObject(/*index*/);
```

## Other
For other usage methods, please refer to [application cases](https://github.com/SGPublic/SimplifyXMLObject/blob/master/src/test/java/Test.java) ，the function is continuously updating



# SimplifyXMLObject

![GitHub](https://img.shields.io/github/license/sgpublic/SimplifyXMLObject)

## English | [中文](https://github.com/SGPublic/SimplifyXMLObject/blob/master/README_CN.md)

`SimplifyXMLObject` is an open source lightweight xml parser, which has the advantages of forming a tree structure, intuitive and easy-to-understand usage process, and easy code writing.

**Note!** The current version of `SimplifyXMLObject` is only a beta version, and its reliability has not been extensively tested. For stability, please refer to or use major well-known xml parsers such as `DOM4j`.

## How to use

### Create an SXMLObject object

Pass in an XML data of type `String`, and return an object of type `SXMLObject`. If the root node of the XML is incomplete or does not exist, an `SXMLException` is thrown.

```java
String xmlData = /*your xml data*/;
SXMLObject object = new SXMLObject(xmlData);
```

### Get child nodes

Pass in the name of the child node you want to get, and return an object of type `SXMLObject`. If there is no such node in the child node of the XML or the node label is incomplete, an `SXMLException` will be thrown.

```java
SXMLObject avtivity = object.getXMLObject("yourTagName");
```

### Get child node attribute value

Pass in the name of the attribute value you want to get, and return the value of the corresponding type. If the attribute value does not exist in the node, an `SXMLException` is thrown.

```java
String stringAttr = object.getStringAttr("yourAttributeName")
int intAttr = object.getIntAttr("yourAttributeName")
long longAttr = object.getLongAttr("yourAttributeName")
double longAttr = object.getDoubleAttr("yourAttributeName")
boolean booleanAttr = object.getBooleanAttr("yourAttributeName")
```

### Get child node group

If the node contains multiple child nodes with the same name, these child nodes can be created as a `SXMLArray` object. Pass in the name of the child node you want to get, and return a `SXMLArray` object. If there is no such node in the child node of the XML or the node label is incomplete, an `SXMLException` will be thrown.

```java
SXMLArray array = object.getXMLArray("yourTagName");
```

### Get node from node group

Pass in the number of the node you want to get (starting from 0), and return an object of type `SXMLObject`. If the sequence number is out of range, a `SXMLException` is thrown.

```java
SXMLObject object1 = array.getXMLObject(1);
```

### Other functions

**_Continuous development_**

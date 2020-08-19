# SimplifyXMLObject

![GitHub](https://img.shields.io/github/license/sgpublic/SimplifyXMLObject)

## [English](https://github.com/SGPublic/SimplifyXMLObject/blob/master/README.md) | 中文

`SimplifyXMLObject` 是一个开源的轻量级xml解析器，拥有形成树形结构、使用流程直观好理解、代码易编写等优点。

**注意！** 目前版本的 `SimplifyXMLObject` 还只是一个 beta 版本，其可靠性尚未大量检验，如追求稳定请参考或使用 `DOM4j` 等各大知名xml解析器。

## 使用

### 创建一个 SXMLObject 对象

传入一个 `String` 类型的 XML 数据，返回一个类型为 `SXMLObject` 的对象。若该 XML 的根节点不完整或不存在，则抛出 `SXMLException` 。

```java
String xmlData = /*your xml data*/;
SXMLObject object = new SXMLObject(xmlData);
```

### 获取子节点

传入想要获取的子节点名称，返回一个类型为 `SXMLObject` 的对象。若该 XML 中的子节点中没有该节点或该节点标签不完整，则抛出 `SXMLException` 。

```java
SXMLObject avtivity = object.getXMLObject("yourTagName");
```

### 获取子节点属性值

传入想获取的属性值名称，返回对应类型的数值。若该节点中不存在该属性值，则抛出 `SXMLException` 。

```java
String stringAttr = object.getStringAttr("yourAttributeName")
int intAttr = object.getIntAttr("yourAttributeName")
long longAttr = object.getLongAttr("yourAttributeName")
double longAttr = object.getDoubleAttr("yourAttributeName")
boolean booleanAttr = object.getBooleanAttr("yourAttributeName")
```

### 获取子节点组

若节点中包含多个名称相同的子节点，则可以将这些子节点创建为一个 `SXMLArray` 对象。传入想获取的子节点名称，返回一个 `SXMLArray` 对象。若该 XML 中的子节点中没有该节点或该节点标签不完整，则抛出 `SXMLException` 。

```java
SXMLArray array = object.getXMLArray("yourTagName");
```

### 从节点组中获取节点

传入想获取的节点序号（从0开始），返回一个类型为 `SXMLObject` 的对象。若序号超出范围，则抛出 `SXMLException` 。

```java
SXMLObject object1 = array.getXMLObject(1);
```

### 其他功能

**_持续更新中_**
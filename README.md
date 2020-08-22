# SimplifyXMLObject

![GitHub](https://img.shields.io/github/license/sgpublic/SimplifyXMLObject)
![GitHub Releases (by Release)](https://img.shields.io/github/downloads/sgpublic/SimplifyXMLObject/1.1.2/total)


## 中文 | [English](https://github.com/SGPublic/SimplifyXMLObject/tree/master/README_EN.md)

这是一个开源的轻量级 XML 解析器，拥有使用方法简单、逻辑结构清晰、代码易编写等优点。

**注意！** 当前版本仅为一个开发版本，其可靠性尚未经过大量检验，如需考虑稳定性请参考或使用 `DOM4j` 等各大知名 XML 解析器。

## 使用
将 `SimplifyXMLObject` 添加到项目依赖：

Gradle
```groovy
implementation 'io.github.sgpublic:SimplifyXMLObject:1.1.2'
```

maven
```xml
<dependency>
  <groupId>io.github.sgpublic</groupId>
  <artifactId>SimplifyXMLObject</artifactId>
  <version>1.1.2</version>
</dependency>
```

### 创建 SXMLObject 对象
使用 String 类型的 XML 数据文本创建一个 `SXMLObject` 对象。若该 XML 的根节点不完整或不存在，则抛出 `SXMLException` 。
```java
String xml = /*XML数据*/;
SXMLObject object = new SXMLObject(xml);
```

### 获取子节点
使用标签名称获取当前节点中指定子节点标签名称的子节点。若当前 XML 节点中没有该子节点或该子节点标签不完整，则抛出 `SXMLException` 。
```java
object.getXMLObject(/*标签名称*/)
```

### 获取属性值
使用属性名称获取节点标签中的属性值。若该节点中不存在该属性值，则抛出 `SXMLException` 。
```java
String stringAttr = object.getStringAttr(/*属性名称*/);
int intAttr = object.getIntAttr(/*属性名称*/);
double doubleAttr = obeject.getDoubleAttr(/*属性名称*/);
long longAttr = object.getLongAttr(/*属性名称*/);
boolean booleanAttr = object.getBooleanAttr(/*属性名称*/);
```

### 获取子节点组
使用标签名称若节点中包含多个名称相同的子节点，则可以将这些子节点创建为一个 SXMLArray 对象。若当前 XML 节点中没有该子节点或该子节点标签不完整，则抛出 `SXMLException` 。
```java
SXMLArray array = object.getXMLArray(/*标签名称*/)
```

### 按序号获取节点组
使用节点序号（序号从 0 开始）从 SXMLArray 中获取对应序号的 SXMLObject。若序号超出范围，则抛出 `SXMLException` 。
```java
array.getXMLObject(/*序号*/);
```

## 其他
其他使用方法参考 [使用案例](https://github.com/SGPublic/SimplifyXMLObject/blob/master/src/test/java/Test.java) ，功能持续更新中。
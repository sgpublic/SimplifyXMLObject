package com.sgpublic.xml;

class SXMLException extends Exception {
    static String NOT_A_XML_DATA = "Cannot parse the string, this string is may not an xml data.";
    static String NOT_A_XML_OBJECT = "An error occurred, the data cannot parse as an XMLObject.";
    static String NOT_A_XML_ARRAY = "An error occurred, the data cannot parse as an XMLArray.";
    static String TAG_NULL = "No tag found for \"%s\"";
    static String ATTR_KEY_NULL = "No attribute found for \"%s\"";
    static String INNER_NULL = "The tag \"%s\" has no content.";
    static String ATTR_NULL = "The tag \"%s\" has no attributes.";
    static String INNER_UNAVAILABLE = "There's no inner xml in tag \"%s\"";

    SXMLException(){}

    SXMLException(String message){
        super(message);
    }

    SXMLException(String message, String arg){
        super(
                String.format(message, arg)
        );
    }
}

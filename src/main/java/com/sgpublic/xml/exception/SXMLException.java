package com.sgpublic.xml.exception;

public class SXMLException extends Exception {
    public static String NOT_A_XML_DATA = "Cannot parse the string, this string is may not an xml data.";
    public static String NOT_A_XML_OBJECT = "An error occurred, the data cannot parse as an XMLObject.";
    public static String NOT_A_XML_ARRAY = "An error occurred, the data cannot parse as an XMLArray.";
    public static String TAG_NULL = "No tag found for \"%s\"";
    public static String ATTR_KEY_NULL = "No attribute found for \"%s\"";
    public static String INNER_NULL = "The tag \"%s\" has no content.";
    public static String ATTR_NULL = "The tag \"%s\" has no attributes.";
    public static String INNER_UNAVAILABLE = "There's no inner data in tag \"%s\"";
    public static String INNER_NOT_STRING_DATA = "The data in tag \"%s\" is not a string data.";
    public static String ARRAY_OUT_OF_RANGE = "Array index out of range.";

    public SXMLException(){}

    public SXMLException(String message){
        super(message);
    }

    public SXMLException(String message, String arg){
        super(
                String.format(message, arg)
        );
    }
}

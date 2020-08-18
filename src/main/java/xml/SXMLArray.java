package com.sgpublic.xml;

import java.util.ArrayList;

public class SXMLArray {
    private final ArrayList<String> xmlArray;

    public SXMLArray(ArrayList<String> xmlString){
        this.xmlArray = xmlString;
    }

    public SXMLObject getXMLObject(int index) throws SXMLException {
        return new SXMLObject(xmlArray.get(index));
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String xmlItem : xmlArray){
            builder.append(xmlItem).append("\n");
        }
        return builder.toString();
    }
}

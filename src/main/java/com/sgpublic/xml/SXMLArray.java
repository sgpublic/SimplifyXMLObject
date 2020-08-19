package com.sgpublic.xml;

import com.sgpublic.xml.exception.SXMLException;

import java.util.ArrayList;

public class SXMLArray {
    private final ArrayList<String> xmlArray;

    public SXMLArray(ArrayList<String> xmlString){
        this.xmlArray = xmlString;
    }

    public SXMLObject getXMLObject(int index) throws SXMLException {
        if (index < 0 || index > length()){
            throw new SXMLException(SXMLException.ARRAY_OUT_OF_RANGE);
        } else {
            return new SXMLObject(xmlArray.get(index));
        }
    }

    public int length(){
        return xmlArray.size();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String xmlItem : xmlArray){
            builder.append(xmlItem).append("\n");
        }
        return builder.toString();
    }
}

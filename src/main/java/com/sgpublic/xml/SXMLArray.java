package com.sgpublic.xml;

import com.sgpublic.xml.exception.SXMLException;

import java.util.ArrayList;

public class SXMLArray {
    private final ArrayList<String> xmlArray;

    /**
     * 新建一个 SXMLArray 对象
     *
     * @param xmlString XML 数据组集合
     */
    public SXMLArray(ArrayList<String> xmlString){
        this.xmlArray = xmlString;
    }

    /**
     * 从 SXMLArray 中获取对应序号的 SXMLObject。
     *
     * @param index 序号
     * @return 返回对应序号的 SXMLObject。
     * @throws SXMLException 若序号超出范围，则抛出 SXMLException。
     */
    public SXMLObject getXMLObject(int index) throws SXMLException {
        if (index < 0 || index > length()){
            throw new SXMLException(SXMLException.ARRAY_OUT_OF_RANGE);
        } else {
            return new SXMLObject(xmlArray.get(index));
        }
    }

    /**
     * 获取 SXMLArray 中成员数量
     *
     * @return 成员数量
     */
    public int length(){
        return xmlArray.size();
    }

    /**
     * 遍历 SXMLArray 中所有成员
     *
     * @param event 遍历事件
     */
    public void forEach(ForEachEvent event) throws SXMLException {
        for (int index = 0; index < length(); index++){
            event.onEachItem(new SXMLObject(xmlArray.get(index)), index);
        }
    }

    /**
     * 返回 SXMLArray 中所有成员连成起来后的 String。
     *
     * @return 连接后的 String
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String xmlItem : xmlArray){
            builder.append(xmlItem);
        }
        return builder.toString();
    }

    public interface ForEachEvent {
        void onEachItem(SXMLObject object, int index) throws SXMLException;
    }
}

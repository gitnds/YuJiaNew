package com.strong.yujiaapp.utils;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringToXml {

	/**
	 * xml�ַ���ת��Ϊlist����
	 * 
	 * @param xmlresult
	 *            xml�ַ���
	 * @param newClass
	 *            �¶���������ʼ��ǩ��
	 * @return List<Map<String, String>>
	 */
	public static List<Map<String, String>> getList(String xmlresult,
			String newClass) {
		List<Map<String, String>> lst = new ArrayList<Map<String, String>>();
		ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(
				xmlresult.getBytes());
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(tInputStringStream, "UTF-8");
			int eventType = parser.getEventType();
			Map<String, String> map = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String tagname = parser.getName();
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
					break;
				case XmlPullParser.START_TAG:// ��ʼԪ���¼�
					if (tagname.equalsIgnoreCase(newClass)) {
						map = new HashMap<String, String>();
					} else if (map != null) {
						map.put(tagname, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:// ����Ԫ���¼�
					if (tagname.equalsIgnoreCase(newClass)) {
						lst.add(map);
					}
					break;
				}
				eventType = parser.next();
			}
			tInputStringStream.close();
		} catch (XmlPullParserException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lst;
	}
}

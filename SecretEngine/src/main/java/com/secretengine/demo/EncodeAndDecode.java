package com.secretengine.demo;

import org.springframework.stereotype.Component;

@Component
public class EncodeAndDecode {
	
	public StringBuffer encode(StringBuffer stringBuffer, int key) {
		StringBuffer str= new StringBuffer();
		for(int i=0;i<stringBuffer.length();i++) {
			char c = stringBuffer.charAt(i);
			int ascii=c;
			ascii = ascii+key;
			char encry = (char)ascii;
			str.append(encry);
		}
		return str;
	}
	
	
	public StringBuffer decode(StringBuffer stringBuffer,int key) {
		StringBuffer str=new StringBuffer();
		for(int i=0;i<stringBuffer.length();i++) {
			char c = stringBuffer.charAt(i);
			int ascii=c;
			ascii = ascii-key;
			char encry = (char)ascii;
			str.append(encry);
		}
		return str;
	}
}

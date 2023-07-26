package com.secretengine.demo.controller;

import org.springframework.stereotype.Component;

@Component
public class EncodeAndDecode {
	
	@SuppressWarnings("null")
	public StringBuffer encode(StringBuffer stringBuffer) {
		StringBuffer str= new StringBuffer();
		for(int i=0;i<stringBuffer.length();i++) {
			char c = stringBuffer.charAt(i);
			int ascii=c;
			ascii = ascii+1;
			char encry = (char)ascii;
			str.append(encry);
		}
		return str;
	}
	@SuppressWarnings("null")
	public StringBuffer decode(StringBuffer stringBuffer) {
		StringBuffer str=new StringBuffer();
		for(int i=0;i<stringBuffer.length();i++) {
			char c = stringBuffer.charAt(i);
			int ascii=c;
			ascii = ascii-1;
			char encry = (char)ascii;
			str.append(encry);
		}
		return str;
	}
}

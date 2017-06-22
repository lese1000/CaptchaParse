package com.itext.test.inpost;

import com.itext.test.http.HttpUtils;

public class GetImgRandomCode {

	public static void main(String[] args) {
		String rep = HttpUtils.doPostByString("https://www.indiapost.gov.in/VAS/Pages/trackconsignment.aspx", "");
		System.out.println(rep);

	}

}

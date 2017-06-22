package com.itext.test.inpost;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

public class QueryTrackingNum {

	public static void main(String[] args) throws Exception{
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("https://www.indiapost.gov.in/VAS/Pages/trackconsignment.aspx");
		post.addHeader("Accept", "*/*");
		post.addHeader("Accept-Encoding", "gzip, deflate, br");
		post.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
		post.addHeader("Cache-Control", "no-cache");
		post.addHeader("Connection", "keep-alive");
//		post.addHeader("Content-Length", "4339");
		post.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		post.addHeader("Cookie", "WSS_FullScreenMode=false");
		post.addHeader("Host", "www.indiapost.gov.in");
		post.addHeader("Origin", "https://www.indiapost.gov.in");
		post.addHeader("Pragma", "no-cache");
		post.addHeader("Referer", "https://www.indiapost.gov.in/VAS/Pages/trackconsignment.aspx");
		post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
		post.addHeader("X-MicrosoftAjax", "Delta=true");
		post.addHeader("X-Requested-With", "XMLHttpRequest");
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("ctl00$ScriptManager", "ctl00$SPWebPartManager1$g_d6d774b9_498e_4de6_8690_a93e944cbeed$ctl00$upnlTrackConsignment|ctl00$SPWebPartManager1$g_d6d774b9_498e_4de6_8690_a93e944cbeed$ctl00$btnSearch"));
		formparams.add(new BasicNameValuePair("_wpcmWpid", ""));
		formparams.add(new BasicNameValuePair("wpcmVal", ""));
		formparams.add(new BasicNameValuePair("MSOWebPartPage_PostbackSource", ""));
		formparams.add(new BasicNameValuePair("MSOTlPn_SelectedWpId", ""));
		formparams.add(new BasicNameValuePair("MSOTlPn_View", "0"));
		formparams.add(new BasicNameValuePair("MSOTlPn_ShowSettings", "False"));
		formparams.add(new BasicNameValuePair("MSOGallery_SelectedLibrary", ""));
		formparams.add(new BasicNameValuePair("MSOGallery_FilterString", ""));
		formparams.add(new BasicNameValuePair("MSOTlPn_Button", "none"));
		formparams.add(new BasicNameValuePair("__EVENTTARGET", ""));
		formparams.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
		formparams.add(new BasicNameValuePair("__REQUESTDIGEST", "0xC280223A8C77F604777B42E2771E66F6E3A4E35DC2DB7E3DE67C2127B4151C8F306A131A0A2FB1BFC582237C67BE455E440BB8914C273F5A16CD8A3B45E2EFD8,21 Jun 2017 09:07:47 -0000"));
		formparams.add(new BasicNameValuePair("MSOSPWebPartManager_DisplayModeName", "Browse"));
		formparams.add(new BasicNameValuePair("MSOSPWebPartManager_ExitingDesignMode", "false"));
		formparams.add(new BasicNameValuePair("MSOWebPartPage_Shared", ""));
		formparams.add(new BasicNameValuePair("MSOLayout_LayoutChanges", ""));
		formparams.add(new BasicNameValuePair("MSOLayout_InDesignMode", ""));
		formparams.add(new BasicNameValuePair("_wpSelected", ""));
		formparams.add(new BasicNameValuePair("_wzSelected", ""));
		formparams.add(new BasicNameValuePair("MSOSPWebPartManager_OldDisplayModeName", "Browse"));
		formparams.add(new BasicNameValuePair("MSOSPWebPartManager_StartWebPartEditingName", "false"));
		formparams.add(new BasicNameValuePair("MSOSPWebPartManager_EndWebPartEditing", "false"));
		formparams.add(new BasicNameValuePair("__LASTFOCUS", ""));
		formparams.add(new BasicNameValuePair("__VIEWSTATE", "Og5578s21K/OuXG9a8T33BhRPfe4qbwD+uB3eXwRR85X1BS1bhrGBZGnW9VR6qZ9RDhaY3BKNrEPTghCBT7sKf9i1720gUfUTtQsnWMdhqhKqi8Q0NhxElul4SOSqfoMUfyaU1gAKDIgWMQPUmamZu23AL1NbjQqrRHl+B2RsYcaEu7DkxEj5U2OeXcdI9tlWveIuvsx4Hip8PlammfuH/ndEP8zZ9rMKKCDaw5s54gbcfgTMu47If89GlLsdSHL0z/1orFZF3o94VDGZ7Wzk/R85r00nZgoktW2QkmMxq5koiwEO4SAZcbufG1Skw33ZJxHdUgCWZcozIMCAy4CeFiUqgBLOvEDogDj5iltK6AXtBYuNrJDTr1xEPvyxJ2UEA7z6lBzfS22zawWXjpA9UxYz44R6rMOewZmccLSZtTOLZ5t9BzZLvQfXlWadgCCVScH0lisyzZdYo137mT/K1/KntmmC3k8v8BVaoXQVQlVCHx8I9sXXmvW4JDVMzvgP2W26hzZliYHGdsCWUWJpD9/oHhX2UG596MH3mVsN6r88N/NmV2h1So7h7d/z3rMnaVtKOSsq4HAmsAMgPzfRWTLOtsoBKsX/RG9eDbxiF4MjzUMjfF3THD9+GmUtIVS9ywm6UsyGAlP1h8h/DlyF/SBXm78FFDB/U95EW8q4GRVK8s3qwjIBEQ4L/H3HkWI3j709Wcfz+PF1TZawGpgmDvpU5IUDZZzVNM2+pVhK+AC0Gk40mWaxcRcq4jn83BwmdCGlfsSED1WTQsDO1bBJkeAfj8M5KAjfyuDD99BYpIgvnbDAXfNPLEf8FBg4C17ZF54M1iij8MwqrLrw1OwBRnVLnYYSw/p8YGuUj0hSgYYaeYNXArDzLYY5N1jnbggTqsKsX7WUqAJhX+bHotrI5ZMioNxKT11mgpGGvUSdmvoguAQX+CzLUAihmFnFF5EdsjPZRiaYs9JeV/oxUm5VehJ/rOIbmIMlatSZPFmnjTc+Hw0wqEvtIT9UENg0P2hzXJmWGpioLtyotZOrNS0utl1kjiNm6m9UZBGk0m3OokB5YGJusPa4Ddtj+gfpjjb798vI7wZ1OzsKmjOcDkdunbkzEK3MQeYY5cPGiSqzjlD3ixC2gWbkW5tOV45x1jbKK5ZQag2HvXNXHR/HsOtBCrL+GPvKui2Ygi0rYvd1ZVlGGym/ckY76MnI1wuqKzvsxBu/T0m7v7ITocv2WwW6ykhvEcbsY8E9mjH22s+qBg90ULDvmwt98FVZsHAdWnoqeUro8D1oZjVjJHhR5QbripmUNCEZb8z6Z2TL+dY20CenHCjIEdsAw0S3EsXNCEJ9ps5EVfe8jg7m2AldcAOJ4Znk7zYF3x64zYivtHhUAu9tb6ExR5U8jJmFPdbXhasMUy6oIf/2MPUmpJFiYrtsPOfnrTzGi/esBAmzBWQ2UbXmMCmn5ZnqQvPtCsvC3dMygwsuBw9KAXiyVXrCf3gQ7MrjX26VCK6jHwJOfqXsL3TcYNMSauK328cHINU9duz3re2X/OTOKh/GqYuSdapGSUdhSBYeO0OllUVg07s2jcNvIshrANM7U3IJ4niShTiGwOGYmi3ETQo9MZG8pAG54Zb8pLoEhmXwpaGNsFg1MnU/B14q74H1MG1N0KmmT71HW5idOr4Y3vTWEcZRJB5IqzFFqXL2QB4BFDoClpGHWmLj1PLLQFXh01Bxy2SDbjzlTar73HCRNVn8PSrSKOl50fGSTBJ3hFPF3gjnrGipewpDoHAuQkTs+F49bTFDjUdDpsti/Gip4Bo9qWrqZ9hK1VtX67mv5jOEPWW1P+jrHA8dgAPrmQ045ZlRsUPuTf1Ao7Gh4RWqtgPlCzTL8TJPkVnkUpnB229VwBaEee+kXwsFXIeXKMU30JPPWZkklAIg/NkIyJZaYJQE1EfkzbUaRBh+oZHZnGTP69kyoxcN0EsSDmRqOAlE51cWhT964EpJhPiXH8JVJhTYsNhTprULReNTTIh4VhbG4G5+UI1V7AO/pcjV5SctjasN2y3XPqYAjUPSLGU7BPUS31S6L2jaFyq9pobOUAI3QpT5NM5ONveDz3xkmg4kaIXkE+N1YFhSOtSjaDWXDN0rmTVpvT/55aY8+GPK+K174Gi3emkSAWbUWtv8kHOKc/S/2uwqMrSEWECSz4TNJUtTXx1Jt/eqbroTYfGgKhq0W7sXr93MbMJhiK8qq8nHtQ8MKyus96exlWytW3hLzY9uZY8uG1uWOqKUijd2mA/qFntaTuDrOPargG1cKxlEWJ0j5R1gjOjYkUhmcF5WXiePLvZ5GzPcqwGemYrlg3oc5fQCoAwJuUkl4CyKfQNbX0jP513mVYHziddNXxykIxyYiNFyMyr+EFfTrS/YXT/5fnhgRSSIHmSwAHZjL+/vJLbhjzfMPnpMI9j0p9EmummRP8LzOIYPcYdqEPL7JV4mV2NOgP2FqTnHz4/weBhU+SebCE320whY4pA2zjpQD7HcPYihsJhcQ=="));
		formparams.add(new BasicNameValuePair("__VIEWSTATEGENERATOR", "7E313FDD"));
		formparams.add(new BasicNameValuePair("__VIEWSTATEENCRYPTED", ""));
		formparams.add(new BasicNameValuePair("__EVENTVALIDATION", "zoU5+pGb45TljaOlNV9/8Hz0SDnMuoZVaVeiHY7eEdbIUalID9esqSuKEWuSeaiyodzrvJ4OPYka1b7A1U4POYrZFKLpim0DO20i5reUNB/oiVccQWSQqzCP0U4NnNF166DzKMVCjdtsx0YA2yO6sYj0YBs8L/zrCt1GIkDz5H9gr+oIiOT9ZdxU1Lqn9AE2Q5CcwIJYqgX6Gg70+BmlXhurI4ttayV2UREDQSKPlvz9xKBFL84BiW7dZj6w5FS4"));
		formparams.add(new BasicNameValuePair("ctl00$SPWebPartManager1$g_d6d774b9_498e_4de6_8690_a93e944cbeed$ctl00$txtOrignlPgTranNo", "EQ268863248IN"));//EQ268879923IN	EQ268863265IN  EQ268863248IN
		formparams.add(new BasicNameValuePair("ctl00$SPWebPartManager1$g_d6d774b9_498e_4de6_8690_a93e944cbeed$ctl00$txtCaptcha", "3ebf2f"));
		formparams.add(new BasicNameValuePair("ctl00$SubscribeNewsletter1$ctl00$txtEmail", "Enter your email..."));
		formparams.add(new BasicNameValuePair("__ASYNCPOST", "true"));
		formparams.add(new BasicNameValuePair("ctl00$SPWebPartManager1$g_d6d774b9_498e_4de6_8690_a93e944cbeed$ctl00$btnSearch", "Search"));
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		post.setEntity(entity);
		
		CloseableHttpResponse response=httpClient.execute(post);
		HttpEntity resentity = response.getEntity();
		resentity.getContent();
		if(null!=resentity){
			String result = EntityUtils.toString(resentity);
			response.close(); 
//			System.out.println(result);
			ByteInputStream bis = new ByteInputStream(result.getBytes(), result.getBytes().length);
			InputStreamReader isr = new InputStreamReader(bis);
			BufferedReader reader = new BufferedReader(isr);
			String lineStr = null;
			String contentStr = "";
			while(null!=(lineStr = reader.readLine())){
				if(lineStr.contains("<div class=\"formbck\">")){
					contentStr  = contentStr + lineStr;
					break;
				}
			}
			while(null!=(lineStr = reader.readLine())){
				if(!lineStr.contains("|0|hiddenField|")){
					contentStr  = contentStr + lineStr;
				}else{
					reader.close();
					break;
				}
			}
			
//			System.out.println(contentStr);
			 Document doc = Jsoup.parse(contentStr);
			 	//追踪记录
		        Elements trs = doc.select("#ctl00_SPWebPartManager1_g_d6d774b9_498e_4de6_8690_a93e944cbeed_ctl00_gvTrckMailArticleEvnt").select("tr");
		        for(int i = 0;i<trs.size();i++){
		            Elements tds = trs.get(i).select("td");
		            for(int j = 0;j<tds.size();j++){
		                String text = tds.get(j).text();
		                System.out.println(text);
		            }
		        }
		        //
			
		}
		
		
	}
	
	
	
	

}

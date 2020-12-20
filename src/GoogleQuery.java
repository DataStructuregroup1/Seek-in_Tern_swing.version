

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;



public class GoogleQuery 

{
	public WebTree tree;
	
	public String searchKeyword;

	public String url;

	public String content;
	
	public HashMap<String, String> retVal;
	
//	public ArrayList<String> titles;

	public GoogleQuery(String searchKeyword)

	{

		this.searchKeyword = searchKeyword;

		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=20";

		WebPage  rootP = new WebPage(this.url);
		rootP.title = "Google Search";
		
		tree = new WebTree(rootP);
		
	}

	

	private String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(url);

		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line=bufReader.readLine())!=null)
		{
			retVal += line;

		}
		return retVal;
	}
	public HashMap<String, String> query() throws IOException

	{

		if(content==null)

		{

			content= fetchContent();

		}

		retVal = new HashMap<String, String>();
//		titles = new ArrayList<String>();
		Document doc = Jsoup.parse(content);
//		System.out.println(doc.text());
		Elements lis = doc.select("div");
//		System.out.println(lis);
		lis = lis.select(".kCrYT");
//		System.out.println(lis.size());
		
		
		for(Element li : lis)
		{
			try 

			{ 
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				
				if(citeUrl.substring(0,7).equals("http://")==false) {
					citeUrl = "http://www.google.com"+citeUrl;
				}
//				citeUrl = real(citeUrl);
				if(title.equals("")) {
					for(WebNode w : tree.root.children) {
						if(w.isTheLastChild()) {
							WebPage child = new WebPage(citeUrl);
							child.title = "Child";
							w.addChild(new WebNode(child));
						}
					}
				}
				else {			
					WebPage w = new WebPage(citeUrl);
					w.title = title;
					tree.root.addChild(new WebNode(w));
				}
				System.out.println("Title: "+title+"\n"+" URL: "+citeUrl+"\n");
				retVal.put(title, citeUrl);

			} catch (IndexOutOfBoundsException e) {

//				e.printStackTrace();

			}
		}
		tree.setPostOrderScore();
		tree.eularPrintTree();
		return retVal;

	}
	
	public String real(String url) throws IOException {
		int itimeout = 900000000;
        Connection.Response res=null;
		res = Jsoup.connect(url).timeout(itimeout).method(Connection.Method.GET).followRedirects(false).execute();
		return res.header("Location");

	}
	
	public void print() {
//		for (String title: retVal.keySet()){
//            String key = title.toString();
//            if(title.equals("")) {
//            }
//            else {
//            	String value = retVal.get(title).toString();  
//            	System.out.println("Title:"+key+ " URL: " + value);
//            }
//}
	}
}

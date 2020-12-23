

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

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
	
	public ArrayList<String> keys;

	public GoogleQuery(String searchKeyword)

	{

		this.searchKeyword = searchKeyword;

		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=2";

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
				citeUrl = real(citeUrl);
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
//				System.out.println("Title: "+title+"\n"+" URL: "+citeUrl+"\n");
				

			} catch (IndexOutOfBoundsException e) {

//				e.printStackTrace();

			}
		}
		tree.setPostOrderScore();
		for(WebNode w:tree.root.children) {
			retVal.put(w.webPage.toString(), w.webPage.url);
		}
//		System.out.print(sort());
//		print();
//		tree.eularPrintTree();
		
		return retVal;

	}
	
	public String real(String url) throws IOException {
		int itimeout = 900000000;
        Connection.Response res=null;
		res = Jsoup.connect(url).timeout(itimeout).method(Connection.Method.GET).followRedirects(false).execute();
		return res.header("Location");
	}
	
	public void print() {
		for (String w: retVal.keySet()){
            String value = retVal.get(w).toString();  
            System.out.println("Title:"+w.substring(w.indexOf("/$/")+3,w.indexOf("!$!"))+ " URL: " + value+" Score: "+w.substring(w.indexOf("!$!")+3,w.length()));
            
		}
		System.out.println(retVal.entrySet());
	}
	
	public String[][] sort(){
		String[][] s = new String[retVal.size()][2];
		keys = new ArrayList<String>();
		int num = 0;
		for(Entry<String, String> entry : retVal.entrySet()) {
		    String key = entry.getKey();
		    keys.add(key);
		    num++;
		}
		mergeSort(keys);
		for(String k:keys) {
		    String value = retVal.get(k);
		    s[num][0] = k;
		    s[num][1] = value;
		}
		return s;
	}
	
	public ArrayList<String> mergeSort(ArrayList<String>s) {
		ArrayList<String>temp = new ArrayList<String>();
		if(s.size()>1) {
			for(int i=0;i<s.size()/2;i++) {
				temp.add(s.remove(0));
			}
			mergeSort(s);
			mergeSort(temp);
			return merge(s,temp);
		}
		return null;
	}
	public ArrayList<String> merge(ArrayList<String>s1,ArrayList<String>s2) {
		ArrayList<String>fin = new ArrayList<String>();
		while(s1.isEmpty()==false&&s2.isEmpty()==false) {
			if(Double.parseDouble(s1.get(0).substring(s1.get(0).indexOf("!$!")+3,s1.get(0).length()))>Double.parseDouble(s2.get(0).substring(s2.get(0).indexOf("!$!")+3,s2.get(0).length()))) {
				fin.add(s1.get(0));
			}
			else {
				fin.add(s2.get(0));
			}
		}
		while(s1.isEmpty()==false) {
			fin.add(s1.get(0));
		}
		while(s2.isEmpty()==false) {
			fin.add(s2.get(0));
		}
		return fin;
	}
}

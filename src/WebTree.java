

import java.io.IOException;
import java.util.ArrayList;


public class WebTree {
	public WebNode root;
	public ArrayList<Keyword> keywords;
	
//	public WebTree(String url){
//		WebPage rootPage = new WebPage(url);
//		this.root = new WebNode(rootPage);
//		setKeyWords();
//	}
	public WebTree(WebPage rootPage){
		this.root = new WebNode(rootPage);
		setKeyWords();
	}
	
	public void setKeyWords() {
		keywords = new ArrayList<Keyword>();
		Keyword k1 = new Keyword("java",1);
		Keyword k2 = new Keyword("實習",20);
		Keyword k3 = new Keyword("資訊",10);
		Keyword k4 = new Keyword("nccu",1);
		keywords.add(k1);
//		keywords.add(k2);
//		keywords.add(k3);
//		keywords.add(k4);
	}
	
	public void setPostOrderScore() throws IOException{
		setPostOrderScore(root, keywords);
	}
	
	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException{
		//4. compute the score of children nodes postorder
		for(WebNode child : startNode.children){
			if(child.webPage.score==0) {
				setPostOrderScore(child,keywords);
			}
							
		}
		//5.setNode score of startNode
		startNode.setNodeScore(keywords);
		
	}
	
	public void eularPrintTree(){
		eularPrintTree(root);
	}
	
	private void eularPrintTree(WebNode startNode){
		int nodeDepth = startNode.getDepth();
		
		if(nodeDepth > 1) System.out.print("\n" + repeat("\t", nodeDepth-1));
		//print "("
		System.out.print("(");
		//print "name","score"
		System.out.print(startNode.webPage.title+","+startNode.nodeScore);
		
		//7.print child preorder
		for(WebNode child : startNode.children){
			eularPrintTree(child);
		}
		
		//print ")"
		System.out.print(")");
		
		/*for example
		 (Publication,286.2)
		*/
		if(startNode.isTheLastChild()) System.out.print("\n" + repeat("\t", nodeDepth-2));
		
	}
	
	private String repeat(String str,int repeat){
		String retVal  = "";
		for(int i=0;i<repeat;i++){
			retVal+=str;
		}
		return retVal;
	}
}

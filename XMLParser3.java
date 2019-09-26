/* CS3331 Adv. Object Oriented Programming
 * Instructor: Dr. Roach
   Program 2
 * Last modified on [9/13/19]
 * Modified and submitted by: [Justin Ruiloba]
*/
//intial version taken from xml Parser from piazza 9/12/19
//able to ignore path complications created menu method9/12/19
//added comments implemented all methods fully working program 9/13/19
//
//
/*Program Takes in a file input.txt given in well formed and valid XML 
 * prints the current element and then prints a menu in which he has 6 options
 * SHOW:Prints all  the current element's contents of the text file 
 * CHANGE: Changes the current element of the XML given the tag 
 * and what you would like to change content to
 * WRITE:which takes your changes and writes to a new file newTest.xml
 * NEXT: Iterates to next element and prints out all of element's contents
 * PREVIOUS:Iterates back and element and prints out all of elements contents   
 * EXIT:exits program completly
 * 
 * 
 * 
 * 
 * */

package CS3331;
//import all neessary libaries 
import java.io.File;
import java.util.Scanner; 

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
public class XMLParser3 {
	//prints menu for user to chose from
	public static void menu(){
		System.out.println("SHOW");
		System.out.println("CHANGE");
		System.out.println("WRITE");
		System.out.println("NEXT");
		System.out.println("PREVIOUS");
		System.out.println("Race");
		System.out.println("EXIT");
	}
	//SHOW:Prints all  the current element's contents of the text file 
	public static void show(NodeList nList,int temp){
		//get current element
		Node nNode = nList.item(temp);
		//print elements name
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
		//if contents is inside element
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			//print elements
			//System.out.println("segment " + eElement.getAttribute("SEGMENT_NUMBER"));
			System.out.println("Segment: " + eElement.getElementsByTagName("SEGMENT_NUMBER").item(0).getTextContent());
			System.out.println("Length: " + eElement.getElementsByTagName("LENGTH").item(0).getTextContent());
			System.out.println("SPEED LIMIT : " + eElement.getElementsByTagName("SPEED_LIMIT").item(0).getTextContent());

		}
		System.out.println();
		System.out.println();
			
		
		
	}
	//CHANGE: Changes the current element of the XML given the tag 
	//* and what you would like to change content to
	public static void change(Node nNode,NodeList a,String tag, String newtxt){
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			eElement.getElementsByTagName(tag).item(0).setTextContent(newtxt);
		}
		System.out.println();
		System.out.println();
	}
	//WRITE:which takes your changes and writes to a new file newTest.xml
	public static void write(Document doc){
		try{
			//saves to new file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource source = new DOMSource(doc);
	        //outputs new file
	        StreamResult result = new StreamResult(new File(".\\newTest.xml"));
	        transformer.transform(source, result);
	         
	         // Output to console for testing
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	         System.out.println();
	 		 System.out.println();
		}
		//catche exception
		catch(Exception e) {
			e.printStackTrace();
		}
		}
	//NEXT: Iterates to next element and prints out all of element's contents
	public static void next(NodeList nList,int temp){
		if((temp+1)<nList.getLength()){
    		temp++;	
    		next(nList,temp);
    	}
    	else{
    		System.out.println("No node exists at end of list");
    		return ;
    	}
		Node nNode = nList.item(temp);
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			System.out.println("Student roll no : " 
					+ eElement.getAttribute("rollno"));
			System.out.println("First Name : " 
					+ eElement.getElementsByTagName("firstname").item(0).getTextContent());
		}
		System.out.println();
		System.out.println();
	}
	public static void previous(NodeList nList,int temp){
		Node nNode = nList.item(temp);
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			System.out.println("Student roll no : " 
					+ eElement.getAttribute("rollno"));
			System.out.println("First Name : " 
					+ eElement.getElementsByTagName("firstname").item(0).getTextContent());
		}
		System.out.println();
		System.out.println();
		
	}
	//checks if seg numbers are equal or if theirs a gap as well as seg miles have to be at least .5
	public static double[][]ValidRaceTrack(double track[][]){
		int num=0;
		int numofsegments =0;
		for(int i=0;i < track.length;i++){
			for(int j=0;j < track[i].length;j++){
				System.out.println(track[i][j]);
				//seg numbers
				if (i ==0){
					
						if(j+1<track[i].length&&track[i][j]==track[i][j+1]&& track[i][j]>0){
						System.out.println(track[i][j]+" "+track[i][j+1]);
						System.out.println("Two mathcihng segment numbers goodbye");
						System.exit(0);
						}
						if(track[i][j]==num+1){
							num++;
						}
						if(track[i][j]>0.0){
							numofsegments++;
						}
					
					
					//check for if no gaps
					
				}
				if (i==1){
					if(j<numofsegments-1){
						if(track[i][j]<.5){
							
							System.out.println("DISTANCE for segment not valid must be at least .5");
							System.exit(0);
						}
				
					
				}
			}
		
			if(num!=numofsegments){
				System.out.println();
				System.out.println(num+"gap in segment numbers goodbye"+numofsegments);
				System.exit(0);
			}
	}
			
		}
		
		
		
		
		
		
		
		
		
		
		return track;
		
	}
	
	//SHOW:Prints all  the current element's contents of the text file 
	public static double[][] Race(NodeList nList,int temp){
		//get current element
		//will contain segment numbers in [0][x]
		//will contain lenght in [1][x]
		//will ocntain speed limit in [2][x]
		double convertstring = 0;
		double[][] alltrackinfo = new double[3][10] ;
		//infinit loop breaks if no more nodes
		while(true){
		Node nNode = nList.item(temp);
		
		//print elements name
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
		//if contents is inside element
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			//print elements
			//System.out.println("segment " + eElement.getAttribute("SEGMENT_NUMBER"));
			System.out.println("Segment: " + eElement.getElementsByTagName("SEGMENT_NUMBER").item(0).getTextContent());
			
			String str=eElement.getElementsByTagName("SEGMENT_NUMBER").item(0).getTextContent();
			convertstring = Double.parseDouble(str);
			alltrackinfo[0][temp] = convertstring; 
			
			System.out.println("Length: " + eElement.getElementsByTagName("LENGTH").item(0).getTextContent());
		    str= eElement.getElementsByTagName("LENGTH").item(0).getTextContent();
			convertstring = Double.parseDouble(str);
			alltrackinfo[1][temp] = convertstring; 
			
			System.out.println("SPEED LIMIT : " + eElement.getElementsByTagName("SPEED_LIMIT").item(0).getTextContent());
			str= eElement.getElementsByTagName("SPEED_LIMIT").item(0).getTextContent();
			convertstring = Double.parseDouble(str);
			alltrackinfo[2][temp] = convertstring; 
		}
		if((temp+1)<nList.getLength()){
    		temp++;	
    		
    	}
		else{
			System.out.println();
			System.out.println();
			return alltrackinfo ;
		}
	}

			
		
		
	}
	public static void main(String[] args) {
		int temp =0;
		try {
			 Scanner scnr = new Scanner(System.in);
			 String tag= " ";
			 String waste=  " ";
		     String newtxt= " ";   
		     
		     System.out.println("Please enter a file name exclude .txt");
				System.out.println("Otherwise default file name input.txt will be used");
				String userfile = scnr.nextLine();
				File inputFile = new File("input.txt");
				if(userfile.length()>0){
					inputFile = new File(userfile+".txt");
				}
		    //infinite loop only ends when user hits exit
			while(true){
					
					
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(inputFile);
					doc.getDocumentElement().normalize();
					//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					NodeList nList = doc.getElementsByTagName("SEGMENT");
					//System.out.println("----------------------------");
					Node nNode = nList.item(0);


					


					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						System.out.println("SEGMENT NUMBER : " 
								+ eElement.getAttribute("SEGMENT NUMBER"));
						System.out.println("LENGTH: " 
								+ eElement.getElementsByTagName("LENGTH").item(0).getTextContent());
					}
					menu();
				 //creates scanner
			   
			    String userinput = scnr.next();
			    //change option needs more input otherwise ignore
			    //if(userinput.equalsIgnoreCase("change") ||userinput.equalsIgnoreCase("c")){
			    	 //tag text is in to change
			    //	 tag = scnr.next();
			    	 //takes in empty space
			    //	 waste = scnr.nextLine();
			    	 //txt to replace with 
			    //	 newtxt = scnr.nextLine();
			    //}
			    //trims spaces
			    userinput =userinput.trim();
			    //testing see if scanner worked
			    //System.out.println(userinput);
			    
			    //if user chooses show
			    if(userinput.equalsIgnoreCase("show")||userinput.equalsIgnoreCase("s") ){
			    	show(nList,temp);
			    }
			    //user chooses change
			    if(userinput.equalsIgnoreCase("change") ||userinput.equalsIgnoreCase("c")){
			    	 //tag text is in to change
			    	System.out.println("ENTER TAG NAME"); 
			    	tag = scnr.next();
			    	 //takes in empty space
			    	 waste = scnr.nextLine();
			    	 //txt to replace with 
			    	 System.out.println("ENTER NEW TEXT TO INSERT");
			    	 newtxt = scnr.nextLine();
			    	change(nNode,nList,tag,newtxt);
			    }
			    //user chooses write
			    if(userinput.equalsIgnoreCase("write")||userinput.equalsIgnoreCase("w")){
			    	write(doc);
			    }
			    //user chooses next
			    if(userinput.equalsIgnoreCase("next")||userinput.equalsIgnoreCase("n")){
			    	//if list is not null
			    		next(nList,temp);
				}
			//if user chooses previous
			if(userinput.equalsIgnoreCase("previous")||userinput.equalsIgnoreCase("p")){
				//if list is not null
				if((temp-1)>=0){
					temp--;	
					previous(nList,temp);
				}
					else{
					System.out.println("No node exists at beggining  of list");
					}
			}
			if(userinput.equalsIgnoreCase("race")||userinput.equalsIgnoreCase("r") ){
		    	
		    	ValidRaceTrack(Race(nList,temp));
		    }
			//user chooses exit 
			if( userinput.equalsIgnoreCase("exit")||userinput.equalsIgnoreCase("e")){
				//print message
				System.out.println("Shutting Down");
				//shutdown program
				System.exit(0);
			}
			
			

			
		}
		  //catch execeptions
		} catch (Exception e) {
			e.printStackTrace();
		}
	}











}

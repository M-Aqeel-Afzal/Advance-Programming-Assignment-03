import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

public class driver {
	
	public static boolean Switch(int[] QueryEachFilesMatch,int QueryAllFilesMatch,String[] vectors,Word[][] wordVectors,String[] args,int[] allwords,int[] fre,int choice,B_S_T tree) throws WrongMenuOptionInput
	{ 
		switch(choice)
		{
		case 1:
			System.out.println("Binary Search Tree is given blew:");
			tree.inorder();
			break;
		case 2:
			System.out.println("Vectors  are given blew:");
			for(int j=0;j<args.length-1;j++)
			{
				
				System.out.println(vectors[j]);
				
			}
			break;
		case 3:
			
			for(int i=0;i<args.length-1;i++)
			{
				for(int j=0;j<allwords[i];j++)
				{
					System.out.println("File "+(i+1)+"   matched word: "+wordVectors[i][j].word+"       Frequency: "+wordVectors[i][j].frequency);
				}
				  System.out.println("\n\n");
			}
			break;
		case 4:
			System.out.println("Please Enter your Query: ");
			Scanner obj1= new Scanner(System.in);
		String query="";
			 
			query= obj1.nextLine();
			QueryAllFilesMatch=	Word.QueryResolve(QueryEachFilesMatch,QueryAllFilesMatch,query,wordVectors,args,allwords,fre);
			System.out.println("Total number of Query word matched in All files: "+ QueryAllFilesMatch);
			for(int i=0;i<args.length-1;i++)
			{
				System.out.println("Total word match  in file "+args[i+1]+" is: "+ QueryEachFilesMatch[i]);
			
			}
			int high=0,index=0,fhigh=0;
			for(int i=0;i<args.length-1;i++)
			{
				System.out.println("frequency: "+fre[i]);
				if(QueryEachFilesMatch[i]>high||fre[i]>fhigh)
				{
					index=i;
					fhigh=fre[i];
					high=QueryEachFilesMatch[i];
				}
			}
			
				System.out.println("file "+args[index+1]+" has proper match having highest matched words: "+ high+"   and highest frequency: "+fhigh);
			
			
			
			break;
			
		case 5:
			System.exit(0);
			break;
			default:
				throw new WrongMenuOptionInput("Wrong input!");
				
				
		}
		return true;
	}
	public static void main(String[] args)
{
		B_S_T tree=new B_S_T();
		int QueryAllFilesMatch=0;
		int[] fre=new int[args.length-1];
		int[] QueryEachFilesMatch=new int[args.length-1];
		Vector<String> tokan_vacs = new Vector<String>();
		System.out.println("Total Number of Files are input: "+ args.length);
		System.out.println("\n\nFollowing Files are input by the user:\n");
		for(int i=0; i < args.length; i++)                        // printing file names
		{
			System.out.println(i+1+":   "+ args[i]);
		
		}
		                                          // reading vacobolary file
	
		//file_threads object[] = new file_threads[args.length];
		for(int i=0; i < args.length; i++)                        // printing file names
		{
			
			if(i>0)
			tokan_vacs.add(args[i]);
			file_threads object= new file_threads(args[i],tree,tokan_vacs);
			
         object.start();
         synchronized( object)
         {
        	 try {
        		 Thread.currentThread().getName();
				object.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
	    
		}
	  //  String s[]= {"9","2","6","4","5","3","7","8","1","10"};
	  //  for(int j=0;j<10;j++)
	  //  {
	  //  	tree.insert(s[j]);
	  //  }
		String[] vectors = new String[args.length];
	    boolean flag=false;
	    String s1="",s2="";
	
		for(int j=0;j<args.length;j++)
		{ vectors[j]="";
			if(j>0&&j<args.length-1)
			{s1=args[j];
			s2=args[j+1];
			}
			else
				s1=args[0];
			for(int i=0; i < tokan_vacs.size(); i++) 
			{	
				if(tokan_vacs.elementAt(i).equals(s1))
					flag=true;
				if(tokan_vacs.elementAt(i).equals(s2))
					flag=false;
				if(tokan_vacs.elementAt(i).equals(s2)&&j==args.length-1)
					{
					flag=true;
					
					}
					
				if(flag)
				{
					vectors[j-1]=vectors[j-1]+tokan_vacs.elementAt(i)+" ";
					
					
				}
			}
			System.out.print("\n");	
		}
		
		int[] allfre= new int[args.length-1];
		int[] allwords= new int[args.length-1];
		Word[][] wordVectors;
		int total=Word.findTotalmatch(args,vectors,tree,allfre);
		 wordVectors=new  Word[total][total];
		Word.matching(args,vectors,tree,wordVectors,allfre,allwords);
	while(true)	
	{System.out.println("<==============>Main menu<==============>");
	System.out.println("1)   Displaying BST build from Vocabulary File");
	System.out.println("2)   Displaying Vectors build from Input files.");
	System.out.println("3)   Viewing Match words and its frequency");
	System.out.println("4)   Searching a query->It should display all the files query found in.");
	System.out.println("5)   Exit");
	
	Scanner obj= new Scanner(System.in);
	int choice=0;
	 
	choice= obj.nextInt();
	try {
		Switch(QueryEachFilesMatch,QueryAllFilesMatch,vectors,wordVectors,args,allwords,fre,choice,tree);
	} catch (WrongMenuOptionInput e) {
		// TODO Auto-generated catch block
		 System.err.println(e);
	}
	
	}
		
	//obj.close();
}
}

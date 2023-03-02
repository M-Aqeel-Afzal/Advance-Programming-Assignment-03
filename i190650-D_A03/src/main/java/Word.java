
public class Word {
protected int frequency;
protected String word;
Word(String w,int f)
{
	frequency=f;
	word=w;
}
public void updateFrequency()
{
	System.out.println("update call for duplicate word: "+word);
	frequency=frequency+1;;
	
}
public static int findTotalmatch(String[] args,String[] vectors,B_S_T tree,int[] allfre)
{
	int total=0,all=0;
	for(int j=0;j<args.length-1;j++)
	{	
		all=0;
		
					String[] arr = vectors[j].split(" ");    

					
					
					 for ( String ss : arr) {
			            	
			            	String s3=ss;
			            	String s4=tree.Search(tree.root,s3);
			            	//System.out.println(j+"  match------------> "+s4);
			                if(s3.equalsIgnoreCase(s4))
			                {
			                	all++;
			                	total++;
			                }
			                
			            }
					 
					 allfre[j]=all;
					
                
           

	}
	return total;
}
public static void matching(String[] args,String[] vectors,B_S_T tree,Word[][] wordVectors,int[] allfre,int[] allwords)
{
	
	
	 Word[] wordVectors1 = new Word[findTotalmatch(args,vectors,tree,allfre)];	 
	
	 int l=0;
	for(int j=0;j<args.length-1;j++)
	{	
		
		int k=0;
		boolean flag1=true;
					String[] arr = vectors[j].split(" ");  		
            for ( String ss : arr) {
            	
            	String s3=ss;
            	String s4=tree.Search(tree.root,s3);
            	
                if(s3.equalsIgnoreCase(s4))
                {
                	
                	
              
               
                for(;k<l;k++)
            	{
            		if( wordVectors1[k].word.equalsIgnoreCase(s3))
            		{
            			flag1=false;
            			wordVectors1[k].updateFrequency();
            			
            		}
            	}
                if(flag1)
                { wordVectors1[l]= new Word(s3,1);
               
                l++;
                }
               
                k=0;
               
              //  System.out.println(wordVectors1[1].frequency);
                }
                
            }
            System.out.println("Total number of matched words of file "+args[j+1]+" in vocabolary file are: "+allfre[j]+"\n");
           
            allwords[j]=l;
            
				for(int m=0;m<allwords[j];m++)
				{
					
					wordVectors[j][m]=wordVectors1[m];
					 wordVectors[j][m]= new Word(wordVectors1[m].word,wordVectors1[m].frequency);
					
				}
				 
			
l=0;
	}
}
public static int QueryResolve(int[] eachfile,int allfile,String query,Word[][] wordVectors,String[] args,int[] allwords,int[] fre)
{
	
	String[] arr = query.split(" ");    

	boolean flag=false;
	
	 for ( String ss : arr) {
		 String s3=ss;
		 flag=false;
		for(int i=0;i<args.length-1;i++)
		{
			flag=false;
			for(int j=0;j<allwords[i];j++)
			{
				
				
		            	
		            	
		                if(s3.equalsIgnoreCase(wordVectors[i][j].word))
		                {
		                	eachfile[i]++;
		                	fre[i]=fre[i]+wordVectors[i][j].frequency;
		                	flag=true;
		                	
		                }
		                
		            if(flag)
		            	break;
			}
			 
			
		}
	
 }
	 
	 
	 String[] arr1 = query.split(" ");    

	
		
		 for ( String ss : arr1) {
			 String s3=ss;
			 flag=false;
			for(int i=0;i<args.length-1;i++)
			{
				for(int j=0;j<allwords[i];j++)
				{
					
					
			            	
			            	
			                if(s3.equalsIgnoreCase(wordVectors[i][j].word))
			                {
			                	allfile++;
			                	 
			                	flag=true;
			                	
			                }
			                
			            if(flag)
			            	break;
				}
				 if(flag)
		            	break;
				
			}
		
	 }
	 return allfile;
}

}

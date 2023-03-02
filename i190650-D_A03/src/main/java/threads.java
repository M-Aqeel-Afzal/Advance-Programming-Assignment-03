import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

class file_threads extends Thread {
	B_S_T tree;
	Vector<String> tokan_vacs;
	boolean flag=true;
    public file_threads(String string,B_S_T t,Vector<String> tokan) {
		// TODO Auto-generated constructor stub
    	super(string);
    	tree=t;
    	tokan_vacs=tokan;
	}

	public void run()
    {
		 
		
		  try {
       
        	synchronized(this)
        	{
        	if(Thread.currentThread().getName().equals("vocabulary1.txt")&&flag)
			{
				
					flag=false;
				int j=0;
				File myObj = new File(Thread.currentThread().getName());
			    
			    Scanner myReader = null;
				try {
					myReader = new Scanner(myObj);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    while (myReader.hasNextLine()) {
			      String data = myReader.nextLine();
			      j++;
			      tree.insert(data);                      // writting vacobolary file into BST tree
			         
			    }
			    myReader.close();
			   
			}
        	else if(!Thread.currentThread().getName().equals("vocabulary1.txt")&&flag)
			{
				
        		flag=false;	
				int j=0;
				File myObj = new File(Thread.currentThread().getName());
			    
			    Scanner myReader = null;
				try {
					myReader = new Scanner(myObj);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println(Thread.currentThread().getName()+" File not found");
				}
			    while (myReader.hasNextLine()) {
			      String data = myReader.nextLine();
		            String[] arr = data.split(" ");    

		            for ( String ss : arr) {
		            	j++;
					      tokan_vacs.add(ss)  ;
					      System.out.print("  "+ss);
		            }
			                            // writting vacobolary file into BST tree
		            System.out.println("");
			         
			    }
			    myReader.close();
			}
        	this.notify();
        	}
        	
        	
        	
        	
        	
        	
        	
        	
        	
            System.out.println(
                "Thread " + Thread.currentThread().getName()
                + " is running");
            wait(2);
           
            	
    		}
        
        catch (Exception e) {
           
        }
		
		
		}
    }
	


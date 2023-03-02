
public class B_S_T {
	
	public class node {
	    protected String Data;
	    protected node Left, Right;

	    public node() 
	    {
	    	Left = null;
	    	Right = null;
	    }

	    public node(String data)
	    {
	        this(data,null,null);
	    }
	    public node(String data, node lp, node rp) 
	    {
	        this.Data = data; 
	        Left = lp; 
	        Right = rp;
	    }
	}
	
	
    protected node root = null;
    public B_S_T(){}

    public void clear()
    {
        root = null;
    }

    public boolean isEmpty() 
    {
        return root == null;
    }


    public void insert(String data) 
    {
    	node current = root, preveous = null;
    while (current != null) {
    	preveous = current;
        if (current.Data.compareTo(data) < 0)
        {  current = current.Right;
        
        }
        else {current = current.Left;
        
        }
    }
    if (root == null)
        root = new node(data);
    else if (preveous.Data.compareTo(data) < 0)
    	{preveous.Right = new node(data);
    	System.out.print("right---> "+data +" \n");
    	
    	}
    else {preveous.Left  = new node(data);
    System.out.print("Left--> "+data +" \n");
    }
    }   

    public void inorder()
    {
        inorder(root);
    }

    private void inorder(node p) 
    {
        if (p != null) 
        {
            inorder(p.Left);
            System.out.print(p.Data + " ");
            inorder(p.Right);
        }
    }
    public String Search(node root, String data)
    {
    	
    	B_S_T.node s1 = search(root,data);
    	
    	if(s1==null)
    	return "";
    	else
    		return s1.Data;
    }
 
	public node search(node root, String data)
    {
       
       
       
        if (root==null)
        { 
        	return root;
        }
        if (root.Data.equalsIgnoreCase(data))
        {  
        	return root;
        }
       
        if (root.Data.compareTo(data) <0)
        {
        	return search(root.Right, data);
        }
     
       
        else {
        return search(root.Left, data);
        }
    }
    


}
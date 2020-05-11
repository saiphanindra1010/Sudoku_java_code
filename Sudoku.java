class solve
{
   
    static int SIZE =9;

    private static int[][] A = {
        {0,0,0,0,8,0,0,0,0},  //sample input sudoku '0'represents empty spaces
        {8,0,9,0,7,1,0,2,0},
        {4,0,3,5,0,0,0,0,1},
        {0,0,0,1,0,0,0,0,7},
        {0,0,2,0,3,4,0,8,0},
        {7,3,0,0,0,9,0,0,4},
        {9,0,0,0,0,0,7,0,2},
        {0,0,8,2,0,5,0,9,0},
        {1,0,0,0,4,0,3,0,0}
    };

    public static void DispSudoku()
    {
    	
        for(int i=0;i<SIZE;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                System.out.print(A[i][j]+"\t");
            }
	    //To display the output 
            System.out.println("");
        }
    }

 
    public static int[] Unassigned(int row, int col)
    {
        int unassign = 0;
        for(int i=0;i<SIZE;i++)
        {
            for(int j=0;j<SIZE;j++)
            {
                if(A[i][j] == 0)
                {
                    
                    row = i;
                    col = j;
                    unassign = 1;
                    int[] a = {unassign, row, col};
                    return a;
                }
            }
        }
        int[] a = {unassign, -1, -1};
        return a;
    }


    public static boolean Done(int n, int r, int c)
    {
        
        for(int i=0;i<SIZE;i++)
        {
           
            if(A[r][i] == n)
                return false;
        }
        
        for(int i=0;i<SIZE;i++)
        {
          
            if(A[i][c] == n)
                return false;
        }
        
        int row_start = (r/3)*3;
        int col_start = (c/3)*3;
        for(int i=row_start;i<row_start+3;i++)
        {
            for(int j=col_start;j<col_start+3;j++)
            {
                if(A[i][j]==n)
                    return false;
            }
        }
        return true;
    }

   
    public static boolean SolveSU()  //returns weather the given values are valid or not
	    
    {
        int row=0;
        int col=0;
        int[] a = Unassigned(row, col);//unassigning unmatched values with 0(Replacing)
       
        if(a[0] == 0)
            return true;
        
        row = a[1];
        col = a[2];
        for(int i=1;i<=SIZE;i++)
        {
         
            if(Done(i, row, col))// checking Done method
            {
                A[row][col] = i;
                
                if(SolveSU())//recurse the method
                    return true;
             
                A[row][col]=0;
            }
        }
        return false;
    }

}
public class Sudoku extends solve{
	
    public static void main(String[] args)
    {
        
        if (SolveSU())
            DispSudoku();
        else
            System.out.println("The values in the puzzel is not valid");
    }
}

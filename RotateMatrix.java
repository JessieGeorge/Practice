/**
 * 1.7 rotate by 90 degrees
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class RotateMatrix {

	private static int n;
	private static int[][] matrix;
	
	private void input()throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter n");
		n = Integer.parseInt(br.readLine());
		matrix = new int[n][n];
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
			{
				System.out.println("Enter a number");
				matrix[i][j] = Integer.parseInt(br.readLine());
			}
			
		}
	}
	
	/*
	 * Book solution
	 * Time O(n^2) best possible because you have to touch each element
	 * Space O(1)
	 */
	private void rotate(int[][]matrix)
	{
		n = matrix[0].length;
		
		for(int layer=0; layer<n/2; layer++)
		{
			int first = layer;
			int last = n-1-layer;
			
			for(int i=first; i<last; i++)
			{
				int offset = i - first;
				
				//save top
				int top = matrix[first][i];
				
				//left->top
				matrix[first][i] = matrix[last-offset][first];
				
				//bottom->left
				matrix[last-offset][first] = matrix[last][last-offset];
				
				//right->bottom
				matrix[last][last-offset] = matrix[i][last];
				
				//top->right
				matrix[i][last] = top;
			}
			
		}
		
	}
	
	private void display(int[][] matrix)
	{
		System.out.println();
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String args[])throws IOException
	{
		RotateMatrix R = new RotateMatrix();
		R.input();
		R.display(matrix);
		R.rotate(matrix);
		R.display(matrix);
	}
}

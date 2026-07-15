package stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximalRectangle {

    /**
     * Finds the largest rectangle containing only '1's in a binary matrix.
     * * @param matrix A 2D binary matrix of characters '0' and '1'
     * @return The area of the largest rectangle
     */
    public int maximalRectangle(char[][] matrix) {
        int m=matrix.length,n=matrix[0].length;
        int[]arr=new int[n];
        long max=Long.MIN_VALUE;
        for(int i=m-1;i>=0;i--){
            for(int j=0;j<n;j++){
                if(matrix[i][j]=='0')arr[j]=0;
                else arr[j]+=1;
            }
            max=Math.max(max,maxArea(arr));
        }

        return Math.toIntExact(max);
    }

    private int[] nextSmaller(int[]arr){
        int n=arr.length;
        int[]res=new int[n];
        Deque<Integer> st=new ArrayDeque<>();
        for(int i=0;i<n;i++){
            while(!st.isEmpty()&&arr[i]<arr[st.peek()])res[st.pop()]=i;
            st.push(i);
        }
        while(!st.isEmpty())res[st.pop()]=n;
        return res;
    }
    private int[] previousSmaller(int[]arr){
        int n=arr.length;
        int[]res=new int[n];
        Deque<Integer> st=new ArrayDeque<>();
        for(int i=n-1;i>=0;i--){
            while(!st.isEmpty()&&arr[i]<arr[st.peek()])res[st.pop()]=i;
            st.push(i);
        }
        while(!st.isEmpty())res[st.pop()]=-1;
        return res;
    }

    private long maxArea(int[]arr){
        long max=Integer.MIN_VALUE;
        int n=arr.length;
        int[]ns=nextSmaller(arr),ps=previousSmaller(arr);
        for(int i=0;i<n;i++){
            int left=ps[i]+1,right=ns[i]-1,length=right-left+1;
            long area=((long)length*arr[i]);
            max=Math.max(max,area);
        }
        return max;
    }

    public static void main(String[] args) {
        MaximalRectangle solver = new MaximalRectangle();

        Object[][] tests = {
                {
                        new char[][]{
                                {'1', '0', '1', '0', '0'},
                                {'1', '0', '1', '1', '1'},
                                {'1', '1', '1', '1', '1'},
                                {'1', '0', '0', '1', '0'}
                        }, 6, "Standard case with mixed values"
                },
                {
                        new char[][]{{'0'}}, 0, "Single element zero"
                },
                {
                        new char[][]{{'1'}}, 1, "Single element one"
                },
                {
                        new char[][]{
                                {'0', '0'},
                                {'0', '0'}
                        }, 0, "All zeros"
                },
                {
                        new char[][]{
                                {'1', '1'},
                                {'1', '1'}
                        }, 4, "All ones"
                },
                {
                        new char[][]{
                                {'1', '0', '1', '1', '1'},
                                {'0', '1', '0', '1', '0'},
                                {'1', '1', '1', '1', '1'}
                        }, 3, "Disconnected rectangles"
                },
                {
                        new char[][]{
                                {'1', '1', '1', '1', '1', '1'}
                        }, 6, "Single row of ones"
                },
                {
                        new char[][]{
                                {'1'}, {'1'}, {'1'}, {'1'}
                        }, 4, "Single column of ones"
                },
                {
                        new char[][]{
                                {'1', '1', '0', '1'},
                                {'1', '1', '0', '1'},
                                {'1', '1', '0', '0'}
                        }, 6, "Tall rectangle on left"
                },
                {
                        new char[][]{
                                {'0', '1', '1'},
                                {'1', '1', '1'},
                                {'0', '1', '1'}
                        }, 6, "Rectangle in the center/right"
                },
                {
                        new char[][]{
                                {'1', '0', '1', '1'},
                                {'1', '1', '1', '1'},
                                {'1', '1', '1', '0'},
                                {'1', '1', '1', '1'}
                        }, 9, "Complex shape with 3x3 internal rectangle"
                }
        };

        int passed = 0;
        for (int i = 0; i < tests.length; i++) {
            char[][] matrix = (char[][]) tests[i][0];
            int expected = (int) tests[i][1];
            String description = (String) tests[i][2];

            int actual = solver.maximalRectangle(matrix);

            System.out.println("Test Case " + (i + 1) + ": " + description);
            System.out.println("Expected: " + expected + " | Actual: " + actual);

            if (actual == expected) {
                System.out.println("Result: PASSED");
                passed++;
            } else {
                System.out.println("Result: FAILED");
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("Total Passed: " + passed + "/" + tests.length);
    }
}
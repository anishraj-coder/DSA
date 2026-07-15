package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PriorityQueueStructure {

    private List<Integer> heap;

    public PriorityQueueStructure() {
        heap = new ArrayList<>();
    }

    /**
     * Type 0: Remove and return the minimum element present in the heap.
     * Assumes heap is not empty when called.
     */
    public int extractMinElement() {
        if(heap.isEmpty())return -1;
        int ans=heap.getFirst();
        this.deleteElement(0);
        return ans;
    }

    /**
     * Type 1: Delete the element present at the 'i'th index.
     * Assumes index i is valid and exists within the heap bounds.
     */
    public void deleteElement(int i) {
        int n=heap.size();
        if(i==n-1){
            heap.removeLast();
            return;
        }
        swap(i,n-1);

        heap.removeLast();
        n--;
        if(i>0&&heap.get(i)<heap.get((i-1)/2)){
            int curr=i;
            while(curr>0){
                int pIdx=(curr-1)/2;
                if(heap.get(pIdx)<=heap.get(curr))break;
                swap(curr,pIdx);
                curr=pIdx;
            }
        }else{
            int curr=i;
            while(curr<n){
                int right=2*curr+2,left=2*curr+1,min=curr;
                if(left<n&&heap.get(left)<heap.get(min))min=left;
                if(right<n&&heap.get(right)<heap.get(min))min=right;
                if(min==curr)break;
                swap(curr,min);
                curr=min;
            }
        }
    }

    /**
     * Type 2: Insert the value 'key' into the heap.
     */
    public void insert(int key) {
        heap.add(key);
        int i=heap.size()-1;
        while(i>0){
            int pIdx=(i-1)/2;
            if(heap.get(pIdx)<=heap.get(i))break;
            swap(pIdx,i);
            i=pIdx;
        }
    }

    /**
     * Helper method to expose current underlying heap array list state for verification.
     */
    public List<Integer> getHeapState() {
        return this.heap;
    }

    private void swap(int i, int j){
        int t=heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,t);
    }

    // =========================================================================
    // TESTING HARNESS (Do not modify code below this line)
    // =========================================================================

    private static class TestCase {
        int id;
        String description;
        List<Query> queries;
        List<Object> expectedOutputs; // Can contain Integer (for extractMin), or List<Integer> for final heap structure checks

        TestCase(int id, String description, List<Query> queries, List<Object> expectedOutputs) {
            this.id = id;
            this.description = description;
            this.queries = queries;
            this.expectedOutputs = expectedOutputs;
        }
    }

    private static class Query {
        int type;
        int val; // used as key for type 2, index for type 1, ignored for type 0

        Query(int type) { this.type = type; }
        Query(int type, int val) { this.type = type; this.val = val; }
    }

    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Test 1: Sample 1 Basic Insert and Extract
        testCases.add(new TestCase(1, "Sample Input 1 - Basic inserts and extractMin",
                Arrays.asList(new Query(2, 2), new Query(2, 1), new Query(0)),
                Arrays.asList(1)
        ));

        // Test 2: Sample 2 Insert, Delete by Index, and Extract
        testCases.add(new TestCase(2, "Sample Input 2 - Insert, delete index 2, extractMin",
                Arrays.asList(new Query(2, 5), new Query(2, 43), new Query(2, 15), new Query(1, 2), new Query(0)),
                Arrays.asList(15)
        ));

        // Test 3: Duplicates handling
        testCases.add(new TestCase(3, "Handling duplicate values",
                Arrays.asList(new Query(2, 10), new Query(2, 10), new Query(2, 5), new Query(2, 10), new Query(0), new Query(0)),
                Arrays.asList(5, 10)
        ));

        // Test 4: Delete Root Element directly via Index 0
        testCases.add(new TestCase(4, "Delete Root directly (index 0) and bubbling up",
                Arrays.asList(new Query(2, 20), new Query(2, 30), new Query(2, 40), new Query(1, 0), new Query(0)),
                Arrays.asList(30)
        ));

        // Test 5: Delete last leaf element (no heapify down needed)
        testCases.add(new TestCase(5, "Delete last leaf element",
                Arrays.asList(new Query(2, 5), new Query(2, 15), new Query(2, 25), new Query(1, 2), new Query(0)),
                Arrays.asList(5)
        ));

        // Test 6: Delete internal node triggering heapify down
        testCases.add(new TestCase(6, "Delete internal node triggering heapify down",
                Arrays.asList(new Query(2, 10), new Query(2, 20), new Query(2, 30), new Query(2, 40), new Query(2, 50), new Query(1, 1), new Query(0), new Query(0)),
                Arrays.asList(10, 30)
        ));

        // Test 7: Delete internal node triggering heapify up
        testCases.add(new TestCase(7, "Delete internal node triggering heapify up",
                Arrays.asList(new Query(2, 10), new Query(2, 80), new Query(2, 90), new Query(2, 85), new Query(1, 1), new Query(0), new Query(0)),
                Arrays.asList(10, 85)
        ));

        // Test 8: Deep decreasing linear insertion (Worst Case Bubble up)
        testCases.add(new TestCase(8, "Strictly decreasing inserts (continuous bubble up)",
                Arrays.asList(new Query(2, 50), new Query(2, 40), new Query(2, 30), new Query(2, 20), new Query(2, 10), new Query(0)),
                Arrays.asList(10)
        ));

        // Test 9: Complex alternating sequence checking structure validation at the end
        testCases.add(new TestCase(9, "Complex alternating operations with valid heap state evaluation",
                Arrays.asList(new Query(2, 15), new Query(2, 7), new Query(2, 9), new Query(2, 12), new Query(0), new Query(1, 1), new Query(2, 4)),
                Arrays.asList(7, Arrays.asList(4, 15, 12)) // After actions, structure must be a valid min-heap shape
        ));

        // Test 10: Single element operations
        testCases.add(new TestCase(10, "Single element insert, delete and extract cycle",
                Arrays.asList(new Query(2, 100), new Query(0), new Query(2, 200), new Query(1, 0)),
                Arrays.asList(100, Arrays.asList())
        ));

        int passed = 0;

        System.out.println("========== RUNNING MIN HEAP TEST SUITE ==========\n");

        for (TestCase tc : testCases) {
            PriorityQueueStructure pq = new PriorityQueueStructure();
            List<Object> actualOutputs = new ArrayList<>();
            boolean failed = false;
            int expectIdx = 0;

            try {
                for (Query q : tc.queries) {
                    if (q.type == 0) {
                        actualOutputs.add(pq.extractMinElement());
                        expectIdx++;
                    } else if (q.type == 1) {
                        pq.deleteElement(q.val);
                    } else if (q.type == 2) {
                        pq.insert(q.val);
                    }
                }

                // Add remaining structure check if expected output contains list configuration
                if (expectIdx < tc.expectedOutputs.size() && tc.expectedOutputs.get(expectIdx) instanceof List) {
                    actualOutputs.add(pq.getHeapState());
                }

                // Verification
                if (actualOutputs.size() != tc.expectedOutputs.size()) {
                    failed = true;
                } else {
                    for (int i = 0; i < tc.expectedOutputs.size(); i++) {
                        if (!Objects.deepEquals(actualOutputs.get(i), tc.expectedOutputs.get(i))) {
                            failed = true;
                            break;
                        }
                    }
                }

                if (!failed) {
                    System.out.printf("Test Case %d: PASSED - %s\n", tc.id, tc.description);
                    passed++;
                } else {
                    System.out.printf("Test Case %d: FAILED - %s\n", tc.id, tc.description);
                    System.out.println("   Expected output stream/state: " + tc.expectedOutputs);
                    System.out.println("   Actual output stream/state:   " + actualOutputs);
                }

            } catch (Exception e) {
                System.out.printf("Test Case %d: FAILED (CRASHED) - %s\n", tc.id, tc.description);
                e.printStackTrace();
            }
        }

        System.out.println("\n-------------------------------------------------");
        System.out.printf("Final Results: Passed %d/%d tests.\n", passed, testCases.size());
        System.out.println("-------------------------------------------------");
    }
}
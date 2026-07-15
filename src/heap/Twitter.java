package heap;

import java.util.*;

public class Twitter {

    static class Node<T>{
        T val;
        Node next;
        Node(T val){
            this.val=val;
            next=null;
        }
        Node(T val,Node next){
            this(val);
            this.next=next;
        }


    }

    static class MyLinkedList<T>{
        Node<T>head;
        int size;

        MyLinkedList(){
            size=0;
            head=null;
        }
        void addFirst(T n ){
            Node<T>newNode=new Node<>(n,head);
            addFirst(newNode);
        }
        void addFirst(Node<T>n){
            n.next=head;
            head=n;
            size++;
        }
        Node<T> getFirst(){
            return head;
        }
        int size(){
            return size;
        }
        List<T>getList(){
            List<T>list=new LinkedList<>();
            Node<T>curr=head;
            while(curr!=null){
                list.add(curr.val);
                curr=curr.next;
            }
            return list;
        }
    }

    static class Tweet implements Comparable<Tweet>{
        int time,tweetId;
        Tweet(int time,int tweetId){
            this.time=time;
            this.tweetId=tweetId;
        }

        @Override
        public int compareTo(Tweet o){
            return o.time-this.time;
        }
    }

    static class User{
        HashSet<User>following;
        int userId;
        MyLinkedList<Tweet>tweets;
        User(int userId){
            this.following=new HashSet<>();
            this.userId=userId;
            this.tweets=new MyLinkedList<>();
        }
        public void addFollower(User user){
            following.add(user);
        }
        public void removeFollower(User user){
            following.remove(user);
        }
        public void addTweet(Tweet tweet){
            tweets.addFirst(tweet);
        }
    }


    private HashMap<Integer,User> users;
    int count;
    /** Initialize your data structure here. */
    public Twitter() {
        users =new HashMap<>();
        count=0;
    }

    /** Composes a new tweet with ID tweetId by the user userId. */
    public void postTweet(int userId, int tweetId) {
        if(!users.containsKey(userId)){
            users.put(userId,new User(userId));
        }
        User user=users.get(userId);
        user.addTweet(new Tweet(count,tweetId));
        count++;
    }

    /** Retrieves the 10 most recent tweet IDs in the user's news feed. */
    public List<Integer> getNewsFeed(int userId) {
        if(!users.containsKey(userId))return new ArrayList<>();
        User user=users.get(userId);
        if(user.following.isEmpty()){
            List<Integer>ans=new ArrayList<>();
            int i=0;
            for(Tweet t:user.tweets.getList()){
                ans.add(t.tweetId);
                i++;
                if(i==10)break;

            }
            return ans;
        }
        List<Integer>ans=new ArrayList<>();
        List<User>f=new ArrayList<>(user.following);
        PriorityQueue<Node<Tweet>>pq=new PriorityQueue<>((a,b)->a.val.compareTo(b.val));
        if(user.tweets.size()>0) pq.offer(user.tweets.getFirst());
        for(int i=0;i<f.size();i++) {
            if(f.get(i).tweets.getFirst()!=null)  pq.offer(f.get(i).tweets.getFirst());
        }
        for(int i=0;i<10;i++){
            if(pq.isEmpty())break;
            Node<Tweet>res=pq.poll();
            ans.add(res.val.tweetId);
            if(res.next!=null) {
                res = res.next;
                pq.offer(res);
            }
        }

        return ans;
    }

    /** The user with ID followerId started following the user with ID followeeId. */
    public void follow(int followerId, int followeeId) {
        if(!users.containsKey(followerId))users.put(followerId,new User(followerId));
        if(!users.containsKey(followeeId))users.put(followeeId,new User(followeeId));
        User follower=users.get(followerId);
        follower.addFollower(users.get(followeeId));
    }

    /** The user with ID followerId started unfollowing the user with ID followeeId. */
    public void unfollow(int followerId, int followeeId) {
        if(!users.containsKey(followeeId)||!users.containsKey(followerId))return;
        users.get(followerId).removeFollower(users.get(followeeId));
    }

    // ==========================================
    // TEST HARNESS (MAIN RUNNER)
    // ==========================================
    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        System.out.println("Executing Test Suite for Design Twitter...\n");

        // ------------------------------------------------------------
        // Test Case 1: Standard Example Walkthrough
        // ------------------------------------------------------------
        try {
            Twitter t = new Twitter();
            t.postTweet(1, 5);
            List<Integer> feed1 = t.getNewsFeed(1);
            t.follow(1, 2);
            t.postTweet(2, 6);
            List<Integer> feed2 = t.getNewsFeed(1);
            t.unfollow(1, 2);
            List<Integer> feed3 = t.getNewsFeed(1);

            boolean p1 = feed1.equals(Arrays.asList(5));
            boolean p2 = feed2.equals(Arrays.asList(6, 5));
            boolean p3 = feed3.equals(Arrays.asList(5));

            if (p1 && p2 && p3) {
                System.out.println("✔ Test Case 1 Passed: Standard Example Walkthrough");
                passed++;
            } else {
                System.out.println("❌ Test Case 1 Failed: Standard Example Walkthrough");
                System.out.println("   Expected: [5] -> [6, 5] -> [5]");
                System.out.println("   Actual:   " + feed1 + " -> " + feed2 + " -> " + feed3);
                failed++;
            }
        } catch (Exception e) {
            System.out.println("❌ Test Case 1 Failed with Exception: " + e.getMessage());
            failed++;
        }

        // ------------------------------------------------------------
        // Test Case 2: Empty Feed for New User
        // ------------------------------------------------------------
        try {
            Twitter t = new Twitter();
            List<Integer> feed = t.getNewsFeed(99);
            if (feed != null && feed.isEmpty()) {
                System.out.println("✔ Test Case 2 Passed: Empty Feed for New User");
                passed++;
            } else {
                System.out.println("❌ Test Case 2 Failed: Empty Feed for New User");
                System.out.println("   Expected: []");
                System.out.println("   Actual:   " + feed);
                failed++;
            }
        } catch (Exception e) {
            System.out.println("❌ Test Case 2 Failed with Exception: " + e.getMessage());
            failed++;
        }

        // ------------------------------------------------------------
        // Test Case 3: User Sees Their Own Tweets
        // ------------------------------------------------------------
        try {
            Twitter t = new Twitter();
            t.postTweet(5, 101);
            t.postTweet(5, 102);
            List<Integer> feed = t.getNewsFeed(5);
            if (feed.equals(Arrays.asList(102, 101))) {
                System.out.println("✔ Test Case 3 Passed: User Sees Their Own Tweets (Chrono Order)");
                passed++;
            } else {
                System.out.println("❌ Test Case 3 Failed: User Sees Their Own Tweets");
                System.out.println("   Expected: [102, 101]");
                System.out.println("   Actual:   " + feed);
                failed++;
            }
        } catch (Exception e) {
            System.out.println("❌ Test Case 3 Failed with Exception: " + e.getMessage());
            failed++;
        }

        // ------------------------------------------------------------
        // Test Case 4: Pagination / 10 Most Recent Tweets Cap
        // ------------------------------------------------------------
        try {
            Twitter t = new Twitter();
            for (int i = 1; i <= 12; i++) {
                t.postTweet(1, i); // tweets 1 to 12
            }
            List<Integer> feed = t.getNewsFeed(1);
            List<Integer> expected = Arrays.asList(12, 11, 10, 9, 8, 7, 6, 5, 4, 3);
            if (feed.equals(expected)) {
                System.out.println("✔ Test Case 4 Passed: 10 Most Recent Tweets Limit Cap");
                passed++;
            } else {
                System.out.println("❌ Test Case 4 Failed: 10 Most Recent Tweets Limit Cap");
                System.out.println("   Expected: " + expected);
                System.out.println("   Actual:   " + feed);
                failed++;
            }
        } catch (Exception e) {
            System.out.println("❌ Test Case 4 Failed with Exception: " + e.getMessage());
            failed++;
        }

        // ------------------------------------------------------------
        // Test Case 5: Unfollowing a User Removing Feeds Instantly
        // ------------------------------------------------------------
        try {
            Twitter t = new Twitter();
            t.follow(10, 20);
            t.postTweet(20, 500);
            t.unfollow(10, 20);
            List<Integer> feed = t.getNewsFeed(10);
            if (feed != null && feed.isEmpty()) {
                System.out.println("✔ Test Case 5 Passed: Unfollowing Drops Followee Tweets");
                passed++;
            } else {
                System.out.println("❌ Test Case 5 Failed: Unfollowing Drops Followee Tweets");
                System.out.println("   Expected: []");
                System.out.println("   Actual:   " + feed);
                failed++;
            }
        } catch (Exception e) {
            System.out.println("❌ Test Case 5 Failed with Exception: " + e.getMessage());
            failed++;
        }

        // ------------------------------------------------------------
        // Test Case 6: Multiple Followees Interleaved Timeline
        // ------------------------------------------------------------
        try {
            Twitter t = new Twitter();
            t.follow(1, 2);
            t.follow(1, 3);

            t.postTweet(2, 201); // older
            t.postTweet(3, 301); // mid
            t.postTweet(1, 101); // newer (self)

            List<Integer> feed = t.getNewsFeed(1);
            List<Integer> expected = Arrays.asList(101, 301, 201);
            if (feed.equals(expected)) {
                System.out.println("✔ Test Case 6 Passed: Interleaved Inter-user Chrono Order");
                passed++;
            } else {
                System.out.println("❌ Test Case 6 Failed: Interleaved Inter-user Chrono Order");
                System.out.println("   Expected: " + expected);
                System.out.println("   Actual:   " + feed);
                failed++;
            }
        } catch (Exception e) {
            System.out.println("❌ Test Case 6 Failed with Exception: " + e.getMessage());
            failed++;
        }

        // ------------------------------------------------------------
        // Test Case 7: Redundant Follow Operations
        // ------------------------------------------------------------
        try {
            Twitter t = new Twitter();
            t.follow(1, 2);
            t.follow(1, 2); // Duplicate follow command
            t.postTweet(2, 88);
            List<Integer> feed = t.getNewsFeed(1);

            // Should not duplicate the tweet in feed
            if (feed.equals(Arrays.asList(88))) {
                System.out.println("✔ Test Case 7 Passed: Redundant Follow Handled Safely");
                passed++;
            } else {
                System.out.println("❌ Test Case 7 Failed: Redundant Follow Handled Safely");
                System.out.println("   Expected: [88]");
                System.out.println("   Actual:   " + feed);
                failed++;
            }
        } catch (Exception e) {
            System.out.println("❌ Test Case 7 Failed with Exception: " + e.getMessage());
            failed++;
        }

        // ------------------------------------------------------------
        // Test Case 8: Redundant/No-Op Unfollow Operations
        // ------------------------------------------------------------
        try {
            Twitter t = new Twitter();
            t.unfollow(1, 2); // Unfollowing someone never followed
            t.postTweet(2, 99);
            List<Integer> feed = t.getNewsFeed(1);
            if (feed != null && feed.isEmpty()) {
                System.out.println("✔ Test Case 8 Passed: No-Op Unfollow Handled Safely");
                passed++;
            } else {
                System.out.println("❌ Test Case 8 Failed: No-Op Unfollow Handled Safely");
                System.out.println("   Expected: []");
                System.out.println("   Actual:   " + feed);
                failed++;
            }
        } catch (Exception e) {
            System.out.println("❌ Test Case 8 Failed with Exception: " + e.getMessage());
            failed++;
        }

        // ------------------------------------------------------------
        // Test Case 9: Global Ordering Sorting Integrity (10 slots split across multiple feeds)
        // ------------------------------------------------------------
        try {
            Twitter t = new Twitter();
            t.follow(1, 2);
            t.follow(1, 3);

            // Generate 15 tweets globally alternating across users
            // 2: 10, 3: 11, 1: 12, 2: 13, 3: 14...
            for(int i = 0; i < 5; i++) {
                t.postTweet(2, 100 + i);
                t.postTweet(3, 200 + i);
                t.postTweet(1, 300 + i);
            }

            // The last 10 posted globally must be retrieved
            List<Integer> feed = t.getNewsFeed(1);
            List<Integer> expected = Arrays.asList(304, 204, 104, 303, 203, 103, 302, 202, 102, 301);

            if (feed.equals(expected)) {
                System.out.println("✔ Test Case 9 Passed: Global Merge-Sorting Integrity (Cap of 10)");
                passed++;
            } else {
                System.out.println("❌ Test Case 9 Failed: Global Merge-Sorting Integrity (Cap of 10)");
                System.out.println("   Expected: " + expected);
                System.out.println("   Actual:   " + feed);
                failed++;
            }
        } catch (Exception e) {
            System.out.println("❌ Test Case 9 Failed with Exception: " + e.getMessage());
            failed++;
        }

        // ------------------------------------------------------------
        // Test Case 10: Tweet Post Order Independent of Tweet ID Value
        // ------------------------------------------------------------
        try {
            Twitter t = new Twitter();
            t.postTweet(1, 9999); // Posted first but high ID value
            t.postTweet(1, 1);    // Posted second but low ID value

            List<Integer> feed = t.getNewsFeed(1);
            List<Integer> expected = Arrays.asList(1, 9999); // strictly by timestamp sequencing
            if (feed.equals(expected)) {
                System.out.println("✔ Test Case 10 Passed: Feeds Ordered strictly by Sequence Time, not by ID Value");
                passed++;
            } else {
                System.out.println("❌ Test Case 10 Failed: Feeds Ordered strictly by Sequence Time, not by ID Value");
                System.out.println("   Expected: " + expected);
                System.out.println("   Actual:   " + feed);
                failed++;
            }
        } catch (Exception e) {
            System.out.println("❌ Test Case 10 Failed with Exception: " + e.getMessage());
            failed++;
        }

        // ------------------------------------------------------------
        // Summary Block
        // ------------------------------------------------------------
        System.out.println("\n--- Execution Summary ---");
        System.out.println("Total Test Cases Run: " + (passed + failed));
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }
}
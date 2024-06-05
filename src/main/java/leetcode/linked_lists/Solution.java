package leetcode.linked_lists;

import structs.linked.LinkedList;
import java.util.HashSet;
import java.util.Set;


public class Solution implements Problems {


    @Override
    public <T> LinkedList<T> removeDups(LinkedList<T> list) {
        Set<T> set = new HashSet<>();
        LinkedList<T> newList = new LinkedList<>();

        for (T x : list) {
            if (set.add(x)) newList.add(x);
        }
        return newList;
    }
}

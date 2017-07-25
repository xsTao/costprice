package nc.bs.mapub.driver.util;

import java.util.LinkedList;

/**
 * ’ª¿‡
 * 
 * @since 6.0
 * @version 2012-5-7 …œŒÁ10:46:49
 * @author liyjf
 */
public class Stacks {
    private LinkedList<String> list = new LinkedList<String>();

    int top = -1;

    public void push(String value) {
        this.top++;
        this.list.addFirst(value);
    }

    public String pop() {
        String temp = this.list.getFirst();
        this.top--;
        this.list.removeFirst();
        return temp;
    }

    public String top() {
        return this.list.getFirst();
    }

    public void clear() {
        this.top = -1;
        this.list.clear();
    }
}

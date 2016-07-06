/**
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (April 2015)
 * Compiler Version Java 1.7
 */

import java.util.Comparator;
 
/**
 * This comparator compares two Print Jobs by their pages.
 *
 */
public class PrintJobPagesComparator implements Comparator<PrintJob> {
 
    @Override
    public int compare(PrintJob job1, PrintJob job2) {
        return job1.getPages() - job2.getPages();
    }
}
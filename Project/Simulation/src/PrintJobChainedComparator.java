/**
 * 
 * @author Long Nguyen
 * 
 * @version 1.0 (April 2015)
 * Compiler Version Java 1.7
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
 
/**
 * This is a chained comparator that is used to sort a list by multiple
 * attributes by chaining a sequence of comparators of individual fields
 * together.
 *
 */
public class PrintJobChainedComparator implements Comparator<PrintJob> {
 
    private List<Comparator<PrintJob>> listComparators;
 
    //@SafeVarargs
    public PrintJobChainedComparator(Comparator<PrintJob>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }
 
    @Override
    public int compare(PrintJob job1, PrintJob job2) {
        for (Comparator<PrintJob> comparator : listComparators) {
            int result = comparator.compare(job1, job2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
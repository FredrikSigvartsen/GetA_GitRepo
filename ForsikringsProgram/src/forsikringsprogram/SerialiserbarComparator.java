
package forsikringsprogram;
import java.io.Serializable;
import java.util.Comparator;

/** 
 * @author Fredrik Aleksander Sigvartsen, Dataingeniør, s236356
 * @author Elias Andreassen Thøgersen, Informasjonsteknologi, s236603
 */
public class SerialiserbarComparator<T> implements Comparator<T>, Serializable {
    
    @Override
    public int compare(T t, T t1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }//end of method compare(T t, T t1)
    
}//end of class SerialiserbarComparator

import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>
{
        public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        int indexT1 = qe1.getInfo().lastIndexOf(" ");
        int indexT2 = qe2.getInfo().lastIndexOf(" ");
        // if (indexT2 == -1 || indexT1 == -1) {
        //     return Double.compare(qe1.getDepth(), qe2.getDepth());
        // }
        String title1 = qe1.getInfo().substring(indexT1);
        String title2 = qe2.getInfo().substring(indexT2);
        
        if (title1.compareTo(title2) < 0) {
            return -1;
        }
        if (title1.compareTo(title2) > 0) {
            return 1;
        }else{
            return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        }
    }


}

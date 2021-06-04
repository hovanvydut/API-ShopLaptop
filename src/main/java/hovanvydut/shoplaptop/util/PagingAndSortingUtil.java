package hovanvydut.shoplaptop.util;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hovanvydut
 * Created on 6/3/21
 */

public class PagingAndSortingUtil {

    public static Sort.Direction getSortDirection(String direction) {
        if ("desc".equals(direction)) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }


    public static Sort processSort(String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }

        return Sort.by(orders);
    }

}

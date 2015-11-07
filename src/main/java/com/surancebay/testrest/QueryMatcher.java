package com.surancebay.testrest;

import org.apache.cxf.jaxrs.ext.ResourceComparator;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfoComparator;
import org.apache.cxf.jaxrs.model.Parameter;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Message;

import javax.ws.rs.core.MultivaluedMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by andrei on 07.11.2015.
 */
public class QueryMatcher extends OperationResourceInfoComparator implements ResourceComparator {

    public QueryMatcher() {
        super(null, null);
    }

    @Override
    public int compare(ClassResourceInfo cri1, ClassResourceInfo cri2, Message message) {
        // Leave Class selection to CXF
        return 0;
    }

    @Override
    public int compare(OperationResourceInfo oper1, OperationResourceInfo oper2, Message message) {

        // Check if CXF can make a decision
        int cxfResult = super.compare(oper1, oper2);
        if (cxfResult != 0)
            return cxfResult;

        int op1Counter = getMatchingRate(oper1, message);
        int op2Counter = getMatchingRate(oper2, message);

        return op1Counter == op2Counter
                ? 0
                : op1Counter<  op2Counter
                ? 1
                : -1;
    }

    /**
     * This method calculates a number indicating a good or bad match between
     * values provided within the request and expected method parameters. A
     * higher number means a better match.
     *
     * @param operation
     *            The operation to be rated, based on contained parameterInfo
     *            values.
     * @param message
     *            A message containing query from user request
     * @return A positive or negative number, indicating a good match between
     *         query and method
     */
    protected int getMatchingRate(OperationResourceInfo operation, Message message) {

        List<Parameter> params = operation.getParameters();
        if (params == null || params.size() == 0)
            return 0;

        // Get Request QueryParams
        Set<String> qParams = getParams((String) message.get(Message.QUERY_STRING));

        int rate = 0;
        for (Parameter p : params) {
            switch (p.getType()) {
                case QUERY:
                    if (qParams.contains(p.getName()))
                        rate += 2;
                    else if (p.getDefaultValue() == null)
                        rate -= 1;
                    break;
                // optionally support other parameter types such as headers, etc
                // case HEADER:
                //  break;
                default:
                    break;
            }
        }
        return rate;
    }

    /**
     * @param query
     *            URL Query Example: 'key=value&key2=value2'
     * @return A Set of all keys, contained within query.
     */
    protected Set<String>  getParams(String query) {
        Set<String>  params = new HashSet<String>();
        if (query == null || query.length() == 0)
            return params;

        MultivaluedMap<String, String> allQueries =
                JAXRSUtils.getStructuredParams(query, "&", false, false);
        return allQueries.keySet();
    }


}

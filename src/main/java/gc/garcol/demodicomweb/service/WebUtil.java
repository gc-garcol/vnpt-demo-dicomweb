package gc.garcol.demodicomweb.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author garcol
 */
public enum  WebUtil {

    INSTANCE;

    public String toQuery(Object object) {
        return toQuery(object, null);
    }

    public String toQuery(Object object, List<String> exceptKey) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(object, Map.class);

        StringBuilder queryBuilder = new StringBuilder();

        for (Object k : map.keySet()){
            String key = (String) k;

            if (map.get(key) == null || (exceptKey != null && exceptKey.contains(key))){
                continue;
            }

            if(map.get(key).getClass().isArray()) {
                for(Object o : (Object[]) map.get(key)) {
                    appendQuery(key, o, queryBuilder);
                }
                continue;
            }

            if(map.get(key) instanceof List) {
                for(Object o : (List) map.get(key)) {
                    appendQuery(key, o, queryBuilder);
                }
                continue;
            }

            if (map.get(key) instanceof Map) {
                for (Map.Entry entry : (Set<Map.Entry>)((Map) map.get(key)).entrySet()) {
                    appendQuery((String) entry.getKey(), entry.getValue(), queryBuilder);
                }
                continue;
            }

            appendQuery(key, map.get(key), queryBuilder);
        }

        if (queryBuilder.length() != 0) {
            queryBuilder.deleteCharAt(queryBuilder.length() - 1);
        }

        return queryBuilder + "";
    }

    private void appendQuery(String key, Object value, StringBuilder queryBuilder) {
        queryBuilder.append(key).append("=").append(value);
        queryBuilder.append("&");
    }
}

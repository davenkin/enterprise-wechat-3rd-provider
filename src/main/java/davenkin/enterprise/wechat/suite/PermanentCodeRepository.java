package davenkin.enterprise.wechat.suite;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yteng on 9/29/17.
 */

@Component
public class PermanentCodeRepository {
    private Map<String, String> codes = new HashMap<>();

    public void save(String corpId, String permanentCode) {
        codes.put(corpId, permanentCode);
    }

    public String getCode(String corpId) {
        return codes.get(corpId);
    }

    public Map<String, String> getCodes() {
        return codes;
    }
}

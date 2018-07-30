package eu.h2020.symbiote.ssppr.clients;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class InnkeeperRegistrationResponse {

    private final String symId;
    private final String sspId;
    private final String result;
    private final Integer registrationExpiration;
    private final List<Map<String,String>> updatedSymId;


	public InnkeeperRegistrationResponse(@JsonProperty("symId") String symId,
                                         @JsonProperty("sspId") String sspId,
                                         @JsonProperty("result") String result,
                                         @JsonProperty("registrationExpiration") Integer registrationExpiration,
                                         @JsonProperty("updatedSymId") List<Map<String, String>> updatedSymId) {
		this.symId = symId;
		this.sspId = sspId;
		this.result = result;
		this.registrationExpiration = registrationExpiration;
		this.updatedSymId = updatedSymId;
	}

    public String getSymId() { return symId; }
    public String getSspId() { return sspId; }
    public String getResult() { return result; }
    public Integer getRegistrationExpiration() { return registrationExpiration; }
    public List<Map<String, String>> getUpdatedSymId() { return updatedSymId; }
}

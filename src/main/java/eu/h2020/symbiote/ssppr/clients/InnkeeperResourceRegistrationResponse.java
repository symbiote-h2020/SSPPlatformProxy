package eu.h2020.symbiote.ssppr.clients;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class InnkeeperResourceRegistrationResponse {
	

	private final String symIdResource;
	private final String sspIdResource;
	private final String symId;
	private final String sspId;
	private final String result;
	private final Integer registration_expiration;

	public InnkeeperResourceRegistrationResponse(@JsonProperty("symIdResource") String symIdResource,
                                                 @JsonProperty("sspIdResource") String sspIdResource,
                                                 @JsonProperty("symId") String symId,
                                                 @JsonProperty("sspId") String sspId,
                                                 @JsonProperty("result") String result,
                                                 @JsonProperty("registrationExpiration") Integer registration_expiration) {
		this.symIdResource = symIdResource;
		this.sspIdResource = sspIdResource;
		this.symId = symId;
		this.sspId = sspId;
		this.result = result;
		this.registration_expiration = registration_expiration;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		InnkeeperResourceRegistrationResponse that = (InnkeeperResourceRegistrationResponse) o;
		return Objects.equals(symIdResource, that.symIdResource) &&
				Objects.equals(sspIdResource, that.sspIdResource) &&
				Objects.equals(symId, that.symId) &&
				Objects.equals(sspId, that.sspId) &&
				Objects.equals(result, that.result) &&
				Objects.equals(registration_expiration, that.registration_expiration);
	}

	@Override
	public int hashCode() {

		return Objects.hash(symIdResource, sspIdResource, symId, sspId, result, registration_expiration);
	}

	@Override
	public String toString() {
		return "InnkeeperResourceRegistrationResponse{" +
				"symIdResource='" + symIdResource + '\'' +
				", sspIdResource='" + sspIdResource + '\'' +
				", symId='" + symId + '\'' +
				", sspId='" + sspId + '\'' +
				", result='" + result + '\'' +
				", registration_expiration=" + registration_expiration +
				'}';
	}
}

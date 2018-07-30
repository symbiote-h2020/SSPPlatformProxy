package eu.h2020.symbiote.ssppr.clients.interfaces;

import eu.h2020.symbiote.cloud.model.ssp.SspRegInfo;
import eu.h2020.symbiote.cloud.model.ssp.SspResource;
import eu.h2020.symbiote.ssppr.clients.InnkeeperRegistrationResponse;
import eu.h2020.symbiote.ssppr.clients.InnkeeperResourceRegistrationResponse;

public interface InnkeeperClient {

    InnkeeperRegistrationResponse registerPlatform(SspRegInfo sspRegInfo, boolean serverValidation);

    InnkeeperRegistrationResponse unregisterPlatform(SspRegInfo sspRegInfo, boolean serverValidation);

    InnkeeperResourceRegistrationResponse registerPlatformResource(SspResource resource, boolean serverValidation);
}

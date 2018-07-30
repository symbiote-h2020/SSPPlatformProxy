package eu.h2020.symbiote.ssppr.clients.feign;

import eu.h2020.symbiote.cloud.model.ssp.SspRegInfo;
import eu.h2020.symbiote.cloud.model.ssp.SspResource;
import eu.h2020.symbiote.security.communication.ApacheCommonsLogger4Feign;
import eu.h2020.symbiote.security.handler.IComponentSecurityHandler;
import eu.h2020.symbiote.ssppr.clients.InnkeeperRegistrationResponse;
import eu.h2020.symbiote.ssppr.clients.InnkeeperResourceRegistrationResponse;
import eu.h2020.symbiote.ssppr.clients.InnkeeperRestControllerConstants;
import eu.h2020.symbiote.ssppr.clients.interfaces.InnkeeperClient;
import feign.*;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory.SERVER_VALIDATION_HEADER;

public class FeignInnkeeperClient implements InnkeeperClient {

    private static final Log logger = LogFactory.getLog(FeignInnkeeperClient.class);

    private InnkeeperI innkeeperClient;

    public FeignInnkeeperClient(IComponentSecurityHandler componentSecurityHandler, String sspId, String localAAMUrl) {

        String innkeeperUrl = localAAMUrl.replace("/paam", InnkeeperRestControllerConstants.INNKEEPER_BASE_PATH);

        this.innkeeperClient = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new ApacheCommonsLogger4Feign(logger))
                .logLevel(Logger.Level.FULL)
                .client(new SymbIoTeSSPFeignComponentClient(
                        componentSecurityHandler,
                        "innkeeper",
                        sspId))
                .target(InnkeeperI.class, innkeeperUrl);

    }

    @Override
    public InnkeeperRegistrationResponse registerPlatform(SspRegInfo sspRegInfo, boolean serverValidation) {
        return this.innkeeperClient.registerPlatform(sspRegInfo, serverValidation);
    }

    @Override
    public InnkeeperRegistrationResponse unregisterPlatform(SspRegInfo sspRegInfo, boolean serverValidation) {
        return this.innkeeperClient.unregisterPlatform(sspRegInfo, serverValidation);
    }

    @Override
    public InnkeeperResourceRegistrationResponse registerPlatformResource(SspResource resource, boolean serverValidation) {
        return this.innkeeperClient.registerPlatformResource(resource, serverValidation);
    }


    private interface InnkeeperI {

        @RequestLine("POST " + InnkeeperRestControllerConstants.INNKEEPER_PLATFORM_REGISTER_REQUEST_PATH)
        @Headers({"Accept: application/json", "Content-Type: application/json",
                SERVER_VALIDATION_HEADER + ": {serverValidation}"})
        InnkeeperRegistrationResponse registerPlatform(SspRegInfo sspRegInfo,
                                                       @Param("serverValidation") Boolean serverValidation);

        @RequestLine("POST " + InnkeeperRestControllerConstants.INNKEEPER_PLATFORM_UNREGISTER_REQUEST_PATH)
        @Headers({"Accept: application/json", "Content-Type: application/json",
                SERVER_VALIDATION_HEADER + ": {serverValidation}"})
        InnkeeperRegistrationResponse unregisterPlatform(SspRegInfo sspRegInfo,
                                                         @Param("serverValidation") Boolean serverValidation);

        @RequestLine("POST " + InnkeeperRestControllerConstants.INNKEEPER_PLATFORM_JOIN_REQUEST_PATH)
        @Headers({"Accept: application/json", "Content-Type: application/json",
                SERVER_VALIDATION_HEADER + ": {serverValidation}"})
        InnkeeperResourceRegistrationResponse registerPlatformResource(SspResource resource,
                                                                       @Param("serverValidation") Boolean serverValidation);
    }

}

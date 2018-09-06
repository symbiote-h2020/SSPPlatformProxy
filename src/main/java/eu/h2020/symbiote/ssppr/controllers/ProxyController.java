package eu.h2020.symbiote.ssppr.controllers;

import eu.h2020.symbiote.client.ssp.feign.SymbIoTeFeignSSPPlatformClientFactory;
import eu.h2020.symbiote.client.ssp.interfaces.InnkeeperClient;
import eu.h2020.symbiote.cloud.model.ssp.SspRegInfo;
import eu.h2020.symbiote.cloud.model.ssp.SspResource;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import eu.h2020.symbiote.ssp.constants.InnkeeperRestControllerConstants;
import eu.h2020.symbiote.ssp.model.InnkeeperRegistrationResponse;
import eu.h2020.symbiote.ssp.model.InnkeeperResourceRegistrationResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/ssppr")
public class ProxyController {
    private static final Log logger = LogFactory.getLog(ProxyController.class);

    private InnkeeperClient innkeeperClient;
    private boolean serverValidation;

    public ProxyController(@Value("${symbIoTe.component.keystore.path}") String keystorePath,
                           @Value("${symbIoTe.component.keystore.password}") String keystorePassword,
                           @Value("${ssp.id}") String sspId,
                           @Value("${symbIoTe.ssp.aam.url}") String localAAMAddress,
                           @Value("${symbIoTe.ssp.base.url}") String sspUrl,
                           @Value("${symbIoTe.component.clientId}") String clientId,
                           @Value("${symbIoTe.component.username}") String componentOwnerUsername,
                           @Value("${symbIoTe.component.password}") String componentOwnerPassword,
                           @Value("${symbIoTe.server.validation}") boolean serverValidation)
            throws SecurityHandlerException {

        this.innkeeperClient = SymbIoTeFeignSSPPlatformClientFactory.getInnkeeperClient(
                new SymbIoTeFeignSSPPlatformClientFactory.Config(
                        keystorePath, keystorePassword, sspId, localAAMAddress, sspUrl,
                        clientId, componentOwnerUsername, componentOwnerPassword
                )
        );
        this.serverValidation = serverValidation;
    }


    @PostMapping(value = InnkeeperRestControllerConstants.INNKEEPER_PLATFORM_REGISTER_REQUEST_PATH)
    public InnkeeperRegistrationResponse platform_register(@RequestBody SspRegInfo sspRegInfo) {
        logger.debug("REGISTRATION MESSAGE:"+ sspRegInfo);
        return this.innkeeperClient.registerPlatform(sspRegInfo, serverValidation);
    }

    @PostMapping(value = InnkeeperRestControllerConstants.INNKEEPER_PLATFORM_UNREGISTER_REQUEST_PATH)
    public InnkeeperRegistrationResponse unregister_platform(@RequestBody SspRegInfo sspRegInfo) {
        logger.debug("UNREGISTRATION MESSAGE:"+ sspRegInfo);
        return this.innkeeperClient.unregisterPlatform(sspRegInfo, serverValidation);
    }

    // PLATFORM RESOURCE REGISTRATION
    @PostMapping(value = InnkeeperRestControllerConstants.INNKEEPER_PLATFORM_JOIN_REQUEST_PATH)
    public InnkeeperResourceRegistrationResponse platform_resources(@RequestBody SspResource resource) {
        logger.debug("PLATFORM RESOURCE REGISTRATION MESSAGE:"+ resource);
        return innkeeperClient.registerPlatformResource(resource, serverValidation);
    }

}

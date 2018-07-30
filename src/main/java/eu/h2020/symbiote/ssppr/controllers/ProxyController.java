package eu.h2020.symbiote.ssppr.controllers;

import eu.h2020.symbiote.cloud.model.ssp.SspRegInfo;
import eu.h2020.symbiote.cloud.model.ssp.SspResource;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import eu.h2020.symbiote.ssppr.clients.InnkeeperRegistrationResponse;
import eu.h2020.symbiote.ssppr.clients.InnkeeperResourceRegistrationResponse;
import eu.h2020.symbiote.ssppr.clients.InnkeeperRestControllerConstants;
import eu.h2020.symbiote.ssppr.clients.feign.SymbIoTeFeignSSPPlatformClientFactory;
import eu.h2020.symbiote.ssppr.clients.interfaces.InnkeeperClient;
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
                           @Value("${symbIoTe.localaam.url}") String localAAMAddress,
                           @Value("${symbIoTe.component.clientId}") String clientId,
                           @Value("${symbIoTe.component.username}") String componentOwnerUsername,
                           @Value("${symbIoTe.component.password}") String componentOwnerPassword,
                           @Value("${symbIoTe.server.validation}") boolean serverValidation)
            throws SecurityHandlerException {

        this.innkeeperClient = SymbIoTeFeignSSPPlatformClientFactory.getInnkeeperClient(
                new SymbIoTeFeignSSPPlatformClientFactory.Config(
                        keystorePath, keystorePassword, sspId, localAAMAddress, clientId, componentOwnerUsername, componentOwnerPassword
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

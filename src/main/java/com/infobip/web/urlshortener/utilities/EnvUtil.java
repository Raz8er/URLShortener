package com.infobip.web.urlshortener.utilities;

import com.infobip.web.urlshortener.exception.HostnameException;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@AllArgsConstructor
public class EnvUtil {
    private final Environment environment;

    public String getServerUrlPrefix() {
        return String.format("http://%s:%s/", getHostname(), getPort());
    }

    private String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new HostnameException("Error when getting hostname", e);
        }
    }

    private String getPort() {
        return environment.getProperty("local.server.port");
    }
}

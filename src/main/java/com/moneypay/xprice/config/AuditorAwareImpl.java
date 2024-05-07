package com.moneypay.xprice.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    private final String auditor;

    public AuditorAwareImpl() throws UnknownHostException {
        final InetAddress inetAddress = InetAddress.getLocalHost();
        auditor = String.format("%s@%s", inetAddress.getHostName(), inetAddress.getHostAddress());
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(auditor);
    }
}

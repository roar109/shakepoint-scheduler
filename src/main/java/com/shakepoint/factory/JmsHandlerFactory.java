package com.shakepoint.factory;

import com.shakepoint.integration.jms.client.handler.JmsHandler;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;

public class JmsHandlerFactory {

        @Resource(lookup = "java:jboss/exported/ConnectionFactory") private ConnectionFactory connectionFactory;

        @Inject private Logger log;

        @Produces @ApplicationScoped public JmsHandler createJmsHandler() {
                return new JmsHandler(connectionFactory, log);
        }
}

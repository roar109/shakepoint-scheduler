package com.shakepoint.test;

import org.apache.log4j.Logger;

import javax.ejb.*;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

@TransactionManagement(TransactionManagementType.BEAN) @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) @MessageDriven(name = "DummyMDB", activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "shakepoint.generate.customer.birthday.promocodes"), @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") }) public class TestMDB implements MessageListener {

        @Inject private Logger log;

        @Override public void onMessage(Message message) {
                log.info(message);
        }
}

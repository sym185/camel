/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.camel.component.spring.ws.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.security.wss4j.Wss4jSecurityInterceptor;

public class ProducerWss4JSecurityHeaderTestInterceptor extends Wss4jSecurityInterceptor {

    public static boolean isX509DataPresent;
    
    protected void validateMessage(SoapMessage soapMessage, MessageContext messageContext) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            soapMessage.writeTo(out);
        } catch (IOException exception) {
          // do nothing - because this is a sample class
        }
        String strMsg = new String(out.toByteArray());
          
        isX509DataPresent = strMsg.contains("X509Data");
        super.validateMessage(soapMessage, messageContext);
    }

}

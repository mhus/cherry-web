/**
 * Copyright (C) 2015 Mike Hummel (mh@mhus.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mhus.app.web.util.area;

import java.util.UUID;

import de.mhus.app.web.api.CallContext;
import de.mhus.app.web.api.VirtualHost;
import de.mhus.app.web.api.WebArea;
import de.mhus.lib.core.logging.MLogUtil;
import de.mhus.lib.core.node.INode;
import de.mhus.lib.errors.MException;
import de.mhus.lib.errors.NotFoundException;
import de.mhus.osgi.api.MOsgi;

public class AreaToService implements WebArea {

    private INode config;
    private String serviceName;
    private WebArea webArea;
    private VirtualHost vHost;
    private UUID instanceId = UUID.randomUUID();

    @Override
    public void doInitialize(UUID instance, VirtualHost vHost, INode config) throws MException {
        this.config = config.getObject("config");
        serviceName = config.getString("service");
        this.vHost = vHost;
    }

    @Override
    public boolean doRequest(UUID instance, CallContext call) throws MException {
        check();
        if (webArea == null) throw new NotFoundException("service not found", serviceName);
        return webArea.doRequest(instanceId, call);
    }

    private synchronized void check() {
        if (webArea == null) {
            try {
                webArea = MOsgi.getService(WebArea.class, "(name=" + serviceName + ")");
                webArea.doInitialize(instanceId, vHost, config);
            } catch (Throwable e) {
                MLogUtil.log().e(serviceName, e);
            }
        }
    }
}

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
package de.mhus.app.web.core;

import de.mhus.app.web.api.WebSession;
import de.mhus.lib.core.IProperties;
import de.mhus.lib.core.MProperties;

public class CherrySession extends MProperties implements WebSession {

    private String sessionId;
    private MProperties pub;

    public CherrySession(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public synchronized IProperties pub() {
        if (pub == null) pub = new MProperties();
        return pub;
    }
}

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
package de.mhus.app.web.api;

import de.mhus.lib.core.config.IConfig;
import de.mhus.lib.errors.MException;

public interface TypeHeaderFactory {

    /**
     * Return a header object if the factory is able to handle the header type.
     *
     * @param header
     * @return a typeheader if the config is accepted
     * @throws MException
     */
    TypeHeader create(IConfig header) throws MException;
}

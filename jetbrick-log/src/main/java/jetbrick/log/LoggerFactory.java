/**
 * Copyright 2013-2014 Guoqiang Chen, Shanghai, China. All rights reserved.
 *
 * Email: subchen@gmail.com
 * URL: http://subchen.github.io/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrick.log;

import jetbrick.log.support.*;

public abstract class LoggerFactory {

    private static final LoggerFactory factory = getDefaultLoggerFactory();

    private static LoggerFactory getDefaultLoggerFactory() {
        String factoryClass = System.getProperty("jetbrick.logger.factory");
        if (factoryClass != null && factoryClass.length() > 0) {
            try {
                Class<?> cls = Class.forName(factoryClass);
                return (LoggerFactory) cls.newInstance();
            } catch(Exception e) {
                throw new IllegalStateException(e);
            }
        }

        try {
            Class.forName("org.slf4j.Logger");
            return new Slf4jLoggerFactory();
        } catch(ClassNotFoundException e) {
        }

        try {
            Class.forName("org.apache.log4j.Logger");
            return new Log4jLoggerFactory();
        } catch(ClassNotFoundException e) {
        }

        return new Jdk14LoggerFactory();
    }

    public static Logger getLogger(Class<?> clazz) {
        return factory.doGetLogger(clazz.getName());
    }

    public static Logger getLogger(String name) {
        return factory.doGetLogger(name);
    }

    protected abstract Logger doGetLogger(String name);
}

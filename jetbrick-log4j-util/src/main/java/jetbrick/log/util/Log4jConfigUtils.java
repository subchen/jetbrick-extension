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
package jetbrick.log.util;

import java.net.URL;
import java.util.*;
import org.apache.log4j.*;

public final class Log4jConfigUtils {

    public static void resetConfiguration() {
        LogManager.resetConfiguration();

        URL config = Thread.currentThread().getContextClassLoader().getResource("/log4j.properties");
        if (config != null) {
            PropertyConfigurator.configure(config);
        }
    }

    /**
     * @param level
     *      OFF, FATAL, ERROR, WARN, INFO, DEBUG and ALL.
     */
    public static void setLevel(String logName, String level) {
        Logger log = (logName == null || logName.length() == 0) ? Logger.getRootLogger() : Logger.getLogger(logName);
        log.setLevel(Level.toLevel(level, Level.DEBUG));
    }

    public static List<Logger> getLoggers() {
        List<Logger> list = new ArrayList<Logger>();
        Enumeration<Logger> loggers = Logger.getRootLogger().getLoggerRepository().getCurrentLoggers();
        while (loggers.hasMoreElements()) {
            list.add(loggers.nextElement());
        }
        return list;
    }
}

/*
 *    Copyright 2021 Szymon Świerkosz
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.swierkosz.log4j2.captor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.ArrayList;

import static org.apache.logging.log4j.Level.TRACE;

/**
 * A class that is responsible for capturing logs and exposing them for testing.
 */
public class LogCaptor extends ArrayList<Log> {

    private LogCaptor(Logger logger) {
        LogCapturingAppender appender = new LogCapturingAppender(this::add);
        logger.removeAppender(appender);
        logger.addAppender(appender);
        logger.setLevel(TRACE);
    }

    /**
     * A LogCaptor factory method
     *
     * @param type a type naming the logger
     * @return an instance of LogCaptor for capturing logs from logger associated with given class
     */
    public static LogCaptor forClass(Class<?> type) {
        return new LogCaptor((Logger) LogManager.getLogger(type));
    }

    /**
     * A LogCaptor factory method
     *
     * @param name a name of the logger
     * @return an instance of LogCaptor for capturing logs from logger associated with given name.
     */
    public static LogCaptor forName(String name) {
        return new LogCaptor((Logger) LogManager.getLogger(name));
    }
}

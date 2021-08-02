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

import org.apache.logging.log4j.Level;

import java.util.Map;
import java.util.Objects;

import static java.util.Collections.emptyMap;

/**
 * A class that represents one log event.
 */
public class Log {

    private final Level level;
    private final String message;
    private final Throwable throwable;
    private final Map<String, String> mdc;

    Log(Level level, String message, Map<String, String> mdc, Throwable throwable) {
        this.level = level;
        this.message = message;
        this.mdc = mdc;
        this.throwable = throwable;
    }

    /**
     * @param level   a level the event was logged with
     * @param message a message the event was logged with
     * @return a representation of one log event
     */
    public static Log log(Level level, String message) {
        return new Log(level, message, emptyMap(), null);
    }

    /**
     * @param level     a level the event was logged with
     * @param message   a message the event was logged with
     * @param throwable a throwable the event was logged with
     * @return a representation of one log event
     */
    public static Log log(Level level, String message, Throwable throwable) {
        return new Log(level, message, emptyMap(), throwable);
    }

    /**
     * @param level   a level the event was logged with
     * @param message a message the event was logged with
     * @param mdc     a mdc context the event was logged with
     * @return a representation of one log event
     */
    public static Log log(Level level, String message, Map<String, String> mdc) {
        return new Log(level, message, mdc, null);
    }

    /**
     * @param level     a level the event was logged with
     * @param message   a message the event was logged with
     * @param throwable a throwable the event was logged with
     * @param mdc       a mdc context the event was logged with
     * @return a representation of one log event
     */
    public static Log log(Level level, String message, Map<String, String> mdc, Throwable throwable) {
        return new Log(level, message, mdc, throwable);
    }

    /**
     * @return a level the event was logged with
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return a message the event was logged with
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return a throwable the event was logged with
     */
    public Throwable getThrowable() {
        return throwable;
    }

    /**
     * @return a mdc context the event was logged with
     */
    public Map<String, String> getMdc() {
        return mdc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Log log = (Log) o;
        return Objects.equals(level, log.level)
                && Objects.equals(message, log.message)
                && Objects.equals(throwable, log.throwable)
                && Objects.equals(mdc, log.mdc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, message, throwable, mdc);
    }

    @Override
    public String toString() {
        return "Log{" +
                "level=" + level +
                ", message='" + message + '\'' +
                ", throwable=" + throwable +
                ", mdc=" + mdc +
                '}';
    }
}

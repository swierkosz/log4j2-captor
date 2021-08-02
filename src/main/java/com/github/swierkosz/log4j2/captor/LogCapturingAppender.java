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

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;

import java.util.function.Consumer;

class LogCapturingAppender extends AbstractAppender {

    private final Consumer<Log> logConsumer;

    LogCapturingAppender(Consumer<Log> logConsumer) {
        super("LogCapturingAppender", null, null, false, Property.EMPTY_ARRAY);
        this.logConsumer = logConsumer;
        start();
    }

    @Override
    public void append(LogEvent event) {
        logConsumer.accept(new Log(
                event.getLevel(),
                event.getMessage().getFormattedMessage(),
                event.getContextData().toMap(),
                event.getThrown()));
    }
}

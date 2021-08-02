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
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.github.swierkosz.log4j2.captor.Log.log;
import static org.assertj.core.api.Assertions.assertThat;

class LogTest {

    @Test
    void shouldCreateLogWithLevelAndMessage() {
        // Given
        Level level = Level.DEBUG;
        String message = "message";

        // When
        Log result = log(level, message);

        // Then
        assertThat(result.getLevel()).isEqualTo(level);
        assertThat(result.getMessage()).isEqualTo(message);
        assertThat(result.getMdc()).isEmpty();
        assertThat(result.getThrowable()).isNull();
    }

    @Test
    void shouldCreateLogWithLevelMessageAndMdc() {
        // Given
        Level level = Level.DEBUG;
        String message = "message";
        Map<String, String> mdc = mdc();

        // When
        Log result = log(level, message, mdc);

        // Then
        assertThat(result.getLevel()).isEqualTo(level);
        assertThat(result.getMessage()).isEqualTo(message);
        assertThat(result.getMdc()).isEqualTo(mdc);
        assertThat(result.getThrowable()).isNull();
    }

    @Test
    void shouldCreateLogWithLevelMessageAndThrowable() {
        // Given
        Level level = Level.DEBUG;
        String message = "message";
        Throwable throwable = new Throwable();

        // When
        Log result = log(level, message, throwable);

        // Then
        assertThat(result.getLevel()).isEqualTo(level);
        assertThat(result.getMessage()).isEqualTo(message);
        assertThat(result.getMdc()).isEmpty();
        assertThat(result.getThrowable()).isEqualTo(throwable);
    }

    @Test
    void shouldCreateLogWithLevelMessageMdcAndThrowable() {
        // Given
        Level level = Level.DEBUG;
        String message = "message";
        Map<String, String> mdc = mdc();
        Throwable throwable = new Throwable();

        // When
        Log result = log(level, message, mdc, throwable);

        // Then
        assertThat(result.getLevel()).isEqualTo(level);
        assertThat(result.getMessage()).isEqualTo(message);
        assertThat(result.getMdc()).isEqualTo(mdc);
        assertThat(result.getThrowable()).isEqualTo(throwable);
    }

    private static Map<String, String> mdc() {
        Map<String, String> result = new HashMap<>();
        result.put("key", "value");
        return result;
    }
}
/*
 * The MIT License
 *
 * Copyright 2021 Karus Labs.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.karuslabs.elementary.junit.annotations;

import com.karuslabs.elementary.junit.ToolsExtension;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * An annotation to mark and simplify retrieval of test cases in tests extended 
 * with {@code ToolsExtension}.
 * 
 * @see com.karuslabs.elementary.junit.Cases
 */
@Usage(ToolsExtension.class)
@Documented
@Retention(RUNTIME)
public @interface Case {
    
    /**
     * A default value for {@code @Case} used to denote that the annotated target's
     * name should be used as its label.
     */
    static final String DEFAULT_LABEL = "${DEFAULT_LABEL}";
    
    /**
     * An optional label for this case.
     * 
     * @return a label
     */
    String value() default DEFAULT_LABEL;
    
}

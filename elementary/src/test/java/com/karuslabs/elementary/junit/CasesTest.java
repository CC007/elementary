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
package com.karuslabs.elementary.junit;

import com.karuslabs.elementary.junit.annotations.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ToolsExtension.class)
@Classpath("com.karuslabs.elementary.junit.CasesCases")
class CasesTest {

    Cases cases = Tools.cases();
    
    @Test
    void one() {
        assertNull(cases.one());
    }
    
    
    @Test
    void one_label() {
        assertNotNull(cases.one("a"));
        assertNotNull(cases.one("A"));
    }
    
    @Test
    void one_label_not_found() {
        assertNull(cases.one("invalid"));
    }
    
    
    @Test
    void get_index() {
        assertEquals("a", cases.get(1).getAnnotation(Case.class).value());
    }
    
    @Test
    void get_string() {
        assertEquals(1, cases.get("a").size());
    }
    
    
    @Test
    void all() {
        assertEquals(2, cases.all().size());
    }
    
    @Test
    void labels() {
        assertEquals(2, cases.labels().size());
    }
    
    @Test
    void count() {
        assertEquals(2, cases.count());
    }
    
}

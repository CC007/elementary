/*
 * The MIT License
 *
 * Copyright 2020 Karus Labs.
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
package com.karuslabs.utilitary.type;

import com.karuslabs.elementary.junit.*;
import com.karuslabs.elementary.junit.annotations.*;

import java.util.*;
import java.util.function.Consumer;
import javax.lang.model.element.*;
import javax.lang.model.type.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.karuslabs.utilitary.type.TypePrinter.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(ToolsExtension.class)
@Introspect
class TypePrinterTest {
    
    Cases cases = Tools.cases();
    @Case("declared") String declared;
    @Case("type_variables") Map<String, Integer> type_variables;
    @Case("upper_bound") class upper_bound<T extends String> {}
    @Case("wildcard_extends") List<? extends String> wildcard_extends;
    @Case("wildcard_super") List<? super String> wildcard_super;
    @Case("intersection") class Intersection<T extends Runnable & Iterable<T> & Consumer<T>> {}
    @Case("array") String[] array;
    @Case("primitive") int primitive;
    @Case("no") void no() {}
    @Case("circular") class Circular<T extends Circular<T, C>, C extends Consumer<T>> {}
    
    
    @Test
    void visitDeclared() {
        assertEquals("String", simple(cases.one("declared").asType()));
    }
    
    @Test
    void visitDeclared_type_variables() {
        assertEquals("Map<String, Integer>", simple(cases.one("type_variables").asType()));
    }
    
    @Test
    void visitTypeVariable_upper() {
        assertEquals("TypePrinterTest.upper_bound<T extends String>", simple(cases.one("upper_bound").asType()));
    }
    
    @Test
    void visitWildcard_extends() {
        assertEquals("List<? extends String>", simple(cases.one("wildcard_extends").asType()));
    }
    
    @Test
    void visitWildcard_super() {
        assertEquals("List<? super String>", simple(cases.one("wildcard_super").asType()));
    }
    
    @Test
    void visitIntersection() {
        assertEquals(
            "TypePrinterTest.Intersection<T extends Runnable & Iterable<T> & Consumer<T>>", 
            simple(cases.one("intersection").asType())
        );
    }
    
    @Test
    void visitArray() {
        assertEquals("String[]", simple(cases.one("array").asType()));
    }
    
    @Test
    void visitPrimitive() {
        assertEquals("int", simple(cases.one("primitive").asType()));
    }
    
    @Test
    void visitNoType() {
        assertEquals("void", simple(((ExecutableElement) cases.one("no")).getReturnType()));
    }
    
    @Test
    void visit_circular() {
        assertEquals("TypePrinterTest.Circular<T extends TypePrinterTest.Circular<T, C extends Consumer<T>>, C>", simple(cases.one("circular").asType()));
    }
    
    @Test
    void defaultAction() {
        TypeMirror type = when(mock(TypeMirror.class).getKind()).thenReturn(TypeKind.EXECUTABLE).getMock();
        
        assertEquals(
            "TypePrinter does not support EXECUTABLE",
            assertThrows(UnsupportedOperationException.class, () -> new SimpleTypePrinter().defaultAction(type, new StringBuilder())).getMessage()
        );
    }

}

@ExtendWith(ToolsExtension.class)
@Introspect("TypePrinterTest")
class QualifiedTypePrinterTest {
    
    Cases cases = Tools.cases();
    @Case("qualified") List<String> qualified;
    
    @Test
    void rawType() {        
        assertEquals("java.util.List<java.lang.String>", qualified(cases.one("qualified").asType()));
    }
    
    @Test
    void rawType_throws_exception() {
        Element element = mock(Element.class);
        DeclaredType type = when(mock(DeclaredType.class).asElement()).thenReturn(element).getMock();
        
        assertEquals(
            "DeclaredType should be a TypeElement",
            assertThrows(IllegalStateException.class, () -> qualified().rawType(type, new StringBuilder())).getMessage()
        );
    }
    
}

@ExtendWith(ToolsExtension.class)
@Introspect("TypePrinterTest")
class SimpleTypePrinterTest {

    Cases cases = Tools.cases();
    @Case("simple") List<String> simple;
    
    @Test
    void rawType() {
        assertEquals("List<String>", TypePrinter.simple(cases.one("simple").asType()));
    }
    
    @Test
    void rawType_throws_exception() {
        Element element = mock(Element.class);
        DeclaredType type = when(mock(DeclaredType.class).asElement()).thenReturn(element).getMock();
        
        assertEquals(
            "DeclaredType should be a TypeElement",
            assertThrows(IllegalStateException.class, () -> simple().rawType(type, new StringBuilder())).getMessage()
        );
    }
    
}
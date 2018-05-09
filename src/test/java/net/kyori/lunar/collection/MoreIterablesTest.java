/*
 * This file is part of lunar, licensed under the MIT License.
 *
 * Copyright (c) 2017-2018 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.lunar.collection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoreIterablesTest {
  @Test
  void testRandom() {
    final Collection<@NonNull String> a = Arrays.asList(
      "abc",
      "def",
      "ghi",
      "jkl"
    );
    assertNotNull(MoreIterables.random(a));
    assertTrue(a.contains(MoreIterables.random(a)));
  }

  @Test
  void testRandomNullable() {
    final Collection<@Nullable Integer> a = Arrays.asList(
      null,
      null,
      null,
      null
    );
    assertNull(MoreIterables.random(a));
    assertTrue(a.contains(MoreIterables.random(a)));
  }

  @Test
  void testRandomThrows() {
    final Collection<String> b = Collections.singletonList("abc");
    final IndexOutOfBoundsException ioobe = assertThrows(IndexOutOfBoundsException.class, () -> assertFalse(b.contains(MoreIterables.random(b, Collections.singletonList("def")))));
    assertEquals("cannot get random value from empty iterable", ioobe.getMessage());
  }
}

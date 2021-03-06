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
package net.kyori.lunar;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A collection of utilities for working with objects.
 */
public final class EvenMoreObjects {
  private EvenMoreObjects() {
  }

  /**
   * Gets a {@code T} from {@code supplier}.
   *
   * @param supplier the supplier
   * @param <T> the value type
   * @return the value
   */
  public static <T> @NonNull T get(final Supplier<T> supplier) {
    return supplier.get();
  }

  /**
   * Configures {@code value} using {@code consumer}.
   *
   * @param value the value
   * @param consumer the consumer
   * @param <T> the value type
   * @return the value
   */
  public static <T> @NonNull T make(final @NonNull T value, final @NonNull Consumer<T> consumer) {
    consumer.accept(value);
    return value;
  }

  /**
   * Configures {@code builder} using {@code consumer} and then builds it using {@code creator}.
   *
   * @param builder the builder
   * @param consumer the consumer
   * @param creator the creator
   * @param <B> the builder type
   * @param <C> the creator type
   * @return the value
   */
  public static <B, C> @NonNull C make(final @NonNull B builder, final @NonNull Consumer<B> consumer, final @NonNull Function<B, C> creator) {
    return creator.apply(make(builder, consumer));
  }

  /**
   * Tests if {@code you} equals {@code me}.
   *
   * @param me this
   * @param you that
   * @param predicate the predicate
   * @param <T> the type
   * @return {@code true} if {@code you} equals {@code me}
   */
  public static <T> boolean equals(final @NonNull T me, final @Nullable Object you, final @NonNull Predicate<T> predicate) {
    final Class<T> type = (Class<T>) me.getClass();
    return equals(type, me, you, predicate);
  }

  /**
   * Tests if {@code you} equals {@code me}.
   *
   * @param type the type of {@code me}
   * @param me this
   * @param you that
   * @param predicate the predicate
   * @param <T> the type
   * @return {@code true} if {@code you} equals {@code me}
   */
  public static <T> boolean equals(final @NonNull Class<T> type, final @NonNull T me, final @Nullable Object you, final @NonNull Predicate<T> predicate) {
    return me == you || (you != null && type.isInstance(you) && predicate.test(type.cast(you)));
  }
}
